package com.blue.task;


import com.blue.common.BaseThread;
import com.blue.common.User;

public class AutoTaskThread extends BaseThread {
	private User user;
	public AutoTaskThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		
		while(true){
			try{
				if(needStop){
					return;
				}
				AutoTask.autoAcceptTask(user);
				AutoTask.autoFinishTask(user);
				TaskManage.autoFinish(user);
				sleep(5*1000*60);
			}catch(Exception e){
			}
		}
	}
}
