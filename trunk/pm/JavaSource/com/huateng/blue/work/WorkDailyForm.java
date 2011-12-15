package com.huateng.blue.work;

import com.eis.base.BaseForm;

public class WorkDailyForm extends BaseForm {
	
    private Integer id;

    
    private Integer distributeId;

    
    private String workId;

    
    private String workDate;

    
    private String userId;

    
    private String content;

    
    private String inputDate;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getDistributeId() {
        return distributeId;
    }

    
    public void setDistributeId(Integer distributeId) {
        this.distributeId = distributeId;
    }

    
    public String getWorkId() {
        return workId;
    }

    
    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    
    public String getWorkDate() {
        return workDate;
    }

    
    public void setWorkDate(String workDate) {
        this.workDate = workDate == null ? null : workDate.trim();
    }

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    
    public String getContent() {
        return content;
    }

    
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    
    public String getInputDate() {
        return inputDate;
    }

    
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? null : inputDate.trim();
    }
}
