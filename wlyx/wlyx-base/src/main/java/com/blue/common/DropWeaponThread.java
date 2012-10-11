package com.blue.common;


public class DropWeaponThread extends BaseThread {
	private User user;
	public DropWeaponThread(User user) {
		this.user = user;
		start();
	}
	@Override
	public void run() {
		while(true){
			int waitTime = MianChiDropWeapon.getWaitSecond();
			try{
				sleep(waitTime*1000);
				DropWeapon.dropWeapon(user);
				sleep(1*60*1000);
				DropWeapon.mountWeapon(user);
			}catch(Exception e){}
		}
	}
}
