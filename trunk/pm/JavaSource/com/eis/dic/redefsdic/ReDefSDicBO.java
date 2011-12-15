package com.eis.dic.redefsdic;

import java.util.List;

import com.eis.base.*;

import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;

/** 
 * ˵�����Զ��嵥���ֵ��ҵ���߼��� 
 */
public class ReDefSDicBO extends BaseBO {

	/** 
	 * ���캯�� 
	 */
	public ReDefSDicBO() {
		super();
	}

	/** 
	 * �����Զ��嵥���ֵ� 
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {
		//����Ƿ������ͬ��¼		
		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		
		if(dao.queryCount(dao.getQuerySQL()+" where TYPE_ID='"+((ReDefSDicVO)vo).getType_id()+"'")>0) {
			BaseException ex = new BaseException();
			ex.setErrorCode("E010041");
			throw ex;
		}

		
		dao.add(vo);

	}

	/** 
	 * �޸��Զ��嵥���ֵ� 
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		dao.update(
			vo,
			" where TYPE_ID='" + ((ReDefSDicVO) vo).getType_id() + "'");

	}

	/** 
	 * ɾ���Զ��嵥���ֵ� 
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		dao.delete(
			dao.getDeleteSQL()
				+ " where TYPE_ID='"
				+ ((ReDefSDicVO) vo).getType_id()
				+ "'");

	}

	/** 
	 * ����ɾ���Զ��嵥���ֵ� 
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
	public List queryList(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_redef_sdic where 1=1 ");
		//�ڴ˴����ģ��ƥ������ 

		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}

	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_redef_sdic where 1=1 ");
			
		//�ڴ˴����ģ��ƥ������ 
		
		String caption = (String) page.getFilter("caption_f");
		if (caption != null)
			sql.append(" and CAPTION like '%" + caption + "%'");

		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		
		return page.getList();

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

		ReDefSDicVO bean = (ReDefSDicVO) vo;
		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");

		return dao.retrieve(
			dao.getQuerySQL()
				+ " where TYPE_ID='"
				+ ((ReDefSDicVO) vo).getType_id()
				+ "'");

	}

	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}

}
