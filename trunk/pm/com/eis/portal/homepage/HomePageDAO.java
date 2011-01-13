 
package com.eis.portal.homepage; 
 
import java.util.List; 
import java.sql.*; 
 
import com.eis.base.BaseVO; 
import com.eis.base.BaseDAO; 
import com.eis.base.PageObject; 
import com.eis.portal.UserContext; 
import com.eis.util.*; 
import com.eis.key.KeyGenerator;
 
 
/** 
 * ˵������ҳģ������ݿ������ 
 */ 
public class HomePageDAO extends BaseDAO { 
 
	/** 
	 * ���캯�� 
	 */ 
	public HomePageDAO() { 
		super(); 
	} 
 
	/** 
	 * ���캯�� 
	 */ 
	public HomePageDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * ��ʼ�����ݿ������� 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_homepage(TEMPL_ID,CAPTION,URL) values (?,?,?)"); 
		setUpdateSQL(" update ep_homepage set CAPTION=?,URL=?"); 
		setQuerySQL(" select * from  ep_homepage");  
		setListSQL(" select * from  ep_homepage");  
		setDeleteSQL(" delete from  ep_homepage");  
		//�˴����order by ��� 
		setOrderBy(" order by TEMPL_ID"); 
 
	} 
 
	/** 
	 * ��ִ���������ӵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		HomePageVO vo = (HomePageVO) bean; 
		ps.setInt(1, vo.getTempl_id()); 
		ps.setString(2, vo.getCaption()); 
		ps.setString(3, vo.getUrl()); 
 
	} 
 
	/** 
	 * ��ִ�������޸ĵ�PreparedStatement�еĲ������и�ֵ 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		HomePageVO vo = (HomePageVO) bean; 
		ps.setString(1, vo.getCaption()); 
		ps.setString(2, vo.getUrl()); 
 
	} 
 
	/** 
	 * ɾ����ҳģ�� 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
 
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
 
		HomePageVO vo = new HomePageVO(); 
		vo.setTempl_id(rs.getInt("TEMPL_ID")); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setUrl(rs.getString("URL").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * ��ȡ��ϸ��Ϣ 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		HomePageVO vo = new HomePageVO(); 
		vo.setTempl_id(rs.getInt("TEMPL_ID")); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setUrl(rs.getString("URL").trim()); 
		return vo; 
 
	} 
 
} 

