/*********************************************************
 * File: ReDefSDicMap.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.cache;

import java.util.*;
import java.sql.*;

import org.apache.struts.util.LabelValueBean;

import com.eis.db.*;
import com.eis.base.*;
import com.eis.util.*;

/**
 * 说明：自定义单级字典缓存类
 * 
 */

public class ReDefSDicMap {

	static {
		loadDicMap();
	}

	private static HashMap map = null;

	private static HashMap optionMap = null;

	private static boolean status = false;

	/**
	 * 构造函数
	 */
	public ReDefSDicMap() {
		super();

	}

	/**
	 * 装载字典数据
	 *
	 */
	public static synchronized void loadDicMap() {

		map = new HashMap(20);
		optionMap = new HashMap(20);

		DBQueryUtil db = new DBQueryUtil();
		String typeID = null;
		status = true;

		try {
			ResultSet rs = null;
			ResultSet rs1 =
				db.sqlQuery(
					"select TYPE_ID,STMT FROM  ep_redef_sdic order by TYPE_ID ");

			while (rs1.next()) {
				typeID = rs1.getString("TYPE_ID").trim();
				rs = db.sqlQueryR(rs1.getString("STMT"));

				String itemID = null;
				ArrayList options = new ArrayList();

				options.add(new LabelValueBean("--", ""));

				HashMap typeMap = new HashMap();
				while (rs.next()) {
					HashMap tmp = new HashMap();
					itemID = rs.getString("ITEM_CODE").trim();
					tmp.put("ITEM_CODE", itemID);
					tmp.put("ITEM_VAL", rs.getString("ITEM_VAL").trim());
					tmp.put("LOGIC_ID", rs.getString("LOGIC_ID"));

					typeMap.put(itemID, tmp);

					options.add(
						new LabelValueBean(
							rs.getString("ITEM_VAL").trim(),
							itemID));
				}

				map.put(typeID, typeMap);

				optionMap.put(typeID, options);

			}

			if (rs != null)
				rs.close();

			if (rs1 != null)
				rs1.close();
		} catch (Exception ex) {
			SysLog.error("装载自定义单级数据字典失败！");
			SysLog.error(ex.getMessage());
			status = false;
		} finally {
			db.close();
		}

	}

	/**
	 * 重新装载字典 
	 */
	public static void reloadDicMap() {
		status = false;
		loadDicMap();
	}

	public static java.util.HashMap getDicMap(String typeID) {

		if (!status) {
			loadDicMap();
		}
		java.util.HashMap tmp = (java.util.HashMap) map.get(typeID);

		return tmp;

	}

	public static java.util.HashMap getMap() {

		if (!status) {
			loadDicMap();
		}

		return map;
	}

	/**
	 * 将指定规类码的字典数据装载到缓存中
	 * @param typeID - 规类码
	 */
	public static synchronized void loadDicMap(String typeID) {

		DBQueryUtil db = new DBQueryUtil();

		try {

			String stmt =
				db.sqlQuerySingle(
					"select STMT FROM  ep_redef_sdic where TYPE_ID'"
						+ typeID
						+ "'");

			ResultSet rs = db.sqlQuery(stmt);
			String itemID = null;

			HashMap typeMap = new HashMap();
			ArrayList options = new ArrayList();

			options.add(new LabelValueBean("--", ""));

			while (rs.next()) {
				HashMap tmp = new HashMap();
				itemID = rs.getString("ITEM_CODE").trim();
				tmp.put("ITEM_CODE", itemID);
				tmp.put("ITEM_VAL", rs.getString("ITEM_VAL").trim());
				tmp.put("LOGIC_ID", rs.getString("LOGIC_ID"));

				typeMap.put(itemID, tmp);

				options.add(
					new LabelValueBean(
						rs.getString("ITEM_VAL").trim(),
						itemID));

			}

			map.put(typeID, typeMap);

			optionMap.put(typeID, options);

			if (rs != null)
				rs.close();

		} catch (Exception ex) {
			SysLog.error("装载自定义单级数据字典失败！规类码＝" + typeID);
			SysLog.error(ex.getMessage());
			status = false;
		} finally {
			db.close();
		}
	}

