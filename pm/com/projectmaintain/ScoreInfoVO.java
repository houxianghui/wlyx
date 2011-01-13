/*
 * @# ScoreInfoVO.java 2009-5-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import com.eis.base.BaseVO;


public class ScoreInfoVO extends BaseVO {
	private String projectId;
	private String status;
	private String userId;
	
	private String checkNo;
	private String checkName;
	private String memo;
	private String allGrade;
	private String grade;
	private int defaultScore;
	private int score;
	private int upperScore;
	private int lowerScore;
	private double modulus;
	private String checkUser;
	
	/**
	 * @return
	 */
	public String getAllGrade() {
		return allGrade;
	}

	/**
	 * @return
	 */
	public String getCheckNo() {
		return checkNo;
	}

	/**
	 * @return
	 */
	public int getDefaultScore() {
		return defaultScore;
	}

	/**
	 * @return
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @return
	 */
	public String getMemo() {
		return memo;
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
	public int getScore() {
		return score;
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
	public void setAllGrade(String string) {
		allGrade = string;
	}

	/**
	 * @param string
	 */
	public void setCheckNo(String string) {
		checkNo = string;
	}

	/**
	 * @param i
	 */
	public void setDefaultScore(int i) {
		defaultScore = i;
	}

	/**
	 * @param string
	 */
	public void setGrade(String string) {
		grade = string;
	}

	/**
	 * @param string
	 */
	public void setMemo(String string) {
		memo = string;
	}

	/**
	 * @param string
	 */
	public void setProjectId(String string) {
		projectId = string;
	}

	/**
	 * @param i
	 */
	public void setScore(int i) {
		score = i;
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
	public String getCheckName() {
		return checkName;
	}

	/**
	 * @param string
	 */
	public void setCheckName(String string) {
		checkName = string;
	}

	/**
	 * @return
	 */
	public int getLowerScore() {
		return lowerScore;
	}

	/**
	 * @return
	 */
	public double getModulus() {
		return modulus;
	}

	/**
	 * @return
	 */
	public int getUpperScore() {
		return upperScore;
	}

	/**
	 * @param i
	 */
	public void setLowerScore(int i) {
		lowerScore = i;
	}

	/**
	 * @param d
	 */
	public void setModulus(double d) {
		modulus = d;
	}

	/**
	 * @param i
	 */
	public void setUpperScore(int i) {
		upperScore = i;
	}

	/**
	 * @return
	 */
	public String getCheckUser() {
		return checkUser;
	}

	/**
	 * @param string
	 */
	public void setCheckUser(String string) {
		checkUser = string;
	}

}
