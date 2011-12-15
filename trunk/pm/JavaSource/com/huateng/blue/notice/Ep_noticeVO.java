package com.huateng.blue.notice; 

import com.eis.base.BaseVO; 
 
/** 
 * 说明：1的数据对象 
 */ 
public class Ep_noticeVO extends BaseVO { 
 
	/** 
	 * 构造函数 
	 */ 
	public Ep_noticeVO() { 
		super(); 
	} 
 
	/** 
	 * notice_no 
	 */ 
	private String oper_id;
	private String oper_date;
	private String filename;
	private int notice_top;
	private int notice_no; 
	private int notice_no_f;
 
	/** 
	 * notice_comment 
	 */ 
	private String notice_comment; 
 
	/** 
	 *  获得notice_no 
	 */ 
	public int getNotice_no() {  
		return notice_no; 
	} 
 
	/** 
	 *  获得notice_comment 
	 */ 
	public String getNotice_comment() {  
		return notice_comment; 
	} 
 
	/** 
	 *  设置notice_no 
	 */ 
	public void  setNotice_no(int val) {  
		notice_no = val; 
	} 
 
	/** 
	 *  设置notice_comment 
	 */ 
	public void  setNotice_comment(String val) {  
		notice_comment = val; 
	} 
 
    /**
     * Create on 2006-12-13 16:16:57 Ranger
     * 
     * 
     * @return
     */
    public int getNotice_no_f() {
        return notice_no_f;
    }

    /**
     * Create on 2006-12-13 16:16:57 Ranger
     * 
     * 
     * @param i
     */
    public void setNotice_no_f(int i) {
        notice_no_f = i;
    }

	/**
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return
	 */
	public int getNotice_top() {
		return notice_top;
	}

	/**
	 * @return
	 */
	public String getOper_date() {
		return oper_date;
	}

	/**
	 * @return
	 */
	public String getOper_id() {
		return oper_id;
	}

	/**
	 * @param string
	 */
	public void setFilename(String string) {
		filename = string;
	}

	/**
	 * @param i
	 */
	public void setNotice_top(int i) {
		notice_top = i;
	}

	/**
	 * @param string
	 */
	public void setOper_date(String string) {
		oper_date = string;
	}

	/**
	 * @param string
	 */
	public void setOper_id(String string) {
		oper_id = string;
	}

} 

