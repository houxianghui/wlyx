package com.blue.monstor;

import com.blue.common.User;

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
 * @author Administrator
 *
 */

public class Monstor {
	private String outMap="http://s4.verycd.9wee.com/modules/map.php?timeStamp=1279088392335&callback_func_name=ajaxCallback&callback_obj_name=dlg_map";
//	private String checkItem
	private String name;
	private String url;
	
	public String getMonstor(User user){
		return null;
	}
	
}
