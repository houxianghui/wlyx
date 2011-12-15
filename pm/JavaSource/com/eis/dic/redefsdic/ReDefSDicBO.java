package com.eis.dic.redefsdic;

import java.util.List;

import com.eis.base.*;

import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;

/** 
 * 说明：自定义单级字典的业务逻辑类 
 */
public class ReDefSDicBO extends BaseBO {

	/** 
	 * 构造函数 
	 */
	public ReDefSDicBO() {
		super();
	}

	/** 
	 * 增加自定义单级字典 
	 */
	public void add(BaseVO vo, UserContext user) throws Exception {
		//检查是否存在相同记录		
		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		
		if(dao.queryCount(dao.getQuerySQL()+" where TYPE_ID='"+((ReDefSDicVO)vo).getType_id()+"'")>0) {
			BaseException ex = new BaseException();
			ex.setErrorCode("E010041");
			throw ex;
		}

		
		dao.add(vo);

	}

	/** 
	 * 修改自定义单级字典 
	 */
	public void update(BaseVO vo, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		dao.update(
			vo,
			" where TYPE_ID='" + ((ReDefSDicVO) vo).getType_id() + "'");

	}

	/** 
	 * 删除自定义单级字典 
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
	 * 批量删除自定义单级字典 
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
	public List queryList(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_redef_sdic where 1=1 ");
		//在此处添加模糊匹配条件 

		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		return page.getList();

	}

	/** 
	 * 查询维护数据，根据查询条件返回符合条件的一页纪录 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		StringBuffer sql =
			new StringBuffer("select * from ep_redef_sdic where 1=1 ");
			
		//在此处添加模糊匹配条件 
		
		String caption = (String) page.getFilter("caption_f");
		if (caption != null)
			sql.append(" and CAPTION like '%" + caption + "%'");

		//执行查询 
		page.setList(dao.queryPage(page, sql.toString()));
		
		return page.getList();

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

		ReDefSDicVO bean = (ReDefSDicVO) vo;
		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");

		return dao.retrieve(
			dao.getQuerySQL()
				+ " where TYPE_ID='"
				+ ((ReDefSDicVO) vo).getType_id()
				+ "'");

	}

	/** 
	 * 根据where条件查询明细信息，返回单一纪录 
	 */
	public BaseVO retrieve(String whereClause, UserContext user)
		throws Exception {

		BaseDAO dao = (BaseDAO) getBean("redefsdic_dao");
		return dao.retrieve(dao.getQuerySQL() + whereClause);

	}

}
