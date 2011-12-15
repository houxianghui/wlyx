/*
 * @# Button.java 2009-9-9 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.eis.html;


public class Button implements HtmlBase {
	public Button(){}
	public Button(String value){
		this.value = value;
	}
	private String name="";
	private String value;
	private String action;
	public String getElement() {
		return new StringBuffer("<input type=\"button\" name=\""+name+"\" value=\""+value+"\" onClick=\""+action+"\" class=\"Button\">&nbsp;" ).toString();
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * 2009-9-9 16:48:22 houxh
	 * 
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

}
