/*
 * @# WorkContent.java 2008-11-28 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.ArrayList;
import java.util.List;

import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.config.SysConfig;
import com.eis.util.CheckUtil;


public class WorkContent {
	private boolean isBusy;
	private boolean heavyWork;
	
	private String projectId;
	private String status;
	private List<Work> workList = new ArrayList<Work>();	
	
	public void addProject(String projectId,String status){
		isBusy = true;
		workList.add(new Work(projectId, status,"P"));
	}
	public void addWork(String workId){
		workList.add(new Work(workId,"","T"));
	}
	public String getDailyDisplay(){
		StringBuffer sb = new StringBuffer();
		for(Work w:workList){
			sb.append(getWorkDisplay(w));
		}
		return sb.toString();
	}
	public String getWorkDisplay(Work w){
		StringBuffer sb = new StringBuffer();
		String fontColor = isHeavyWork()?"yellow":"white";
		if("P".equals(w.getType())){
			sb.append("<a href='ProjectMaintain.do?act=qp&projectId=");
			sb.append(w.getId());
			sb.append("' style=\"color:"+fontColor+";text-decoration:none;\">");
			sb.append(w.getId());
			sb.append("</a><br>");
			sb.append(" "+SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,w.getStatus())+" ");
		}else{
			sb.append("<a href='WorkList.do?act=view&workId=");
			sb.append(w.getId());
			sb.append("' style=\"color:"+fontColor+";text-decoration:none;\">");
			sb.append(w.getId());
			sb.append("</a><br> ");
		}
		return sb.toString();
	}
	/**
	 * @return
	 */
	public boolean isBusy() {
		return isBusy;
	}

	/**
	 * @return
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param b
	 */
	public void setBusy(boolean b) {
		isBusy = b;
	}

	/**
	 * @param string
	 */
	public void setProjectId(String string) {
		projectId = string;
	}

	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string) {
		status = string;
	}
	
	class Work{
		private String id;
		private String status;
		private String type;
		Work(String id,String status,String type){
			this.id = id;
			this.status = status;
			this.type = type;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	}

	public boolean isHeavyWork() {
		String heavy  = SysConfig.getProperty("work.heavy");
		if(CheckUtil.isEmptry(heavy)){
			return workList.size()>2;
		}
		return workList.size()>Integer.parseInt(heavy);
	}
	public void setHeavyWork(boolean heavyWork) {
		this.heavyWork = heavyWork;
	}

}
