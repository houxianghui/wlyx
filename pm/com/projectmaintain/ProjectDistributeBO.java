/*
 * @# ProjectDistributeBO.java 2008-11-21 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.DateUtil;


public class ProjectDistributeBO extends IbatisBaseBO {
	public void validateStatus(Object obj)throws Exception{
		ProjectDistributeForm form = (ProjectDistributeForm)obj;
		ProjectMaintainVO vo = (ProjectMaintainVO)getProject(form.getProjectId());
		if(!form.getStatus().equals(vo.getStatus())){
			throw new MessageException("项目当前阶段为"+SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())+",你还不能执行此操作！");
		}
	}
	public void validateWork(Object obj,UserContext user)throws Exception{
		ProjectDistributeForm form = (ProjectDistributeForm)obj;
		form.setEndDate(DateUtil.getDTStr());
		form.setUserId(user.getUserID());		
		if(dao.queryForObject("getTodayWork",obj) == null){
			throw new MessageException("该项目当日工时未填写，请先填写工时");
		}
	}
	private Object getProject(String projectId){
		ProjectMaintainVO vo = new ProjectMaintainVO();
		vo.setProjectId(projectId);
		return dao.queryForObject("getProjectUseVO",vo);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {

	}
	public List getDoneProjectDistributes(Object obj)throws Exception{
		return dao.queryForList("getDoneProjectDistributes",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj,UserContext user) throws Exception {
		ChangeRecordVO vo = new ChangeRecordVO();		
		vo.setProjectId(((ProjectDistributeVO)obj).getProjectId());
		vo.setChangeDate(DateUtil.getDTStr());
		vo.setContent(getContent(obj));
		vo.setReason(((ProjectDistributeVO)obj).getReason());
		vo.setUserId(user.getUserID());
		dao.insert("insertChangeRecord",vo);
		dao.update("updateProjectDistribute",obj);
	}
	private String getContent(final Object obj)throws Exception{
		StringBuffer sb = new StringBuffer();
		ProjectDistributeVO vo = (ProjectDistributeVO)obj;
		sb.append("新内容为:"+getDiscription(vo));
		ProjectDistributeVO pdvo = (ProjectDistributeVO)queryForObject(obj);
		sb.append("<br>原内容为:"+getDiscription(pdvo));
		return sb.toString();
	}
	private String getDiscription(ProjectDistributeVO vo){
		return "开始时间-<strong>"+vo.getStartDate()+"</strong>结束时间-<strong>"+vo.getEndDate()+"</strong>";	
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		dao.insert("insertProjectDistribute",obj);
	}
	public Object getProjectDistributeDetail(Object obj)throws Exception{
		return dao.queryForObject("getProjectDistributeDetail",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return dao.queryForObject("queryForProjectDistribute",obj);
	}
	public void remove(Object obj)throws Exception{
		dao.delete("removeProjectDistribute",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("getProjectDistribute",obj);
	}
	public List getNotDoneWorks(Object obj)throws Exception{
		return dao.queryForList("getNotDoneWorks",obj);
	}
	public List queryForProjectDistributeList(Object obj)throws Exception{
		return dao.queryForList("getProjectDistributeList",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {
		dao.delete("deleteProjectDistribute",obj);
	}
	
	public List getSelectedStuff(Object obj)throws Exception{
		return dao.queryForList("getSelectedStuff",obj);
	}
	public List getNotSelectedStuff(Object obj)throws Exception{
		return dao.queryForList("getNotSelectedStuff",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {
		if(obj.length  == 0){
			return;
		}
		//delete(obj[0]);
		for(int i = 0;i < obj.length;i++){
			insert(obj[i]);
		}
	}
	public List getMyProjects(Object obj)throws Exception{
		return dao.queryForList("getMyProjects",obj);
	}
	/**
	 * 获得当月所有人员的工作分配情况
	 * 
	 * @param obj 月份
	 * @return List of ProjectDistribu
	 */
	public List getMonthlyDistribute(Object obj)throws Exception{
		return dao.queryForList("getMonthlyDistribute",obj);
	}
	/**
	 * 获得有时间冲突的分配记录
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getConflictDistribute(Object obj)throws Exception{
		return dao.queryForList("getConflictDistribute",obj);
	}
	public void checkBusy(ProjectDistributeVO vo)throws Exception{
		List l = getConflictDistribute(vo);
		if(l == null || l.size() == 0){
			return;
		}
		Iterator it  = l.iterator();
		StringBuffer sb = new StringBuffer("时间分配冲突，冲突项目信息：");
		while(it.hasNext()){
			ProjectDistributeVO v = (ProjectDistributeVO)it.next();
			sb.append(v.getProjectId()+""+SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,v.getStatus())+" "+v.getStartDate()+"-"+v.getEndDate()+" ");
		}
		throw new MessageException(sb.toString());
	}
	/**
	 * 获得员工列表
	 * 
	 * @return List of Stuff userId
	 */
	public List getStuff(){
		return dao.queryForList("getStuff",null);
	}
	public void finishMyProject(Object obj,UserContext user){
		ProjectDistributeForm form = (ProjectDistributeForm)obj;
		ProjectDistributeVO vo = new ProjectDistributeVO();
		vo.setId(form.getId());
		vo.setUserId(user.getUserID());
		vo.setProjectId(form.getProjectId());
		vo.setStatus(form.getStatus());
		dao.update("finishMyProject",vo);
	}
	
	public String getDisplay(String now)throws Exception{
		WorkTable wt[] = getWorkTable(now);
		String end = DateUtil.getMonEnd(now.substring(0,6));
		String start = now.substring(0,6)+"01";
		int days = (int)DateUtil.getDays(start,end)+1;
		Calendar dayOfWeek[] = new Calendar[days];
		setDate(dayOfWeek,now);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table width=\"98%\" class=\"dtPanel_Line1\" border=\"0\" cellspacing=\"1\" align=\"center\" cellpadding=\"0\">");
		sb.append(getHead(days,dayOfWeek));
		for(int i = 0;i < wt.length;i++){
			sb.append(getLine(wt[i],days,dayOfWeek));
		}
		
		sb.append("</table>");
		return sb.toString();
	}
	
	private StringBuffer getLine(WorkTable wt,int days,Calendar[] c){
		StringBuffer sb = new StringBuffer();	
		sb.append("<tr align=\"center\" class=\"dtPanel_Top01\">");
		sb.append("<td width=\"7%\">"+ReDefSDicMap.getDicItemVal("0003",wt.getUserId())+"</td>");
		String bgColor = null;
		
		for(int i = 0;i<days;i++){
			if(isWeekEnd(c[i])){
				bgColor = "gray";
			}else{
				bgColor = "green";
			}
			if(wt.getTable()[i] == null){
				sb.append("<td bgcolor="+bgColor+"></td>");
			}else{
				sb.append("<td bgcolor="+bgColor+"><font color=\"white\">"+wt.getTable()[i].getProjectId()+" "+SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,wt.getTable()[i].getStatus())+"</font></td>");
			}
			
		}
		
		sb.append("</tr>");
		return sb;
	}
	

	private StringBuffer getHead(int days,Calendar[] c){
		StringBuffer sb = new StringBuffer("<tr align=\"center\" class=\"dtPanel_Top01\">");
		sb.append("<td></td>");		
		for(int i = 1;i <= days;i++){ 
			sb.append("<td width=\"3.3%\">"+i+"<br>"+getNewDayOfWeek(c[i-1])+"</td>");
		}
		sb.append("</tr>");
		return sb;
	}
	private boolean isWeekEnd(Calendar c){
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return true;
		}
		return false;
	}
	private String getDayOfWeek(Calendar c){
		SimpleDateFormat sf = new SimpleDateFormat("E");
		return sf.format(c.getTime());
	}
	private String getNewDayOfWeek(Calendar c){
		return getDayOfWeek(c).substring(2);
	}
	private void setDate(Calendar[] days,String now){
		for(int i = 0;i < days.length;i++){
			days[i] = getDayOfWeek(i+1,now);
		}
	}
	private Calendar getDayOfWeek(int day,String now){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,Integer.parseInt(now.substring(0,4)));
		c.set(Calendar.MONTH,Integer.parseInt(now.substring(4,6))-1);
		c.set(Calendar.DATE,day);
		return c;
	}
	private WorkTable[] getWorkTable(String now)throws Exception{		
		String monthEnd = DateUtil.getMonEnd(now.substring(0,6));		
		List stuff = getStuff();
		WorkTable wt[] = new WorkTable[stuff.size()];
		Iterator it = stuff.iterator();
		int i = 0;
		while(it.hasNext()){			
			wt[i] = new WorkTable();
			wt[i++].setUserId((String)it.next());
		}
		
		List projectDistribute = getMonthlyDistribute(now);
		Iterator pd = projectDistribute.iterator();
		while(pd.hasNext()){
			ProjectDistributeVO vo = (ProjectDistributeVO)pd.next();					
			if(vo.getStartDate().compareTo(monthEnd) > 0){
				continue;
			}
			int position = findPosition(vo,wt);
			int[] se = getStartAndEndPosition(vo,now,wt[position]);
			for(int j = se[0];j <se[1];j++ ){
				wt[position].getTable()[j] = new WorkContent();
				wt[position].getTable()[j].setBusy(true);
				wt[position].getTable()[j].setProjectId(vo.getProjectId());
				wt[position].getTable()[j].setStatus(vo.getStatus());
			}			
		}
		return wt;
	}
	
	private int findPosition(ProjectDistributeVO vo,WorkTable[] wt)throws Exception{
		for(int i = 0;i < wt.length;i++){
			if(vo.getUserId().equals(wt[i].getUserId())){
				return i;
			}
		}
		throw new MessageException("项目 "+vo.getProjectId()+" 阶段 "+vo.getStatus()+" 分配给员工 "+
		ReDefSDicMap.getDicItemVal("0003",vo.getUserId())+" ,但该员工身份已经无效，请将对应工作分配记录删除");
	}
	private int[] getStartAndEndPosition(ProjectDistributeVO vo,String now,WorkTable wt){
		int positions[] = new int[2];
		String monthEnd = DateUtil.getMonEnd(now.substring(0,6));
		String monthStart = now.substring(0,6)+"01";
		
		if(vo.getStartDate().compareTo(monthStart) < 0){
			positions[0] = 0;
			if(vo.getEndDate().compareTo(monthEnd) > 0){
				positions[1] = wt.getTable().length;
			}else{
				positions[1] = (int)DateUtil.getDays(monthStart,vo.getEndDate())+1;
			}
		}else{
			positions[0] = (int)DateUtil.getDays(monthStart,vo.getStartDate());
			if(vo.getEndDate().compareTo(monthEnd) > 0){
				positions[1] = wt.getTable().length;
			}else{
				positions[1] = (int)DateUtil.getDays(monthStart,vo.getEndDate())+1;
			}
		}
		return positions;
	}
	public boolean checkBlankWork(UserContext user)throws Exception{
		BlankWorkVO vo = new BlankWorkVO();
		boolean flag = false;
		Calendar c = Calendar.getInstance();
		int days = c.get(Calendar.DAY_OF_WEEK);
		String sdate = "";
		String edate = "";
		int count;
		vo.setUser(user.getUserID());
		if(days == 1 || days == 7 || days == 2){
			if(days==1){
				sdate = DateUtil.getDateBeforeSysdt(6);
				edate = DateUtil.getDateBeforeSysdt(2);
			}
			if(days==7){
				sdate = DateUtil.getDateBeforeSysdt(5);
				edate = DateUtil.getDateBeforeSysdt(1);
			}
			if(days==2){
				sdate = DateUtil.getDateBeforeSysdt(7);
				edate = DateUtil.getDateBeforeSysdt(3);
			}
			vo.setEdate(edate);
			vo.setSdate(sdate);
			Object obj = dao.queryForObject("getBlankWork",vo);
			if(null==obj){
				count=0;
			}else{
				count = ((Integer)obj).intValue();
			}
			if(count <5){
				flag = true;
			}
				
		}else{
			sdate = DateUtil.getDateBeforeSysdt(days-2);
			edate = DateUtil.getDateBeforeSysdt(1);
			vo.setEdate(edate);
			vo.setSdate(sdate);
			Object obj = dao.queryForObject("getBlankWork",vo);
			if(null==obj){
				count=0;
			}else{
				count = ((Integer)obj).intValue();
			}
			if(count <(days-2)){
				flag = true;
			}
				
		}
		return flag;

	}
	
}
