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
 * ��չJdbcTemplate,����������ѯ����,��������exQ��ͷ
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
	 * ���ݲ�ѯ�����������ݿ����
	 * ��չ��jdbcTemplate��queryForObject(String sql, Object[] args, Class requiredType)����
	 * JdbcTemplate��ԭ����ʹ����SingleColumnRowMapper,�������ڷ���Java�Դ����͵Ķ���,��String��
	 * ���������Զ�������
	 * @param sql Ҫִ�е�sql���
	 * @param entityClass  ���ؽ����ʵ������
	 * @param args sql�еĲ�ѯ�кͲ���ֵ
	 * ��ע��requiredType�Ķ������Ʊ��������ݿ����е����Ʊ���һֱ(��Сд����ν),�����һ����Ҫ�Զ���RowMapper
	 * ʹ��exQueryForEntityObject(String sql, Map args, RowMapper rowMapper)����
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
	 * ���ݲ�ѯ�����������ݿ����з������������list
	 * ������jdbcTemplate��queryForList(String sql, Object[] args, Class elementType)����
	 * JdbcTemplate��ԭ����ʹ����SingleColumnRowMapper,�������ڷ���Java�Դ����͵Ķ���,��String��
	 * ���������Զ�������
	 * @param sql Ҫִ�е�sql���
	 * @param entityClass  ���ؽ����ʵ������
	 * @param args sql�еĲ�ѯ�кͲ���ֵ
	 * @return
	 * ��ע��entityClass�Ķ�����������ݿⱣ��һֱ,�����һ����Ҫ�Զ���RowMapper
	 * ʹ��exQueryForEntityList(String sql, Map args, RowMapper rowMapper)����
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
	 * ���ݲ�ѯ�����������ݿ�������������һҳ����
	 * ������jdbcTemplate��queryForList(String sql, Object[] args, Class elementType)����
	 * JdbcTemplate��ԭ����ʹ����SingleColumnRowMapper,�������ڷ���Java�Դ����͵Ķ���,��String��
	 * ���������Զ�������
	 * @param pageObject ��ҳ����
	 * @param sql Ҫִ�е�sql���
	 * @param entityClass  ���ؽ����ʵ������
	 * @param args sql�еĲ�ѯ�кͲ���ֵ
	 * @return
	 * @throws SQLException
	 * ��ע��entityClass�Ķ������Ʊ��������ݿ��е����Ʊ���һ��(��С������ν),�����һ����Ҫ�Զ���RowMapper
	 * ʹ��exQueryForPageList(PageObject pageObject,String sql,Map args,RowMapper rowMapper)����
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
	 * ���ݲ�ѯ�����������ݿ�������������һҳ����
	 * ������entityClass�Ķ�������ݿⲻһ�µ����
	 * @param pageObject ��ҳ����
	 * @param sql Ҫִ�е�sql���
	 * @param args sql�еĲ���
	 * @param rowMapper �Զ����rowMapper
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
	 * ��ѯ�б�ʱʹ���Զ����statement: ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE
	 */
	public Object query(String sql, PreparedStatementSetter pss, ResultSetExtractor rse) throws DataAccessException 
	{
		return query(new MySimplePreparedStatementCreator(sql), pss, rse);
	}
	
	/**
	 * ���jdbcTemplate��queryForObject����û�н�����쳣����
	 */
	public Object queryForObject(String sql, RowMapper rowMapper) throws DataAccessException {
		List results = query(sql, rowMapper);
		return DataAccessUtils.singleResult(results);
	}
	/**
	 * ���jdbcTemplate��queryForObject����û�н�����쳣����
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
		result.add(sb.toString());//��sql�ŵ����������
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
