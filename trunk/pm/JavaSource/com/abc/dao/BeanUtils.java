package com.abc.dao;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.abc.exception.BeanProcessException;


/**
 * ������������
 * ��Ҫ����ͨ��������������ͨ�����������������Ϊ��ֵ
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
	 * �����������������Class�ж�Ӧ����
	 * �籾��Class�Ҳ�������Ҫȥ��Classȥ�ң��綼�Ҳ����׳�NoSuchFieldException
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
				// Field���ڵ�ǰ�ඨ��,��������ת��
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
	}
	
	/**
	 * �����������Ϊָ������ĸ���ֵ
	 * Ĭ�ϰ�double��decimal���͵����ֵ����Ϊint,��ȡ��
	 * @param object ָ���Ķ���
	 * @param propertyName �������
	 * @param newValue ���ֵ
	 */
	public static void setDeclaredProperty(Object object, String propertyName, Object newValue)
	{		
		try
		{
			Field field = getDeclaredField(object, propertyName);
			String type = field.getType().getName();
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			//��double��decimal���͵�����Ϊint,��ȡ��
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
	 * ����������ƴ�ָ���Ķ����з��ظ����value
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
