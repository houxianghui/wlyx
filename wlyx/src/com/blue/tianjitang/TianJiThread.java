package com.blue.tianjitang;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class TianJiThread extends BaseThread {
	private User user;
	public TianJiThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			if(needStop){
				return;
			}
			try{
				TianJiTang.autoTask(user);
				TianJiTang.autoFinish(user);
				TianJiTang.build(user);
				sleep(1000*60*5);
			}catch(Exception e){
			}
		}
	}
}
