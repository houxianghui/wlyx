 
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
 * 说明：首页模板的数据库访问类 
 */ 
public class HomePageDAO extends BaseDAO { 
 
	/** 
	 * 构造函数 
	 */ 
	public HomePageDAO() { 
		super(); 
	} 
 
	/** 
	 * 构造函数 
	 */ 
	public HomePageDAO(String dsName) { 
		super(dsName); 
	} 
 
	/** 
	 * 初始化数据库访问语句 
	 */ 
	public void initSQL() { 
 
		setInsertSQL("  insert into ep_homepage(TEMPL_ID,CAPTION,URL) values (?,?,?)"); 
		setUpdateSQL(" update ep_homepage set CAPTION=?,URL=?"); 
		setQuerySQL(" select * from  ep_homepage");  
		setListSQL(" select * from  ep_homepage");  
		setDeleteSQL(" delete from  ep_homepage");  
		//此处添加order by 语句 
		setOrderBy(" order by TEMPL_ID"); 
 
	} 
 
	/** 
	 * 对执行数据增加的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareInsertStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		HomePageVO vo = (HomePageVO) bean; 
		ps.setInt(1, vo.getTempl_id()); 
		ps.setString(2, vo.getCaption()); 
		ps.setString(3, vo.getUrl()); 
 
	} 
 
	/** 
	 * 对执行数据修改的PreparedStatement中的参数进行赋值 
	 */ 
	public void prepareUpdateStatement(PreparedStatement ps, BaseVO bean) throws Exception { 
 
		HomePageVO vo = (HomePageVO) bean; 
		ps.setString(1, vo.getCaption()); 
		ps.setString(2, vo.getUrl()); 
 
	} 
 
	/** 
	 * 删除首页模板 
	 */ 
	public void delete(BaseVO vo) throws Exception { 
 
		delete(getDeleteSQL()+" where TEMPL_ID="+((HomePageVO) vo).getTempl_id()+""); 
 
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
 
		HomePageVO vo = new HomePageVO(); 
		vo.setTempl_id(rs.getInt("TEMPL_ID")); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setUrl(rs.getString("URL").trim()); 
		return vo; 
 
	} 
 
	/** 
	 * 获取明细信息 
	 */ 
	public BaseVO detail(ResultSet rs) throws Exception { 
 
		HomePageVO vo = new HomePageVO(); 
		vo.setTempl_id(rs.getInt("TEMPL_ID")); 
		vo.setCaption(rs.getString("CAPTION").trim()); 
		vo.setUrl(rs.getString("URL").trim()); 
		return vo; 
 
	} 
 
} 

