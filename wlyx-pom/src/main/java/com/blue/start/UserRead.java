package com.blue.start;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.blue.common.User;
import com.blue.enums.Profession;
import com.blue.fyzb.ServerDuelConfig;
import com.blue.tools.FileUtil;
import com.blue.tools.Tools;

public class UserRead {

	public Document parse(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(FileUtil.readFile(fileName));
		return document;
	}

	public List<User> readUser() throws DocumentException {
		List<User> l = new ArrayList<User>();
		Document doc = parse("user.xml");
		Element root = doc.getRootElement();
		Element sys = root.element("system");
		Properties p = System.getProperties();
		if (!Tools.isEmpty(sys.elementText("proxyHost")) && !Tools.isEmpty(sys.elementText("proxyPort"))) {
			p.setProperty("http.proxyHost", sys.elementText("proxyHost"));
			p.setProperty("http.proxyPort", sys.elementText("proxyPort"));
		}

		for (Iterator<Element> it = root.element("users").elementIterator("user"); it.hasNext();) {
			Element e = it.next();
			setUserInfo(e, l);
		}
		return l;
	}

	private void setUserInfo(Element e, List<User> l) {
		String userName = e.attributeValue("username");
		if (Tools.isEmpty(userName)) {
			return;
		}
		String pwd = e.attributeValue("password");
		String stockPwd = e.attributeValue("stockPwd");
		if (stockPwd == null) {
			stockPwd = "";
		}
		String[] users = userName.split(",");
		String[] pwds = pwd.split(",");
		String[] stockPwds = stockPwd.split(",");
		for (int i = 0; i < users.length; i++) {
			User user = new User();
			user.setUserName(users[i]);
			user.setPassword(pwds[i]);
			if (i < stockPwds.length) {
				user.setStockPwd(stockPwds[i]);
			}
			user.setUrl(e.attributeValue("host"));
			setMonstor(e, user);
			setWarrior(e, user);
			setDuel(e, user);
			setTask(e, user);
			setSlavy(e, user);
			setDaily(e, user);
			setTeam(e, user);
			setSoul(e, user);
			setServerDuel(e, user);
			l.add(user);
		}
	}

	private void setServerDuel(Element e, User user) {
		Element duel = e.element("serverDuel");
		if (duel == null) {
			return;
		}
		String needDuel = duel.elementText("needDuel");
		if (!Tools.isEmpty(needDuel)) {
			user.setNeedServerDuelHall("1".equals(needDuel));
		}
		if (!user.isNeedServerDuelHall()) {
			return;
		}
		Element c = duel.element("config");
		List<Element> l = c.elements();
		for (Element el : l) {
			ServerDuelConfig config = new ServerDuelConfig();
			config.setChallengProfession(Profession.valueOf(el.attributeValue("name")));
			config.setActiveSkill(el.elementText("activeSkill"));
			config.setAssistSkillA(el.elementText("assistSkillA"));
			config.setAssistSkillB(el.elementText("assistSkillB"));
			user.getServerDuelConfigMap().put(config.getChallengProfession(), config);
		}
	}

	private void setSoul(Element e, User user) {
		Element soul = e.element("soul");
		if (soul == null) {
			return;
		}
		String needTrain = soul.elementText("needTrain");
		String needProm = soul.elementText("needProm");
		String start = soul.elementText("longStart");
		String count = soul.elementText("trainCount");
		String promType = soul.elementText("promType");
		String promUse = soul.elementText("promUse");
		if (!Tools.isEmpty(needTrain)) {
			user.setNeedWHTrain(needTrain.equals("1"));
		}
		if (!Tools.isEmpty(needProm)) {
			user.setNeedWHProm("1".equals(needProm));
		}
		if (!Tools.isEmpty(start)) {
			user.setLongWHTrainStart(Integer.parseInt(start));
		}
		if (!Tools.isEmpty(count)) {
			user.setSoulCount(Integer.parseInt(count));
		}
		if (!Tools.isEmpty(promType)) {
			user.setPromType(Integer.parseInt(promType));
		}
		if (!Tools.isEmpty(promUse)) {
			user.setPromUse(Integer.parseInt(promUse));
		}
	}

