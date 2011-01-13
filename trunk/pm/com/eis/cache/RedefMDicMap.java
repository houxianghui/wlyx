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
 * ˵��: �Զ���༶�ֵ仺����
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
	 * ���캯��
	 */
	public RedefMDicMap() {
		super();

	}

	/**
	 * װ���ֵ�����
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

				//���ÿһ��༶�ֵ��MAP����
				HashMap typeMap = new HashMap();
				
				
				//���ÿһ��༶�ֵ��ArrayList����
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
			SysLog.error("װ���Զ���༶�����ֵ�ʧ�ܣ�");
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
	 * ���ݹ����뷵���ֵ�����
	 * @param typeID - ������
	 * @return �ֵ����� 
	 */
	public static String getDicMapName(String typeID) {

		if (!status) {
			loadDicMap();
		}
		

		return (String)map.get(typeID+"name");

	}

	/**
	 * ���ݹ����뷵��ѡ����󼯺�
	 * @param typeID - ������
	 * @return ѡ����󼯺� 
	 */
	public static java.util.Collection getOptionCollection(String typeID) {

		if (!status) {
			loadDicMap();
		}
				
		return (Collection)map.get(typeID+"list");

	}

	/**
	 * ��������ֵ����ݵ�map
	 * @return �����ֵ����ݵ�map
	 */
	public static java.util.HashMap getMap() {

		if (!status) {
			loadDicMap();
		}

		return map;
	}

	/**
	 * ����װ���ֵ� 
	 */
	public static void reloadDicMap() {
		status = false;
		loadDicMap();
	}


	/**
	* ��ѯ�����ֵ�������
	* @param typeID - ������
	* @param itemID - ѡ��ֵ
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
	* ��������ֵ�����߼����ƴ���
	* @param typeID - ������
	* @param itemID - ѡ��ֵ
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
