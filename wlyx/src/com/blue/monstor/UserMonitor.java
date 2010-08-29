package com.blue.monstor;

import java.util.Iterator;
import java.util.List;

import com.blue.common.User;

public class UserMonitor extends Thread {
	private List<User> l;
	public UserMonitor(List<User> l) {
		this.l = l;
		start();
	}
	@Override
	public void run() {
		while(true){
			try{
				Iterator<User> it = l.iterator();
				while(it.hasNext()){
					User u = it.next();
					List<Thread> work = u.getWork();
					Iterator<Thread> thread = work.iterator();
					while(thread.hasNext()){
						Thread t = thread.next();
						if(!t.isAlive()){
							t.getClass().newInstance().start();							
						}
					}
				}
				sleep(5*1000*60);
			}catch(Exception e){
			}
		}
	}
	
}
