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
			"������Ϊ"
				+ entityClass.getName()
				+ "�������ʧ��!["
				+ cause.getMessage()
				+ "]");
	}

	public BeanProcessException(
		Class entityClass,
		String fieldName,
		Throwable cause) {
		super(
			"Ϊ��Ϊ"
				+ entityClass.getName()
				+ "��������"
				+ fieldName
				+ "�����Ը�ֵʧ��!["
				+ cause.getMessage()
				+ "]");
	}

	
}
