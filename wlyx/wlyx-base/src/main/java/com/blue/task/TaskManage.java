package com.blue.task;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

/**
 * 任务托管
 * @author blue
 *
 */
public class TaskManage {
	private static Logger logger = Logger.getLogger(TaskManage.class);
	/**
	 * http://s4.verycd.9wee.com/modules/role_mission.php?act=task_manage&function=day&op=show&timeStamp=1371624178776&callback_func_name=ajaxCallback&callback_obj_name=dlg_duel
	 * @param user
	 * @return
	 */
	private static boolean isManaged(User user){
		String url = user.getUrl()+"modules/role_mission.php?act=task_manage&function=day&op=show"+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		if(page.indexOf("请选择任务类型")!=-1){
			return false;
		}
		return true;
	}
	/**
	 * http://s4.verycd.9wee.com/modules/role_mission.php?act=task_manage&function=day&op=manage&type=1&timeStamp=1371624751871&callback_func_name=ajaxCallback
	 * @param user
	 */
	public static void autoFinish(User user){
		if(user.isNeedTaskManage()){
			if(isManaged(user)){
				getReward(user);
				return;
			}else{
				String url = user.getUrl()+"modules/role_mission.php?act=task_manage&function=day&op=manage&type="+user.getTaskManageType()+Tools.getTimeStamp(true);
				String page = PageService.getPageWithCookie(url, user);
				if(page.indexOf("托管中")!=-1){
					logger.info("任务托管成功");
				}
			}
		}
	}

	/**
	 * http://s4.verycd.9wee.com/modules/role_mission.php?act=task_manage&function=day&op=getreward&type=0&timeStamp=1371628082604&callback_func_name=ajaxCallback
	 * @param user
	 */
	private static void getReward(User user) {
		for(int i = 0;i<3;i++){
			String url = user.getUrl()+"modules/role_mission.php?act=task_manage&function=day&op=getreward&type="+i+Tools.getTimeStamp(true);
			PageService.getPageWithCookie(url, user);
		}
	}
}
