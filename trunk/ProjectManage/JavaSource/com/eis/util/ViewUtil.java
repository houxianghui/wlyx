/*********************************************************
 * File: ViewUtil.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-9
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.util;

import java.sql.*;
import java.util.*;

import org.apache.struts.util.*;
import com.eis.db.*;
import com.eis.cache.*;
import com.eis.util.*;

/**
 * ˵����
 * 
 */
public final class ViewUtil {
	/**
	 * ��þ�����ʽ�����ҳ�����
	 * @param title
	 * @return
	 */
	public static String getTitle(String title) {
		StringBuf sb = new StringBuf();
		sb.appendln(
			"    <table align=\"center\" width=\"98%\"   class=\"dtPanel_Line3\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"> ");
		sb.appendln("     <tr align=\"center\" class=\"dtPanel_Top01\"  >");
		sb.appendln(
			"        <td   class=\"dtPanel_Top01\">"
				+ title
				+ "</td>");
		sb.appendln("      </tr>");
		sb.appendln("     </table>");

		return sb.toString();		

	}

	/**
	 * ��ñ�������ʾ��	
	 * @return ��������ʾ��
	 */
	public static String must() {
		return "<font color=\"#0000FF\">(*)</font>";

	}

	/**
	* ���ݵ����ֵ�Ĺ��������������<select name = 'selname'>......</select>��ǩ
	* @param selName - �����������
	* @param typeID - �ֵ������
	* @param tagval - ��Ҫ��λ��ѡ��ֵ	
	*/
	public static String getSDSelect(
		String selName,
		String typeID,
		String tagval) {
		StringBuffer select =
			new StringBuffer(
				" <SELECT class='Select' name='" + selName + "'> ");

		HashMap map = SingleDicMap.getDicMap(typeID);

		String[] itemID = (String[]) map.keySet().toArray();
		String[] itemVal = new String[itemID.length];
		for (int i = 0; i < itemID.length; i++) {
			itemVal[i] = (String) (((HashMap) map.get(itemID)).get("ITEM_VAL"));
		}

		select.append(getOption(tagval, itemID, itemVal));

		select.append("</SELECT>");
		return select.toString();

	}

	/**
	* ���ݵ����ֵ�Ĺ��������������<select name = 'selname'>......</select>��ǩ
	* @param selName - �����������
	* @param typeID - �ֵ������ 	
	*/
	public static String getSDSelect(String selName, String typeID) {
		StringBuffer select =
			new StringBuffer(
				" <SELECT class='Select' name='" + selName + "'> ");

		HashMap map = SingleDicMap.getDicMap(typeID);

		String[] itemID = (String[]) map.keySet().toArray();
		String[] itemVal = new String[itemID.length];
		for (int i = 0; i < itemID.length; i++) {
			itemVal[i] = (String) (((HashMap) map.get(itemID)).get("ITEM_VAL"));
		}

		select.append(getOption(itemID, itemVal));

		select.append("</SELECT>");
		return select.toString();

	}

	/**
	* ���ݵ����ֵ�Ĺ���������������ѡ���<option value= 'val'>......</option>
	* @param typeID - �ֵ������
	*	
	*/
	public static String getSDOption(String typeID) {

		HashMap map = SingleDicMap.getDicMap(typeID);

		String[] itemID = (String[]) map.keySet().toArray();
		String[] itemVal = new String[itemID.length];
		for (int i = 0; i < itemID.length; i++) {
			itemVal[i] = (String) (((HashMap) map.get(itemID)).get("ITEM_VAL"));
		}
		return getOption(itemID, itemVal);

	}

	/**
	* ���ݵ����ֵ�Ĺ���������������ѡ���<option value= 'val'>......</option>,
	* ���Զ���λ����Ӧ��ѡ��
	* @param typeID - �ֵ������
	* @param tagval - ��Ҫ��λ��ѡ��ֵ	
	*/
	public static String getSDOption(String typeID, String tagval) {

		HashMap map = SingleDicMap.getDicMap(typeID);

		String[] itemID = (String[]) map.keySet().toArray();
		String[] itemVal = new String[itemID.length];
		for (int i = 0; i < itemID.length; i++) {
			itemVal[i] = (String) (((HashMap) map.get(itemID)).get("ITEM_VAL"));
		}
		return getOption(tagval, itemID, itemVal);

	}

	/**
	* ���������<select name = 'selname'>......</select>��ǩ
	* @param selName - �����������
	* @param tagval - ��Ҫ��λ��ѡ��ֵ
	* @param optval - ѡ��ֵ����
	* @param optname - ѡ����ʾ��������	
	*/
	public static String getSelect(
		String selName,
		String tagval,
		String[] optval,
		String[] optname) {
		StringBuffer sel =
			new StringBuffer("<Select class='Select' name='" + selName + "'>");
		sel.append("option value=''>--</option>");
		for (int i = 0; i < optval.length; i++) {
			if (tagval.equals(optval[i]))
				sel.append(
					"<option value='"
						+ optval[i]
						+ "' selected>"
						+ optname[i]
						+ "</option>");
			else
				sel.append(
					"<option value='"
						+ optval[i]
						+ "'>"
						+ optname[i]
						+ "</option>");
		}
		sel.append("</Select>");
		return sel.toString();
	}

	/**
	* ����������ѡ���<option value= 'val'>......</option>,���Զ���λ��Ӧ��ѡ��
	* @param tagval - ��Ҫ��λ��ѡ��ֵ
	* @param optval - ѡ��ֵ����
	* @param optname - ѡ����ʾ��������	
	*/
	public static String getOption(
		String tagval,
		String[] optval,
		String[] optname) {
		StringBuffer sel = new StringBuffer("option value=''>--</option>");
		for (int i = 0; i < optval.length; i++) {
			if (tagval.equals(optval[i]))
				sel.append(
					"<option value='"
						+ optval[i]
						+ "' selected>"
						+ optname[i]
						+ "</option>");
			else
				sel.append(
					"<option value='"
						+ optval[i]
						+ "'>"
						+ optname[i]
						+ "</option>");
		}
		sel.append("</Select>");
		return sel.toString();
	}

	/**
	* ����������ѡ���<option value= 'val'>......</option>
	* 
	* @param optval - ѡ��ֵ����
	* @param optname - ѡ����ʾ��������	
	*/
	public static String getOption(String[] optval, String[] optname) {
		StringBuffer sel = new StringBuffer("option value=''>--</option>");
		for (int i = 0; i < optval.length; i++) {

			sel.append(
				"<option value='"
					+ optval[i]
					+ "'>"
					+ optname[i]
					+ "</option>");
		}
		sel.append("</Select>");
		return sel.toString();
	}

}
