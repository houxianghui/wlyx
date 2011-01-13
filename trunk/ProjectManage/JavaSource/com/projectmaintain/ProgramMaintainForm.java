/*
 * @# ProgramMaintainForm.java 2008-11-12 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import org.apache.struts.upload.FormFile;

import com.eis.base.BaseForm;


public class ProgramMaintainForm extends BaseForm {
	private String projectId;
	private String program;
	private FormFile file;
	/**
	 * @return
	 */
	public FormFile getFile() {
		return file;
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
	 * @param file
	 */
	public void setFile(FormFile file) {
		this.file = file;
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
