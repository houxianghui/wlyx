package com.blue.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.blue.daily.DailyAward;
import com.blue.daily.DailyWealsThread;
import com.blue.duel.DuelThread;
import com.blue.monstor.MonstorThread;
import com.blue.slavy.CatchSlavyThread;
import com.blue.task.AutoRewardThread;
import com.blue.task.AutoTaskThread;
import com.blue.team.WuGuanThread;
import com.blue.tianjitang.TianJiThread;
import com.blue.tools.PageService;
import com.blue.warrior.WarriorThread;

public class User {
	private Logger logger  = Logger.getLogger(this.getClass());
	private String userName;
	private String password;
	private String needWar;
	private String gloryBuy;
	private int buildDoor;	//2 ���䣬3 ��ȸ 4 �׻� 5 ����
	private int blackStartTime;
	private int blackEndTime;
	private int mingZhong;
	private int duoShan;
	private int baoJi;
	private int poJi;
	private int maxHP;
	private int tianJiDoor=1;	// 1 �ĺ��ⷿ 2 ���ع� 3 ����� 4 �����
	private int buildPoint;
	private String teamId;
	private boolean needRestart = false;
	private boolean needGetAward;
	private boolean needGuoDu;	//�Ƿ�����������
	private int duelStartTime;
	private boolean friendly;
	
