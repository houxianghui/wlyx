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
		secMap.put("青石路", "45");
		secMap.put("平邑镇", "18");
		secMap.put("乱葬坟堆", "47");
		secMap.put("古战场", "135");
		secMap.put("枪墓", "138");
		secMap.put("祭拜之门 ", "143");
		secMap.put("铁索", "145");
		secMap.put("沧源遗迹", "136");
		secMap.put("屯留军营", "141");
		secMap.put("锥溶洞", "146");
		secMap.put("古巫源地", "137");
		secMap.put("浮台", "148");
		secMap.put("云梦山", "160");
		secMap.put("五岩山", "157");
		secMap.put("悬棺", "151");
		secMap.put("天刹洞", "150");
		secMap.put("卫国城墙", "162");
		secMap.put("舍身崖", "163");
		secMap.put("九宫阁", "152");
		secMap.put("地底牢狱", "153");
		secMap.put("淇园", "161");	
		secMap.put("断天坡", "159");	
		secMap.put("孤山道", "156");
	}
	private static void initThird(){
		thirdMap.put("洛河道", "180");
		thirdMap.put("铁狱牢房", "226");
		thirdMap.put("右将军府", "224");
		thirdMap.put("红枫林", "182");
		thirdMap.put("万葬场", "228");
		thirdMap.put("寂静原野", "421");
		thirdMap.put("惊恐之地", "422");
		thirdMap.put("万丈天沟", "423");
		thirdMap.put("试枪石", "433");
		thirdMap.put("万年峭壁", "457");
//		thirdMap.put("聚龙盘石", "");
		thirdMap.put("军营禁地", "445");
		thirdMap.put("军营大寨", "446");
		thirdMap.put("万年峭壁", "457");
		thirdMap.put("上古铁松", "458");
//		thirdMap.put("招神旗阵", "");
		thirdMap.put("断玄洞", "459");
		thirdMap.put("上古铁松", "458");
		thirdMap.put("落枪林", "434");
		thirdMap.put("浮台机关", "470");
		thirdMap.put("浮云秘室", "471");
		thirdMap.put("枪魂秘室", "435");
		thirdMap.put("日月坪", "501");
		thirdMap.put("铜尸棺", "482");
		thirdMap.put("孙膑洞", "513");
		thirdMap.put("四象迷阵", "502");
		thirdMap.put("万劫碑", "479");
		thirdMap.put("同福窑", "521");
		thirdMap.put("摘心台", "525");
		thirdMap.put("生死阁", "487");
		thirdMap.put("鬼谷故居", "490");
		thirdMap.put("玄铁牢笼", "491");
		thirdMap.put("山野贼窝", "497");
		thirdMap.put("黄沙岭", "498");
		thirdMap.put("凤凰巢", "499");
		thirdMap.put("毛遂洞", "514");
		thirdMap.put("水帘洞", "515");
		thirdMap.put("鸡冠山", "518");
		thirdMap.put("淇水关", "519");
		thirdMap.put("卫国密室", "522");
		thirdMap.put("黄龙碑", "523");
		thirdMap.put("纣王亩", "526");
		thirdMap.put("封神台", "527");
		thirdMap.put("太乙池", "509");
		thirdMap.put("纣王墓", "526");
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
		//http://s4.verycd.9wee.com/modules/scene_walk.php?action=world_move&scene_id=9000&rand=1294314557369&timeStamp=1294314549966&callback_func_name=callbackFnWorldTransport
		bigMap.put("9000", "渑池战场");
	}
}
