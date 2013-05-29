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
import com.blue.enums.Profession;
import com.blue.fyzb.ServerDuelConfig;
import com.blue.fyzb.ServerDuelHallThread;
import com.blue.monitor.PackageMonitorThread;
import com.blue.monitor.YiShouMonitorThread;
import com.blue.serverarean.ServerAreanConfig;
import com.blue.serverarean.ServerAreanThread;
import com.blue.slavy.CatchSlavyThread;
import com.blue.task.AutoRewardThread;
import com.blue.task.AutoTaskThread;
import com.blue.team.WuGuanThread;
import com.blue.tianjitang.TianJiThread;
import com.blue.tools.Item;
import com.blue.tools.PageService;
import com.blue.user.RoleSkillForProfession;
import com.blue.warrior.WarriorThread;

public class User {
	private Logger logger  = Logger.getLogger(this.getClass());
	
	
	public String getFightPersion() {
		return fightPersion;
	}
	public void setFightPersion(String fightPersion) {
		this.fightPersion = fightPersion;
	}	
	//�ƽ�����
	private ServerAreanConfig serverAreanConfig;	//�ƽ���������
	//�������
	private boolean needTeamWork=true;					//�Ƿ���Ҫ�߻��ݹ���
	private boolean friendly;						//�Ѻ��߹�
	private double teamProtectedPercent=0.8;		//���ݱ���
	private boolean needTiGuan;						//�Ƿ��߹�
	private boolean needHuGuan;
	private boolean openRedBeat = true;				//�߹ݺ�ͼ
	private boolean openRedProtect = true;			//���ݺ�ͼ
	private String beatTeam;						//�߹�����
	private int buildPoint;							//��������
	private String teamId;							//��ݱ��
	private int minThickness = 800000;				//Ҫ�����߹ݵ���С���
	private boolean needAutoRent;					//�Ƿ��Զ�����
	private String protectTeam ;
	private Map<String, String> unionTeam = new HashMap<String,String>();
	/*---------------------------------------------------------------*/
	//��Ұ����
	private String killMonstorOnce = "24";		//ÿ�λ�ɱ�������
	private String level;				//�ȼ�
	private String point;				//��������
	private int savePoint;				//������������
	private int beginTime=0;			//������ʼʱ��
	private boolean canMove;			//�Ƿ���ƶ�״̬
	private String status;				//��ɫ״̬
	private int endTime=8;				//��������ʱ��
	private int qualitySave = 4;		//����װ��Ʒ��
	private boolean needBeauty = true;	//�Ƿ���Ҫ������ͼ
	/*---------------------------------------------------------------*/
	
	//����������
	private int duelTime;						//��ɾ�������
	private String fightPersion;				//ָ������
	private int duelStartTime;					//������ʼʱ��
	private String duelType="1";				//1 �� 2 �弶
	private boolean needBeatTail = true;		//�Ƿ�ˢβ��
	private boolean duelDropWeapon = false;		//�Ƿ�ж�侺��
	private int dueSleepInteval = 60;			//�����ȴ�ʱ��
	private int challengeTimes;					//��Ҫ�Զ���������
	private int duelNo;							//����������
	private boolean fastChallenge = true;		//�Ƿ���پ���
	private boolean needJingJi;					//�Ƿ���Ҫ����
	private String duelInfo;					//��������Ϣ
	/*---------------------------------------------------------------*/
	
	//�ճ���������
	private int dialog = 4;						//�Ի�����
	private boolean autoTianJi = true;			//�Ƿ��Զ�������������
	private int miniMoney = 10000;				//��ͽ��
	private int miniJingYan;					//��С����
	private int workType;						//�ճ���������
	private String buySeed;						//��������
	private boolean autoHarvest=true;			//�Ƿ��Զ��ջ�����
	private String plantDay="8";					//��ֲ����
	private boolean needFarm = true;			//�Ƿ��Զ���ˮ
	private String taskInfo;					//�ճ�����������
	
	/*---------------------------------------------------------------*/
	
	//��������
	private boolean needXunLian;		//�Ƿ���Ҫ����
	private String needWar;				//�Ƿ��Զ������
	private int warriorChoice = 1;		//1 ѵ�� 2 ���� 0����
	private String longTrainStartTime;	//�����һ���ʼʱ��
	private String trainOnce;			//�����һ�Сʱ��
	private String soulTrainOnce;		//���ѵ��ʱ��
	/*---------------------------------------------------------------*/
	
