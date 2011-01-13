/*
 * 创建日期 2007-12-28
 *
 * houxh
 */
package com.eis.util;

import com.eis.exception.MessageException;
import com.eis.exception.OpenWinException;

/**
 * @author houxh
 *
 */
public class ValidateUtil {
	/**
     * @author houxh 2008-1-14
     * 必输项不能为空或NULl
     * @param key
     * @param name
     * @throws MessageException 
     */
    public static void rejectIfEmpty(String key,String name) throws MessageException{
		if(key == null || key.trim().length() == 0){
			throw new MessageException(name+"为必输项");
		}
	}
	public static void alertEmptyMessage(String key,String msg)throws MessageException{
		if(key == null || key.trim().length() == 0){
			throw new MessageException(msg);
		}
	}
	/**
     * @author houxh 2008-1-14
     * 
     * 对于NULL的对象，抛出msg提示
     * 
     * @param obj
     * @param msg
     * @throws MessageException 
     */
    public static void rejectNullValue(Object obj,String msg)throws MessageException{
		if(obj == null){
			throw new MessageException(msg);
		}
	}
	
	public static void rejectNullObjectOfOpenWin(Object obj,String msg)throws OpenWinException{
		if(obj == null){
			throw new OpenWinException(msg);
		}
	}
	public static boolean stringEquals(String src,String target){
		if(src == null || target == null){
			return false;
		}
		return src.trim().equals(target.trim());
	}
}
