/*
 * 创建日期 2006-9-21
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
	 * 根据参数编号获得参数值
	 * @param paramID 参数编号
	 * @return 参数值
	 */
	public static String getParamVal(String paramID) throws Exception{
		
		BaseDAO  dao = (BaseDAO)getBean("sysparam_dao");		
		return dao.querySingle("select PARAM_VAL  from ep_sys_param where PARAM_ID='"+paramID+"'");

	}
	
	
	/**
	 * 根据bean ID获得实例对象
	 * @param name - bean ID	 
	 * @return bean的实例对象
	 *
	 */
	public static Object getBean(String name) throws Exception {
		
		return BeanFactory.getBean(name);
	}

}
