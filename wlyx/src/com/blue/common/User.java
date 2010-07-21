package com.blue.common;

import com.blue.tools.CookieManage;

public class User {
	private String userName;
	private String password;
	private String wealDate;	//�ϴ�ռ��ʱ��
	private String level;		//�ȼ�
	private String point;		//��������
	private String url;			//���ڷ�����
	private int beginTime;		//������ʼʱ��
	private int endTime;		//��������ʱ��
	private int workType;		//�ճ���������
	private boolean needXunLian;	//�Ƿ���Ҫ����
	private boolean needJingJi;		//�Ƿ���Ҫ����
	private String cookie;			
	private int challengeTimes;		//��Ҫ�Զ���������
	private int duelNo;				//����������
	private boolean fastChallenge;	//�Ƿ���پ���
	private String killMonstorOnce;	//ÿ�λ�ɱ�������
	
	
	public String getKillMonstorOnce() {
		return killMonstorOnce;
	}
	public void setKillMonstorOnce(String killMonstorOnce) {
		this.killMonstorOnce = killMonstorOnce;
	}
	
	
	public boolean isFastChallenge() {
		return fastChallenge;
	}
	public void setFastChallenge(boolean fastChallenge) {
		this.fastChallenge = fastChallenge;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
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
	
	public int getDuelNo() {
		return duelNo;
	}
	public void setDuelNo(int duelNo) {
		this.duelNo = duelNo;
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
