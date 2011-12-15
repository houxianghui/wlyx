package com.eis.base;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.Assert;



public class BeanUtils {
	public static List getFieldsByType(Object object, Class type) {
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
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}
	public static Field getDeclaredField(Class clazz, String propertyName) throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			}
			catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
	}
	public static void setDeclaredProperty(Object object, String propertyName, Object newValue) throws IllegalAccessException, NoSuchFieldException {		
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = getDeclaredField(object, propertyName);
		String type = field.getType().getName();
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		if (type.equalsIgnoreCase("double") || type.equalsIgnoreCase("java.lang.Double") || newValue instanceof java.math.BigDecimal)
			field.setInt(object, (int) ((BigDecimal) newValue).doubleValue());
		else
			field.set(object, newValue);
		field.setAccessible(accessible);
	}
	public static Object getDeclaredProperty(Object object, String propertyName) throws IllegalAccessException, NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		Object result = null;
		result = field.get(object);
		field.setAccessible(accessible);
		return result;
	}
	
}
