package cl.signosti.kantar.consistencia.Service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.Stack;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.MarcaDao;
import cl.signosti.kantar.consistencia.dao.NomesclaturaDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Marcam;
import cl.signosti.kantar.consistencia.utils.CodeRecNew;
import cl.signosti.kantar.consistencia.utils.FieldRecNew;
import cl.signosti.kantar.consistencia.utils.Nivel;

public class ModFunciones {
	
	MarcaDao mar = LocatorDao.getMarcaDao();

	EjecucionesDao ejecucion = LocatorDao.getEjecucionesDao();
	NomesclaturaDao nom = LocatorDao.getNomenclarurasDao();
	private static final Logger logger = Logger.getLogger(ModFunciones.class);

	Stack<Nivel> itStack = new Stack<Nivel>();
	FieldRecNew myWeightFieldRec = null;
	FieldRecNew myIT0FieldRec = null;
	boolean WeightHasDecode = false;
	boolean WeightHasRecord = false;
	boolean IT0HasDecode = false;
	boolean IT0HasRecord = false;
	long[][] myWeightDecodeArray = null;
	long[][] myWeightRecordArray = null;
	long[][] myIT0DecodeArray = null;
	long[][] myIT0RecordArray = null;
	int[] myDecodes = null;
	int[] myWeights = null;
	long WeightRecordLines = 0;
	long IT0RecordLines = 0;
	int WeightRecordCol = 0;
	int IT0NumItems = 0;
	int IT0DecodeLines = 0;
	CodeRecNew[] myIT0CodeRecArray = null;

