/*
 * �������� 2006-9-21
 *
 * 
 */
package com.eis.util;


import java.sql.*;
import java.util.*;

import org.apache.struts.util.*;
import com.eis.base.*;
import com.eis.cache.*;
import com.eis.util.*;

import com.eis.factory.*;

public class BizUtil {
	
	/**
	 * ���ݲ�����Ż�ò���ֵ
	 * @param paramID �������
	 * @return ����ֵ
	 */
	public static String getParamVal(String paramID) throws Exception{
		
		BaseDAO  dao = (BaseDAO)getBean("sysparam_dao");		
		return dao.querySingle("select PARAM_VAL  from ep_sys_param where PARAM_ID='"+paramID+"'");

	}
	
	
	/**
	 * ����bean ID���ʵ������
	 * @param name - bean ID	 
	 * @return bean��ʵ������
	 *
	 */
	public static Object getBean(String name) throws Exception {
		
		return BeanFactory.getBean(name);
	}

}