	//��������
	private String userName;			//��½�û���
	private String password;
	private String stockPwd;			//�ֿ�����
	private int mingZhong;				//����
	private int duoShan;				//����
	private int baoJi;					//����
	private int poJi;					//�ƻ�
	private int maxHP;					//���HP
	private int maxMP;
	private String roleName;			//��Ϸ��ɫ��
	private int currHP;					//��ǰHP
	private int currMP;					//��ǰMP
	private Map<String, Integer> attribMap = new HashMap<String, Integer>();
	private Profession profession;		//��ɫ��ϵ
	private String position;			//��ɫλ��
	private String capacity;			//���
	private String gotWeals;			//ÿ�ո���
	private List<Item> packageItems;	//������Ʒ
	private List<Item> stockItems;		//�ֿ���Ʒ
	private List<Item> teamStockItems;	//�ĺ��ⷿ
	/*---------------------------------------------------------------*/
	//ū������
	private int needCatchSlavy = 0;
	private int slavyMin = 2;					//��Сū����
	private int blackStartTime;					//С����
	private int blackEndTime;
	private int buildDoor;						//2 ���䣬3 ��ȸ 4 �׻� 5 ����
	private int tianJiDoor=1;					// 1 �ĺ��ⷿ 2 ���ع� 3 ����� 4 �����
	private String painType; 					//1 ���� 2 �޽�
	private String menKeStep = "1,5,3,4,2";				//�ſ���ĥ˳��
	/*---------------------------------------------------------------*/
		
	//�����ճ�����
	
	private String gloryBuy;					//������
	private boolean needGetAward;				//��ȡ����
	private boolean needGuoDu;					//�Ƿ�����������
	private boolean mianChiDropWeapon;			//�ų�ж��
	private String painShiKeType="1";			//��ĥʳ��
	private String dailyWeal = "1";				//ȫ��������
	private boolean needDuiHuan = false;		//�Ƿ���Ҫ�һ���Ʒ
	private boolean needReadSave = false;		//�Ƿ��Զ���ȡ�þ�������
	private Map<String, String> gloryMap = new HashMap<String, String>();	//����������
	private boolean needGetLiBao = true;		//�Ƿ��Զ���ȡ���
	private boolean needRoomAct = false;	//�Ƿ��Զ���ȡ�ͷ�����
	
	//----------------------------------------------------------------
	//�������
	private boolean needWHTrain;				//�Ƿ���Ҫѵ��
	private boolean needWHProm;					//�Ƿ���Ҫ����Ʒ��
	private int promType = 1;					//�������ʹ�÷�ʽ 1=ͭ�� 2=1��ȯ 3=3��ȯ 4=8��ȯ 5=12�ƽ�
	private int promUse = 1;					//����ʹ�� 0=�ƽ� 1=��ȯ
	private boolean needWHFoster;				//�Ƿ���Ҫ����
	private int longWHTrainStart;				//8Сʱ��ʼʱ��
	private int soulCount=1;					//ѵ��������
	//----------------------------------------------------------------
	
	private boolean needRestart = false;	//�����û�
	private String xiYingName;
	private String xiYingLevel;
	private String wealDate;	//�ϴ�ռ��ʱ��
	private String url = "s4.verycd.9wee.com";			//���ڷ�����	
	private String cookie;			
	private String serverAreanCookie;				//�ƽ�����cookie
	//-----------------------------------------------------------------
	//�������
	private boolean needServerDuelHall;		//�Ƿ���Ҫ�������
	private boolean useDefault = true;		//�Ƿ�ʹ��ְҵ����
	private Map<Profession,ServerDuelConfig> serverDuelConfigMap = new HashMap<Profession, ServerDuelConfig>();		//��ս��ϵ����
	//-----------------------------------------------------------------
	public boolean isNeedDuiHuan() {
		return needDuiHuan;
	}
	public void setNeedDuiHuan(boolean needDuiHuan) {
		this.needDuiHuan = needDuiHuan;
	}
	public String getXiYingName() {
		return xiYingName;
	}
	public void setXiYingName(String xiYingName) {
		this.xiYingName = xiYingName;
	}
	public String getXiYingLevel() {
		return xiYingLevel;
	}
	public void setXiYingLevel(String xiYingLevel) {
		this.xiYingLevel = xiYingLevel;
	}
	public Map<String, String> getGloryMap() {
		return gloryMap;
	}
	public void setGloryMap(Map<String, String> gloryMap) {
		this.gloryMap = gloryMap;
	}
	public boolean isOpenRedBeat() {
		return openRedBeat;
	}
	public void setOpenRedBeat(boolean openRedBeat) {
		this.openRedBeat = openRedBeat;
	}
	public boolean isOpenRedProtect() {
		return openRedProtect;
	}
	public void setOpenRedProtect(boolean openRedProtect) {
		this.openRedProtect = openRedProtect;
	}
	public String getDailyWeal() {
		return dailyWeal;
	}
	public void setDailyWeal(String dailyWeal) {
		this.dailyWeal = dailyWeal;
	}
	public boolean isAutoTianJi() {
		return autoTianJi;
	}
	public void setAutoTianJi(boolean autoTianJi) {
		this.autoTianJi = autoTianJi;
	}
	public boolean isNeedBeatTail() {
		return needBeatTail;
	}
	public void setNeedBeatTail(boolean needBeatTail) {
		this.needBeatTail = needBeatTail;
	}
	public String getDuelType() {
		return duelType;
	}
	public void setDuelType(String duelType) {
		this.duelType = duelType;
	}
	public int getDialog() {
		return dialog;
	}
	public void setDialog(int dialog) {
		this.dialog = dialog;
	}
	public String getPainShiKeType() {
		return painShiKeType;
	}
	public void setPainShiKeType(String painShiKeType) {
		this.painShiKeType = painShiKeType;
	}
	public boolean isMianChiDropWeapon() {
		return mianChiDropWeapon;
	}
	public void setMianChiDropWeapon(boolean mianChiDropWeapon) {
		this.mianChiDropWeapon = mianChiDropWeapon;
	}
	public boolean isNeedTiGuan() {
		return needTiGuan;
	}
	public void setNeedTiGuan(boolean needTiGuan) {
		this.needTiGuan = needTiGuan;
	}
	public boolean isNeedHuGuan() {
		return needHuGuan;
	}
	public void setNeedHuGuan(boolean needHuGuan) {
		this.needHuGuan = needHuGuan;
	}
	public double getTeamProtectedPercent() {
		return teamProtectedPercent;
	}
	public void setTeamProtectedPercent(double teamProtectedPercent) {
		this.teamProtectedPercent = teamProtectedPercent;
	}
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
	
