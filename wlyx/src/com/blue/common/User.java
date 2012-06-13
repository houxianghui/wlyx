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
import com.blue.monitor.YiShouMonitorThread;
import com.blue.slavy.CatchSlavyThread;
import com.blue.task.AutoRewardThread;
import com.blue.task.AutoTaskThread;
import com.blue.team.WuGuanThread;
import com.blue.tianjitang.TianJiThread;
import com.blue.tools.PageService;
import com.blue.warrior.WarriorThread;

public class User {
	private Logger logger  = Logger.getLogger(this.getClass());
	
	
	public String getFightPersion() {
		return fightPersion;
	}
	public void setFightPersion(String fightPersion) {
		this.fightPersion = fightPersion;
	}	
	//武馆设置
	private boolean friendly;						//友好踢馆
	private double teamProtectedPercent=0.8;		//护馆比例
	private boolean needTiGuan;						//是否踢馆
	private boolean needHuGuan;
	private boolean openRedBeat = true;				//踢馆红图
	private boolean openRedProtect = true;			//护馆红图
	private String beatTeam;						//踢馆名称
	private int buildPoint;							//建筑点数
	private String teamId;							//武馆编号
	private int minThickness = 800000;				//要进入踢馆的最小厚度
	private String protectTeam ;
	private Map<String, String> unionTeam = new HashMap<String,String>();
	/*---------------------------------------------------------------*/
	//挂野设置
	private String killMonstorOnce;		//每次击杀怪物个数
	private String level;				//等级
	private String point;				//精力点数
	private int savePoint;				//保留精力点数
	private int beginTime=0;			//修炼开始时间
	private boolean canMove;			//是否可移动状态
	private String status;				//角色状态
	private int endTime=8;				//修炼结束时间
	private int qualitySave = 4;		//保留装备品质
	private boolean needBeauty = true;	//是否需要开经验图
	/*---------------------------------------------------------------*/
	
	//竞技场设置
	private String fightPersion;				//指定对手
	private int duelStartTime;					//竞技开始时间
	private String duelType="1";				//1 逐级 2 冲级
	private boolean needBeatTail = true;		//是否刷尾巴
	private boolean duelDropWeapon = false;		//是否卸武竞技
	private int dueSleepInteval = 60;			//竞技等待时间
	private int challengeTimes;					//需要自动竞技次数
	private int duelNo;							//竞技场排名
	private boolean fastChallenge = true;		//是否快速竞技
	private boolean needJingJi;					//是否需要竞技
	/*---------------------------------------------------------------*/
	
	//日常任务设置
	private int dialog = 4;						//对话任务
	private boolean autoTianJi = true;			//是否自动完成天机堂任务
	private int miniMoney = 10000;				//最低金额
	private int miniJingYan;					//最小经验
	private int workType;						//日常任务类型
	private String buySeed;						//购买种子
	private boolean autoHarvest=true;			//是否自动收获种子
	private String plantDay;					//种植星期
	/*---------------------------------------------------------------*/
	
	//大厅设置
	private boolean needXunLian;		//是否需要大厅
	private String needWar;				//是否自动挂辎重
	private int warriorChoice = 1;		//1 训练 2 授艺 0不挂
	private String longTrainStartTime;	//超长挂机开始时间
	private String trainOnce;			//超长挂机小时数
	private String soulTrainOnce;		//武魂训练时间
	/*---------------------------------------------------------------*/
	
	//个人属性
	private String userName;			//登陆用户名
	private String password;
	private String stockPwd;			//仓库密码
	private int mingZhong;				//命中
	private int duoShan;				//闪避
	private int baoJi;					//暴击
	private int poJi;					//破击
	private int maxHP;					//最大HP
	private int maxMP;
	private String roleName;			//游戏角色名
	private int currHP;					//当前HP
	private int currMP;					//当前MP
	private Map<String, Integer> attribMap = new HashMap<String, Integer>();
	/*---------------------------------------------------------------*/
	//奴隶设置
	private int needCatchSlavy = 0;
	private int slavyMin = 2;					//最小奴隶数
	private int blackStartTime;					//小黑屋
	private int blackEndTime;
	private int buildDoor;						//2 玄武，3 朱雀 4 白虎 5 青龙
	private int tianJiDoor=1;					// 1 四海库房 2 万守关 3 玲珑阁 4 藏书馆
	private String painType; 					//1 宣传 2 修建
	private String menKeStep = "1,5,3,4,2";				//门客折磨顺序
	/*---------------------------------------------------------------*/
		
	//其它日常设置
	
	private String gloryBuy;					//荣誉换
	private boolean needGetAward;				//领取奖励
	private boolean needGuoDu;					//是否报名国都演武
	private boolean mianChiDropWeapon;			//渑池卸武
	private String painShiKeType="1";			//折磨食客
	private String dailyWeal = "1";				//全民福利类型
	private boolean needDuiHuan = false;		//是否需要兑换物品
	private boolean needReadSave = false;		//是否自动读取幻境塔进度
	private Map<String, String> gloryMap = new HashMap<String, String>();	//荣誉换。。
	private boolean needGetLiBao = true;		//是否自动领取礼包
	
	//----------------------------------------------------------------
	
	private boolean needRestart = false;	//重启用户
	private String xiYingName;
	private String xiYingLevel;
	private String wealDate;	//上次占卜时间
	private String url = "s4.verycd.9wee.com";			//所在服务器	
	private String cookie;			

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
	private String weapon = null;	//卸武编号
	
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
			logger.info(getUserName()+"登录失败，3秒钟后重试");
			Thread.sleep(3*1000);
			PageService.login(this);
		}
		Portal.setUserInfo(this);
		
		logger.info(getRoleName()+"登陆成功");
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
		work.add(new WarriorThread(this));	//大厅
		work.add(new DuelThread(this));			//竞技
		work.add(new AutoTaskThread(this));		//任务
		work.add(new AutoRewardThread(this));		//任务奖励
		work.add(new DailyWealsThread(this));		//每日福利
//		work.add(new MonstorThread(this));		//野训
		work.add(new MonitorThread(this));		//图片等
		work.add(new WuGuanThread(this));			//武馆
		work.add(new CatchSlavyThread(this));		//自动抓奴
		work.add(new TianJiThread(this));			//自动天机堂任务
		work.add(new DailyAward(this));			//自动领取全民福利
		work.add(new YiShouMonitorThread(this));	//免费异兽蛋
		if(isMianChiDropWeapon()){
			work.add(new DropWeaponThread(this));	//渑池卸武
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
}
