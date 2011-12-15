/*
 * @# IssueRecordForm.java 2009-6-11 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseForm;


public class IssueRecordForm extends BaseForm {
	private String projectId;
	private String status;
	private String userId;
	private String memo;
	private String checkUser;
	private int id;
	private int distributeId;
	/**
	 * @return
	 */
	public String getCheckUser() {
		return checkUser;
	}

	/**
	 * @return
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @return
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param string
	 */
	public void setCheckUser(String string) {
		checkUser = string;
	}

	/**
	 * @param string
	 */
	public void setMemo(String string) {
		memo = string;
	}

	/**
	 * @param string
	 */
	public void setProjectId(String string) {
		projectId = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string) {
		status = string;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string) {
		userId = string;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	public int getDistributeId() {
		return distributeId;
	}

	public void setDistributeId(int distributeId) {
		this.distributeId = distributeId;
	}

}