	public boolean isFriendly() {
		return friendly;
	}
	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}
	public int getDuelStartTime() {
		return duelStartTime;
	}
	public void setDuelStartTime(int duelStartTime) {
		this.duelStartTime = duelStartTime;
	}
	private String weapon = null;	//ж����
	private boolean duelDropWeapon = false;
	
	public boolean isDuelDropWeapon() {
		return duelDropWeapon;
	}
	public void setDuelDropWeapon(boolean duelDropWeapon) {
		this.duelDropWeapon = duelDropWeapon;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	public boolean isNeedGuoDu() {
		return needGuoDu;
	}
	public void setNeedGuoDu(boolean needGuoDu) {
		this.needGuoDu = needGuoDu;
	}
	public boolean isNeedGetAward() {
		return needGetAward;
	}
	public void setNeedGetAward(boolean needGetAward) {
		this.needGetAward = needGetAward;
	}
	private String beatTeam;
	
	public String getBeatTeam() {
		return beatTeam;
	}
	public void setBeatTeam(String beatTeam) {
		this.beatTeam = beatTeam;
	}
	public boolean isNeedRestart() {
		return needRestart;
	}
	public void setNeedRestart(boolean needRestart) {
		this.needRestart = needRestart;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	private Map<String, String> unionTeam = new HashMap<String,String>();
	public Map<String, String> getUnionTeam() {
		return unionTeam;
	}
	public int getBuildPoint() {
		return buildPoint;
	}
	public void setBuildPoint(int buildPoint) {
		this.buildPoint = buildPoint;
	}
	public int getTianJiDoor() {
		return tianJiDoor;
	}
	public void setTianJiDoor(int tianJiDoor) {
		this.tianJiDoor = tianJiDoor;
	}
	private Map<String, Integer> attribMap = new HashMap<String, Integer>();
	
	public Map<String, Integer> getAttribMap() {
		return attribMap;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public int getMaxMP() {
		return maxMP;
	}
	public void setMaxMP(int maxMP) {
		this.maxMP = maxMP;
	}
	private int maxMP;
	
	public int getMingZhong() {
		return mingZhong;
	}
	public void setMingZhong(int mingZhong) {
		this.mingZhong = mingZhong;
	}
	public int getDuoShan() {
		return duoShan;
	}
	public void setDuoShan(int duoShan) {
		this.duoShan = duoShan;
	}
	public int getBaoJi() {
		return baoJi;
	}
	public void setBaoJi(int baoJi) {
		this.baoJi = baoJi;
	}
	public int getPoJi() {
		return poJi;
	}
	public void setPoJi(int poJi) {
		this.poJi = poJi;
	}
	public int getBlackStartTime() {
		return blackStartTime;
	}
	public void setBlackStartTime(int blackStartTime) {
		this.blackStartTime = blackStartTime;
	}
	public int getBlackEndTime() {
		return blackEndTime;
	}
	public void setBlackEndTime(int blackEndTime) {
		this.blackEndTime = blackEndTime;
	}
	public int getBuildDoor() {
		return buildDoor;
	}
	public void setBuildDoor(int buildDoor) {
		this.buildDoor = buildDoor;
	}
	private String painType; //1 ���� 2 �޽�
	
	public String getPainType() {
		return painType;
	}
	public void setPainType(String painType) {
		this.painType = painType;
	}
	public String getGloryBuy() {
		return gloryBuy;
	}
	public void setGloryBuy(String gloryBuy) {
		this.gloryBuy = gloryBuy;
	}
	public String getNeedWar() {
		return needWar;
	}
	public void setNeedWar(String needWar) {
		this.needWar = needWar;
	}
	public int getQualitySave() {
		return qualitySave;
	}
	public void setQualitySave(int qualitySave) {
		this.qualitySave = qualitySave;
	}
	private String roleName;
	private int qualitySave = 4;
	
	public int getNeedCatchSlavy() {
		return needCatchSlavy;
	}
	public void setNeedCatchSlavy(int needCatchSlavy) {
		this.needCatchSlavy = needCatchSlavy;
	}
	private int slavyMin = 2;
	private int needCatchSlavy = 0;
	
	public int getSlavyMin() {
		return slavyMin;
	}
	public void setSlavyMin(int slavyMin) {
		this.slavyMin = slavyMin;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private String wealDate;	//�ϴ�ռ��ʱ��
	private int warriorChoice = 1;	//1 ѵ�� 2 ���� 0����
	
	public int getWarriorChoice() {
		return warriorChoice;
	}
	public void setWarriorChoice(int warriorChoice) {
		this.warriorChoice = warriorChoice;
	}
	private String level;		//�ȼ�
	private String point;		//��������
	private int savePoint;		//������������
	private int beginTime=0;		//������ʼʱ��
	private boolean canMove;	//�Ƿ���ƶ�״̬
	private String status;		//��ɫ״̬
	private int miniMoney = 10000;		//��ͽ��
	private int miniJingYan;	//��С����
	private int endTime=8;		//��������ʱ��
	
	private String url = "s4.verycd.9wee.com";			//���ڷ�����	
	private int workType;		//�ճ���������
	
	private boolean needXunLian;	//�Ƿ���Ҫ����
	private boolean needJingJi;		//�Ƿ���Ҫ����
	
	private String cookie;			
	private int challengeTimes;		//��Ҫ�Զ���������
	private int duelNo;				//����������
	private boolean fastChallenge = true;	//�Ƿ���پ���
	private String killMonstorOnce;	//ÿ�λ�ɱ�������
	private int dueSleepInteval = 60;	//�����ȴ�ʱ��
	
	public int getDueSleepInteval() {
		return dueSleepInteval;
	}
	public void setDueSleepInteval(int dueSleepInteval) {
		this.dueSleepInteval = dueSleepInteval;
	}
	private boolean shouldKillMonstor ;
	
	public boolean isShouldKillMonstor() {
		return shouldKillMonstor;
	}
	public void setShouldKillMonstor(boolean shouldKillMonstor) {
		this.shouldKillMonstor = shouldKillMonstor;
	}
	public int getMiniMoney() {
		return miniMoney;
	}
	public void setMiniMoney(int miniMoney) {
		this.miniMoney = miniMoney;
	}
	public int getMiniJingYan() {
		return miniJingYan;
	}
	public void setMiniJingYan(int miniJingYan) {
		this.miniJingYan = miniJingYan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isCanMove() {
		return canMove;
	}
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	public int getSavePoint() {
		return savePoint;
	}
	public void setSavePoint(int savePoint) {
		this.savePoint = savePoint;
	}
	
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
		return "http://"+url+"/";
	}
	public String getHost(){
		return url;
	}
	public void setUrl(String url) {
		this.url =url;
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
	public boolean login(boolean startWork)throws Exception{
		PageService.login(this);
		
		while(getCookie() == null || getCookie().trim().length() == 0){
			logger.info(getUserName()+"��¼ʧ�ܣ�5���Ӻ�����");
			Thread.sleep(5*1000);
			PageService.login(this);
		}
		Portal.setUserInfo(this);
		
		logger.info(getRoleName()+"��½�ɹ�");
		if(startWork){
			startWork();
		}
		return true;
		
	}
	private List<BaseThread> work = new ArrayList<BaseThread>();
	public List<BaseThread> getWork() {
		return work;
	}
	private void startWork(){
		Iterator<BaseThread> it = work.iterator();
		while(it.hasNext()){
			BaseThread t = it.next();
			t.setNeedStop(true);
		}
		work = new ArrayList<BaseThread>();
		work.add(new WarriorThread(this));	//����
		work.add(new DuelThread(this));			//����
		work.add(new AutoTaskThread(this));		//����
		work.add(new AutoRewardThread(this));		//������
		work.add(new DailyWealsThread(this));		//ÿ�ո���
//		work.add(new MonstorThread(this));		//Ұѵ
		work.add(new MonitorThread(this));		//ͼƬ��
		work.add(new WuGuanThread(this));			//���
		work.add(new CatchSlavyThread(this));		//�Զ�ץū
		work.add(new TianJiThread(this));			//�Զ����������
		work.add(new DailyAward(this));			//�Զ���ȡȫ����
	}
}
