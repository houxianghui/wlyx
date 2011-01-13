/*********************************************************
 * File: ErrorCodeMap.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-24
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
import com.eis.util.*;

/**
 * ˵����������Ϣ������
 * 
 */
public class ErrorCodeMap {

	static {
		loadDicMap();
	}

	private static HashMap map = new HashMap(20);
	private static boolean status = false;

	/**
	 * װ�ش�����Ϣ�����ֵ�����
	 *
	 */
	public static synchronized void loadDicMap() {

		map = new HashMap();

		DBQueryUtil db = new DBQueryUtil();

		status = true;

		try {

			//��ѯ��������Ϣ�б�
			ResultSet rs = db.sqlQuery("select * from  ep_error_code ");

			while (rs.next()) {

				map.put(
					rs.getString("ERROR_CODE").trim(),
					rs.getString("CAPTION").trim());
			}

			if (rs != null)
				rs.close();

		} catch (Exception ex) {
			SysLog.error("װ�ش������ֵ�ʧ�ܣ�");
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
	 * ���ݴ����뷵�ش�����Ϣ
	 * @param errorCode - ������
	 * @return ������Ϣ
	 */

	public static String getErrorInfo(String errorCode) {

		if (!status) {
			loadDicMap();
		}

		String info = null;

		SysLog.info("�������:" + errorCode);

		Object obj = map.get(errorCode);

		if (null != obj)
			info = (String) obj;
		else {
			SysLog.error("δ�ҵ��������Ӧ����Ϣ���������=" + errorCode);
			info = "δ֪����";
		}			

		return info;
	}

}
