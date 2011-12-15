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
public class ReportProjectDataListVO extends BaseForm {
	
	private String projectId;
	private String issId;
	private String projectName;
	private String startDate;
	private String status;
	private String designBRD;
	private String designFTS;
	private String designDEV;
	private String designSIT;
	private String designUAT;
	private String finalBRD;
	private String finalFTS;
	private String finalDEV;
	private String finalSIT;
	private String finalUAT;
	private String designDay;
	private String finalDay;
	private String errorRate;
	
	/**
	 * @return
	 */
	public String getDesignBRD() {
		return designBRD==null?"":designBRD;
	}

	/**
	 * @return
	 */
	public String getDesignDay() {
		return designDay==null?"":designDay;
	}

	/**
	 * @return
	 */
	public String getDesignDEV() {
		return designDEV==null?"":designBRD;
	}

	/**
	 * @return
	 */
	public String getDesignFTS() {
		return designFTS==null?"":designFTS;
	}

	/**
	 * @return
	 */
	public String getDesignSIT() {
		return designSIT==null?"":designSIT;
	}

	/**
	 * @return
	 */
	public String getDesignUAT() {
		return designUAT==null?"":designUAT;
	}

	/**
	 * @return
	 */
	public String getErrorRate() {
		return errorRate==null?"":errorRate;
	}

	/**
	 * @return
	 */
	public String getFinalBRD() {
		return finalBRD==null?"":finalBRD;
	}

	/**
	 * @return
	 */
	public String getFinalDay() {
		return finalDay==null?"":finalDay;
	}

	/**
	 * @return
	 */
	public String getFinalDEV() {
		return finalDEV==null?"":finalDEV;
	}

	/**
	 * @return
	 */
	public String getFinalFTS() {
		return finalFTS==null?"":finalFTS;
	}

	/**
	 * @return
	 */
	public String getFinalSIT() {
		return finalSIT==null?"":finalSIT;
	}

	/**
	 * @return
	 */
	public String getFinalUAT() {
		return finalUAT==null?"":finalUAT;
	}

	/**
	 * @return
	 */
	public String getProjectId() {
		return projectId==null?"":projectId;
	}

	/**
	 * @return
	 */
	public String getProjectName() {
		return projectName==null?"":projectName;
	}

	/**
	 * @return
	 */
	public String getStartDate() {
		return startDate==null?"":startDate;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status==null?"":status;
	}

	/**
	 * @param string
	 */
	public void setDesignBRD(String string) {
		designBRD = string;
	}

	/**
	 * @param string
	 */
	public void setDesignDay(String string) {
		designDay = string;
	}

	/**
	 * @param string
	 */
	public void setDesignDEV(String string) {
		designDEV = string;
	}

	/**
	 * @param string
	 */
	public void setDesignFTS(String string) {
		designFTS = string;
	}

	/**
	 * @param string
	 */
	public void setDesignSIT(String string) {
		designSIT = string;
	}

	/**
	 * @param string
	 */
	public void setDesignUAT(String string) {
		designUAT = string;
	}

	/**
	 * @param string
	 */
	public void setErrorRate(String string) {
		errorRate = string;
	}

	/**
	 * @param string
	 */
	public void setFinalBRD(String string) {
		finalBRD = string;
	}

	/**
	 * @param string
	 */
	public void setFinalDay(String string) {
		finalDay = string;
	}

	/**
	 * @param string
	 */
	public void setFinalDEV(String string) {
		finalDEV = string;
	}

	/**
	 * @param string
	 */
	public void setFinalFTS(String string) {
		finalFTS = string;
	}

	/**
	 * @param string
	 */
	public void setFinalSIT(String string) {
		finalSIT = string;
	}

	/**
	 * @param string
	 */
	public void setFinalUAT(String string) {
		finalUAT = string;
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

	/**
	 * @return
	 */
	public String getIssId() {
		return issId==null?"":issId;
	}

	/**
	 * @param string
	 */
	public void setIssId(String string) {
		issId = string;
	}

}
