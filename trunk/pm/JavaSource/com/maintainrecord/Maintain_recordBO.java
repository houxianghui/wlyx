 
package com.maintainrecord; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*;
 
 
/** 
 * 说明：技术支持服务记录的业务逻辑类 
 */ 
public class Maintain_recordBO extends BaseBO { 
	/** 
	 * 构造函数 
	 */ 
	public Maintain_recordBO() { 
		super(); 
	} 
	 /*
	 * @see com.eis.base.BusinessService#list(com.eis.base.PageObject, com.eis.portal.UserContext)
	 */
	public List list(PageObject page, UserContext user) throws Exception {
	    return null;
	}
	/*
	 * @see com.eis.base.BusinessService#queryList(com.eis.base.PageObject, com.eis.portal.UserContext)
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {
	    return null;
	}

	/** 
	 * 增加技术支持服务记录 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * 批量增加技术支持服务记录 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * 修改技术支持服务记录 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.update(vo," where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
	} 
 
	/** 
	 * 批量修改技术支持服务记录 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 删除技术支持服务记录 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.executeUpdate("update maintain_record set RES_RESULT='"+Maintain_recordVO.DELETED+"' where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'");
 
	} 
 
	/** 
	 * 批量删除技术支持服务记录 
	 */ 
	public void deleteList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * 查询列表，没有查询条件，返回所有纪录 
	 */ 
	public List queryList(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * 查询列表，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List queryList(PageObject page, BaseForm form) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		StringBuffer sql =new StringBuffer("select * from maintain_record where 1=1 "); 
		//在此处添加模糊匹配条件 
 		filterForm(form,sql);
		//执行查询 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */ 
	public List list(PageObject page,BaseForm form) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		StringBuffer sql =new StringBuffer("select * from maintain_record where RES_RESULT not in ('"+Maintain_recordVO.FINISHED+"','"+Maintain_recordVO.DELETED+"') "); 

 		filterForm(form,sql);

		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 	void filterForm(BaseForm form,StringBuffer sql){
 		Maintain_recordForm mForm = (Maintain_recordForm)form;
 		if(!CheckUtil.isEmptry(mForm.getQus_date_f()))
 			sql.append(" and QUS_DATE like '"+mForm.getQus_date_f()+"%' ");
 		if(!CheckUtil.isEmptry(mForm.getRes_result_f())){
 			sql.append(" and RES_RESULT = '"+mForm.getRes_result_f()+"' ");
 		}
 	}
	/** 
	 * 查询维护数据，返回所有纪录 
	 */ 
	public List list(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * 查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
	} 
 
	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

