package com.blue.tianjitang;

import org.apache.log4j.Logger;

import com.blue.common.User;

public class TianJiThread extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
	private User user;
	private TianJiTang tianJiTang;
	public TianJiThread(User user,TianJiTang tianJiTang) {
		this.user = user;
		this.tianJiTang = tianJiTang;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				tianJiTang.autoTask(user);
				tianJiTang.autoFinish(user);
				tianJiTang.build(user);
				sleep(1000*60*5);
			}catch(Exception e){
				logger.error(user.getRoleName()+e.getMessage());
			}
		}
	}
}
