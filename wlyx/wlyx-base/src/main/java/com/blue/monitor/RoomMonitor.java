package com.blue.monitor;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;

public class RoomMonitor {
	//http://s4.verycd.9wee.com/modules/warrior.php?act=guestroom&op=restore&id=7&timeStamp=1329664159939&callback_func_name=warrior_common_callback
	public static final String roomFree = "modules/warrior.php?act=guestroom&op=restore&id=";
	
	public static void getRoomFree(User user){
		if(!user.isNeedRoomAct()){
			return;
		}
		
		for(int i = 6;i<8;i++){
			String url = user.getUrl()+roomFree+i+Tools.getTimeStamp(true);
			PageService.getPageWithCookie(url, user);
		}
	}
}
