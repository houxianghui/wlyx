package com.abc.dao;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.abc.exception.BeanProcessException;


/**
 * 操作类对象的域
 * 主要包括通过类型来查找域、通过域的名称来查找域、为域赋值
 * @author songlijun
 *
 */

public class BeanUtils 
{
	public static List getFieldsByType(Object object, Class type) 
	{
		List list = new ArrayList();
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getType().isAssignableFrom(type)) {
				list.add(field);
			}
		}
		return list;
	}
	
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException 
	{
		return getDeclaredField(object.getClass(), propertyName);
	}
	
	/**
	 * 根据域的名称来查找Class中对应的域
	 * 如本级Class找不到，需要去父Class去找，如都找不到抛出NoSuchFieldException
	 * @param clazz
	 * @param propertyName
	 * @return
	 * @throws NoSuchFieldException
	 */
	public static Field getDeclaredField(Class clazz, String propertyName) throws NoSuchFieldException 
	{
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) 
		{
			try 
			{
				return superClass.getDeclaredField(propertyName);
			}
			catch (NoSuchFieldException e) 
			{
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
	}
	
	/**
	 * 根据域的名称为指定对象的该域赋值
	 * 默认把double和decimal类型的域的值设置为int,即取整
	 * @param object 指定的对象
	 * @param propertyName 域的名称
	 * @param newValue 域的值
	 */
	public static void setDeclaredProperty(Object object, String propertyName, Object newValue)
	{		
		try
		{
			Field field = getDeclaredField(object, propertyName);
			String type = field.getType().getName();
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			//把double和decimal类型的设置为int,即取整
			if (type.equalsIgnoreCase("double") || type.equalsIgnoreCase("java.lang.Double") || newValue instanceof java.math.BigDecimal)
				field.setInt(object, (int) ((BigDecimal) newValue).doubleValue());
			
			else
				field.set(object, newValue);
			field.setAccessible(accessible);
		}
		catch(IllegalArgumentException e1)
		{
			throw new BeanProcessException(object.getClass(),propertyName,e1);
		}
		catch(IllegalAccessException e1)
		{
			throw new BeanProcessException(object.getClass(),propertyName,e1);
		}
		catch(NoSuchFieldException e2)
		{
			throw new BeanProcessException(object.getClass(),propertyName,e2);
		}
		
	}
	
	/**
	 * 根据域的名称从指定的对象中返回该域的value
	 * @param object
	 * @param propertyName
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	public static Object getDeclaredProperty(Object object, String propertyName) throws IllegalAccessException, NoSuchFieldException 
	{
		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		Object result = null;
		result = field.get(object);
		field.setAccessible(accessible);
		return result;
	}	
}