	private void setMonstor(Element e, User user) {
		Element monstor = e.element("monstor");
		String startTime = monstor.elementText("startTime");
		String endTime = monstor.elementText("endTime");
		String savePoint = monstor.elementText("savePoint");
		String picture = monstor.elementText("beauty");
		String killOnce = monstor.elementText("killOnce");
		if (!Tools.isEmpty(startTime)) {
			user.setBeginTime(Integer.parseInt(startTime.trim()));
		}
		if (!Tools.isEmpty(endTime)) {
			user.setEndTime(Integer.parseInt(endTime.trim()));
		}
		if (!Tools.isEmpty(savePoint)) {
			user.setSavePoint(Integer.parseInt(savePoint.trim()));
		}
		if (!Tools.isEmpty(picture)) {
			user.setNeedBeauty("1".equals(picture.trim()));
		}
		if (!Tools.isEmpty(killOnce)) {
			user.setKillMonstorOnce(killOnce);
		}
	}

	private void setWarrior(Element e, User user) {
		Element w = e.element("warrior");
		String warriorType = w.elementText("warriorType");
		String needZiZhong = w.elementText("needZiZhong");
		String startTime = w.elementText("startTime");
		String hourOnce = w.elementText("hourOnce");
		if (!Tools.isEmpty(warriorType)) {
			user.setWarriorChoice(Integer.parseInt(warriorType.trim()));
		}
		if (!Tools.isEmpty(needZiZhong)) {
			user.setNeedWar(needZiZhong.trim());
		}
		if (!Tools.isEmpty(startTime)) {
			user.setLongTrainStartTime(startTime.trim());
		}
		if (!Tools.isEmpty(hourOnce)) {
			user.setTrainOnce(hourOnce.trim());
		}
	}

	private void setDuel(Element element, User user) {
		Element e = element.element("duel");
		String start = e.elementText("duelStart");
		String drop = e.elementText("dropWeapon");
		String due = e.elementText("duelDue");
		String duelType = e.elementText("duelType");
		String needTail = e.elementText("needBeatTail");
		String givenUser = e.elementText("fightPerson");
		if (!Tools.isEmpty(duelType)) {
			user.setDuelType(duelType.trim());
		}
		if (!Tools.isEmpty(needTail)) {
			user.setNeedBeatTail("1".equals(needTail.trim()));
		}
		if (!Tools.isEmpty(start)) {
			user.setDuelStartTime(Integer.parseInt(start.trim()));
		}
		if (!Tools.isEmpty(drop)) {
			user.setDuelDropWeapon(drop.trim().equals("1"));
		}
		if (!Tools.isEmpty(due)) {
			user.setDueSleepInteval(Integer.parseInt(due.trim()));
		}
		if (!Tools.isEmpty(givenUser)) {
			user.setFightPersion(givenUser);
		}
	}

	private void setTask(Element element, User user) {
		Element e = element.element("task");
		String min = e.elementText("minMoney");
		String door = e.elementText("tianJiDoor");
		String dialog = e.elementText("dialog");
		String autoTianJi = e.elementText("autoTianJi");
		String minJingYan = e.elementText("minJingYan");
		String buySeed = e.elementText("buySeed");
		String autoHarvest = e.elementText("autoHarvest");
		String plantDay = e.elementText("plantDay");

		if (!Tools.isEmpty(minJingYan)) {
			user.setMiniJingYan(Integer.parseInt(minJingYan));
		}
		if (!Tools.isEmpty(autoTianJi)) {
			user.setAutoTianJi("1".equals(autoTianJi.trim()));
		}
		if (!Tools.isEmpty(min)) {
			user.setMiniMoney(Integer.parseInt(min.trim()));
		}
		if (!Tools.isEmpty(door)) {
			user.setTianJiDoor(Integer.parseInt(door.trim()));
		}
		if (!Tools.isEmpty(dialog)) {
			user.setDialog(Integer.parseInt(dialog.trim()));
		}
		if (!Tools.isEmpty(buySeed)) {
			user.setBuySeed(buySeed);
		}
		if (!Tools.isEmpty(autoHarvest)) {
			user.setAutoHarvest("1".equals(autoHarvest.trim()));
		}
		if (!Tools.isEmpty(plantDay)) {
			user.setPlantDay(plantDay);
		}
	}

