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
		secMap.put("��ʯ·", "");
		secMap.put("ƽ����", "");
		secMap.put("����ض�", "");
		secMap.put("��ս��", "");
		secMap.put("ǹĹ", "");
		secMap.put("����֮�� ", "");
		secMap.put("����", "");
		secMap.put("��Դ�ż�", "");
		secMap.put("������Ӫ", "");
		secMap.put("׶�ܶ�", "");
		secMap.put("����Դ��", "");
		secMap.put("��̨", "");
		secMap.put("����ɽ", "");
		secMap.put("����ɽ", "157");
		secMap.put("����", "");
		secMap.put("��ɲ��", "");
		secMap.put("������ǽ", "");
		secMap.put("������", "");
		secMap.put("�Ź���", "");
		secMap.put("�ص�����", "");
		secMap.put("������", "");
	}
	private static void initThird(){
		thirdMap.put("��ӵ�", "180");
		thirdMap.put("�����η�", "226");
		thirdMap.put("�ҽ�����", "");
		thirdMap.put("�����", "");
		thirdMap.put("���᳡", "");
		thirdMap.put("�ž�ԭҰ", "");
		thirdMap.put("����֮��", "");
		thirdMap.put("�����칵", "");
		thirdMap.put("��ǹʯ", "");
		thirdMap.put("�ٻ��", "");
		thirdMap.put("�����ͱ�", "");
		thirdMap.put("������ʯ", "");
		thirdMap.put("��Ӫ����", "");
		thirdMap.put("��Ӫ��կ", "");
		thirdMap.put("������", "");
		thirdMap.put("�����ͱ�", "");
		thirdMap.put("�Ϲ�����", "");
		thirdMap.put("��������", "");
		thirdMap.put("������", "");
		thirdMap.put("�Ϲ�����", "");
		thirdMap.put("������", "");
		thirdMap.put("��ǹ��", "");
		thirdMap.put("��̨����", "");
		thirdMap.put("��������", "");
		thirdMap.put("ǹ������", "");
		thirdMap.put("����ƺ", "");
		thirdMap.put("ͭʬ��", "");
		thirdMap.put("������", "");
		thirdMap.put("��������", "502");
		thirdMap.put("��ٱ�", "");
		thirdMap.put("ͬ��Ҥ", "");
		thirdMap.put("ժ��̨", "");
		thirdMap.put("������", "");
		thirdMap.put("��ȹʾ�", "");
		thirdMap.put("��������", "");
		thirdMap.put("ë�춴", "");
		thirdMap.put("ˮ����", "");
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
	}
}
