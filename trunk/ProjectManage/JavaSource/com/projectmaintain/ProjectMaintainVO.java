/*
 * @# ProjectMaintainVO.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */

package com.projectmaintain;

import com.eis.base.BaseVO;

public class ProjectMaintainVO extends BaseVO {
	private String projectId;
	private String startDate;
	private String endDate;
	private String status;
	private String userId;
	private String projectName;
	private String memo;
	private String issId;
	private String isInContract;
	private String needDev;
	private String reason;
	private String owning;
	private String projectClass;
	
	/**
	 * @return
	 */
	public String getEndDate() {
		return endDate;
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
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param string
	 */
	public void setEndDate(String string) {
		endDate = string;
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
	public void setStartDate(String string) {
		startDate = string;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string) {
		status = string;
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
	public void setUserId(String string) {
		userId = string;
	}

	/**
	 * @return
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param string
	 */
	public void setProjectName(String string) {
		projectName = string;
	}

	/**
	 * @return
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param string
	 */
	public void setMemo(String string) {
		memo = string;
	}

	/**
	 * @return
	 */
	public String getIssId() {
		return issId;
	}

	/**
	 * @param string
	 */
	public void setIssId(String string) {
		issId = string;
	}

	/**
	 * @return
	 */
	public String getIsInContract() {
		return isInContract;
	}

	/**
	 * @return
	 */
	public String getNeedDev() {
		return needDev;
	}

	/**
	 * @param string
	 */
	public void setIsInContract(String string) {
		isInContract = string;
	}

	/**
	 * @param string
	 */
	public void setNeedDev(String string) {
		needDev = string;
	}

	/**
	 * @return
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param string
	 */
	public void setReason(String string) {
		reason = string;
	}

	/**
	 * @return
	 */
	public String getOwning() {
		return owning;
	}

	/**
	 * @param string
	 */
	public void setOwning(String string) {
		owning = string;
	}

	/**
	 * @return
	 */
	public String getProjectClass() {
		return projectClass;
	}

	/**
	 * @param string
	 */
	public void setProjectClass(String string) {
		projectClass = string;
	}

}
