/*********************************************************
 * File: SingleDicCache.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-9
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.cache;

import java.util.*;
import java.sql.*;

import org.apache.struts.util.LabelValueBean;

import com.eis.connectionPool.*;
import com.eis.db.*;
import com.eis.base.*;
import com.eis.util.*;

/**
 * 说明：单级字典缓存类
 * 
 */
public final class SingleDicMap {

	static {
		loadDicMap();
	}

	private static HashMap map = null;

	private static HashMap optionMap = null;

	private static boolean status = false;

	/**
	 * 构造函数
	 */
	public SingleDicMap() {
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
			ResultSet rs1 = db.sqlQuery("select * from  ep_dic_type where DIC_LEVEL='1'");

			while (rs1.next()) {

				typeID = rs1.getString("TYPE_ID").trim();
				rs = db.sqlQueryR("select * from ep_sdic where STAT='1' and TYPE_ID='" + typeID
						+ "' order by LIST_ORDER");

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

					options.add(new LabelValueBean(rs.getString("ITEM_VAL").trim(), itemID));
				}

				map.put(typeID, typeMap);

				optionMap.put(typeID, options);

			}

			if (rs != null)
				rs.close();

			if (rs1 != null)
				rs1.close();

		} catch (Exception ex) {
			SysLog.error("装载单级数据字典失败！");
			SysLog.error(ex.getMessage());
			status = false;
		} finally {
			db.close();
		}

	}

	public static java.util.HashMap getDicMap(String typeID) {

		if (!status) {
			loadDicMap();
		}
		java.util.HashMap tmp = (java.util.HashMap) map.get(typeID);

		return tmp;

	}

	/**
	 * 根据规类码返回选项对象集合
	 * 
	 * @param typeID
	 *            - 归类码
	 * @return 选项对象集合
	 */
	public static java.util.Collection getOptionCollection(String typeID) {

		if (!status) {
			loadDicMap();
		}

		return (Collection) optionMap.get(typeID);

	}

	/**
	 * 获得所有字典数据的map
	 * 
	 * @return 所有字典数据的map
	 */
	public static java.util.HashMap getMap() {

		if (!status) {
			loadDicMap();
		}

		return map;
	}

	/**
	 * 重新装载字典
	 */
	public static void reloadDicMap() {
		status = false;
		loadDicMap();
	}

	/**
	 * 将指定规类码的字典数据装载到缓存中
	 * 
	 * @param typeID
	 *            - 规类码
	 */
	public static synchronized void loadDicMap(String typeID) {

		DBQueryUtil db = new DBQueryUtil();

		try {

			ResultSet rs = db.sqlQuery("select * from ep_sdic where  TYPE_ID='" + typeID
					+ "' and STAT='1' order by LIST_ORDER");
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

				options.add(new LabelValueBean(rs.getString("ITEM_VAL").trim(), itemID));
			}

			map.put(typeID, typeMap);
			optionMap.put(typeID, options);

			if (rs != null)
				rs.close();

		} catch (Exception ex) {
			SysLog.error("装载单级数据字典失败！规类码＝" + typeID);
			SysLog.error(ex.getMessage());
			status = false;
		} finally {
			db.close();
		}
	}

	/**
	 * 查询数据字典项名称
	 * 
	 * @param typeID
	 *            - 规类码
	 * @param itemID
	 *            - 选项值
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
				return (String) itemVal;
		}

	}

	/**
	 * 获得数据字典项的逻辑控制代码
	 * 
	 * @param typeID
	 *            - 规类码
	 * @param itemID
	 *            - 选项值
	 */
	public static String getDicItemLogicID(String typeID, String itemID) {
		if (!status) {
			loadDicMap();
		}
		HashMap typeMap = (HashMap) map.get(typeID);
		return (String) ((HashMap) typeMap.get(itemID)).get("LOGIC_ID");
	}

	/**
	 * 根据单级字典的规类码输出多个单选钮
	 * 
	 * @param radioName
	 *            - 单选钮的名字
	 * @param typeID
	 *            - 字典规类码
	 */
	public static String getRadio(String radioName, String typeID) {

		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
					+ bean.getValue() + "\">" + bean.getLabel() + "&nbsp;&nbsp; ");

		}

		return radio.toString();

	}

	/**
	 * 
	 *add by li xue meng 中央预算单位公务卡 申请资料录入页面中修改“职务”一栏的显示内容 *
	 */
	public static String getRadio(String radioName, String typeID, String tagVal, boolean flag) {
		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			String value = bean.getValue();

			if (!(value.equals("A") || value.equals("B") || value.equals("C"))) {

				if (flag && tagVal.equals(value)) {

					radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"RadioButton\" value=\""
							+ bean.getValue() + "\" checked>" + bean.getLabel() + "&nbsp;&nbsp;");
				} else {
					radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"RadioButton\" value=\""
							+ bean.getValue() + "\">" + bean.getLabel() + "&nbsp;&nbsp;");
				}

			}

		}
		return radio.toString();
	}

	/**
	 * 根据单级字典的规类码输出多个单选钮
	 * 
	 * @param radioName
	 *            - 单选钮的名字
	 * @param typeID
	 *            - 字典规类码
	 * @param tagval
	 *            - 需要定位的选项值
	 */
	public static String getRadio(String radioName, String typeID, String tagVal) {

		if (tagVal == null || tagVal.trim().length() <= 0)
			return getRadio(radioName, typeID);

		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			if (tagVal.equals(bean.getValue()))
				radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
						+ bean.getValue() + "\" checked>" + bean.getLabel() + "&nbsp;&nbsp; ");

			else
				radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
						+ bean.getValue() + "\">" + bean.getLabel() + "&nbsp;&nbsp; ");

		}

		return radio.toString();

	}

	/**
	 * 根据单级字典的规类码输出多个复选框
	 * 
	 * @param fieldName
	 *            - 复选框的名字
	 * @param typeID
	 *            - 字典规类码
	 */
	public static String getCheckBox(String fieldName, String typeID) {

		StringBuffer checkbox = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			checkbox.append("<input name=\"" + fieldName + "\" type=\"checkbox\" class=\"Checkbox\" value=\""
					+ bean.getValue() + "\">" + bean.getLabel() + "&nbsp;&nbsp; ");

		}

		return checkbox.toString();

	}

	/**
	 * 根据单级字典的规类码输出多个复选框
	 * 
	 * @param fieldName
	 *            - 复选框的名字
	 * @param typeID
	 *            - 字典规类码
	 * @param tagvals
	 *            - 需要定位的选项值(多个)
	 */
	public static String getCheckBox(String fieldName, String typeID, List tagVals) {
 
		if (tagVals == null || tagVals.size() < 1)
			return getCheckBox(fieldName, typeID);

		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			if (tagVals.contains(bean.getValue()))
				radio.append("<input name=\"" + fieldName + "\" type=\"checkbox\" class=\"Checkbox\" value=\""
						+ bean.getValue() + "\" checked>" + bean.getLabel() + "&nbsp;&nbsp; ");

			else
				radio.append("<input name=\"" + fieldName + "\" type=\"checkbox\" class=\"Checkbox\" value=\""
						+ bean.getValue() + "\">" + bean.getLabel() + "&nbsp;&nbsp; ");

		}

		return radio.toString();

	}
	/**
		* 根据单级字典的规类码输出多个复选框
		* @param fieldName - 复选框的名字
		* @param typeID - 字典规类码
		* @param tagvals - 需要定位的选项值(多个)	
		*/
		public static String getCheckBox(
			String fieldName,
			String typeID,
			String[] tagVals) {
 
			if (tagVals == null || tagVals.length < 1)
				return getCheckBox(fieldName, typeID);

			StringBuffer radio = new StringBuffer("");
			ArrayList options = (ArrayList) getOptionCollection(typeID);

			for (int i = 1; i < options.size(); i++) {
				LabelValueBean bean = (LabelValueBean) (options.get(i));
				boolean isContain = false;
				for (int j=0;j<tagVals.length;j++){
					if (tagVals[j].equals(bean.getValue())){
						isContain=true;
						break;
					}
				}
				if (isContain)
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
	

	public static String getRadioWithFun(String radioName, String typeID, String tagVal, String func) {

		if (tagVal == null || tagVal.trim().length() <= 0)
			return getRadio(radioName, typeID);

		StringBuffer radio = new StringBuffer("");
		ArrayList options = (ArrayList) getOptionCollection(typeID);

		for (int i = 1; i < options.size(); i++) {
			LabelValueBean bean = (LabelValueBean) (options.get(i));
			if (tagVal.equals(bean.getValue()))
				radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
						+ bean.getValue() + "\" onclick=\"" + func + "\" checked>" + bean.getLabel() + "&nbsp;&nbsp; ");

			else
				radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
						+ bean.getValue() + "\" onclick=\"" + func + "\">" + bean.getLabel() + "&nbsp;&nbsp; ");

		}

		return radio.toString();

	}
	//add 20100920 F10103 by lixq
	public static String getRadio_WithFun(String radioName, String typeID, String func) {
	   	StringBuffer radio = new StringBuffer("");
			ArrayList options = (ArrayList) getOptionCollection(typeID);

			for (int i = 1; i < options.size(); i++) {
				LabelValueBean bean = (LabelValueBean) (options.get(i));
					radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
							+ bean.getValue() + "\" onclick=\"" + func + "\">" + bean.getLabel() + "&nbsp;&nbsp; ");

			}
			return radio.toString();
		}
		
			public static String getRadio_WithFun(String radioName, String typeID, String tagVal, String func) {
			StringBuffer radio = new StringBuffer("");
			ArrayList options = (ArrayList) getOptionCollection(typeID);
			for (int i = 1; i < options.size(); i++) {
				LabelValueBean bean = (LabelValueBean) (options.get(i));
				if (tagVal.equals(bean.getValue()))
					radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
							+ bean.getValue() + "\" onclick=\"" + func + "\" checked>" + bean.getLabel() + "&nbsp;&nbsp; ");

				else
					radio.append("<input name=\"" + radioName + "\" type=\"radio\" class=\"Radiobutton\" value=\""
							+ bean.getValue() + "\" onclick=\"" + func + "\">" + bean.getLabel() + "&nbsp;&nbsp; ");

			}
			return radio.toString();
		}

}
