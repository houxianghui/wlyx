package com.blue.common;

import java.util.HashMap;

public class Map {
	private HashMap<String, String>  bigMap = new HashMap<String, String>();
	private java.util.Map<String, String> secMap = new HashMap<String, String>();
	private java.util.Map<String, String> thirdMap = new HashMap<String, String>();
	
	public Map() {
		initBigMap();
		initSecMap();
		initThird();
	}
	public String getId(int level,String position){
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
	private void initSecMap(){
		secMap.put("��¹��", "");
		secMap.put("�Թ���Ӫ", "");
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
		secMap.put("����ɽ", "");
		secMap.put("����", "");
		secMap.put("��ɲ��", "");
		secMap.put("������ǽ", "");
		secMap.put("������", "");
		secMap.put("�Ź���", "");
		secMap.put("�ص�����", "");
		secMap.put("������", "");
	}
	private void initThird(){
		thirdMap.put("��ӵ�", "");
		thirdMap.put("�����η�", "");
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
		thirdMap.put("��������", "");
		thirdMap.put("��ٱ�", "");
		thirdMap.put("ͬ��Ҥ", "");
		thirdMap.put("ժ��̨", "");
		thirdMap.put("������", "");
		thirdMap.put("��ȹʾ�", "");
		thirdMap.put("��������", "");
		thirdMap.put("ë�춴", "");
		thirdMap.put("ˮ����", "");
	}
	private void initBigMap() {
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
		
		bigMap.put("����", "");
		bigMap.put("�ٴ�", "");
		bigMap.put("��֣", "");
		bigMap.put("����", "");
		bigMap.put("����", "");
		bigMap.put("����", "");
		
		bigMap.put("����ٵ�", "");
		bigMap.put("����ٵ�", "");
		bigMap.put("����ٵ�", "64");
		bigMap.put("�����ٵ�", "68");
		bigMap.put("��κ�ٵ�", "");
		bigMap.put("κ�Թٵ�", "");
	}
}
