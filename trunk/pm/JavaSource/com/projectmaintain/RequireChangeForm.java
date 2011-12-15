/*
 * @# RequireChangeForm.java 2009-6-12 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseForm;


public class RequireChangeForm extends BaseForm {
	private String projectId;
	private int version;
	private String content;
	private String reason;
	private String userId;
	private String changeDate;
	
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
	public String getUserId() {
		return userId;
	}

	/**
	 * @return
	 */
	public int getVersion() {
		return version;
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
	 * @param string
	 */
	public void setUserId(String string) {
		userId = string;
	}

	/**
	 * @param i
	 */
	public void setVersion(int i) {
		version = i;
	}

}
