package com.blue.monstor;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.blue.common.BaseThread;
import com.blue.common.User;

public class UserMonitor extends Thread {
	private Logger logger = Logger.getLogger(this.getClass());
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
					List<BaseThread> work = u.getWork();
					Iterator<BaseThread> thread = work.iterator();
					while(thread.hasNext()){
						Thread t = thread.next();
						if(!t.isAlive()){
							logger.info(u.getRoleName()+"重新启动线程"+t.getClass().getName());
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
