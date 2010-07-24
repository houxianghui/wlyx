package com.blue.common;

import java.util.HashMap;

public class Map {
	private static HashMap<String, String>  bigMap = new HashMap<String, String>();
	private static java.util.Map<String, String> secMap = new HashMap<String, String>();
	private static java.util.Map<String, String> thirdMap = new HashMap<String, String>();
	static{
		initBigMap();
		initSecMap();
		initThird();
	}
	private Map() {
		
	}
	public static String getId(int level,String position){
		if(level == 1){
			return bigMap.get(position);
		}
		if(level == 2){
			return secMap.get(position);
			
		}
		if(level == 3){
			return thirdMap.get(position);
		}
		return null;
	}
	private static void initSecMap(){
		secMap.put("巨鹿镇", "17");
		secMap.put("赵国官营", "46");
		secMap.put("青石路", "");
		secMap.put("平邑镇", "");
		secMap.put("乱葬坟堆", "");
		secMap.put("古战场", "");
		secMap.put("枪墓", "");
		secMap.put("祭拜之门 ", "");
		secMap.put("铁索", "");
		secMap.put("沧源遗迹", "");
		secMap.put("屯留军营", "");
		secMap.put("锥溶洞", "");
		secMap.put("古巫源地", "");
		secMap.put("浮台", "");
		secMap.put("云梦山", "");
		secMap.put("五岩山", "157");
		secMap.put("悬棺", "");
		secMap.put("天刹洞", "");
		secMap.put("卫国城墙", "");
		secMap.put("舍身崖", "");
		secMap.put("九宫阁", "");
		secMap.put("地底牢狱", "");
		secMap.put("舍身崖", "");
	}
	private static void initThird(){
		thirdMap.put("洛河道", "180");
		thirdMap.put("铁狱牢房", "226");
		thirdMap.put("右将军府", "");
		thirdMap.put("红枫林", "");
		thirdMap.put("万葬场", "");
		thirdMap.put("寂静原野", "");
		thirdMap.put("惊恐之地", "");
		thirdMap.put("万丈天沟", "");
		thirdMap.put("试枪石", "");
		thirdMap.put("焚火道", "");
		thirdMap.put("万年峭壁", "");
		thirdMap.put("聚龙盘石", "");
		thirdMap.put("军营禁地", "");
		thirdMap.put("军营大寨", "");
		thirdMap.put("寒冰室", "");
		thirdMap.put("万年峭壁", "");
		thirdMap.put("上古铁松", "");
		thirdMap.put("招神旗阵", "");
		thirdMap.put("断玄洞", "");
		thirdMap.put("上古铁松", "");
		thirdMap.put("寒冰室", "");
		thirdMap.put("落枪林", "");
		thirdMap.put("浮台机关", "");
		thirdMap.put("浮云秘室", "");
		thirdMap.put("枪魂秘室", "");
		thirdMap.put("日月坪", "");
		thirdMap.put("铜尸棺", "");
		thirdMap.put("孙膑洞", "");
		thirdMap.put("四象迷阵", "502");
		thirdMap.put("万劫碑", "");
		thirdMap.put("同福窑", "");
		thirdMap.put("摘心台", "");
		thirdMap.put("生死阁", "");
		thirdMap.put("鬼谷故居", "");
		thirdMap.put("玄铁牢笼", "");
		thirdMap.put("毛遂洞", "");
		thirdMap.put("水帘洞", "");
	}
	private static void initBigMap() {
		bigMap.put("蓟京", "8");
		bigMap.put("渔阳郡", "19");
		bigMap.put("济南郡", "24");
		bigMap.put("江陵郡", "29");
		bigMap.put("上党郡", "34");
		bigMap.put("河东郡", "39");
		bigMap.put("安平郡", "14");
		
		bigMap.put("屯留", "134");
		bigMap.put("中牟", "154");
		bigMap.put("鬼谷", "144");
		bigMap.put("渑池", "164");
		
		bigMap.put("临淄", "9");
		bigMap.put("寿春", "10");
		bigMap.put("新郑", "11");
		bigMap.put("咸阳", "13");
		bigMap.put("邯郸", "7");
		bigMap.put("大梁", "12");
		
		bigMap.put("赵燕官道", "44");
		bigMap.put("燕齐官道", "51");
		bigMap.put("齐楚官道", "58");
		bigMap.put("楚韩官道", "65");
		bigMap.put("韩魏官道", "72");
		bigMap.put("魏赵官道", "79");
	}
}
