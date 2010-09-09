package com.start;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.blue.common.User;
import com.blue.tools.Tools;

public class UserRead {
	
	public Document parse(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(fileName);
		return document;
	}
	public List<User> readUser()throws DocumentException{
		List<User> l = new ArrayList<User>();
		Document doc = parse("user.xml");
		Element root = doc.getRootElement();
		Element sys = root.element("system");
		Properties p = System.getProperties();
		if(!Tools.isEmpty(sys.elementText("proxyHost")) && !Tools.isEmpty(sys.elementText("proxyPort"))){
			p.setProperty("http.proxyHost", sys.elementText("proxyHost"));
			p.setProperty("http.proxyPort", sys.elementText("proxyPort"));
		}
		
		for(Iterator<Element> it = root.element("users").elementIterator("user");it.hasNext();){
			Element e = it.next();
			User user = new User();
			setUserInfo(e, user);
			setMonstor(e,user);
			setWarrior(e, user);
			setDuel(e,user);
			setTask(e,user);
			setSlavy(e, user);
			setDaily(e,user);
			setTeam(e,user);
			l.add(user);
		}
		return l;
	}
	private void setUserInfo(Element e, User user) {
		String userName = e.attributeValue("username");
		String pwd = e.attributeValue("password");
		String url = e.attributeValue("host");
		if(!Tools.isEmpty(userName)){
			user.setUserName(userName.trim());
		}
		if(!Tools.isEmpty(pwd)){
			user.setPassword(pwd.trim());
		}
		if(!Tools.isEmpty(url)){
			user.setUrl(url);
		}
	}
	private void setMonstor(Element e,User user){
		Element monstor = e.element("monstor");
		String startTime = monstor.elementText("startTime");
		String endTime = monstor.elementText("endTime");
		String savePoint = monstor.elementText("savePoint");
		if(!Tools.isEmpty(startTime)){
			user.setBeginTime(Integer.parseInt(startTime.trim()));
		}
		if(!Tools.isEmpty(endTime)){
			user.setEndTime(Integer.parseInt(endTime.trim()));
		}
		if(!Tools.isEmpty(savePoint)){
			user.setSavePoint(Integer.parseInt(savePoint.trim()));
		}
	}
	private void setWarrior(Element e,User user){
		Element w = e.element("warrior");
		String warriorType = w.elementText("warriorType");
		String needZiZhong = w.elementText("needZiZhong");
		if(!Tools.isEmpty(warriorType)){
			user.setWarriorChoice(Integer.parseInt(warriorType.trim()));
		}
		if(!Tools.isEmpty(needZiZhong)){
			user.setNeedWar(needZiZhong.trim());
		}
	}
	private void setDuel(Element element,User user){
		Element e = element.element("duel");
		String start = e.elementText("duelStart");
		String drop = e.elementText("dropWeapon");
		String due = e.elementText("duelDue");
		if(!Tools.isEmpty(start)){
			user.setDuelStartTime(Integer.parseInt(start.trim()));
		}
		if(!Tools.isEmpty(drop)){
			user.setDuelDropWeapon(drop.trim().equals("1"));
		}
		if(!Tools.isEmpty(due)){
			user.setDueSleepInteval(Integer.parseInt(due.trim()));
		}
	}
	private void setTask(Element element,User user){
		Element e = element.element("task");
		String min = e.elementText("minMoney");
		String door = e.elementText("tianJiDoor");
		if(!Tools.isEmpty(min)){
			user.setMiniMoney(Integer.parseInt(min.trim()));
		}
		if(!Tools.isEmpty(door)){
			user.setTianJiDoor(Integer.parseInt(door.trim()));
		}
	}
	private void setSlavy(Element element,User user){
		Element e = element.element("slavy");
		String catchSlavy = e.elementText("catchSlavy");
		String painType = e.elementText("painType");
		String build = e.elementText("build");
		String start = e.elementText("blackStart");
		String end  = e.elementText("blackEnd");
		if(!Tools.isEmpty(catchSlavy)){
			user.setNeedCatchSlavy(Integer.parseInt(catchSlavy.trim()));
		}
		if(!Tools.isEmpty(painType)){
			user.setPainType(painType.trim());
		}
		if(!Tools.isEmpty(build)){
			user.setBuildDoor(Integer.parseInt(build.trim()));
		}
		if(!Tools.isEmpty(start)){
			user.setBlackStartTime(Integer.parseInt(start.trim()));
		}
		if(!Tools.isEmpty(end)){
			user.setBlackEndTime(Integer.parseInt(end.trim()));
		}
	}
	private void setDaily(Element element,User user){
		Element e = element.element("daily");
		String buy = e.elementText("buyGlory");
		String dailyWeal = e.elementText("dailyWeal");
		String guodu = e.elementText("guoDuYanWu");
		if(!Tools.isEmpty(buy)){
			user.setGloryBuy(buy.trim());
		}
		if(!Tools.isEmpty(dailyWeal)){
			user.setNeedGetAward("1".equals(dailyWeal.trim()));
		}
		if(!Tools.isEmpty(guodu)){
			user.setNeedGuoDu("1".equals(guodu.trim()));
		}
	}
	private void setTeam(Element element,User user){
		Element e = element.element("team");
		String name = e.elementText("teamName");
		if(!Tools.isEmpty(name)){
			user.setBeatTeam(name);
		}
	}
}
