package com.huateng.blue.work;

import com.eis.base.BaseForm;

public class SignRecordForm extends BaseForm {
	
	private String date_f;
	private String userId_f;
	
    private String recordTime;

    
    private String isModified;

    
    private String modifyUser;

    
    public String getRecordTime() {
        return recordTime;
    }
    private String recordDate;

    
    private String recordType;

    
    private String userId;

    
    public String getRecordDate() {
        return recordDate;
    }

    
    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate == null ? null : recordDate.trim();
    }

    
    public String getRecordType() {
        return recordType;
    }

    
    public void setRecordType(String recordType) {
        this.recordType = recordType == null ? null : recordType.trim();
    }

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
    
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime == null ? null : recordTime.trim();
    }

    
    public String getIsModified() {
        return isModified;
    }

    
    public void setIsModified(String isModified) {
        this.isModified = isModified == null ? null : isModified.trim();
    }

    
    public String getModifyUser() {
        return modifyUser;
    }

    
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }


	public String getDate_f() {
		return date_f;
	}


	public void setDate_f(String date_f) {
		this.date_f = date_f;
	}


	public String getUserId_f() {
		return userId_f;
	}


	public void setUserId_f(String userId_f) {
		this.userId_f = userId_f;
	}
}
