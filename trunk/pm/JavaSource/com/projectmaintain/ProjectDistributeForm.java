/*
 * @# ProjectDistributeForm.java 2008-11-21 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.Collection;

import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;


public class ProjectDistributeForm extends BaseForm {
	private int id;
	private String type;
	private String projectId;
	private String status;
	private String userId;
	private String startDate;
	private String endDate;
	private String userName;
	private String isDone;
	private String memo;
	private String reason;
	
	private Collection statusCollection;
	private Collection stuff;
	
	private String project_f;
	private String endDate_f;
	private String day;
	
	private String title;
	private Collection titleCollection;
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
	public Collection getStuff() {
		return ReDefSDicMap.getOptionCollection(RedefSDicCodes.STUFF_COLLECTION);
	}

	/**
	 * @param collection
	 */
	public void setStuff(Collection collection) {
		stuff = collection;
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
	public String getProject_f() {
		return project_f;
	}


	/**
	 * @param string
	 */
	public void setProject_f(String string) {
		project_f = string;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection getTitleCollection() {
		return SingleDicMap.getOptionCollection(SingleDic.TITLE);
	}

	public void setTitleCollection(Collection titleCollection) {
		this.titleCollection = titleCollection;
	}


}
