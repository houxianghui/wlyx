/*
 * @# ProgramMaintainVO.java 2008-11-12 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseVO;


public class ProgramMaintainVO extends BaseVO {
	private String projectId;
	private String program;
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

}
