package com.blue.common;

public class BaseThread extends Thread {
	protected boolean needStop;

	public boolean isNeedStop() {
		return needStop;
	}

	public void setNeedStop(boolean needStop) {
		this.needStop = needStop;
	}
	
}
