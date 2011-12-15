package com.eis.util;

public class DiffInfo {
	private String id;
	private String from;
	private String to;
	
	public DiffInfo(String id,Object from,Object to) {
		this.id = id;
		this.from = from==null?"":from.toString();
		this.to = to==null?"":to.toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "将 "+id+" 从 "+from +" 修改为 "+to;
	}
}
