package com.eis.dic.dictype;

import com.eis.base.BaseForm;

/** 
 * ˵�����ֵ������Ϣ�����ݶ��� 
 */
public class DicTypeForm extends BaseForm {

	/** 
	 * ���캯�� 
	 */
	public DicTypeForm() {
		super();
	}

	private String type_id_f;
	private String type_name_f;
	private String dic_level_caption;

	/** 
	 * ������ 
	 */
	private String type_id;

	/** 
	 * ���� 
	 */
	private String type_name;

	/** 
	 * �����־ 
	 */
	private String dic_level;

	public String getType_id_f() {
		return type_id_f;
	}
	public String getType_name_f() {
		return type_name_f;
	}
	/** 
	 *  ��ù����� 
	 */
	public String getType_id() {
		return type_id;
	}

	/** 
	 *  ������� 
	 */
	public String getType_name() {
		return type_name;
	}

	/** 
	 *  ��ü����־ 
	 */
	public String getDic_level() {
		return dic_level;
	}

	public void setType_id_f(String str) {
		type_id_f = str;
	}
	public void setType_name_f(String str) {
		type_name_f = str;
	}
	/** 
	 *  ���ù����� 
	 */
	public void setType_id(String str) {
		type_id = str;
	}

	/** 
	 *  �������� 
	 */
	public void setType_name(String str) {
		type_name = str;
	}

	/** 
	 *  ���ü����־ 
	 */
	public void setDic_level(String str) {
		dic_level = str;
	}

	/**
	 * @return
	 */
	public String getDic_level_caption() {
		//�����ֵ�����ת��
		if (getDic_level().equals("1"))
			dic_level_caption = "�����ֵ�";
		else
			dic_level_caption = "�༶�ֵ�";

		return dic_level_caption;
	}

	/**
	 * @param string
	 */
	public void setDic_level_caption(String string) {
		dic_level_caption = string;
	}

}
