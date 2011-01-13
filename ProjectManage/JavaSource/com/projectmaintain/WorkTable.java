/*
 * @# WorkTable.java 2008-11-28 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;


public class WorkTable {
	private final int MAX_DAYS = 31;
	private String userId;
	private WorkContent[] table= new WorkContent[MAX_DAYS];
	/**
	 * @return
	 */
	public WorkContent[] getTable() {
		return table;
	}

	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param contents
	 */
	public void setTable(WorkContent[] contents) {
		table = contents;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string) {
		userId = string;
	}

}
