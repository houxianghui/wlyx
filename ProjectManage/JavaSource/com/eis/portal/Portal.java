/*********************************************************
 * File: Portal.java
 * 
 * Version 1.0
 * 
 * Date     2005-10-19
 * 
 * Author   xin yong
 * 
 ********************************************************/

package com.eis.portal;


import java.util.*;
import java.sql.*;

import com.eis.connectionPool.*;
import com.eis.db.*;
import com.eis.base.*;
import com.eis.util.*;

/**
 * ˵����
 * 
 */
public class Portal {
	
	public static void init(){
		DBUtil db = new DBUtil();		
		try {
			//�����û���¼��¼��
			db.sqlUpdate("delete from ep_user_login");

		
			
		} catch (Exception ex) {
			SysLog.error("�Ż���������ʧ�ܣ�");
			SysLog.error(ex.getMessage());			
		} finally {
			db.close();

		}

	}

}
