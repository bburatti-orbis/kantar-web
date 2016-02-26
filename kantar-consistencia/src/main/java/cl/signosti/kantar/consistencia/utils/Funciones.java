package cl.signosti.kantar.consistencia.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Stack;

import org.apache.log4j.Logger;

import cl.signosti.kantar.consistencia.dao.EjecucionesDao;
import cl.signosti.kantar.consistencia.dao.MarcaDao;
import cl.signosti.kantar.consistencia.dao.NomesclaturaDao;
import cl.signosti.kantar.consistencia.dao.locator.LocatorDao;
import cl.signosti.kantar.consistencia.modelo.Marcam;

public class Funciones {
	private static final Logger logger = Logger.getLogger(Funciones.class);
	MarcaDao mar = LocatorDao.getMarcaDao();
	EjecucionesDao ejecucion = LocatorDao.getEjecucionesDao();
	NomesclaturaDao nom = LocatorDao.getNomenclarurasDao();

	public boolean procesarIT2(String ruta, int idnomen, int idejec)
			throws IOException {

		FileInputStream file = new FileInputStream(ruta);
		Marcam marca = new Marcam();
		Stack<Nivel> itStack = new Stack<Nivel>();
		String rutasalida = "C:\\Users\\Jose\\Desktop\\salida.txt";
		BufferedInputStream bufer = new BufferedInputStream(file, 8 * 1024);
		FileWriter fichero = new FileWriter(rutasalida);
		byte[] temp = new byte[152];
		byte[] rec = new byte[88];

		try {

			bufer.read(temp, 0, 152);
			String FileName = new String(temp, "UTF-8");
			String f[] = FileName.split("\0");
			fichero.write(f[0] + "\r\n");

			int regs = getRecs(temp);
			Nivel nvl = null;

			for (int j = 0; j < regs; j++) {
				bufer.read(rec, 0, 88);
				String line = new String(rec, "UTF-8");
				String[] lin = line.split("\0");
				int nivel = getNivel(rec);

				marca.setGlosa(lin[0]);
				marca.setLinea(j);

				if (itStack.empty()) {
					nvl = new Nivel();
					nvl.setNroNivel(nivel);
					nvl.setTotal(0);
					itStack.push(nvl);
				} else {
					nvl = itStack.peek();
					while (!itStack.empty() && nivel < nvl.getNroNivel()) {
						Nivel lst = itStack.pop();
						nvl = itStack.peek();
						if (nvl.getValor() != lst.getTotal()) {
							nvl.setEstado(1);
							nvl.setSumaHijos(lst.getTotal());
							setError(nvl, idnomen, idejec);

						}
					}

					if (nivel > nvl.getNroNivel()) {
						Nivel newNivel = new Nivel();
						newNivel.setNroNivel(nivel);
						newNivel.setTotal(0);
						itStack.push(newNivel);
						nvl = itStack.peek();
					}

				}

				fichero.write(String.valueOf(nivel) + " ");
				fichero.write(lin[0] + ": ");

				int total = getTotal(rec);
				marca.setValor(total);
				marca.setNivel(nvl.getNroNivel());
				marca.setNomenclatura(idnomen);
				nvl.setValor(total);
				nvl.setTotal(nvl.getTotal() + total);

				fichero.write(String.valueOf(total));
				fichero.write("\r\n");

				marca.setEstadoCinterna(3);
				int id_ultimo_registro = mar.setMarca(marca);
				nvl.setId(id_ultimo_registro);
			}

			while (!itStack.empty()) {
				Nivel lst = itStack.pop();
				if (!itStack.empty()) {
					nvl = itStack.peek();
					if (nvl.getValor() != lst.getTotal()) {
						nvl.setEstado(1);
						nvl.setSumaHijos(lst.getTotal());
						setError(nvl, idnomen, idejec);
					}
				}
			}

		} catch (Exception e) {
				 logger.error("Error, causa:" ,e);
		} finally {
			fichero.close();
		}
		bufer.close();
		return false;

	}

	static private int getTotal(byte[] b) {
		int total = 0;

		int i = 46;
		int j = 0;
		while (b[i] != 0 && i < 51) {
			int shift = (j * 8);
			total += (b[i] & 0x000000FF) << shift;
			j++;
			i++;
		}

		return total;
	}

	static private int getRecs(byte[] b) {
		int recs = 0;

		int i = 98;
		int j = 0;
		while (b[i] != 0 && i < 100) {
			int shift = (j * 8);
			recs += (b[i] & 0x000000FF) << shift;
			j++;
			i++;
		}

		return recs;
	}

	static private int getNivel(byte[] b) {

		return (int) b[40];
	}

	private void setError(Nivel nivel, int nome, int idejec)
			throws SQLException {

		mar.setEstadoError(nivel);
		nom.setErrornomenclatura(nome);
		ejecucion.setErrorejecucion(idejec);

	}

}
