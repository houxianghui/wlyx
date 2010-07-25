package com.blue.beauty;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Beauty {
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
	
	public static boolean activeBeauty(User user,String id){
		String url = user.getUrl()+ MEI_NV+id+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		System.out.println(user.getUserName()+" ¼¤»îÍ¼Æ¬"+id);
		return Tools.success(page);
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
}
