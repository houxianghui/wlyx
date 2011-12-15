/*
 * @# ChangeRecordForm.java 2008-12-1 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseForm;


public class ChangeRecordForm extends BaseForm {
	private int recordId;
	private String projectId;
	private String userId;
	private String content;
	private String reason;
	private String changeDate;
	private int id;
	/**
	 * @return
	 */
	public String getChangeDate() {
		return changeDate;
	}

	/**
	 * @return
	 */
	public String getContent() {
		return content;
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
	public String getReason() {
		return reason;
	}

	/**
	 * @return
	 */
	public int getRecordId() {
		return recordId;
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
	public void setChangeDate(String string) {
		changeDate = string;
	}

	/**
	 * @param string
	 */
	public void setContent(String string) {
		content = string;
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
	public void setReason(String string) {
		reason = string;
	}

	/**
	 * @param i
	 */
	public void setRecordId(int i) {
		recordId = i;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string) {
		userId = string;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
