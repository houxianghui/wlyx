/*
 * 创建日期 2009-4-14
 */
package com.eis.base;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.StringUtils;
/**
 * @author zhangtao
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class JdbcDaoHelper extends JdbcDaoSupport {
	
	/**
	 * 根据查询检索数据库对象
	 * @param sql
	 * @param mapper 自定义的RowMapper
	 * @param args
	 * @return
	 */
	public Object queryForObject(String sql,RowMapper mapper, Object[] args) {
		return this.getJdbcTemplate().queryForObject(sql,args,mapper);
	}
	
	/**
	 * 根据查询条件检索数据库对象
	 * @param sql
	 * @param entityClass  
	 * @param args
	 * @return
	 */
	public Object queryForObject(String sql,Class entityClass,Object[] args) {		
		return queryForObject(sql,new SimpleParameterizedRowMapper(sql,entityClass), args);		
	}
	
	/**
	 * 查询返回list
	 * @param sql
	 * @param entityClass
	 * @param args
	 * @return
	 * 备注：entityClass的对象必须与数据库保持一直
	 */
	public List queryForList(String sql,Class entityClass,Object[] args){
		return this.queryForList(sql,args,new ListParameterizedRowMapper(sql,entityClass));
	}
	
	public List queryForList(String sql,Object[] args,RowMapper mapper){
		return this.getJdbcTemplate().query(sql,args,mapper);
	}
	
	
	class ListParameterizedRowMapper extends BeanPropertyRowMapper {
			private Class entityClass;
			private Field[] fields;
			private ResultSetMetaData rsmd = null;
			private List rsColumns = new ArrayList();
			private Map customerField = new HashMap();
			ListParameterizedRowMapper(String sql, Class entityClass) {
				this.entityClass = entityClass;
				fields = entityClass.getDeclaredFields();
				sql = sql.toUpperCase();
				String select = sql.substring(sql.indexOf("SELECT") + 7, sql.indexOf("FROM")).trim();
				String[] selectColumn = StringUtils.delimitedListToStringArray(select,",");
				for (int i = 0; i < selectColumn.length; i++) {
					String[] customer = StringUtils.delimitedListToStringArray(selectColumn[i],"\\p{Blank}");
					if (customer.length == 2) {
						customerField.put(customer[1].trim().toUpperCase(), customer[1].trim());
					}
				}
			}
			private Object newEntityInstance() {
				try {
					return Class.forName(entityClass.getName()).newInstance();
				}
				catch (Exception e) {
				}
				return null;
			}			
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
							
				if (rsmd == null) {
					rsmd = rs.getMetaData();
					for (int column = 1; column <= rsmd.getColumnCount(); column++) {
						String columnName = rsmd.getColumnName(column);
						rsColumns.add(columnName.toUpperCase());
					}
				}				
								
				Object entity = newEntityInstance();
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].getName();
					Object value = null;
					try {
						value = rs.getObject(fieldName);
					}
					catch (Exception e) {
					}
					try {
						if (rsColumns.contains(fieldName.toUpperCase()))
							BeanUtils.setDeclaredProperty(entity, fieldName, value);
						
					}
					catch (IllegalAccessException e) {
					}
					catch (NoSuchFieldException e) {
					}
				}
				
				return entity;
			}
		}
		
		
	
	class SimpleParameterizedRowMapper extends BeanPropertyRowMapper {
		private Class entityClass;
		private Field[] fields;
		private ResultSetMetaData rsmd = null;
		private List rsColumns = new ArrayList();
		private Map customerField = new HashMap();
		SimpleParameterizedRowMapper(String sql, Class entityClass) {
			this.entityClass = entityClass;
			fields = entityClass.getDeclaredFields();
			sql = sql.toUpperCase();
			String select = sql.substring(sql.indexOf("SELECT") + 7, sql.indexOf("FROM")).trim();
			String[] selectColumn = StringUtils.delimitedListToStringArray(select,",");
			for (int i = 0; i < selectColumn.length; i++) {
				String[] customer = StringUtils.delimitedListToStringArray(selectColumn[i],"\\p{Blank}");
				if (customer.length == 2) {
					customerField.put(customer[1].trim().toUpperCase(), customer[1].trim());
				}
			}
		}
		private Object newEntityInstance() {
			try {
				return Class.forName(entityClass.getName()).newInstance();
			}
			catch (Exception e) {
			}
			return null;
		}			
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Object entity = newEntityInstance();
			if (rsmd == null) {
				rsmd = rs.getMetaData();
				for (int column = 1; column <= rsmd.getColumnCount(); column++) {
					String columnName = rsmd.getColumnName(column);
					rsColumns.add(columnName.toUpperCase());
				}
			}
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				Object value = null;
				try {
					value = rs.getObject(fieldName);
				}
				catch (Exception e) {
				}
				try {
					if (rsColumns.contains(fieldName.toUpperCase()))
						BeanUtils.setDeclaredProperty(entity, fieldName, value);
				}
				catch (IllegalAccessException e) {
				}
				catch (NoSuchFieldException e) {
				}
			}
			return entity;
		}
	}
}
