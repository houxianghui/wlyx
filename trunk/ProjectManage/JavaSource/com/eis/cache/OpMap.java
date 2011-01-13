/*********************************************************
 * File: OpMap.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-18
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
import com.eis.config.*;
/**
 * ˵������ɫ����Ȩ�޻�����
 * 
 */
public class OpMap {

	static {
		loadDicMap();
	}

	private static HashMap map = new HashMap(20);
	private static boolean status = false;
	private static int authMark = 0;

	/**
	 * װ�ؽ�ɫ����Ȩ���ֵ�����
	 *
	 */
	public static synchronized void loadDicMap() {

		map = new HashMap();

		DBQueryUtil db = new DBQueryUtil();
		String typeID = null;
		status = true;

		authMark = Integer.parseInt(SysConfig.getProperty("authMark"));

		try {

			ResultSet rs1 = null;

			//��ѯ��ɫ�б�
			ResultSet rs =
				db.sqlQuery("select ROLE_ID from  ep_role where STAT='1' ");

			while (rs.next()) {

				//װ��ÿ����ɫ�Ĳ���Ȩ���ֵ�
				String roleID = rs.getString("ROLE_ID").trim();
				rs1 =
					db.sqlQueryR(
						"select OP_CODE from  ep_role_auth where ROLE_ID='"
							+ roleID
							+ "' ");
				HashMap opMap = new HashMap();

				while (rs1.next()) {
					opMap.put(rs1.getString("OP_CODE").trim(), "1");

				}

				map.put(roleID, opMap);
			}

			if (rs1 != null)
				rs1.close();

			if (rs != null)
				rs.close();

		} catch (Exception ex) {
			SysLog.error("װ�ؽ�ɫ����Ȩ���ֵ�ʧ�ܣ�");
			SysLog.error(ex.getMessage());
			status = false;
		} finally {
			db.close();

		}

	}

	/**
	 * ����װ���ֵ� 
	 */
	public static void reloadDicMap() {
		status = false;
		loadDicMap();
	}

	/**
	 * ���ݽ�ɫ��źͲ���Ȩ�������ɫ�Ƿ��в���Ȩ��
	 * @param roleID - ��ɫ���
	 * @param opCode - ������
	 * @return boolean (true:��Ȩ��  ��false:��Ȩ��)
	 */

	public static boolean checkRoleAuth(String roleID, String opCode) {

		if (!status) {
			loadDicMap();
		}

		if (map.get(roleID) == null)
			return false;
		HashMap opMap = (HashMap) map.get(roleID);
		if (authMark <= 0 || opMap.containsKey(opCode))
			return true;
		else
			return false;

	}

}