	private void setSlavy(Element element, User user) {
		Element e = element.element("slavy");
		String catchSlavy = e.elementText("catchSlavy");
		String painType = e.elementText("painType");
		String build = e.elementText("build");
		String start = e.elementText("blackStart");
		String end = e.elementText("blackEnd");
		String minSlavy = e.elementText("minSlavy");
		String menKeStep = e.elementText("menKeStep");
		if (!Tools.isEmpty(catchSlavy)) {
			user.setNeedCatchSlavy(Integer.parseInt(catchSlavy.trim()));
		}
		if (!Tools.isEmpty(painType)) {
			user.setPainType(painType.trim());
		}
		if (!Tools.isEmpty(build)) {
			user.setBuildDoor(Integer.parseInt(build.trim()));
		}
		if (!Tools.isEmpty(start)) {
			user.setBlackStartTime(Integer.parseInt(start.trim()));
		}
		if (!Tools.isEmpty(end)) {
			user.setBlackEndTime(Integer.parseInt(end.trim()));
		}
		if (!Tools.isEmpty(minSlavy)) {
			user.setSlavyMin(Integer.parseInt(minSlavy.trim()));
		}
		if (!Tools.isEmpty(menKeStep)) {
			user.setMenKeStep(menKeStep.trim());
		}
	}

	private void setDaily(Element element, User user) {
		Element e = element.element("daily");
		String buy = e.elementText("buyGlory");
		String dailyWeal = e.elementText("dailyWeal");
		String guodu = e.elementText("guoDuYanWu");
		String dropWeapon = e.elementText("mianChi");
		String needChangeItem = e.elementText("needChangeItem");
		String needReadSave = e.elementText("needReadSave");
		String needGetLiBao = e.elementText("needGetLiBao");
		String needAutoRent = e.elementText("needAutoRent");
		if (!Tools.isEmpty(needGetLiBao)) {
			user.setNeedGetLiBao("1".equals(needGetLiBao.trim()));
		}
		if (!Tools.isEmpty(buy)) {
			user.setGloryBuy(buy.trim());
		}
		if (!Tools.isEmpty(dailyWeal)) {
			// user.setNeedGetAward("1".equals(dailyWeal.trim()));
			user.setDailyWeal(dailyWeal);
		}
		if (!Tools.isEmpty(guodu)) {
			user.setNeedGuoDu("1".equals(guodu.trim()));
		}
		if (!Tools.isEmpty(dropWeapon)) {
			user.setMianChiDropWeapon("1".equals(dropWeapon.trim()));
		}
		if (!Tools.isEmpty(needChangeItem)) {
			user.setNeedDuiHuan("1".equals(needChangeItem));
		}
		if (!Tools.isEmpty(needReadSave)) {
			user.setNeedReadSave("1".equals(needReadSave));
		}
		if (!Tools.isEmpty(needAutoRent)) {
			user.setNeedAutoRent("1".equals(needAutoRent));
		}
	}

	private void setTeam(Element element, User user) {
		Element e = element.element("team");
		String open = e.attributeValue("open");
		String name = e.elementText("teamName");
		String friendly = e.elementText("friendly");
		String percent = e.elementText("protectMyTeam");
		String needTiGuan = e.elementText("needDesdroy");
		String needHuGuan = e.elementText("needProtect");
		String openRedBeat = e.elementText("openRedBeat");
		String openRedProtect = e.elementText("openRedProtect");
		String minThickness = e.elementText("minThickness");
		String forceProtect = e.elementText("forceProtect");
		if (!Tools.isEmpty(name)) {
			user.setBeatTeam(name);
		}
		if (!Tools.isEmpty(friendly)) {
			user.setFriendly("1".equals(friendly.trim()));
		}
		if (!Tools.isEmpty(percent)) {
			user.setTeamProtectedPercent(Double.parseDouble(percent));
		}
		if (!Tools.isEmpty(needHuGuan)) {
			user.setNeedHuGuan(needHuGuan.trim().equals("1"));
		}
		if (!Tools.isEmpty(needTiGuan)) {
			user.setNeedTiGuan("1".equals(needTiGuan.trim()));
		}
		if (!Tools.isEmpty(openRedBeat)) {
			user.setOpenRedBeat("1".equals(openRedBeat.trim()));
		}
		if (!Tools.isEmpty(openRedProtect)) {
			user.setOpenRedProtect("1".equals(openRedProtect.trim()));
		}
		if (!Tools.isEmpty(minThickness)) {
			user.setMinThickness(Integer.parseInt(minThickness));
		}
		if (!Tools.isEmpty(forceProtect)) {
			user.setProtectTeam(forceProtect);
		}
		if (!Tools.isEmpty(open)) {
			user.setNeedTeamWork("1".equals(open));
		}
	}
}
