package com.blue.beauty;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
/**
 * 1 小家碧玉
2 各国佳丽
3 绝代风华
4 软玉温香
12 雍容华贵
13 秋水伊人
1 米脂花容
7 侠女柔情
10 艳冶柔媚
9 金枝玉叶
8 【民女】琴姬
23 【民女】小焉
28 【民女】春丽
42 【民女】蝶舞
49 【民女】静茹
6 【宫女】碧玉
4 软玉温香

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
		beautyMap.put(GONG_XIAN, "贡献");
		beautyMap.put(TI_GUAN, "踢馆");
		beautyMap.put(HU_GUAN, "护馆");
		beautyMap.put(JING_YAN, "经验");
		beautyMap.put(RONG_YU, "荣誉");
		beautyMap.put(JING_JI, "竞技");
		beautyMap.put(RI_CHANG, "日常任务");
		beautyMap.put(NU_LI, "奴隶");
		beautyMap.put(RED_TI_GUAN, "踢馆红图");
		beautyMap.put(RED_HU_GUAN, "护馆红图");
		beautyMap.put(GEN_GU_10, "根骨10");
		beautyMap.put(GEN_GU_6, "根骨6");
	}
	
	public static boolean activeBeauty(User user,String id){
		String url = user.getUrl()+ MEI_NV+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		if(Tools.success(page)){
			logger.info(user.getRoleName()+"激活图片"+beautyMap.get(id));
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
