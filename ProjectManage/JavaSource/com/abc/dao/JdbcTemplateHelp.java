package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.util.Assert;

import com.eis.base.PageObject;


/**
 * 扩展JdbcTemplate,新增几个查询方法,方法名以exQ开头
 * @author songlijun
 *
 */
public class JdbcTemplateHelp extends JdbcTemplate
{
	
	public JdbcTemplateHelp(){}
	
	public JdbcTemplateHelp(DataSource dataSource)
	{
		super(dataSource);
	}

	public JdbcTemplateHelp(DataSource dataSource, boolean lazyInit) 
	{
		super(dataSource,lazyInit);
	}

	/**
	 * 根据查询条件检索数据库对象
	 * 扩展了jdbcTemplate的queryForObject(String sql, Object[] args, Class requiredType)方法
	 * JdbcTemplate的原方法使用了SingleColumnRowMapper,仅适用于返回Java自带类型的对象,如String等
	 * 不适用于自定义类型
	 * @param sql 要执行的sql语句
	 * @param entityClass  返回结果的实例类型
	 * @param args sql中的查询列和参数值
	 * 备注：requiredType的对象名称必须与数据库中列的名称保持一直(大小写无所谓),如果不一致需要自定义RowMapper
	 * 使用exQueryForEntityObject(String sql, Map args, RowMapper rowMapper)方法
	 */
	public Object exQueryForEntityObject(String sql, Map args, Class requiredType) throws DataAccessException
	{		
		List result=verifySqlByArgs(sql,args);
		sql=(String) result.get(0);
		Object[] arg=(Object[]) result.get(1);
	
		return this.queryForObject(sql,arg,new MultiColumnRowMapper(sql,requiredType));		
	}

	public Object exQueryForEntityObject(String sql, Map args, RowMapper rowMapper) throws DataAccessException
	{
		List result=verifySqlByArgs(sql,args);
		sql=(String) result.get(0);
		Object[] arg=(Object[]) result.get(1);
		return this.queryForObject(sql, arg, rowMapper);

	}

	/**
	 * 根据查询条件返回数据库所有符合条件对象的list
	 * 补充了jdbcTemplate的queryForList(String sql, Object[] args, Class elementType)方法
	 * JdbcTemplate的原方法使用了SingleColumnRowMapper,仅适用于返回Java自带类型的对象,如String等
	 * 不适用于自定义类型
	 * @param sql 要执行的sql语句
	 * @param entityClass  返回结果的实例类型
	 * @param args sql中的查询列和参数值
	 * @return
	 * 备注：entityClass的对象必须与数据库保持一直,如果不一致需要自定义RowMapper
	 * 使用exQueryForEntityList(String sql, Map args, RowMapper rowMapper)方法
	 */
	public List exQueryForEntityList(String sql, Map args, Class elementType) throws DataAccessException
	{
		List result=verifySqlByArgs(sql,args);
		sql=(String) result.get(0);
		Object[] arg=(Object[]) result.get(1);
		return this.query(sql,arg,new MultiColumnRowMapper(sql,elementType));
	}

	public List exQueryForEntityList(String sql, Map args,RowMapper rowMapper) throws DataAccessException
	{
		List result=verifySqlByArgs(sql,args);
		sql=(String) result.get(0);
		Object[] arg=(Object[]) result.get(1);
		return this.query(sql,arg,rowMapper);
	}



