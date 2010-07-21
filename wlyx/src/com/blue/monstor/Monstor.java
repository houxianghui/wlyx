package com.blue.monstor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.Move;
import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

/**
 * check item 
 * http://s4.verycd.9wee.com/modules/role_item.php?act=check_item&item_type=temp&id=2962891&timeStamp=1279249487002&callback_func_name=itemClass.dragItemCallback
 * http://s4.verycd.9wee.com/modules/role_item.php?act=check_item&item_type=temp&id=2961514&timeStamp=1279249563631&callback_func_name=itemClass.dragItemCallback
 * 
 * 入包
 * http://s4.verycd.9wee.com/modules/role_item.php?act=drag_item&id=2962891&from=temp&to=pack&timeStamp=1279249615760&callback_func_name=itemClass.dragItemCallback
 * 全部出售
 * http://s4.verycd.9wee.com/modules/role_item.php?act=sell_all_temp&timeStamp=1279249631858
 * 
 * 自动完成免费：http://s4.verycd.9wee.com/modules/auto_combats.php?act=show&mid=29&timeStamp=1279182628328&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster
 * 挂起训练：http://s4.verycd.9wee.com/modules/auto_combats.php?act=complete&isfree=1&timeStamp=1279182562076&callback_func_name=callbackFnCancelAutoCombat
 * 怪物信息
 * http://s4.verycd.9wee.com/modules/auto_combats.php?act=show&mid=63&timeStamp=1279615833845&callback_func_name=ajaxCallback&callback_obj_name=dlg_view_monster
 * 
 * @author Administrator
 *
 */


public class Monstor {
	private static final String KILL_URL="modules/auto_combats.php?act=start";
	private Pattern p = Pattern.compile("monster_id\":\"(\\d+)\",\"level_range\":\"Lv.(\\d+)-(\\d+)");
	
	public boolean killMonstor(User user,Portal p)throws Exception{
		p.setUserInfo(user);
		return moveToMonstor(user);	
	}
	private boolean moveToMonstor(User user)throws Exception{
		String level = user.getLevel();
		String[] monstor = LevelVSMonstor.getMonstorInfo(level);
		int times = 3;
		String page = Move.worldMove(user, monstor[0]);
		while(!Tools.success(page) && times > 0){
			System.out.println("move to "+monstor[0]+" failed! retry!");
			times--;
		}
		if(times == 0){
			return false;
		}
		times = 3;
		page = Move.secMove(user, monstor[1]);
		while(!Tools.success(page) && times > 0){
			System.out.println("move to "+monstor[0]+" failed! retry!");
			times--;
		}
		if(times == 0){
			return false;
		}
		times = 3;
		page = Move.thirdMove(user, monstor[2]);
		while(!Tools.success(page) && times > 0){
			System.out.println("move to "+monstor[0]+" failed! retry!");
			times--;
		}
		if(times == 0){
			return false;
		}
		Matcher m = p.matcher(page);
		String mid = null;
		while(m.find()){
			if(m.group(2).equals(level)){
				mid = m.group(1);
				break;
			}
		}
		return killIt(mid, user);
	}
	private boolean killIt(String monstor,User user)throws Exception{
		String url = user.getUrl()+KILL_URL+Tools.getTimeStamp(true);
		String page = PageService.postPage(url, getData(monstor,user), user);
		return Tools.success(page);
	}
	private String getData(String monstor,User user){
		return "mid="+monstor+"&select_frequency="+user.getKillMonstorOnce()+"&callback_func_name=callbackFnStartAutoCombat";
	}
}
