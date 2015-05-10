package com.plat.orcl.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.plat.orcl.web.formbean.AllScoreForm;

/**
 * Title: 公共处理函数 Description:字符串等处理函数 Copyright: Copyright (c) 2015
 */
public class PubUtil {

	// 课程id的长度
	public static final int COURSE_ID_LENGTH = 6;
	public static final String SEPARATOR = "·";
	// 选取人数
	public static final int SELECT_NUM = 5;
	// 服务器邮箱配置
	public static final String TEA_EMAIL = "z1415926535@qq.com";
	public static final String TEA_PASSWORD = "zqw728728";
	public static final String MAIL_HOST = "smtp.qq.com";

	// 导出excel存放位置
	public static final String EXCL_SAVE_PATH = "E:/excel";

	/***
	 * @Description: 常规判断字符串为空
	 * @param str1
	 * @return 
	 * @return boolean 
	 * @date 2015年5月8日
	 */
	public static boolean isEmptyString(Object str1) {
		if (null == str1)
			return true;

		if (str1.equals(""))
			return true;

		return false;
	}
	/***
	 * 
	 * @Description: 判断字符串为空升级版
	 * @param str1
	 * @return 
	 * @return boolean 
	 * @date 2015年5月8日
	 */
	public static boolean isEmptring(Object str1) {
		if (null == str1)
			return true;

		if (str1.equals(""))
			return true;

		if (((String) str1).contains("null"))
			return true;

		return false;
	}

	// 比较字符串
	public static boolean isEqualsString(String str1, String str2) {
		if (null != str1 && null != str2)
			return str1.equals(str2);

		if (null == str1 && null == str2)
			return true;

		return false;
	}

	// 比较字符串
	public static boolean isEqualsStringIgnoreNull(String str1, String str2) {
		if (null != str1 && null != str2)
			return str1.equals(str2);

		if (isEmptyString(str1) && isEmptyString(str2))
			return true;

		return false;
	}

	// 比较字符串
	public static boolean isEqualsStringIgnoreNull(Object str1, Object str2) {
		if (nullToEmptyString(str1).equals(nullToEmptyString(str2)))
			return true;

		return false;
	}

	// 比较字符串,IgnoreCase
	public static boolean isEqualsStringIgnoreCase(String str1, String str2) {
		if (null != str1 && null != str2)
			return str1.equalsIgnoreCase(str2);

		if (null == str1 && null == str2)
			return true;

		return false;
	}

	// 返回填充固定数量连续的字符，如：000000
	public static String pad(String str1, int count) {
		if (count <= 0)
			return "";

		StringBuffer strRet = new StringBuffer();
		for (int i = 0; i < count; i++) {
			strRet.append(str1);
		}

		return strRet.toString();
	}

	// 返回填充固定数量连续的字符，如：000000
	public static String pad(String[] str1) {
		if (null == str1 || str1.length <= 0)
			return "";

		StringBuffer strRet = new StringBuffer();
		for (int i = 0; i < str1.length; i++) {
			strRet.append(str1[i]);
		}

		return strRet.toString();
	}

	public static String[] initArray(String strDef, int count) {
		if (count <= 0)
			return null;

		String[] strRet = new String[count];
		for (int i = 0; i < count; i++) {
			strRet[i] = strDef;
		}

		return strRet;
	}

	public static HashMap cloneHashMap(HashMap inData) {
		if (null == inData) {
			return null;
		}

		HashMap retData = new HashMap();
		for (java.util.Iterator iter = inData.keySet().iterator(); iter
				.hasNext();) {
			String strKey = (String) iter.next();
			Object obj = inData.get(strKey);

			if (obj instanceof String) {
				String strValue = (String) obj;
				retData.put(strKey, strValue);
			} else if (obj instanceof java.math.BigDecimal) {
				java.math.BigDecimal decValue = new java.math.BigDecimal(
						((java.math.BigDecimal) obj).toString());
				retData.put(strKey, decValue);
			} else if (obj instanceof Integer) {
				int iValue = ((Integer) obj).intValue();
				retData.put(strKey, new Integer(iValue));
			} else if (obj instanceof String[]) {
				String[] strValue = PubUtil.cloneString((String[]) obj);
				retData.put(strKey, strValue);
			} else {
				retData.put(strKey, obj);
			}
		}
		return retData;
	}