	public void ejecutarVerificacion(File data, int nom, String mon, int idejc)
			throws IOException {
		try {
			int año = 0;

			File PVWeight = new File(mon);

//			File folder = data;
//			int filaIT0 = 0;
			File PVField = data;
			String[] ext = PVField.getName().split("\\.");
			if ("IT0".equals(ext[ext.length - 1].toUpperCase())) {

				año = getAño(ext[0]);

			}
			OpenDemographic(PVField, PVWeight, año, nom, idejc);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return;

	}

	private String OpenDemographic(File PVField, File PVWeight, int año,
			int nom, int idejc) throws IOException {
		String ret = "L";
		Nivel nvl = null;
		Marcam marca = new Marcam();
		

//		String[] fileName = PVField.getName().split("\\.");

		if (PVWeight != null) {
			OpenWeights(PVWeight, año);
		}

		OpenIT0Itemisation(PVField);

		int[] myHHolds = new int[(int) WeightRecordLines];
		for (int i = 0; i < (int) WeightRecordLines; i++) {

			for (int j = 0; j < WeightRecordCol; j++) {
				if (myWeights[j] == 1 && myWeightRecordArray[i][j] > 0) {
					myHHolds[i] = 1;
					break;
				}
			}

		}

		for (int i = 0; i < IT0NumItems; i++) {
			for (int j = 0; j < IT0DecodeLines; j++) {
				myDecodes[j] = 0;
			}
			for (int j = 0; j < IT0DecodeLines; j++) {
				for (int k = 1; k <= myIT0DecodeArray[j][0]; k++) {
					if (myIT0DecodeArray[j][k] == i) {
						myDecodes[j] = 1;
						break;
					}
				}
			}

			int lCount = 0;
			for (int j = 0; j < IT0RecordLines; j++) {
				if (myHHolds[j] == 1
						&& myDecodes[(int) myIT0RecordArray[j][0]] == 1) {
					lCount++;
				}
			}
//			String descs = myIT0CodeRecArray[i].getsDesc();
			int nivel = myIT0CodeRecArray[i].getByIndentLevel() - 1;
			marca.setEstadoCinterna(3);
			marca.setGlosa(myIT0CodeRecArray[i].getsDesc());
			marca.setLinea(i);
			marca.setNivel(nivel);
			marca.setValor(lCount);

			marca.setNomenclatura(nom);

			int ult_reg = mar.setMarca(marca);

			if (itStack.empty()) {
				nvl = new Nivel();
				nvl.setNroNivel(nivel);
				nvl.setTotal(0);
				nvl.setId(ult_reg);
				itStack.push(nvl);

			} else {
				nvl = itStack.peek();
				while (!itStack.empty() && nivel < nvl.getNroNivel()) {
					Nivel lst = itStack.pop();
					nvl = itStack.peek();
					if (nvl.getValor() != lst.getTotal()) {
						nvl.setEstado(1);
						nvl.setSumaHijos(lst.getTotal());

						mar.setEstadoError(nvl);

					}

				}
				if (nivel > nvl.getNroNivel()) {
					Nivel newNivel = new Nivel();
					newNivel.setNroNivel(nivel);
					newNivel.setTotal(0);
					newNivel.setId(ult_reg);
					itStack.push(newNivel);

					nvl = itStack.peek();
				}
				nvl.setId(ult_reg);
				nvl.setValor(lCount);
				nvl.setTotal(nvl.getTotal() + lCount);
			}

		}
		while (!itStack.empty()) {
			Nivel lst = itStack.pop();
			if (!itStack.empty()) {
				nvl = itStack.peek();
				if (nvl.getValor() != lst.getTotal()) {
					nvl.setEstado(1);
					nvl.setSumaHijos(lst.getTotal());
					try {
						setError(nvl, nom, idejc);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return ret;
	}

	public void OpenIT0Itemisation(File PVField) throws IOException {
		IT0NumItems = 0;
		myIT0FieldRec = new FieldRecNew(PVField);

		// Chequeo que sea existan líneas y preparo los Arrays
		IT0NumItems = myIT0FieldRec.getnNumCodes();
		if (IT0NumItems == 0) {
			IT0NumItems = myIT0FieldRec.getnGridNumAlphas();
		}
		if (IT0NumItems == 0) {
			// TODO
			// MsgBox "Invalid File", vbCritical, "No lines"
			return;
		}

		// Leo los CodeRecs
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(PVField)));
		in.skipBytes(149);
		byte[] b = new byte[88];
		myIT0CodeRecArray = new CodeRecNew[IT0NumItems];
		for (int i = 0; i < IT0NumItems; i++) {
			in.read(b, 0, 88);
			CodeRecNew codeRecNew = new CodeRecNew(b);
			myIT0CodeRecArray[i] = codeRecNew;
		}
		in.close();
		// Leo la sección Decode, y si no existe leo la sección Record
		ReadIT0Decode(PVField);

		ReadIT0Record(PVField);
	}

	private void ReadIT0Record(File PVField) throws IOException {
		if (myIT0FieldRec.getlHitOffset() == -1) {
			return;
		}
		IT0HasRecord = true;
		IT0RecordLines = myIT0FieldRec.getlTotalCount();
		int IT0RecordCol = 1;
		if (myIT0FieldRec.getnHitArrayLen() > 1) {
			IT0RecordCol = myIT0FieldRec.getnHitArrayLen();
		}
		myIT0RecordArray = new long[(int) IT0RecordLines][IT0RecordCol];

		byte[] buffer = new byte[2];

		DataInputStream in = null;
		try {
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(PVField)));

			for (int Rec = 1; Rec <= IT0RecordLines; Rec++) {
				if (Rec == 1) {
					in.skipBytes((int) myIT0FieldRec.getlHitOffset());
				}

				for (int Rec2 = 1; Rec2 <= IT0RecordCol; Rec2++) {
					switch (myIT0FieldRec.getByHitStorageType()) {
					case 1:
						myIT0RecordArray[Rec - 1][Rec2 - 1] = (long) in
								.readUnsignedByte();
						break;
					case 2:
						in.read(buffer);
						myIT0RecordArray[Rec - 1][Rec2 - 1] = (buffer[0] & 0xff)
								| (buffer[1] & 0xff) << 8;
						break;
					case 4:
						myIT0RecordArray[Rec - 1][Rec2 - 1] = (long) in
								.readFloat();
						break;
					}
				}
			}
		} finally {
			in.close();
		}
	}

	private void ReadIT0Decode(File PVField) throws IOException {
		long L = 0L;
		if (myIT0FieldRec.getlRefOffset() == -1) {
			return;
		}

		IT0HasDecode = true;
		IT0DecodeLines = myIT0FieldRec.getnRefTotRecs();
		int IT0DecodeCol = myIT0FieldRec.getnRefMaxHit();
		if (IT0DecodeLines < 0) {
			IT0DecodeLines = 65536 + IT0DecodeLines;
		}
		myIT0DecodeArray = new long[IT0DecodeLines][IT0DecodeCol];
		myDecodes = new int[IT0DecodeLines];
		DataInputStream in = null;
		byte[] buf2 = new byte[2];

		try {
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(PVField)));

			for (int Rec = 1; Rec <= IT0DecodeLines; Rec++) {
				if (Rec == 1) {
					in.skipBytes((int) myIT0FieldRec.getlRefOffset());
				}
				switch (myIT0FieldRec.getByRefStorageType()) {
				case 1:
					L = (long) in.readUnsignedByte();
					break;
				case 2:
					in.read(buf2);
					L = (buf2[0] & 0xff) | (buf2[1] & 0xff) << 8;
					break;
				case 4:
					L = in.readLong();
					break;
				}

				myIT0DecodeArray[Rec - 1][0] = L;

				for (int Rec2 = 1; Rec2 < IT0DecodeCol; Rec2++) {
					switch (myIT0FieldRec.getByRefStorageType()) {
					case 1:
						myIT0DecodeArray[Rec - 1][Rec2] = (long) in
								.readUnsignedByte();
						break;
					case 2:
						in.read(buf2);
						myIT0DecodeArray[Rec - 1][Rec2] = (buf2[0] & 0xff)
								| (buf2[1] & 0xff) << 8;
						break;
					case 4:
						myIT0DecodeArray[Rec - 1][Rec2] = in.readLong();
						break;
					}
				}
			}
		} finally {
			in.close();
		}
	}

	public int[] OpenWeights(File PVField, int año) throws IOException {
		myWeights = new int[] { 0 };
		int WeightNumItems = 0;
		myWeightFieldRec = new FieldRecNew(PVField);

		WeightNumItems = myWeightFieldRec.getnNumCodes();
		if (WeightNumItems == 0) {
			WeightNumItems = myWeightFieldRec.getnGridNumAlphas();
		}
		if (WeightNumItems == 0) {
			logger.error("Invalid File, ¡No lines!");
			return myWeights;
		}

		myWeights = new int[WeightNumItems];
		for (int i = 0; i < myWeights.length; i++) {
			myWeights[i] = 0;
		}

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int modYear = year % 10;
		int decade = (year / 10) * 10;

		FileInputStream file = new FileInputStream(PVField);
		BufferedInputStream bufer = new BufferedInputStream(file);
		byte[] temp = new byte[88];
		String descs = null;
		int last = 0;

		bufer.skip(149);
		bufer.mark(0);
		if (año == 0) {
			// read number of bytes available

			bufer.skip((WeightNumItems - 1) * 88);

			bufer.read(temp, 0, 88);
			descs = new String(temp).substring(3, 4 + 33).trim();
			last = Integer.parseInt(descs.substring(descs.length() - 1));

			if (last <= modYear) {
				año = decade + last;
			} else {
				año = decade - 10 + last;
			}

		}

		int lastAño = (año % 10);

		if (año > 0) {

			bufer.reset();
			for (int i = 0; i <= WeightNumItems - 1; i++) {
				temp = new byte[88];
				bufer.read(temp, 0, 88);

				descs = new String(temp).substring(3, 4 + 33).trim();
				last = Integer.parseInt(descs.substring(descs.length() - 1));

				if (last == lastAño) {
					myWeights[i] = 1;
				}

			}
			bufer.close();
		}

		// Leo la sección Decode, y si no existe leo la sección Record
		ReadWeightDecode(PVField);

		ReadWeightRecord(PVField);

		return myWeights;
	}

	private void ReadWeightRecord(File PVField) throws IOException {
		if (myWeightFieldRec.getlHitOffset() == -1) {
			WeightHasRecord = false;
			return;
		}
		WeightHasRecord = true;
		WeightRecordLines = myWeightFieldRec.getlTotalCount();
		WeightRecordCol = 1;
		if (myWeightFieldRec.getnHitArrayLen() > 1) {
			WeightRecordCol = myWeightFieldRec.getnHitArrayLen();
		}
		myWeightRecordArray = new long[(int) WeightRecordLines][WeightRecordCol];

		long L = 0;
		byte[] b4 = new byte[4];
		byte[] b2 = new byte[2];
//		int lCount = 0;

		DataInputStream in = null;
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(PVField);
			in = new DataInputStream(new BufferedInputStream(inFile));

			for (int Rec = 1; Rec <= WeightRecordLines; Rec++) {
				if (Rec == 1) {
					in.skipBytes((int) myWeightFieldRec.getlHitOffset());
				}

				for (int Rec2 = 1; Rec2 <= WeightRecordCol; Rec2++) {
					switch (myWeightFieldRec.getByHitStorageType()) {
					case 1:
						L = in.readUnsignedByte();
						break;
					case 2:
						in.readFully(b2);
						L = (b2[1] & 0xff) << 8 | (b2[0] & 0xff);
						break;
					case 4:
						in.readFully(b4, 0, 4);
						L = (long) Float.intBitsToFloat((b4[3] << 24)
								| (b4[2] & 0xff) << 16 | (b4[1] & 0xff) << 8
								| (b4[0] & 0xff));
						break;
					}
					if (L <= 0) {
//						lCount++;
					}
					myWeightRecordArray[Rec - 1][Rec2 - 1] = L;
				}

			}
		} finally {
			in.close();
			inFile.close();
		}
	}

	private void ReadWeightDecode(File PVField) throws IOException {
		long L = 0L;

		if (myWeightFieldRec.getlRefOffset() == -1) {
			WeightHasDecode = false;
			return;
		}

		WeightHasDecode = true;
		int WeightDecodeLines = myWeightFieldRec.getnRefTotRecs();
		int WeightDecodeCol = myWeightFieldRec.getnRefMaxHit();
		if (WeightDecodeLines < 0) {
			WeightDecodeLines = 65536 + WeightDecodeLines;
		}

		myWeightDecodeArray = new long[WeightDecodeLines][WeightDecodeCol];

		DataInputStream in = null;
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(PVField);
			in = new DataInputStream(new BufferedInputStream(inFile));

			for (int Rec = 1; Rec < WeightDecodeLines; Rec++) {
				if (Rec == 1) {
					in.skipBytes((int) myWeightFieldRec.getlRefOffset());
				}
				switch (myWeightFieldRec.getByRefStorageType()) {
				case 1:
					L = (long) in.readUnsignedByte();
					break;
				case 2:
					L = (long) in.readInt();
					break;
				case 3:
					L = in.readLong();
					break;
				}

				myWeightDecodeArray[Rec - 1][0] = L;

				for (int Rec2 = 1; Rec2 < WeightDecodeCol; Rec2++) {
					switch (myWeightFieldRec.getByRefStorageType()) {
					case 1:
						L = (long) in.readUnsignedByte();
						break;
					case 2:
						L = (long) in.readInt();
						break;
					case 3:
						L = in.readLong();
						break;
					}
					myWeightDecodeArray[Rec - 1][Rec2] = L;
				}
			}
		} finally {
			in.close();
			inFile.close();
		}
	}

	private int getAño(String PVField) {
		int año = 0;
		int lastCaracter = isNumeric(PVField.substring(PVField.length() - 1)) ? Integer
				.parseInt(PVField.substring(PVField.length() - 1)) : -1;
		switch (lastCaracter) {
		case 9:
			año = 2000 + lastCaracter;
			break;
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			año = 2010 + lastCaracter;
			break;
		}

		if ("L_ID1219".equals(PVField.toUpperCase())
				|| "L_REG10".equals(PVField.toUpperCase())
				|| "R_LA123".equals(PVField.toUpperCase())
				|| "R_REGIO2".equals(PVField.toUpperCase())
				|| "L_REG2".equals(PVField.toUpperCase())
				|| "L_CITY2".equals(PVField.toUpperCase())
				|| "CITY2".equals(PVField.toUpperCase())) {
			año = 0;
		}

		String tmp = PVField.substring(PVField.length() - 2).toUpperCase();
		if ("Q1".equals(tmp) || "Q2".equals(tmp) || "Q3".equals(tmp)
				|| "Q4".equals(tmp)) {
			lastCaracter = Integer.parseInt(PVField.substring(
					PVField.length() - 4, PVField.length() - 3));
			switch (lastCaracter) {
			case 9:
				año = 2000 + lastCaracter;
				break;
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				año = 2010 + lastCaracter;
				break;
			default:
				año = 0;
			}
		}

		return año;
	}

	public boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

//	private void copyFile(String source, String dest) {
//		InputStream inStream = null;
//		OutputStream outStream = null;
//
//		try {
//
//			File afile = new File(source);
//			File bfile = new File(dest);
//
//			inStream = new FileInputStream(afile);
//			outStream = new FileOutputStream(bfile);
//
//			byte[] buffer = new byte[1024];
//
//			int length;
//			// copy the file content in bytes
//			while ((length = inStream.read(buffer)) > 0) {
//
//				outStream.write(buffer, 0, length);
//
//			}
//
//			inStream.close();
//			outStream.close();
//
//			logger.info("Archivo: " + dest + " copiado exitosamente!");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	private void setError(Nivel nivel, int nome, int idejec)
			throws SQLException {

		mar.setEstadoError(nivel);
		nom.setErrornomenclatura(nome);
		ejecucion.setErrorejecucion(idejec);

	}

}
