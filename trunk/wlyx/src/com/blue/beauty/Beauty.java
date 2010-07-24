package com.blue.beauty;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class Beauty {
	public static final String OPEN_MEI_NV = "http://s4.verycd.9wee.com/modules/beauty.php?callback_func_name=ajaxCallback&callback_obj_name=dlg_duel";
	public static final String JIN_YAN = "";
	//modules/beauty.php?act=set_effect&set_id=8&rand=1279976599937&timeStamp=1279974548171&callback_func_name=getBeautySetEffectCallback
	public static final String RI_CHANG = "modules/beauty.php?act=set_effect&set_id=8&callback_func_name=ajaxCallback&callback_obj_name=dlg_set_effect_erreo";

	public static boolean dailyTask(User user){
		String url = user.getUrl()+ RI_CHANG+Tools.getRandAndTime();
		String page = PageService.getPageWithCookie(url, user);
		System.out.println(user.getUserName()+" 激活日常任务图片");
		return Tools.success(page);
	}
}
