package com.eis.dic.dictype;

import java.util.List;
import java.sql.*;

import com.eis.base.BaseVO;
import com.eis.base.BaseDAO;
import com.eis.base.PageObject;
import com.eis.portal.UserContext;
import com.eis.util.*;

/** 
 * ˵�����ֵ������Ϣ�����ݿ������ 
 */
public class DicTypeDAO extends BaseDAO {

	/** 
	 * ���캯�� 
	 */
	public DicTypeDAO() {
		super();
	}

	/** 
	 * ���캯�� 
	 */
	public DicTypeDAO(String dsName) {
		super(dsName);
	}

	/** 
	 * ��ʼ�����ݿ������� 
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
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {

		DicTypeVO vo = (DicTypeVO) bean;
		ps.setString(1, vo.getType_id());
		ps.setString(2, vo.getType_name());
		ps.setString(3, vo.getDic_level());

	}

	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean)
		throws Exception {

		DicTypeVO vo = (DicTypeVO) bean;
		ps.setString(1, vo.getType_name());
		ps.setString(2, vo.getDic_level());

	}

	/** 
	 * ɾ���ֵ������Ϣ 
	 */
	public void delete(BaseVO vo) throws Exception {

		delete(
			getDeleteSQL()
				+ " where TYPE_ID='"
				+ ((DicTypeVO) vo).getType_id()
				+ "'");

	}


	/** 
	 * ��ѯ�б����ݲ�ѯ��������һҳ���� 
	 */
	public List queryList(PageObject page, UserContext user) throws Exception {

		return null;

	}

	/** 
	 * ά�����ܵ��б����ݲ�ѯ����������һҳ���� 
	 */
	public List list(PageObject page, UserContext user) throws Exception {

		return null;

	}



	/** 
	 * ��ȡ�б����ݵ�һ����¼ 
	 */
	public BaseVO retrieveResult(ResultSet rs) throws Exception {

		DicTypeVO vo = new DicTypeVO();
		vo.setType_id(rs.getString("TYPE_ID").trim());
		vo.setType_name(rs.getString("TYPE_NAME").trim());
		vo.setDic_level(rs.getString("DIC_LEVEL").trim());

		//if (vo.getDic_level().equals("1"))
		//	vo.setDic_level("�����ֵ�");
		//else {
			//vo.setDic_level("�༶�ֵ�");

		//}
		return vo;

	}

	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */
	public BaseVO detail(ResultSet rs) throws Exception {

		DicTypeVO vo = new DicTypeVO();
		vo.setType_id(rs.getString("TYPE_ID").trim());
		vo.setType_name(rs.getString("TYPE_NAME").trim());
		vo.setDic_level(rs.getString("DIC_LEVEL").trim());

		return vo;

	}

}
