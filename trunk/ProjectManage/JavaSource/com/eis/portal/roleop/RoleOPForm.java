/*
 * �������� 2005-9-24
 *
 */
package com.eis.portal.roleop;
import com.eis.base.BaseForm;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class RoleOPForm extends BaseForm {

	/**
	 * ���캯��
	 */
	public RoleOPForm() {
		super();

	}

	private String role_id; //��ɫ����

	private String role_name; //��ɫ����

	/**
	 * @return
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @return
	 */
	public String getRole_name() {
		return role_name;
	}

	/**
	 * @param string
	 */
	public void setRole_id(String string) {
		role_id = string;
	}

	/**
	 * @param string
	 */
	public void setRole_name(String string) {
		role_name = string;
	}

}
