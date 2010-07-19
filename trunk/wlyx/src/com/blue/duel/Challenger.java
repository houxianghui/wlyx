package com.blue.duel;

public class Challenger {
	private String id;
	private String level;
	private String duelNo;
	public Challenger(String id,String duelNo,String level) {
		this.id = id;
		this.level = level;
		this.duelNo = duelNo;
	}
	public String getId() {
		return id;
	}
	public String getLevel() {
		return level;
	}
	public String getDuelNo(){
		return duelNo;
	}

}