	public static HashSet cloneHashSet(HashSet inData) {
		if (null == inData) {
			return null;
		}

		HashSet retData = new HashSet();
		for (java.util.Iterator iter = inData.iterator(); iter.hasNext();) {
			Object obj = (String) iter.next();
			if (obj instanceof String[]) {
				String[] strValue = PubUtil.cloneString((String[]) obj);
				retData.add(strValue);
			} else {
				retData.add(obj);
			}
		}

		return retData;
	}

	public static ArrayList cloneArrayList(ArrayList inData) {
		if (null == inData) {
			return null;
		}
		ArrayList retData = new ArrayList();
		for (int i = 0; i < inData.size(); i++) {
			Object obj = inData.get(i);
			if (obj instanceof String) {
				String strValue = new String((String) obj);
				retData.add(strValue);
			} else if (obj instanceof Double) {
				Double decValue = new Double(((Double) obj).doubleValue());
				retData.add(decValue);
			} else if (obj instanceof Integer) {
				int iValue = ((Integer) obj).intValue();
				retData.add(new Integer(iValue));
			} else if (obj instanceof java.math.BigDecimal) {
				java.math.BigDecimal decValue = new java.math.BigDecimal(
						((java.math.BigDecimal) obj).toString());
				retData.add(decValue);
			} else if (obj instanceof String[]) {
				String[] strValue = PubUtil.cloneString((String[]) obj);
				retData.add(strValue);
			} else if (obj instanceof ArrayList) {
				ArrayList strValue = PubUtil.cloneArrayList((ArrayList) obj);
				retData.add(strValue);
			} else {
				retData.add(obj);
			}
		}

		return retData;
	}

	public static String[] cloneString(String[] inData) {
		if (null == inData) {
			return null;
		}

		String[] retData = new String[inData.length];
		for (int i = 0; i < inData.length; i++) {
			String strKey = inData[i];
			retData[i] = strKey;
		}
		return retData;
	}

	public static int[] cloneInt(int[] inData) {
		if (null == inData) {
			return null;
		}
		int[] retData = new int[inData.length];
		for (int i = 0; i < inData.length; i++) {
			int strKey = inData[i];
			retData[i] = strKey;
		}
		return retData;
	}

	// 追加int[]
	public static int[] appendIntArray(int[] inData, int iNewData) {
		if (null == inData) {
			return null;
		}

		int[] retData = new int[inData.length + 1];
		for (int i = 0; i < inData.length; i++) {
			retData[i] = inData[i];
		}

		retData[retData.length - 1] = iNewData;

		return retData;
	}

	// 追加String[]
	public static String[] appendStringArray(String[] inData, String strNewData) {
		if (null == inData) {
			return null;
		}

		String[] retData = new String[inData.length + 1];
		for (int i = 0; i < inData.length; i++) {
			retData[i] = inData[i];
		}

		retData[retData.length - 1] = strNewData;

		return retData;
	}

	public static String nullToEmpty(String inString) {
		return (inString == null) ? "" : inString;
	}

	public static String nullToEmptyString(Object inString) {
		return (inString == null) ? "" : (String) inString;
	}

	public static String nullToZero(String inString) {
		if (isEmptyString(inString)) {
			return "0";
		}
		return inString;
	}

	public static String nullToZero(Object inString) {
		if (null == inString) {
			return "0";
		} else if (PubUtil.isEmptyString((String) inString)) {
			return "0";
		}
		return (String) inString;
	}

	public static String nullToEmptyBracket(Object inString) {
		if (null == inString) {
			return "  ";
		} else if (PubUtil.isEmptyString((String) inString)) {
			return "  ";
		}
		return (String) inString;
	}

