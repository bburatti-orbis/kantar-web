package cl.signosti.kantar.consistencia.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CodeRecNew {
	private int nAlphaLine;
	private int nCodeLine;
	private String sDesc;
	private byte byFill1;
	private int nHeadingLine;
	private int nHeadingStart;
	private int nHeadingEnd;
	private byte byIndentLevel;
	private byte byNumLeadBlanks;
	private byte byNumSkipLines;
	private byte byUnderline;
	private byte byBold;
	private byte byItalic;
	private long lTotCounts;
	private float siFactor;
	private float siVolFrom;
	private float siVolTo;
	private byte byVolFromOper;
	private byte byVolToOper;
	private int bDisplay;
	private int bInclude;
	private int nRangeAlpha;
	private byte byRowBreak;
	private byte byColBreak;
	private byte byWidth;
	private byte byDecimal;
	private int bSuppressHide;
	private byte byFill2;
	private byte byFill3;
	private byte byFill4;
	private byte byFill5;
	private byte byFill6;
	private byte byFill7;
	private byte byFill8;

	public CodeRecNew(byte[] b) {
		nAlphaLine = (int) getValue(b, 0, 2);
		nCodeLine = (int) getValue(b, 2, 2);
		sDesc = new String(b).substring(3, 33 + 3).trim();
		byFill1 = b[36];
		nHeadingLine = (int) getValue(b, 37, 2);
		nHeadingStart = (int) getValue(b, 39, 2);
		nHeadingEnd = (int) getValue(b, 41, 2);
		byIndentLevel = b[43];
		byNumLeadBlanks = b[44];
		byNumSkipLines = b[45];
		byUnderline = b[46];
		byBold = b[47];
		byItalic = b[48];
		lTotCounts = getValue(b, 49, 4);
		siFactor = Float.intBitsToFloat((int) getValue(b, 53, 4));
		siVolFrom = Float.intBitsToFloat((int) getValue(b, 57, 4));
		siVolTo = Float.intBitsToFloat((int) getValue(b, 61, 4));
		byVolFromOper = b[67];
		byVolToOper = b[66];
		bDisplay = (int) getValue(b, 67, 2);
		bInclude = (int) getValue(b, 69, 2);
		nRangeAlpha = (int) getValue(b, 71, 2);
		byRowBreak = b[73];
		byColBreak = b[74];
		byWidth = b[75];
		byDecimal = b[76];
		bSuppressHide = (int) getValue(b, 77, 2);
	}

	public CodeRecNew(File file) throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(file)));

		nAlphaLine = in.readInt();
		nCodeLine = in.readInt();
		sDesc = in.readUTF();
		byFill1 = in.readByte();
		nHeadingLine = in.readInt();
		nHeadingStart = in.readInt();
		nHeadingEnd = in.readInt();
		byIndentLevel = in.readByte();
		byNumLeadBlanks = in.readByte();
		byNumSkipLines = in.readByte();
		byUnderline = in.readByte();
		byBold = in.readByte();
		byItalic = in.readByte();
		lTotCounts = in.readLong();
		siFactor = in.readFloat();
		siVolFrom = in.readFloat();
		siVolTo = in.readFloat();
		byVolFromOper = in.readByte();
		byVolToOper = in.readByte();
		bDisplay = in.readInt();
		bInclude = in.readInt();
		nRangeAlpha = in.readInt();
		byRowBreak = in.readByte();
		byColBreak = in.readByte();
		byWidth = in.readByte();
		byDecimal = in.readByte();
		bSuppressHide = in.readInt();
		byFill2 = in.readByte();
		byFill3 = in.readByte();
		byFill4 = in.readByte();
		byFill5 = in.readByte();
		byFill6 = in.readByte();
		byFill7 = in.readByte();
		byFill8 = in.readByte();

		in.close();
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

	public int getnAlphaLine() {
		return nAlphaLine;
	}

	public void setnAlphaLine(int nAlphaLine) {
		this.nAlphaLine = nAlphaLine;
	}

	public int getnCodeLine() {
		return nCodeLine;
	}

	public void setnCodeLine(int nCodeLine) {
		this.nCodeLine = nCodeLine;
	}

	public String getsDesc() {
		return sDesc;
	}

	public void setsDesc(String sDesc) {
		this.sDesc = sDesc;
	}

	public byte getByFill1() {
		return byFill1;
	}

	public void setByFill1(byte byFill1) {
		this.byFill1 = byFill1;
	}

	public int getnHeadingLine() {
		return nHeadingLine;
	}

	public void setnHeadingLine(int nHeadingLine) {
		this.nHeadingLine = nHeadingLine;
	}

	public int getnHeadingStart() {
		return nHeadingStart;
	}

	public void setnHeadingStart(int nHeadingStart) {
		this.nHeadingStart = nHeadingStart;
	}

	public int getnHeadingEnd() {
		return nHeadingEnd;
	}

	public void setnHeadingEnd(int nHeadingEnd) {
		this.nHeadingEnd = nHeadingEnd;
	}

	public byte getByIndentLevel() {
		return byIndentLevel;
	}

	public void setByIndentLevel(byte byIndentLevel) {
		this.byIndentLevel = byIndentLevel;
	}

	public byte getByNumLeadBlanks() {
		return byNumLeadBlanks;
	}

	public void setByNumLeadBlanks(byte byNumLeadBlanks) {
		this.byNumLeadBlanks = byNumLeadBlanks;
	}

	public byte getByNumSkipLines() {
		return byNumSkipLines;
	}

	public void setByNumSkipLines(byte byNumSkipLines) {
		this.byNumSkipLines = byNumSkipLines;
	}

	public byte getByUnderline() {
		return byUnderline;
	}

	public void setByUnderline(byte byUnderline) {
		this.byUnderline = byUnderline;
	}

	public byte getByBold() {
		return byBold;
	}

	public void setByBold(byte byBold) {
		this.byBold = byBold;
	}

	public byte getByItalic() {
		return byItalic;
	}

	public void setByItalic(byte byItalic) {
		this.byItalic = byItalic;
	}

	public long getlTotCounts() {
		return lTotCounts;
	}

	public void setlTotCounts(long lTotCounts) {
		this.lTotCounts = lTotCounts;
	}

	public float getSiFactor() {
		return siFactor;
	}

	public void setSiFactor(float siFactor) {
		this.siFactor = siFactor;
	}

	public float getSiVolFrom() {
		return siVolFrom;
	}

	public void setSiVolFrom(float siVolFrom) {
		this.siVolFrom = siVolFrom;
	}

	public float getSiVolTo() {
		return siVolTo;
	}

	public void setSiVolTo(float siVolTo) {
		this.siVolTo = siVolTo;
	}

	public byte getByVolFromOper() {
		return byVolFromOper;
	}

	public void setByVolFromOper(byte byVolFromOper) {
		this.byVolFromOper = byVolFromOper;
	}

	public byte getByVolToOper() {
		return byVolToOper;
	}

	public void setByVolToOper(byte byVolToOper) {
		this.byVolToOper = byVolToOper;
	}

	public int getbDisplay() {
		return bDisplay;
	}

	public void setbDisplay(int bDisplay) {
		this.bDisplay = bDisplay;
	}

	public int getbInclude() {
		return bInclude;
	}

	public void setbInclude(int bInclude) {
		this.bInclude = bInclude;
	}

	public int getnRangeAlpha() {
		return nRangeAlpha;
	}

	public void setnRangeAlpha(int nRangeAlpha) {
		this.nRangeAlpha = nRangeAlpha;
	}

	public byte getByRowBreak() {
		return byRowBreak;
	}

	public void setByRowBreak(byte byRowBreak) {
		this.byRowBreak = byRowBreak;
	}

	public byte getByColBreak() {
		return byColBreak;
	}

	public void setByColBreak(byte byColBreak) {
		this.byColBreak = byColBreak;
	}

	public byte getByWidth() {
		return byWidth;
	}

	public void setByWidth(byte byWidth) {
		this.byWidth = byWidth;
	}

	public byte getByDecimal() {
		return byDecimal;
	}

	public void setByDecimal(byte byDecimal) {
		this.byDecimal = byDecimal;
	}

	public int getbSuppressHide() {
		return bSuppressHide;
	}

	public void setbSuppressHide(int bSuppressHide) {
		this.bSuppressHide = bSuppressHide;
	}

	public byte getByFill2() {
		return byFill2;
	}

	public void setByFill2(byte byFill2) {
		this.byFill2 = byFill2;
	}

	public byte getByFill3() {
		return byFill3;
	}

	public void setByFill3(byte byFill3) {
		this.byFill3 = byFill3;
	}

	public byte getByFill4() {
		return byFill4;
	}

	public void setByFill4(byte byFill4) {
		this.byFill4 = byFill4;
	}

	public byte getByFill5() {
		return byFill5;
	}

	public void setByFill5(byte byFill5) {
		this.byFill5 = byFill5;
	}

	public byte getByFill6() {
		return byFill6;
	}

	public void setByFill6(byte byFill6) {
		this.byFill6 = byFill6;
	}

	public byte getByFill7() {
		return byFill7;
	}

	public void setByFill7(byte byFill7) {
		this.byFill7 = byFill7;
	}

	public byte getByFill8() {
		return byFill8;
	}

	public void setByFill8(byte byFill8) {
		this.byFill8 = byFill8;
	}

}
