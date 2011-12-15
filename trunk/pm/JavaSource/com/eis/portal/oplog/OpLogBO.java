package com.eis.portal.oplog;

import java.util.List;

import com.eis.base.*;
import com.eis.cache.*;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;

/** 
 * 说明：操作日志的业务逻辑类 
 */
public class OpLogBO extends BaseBO {

	/** 
	 * 构造函数 
	 */
	public OpLogBO() {
		super();
	}

	/** 
	 * 增加操作日志 
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		dao.add(vo);

	}


	/** 
	 * 修改操作日志 
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
	 * 删除操作日志 
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
	 * 查询列表，没有查询条件，返回所有纪录 
	 */
	public List queryList(UserContext user) throws Exception {

		return null;

	}

	/** 
	 * 查询列表，根据查询条件返回符合条件的一页纪录 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		
		StringBuffer sql =
			new StringBuffer("set rowcount 2000 select * from ep_op_log_view where 1=1 ");
			
			
		//添加辖属机构逻辑
		//在此处添加模糊匹配条件 
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
		
		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		
		return page.getList();

	}

	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		
		return null;


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

		OpLogVO bean = (OpLogVO) vo;
		BaseDAO dao = (BaseDAO) getBean("oplog_dao");

		return dao.retrieve(
			dao.getQuerySQL()
				+ " where SYS_ID="+ ((OpLogVO) vo).getSys_id());

	}

	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}
	/** 
	 * 根据login_id,取得user_id 
	 */
	public String getUserID(String login_id)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("oplog_dao");
		String user_id = dao.querySingle("select USER_ID from ep_user where LOGIN_ID='"+login_id+"'");
		return user_id;

	}

}
