/*
 * @# ProjectDistributeVO.java 2008-11-21 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseVO;


public class ProjectDistributeVO extends BaseVO{
	private int id;
	
	private String projectId;
	private String userName;
	private String status;
	private String userId;
	private String startDate;
	private String endDate;
	private String isDone;
	private String memo;
	private String reason;
	private String ready;
	
	private double score;
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
	 * @return
	 */
	public String getUserId() {
		return userId;
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
	 * @param string
	 */
	public void setUserId(String string) {
		userId = string;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}

	/**
	 * @return
	 */
	public String getIsDone() {
		return isDone;
	}

	/**
	 * @param string
	 */
	public void setIsDone(String string) {
		isDone = string;
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
	public String getReady() {
		return ready;
	}

	/**
	 * @param string
	 */
	public void setReady(String string) {
		ready = string;
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



	/**
	 * @return
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param d
	 */
	public void setScore(double d) {
		score = d;
	}

}