	/**
	* 查询数据字典项名称
	* @param typeID - 规类码
	* @param itemID - 选项值
	*/
	public static String getDicItemVal(String typeID, String itemID) {
		if (!status) {
			loadDicMap();
		}

		HashMap typeMap = (HashMap) map.get(typeID);

		Object val = typeMap.get(itemID);
		if (val == null)
			return "";
		else {
			Object itemVal = ((HashMap) val).get("ITEM_VAL");
			if (itemVal == null)
				return "";
			else
				return (String)itemVal;
		}

	}

	/**
	* 获得数据字典项的逻辑控制代码
	* @param typeID - 规类码
	* @param itemID - 选项值
	*/
	public static String getDicItemLogicID(String typeID, String itemID) {
		if (!status) {
			loadDicMap();
		}

		HashMap typeMap = (HashMap) map.get(typeID);
		return (String) ((HashMap) typeMap.get(itemID)).get("LOGIC_ID");
	}

	/**
	 * 根据规类码返回选项对象集合
	 * @param typeID - 归类码
	 * @return 选项对象集合 
	 */
	public static java.util.Collection getOptionCollection(String typeID) {

		if (!status) {
			loadDicMap();
		}
		return (Collection) optionMap.get(typeID);
	}

	/**
	* 根据字典的规类码输出多个单选钮
	* @param radioName - 单选钮的名字
	* @param typeID - 字典规类码 	
	*/
	public static String getRadio(String radioName, String typeID) {

		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			radio.append(
				"<input name=\""
					+ radioName
					+ "\" type=\"radio\" class=\"Radiobutton\" value=\""
					+ bean.getValue()
					+ "\">"
					+ bean.getLabel()
					+ "&nbsp;&nbsp; ");

		}

		return radio.toString();

	}

	/**
	* 根据字典的规类码输出多个单选钮
	* @param radioName - 单选钮的名字
	* @param typeID - 字典规类码
	* @param tagval - 需要定位的选项值	
	*/
	public static String getRadio(
		String radioName,
		String typeID,
		String tagVal) {

		if (tagVal == null || tagVal.trim().length() <= 0)
			return getRadio(radioName, typeID);

		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			if (tagVal.equals(bean.getValue()))
				radio.append(
					"<input name=\""
						+ radioName
						+ "\" type=\"radio\" class=\"Radiobutton\" value=\""
						+ bean.getValue()
						+ "\" checked>"
						+ bean.getLabel()
						+ "&nbsp;&nbsp; ");

			else
				radio.append(
					"<input name=\""
						+ radioName
						+ "\" type=\"radio\" class=\"Radiobutton\" value=\""
						+ bean.getValue()
						+ "\">"
						+ bean.getLabel()
						+ "&nbsp;&nbsp; ");

		}

		return radio.toString();

	}

	/**
	* 根据字典的规类码输出多个复选框
	* @param fieldName - 复选框的名字
	* @param typeID - 字典规类码		
	*/
	public static String getCheckBox(String fieldName, String typeID) {

		StringBuffer checkbox = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			checkbox.append(
				"<input name=\""
					+ fieldName
					+ "\" type=\"checkbox\" class=\"Checkbox\" value=\""
					+ bean.getValue()
					+ "\">"
					+ bean.getLabel()
					+ "&nbsp;&nbsp; ");

		}

		return checkbox.toString();

	}

	/**
	* 根据字典的规类码输出多个复选框
	* @param fieldName - 复选框的名字
	* @param typeID - 字典规类码
	* @param tagvals - 需要定位的选项值(多个)	
	*/
	public static String getCheckBox(
		String fieldName,
		String typeID,
		List tagVals) {

		if (tagVals == null || tagVals.size() < 1)
			return getCheckBox(fieldName, typeID);

		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			if (tagVals.contains(bean.getValue()))
				radio.append(
					"<input name=\""
						+ fieldName
						+ "\" type=\"checkbox\" class=\"Checkbox\" value=\""
						+ bean.getValue()
						+ "\" checked>"
						+ bean.getLabel()
						+ "&nbsp;&nbsp; ");

			else
				radio.append(
					"<input name=\""
						+ fieldName
						+ "\" type=\"checkbox\" class=\"Checkbox\" value=\""
						+ bean.getValue()
						+ "\">"
						+ bean.getLabel()
						+ "&nbsp;&nbsp; ");

		}

		return radio.toString();

	}

}
