package com.eis.dic.dictype;

import java.util.List;
import java.sql.*;

import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.util.*;

/** 
 * 说明：字典归类信息的数据库访问类 
 */
public class DicTypeDAO extends BaseDAO {

	/** 
	 * 构造函数 
	 */
	public DicTypeDAO() {
		super();
	}

	/** 
	 * 构造函数 
	 */
	public DicTypeDAO(String dsName) {
		super(dsName);
	}

	/** 
	 * 初始化数据库访问语句 
	 */
	public void initSQL() {

		setInsertSQL("  insert into ep_dic_type(TYPE_ID,TYPE_NAME,DIC_LEVEL) values (?,?,?)");
		setUpdateSQL(" update ep_dic_type set TYPE_NAME=?,DIC_LEVEL=?");
		setQuerySQL(" select * from  ep_dic_type");
		setListSQL(" select * from  ep_dic_type");
		setDeleteSQL(" delete from  ep_dic_type");		
		setOrderBy(" order by TYPE_ID"); 

	}

	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {

		DicTypeVO vo = (DicTypeVO) bean;
		ps.setString(1, vo.getType_id());
		ps.setString(2, vo.getType_name());
		ps.setString(3, vo.getDic_level());

	}

	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {

		DicTypeVO vo = (DicTypeVO) bean;
		ps.setString(1, vo.getType_name());
		ps.setString(2, vo.getDic_level());

	}

	/** 
	 * 删除字典归类信息 
	 */
	public void delete(BaseVO vo) throws Exception {

		delete(
			getDeleteSQL()
				+ " where TYPE_ID='"
				+ ((DicTypeVO) vo).getType_id()
				+ "'");

	}


	/** 
	 * 查询列表，根据查询条件返回一页数据 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;

	}

	/** 
	 * 维护功能的列表，根据查询条件，返回一页数据 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;

	}



	/** 
	 * 获取列表数据的一条纪录 
	 */
	public BaseVO retrieveResult(ResultSet rs) throws Exception {

		DicTypeVO vo = new DicTypeVO();
		vo.setType_id(rs.getString("TYPE_ID").trim());
		vo.setType_name(rs.getString("TYPE_NAME").trim());
		vo.setDic_level(rs.getString("DIC_LEVEL").trim());

		//if (vo.getDic_level().equals("1"))
		//	vo.setDic_level("单级字典");
		//else {
			//vo.setDic_level("多级字典");

		//}
		return vo;

	}

	/** 
	 * 获取明细信息 
	 */
	public BaseVO detail(ResultSet rs) throws Exception {

		DicTypeVO vo = new DicTypeVO();
		vo.setType_id(rs.getString("TYPE_ID").trim());
		vo.setType_name(rs.getString("TYPE_NAME").trim());
		vo.setDic_level(rs.getString("DIC_LEVEL").trim());

		return vo;

	}

}
