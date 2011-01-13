/*
 * @# ProjectMaintainBO.java 2008-11-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.eis.base.IbatisBaseBO;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.util.DateUtil;


public class ProjectMaintainBO extends IbatisBaseBO {

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {
		ChangeRecordVO vo = new ChangeRecordVO();
		vo.setProjectId(((ProjectMaintainVO)obj).getProjectId());
		vo.setChangeDate(DateUtil.getDTStr());
		vo.setContent(getContent(obj));
		vo.setReason(((ProjectMaintainVO)obj).getReason());
		vo.setUserId(((ProjectMaintainVO)obj).getUserId());
		dao.insert("insertChangeRecord",vo);		
		dao.update("updateProject",obj);
	}
	private String getContent(Object obj)throws Exception{
		StringBuffer sb = new StringBuffer();
		ProjectMaintainVO vo = (ProjectMaintainVO)obj;
		sb.append("新内容为:"+getDiscription(vo));
		ProjectMaintainVO vo2 = (ProjectMaintainVO)queryForObjectUseVO(obj);
		sb.append("<br>原内容为:"+getDiscription(vo2));
		return sb.toString();
	}
	private String getDiscription(ProjectMaintainVO vo){
		return "阶段-<strong>"+SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())+
				"</strong>;是否合同内-<strong>"+SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getIsInContract())+
				"</strong>;是否参数调整实现-<strong>"+SingleDicMap.getDicItemVal(SingleDic.YES_OR_NO,vo.getNeedDev())+
				"</strong>;问题单列表-<strong>"+vo.getIssId()+"</strong>";	
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {
		dao.insert("insertProject",obj);
	}
	public List getChangeRecord(Object obj)throws Exception{
		return dao.queryForList("getChangeRecord",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return dao.queryForObject("getProject",obj);
	}
	public Object queryForObjectUseVO(Object obj)throws Exception{
		return dao.queryForObject("getProjectUseVO",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList("getActiveProject",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {
		dao.update("deleteProject",obj);
		ProjectDistributeVO vo = new ProjectDistributeVO();
		vo.setProjectId(((ProjectMaintainVO)obj).getProjectId());
		dao.delete("deleteProjectDistribute",vo);
	}
	
	public List queryForAllList(Object obj)throws Exception{
		return dao.queryForList("getProjectList",obj);
	}
	public List getMyInputProjects(Object obj)throws Exception{
		return dao.queryForList("getMyInputProjects",obj);
	}
	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {
		dao.delete("deleteProjectRelation",obj[0]);
		for(int i=0;i < obj.length;i++){
			dao.insert("insertProjectRelation",obj[i]);
		}
	}
	public void deletePreProjects(Object obj)throws Exception{
		dao.delete("deleteProjectRelation",obj);
	}
	public List getSelectedProjects(Object obj)throws Exception{
		List l = dao.queryForList("getSelectedProjects",obj);
		ArrayList al = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){
			al.add(it.next());
		}
		return al;
	}
	public List getNotSelectedProjects(Object obj)throws Exception{
		List l = dao.queryForList("getNotSelectedProjects",obj);
		ArrayList al = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){			
			al.add(it.next());
		}
		return al;
	}
	/**
	 * 获得冲突项目列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getConflictProjects(Object obj)throws Exception{
		return dao.queryForList("getConflictProjects",obj);
	}
	/**
	 * 获得制定项目编号的项目的所有前驱项目的编号
	 * @param projectId
	 * @param l
	 * @return 
	 */
	private List getPreProject(String projectId,List l){
		List list = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){
			PreProjectVO pv = (PreProjectVO)it.next();
			if(projectId.equals(pv.getProjectId())){
				List preProject = getPreProject(pv.getPreProject(),l);
				Iterator iter = preProject.iterator();
				while(iter.hasNext()){
					list.add(iter.next().toString());
				}
				list.add(pv.getPreProject());
			}
		}
		list.add("''");
		return list;
	}
	private List getPreProjects(Object obj)throws Exception{
		return getPreProject(((ProjectMaintainForm)obj).getProjectId(),getProjectRelation(obj));
	}
	/**
	 * 获得制定项目编号的项目的所有后继项目的编号
	 * @param projectId
	 * @param l
	 * @return 
	 */
	private List getSufProject(String projectId,List l){
		List list = new ArrayList();
		Iterator it = l.iterator();
		while(it.hasNext()){
			PreProjectVO pv = (PreProjectVO)it.next();
			if(projectId.equals(pv.getPreProject())){
				List sufProject = getSufProject(pv.getProjectId(),l);
				Iterator iter = sufProject.iterator();
				while(iter.hasNext()){
					list.add(iter.next().toString());
				}
				list.add(pv.getProjectId());
			}
		}

		return list;
	}
	
	/**
	 * 获得所有项目的前驱关系信息
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private List getProjectRelation(Object obj)throws Exception{
		return dao.queryForList("getProjectRelation",obj);
	}
	/**
	 * 将两个List合并成一个List
	 * @param preList
	 * @param sufList
	 * @return
	 */
	private List getRelateProjects(List preList,List sufList){
		List l = new ArrayList();
		Iterator it = preList.iterator();
		while(it.hasNext()){
			l.add(it.next().toString());
		}
		it = sufList.iterator();
		while(it.hasNext()){
			l.add(it.next().toString());
		}
		return l;
	}
	/**
	 * 获得与本项目有前驱或者后继关系的项目编号列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getRelateProjects(Object obj)throws Exception{
		List l = getProjectRelation(obj);
		return getRelateProjects(getPreProject(((ProjectMaintainForm)obj).getProjectId(),l),getSufProject(((ProjectMaintainForm)obj).getProjectId(),l));
	}
	/**
	 * 获得前驱项目列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getPreProjectStatus(Object obj)throws Exception{
		return dao.queryForList("getPreProjectStatus",getPreProjects(obj));
	}
	public void validateUpdateStatus(Object obj)throws Exception{
		if(((ProjectMaintainForm)obj).getStatus().equals("9")){
			return;
		}
		List l = getPreProjectStatus(obj);
		Iterator it = l.iterator();
		while(it.hasNext()){
			ProjectMaintainVO vo = (ProjectMaintainVO)it.next();
			if(Integer.parseInt(vo.getStatus()) <= Integer.parseInt(((ProjectMaintainForm)obj).getStatus())){
				throw new MessageException("前驱项目"+vo.getProjectId()+" "+vo.getProjectName()+"阶段为"+
				SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION, vo.getStatus())+"本项目阶段不能为"+
				SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION, ((ProjectMaintainForm)obj).getStatus()));
			}
		}
	}
	
}
