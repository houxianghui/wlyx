package com.blue.beauty;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
/**
 * 1 С�ұ���
2 ��������
3 �����绪
4 ��������
12 Ӻ�ݻ���
13 ��ˮ����
1 ��֬����
7 ��Ů����
10 ��ұ����
9 ��֦��Ҷ
8 ����Ů���ټ�
23 ����Ů��С��
28 ����Ů������
42 ����Ů������
49 ����Ů������
6 ����Ů������
4 ��������

 * @author blue
 *
 */

public class Beauty {
	private static Logger logger = Logger.getLogger(Beauty.class);
	public static final String OPEN_MEI_NV = "http://s4.verycd.9wee.com/modules/beauty.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_duel";
	public static final String MEI_NV = "modules/beauty.php?act=set_effect&callback_func_name=ajaxCallback&callback_obj_name=dlg_set_effect_erreo&set_id=";
	public static final String RI_CHANG = "8";
	public static final String JING_YAN = "4";	
	public static final String TI_GUAN = "2";	
	public static final String HU_GUAN = "3";
	public static final String RONG_YU = "6";
	public static final String GONG_XIAN = "1";
	public static final String JING_JI = "7";
	public static final String NU_LI = "10";
	public static final String RED_TI_GUAN = "14";
	public static final String RED_HU_GUAN = "15";
	public static final String GEN_GU_10 = "13";
	public static final String GEN_GU_6 = "12";
	
	private static Map<String, String> beautyMap = new HashMap<String, String>();
	static{
		beautyMap.put(GONG_XIAN, "����");
		beautyMap.put(TI_GUAN, "�߹�");
		beautyMap.put(HU_GUAN, "����");
		beautyMap.put(JING_YAN, "����");
		beautyMap.put(RONG_YU, "����");
		beautyMap.put(JING_JI, "����");
		beautyMap.put(RI_CHANG, "�ճ�����");
		beautyMap.put(NU_LI, "ū��");
		beautyMap.put(RED_TI_GUAN, "�߹ݺ�ͼ");
		beautyMap.put(RED_HU_GUAN, "���ݺ�ͼ");
		beautyMap.put(GEN_GU_10, "����10");
		beautyMap.put(GEN_GU_6, "����6");
	}
	
	public static boolean activeBeauty(User user,String id){
		String url = user.getUrl()+ MEI_NV+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"����ͼƬ"+beautyMap.get(id));
			return true;
		}
		return false;
	}
	public static boolean dailyTask(User user){
		return activeBeauty(user, RI_CHANG);
	}
	public static boolean gongXian(User user){
		return activeBeauty(user, GONG_XIAN);
	}
	public static boolean rongYu(User user){
		return activeBeauty(user, RONG_YU);
	}
	public static boolean jingYan(User user){
		return activeBeauty(user, JING_YAN);
	}
	public static boolean tiGuan(User user){
		return activeBeauty(user, TI_GUAN);
	}
	public static boolean huGuan(User user){
		return activeBeauty(user, HU_GUAN);
	}
	public static boolean jingJi(User user){
		return activeBeauty(user, JING_JI);
	}
	public static boolean nuLi(User user){
		return activeBeauty(user, NU_LI);
	}
	public static boolean redTiGuan(User user){
		return activeBeauty(user, RED_TI_GUAN);
	}
	public static boolean redHuGuan(User user){
		return activeBeauty(user, RED_HU_GUAN);
	}
	public static boolean genGuTu(User user){
		if(Tools.getNowHour() >= 21){
			return activeBeauty(user, GEN_GU_10);
		}
		if(Tools.getNowHour()>=18){
			return activeBeauty(user, GEN_GU_6);
		}
		return false;
	}
}
