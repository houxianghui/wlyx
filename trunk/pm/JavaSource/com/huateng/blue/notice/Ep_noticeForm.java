package com.huateng.blue.notice;  
import java.util.*; 
 
import com.eis.base.BaseForm; 
import com.eis.cache.*; 
import org.apache.struts.upload.FormFile;

/** 
 * 说明：1的数据对象 
 */ 
public class Ep_noticeForm extends BaseForm { 
 
	/** 
	 * 构造函数 
	 */ 
	public Ep_noticeForm() { 
		super(); 
	} 
 
	/** 
	 * notice_no 
	 */ 
	private String oper_id;
	private String oper_date;
	private String filename;
	private String notice_top;
	private String notice_no; 
	private String notice_no_f;
	private FormFile file;
	private String oper_id_f;
	private String start_date_f;
	private String end_date_f;

	/** 
	 * notice_comment 
	 */ 
	private String notice_comment; 
	 
 
	/** 
	 *  获得notice_comment 
	 */ 
	public String getNotice_comment() {  
		return notice_comment; 
	} 
 
 
	/** 
	 *  设置notice_comment 
	 */ 
	public void  setNotice_comment(String str) {  
		notice_comment = str; 
	} 
 
    /**
     * Create on 2006-12-13 16:17:19 Ranger
     * 
     * 
     * @return
     */
    public String getNotice_no_f() {
        return notice_no_f;
    }

    /**
     * Create on 2006-12-13 16:17:19 Ranger
     * 
     * 
     * @param string
     */
    public void setNotice_no_f(String string) {
        notice_no_f = string;
    }

 

    /**
     * Create on 2006-12-13 19:09:50 Ranger
     * 
     * 
     * @return
     */
    public String getNotice_no() {
        return notice_no;
    }

    /**
     * Create on 2006-12-13 19:09:50 Ranger
     * 
     * 
     * @param string
     */
    public void setNotice_no(String string) {
        notice_no = string;
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

	/**
	 * @return
	 */
	public FormFile getFile() {
		return file;
	}

	/**
	 * @param file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}

	/**
	 * @return
	 */
	public String getNotice_top() {
		return notice_top;
	}

	/**
	 * @param string
	 */
	public void setNotice_top(String string) {
		notice_top = string;
	}

	/**
	 * @return
	 */
	public String getEnd_date_f() {
		return end_date_f;
	}

	/**
	 * @return
	 */
	public String getOper_id_f() {
		return oper_id_f;
	}

	/**
	 * @return
	 */
	public String getStart_date_f() {
		return start_date_f;
	}

	/**
	 * @param string
	 */
	public void setEnd_date_f(String string) {
		end_date_f = string;
	}

	/**
	 * @param string
	 */
	public void setOper_id_f(String string) {
		oper_id_f = string;
	}

	/**
	 * @param string
	 */
	public void setStart_date_f(String string) {
		start_date_f = string;
	}

} 

