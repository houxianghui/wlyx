package com.blue.common;

import java.util.List;

import com.blue.duel.Challenger;
import com.blue.task.Task;
import com.blue.tools.CookieManage;

public class User {
	private String userName;
	private String password;
	private String wealDate;
	
	public String getWealDate() {
		return wealDate;
	}
	public void setWealDate(String wealDate) {
		this.wealDate = wealDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = "http://"+url+"/";
	}
	public int getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getWorkType() {
		return workType;
	}
	public void setWorkType(int workType) {
		this.workType = workType;
	}
	public boolean isNeedXunLian() {
		return needXunLian;
	}
	public void setNeedXunLian(boolean needXunLian) {
		this.needXunLian = needXunLian;
	}
	public boolean isNeedJingJi() {
		return needJingJi;
	}
	public void setNeedJingJi(boolean needJingJi) {
		this.needJingJi = needJingJi;
	}
	private String url;
	private int beginTime;
	private int endTime;
	private int workType;
	private boolean needXunLian;
	private boolean needJingJi;
	private String cookie;
	private int challengeTimes;
	private List<Challenger> challengeList;
	private int duelNo;
	private List<Task> taskList;
	
	
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	public int getDuelNo() {
		return duelNo;
	}
	public void setDuelNo(int duelNo) {
		this.duelNo = duelNo;
	}
	public List<Challenger> getChallengeList() {
		return challengeList;
	}
	public void setChallengeList(List<Challenger> challengeList) {
		this.challengeList = challengeList;
	}
	public int getChallengeTimes() {
		return challengeTimes;
	}
	public void setChallengeTimes(int challengeTimes) {
		this.challengeTimes = challengeTimes;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public boolean login()throws Exception{
		setCookie(CookieManage.getCookie(this));
		if(getCookie() == null){
			return false;
		}else{
			return true;
		}
	}
}
