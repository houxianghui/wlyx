 
package com.eis.dic.mdic; 
 
import java.util.*; 
 
import com.eis.base.*; 
import com.eis.portal.UserContext; 
import com.eis.factory.*; 
import com.eis.util.*; 
import com.eis.connectionPool.*;
import com.eis.db.*;

 
 
/** 
 * ˵�����༶�ֵ��ҵ���߼��� 
 */ 
public class MDicBO extends BaseBO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public MDicBO() { 
		super(); 
	} 
	
	/*
	 * ɾ����ѡ�ֵ估�¼��ֵ�����
	 */
	public void deleteMDic(BaseVO vo, UserContext user) throws Exception {
		//��ͬһ�����У�ɾ����˵����������ϵ�����б�
		java.sql.Connection con = DBPoolManager.getConnection();
		con.setAutoCommit(false);
		try {
			ArrayList list = new ArrayList();
			
			//��ȡ�������¼��ֵ伯�ϣ������ǰ��ֵ伶���ɵ���߻�ȡ��
			getSubDic(((MDicVO) vo).getSys_id(),list);
			for(int i=0;i<list.size();i++) {
				SysLog.debug("�¼��ֵ���:"+list.get(i).toString());
				
				//���˵������ɵ͸�ɾ���˵�
				deleteDic(Long.parseLong((String)list.get(i)),con);
			}
			//�ύ����
			con.commit();
		} catch (Exception ex) {
			con.rollback();
		} finally {
			if (con != null)
				con.close();
		}

	}
	/*
	 * �ݹ��ѯ�˵����ݣ���������֮�¸����˵����뼯��list
	 */
	public void getSubDic(long sys_id, List list) throws Exception {

		
		DBQueryUtil db = new DBQueryUtil();
		try {
			java.sql.ResultSet rs =
				db.sqlQuery(
					"select SYS_ID from ep_mdic  where PARENT_ID =" + sys_id + "");
			while (rs.next()) {
				//������¼��˵����ݹ����				
				getSubDic(rs.getLong("SYS_ID"), list);
			}
			//�������˵����뼯��
			if (!list.contains(Long.toString(sys_id)))
				list.add(Long.toString(sys_id));
		} finally {
			db.close();
		}
	}

	/*
	 * ɾ�����ֵ����������ϵ�����й�ϵ����ɾ������
	 */
	public void deleteDic(long sys_id, java.sql.Connection con)
		throws Exception {

		//ɾ���˵����еļ�¼��ep_menu	
		BaseDAO dao = (BaseDAO) BeanFactory.getBean("mdic_dao");		
		dao.delete(
			dao.getDeleteSQL()
				+ " where SYS_ID ="
				+ sys_id
				+ "",
			con);

	}
	
	/** 
	 * ���Ӷ༶�ֵ� 
	 */ 
	public void add(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.add(vo); 
 
	} 
 
	/** 
	 * �������Ӷ༶�ֵ� 
	 */ 
	public void addList(BaseVO[] list, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.addList(list); 
 
	} 
 
	/** 
	 * �޸Ķ༶�ֵ� 
	 */ 
	public void update(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.update(vo," where SYS_ID="+((MDicVO) vo).getSys_id()+""); 

 
	} 
 
	/** 
	 * �����޸Ķ༶�ֵ� 
	 */ 
	public void updateList(BaseVO[] list, UserContext user) throws Exception { 
 
	} 
 
	/** 
	 * ɾ���༶�ֵ� 
	 */ 
	public void delete(BaseVO vo, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		dao.delete(dao.getDeleteSQL()+" where SYS_ID="+((MDicVO) vo).getSys_id()+""); 
 
	} 
 
	/** 
	 * ����ɾ���༶�ֵ� 
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
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_mdic where 1=1 "); 
		//�ڴ˴����ģ��ƥ������ 
 
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page,sql.toString())); 
		return page.getList(); 
 
	} 
	public List list(PageObject page, UserContext user) throws Exception { 
		return null;
	}
	
 
	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */ 
	public List listR(PageObject page, UserContext user,String type_id) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		StringBuffer sql =new StringBuffer("select * from ep_mdic where 1=1 and TYPE_ID = '"+type_id+"' order by SYS_ID,PARENT_ID,LIST_ORDER"); 
		//�ڴ˴����ģ��ƥ������ 
 		SysLog.debug(sql.toString());
		//ִ�в�ѯ 
		return dao.queryList(sql.toString()); 

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
 
		MDicVO  bean = (MDicVO)vo; 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
 
		return dao.retrieve(dao.getQuerySQL()+" where SYS_ID="+((MDicVO) vo).getSys_id()+""); 
 
	} 
 
	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */ 
	public BaseVO retrieve(String whereClause, UserContext user) throws Exception { 
 
		BaseDAO  dao = (BaseDAO)getBean("mdic_dao"); 
		return dao.retrieve(dao.getQuerySQL()+ whereClause); 
 
	} 
 
} 