	// StringBuffer中的字符串数值加1，如0000002变为0000003
	public static void increteStringBuffer(StringBuffer sTempStart) {
		int iMin = (new Integer(sTempStart.toString())).intValue();
		iMin++;

		int iLength = sTempStart.length();
		sTempStart.delete(0, iLength);
		sTempStart.append("0000000000000000000000000000000000000000").append(
				iMin);
		sTempStart.delete(0, sTempStart.length() - iLength);
	}

	// 替换单引号为两个单引号
	public static String convertSingleQuote(String strValue) {
		if (isEmptyString(strValue)) {
			return "";
		}

		return strValue.replaceAll("'", "''");
	}

	// 去掉单引号
	public static String disSingleQuote(String strValue) {
		if (isEmptyString(strValue)) {
			return "";
		}

		return strValue.replaceAll("'", "");
	}

	// 转换Iterator到ArrayList
	public static ArrayList Iter2List(Iterator iter) {
		ArrayList a = new ArrayList();
		while (iter.hasNext()) {
			a.add(iter.next());
		}
		return a;
	}

	public static ArrayList split(String src, String sep) {
		ArrayList a = new ArrayList();
		if (null == src || null == sep)
			return a;

		int iStart = 0;
		int iEnd = 0;
		while (true) {
			iEnd = src.indexOf(sep, iStart);
			if (iEnd < 0) {
				a.add(src.substring(iStart));
				break;
			}

			a.add(src.substring(iStart, iEnd));
			iStart = iEnd + 1;
		}

		return a;
	}

	public static ArrayList split(String src, String sep, int iStartPoint,
			int iEndPoint) {
		ArrayList a = new ArrayList();
		if (null == src || null == sep)
			return a;

		int iEnd = 0;
		while (true) {
			iEnd = src.indexOf(sep, iStartPoint);
			if (iEnd < 0 || iEnd >= iEndPoint) {
				a.add(src.substring(iStartPoint, iEndPoint));
				break;
			}

			a.add(src.substring(iStartPoint, iEnd));
			iStartPoint = iEnd + 1;
		}

		return a;
	}

	public static String[] splitToArray(String src, String sep) {
		ArrayList a = new ArrayList();
		if (null == src || null == sep)
			return null;

		int iStart = 0;
		int iEnd = 0;
		while (true) {
			iEnd = src.indexOf(sep, iStart);
			if (iEnd < 0) {
				a.add(src.substring(iStart));
				break;
			}

			a.add(src.substring(iStart, iEnd));
			iStart = iEnd + 1;
		}

		String[] sRet = new String[a.size()];
		for (int i = 0; i < a.size(); i++) {
			sRet[i] = (String) a.get(i);
		}

		return sRet;
	}

	public static String StringOfDateTimeMills() {
		long lTime = System.currentTimeMillis();
		java.util.Date d = new java.util.Date(lTime);

		int year = d.getYear() + 1900;
		String month = "00" + (d.getMonth() + 1);
		String day = "00" + d.getDate();
		String hour = "00" + d.getHours();
		String minus = "00" + d.getMinutes();
		String second = "00" + d.getSeconds();

		return year + "-" + month.substring(month.length() - 2) + "-"
				+ day.substring(day.length() - 2) + " "
				+ hour.substring(hour.length() - 2) + ":"
				+ minus.substring(minus.length() - 2) + ":"
				+ second.substring(second.length() - 2) + "." + (lTime % 1000);
	}

	// YYYY-MM-DD
	public static String StringOfDate2() {
		java.util.Date d = new java.util.Date();
		int year = d.getYear() + 1900;
		String month = "00" + (d.getMonth() + 1);
		String day = "00" + d.getDate();

		return year + "-" + month.substring(month.length() - 2) + "-"
				+ day.substring(day.length() - 2);
	}

	// YYYY-MM-DD
	public static String StringOfDate2(java.util.Date d) {
		int year = d.getYear() + 1900;
		String month = "00" + (d.getMonth() + 1);
		String day = "00" + d.getDate();

		return year + "-" + month.substring(month.length() - 2) + "-"
				+ day.substring(day.length() - 2);
	}

	// YYYY-MM-DD
	public static String StringOfDate2(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		String month = "00" + (cal.get(Calendar.MONTH) + 1);
		String day = "00" + cal.get(Calendar.DATE);

		return year + "-" + month.substring(month.length() - 2) + "-"
				+ day.substring(day.length() - 2);
	}

