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
 * 说明：
 * 
 */
public final class ViewUtil {
	/**
	 * 获得经过格式化后的页面标题
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
	 * 获得必输项提示符	
	 * @return 必输项提示符
	 */
	public static String must() {
		return "<font color=\"#0000FF\">(*)</font>";

	}

	/**
	* 根据单级字典的规类码输出下拉框<select name = 'selname'>......</select>标签
	* @param selName - 下拉框的名字
	* @param typeID - 字典规类码
	* @param tagval - 需要定位的选项值	
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
	* 根据单级字典的规类码输出下拉框<select name = 'selname'>......</select>标签
	* @param selName - 下拉框的名字
	* @param typeID - 字典规类码 	
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
	* 根据单级字典的规类码输出下拉框的选项部分<option value= 'val'>......</option>
	* @param typeID - 字典规类码
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
	* 根据单级字典的规类码输出下拉框的选项部分<option value= 'val'>......</option>,
	* 并自动定位到相应的选项
	* @param typeID - 字典规类码
	* @param tagval - 需要定位的选项值	
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
	* 输出下拉框<select name = 'selname'>......</select>标签
	* @param selName - 下拉框的名字
	* @param tagval - 需要定位的选项值
	* @param optval - 选项值数组
	* @param optname - 选项显示名称数组	
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
	* 输出下拉框的选项部分<option value= 'val'>......</option>,并自动定位相应的选项
	* @param tagval - 需要定位的选项值
	* @param optval - 选项值数组
	* @param optname - 选项显示名称数组	
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
	* 输出下拉框的选项部分<option value= 'val'>......</option>
	* 
	* @param optval - 选项值数组
	* @param optname - 选项显示名称数组	
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
