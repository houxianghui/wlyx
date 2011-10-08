package com.blue.monstor;

import com.blue.tools.Tools;

public class Item {
	public static String HORSE = "11";
	private String id;
	private String name;
	private String quality;
	private String checked;
	private int sellPrice;
	private String equipType;
	private String count;
	private String maxCount;
	public String getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public String getPositonX() {
		return positionX;
	}
	public void setPositonX(String positonX) {
		this.positionX = positonX;
	}
	public String getPositionY() {
		return positionY;
	}
	public void setPositionY(String positionY) {
		this.positionY = positionY;
	}
	private String positionX;
	private String positionY;
	public Item(String id,String name,String type,String quality,String checked,int sellPrice) {
		this.id = id;
		this.name = name;
		this.quality = quality;
		this.checked = checked;
		this.sellPrice = sellPrice;
		this.equipType = type;
	}
	public Item(String id,String name,String equipType,String x,String y,String count,String maxCount) {
		this.id = id;
		this.name = name;
		this.equipType = equipType;
		this.positionX = x;
		this.positionY = y;
		this.count = count;
		this.maxCount = maxCount;
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
	public String getCNName(){
		return Tools.hexToString(name);
	}
	@Override
	public String toString() {
		return "[id="+id+"\tname="+Tools.hexToString(name)+"\tequipType="+equipType+"\tpositionX="+positionX+"\tPositionY="+positionY+"\tcount="+count+"\tmaxcount="+maxCount+"]";
	}
}
