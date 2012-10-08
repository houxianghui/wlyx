package com.blue.daily;

import com.blue.common.User;
import com.blue.tools.Tools;

public class MianChiLingPaiThread extends Thread {
	private User user;
	public MianChiLingPaiThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(Tools.needGetLingPai()){
					if(user.isCanMove()){
						user.setCanMove(false);
						MianChiLingPai.moveToLianZongXiYing(user);
						user.setCanMove(true);
					}
				}else{
					sleep(4*60*60*1000);
				}
			}catch(Exception e){}
		}
	}
}
