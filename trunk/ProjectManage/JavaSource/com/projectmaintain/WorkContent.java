/*
 * @# WorkContent.java 2008-11-28 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;


public class WorkContent {
	private boolean isBusy;
	private String projectId;
	private String status;
		
	/**
	 * @return
	 */
	public boolean isBusy() {
		return isBusy;
	}

	/**
	 * @return
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param b
	 */
	public void setBusy(boolean b) {
		isBusy = b;
	}

	/**
	 * @param string
	 */
	public void setProjectId(String string) {
		projectId = string;
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
	public void setStatus(String string) {
		status = string;
	}

}