	public int getNeedCatchSlavy() {
		return needCatchSlavy;
	}
	public void setNeedCatchSlavy(int needCatchSlavy) {
		this.needCatchSlavy = needCatchSlavy;
	}
	
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
	
	
	public int getWarriorChoice() {
		return warriorChoice;
	}
	public void setWarriorChoice(int warriorChoice) {
		this.warriorChoice = warriorChoice;
	}
	
	public int getCurrMP() {
		return currMP;
	}
	public void setCurrMP(int currMP) {
		this.currMP = currMP;
	}
	public int getCurrHP() {
		return currHP;
	}
	public void setCurrHP(int currHP) {
		this.currHP = currHP;
	}
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
		return cookie==null?"":cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public boolean isNeedReadSave() {
		return needReadSave;
	}
	public void setNeedReadSave(boolean needReadSave) {
		this.needReadSave = needReadSave;
	}
	public boolean isNeedBeauty() {
		return needBeauty;
	}
	public void setNeedBeauty(boolean needBeauty) {
		this.needBeauty = needBeauty;
	}
	public boolean isNeedGetLiBao() {
		return needGetLiBao;
	}
	public void setNeedGetLiBao(boolean needGetLiBao) {
		this.needGetLiBao = needGetLiBao;
	}
	public String getMenKeStep() {
		return menKeStep;
	}
	public void setMenKeStep(String menKeStep) {
		this.menKeStep = menKeStep;
	}
	public boolean login(boolean startWork)throws Exception{
		PageService.login(this);
		
		while(getCookie() == null || getCookie().trim().length() == 0){
			logger.info(getUserName()+"��¼ʧ�ܣ�3���Ӻ�����");
			Thread.sleep(3*1000);
			PageService.login(this);
		}
		Portal.setUserInfo(this);
		Portal.setUserAttribute(this);
		//���ÿ����������
		if(useDefault){
			RoleSkillForProfession.setServerDuelConfig(this);
		}
		
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
		work.add(new YiShouMonitorThread(this));	//������޵�
		work.add(new ServerDuelHallThread(this));//�������
		work.add(new PackageMonitorThread(this));	//��Ʒ�б�
		work.add(new ServerAreanThread(this));	//��ս������
		if(isMianChiDropWeapon()){
			work.add(new DropWeaponThread(this));	//�ų�ж��
		}
	}
	public int getMinThickness() {
		return minThickness;
	}
	public void setMinThickness(int minThickness) {
		this.minThickness = minThickness;
	}
	public String getStockPwd() {
		return stockPwd;
	}
	public void setStockPwd(String stockPwd) {
		this.stockPwd = stockPwd;
	}
	public String getBuySeed() {
		return buySeed;
	}
	public void setBuySeed(String buySeed) {
		this.buySeed = buySeed;
	}
	public boolean isAutoHarvest() {
		return autoHarvest;
	}
	public void setAutoHarvest(boolean autoHarvest) {
		this.autoHarvest = autoHarvest;
	}
	public String getPlantDay() {
		return plantDay;
	}
	public void setPlantDay(String plantDay) {
		this.plantDay = plantDay;
	}
	public String getProtectTeam() {
		return protectTeam;
	}
	public void setProtectTeam(String protectTeam) {
		this.protectTeam = protectTeam;
	}
	public String getLongTrainStartTime() {
		return longTrainStartTime;
	}
	public void setLongTrainStartTime(String longTrainStartTime) {
		this.longTrainStartTime = longTrainStartTime;
	}
	public String getTrainOnce() {
		return trainOnce;
	}
	public void setTrainOnce(String trainOnce) {
		this.trainOnce = trainOnce;
	}
	public String getSoulTrainOnce() {
		return soulTrainOnce;
	}
	public void setSoulTrainOnce(String soulTrainOnce) {
		this.soulTrainOnce = soulTrainOnce;
	}
	public boolean isNeedWHTrain() {
		return needWHTrain;
	}
	public void setNeedWHTrain(boolean needWHTrain) {
		this.needWHTrain = needWHTrain;
	}
	public boolean isNeedWHProm() {
		return needWHProm;
	}
	public void setNeedWHProm(boolean needWHProm) {
		this.needWHProm = needWHProm;
	}
	public boolean isNeedWHFoster() {
		return needWHFoster;
	}
	public void setNeedWHFoster(boolean needWHFoster) {
		this.needWHFoster = needWHFoster;
	}
	public boolean isNeedServerDuelHall() {
		return needServerDuelHall;
	}
	public void setNeedServerDuelHall(boolean needServerDuelHall) {
		this.needServerDuelHall = needServerDuelHall;
	}
	public int getLongWHTrainStart() {
		return longWHTrainStart;
	}
	public void setLongWHTrainStart(int longWHTrainStart) {
		this.longWHTrainStart = longWHTrainStart;
	}
	public boolean isNeedAutoRent() {
		return needAutoRent;
	}
	public void setNeedAutoRent(boolean needAutoRent) {
		this.needAutoRent = needAutoRent;
	}
	public boolean isNeedTeamWork() {
		return needTeamWork;
	}
	public void setNeedTeamWork(boolean needTeamWork) {
		this.needTeamWork = needTeamWork;
	}
	public int getSoulCount() {
		return soulCount;
	}
	public void setSoulCount(int soulCount) {
		this.soulCount = soulCount;
	}
	public int getPromType() {
		return promType;
	}
	public void setPromType(int promType) {
		this.promType = promType;
	}
	public int getPromUse() {
		return promUse;
	}
	public void setPromUse(int promUse) {
		this.promUse = promUse;
	}
	public Profession getProfession() {
		return profession;
	}
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	public Map<Profession, ServerDuelConfig> getServerDuelConfigMap() {
		return serverDuelConfigMap;
	}
	public void setServerDuelConfigMap(Map<Profession, ServerDuelConfig> serverDuelConfigMap) {
		this.serverDuelConfigMap = serverDuelConfigMap;
	}
	public boolean isUseDefault() {
		return useDefault;
	}
	public void setUseDefault(boolean useDefault) {
		this.useDefault = useDefault;
	}
	public boolean isNeedFarm() {
		return needFarm;
	}
	public void setNeedFarm(boolean needFarm) {
		this.needFarm = needFarm;
	}
	public int getDuelTime() {
		return duelTime;
	}
	public void setDuelTime(int duelTime) {
		this.duelTime = duelTime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getGotWeals() {
		return gotWeals;
	}
	public void setGotWeals(String gotWeals) {
		this.gotWeals = gotWeals;
	}
	public String getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	public String getDuelInfo() {
		return duelInfo;
	}
	public void setDuelInfo(String duelInfo) {
		this.duelInfo = duelInfo;
	}
	public List<Item> getPackageItems() {
		return packageItems;
	}
	public void setPackageItems(List<Item> packageItems) {
		this.packageItems = packageItems;
	}
	public List<Item> getStockItems() {
		return stockItems;
	}
	public void setStockItems(List<Item> stockItems) {
		this.stockItems = stockItems;
	}
	public List<Item> getTeamStockItems() {
		return teamStockItems;
	}
	public void setTeamStockItems(List<Item> teamStockItems) {
		this.teamStockItems = teamStockItems;
	}
	public ServerAreanConfig getServerAreanConfig() {
		return serverAreanConfig;
	}
	public void setServerAreanConfig(ServerAreanConfig serverAreanConfig) {
		this.serverAreanConfig = serverAreanConfig;
	}
	public String getServerAreanCookie() {
		return serverAreanCookie;
	}
	public void setServerAreanCookie(String serverAreanCookie) {
		this.serverAreanCookie = serverAreanCookie;
	}
	public boolean isNeedRoomAct() {
		return needRoomAct;
	}
	public void setNeedRoomAct(boolean needRoomAct) {
		this.needRoomAct = needRoomAct;
	}
}
