/*********************************************************
 * File: RedefMDicMap
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
import com.eis.dic.mdic.*;

/**
 * 说明: 自定义多级字典缓存类
 * 
 */
public class RedefMDicMap {

	static {
		loadDicMap();
	}

	private static HashMap map = null;

	private static HashMap optionMap = null;

	private static boolean status = false;

	/**
	 * 构造函数
	 */
	public RedefMDicMap() {
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
		String typeName = null;
		status = true;

		try {

			ResultSet rs = null;
			ResultSet rs1 =
				db.sqlQuery(
					"select * from  ep_redef_mdic");

			while (rs1.next()) {

				typeID = rs1.getString("TYPE_ID").trim();
				typeName = rs1.getString("CAPTION").trim();
				
				rs =
					db.sqlQueryR(rs1.getString("STMT"));

				String itemID = null;

				//存放每一类多级字典的MAP对象
				HashMap typeMap = new HashMap();
				
				
				//存放每一类多级字典的ArrayList对象
				ArrayList options = new ArrayList();

				
				while (rs.next()) {

					MDicVO vo = new MDicVO();

					itemID = rs.getString("ITEM_ID").trim();

					vo.setItem_id(itemID);
					vo.setItem_val(rs.getString("ITEM_VAL").trim());
					//vo.setItem_level(rs.getShort("ITEM_LEVEL"));
					vo.setLogic_id(rs.getString("LOGIC_ID").trim());
					//vo.setList_order(rs.getShort("LIST_ORDER"));
					vo.setParent_id(rs.getLong("PARENT_ID"));
					//vo.setSys_id(rs.getLong("SYS_ID"));

					typeMap.put(itemID, vo);
					
					options.add(vo);				

				}
				
				map.put(typeID, typeMap);
				
				map.put(typeID+"name", typeName);
				
				map.put(typeID+"list", options);

			}

			if (rs != null)
				rs.close();

			if (rs1 != null)
				rs1.close();

		} catch (Exception ex) {
			SysLog.error("装载自定义多级数据字典失败！");
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
	 * 根据规类码返回字典名称
	 * @param typeID - 归类码
	 * @return 字典名称 
	 */
	public static String getDicMapName(String typeID) {

		if (!status) {
			loadDicMap();
		}
		

		return (String)map.get(typeID+"name");

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
				
		return (Collection)map.get(typeID+"list");

	}

	/**
	 * 获得所有字典数据的map
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
	* 查询数据字典项名称
	* @param typeID - 规类码
	* @param itemID - 选项值
	*/
	public static String getDicItemVal(String typeID, String itemID) {
		if (!status) {
			loadDicMap();
		}

		HashMap typeMap = (HashMap) map.get(typeID);

		MDicVO vo = (MDicVO) typeMap.get(itemID);

		if (vo == null)
			return "";
		else
			return vo.getItem_val();

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

		MDicVO vo = (MDicVO) typeMap.get(itemID);

		if (vo == null)
			return null;
		else
			return vo.getLogic_id();
	}

}
