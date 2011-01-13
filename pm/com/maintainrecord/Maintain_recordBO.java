 
package com.maintainrecord; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.util.*;
 
 
/** 
 * ˵��������֧�ַ����¼��ҵ���߼��� 
 */ 
public class Maintain_recordBO extends BaseBO { 
	/** 
	 * ���캯�� 
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
	 * ���Ӽ���֧�ַ����¼ 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * �������Ӽ���֧�ַ����¼ 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸ļ���֧�ַ����¼ 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.update(vo," where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
	} 
 
	/** 
	 * �����޸ļ���֧�ַ����¼ 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ������֧�ַ����¼ 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		dao.executeUpdate("update maintain_record set RES_RESULT='"+Maintain_recordVO.DELETED+"' where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'");
 
	} 
 
	/** 
	 * ����ɾ������֧�ַ����¼ 
	 */ 
	public void deleteList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ��ѯ�б�û�в�ѯ�������������м�¼ 
	 */ 
	public List queryList(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * ��ѯ�б����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List queryList(PageObject page, BaseForm form) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		StringBuffer sql =new StringBuffer("select * from maintain_record where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
 		filterForm(form,sql);
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
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
	 * ��ѯά�����ݣ��������м�¼ 
	 */ 
	public List list(UserContext user) throws Exception { 
 
		return null; 
 
	} 
 
	/** 
	 * ��ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where SEQ_NO='"+((Maintain_recordVO) vo).getSeq_no()+"'"); 
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("maintain_record_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

