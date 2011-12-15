/*
 * @# ConflictProjectVO.java 2008-11-13 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseVO;


public class ConflictProjectVO extends BaseVO{
	private String projectId;
	private String preProject;
	private String status;
	private String program;
	private String projectName;
	/**
	 * @return
	 */
	public String getPreProject() {
		return preProject;
	}

	/**
	 * @return
	 */
	public String getProgram() {
		return program;
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
	 * @param string
	 */
	public void setPreProject(String string) {
		preProject = string;
	}

	/**
	 * @param string
	 */
	public void setProgram(String string) {
		program = string;
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

}
