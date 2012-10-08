package com.blue.duel;

public class Challenger {
	private String id;
	private String level;
	private String duelNo;
	private String name;
	
	public Challenger(String id,String duelNo,String level,String name) {
		this.id = id;
		this.level = level;
		this.duelNo = duelNo;
		this.name = name;
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
	public String getName(){
		return name;
	}

}
