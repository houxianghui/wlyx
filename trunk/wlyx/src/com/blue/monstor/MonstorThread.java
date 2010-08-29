package com.blue.monstor;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class MonstorThread extends BaseThread {
	private User user;
	public MonstorThread(User user) {
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
				if(Monstor.killMonstor(user)){
					sleep(3*60*1000);
				}else{
					sleep(5*10*1000);
				}
			}catch(Exception e){
			}
		}
	}
}
