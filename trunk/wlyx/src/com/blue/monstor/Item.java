package com.blue.monstor;

public class Item {
	private String id;
	private String name;
	private String quality;
	private String checked;
	private int sellPrice;
	public Item(String id,String name,String quality,String checked,int sellPrice) {
		this.id = id;
		this.name = name;
		this.quality = quality;
		this.checked = checked;
		this.sellPrice = sellPrice;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
}
