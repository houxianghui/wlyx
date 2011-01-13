/*
 * @# ProjectMaintainForm.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;


public class ProjectMaintainForm extends BaseForm {
	
	public static final String OWNING_COLLECTION = "9988";
	public static final String EIS = "3";
	public static final String V_PLUS = "2";
	public static final String EIS_COLLECTION = "9987";
	public static final String V_PLUS_COLLECTION = "9986";
	
	private String projectId;
	private String startDate;
	private String endDate;
	private String status;
	private String userId;
	private String projectName;
	private String preProjects;
	private String memo;
	private String issId;
	private String isInContract;
	private String needDev;
	private String reason;
	private String owning;
	private String projectId_f;
	private String status_f;
	private String endDate_f;
	private String day;
	private String projectClass;
	private Collection statusCollection;
	private Collection isInContractCollection;
	private Collection needDevCollection;
	private Collection owningCollection;
	private Collection projectClassCollection;
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
	public Collection getStatusCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.PROJECT_MANAGE_COLLECTION);
	}

	/**
	 * @param collection
	 */
	public void setStatusCollection(Collection collection) {
		statusCollection = collection;
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
	public String getPreProjects() {
		return preProjects;
	}

	/**
	 * @param string
	 */
	public void setPreProjects(String string) {
		preProjects = string;
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
	public Collection getIsInContractCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.YES_OR_NO);
	}

	/**
	 * @return
	 */
	public Collection getNeedDevCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.YES_OR_NO);
	}

	/**
	 * @param collection
	 */
	public void setIsInContractCollection(Collection collection) {
		isInContractCollection = collection;
	}

	/**
	 * @param collection
	 */
	public void setNeedDevCollection(Collection collection) {
		needDevCollection = collection;
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
	public String getProjectId_f() {
		return projectId_f;
	}

	/**
	 * @return
	 */
	public String getStatus_f() {
		return status_f;
	}

	/**
	 * @param string
	 */
	public void setProjectId_f(String string) {
		projectId_f = string;
	}

	/**
	 * @param string
	 */
	public void setStatus_f(String string) {
		status_f = string;
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
	public Collection getOwningCollection() {
		return SingleDicMap.getOptionCollection(OWNING_COLLECTION);
	}

	/**
	 * @param collection
	 */
	public void setOwningCollection(Collection collection) {
		owningCollection = collection;
	}

	/**
	 * @return
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @return
	 */
	public String getEndDate_f() {
		return endDate_f;
	}

	/**
	 * @param string
	 */
	public void setDay(String string) {
		day = string;
	}

	/**
	 * @param string
	 */
	public void setEndDate_f(String string) {
		endDate_f = string;
	}

	/**
	 * @return
	 */
	public String getProjectClass() {
		return (projectClass==null?"":projectClass.trim());
	}

	/**
	 * @param string
	 */
	public void setProjectClass(String string) {
		projectClass = string;
	}

	/**
	 * @return
	 */
	public Collection getProjectClassCollection() {
		if(projectClass != null){
			if(V_PLUS.equals(owning)){
				return SingleDicMap.getOptionCollection(V_PLUS_COLLECTION);
			}else if(EIS.equals(owning)){
				return SingleDicMap.getOptionCollection(EIS_COLLECTION);
			}
		}
		
		return new HashMap().values();
		
	}
	public String getProjectClassName(){
		if(projectClass != null){
			if(V_PLUS.equals(owning)){
				return SingleDicMap.getDicItemVal(V_PLUS_COLLECTION,projectClass.trim());
			}else if(EIS.equals(owning)){
				return SingleDicMap.getDicItemVal(EIS_COLLECTION,projectClass.trim());
			}
		}
		
		return "";
	}
	/**
	 * @param collection
	 */
	public void setProjectClassCollection(Collection collection) {
		projectClassCollection = collection;
	}

}