	/**
	 * 根据查询条件返回数据库符合条件对象的一页数据
	 * 补充了jdbcTemplate的queryForList(String sql, Object[] args, Class elementType)方法
	 * JdbcTemplate的原方法使用了SingleColumnRowMapper,仅适用于返回Java自带类型的对象,如String等
	 * 不适用于自定义类型
	 * @param pageObject 分页对象
	 * @param sql 要执行的sql语句
	 * @param entityClass  返回结果的实例类型
	 * @param args sql中的查询列和参数值
	 * @return
	 * @throws SQLException
	 * 备注：entityClass的对象名称必须与数据库列的名称保持一致(大小字无所谓),如果不一致需要自定义RowMapper
	 * 使用exQueryForPageList(PageObject pageObject,String sql,Map args,RowMapper rowMapper)方法
	 */
	public PageObject exQueryForPageList(PageObject pageObject,String sql,Map args,Class entityClass) throws SQLException
	{
		List result=verifySqlByArgs(sql,args);
		sql=(String) result.get(0);
		Object[] arg=(Object[]) result.get(1);
		System.out.println(sql);
		pageObject.setList((List)this.query(sql, arg, new PageRowMapperResultSetExtractor(new MultiColumnRowMapper(sql,entityClass),pageObject)));
		return pageObject;
	}

	/**
	 * 根据查询条件返回数据库符合条件对象的一页数据
	 * 适用于entityClass的对象和数据库不一致的情况
	 * @param pageObject 分页对象
	 * @param sql 要执行的sql语句
	 * @param args sql中的参数
	 * @param rowMapper 自定义的rowMapper
	 * @return
	 * @throws SQLException
	 */
	public PageObject exQueryForPageList(PageObject pageObject,String sql,Map args,RowMapper rowMapper) throws SQLException
	{
		List result=verifySqlByArgs(sql,args);
		sql=(String) result.get(0);
		Object[] arg=(Object[]) result.get(1);
		pageObject.setList((List)this.query(sql, arg, new PageRowMapperResultSetExtractor(rowMapper,pageObject)));
		return pageObject;
	}
	
	/**
	 * 查询列表时使用自定义的statement: ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE
	 */
	public Object query(String sql, PreparedStatementSetter pss, ResultSetExtractor rse) throws DataAccessException 
	{
		return query(new MySimplePreparedStatementCreator(sql), pss, rse);
	}
	
	/**
	 * 解决jdbcTemplate的queryForObject方法没有结果报异常问题
	 */
	public Object queryForObject(String sql, RowMapper rowMapper) throws DataAccessException {
		List results = query(sql, rowMapper);
		return DataAccessUtils.singleResult(results);
	}
	/**
	 * 解决jdbcTemplate的queryForObject方法没有结果报异常问题
	 */
	public Object queryForObject(String sql, Object[] args, RowMapper rowMapper) throws DataAccessException {
		List results = query(sql, args, rowMapper);
		return DataAccessUtils.singleResult(results);
	}


	private List verifySqlByArgs(String sql,Map args)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(sql);
		if(sql.indexOf("where")==-1)
			sb.append(" where 1=1 ");
		String column="";
		Object value=null;
		List result=new ArrayList();
		List list=new ArrayList();
		Iterator it=args.keySet().iterator();
		while(it.hasNext())
		{
			column=(String)it.next();
			value=args.get(column);
			if(value!=null&&!value.toString().equalsIgnoreCase(""))
			{
				sb.append(" and "+column.toUpperCase()+" ? ");
				list.add(value);


			}
		}
		result.add(sb.toString());//把sql放到结果链表中
		Object[] arg=new Object[list.size()];
		for(int i=0;i<list.size();i++)
		{
			arg[i]=list.get(i);
		}
		result.add(arg);
		return result;
	}

	
	/**
	 * Simple adapter for PreparedStatementCreator, allowing to use a plain SQL statement.
	 */
	private static class MySimplePreparedStatementCreator implements PreparedStatementCreator, SqlProvider 
	{

		private final String sql;

		public MySimplePreparedStatementCreator(String sql) 
		{
			Assert.notNull(sql, "SQL must not be null");
			this.sql = sql;
		}

		public PreparedStatement createPreparedStatement(Connection con) throws SQLException 
		{
			return con.prepareStatement(this.sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}

		public String getSql()
		{
			return this.sql;
		}
	}
}