	// YYYY-MM-DD HH:MI
	public static String StringOfDateMinus2() {
		java.util.Date d = new java.util.Date();
		int year = d.getYear() + 1900;
		String month = "00" + (d.getMonth() + 1);
		String day = "00" + d.getDate();
		String hour = "00" + d.getHours();
		String minus = "00" + d.getMinutes();

		return year + "-" + month.substring(month.length() - 2) + "-"
				+ day.substring(day.length() - 2) + " "
				+ hour.substring(hour.length() - 2) + ":"
				+ minus.substring(minus.length() - 2);
	}

	// YYYY-MM-DD HH:MI:SS
	public static String StringOfDateTime2() {
		java.util.Date d = new java.util.Date();
		int year = d.getYear() + 1900;
		String month = "00" + (d.getMonth() + 1);
		String day = "00" + d.getDate();
		String hour = "00" + d.getHours();
		String minus = "00" + d.getMinutes();
		String second = "00" + d.getSeconds();

		return year + "-" + month.substring(month.length() - 2) + "-"
				+ day.substring(day.length() - 2) + " "
				+ hour.substring(hour.length() - 2) + ":"
				+ minus.substring(minus.length() - 2) + ":"
				+ second.substring(second.length() - 2);
	}

	// YYYY-MM-DD HH:MI:SS
	public static String StringOfDateTime2(java.util.Date d) {
		int year = d.getYear() + 1900;
		String month = "00" + (d.getMonth() + 1);
		String day = "00" + d.getDate();
		String hour = "00" + d.getHours();
		String minus = "00" + d.getMinutes();
		String second = "00" + d.getSeconds();

		return year + "-" + month.substring(month.length() - 2) + "-"
				+ day.substring(day.length() - 2) + " "
				+ hour.substring(hour.length() - 2) + ":"
				+ minus.substring(minus.length() - 2) + ":"
				+ second.substring(second.length() - 2);
	}

	// YYYYMMDDHHMISS
	public static String StringOfShortDateTime() {
		java.util.Date d = new java.util.Date();
		int year = d.getYear() + 1900;
		String month = "00" + (d.getMonth() + 1);
		String day = "00" + d.getDate();
		String hour = "00" + d.getHours();
		String minus = "00" + d.getMinutes();
		String second = "00" + d.getSeconds();

		return year + month.substring(month.length() - 2)
				+ day.substring(day.length() - 2)
				+ hour.substring(hour.length() - 2)
				+ minus.substring(minus.length() - 2)
				+ second.substring(second.length() - 2);
	}

	// 系统字符集
	private static String CHARSET = null;

	public static String getCharSet() {
		if (null == CHARSET) {
			java.util.Properties p = System.getProperties();
			if (p.containsKey("file.encoding")) {
				String sCharSet = p.getProperty("file.encoding");
				System.out.println("system file.encoding=" + sCharSet);
				if (PubUtil.isEqualsStringIgnoreCase(sCharSet, "GBK")
						|| PubUtil.isEqualsStringIgnoreCase(sCharSet, "GB2312")) {
					CHARSET = "GBK";
					System.out.println("set file.encoding=GBK");
				} else {
					CHARSET = sCharSet;
				}
			}

			if (null == CHARSET) {
				CHARSET = "GBK";
				System.out.println("set default file.encoding=GBK");
			}
		}

		return CHARSET;
	}

	public static HashMap _hmGBKChar = new HashMap();

