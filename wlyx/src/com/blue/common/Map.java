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
		secMap.put("��¹��", "17");
		secMap.put("�Թ���Ӫ", "46");
		secMap.put("��ʯ·", "45");
		secMap.put("ƽ����", "18");
		secMap.put("����ض�", "47");
		secMap.put("��ս��", "135");
		secMap.put("ǹĹ", "138");
		secMap.put("����֮�� ", "143");
		secMap.put("����", "145");
		secMap.put("��Դ�ż�", "136");
		secMap.put("������Ӫ", "141");
		secMap.put("׶�ܶ�", "146");
		secMap.put("����Դ��", "137");
		secMap.put("��̨", "148");
		secMap.put("����ɽ", "160");
		secMap.put("����ɽ", "157");
		secMap.put("����", "151");
		secMap.put("��ɲ��", "150");
		secMap.put("������ǽ", "162");
		secMap.put("������", "163");
		secMap.put("�Ź���", "152");
		secMap.put("�ص�����", "153");
		secMap.put("�԰", "161");	
		secMap.put("������", "159");	
		secMap.put("��ɽ��", "156");
	}
	private static void initThird(){
		thirdMap.put("��ӵ�", "180");
		thirdMap.put("�����η�", "226");
		thirdMap.put("�ҽ�����", "224");
		thirdMap.put("�����", "182");
		thirdMap.put("���᳡", "228");
		thirdMap.put("�ž�ԭҰ", "421");
		thirdMap.put("����֮��", "422");
		thirdMap.put("�����칵", "423");
		thirdMap.put("��ǹʯ", "433");
		thirdMap.put("�����ͱ�", "457");
//		thirdMap.put("������ʯ", "");
		thirdMap.put("��Ӫ����", "445");
		thirdMap.put("��Ӫ��կ", "446");
		thirdMap.put("�����ͱ�", "457");
		thirdMap.put("�Ϲ�����", "458");
//		thirdMap.put("��������", "");
		thirdMap.put("������", "459");
		thirdMap.put("�Ϲ�����", "458");
		thirdMap.put("��ǹ��", "434");
		thirdMap.put("��̨����", "470");
		thirdMap.put("��������", "471");
		thirdMap.put("ǹ������", "435");
		thirdMap.put("����ƺ", "501");
		thirdMap.put("ͭʬ��", "482");
		thirdMap.put("������", "513");
		thirdMap.put("��������", "502");
		thirdMap.put("��ٱ�", "479");
		thirdMap.put("ͬ��Ҥ", "521");
		thirdMap.put("ժ��̨", "525");
		thirdMap.put("������", "487");
		thirdMap.put("��ȹʾ�", "490");
		thirdMap.put("��������", "491");
		thirdMap.put("ɽҰ����", "497");
		thirdMap.put("��ɳ��", "498");
		thirdMap.put("��˳�", "499");
		thirdMap.put("ë�춴", "514");
		thirdMap.put("ˮ����", "515");
		thirdMap.put("����ɽ", "518");
		thirdMap.put("�ˮ��", "519");
		thirdMap.put("��������", "522");
		thirdMap.put("������", "523");
		thirdMap.put("����Ķ", "526");
		thirdMap.put("����̨", "527");
		thirdMap.put("̫�ҳ�", "509");
		thirdMap.put("����Ĺ", "526");
	}
	private static void initBigMap() {
		bigMap.put("����", "8");
		bigMap.put("������", "19");
		bigMap.put("���Ͽ�", "24");
		bigMap.put("���꿤", "29");
		bigMap.put("�ϵ���", "34");
		bigMap.put("�Ӷ���", "39");
		bigMap.put("��ƽ��", "14");
		
		bigMap.put("����", "134");
		bigMap.put("��Ĳ", "154");
		bigMap.put("���", "144");
		bigMap.put("�ų�", "164");
		
		bigMap.put("����", "9");
		bigMap.put("�ٴ�", "10");
		bigMap.put("��֣", "11");
		bigMap.put("����", "13");
		bigMap.put("����", "7");
		bigMap.put("����", "12");
		
		bigMap.put("����ٵ�", "44");
		bigMap.put("����ٵ�", "51");
		bigMap.put("����ٵ�", "58");
		bigMap.put("�����ٵ�", "65");
		bigMap.put("��κ�ٵ�", "72");
		bigMap.put("κ�Թٵ�", "79");
		//http://s4.verycd.9wee.com/modules/scene_walk.php?action=world_move&scene_id=9000&rand=1294314557369&timeStamp=1294314549966&callback_func_name=callbackFnWorldTransport
		bigMap.put("9000", "�ų�ս��");
	}
}
