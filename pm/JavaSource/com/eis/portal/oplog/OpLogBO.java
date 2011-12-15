package com.eis.portal.oplog;

import java.util.List;

import com.eis.base.*;
import com.eis.cache.*;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;

/** 
 * ˵����������־��ҵ���߼��� 
 */
public class OpLogBO extends BaseBO {

	/** 
	 * ���캯�� 
	 */
	public OpLogBO() {
		super();
	}

	/** 
	 * ���Ӳ�����־ 
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		dao.add(vo);

	}


	/** 
	 * �޸Ĳ�����־ 
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		dao.update(
			vo,
			" where USER_ID='"
				+ ((OpLogVO) vo).getUser_id()
				+ "'"
				+ " and EVENT_TIME='"
				+ ((OpLogVO) vo).getEvent_time()
				+ "'");

	}


	/** 
	 * ɾ��������־ 
	 */
	public void delete(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		dao.delete(
			dao.getDeleteSQL()
				+ " where USER_ID='"
				+ ((OpLogVO) vo).getUser_id()
				+ "'"
				+ " and EVENT_TIME='"
				+ ((OpLogVO) vo).getEvent_time()
				+ "'");

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

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		
		StringBuffer sql =
			new StringBuffer("set rowcount 2000 select * from ep_op_log_view where 1=1 ");
			
			
		//���Ͻ�������߼�
		//�ڴ˴����ģ��ƥ������ 
		String event_date_begin = (String) page.getFilter("event_date_begin");
		if (event_date_begin != null && event_date_begin.trim().length()>0){
			event_date_begin = event_date_begin + "000000";
			sql.append(" and EVENT_TIME >= '" + event_date_begin.trim() + "'");
		}
		
		String event_date_end = (String) page.getFilter("event_date_end");
		if (event_date_end != null && event_date_end.trim().length()>0){
			event_date_end = event_date_end + "240000";
			sql.append(" and EVENT_TIME < '" + event_date_end.trim() + "'");
		}
			
		String event_type = (String) page.getFilter("event_type");
		if (event_type != null && event_type.trim().length()>0)
			sql.append(" and EVENT_TYPE = '" + event_type + "'");
			
		String op_id = (String) page.getFilter("op_id");
		if (op_id != null && op_id.trim().length()>0)
			sql.append(" and OP_ID = '" + op_id + "'");
			
			
		String user_id = (String) page.getFilter("user_id");
		if (user_id != null && user_id.trim().length()>0){		
			sql.append(" and USER_ID = '" + user_id.trim() + "'");
		}

			
		sql.append(" order by EVENT_TIME desc set rowcount 0");
		
		//ִ�в�ѯ 
		page.setList(dao.queryPage(page, sql.toString()));
		
		return page.getList();

	}

	/** 
	 * ��ѯά�����ݣ����ݲ�ѯ�������ط���������һҳ��¼ 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		
		return null;


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

		OpLogVO bean = (OpLogVO) vo;
		BaseDAO dao = (BaseDAO) getBean("oplog_dao");

		return dao.retrieve(
			dao.getQuerySQL()
				+ " where SYS_ID="+ ((OpLogVO) vo).getSys_id());

	}

	/** 
	 * ����where������ѯ��ϸ��Ϣ�����ص�һ��¼ 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}
	/** 
	 * ����login_id,ȡ��user_id 
	 */
	public String getUserID(String login_id)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		String user_id = dao.querySingle("select USER_ID from ep_user where LOGIN_ID='"+login_id+"'");
		return user_id;

	}

}
