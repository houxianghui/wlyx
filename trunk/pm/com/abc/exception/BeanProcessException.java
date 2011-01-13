package com.abc.exception;


public class BeanProcessException extends RuntimeException {
	private Class entityClass;
	private String fieldName;
	public Class getEntityClass() {
		return entityClass;
	}
	public String getFieldName() {
		return fieldName;
	}

	public BeanProcessException(Class entityClass, Throwable cause) {
		super(
			"创建名为"
				+ entityClass.getName()
				+ "的类对象失败!["
				+ cause.getMessage()
				+ "]");
	}

	public BeanProcessException(
		Class entityClass,
		String fieldName,
		Throwable cause) {
		super(
			"为名为"
				+ entityClass.getName()
				+ "的类对象的"
				+ fieldName
				+ "的属性赋值失败!["
				+ cause.getMessage()
				+ "]");
	}

	
}