	/**
	 * 拼写SQL语句中的in条件
	 * 
	 * @param arr
	 * @return
	 */
	public static StringBuffer getInCondition(String strFldCode, ArrayList arr,
			boolean bAddQuota) {
		String strQuota = "";
		if (bAddQuota) {
			strQuota = "'";
		}
		StringBuffer sbCon = new StringBuffer();
		int count = 0;
		for (int i = 0; i < arr.size(); i++) {
			String strDWID = arr.get(i).toString();
			if (PubUtil.isEmptyString(strDWID)
					|| PubUtil.isEqualsString(strDWID, "''")) {
				continue;
			}
			if (count < 1000) {
				if (count > 0)
					sbCon.append(",");
				count++;
				sbCon.append(strQuota + strDWID + strQuota);
			} else {
				sbCon.append(") or " + strFldCode + " in (");
				sbCon.append(strQuota + strDWID + strQuota);
				count = 1;
			}
		}

		if (sbCon.length() > 0) {
			sbCon.insert(0, " (" + strFldCode + " in  (");
			sbCon.append(")) ");
		} else {
			sbCon.append(" 1=0 ");
		}

		return sbCon;
	}

	/**
	 * 拼写SQL语句中的in条件
	 * 
	 * @param arr
	 * @return
	 */
	public static StringBuffer getInCondition(String strFldCode, ArrayList arr) {
		return getInCondition(strFldCode, arr, true);
	}

	public static StringBuffer getInCondition(String strFieldName, Iterator iter) {
		ArrayList aValue = new ArrayList();
		while (iter.hasNext()) {
			aValue.add(iter.next());
		}
		return getInCondition(strFieldName, aValue);
	}

	public static StringBuffer getNotInCondition(String strFldCode,
			Iterator iter) {
		ArrayList aValue = new ArrayList();
		while (iter.hasNext()) {
			aValue.add(iter.next());
		}
		return getNotInCondition(strFldCode, aValue);
	}

	public static StringBuffer getNotInCondition(String strFldCode,
			ArrayList arr) {
		return getNotInCondition(strFldCode, arr, true);
	}

	/**
	 * 拼写SQL语句中的in条件
	 * 
	 * @param arr
	 * @return
	 */
	public static StringBuffer getNotInCondition(String strFldCode,
			ArrayList arr, boolean bAddQuota) {
		String strQuota = "";
		if (bAddQuota) {
			strQuota = "'";
		}
		StringBuffer sbCon = new StringBuffer();
		int count = 0;
		for (int i = 0; i < arr.size(); i++) {
			String strDWID = (String) arr.get(i);
			if (PubUtil.isEmptyString(strDWID)
					|| PubUtil.isEqualsString(strDWID, "''")) {
				continue;
			}

			if (count < 1000) {
				if (count > 0)
					sbCon.append(",");
				count++;
				sbCon.append(strQuota + strDWID + strQuota);
			} else {
				sbCon.append(") and " + strFldCode + " not in (");
				sbCon.append(strQuota + strDWID + strQuota);
				count = 1;
			}
		}

		if (sbCon.length() > 0) {
			sbCon.insert(0, " (" + strFldCode + " not in  (");
			sbCon.append(")) ");
		} else {
			sbCon.append(" 1=0 ");
		}

		return sbCon;
	}

	public static String disQuota(String strOnevalue) {
		if (PubUtil.isEmptyString(strOnevalue) || strOnevalue.length() <= 1) {
			return strOnevalue;
		} else if (strOnevalue.startsWith("'") && strOnevalue.endsWith("'")) {
			return strOnevalue.substring(1, strOnevalue.length() - 1);
		} else {
			return strOnevalue;
		}
	}

	public static String filterErrMsg(String strErr) {
		if (PubUtil.isEmptyString(strErr)) {
			return "";
		}

		int iPoint = strErr.indexOf("Exception:");
		if (iPoint >= 0) {
			return strErr.substring(iPoint + 10);
		}

		return strErr;
	}

	// 获得一个字符串的1000以内的码，用于缓存
	public static Integer getCode1000(String strValue) {
		if (PubUtil.isEmptyString(strValue)) {
			return new Integer(0);
		}

		int c = 0;
		for (int i = 0; (i < strValue.length() && i < 20); i++) {
			c = c + strValue.charAt(i);
		}

		return new Integer(c % 999);
	}

	// 插入值
	public static void put1000(HashMap hm1000, String ID, Object obj) {
		Integer iID1000 = PubUtil.getCode1000(ID);
		HashMap hm = null;
		if (hm1000.containsKey(iID1000)) {
			hm = (HashMap) hm1000.get(iID1000);
		} else {
			hm = new HashMap();
			hm1000.put(iID1000, hm);
		}

		hm.put(ID, obj);
	}

