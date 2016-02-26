package cl.signosti.kantar.consistencia.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FieldRecNew {
	private short byFill1;
	private String sname;
	private String sReClassifyFld;
	private String sDesc;
	private int nFileNum;
	private int nLevelNum;
	private short byType;
	private short byVolType;
	private short byStatus;
	private short byFill2;
	private int bDictionary;
	private int bDisplay;
	private short siStartNum;
	private short siIncrement;
	private int nNumBreaks;
	private long lTotalCount;
	private long lCodeOffset;
	private long lRefOffset;
	private long lHitOffset;
	private int nNumAlphas;
	private int nNumCodes;
	private int nMaxIndentLevel;
	private int bMutuallyExcl;
	private short byRefStorageType;
	private short bySuppressed;
	private int nRefTotHit;
	private int nRefTotRecs;
	private int nRefMaxHit;
	private int nHitArrayLen;
	private short byHitStorageType;
	private short byFill4;
	private long lGridCodeOffset;
	private int nGridNumAlphas;
	private int nGridNumCodes;
	private int bGridMutuallyExcl;
	private int nMaxPackItemRead;
	private int bZeroLine;
	private int nStatValue;
	private int nRangeAlpha;
	private short byDecimal;
	private float siFactor;
	private int bWaveField;
	private int usTimeStamp;
	private short byFill5;
	private short byFill6;
	private short byFill7;

	public FieldRecNew(File ruta) throws IOException {
		FileInputStream file = new FileInputStream(ruta);
		BufferedInputStream bufer = new BufferedInputStream(file, 8 * 1024);
		byte[] temp = new byte[148];
		bufer.read(temp, 0, 148);
		bufer.close();

		byFill1 = (short) getValue(temp, 59, 1);
		nFileNum = (int) getValue(temp, 60, 2);
		nLevelNum = (int) getValue(temp, 62, 2);
		byType = (short) getValue(temp, 64, 1);
		byVolType = (short) getValue(temp, 65, 1);
		byStatus = (short) getValue(temp, 66, 1);
		byFill2 = (short) getValue(temp, 67, 1);
		bDictionary = (int) getValue(temp, 68, 2);
		bDisplay = (int) getValue(temp, 70, 2);
		siStartNum = (short) getValue(temp, 72, 1);
		siIncrement = (short) getValue(temp, 73, 1);
		nNumBreaks = (int) getValue(temp, 74, 2);
		lTotalCount = getValue(temp, 82, 4);
		lCodeOffset = getValue(temp, 86, 4);
		lRefOffset = getValue(temp, 90, 4);
		lHitOffset = getValue(temp, 94, 4);
		nNumAlphas = (int) getValue(temp, 98, 2);
		nNumCodes = (int) getValue(temp, 100, 2);
		nMaxIndentLevel = (int) getValue(temp, 102, 2);
		bMutuallyExcl = (int) getValue(temp, 104, 2);
		byRefStorageType = (short) getValue(temp, 106, 1);
		bySuppressed = (short) getValue(temp, 107, 1);
		nRefTotHit = (int) getValue(temp, 108, 2);
		nRefTotRecs = (int) getValue(temp, 110, 2);
		nRefMaxHit = (int) getValue(temp, 112, 2);
		nHitArrayLen = (int) getValue(temp, 114, 2);
		byHitStorageType = (short) getValue(temp, 116, 1);
		byFill4 = (short) getValue(temp, 117, 1);
		lGridCodeOffset = getValue(temp, 118, 4);
		nGridNumAlphas = (int) getValue(temp, 122, 2);
		nGridNumCodes = (int) getValue(temp, 124, 2);
		bGridMutuallyExcl = (int) getValue(temp, 126, 2);
		nMaxPackItemRead = (int) getValue(temp, 128, 2);
		bZeroLine = (int) getValue(temp, 130, 2);
		nStatValue = (int) getValue(temp, 132, 2);
		nRangeAlpha = (int) getValue(temp, 134, 2);
		byDecimal = (short) getValue(temp, 136, 1);
		siFactor = Float.intBitsToFloat((int) getValue(temp, 137, 4));
		bWaveField = (int) getValue(temp, 141, 2);
		usTimeStamp = (int) getValue(temp, 143, 2);
		byFill5 = (short) getValue(temp, 145, 1);
		byFill6 = (short) getValue(temp, 146, 1);
		byFill7 = (short) getValue(temp, 147, 1);
	}

	private long getValue(byte[] b, int pos, int l) {
		long val = 0;

		int i = pos;
		for (int j = 0; j < l; j++) {
			int shift = (j * 8);
			val += (b[i] & 0x000000FF) << shift;
			i++;
		}

		return val;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getsReClassifyFld() {
		return sReClassifyFld;
	}

	public void setsReClassifyFld(String sReClassifyFld) {
		this.sReClassifyFld = sReClassifyFld;
	}

	public String getsDesc() {
		return sDesc;
	}

	public void setsDesc(String sDesc) {
		this.sDesc = sDesc;
	}

	public int getnFileNum() {
		return nFileNum;
	}

	public void setnFileNum(int nFileNum) {
		this.nFileNum = nFileNum;
	}

	public int getnLevelNum() {
		return nLevelNum;
	}

	public void setnLevelNum(int nLevelNum) {
		this.nLevelNum = nLevelNum;
	}

	public short getByType() {
		return byType;
	}

	public void setByType(short byType) {
		this.byType = byType;
	}

	public short getByVolType() {
		return byVolType;
	}

	public void setByVolType(short byVolType) {
		this.byVolType = byVolType;
	}

	public short getByStatus() {
		return byStatus;
	}

	public void setByStatus(short byStatus) {
		this.byStatus = byStatus;
	}

	public short getByFill2() {
		return byFill2;
	}

	public void setByFill2(short byFill2) {
		this.byFill2 = byFill2;
	}

	public int getbDictionary() {
		return bDictionary;
	}

	public void setbDictionary(int bDictionary) {
		this.bDictionary = bDictionary;
	}

	public int getbDisplay() {
		return bDisplay;
	}

	public void setbDisplay(int bDisplay) {
		this.bDisplay = bDisplay;
	}

	public short getSiStartNum() {
		return siStartNum;
	}

	public void setSiStartNum(short siStartNum) {
		this.siStartNum = siStartNum;
	}

	public short getSiIncrement() {
		return siIncrement;
	}

	public void setSiIncrement(short siIncrement) {
		this.siIncrement = siIncrement;
	}

	public int getnNumBreaks() {
		return nNumBreaks;
	}

	public void setnNumBreaks(int nNumBreaks) {
		this.nNumBreaks = nNumBreaks;
	}

	public long getlTotalCount() {
		return lTotalCount;
	}

	public void setlTotalCount(long lTotalCount) {
		this.lTotalCount = lTotalCount;
	}

	public long getlCodeOffset() {
		return lCodeOffset;
	}

	public void setlCodeOffset(long lCodeOffset) {
		this.lCodeOffset = lCodeOffset;
	}

	public long getlRefOffset() {
		return lRefOffset;
	}

	public void setlRefOffset(long lRefOffset) {
		this.lRefOffset = lRefOffset;
	}

	public long getlHitOffset() {
		return lHitOffset;
	}

	public void setlHitOffset(long lHitOffset) {
		this.lHitOffset = lHitOffset;
	}

	public int getnNumAlphas() {
		return nNumAlphas;
	}

	public void setnNumAlphas(int nNumAlphas) {
		this.nNumAlphas = nNumAlphas;
	}

	public int getnNumCodes() {
		return nNumCodes;
	}

	public void setnNumCodes(int nNumCodes) {
		this.nNumCodes = nNumCodes;
	}

	public int getnMaxIndentLevel() {
		return nMaxIndentLevel;
	}

	public void setnMaxIndentLevel(int nMaxIndentLevel) {
		this.nMaxIndentLevel = nMaxIndentLevel;
	}

	public int getbMutuallyExcl() {
		return bMutuallyExcl;
	}

	public void setbMutuallyExcl(int bMutuallyExcl) {
		this.bMutuallyExcl = bMutuallyExcl;
	}

	public short getByRefStorageType() {
		return byRefStorageType;
	}

	public void setByRefStorageType(short byRefStorageType) {
		this.byRefStorageType = byRefStorageType;
	}

	public short getBySuppressed() {
		return bySuppressed;
	}

	public void setBySuppressed(short bySuppressed) {
		this.bySuppressed = bySuppressed;
	}

	public int getnRefTotHit() {
		return nRefTotHit;
	}

	public void setnRefTotHit(int nRefTotHit) {
		this.nRefTotHit = nRefTotHit;
	}

	public int getnRefTotRecs() {
		return nRefTotRecs;
	}

	public void setnRefTotRecs(int nRefTotRecs) {
		this.nRefTotRecs = nRefTotRecs;
	}

	public int getnRefMaxHit() {
		return nRefMaxHit;
	}

	public void setnRefMaxHit(int nRefMaxHit) {
		this.nRefMaxHit = nRefMaxHit;
	}

	public int getnHitArrayLen() {
		return nHitArrayLen;
	}

	public void setnHitArrayLen(int nHitArrayLen) {
		this.nHitArrayLen = nHitArrayLen;
	}

	public short getByHitStorageType() {
		return byHitStorageType;
	}

	public void setByHitStorageType(short byHitStorageType) {
		this.byHitStorageType = byHitStorageType;
	}

	public short getByFill4() {
		return byFill4;
	}

	public void setByFill4(short byFill4) {
		this.byFill4 = byFill4;
	}

	public long getlGridCodeOffset() {
		return lGridCodeOffset;
	}

	public void setlGridCodeOffset(long lGridCodeOffset) {
		this.lGridCodeOffset = lGridCodeOffset;
	}

	public int getnGridNumAlphas() {
		return nGridNumAlphas;
	}

	public void setnGridNumAlphas(int nGridNumAlphas) {
		this.nGridNumAlphas = nGridNumAlphas;
	}

	public int getnGridNumCodes() {
		return nGridNumCodes;
	}

	public void setnGridNumCodes(int nGridNumCodes) {
		this.nGridNumCodes = nGridNumCodes;
	}

	public int getbGridMutuallyExcl() {
		return bGridMutuallyExcl;
	}

	public void setbGridMutuallyExcl(int bGridMutuallyExcl) {
		this.bGridMutuallyExcl = bGridMutuallyExcl;
	}

	public int getnMaxPackItemRead() {
		return nMaxPackItemRead;
	}

	public void setnMaxPackItemRead(int nMaxPackItemRead) {
		this.nMaxPackItemRead = nMaxPackItemRead;
	}

	public int getbZeroLine() {
		return bZeroLine;
	}

	public void setbZeroLine(int bZeroLine) {
		this.bZeroLine = bZeroLine;
	}

	public int getnStatValue() {
		return nStatValue;
	}

	public void setnStatValue(int nStatValue) {
		this.nStatValue = nStatValue;
	}

	public int getnRangeAlpha() {
		return nRangeAlpha;
	}

	public void setnRangeAlpha(int nRangeAlpha) {
		this.nRangeAlpha = nRangeAlpha;
	}

	public short getByDecimal() {
		return byDecimal;
	}

	public void setByDecimal(short byDecimal) {
		this.byDecimal = byDecimal;
	}

	public float getSiFactor() {
		return siFactor;
	}

	public void setSiFactor(float siFactor) {
		this.siFactor = siFactor;
	}

	public int getbWaveField() {
		return bWaveField;
	}

	public void setbWaveField(int bWaveField) {
		this.bWaveField = bWaveField;
	}

	public int getUsTimeStamp() {
		return usTimeStamp;
	}

	public void setUsTimeStamp(int usTimeStamp) {
		this.usTimeStamp = usTimeStamp;
	}

	public short getByFill5() {
		return byFill5;
	}

	public void setByFill5(short byFill5) {
		this.byFill5 = byFill5;
	}

	public short getByFill6() {
		return byFill6;
	}

	public void setByFill6(short byFill6) {
		this.byFill6 = byFill6;
	}

	public short getByFill7() {
		return byFill7;
	}

	public void setByFill7(short byFill7) {
		this.byFill7 = byFill7;
	}

	public short getByFill1() {
		return byFill1;
	}

	public void setByFill1(short byFill1) {
		this.byFill1 = byFill1;
	}

}
