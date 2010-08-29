package com.blue.tianjitang;

import org.apache.log4j.Logger;

import com.blue.common.User;

public class TianJiThread extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
	private User user;
	public TianJiThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
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
