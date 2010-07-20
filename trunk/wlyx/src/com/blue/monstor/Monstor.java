package com.blue.monstor;

import com.blue.common.Move;
import com.blue.common.Portal;
import com.blue.common.User;
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
//	private String checkItem
	
	public boolean killMonstor(User user,Portal p){
		p.setUserInfo(user);
		moveToMonstor(user);	
		return false;
	}
	private boolean moveToMonstor(User user){
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
		killIt(monstor[3]);
		return true;
	}
	private boolean killIt(String monstor){
		return false;
	}
	
}
