/*
 * @# PreProjectVO.java 2008-11-11 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseVO;


public class PreProjectVO extends BaseVO {
	private String projectId;
	private String preProject;
	/**
	 * @return
	 */
	public String getPreProject() {
		return preProject;
	}

	/**
	 * @return
	 */
	public String getProjectId() {
		return projectId;
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
	public void setProjectId(String string) {
		projectId = string;
	}

}
