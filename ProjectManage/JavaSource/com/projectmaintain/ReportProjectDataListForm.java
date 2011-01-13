/*
 * 创建日期 2009-2-12
 *
 * zhengpy
 * 
 * ReportProjectDataListForm
 */
package com.projectmaintain;

import com.eis.base.BaseForm;

/**
 * @author zhengpy
 *
 * @
 */
public class ReportProjectDataListForm extends BaseForm {
	
	private String projectId;
	private String projectName;
	private String startDate;
	private String status;
	
	
	/**
	 * @return
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @return
	 */
	public String getProjectName() {
		return projectName;
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
	public void setProjectId(String string) {
		projectId = string;
	}

	/**
	 * @param string
	 */
	public void setProjectName(String string) {
		projectName = string;
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

}
