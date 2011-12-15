
package com.eis.util;

import com.eis.db.DBUtil;


public class DicTools {
	public static String getManagerName(String managerId){
		DBUtil db = null;
		try{
			db = new DBUtil();
			return db.sqlQuerySingle("select MANAGER_NAME from ep_expand_manager_info where SEQ_NO='"+managerId+"'");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		return "";
	}
}
