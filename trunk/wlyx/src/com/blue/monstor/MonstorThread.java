package com.blue.monstor;

import com.blue.common.User;

public class MonstorThread extends Thread {
	private Monstor monstor;
	private User user;
	public MonstorThread(User user,Monstor monstor) {
		this.user = user;
		this.monstor = monstor;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				if(monstor.killMonstor(user)){
					sleep(3*60*1000);
				}else{
					sleep(10*1000);
				}
			}catch(Exception e){}
		}
	}
}