	// 是否存在
	public static boolean exist1000(HashMap hm1000, String ID) {
		Integer iID1000 = PubUtil.getCode1000(ID);
		HashMap hm = null;
		if (hm1000.containsKey(iID1000)) {
			hm = (HashMap) hm1000.get(iID1000);
			if (hm.containsKey(ID)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// 获得对象
	public static Object get1000(HashMap hm1000, String ID) {
		if (false == exist1000(hm1000, ID)) {
			return null;
		}
		Integer iID1000 = PubUtil.getCode1000(ID);
		HashMap hm = (HashMap) hm1000.get(iID1000);
		;
		return hm.get(ID);
	}

	// 默认年度
	public static String getDefaultNd() {
		java.util.Date d = new java.util.Date();
		return "" + (d.getYear() + 1900);
	}

	// 将LIST转换成String 输出
	public static String arrayListToString(AbstractCollection list,
			String splitChar) {
		StringBuffer buffer = new StringBuffer();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			buffer.append(it.next()).append(splitChar);
		}
		/*
		 * for (int i=0; i<list.size(); i++) {
		 * buffer.append(list.get(i)).append(splitChar); }
		 */
		String tmpStr = buffer.toString();
		if (tmpStr.equals("")) {
			return "";
		} else {
			return tmpStr.substring(0, tmpStr.length() - 1);
		}
	}

	// 将LIST转换成String 输出
	public static String[] CollectionToArray(AbstractCollection collection) {
		String[] buffer = new String[collection.size()];
		Iterator it = collection.iterator();
		int i = 0;
		while (it.hasNext()) {
			buffer[i] = (String) it.next();
			i++;
		}
		return buffer;
	}
	// sql数据库的字段类型java.sql.types转换为java实际类型
	public static int sql2JavaType(int intJavaSQLType) {
		switch (intJavaSQLType) {
		case java.sql.Types.BIGINT: // 整形数据
		case java.sql.Types.INTEGER:
		case java.sql.Types.SMALLINT:
		case java.sql.Types.TINYINT:
			return PubConst.DATATYPE_DECIMAL;

		case java.sql.Types.DATE: // 日期数据
		case java.sql.Types.TIME:
		case java.sql.Types.TIMESTAMP:
			return PubConst.DATATYPE_CHAR;

		case java.sql.Types.DECIMAL: // 小数数据
		case java.sql.Types.DOUBLE:
		case java.sql.Types.FLOAT:
		case java.sql.Types.NUMERIC:
		case java.sql.Types.REAL:
			return PubConst.DATATYPE_DECIMAL;

		default: // 字符数据
			return PubConst.DATATYPE_CHAR;
		}
	}

	// UQ的字段类型转换为java实际类型
	public static int uq2JavaType(String strUQType) {
		int intUQType = (new Integer(strUQType)).intValue();
		return uq2JavaType(intUQType);
	}

	// UQ的字段类型转换为java实际类型
	public static int uq2JavaType(int intUQType) {
		switch (intUQType) {
		case PubConst.DATATYPE_INTEGER: // 整形
			return PubConst.DATATYPE_DECIMAL;

		case PubConst.DATATYPE_DECIMAL: // 小数、金额、比率
		case PubConst.DATATYPE_MONEY:
		case PubConst.DATATYPE_RATE:
			return PubConst.DATATYPE_DECIMAL;

		default: // 字符数据
			return PubConst.DATATYPE_CHAR;
		}
	}

	// 取得唯一码
	public static String createGUID() {
		UUID id = UUID.randomUUID();
		String tempID = id.toString().replaceAll("-", "");

		return tempID;
	}

	/**
	 * Java文件操作 获取文件扩展名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * Java文件操作 获取不带扩展名的文件名
	 */
	public static String getFileNameNoEx(String filename) {
		if(filename.length()>21){
			filename=filename.substring(0, 20);
			if ((filename != null) && (filename.length() > 0)) {
				int dot = filename.lastIndexOf('.');
				if ((dot > -1) && (dot < (filename.length()))) {
					return filename.substring(0, dot);
				}
			}
			
		}
		return filename;
	}

	/***
	 * 
	 * @Description:在数字的前面补零
	 * @param num
	 * @param length
	 * @return
	 * @return String
	 * @date 2015年3月16日
	 */
	public static String appendZero(int num, int length) {
		// String.valueOf()是用来将其他类型的数据转换为string型数据的
		String tmpString = String.valueOf(num);
		for (int i = tmpString.length(); i < length; i++) {
			tmpString = "0" + tmpString;
		}
		return tmpString;
	}
	
	/**
	 * 生成随机文件名：当前年月日时分秒+五位随机数
	 * 
	 * @return
	 */
	public static String randomFileName() {

		SimpleDateFormat simpleDateFormat;

		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

		return rannum + str;// 当前时间
	}

	public static File exportExcel(ArrayList<AllScoreForm> sl) {
		String path = PubUtil.EXCL_SAVE_PATH + java.io.File.separator;
		File excel = new File(path);
		if (!excel.exists()) {
			excel.mkdirs();
		}
		String excelID = "成绩表："+PubUtil.randomFileName()+".xls";
		//将sl中数据放入二维数组中
		Object [] data = sl.toArray();
		path = path+excelID;
		String[] title = {"学号","姓名","课程号","课程名","学生评分","教师评分","测试评分","期末考试得分","总分"};
		PubUtil.array2Excel(path,"分数表",title,data);
		return new File(path);
	}
	
	//将两个数组中的东西写到Excel中
	public static void array2Excel(String path,String name , String[] title, Object[] data) {
		try {

			// 1、创建WritableWorkbook对象
	        File file = new File(path);
	        WritableWorkbook WritableBK = Workbook.createWorkbook(file);
	
	        // 2、创建WritableSheet对象
	        WritableSheet WritableSheet = WritableBK.createSheet(name, 0);
	        /**
	         * 定义单元格样式
	         */
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
			WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
			jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
	        //wcf.setBackground(jxl.format.Colour.BLACK); // 设置单元格的背景颜色
	        wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
	        
	        //4、 合并单元格创建标题
	        int cNum = title.length;
	        //记录最大列宽的数组
	        int[] max= new int[cNum];
	        //设置标题相关样式
	        WritableSheet.mergeCells(0,0, cNum-1, 0);// 参数说明，前两个参数为待合并的起始单元格位置，后两个参数用来指定结束单元格位置（列和行）
	        WritableFont wft = new WritableFont(WritableFont.ARIAL, 25,
			WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcft = new WritableCellFormat(wft); // 单元格定义
	        wcft.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
	        Label labeltitle = new Label(0, 0, name,wcft);
        	WritableSheet.addCell(labeltitle);
	        // 3、添加(标题)单元格
	        for(int i=0;i<title.length;i++){
	        	if(title[i].length()>max[i]) max[i]=title[i].length();
	        	Label label1 = new Label(i, 1, title[i],wcf);
	        	WritableSheet.addCell(label1);
	        }
	        // 3、添加(数据)单元格
	        for(int i=0;i<data.length;i++){
	        	String[] datas = (data[i].toString()).split(",");
	        	for(int j=0;j<datas.length;j++){
	        		if(datas[j].length()>max[j]) max[j]=datas[j].length();
	        		Label label1 = new Label(j, 2+i,datas[j],wcf);
	        		WritableSheet.addCell(label1);
	        	}
	        }
	        PubUtil.setAutoSize(WritableSheet,data,title);
			//设置自动调整列宽
			for(int i =0;i<cNum;i++){
				WritableSheet.setColumnView(i, max[i]*3);//根据内容自动设置列宽
			}

	
	        WritableBK.write();
	        WritableBK.close();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void setAutoSize(WritableSheet writableSheet, Object[] row, String[] col) {
		for(int i=0;i<col.length;i++){
			for(int j=0;j<row.length;j++){
				row[j].toString().split(",");
			}
		}
	}
	

	/*
	 * public static int[] RandomDiffInt(int amount){ int[] a = new return ; }
	 */
}
