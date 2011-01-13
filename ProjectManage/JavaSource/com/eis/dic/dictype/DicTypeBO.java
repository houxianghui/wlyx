 
package com.eis.dic.dictype; 
 
import java.util.List; 
 
import com.eis.base.*; 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
import com.eis.connectionPool.*;
 
 
/** 
 * ˵�����ֵ������Ϣ��ҵ���߼��� 
 */ 
public class DicTypeBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public DicTypeBO() { 
		super(); 
	} 
 
	/** 
	 * �����ֵ������Ϣ 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		
		//���������������Ƿ����
		String sql =  dao.getQuerySQL()
						+ " where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'" ;
		
		if(dao.queryCount(sql)>0) {
			BaseException ex = new BaseException();
			ex.setErrorCode("E010041");
			throw ex;
		}
		
		dao.add(vo); 
		
		//�Ǽ������ֵ������Ϣ������־		
		OpLog.Log(user,"03","c","�����ֵ������Ϣ",((DicTypeVO) vo).getType_id());	

 
	} 
 
	/** 
	 * ���������ֵ������Ϣ 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸��ֵ������Ϣ 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		dao.update(vo," where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'"); 
		
		//�Ǽ��޸��ֵ������Ϣ������־		
		OpLog.Log(user,"03","u","�޸��ֵ������Ϣ",((DicTypeVO) vo).getType_id());	

 
	} 
 
	/** 
	 * �����޸��ֵ������Ϣ 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ���ֵ������Ϣ 
	 * ���������ɾ����Ӧ��ep_sdic��
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
		
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);
	 	try{
			BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
			//ɾ��dic_type���еĶ�Ӧ��
			dao.delete(dao.getDeleteSQL()+" where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con); 
			//����ָ�������룬ɾ��sdic������Ϣ
			dao.delete("delete from ep_sdic  where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con);
		
			//�Ǽ�ɾ���ֵ������Ϣ������־		
			OpLog.Log(user,"03","d","ɾ�������ֵ������Ϣ",((DicTypeVO) vo).getType_id());	
			//�ύ����
			con.commit();
		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}
 
	} 
	/** 
	 * ɾ���ֵ������Ϣ 
	 * ���������ɾ����Ӧ��ep_mdic��
	 */ 
	public void deleteM(BaseVO vo, UserContext user) throws Exception { 
		
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);
		try{
			BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
			//ɾ��dic_type���еĶ�Ӧ��
			dao.delete(dao.getDeleteSQL()+" where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con); 
			//����ָ�������룬ɾ��sdic������Ϣ
			dao.delete("delete from ep_mdic  where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'",con);
		
			//�Ǽ�ɾ���ֵ������Ϣ������־		
			OpLog.Log(user,"03","d","ɾ���༶�ֵ������Ϣ",((DicTypeVO) vo).getType_id());	
			//�ύ����
			con.commit();
		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}
 
	} 
 
	/** 
	 * ����ɾ���ֵ������Ϣ 
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
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_dic_type where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List list(PageObject page, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_dic_type where 1=1 "); 
		
		//�ڴ˴����ģ��ƥ������ 
		String type_id= (String)page.getFilter("type_id_f");
		if(type_id!= null) 
		sql.append(" and TYPE_ID like '%"+type_id+"%'");
		
		String type_name= (String)page.getFilter("type_name_f");
		if(type_name!= null) 
		sql.append(" and TYPE_NAME like '%"+type_name+"%'");
		
		sql.append(dao.getOrderBy());
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
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
 
		DicTypeVO  bean = (DicTypeVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		
		BaseVO v =  dao.retrieve(dao.getQuerySQL()+" where TYPE_ID='"+((DicTypeVO) vo).getType_id()+"'");
 
		//�Ǽǲ鿴�ֵ������Ϣ������־		
		OpLog.Log(user,"03","r","�鿴�ֵ������Ϣ",((DicTypeVO) vo).getType_id());	 
		
		return v;
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("dictype_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

