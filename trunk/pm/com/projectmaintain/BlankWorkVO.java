/*
 * �������� 2009-2-27
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.projectmaintain;

import com.eis.base.BaseVO;

/**
 * @author doria
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class BlankWorkVO extends BaseVO {
	private String sdate;
	private String edate;
	private String user;
	/**
	 * @return
	 */
	public String getEdate() {
		return edate;
	}

	/**
	 * @return
	 */
	public String getSdate() {
		return sdate;
	}

	/**
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param string
	 */
	public void setEdate(String string) {
		edate = string;
	}

	/**
	 * @param string
	 */
	public void setSdate(String string) {
		sdate = string;
	}

	/**
	 * @param string
	 */
	public void setUser(String string) {
		user = string;
	}

}
