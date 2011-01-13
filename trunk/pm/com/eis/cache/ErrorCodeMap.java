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
 * 说明：错误信息缓存类
 * 
 */
public class ErrorCodeMap {

	static {
		loadDicMap();
	}

	private static HashMap map = new HashMap(20);
	private static boolean status = false;

	/**
	 * 装载错误信息缓存字典数据
	 *
	 */
	public static synchronized void loadDicMap() {

		map = new HashMap();

		DBQueryUtil db = new DBQueryUtil();

		status = true;

		try {

			//查询错误码信息列表
			ResultSet rs = db.sqlQuery("select * from  ep_error_code ");

			while (rs.next()) {

				map.put(
					rs.getString("ERROR_CODE").trim(),
					rs.getString("CAPTION").trim());
			}

			if (rs != null)
				rs.close();

		} catch (Exception ex) {
			SysLog.error("装载错误码字典失败！");
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

	/**
	 * 根据错误码返回错误信息
	 * @param errorCode - 错误码
	 * @return 错误信息
	 */

	public static String getErrorInfo(String errorCode) {

		if (!status) {
			loadDicMap();
		}

		String info = null;

		SysLog.info("错误代码:" + errorCode);

		Object obj = map.get(errorCode);

		if (null != obj)
			info = (String) obj;
		else {
			SysLog.error("未找到错误码对应的信息！错误代码=" + errorCode);
			info = "未知错误！";
		}			

		return info;
	}

}
