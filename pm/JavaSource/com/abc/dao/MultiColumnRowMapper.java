package com.abc.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.StringUtils;

import com.abc.exception.BeanProcessException;


/**
 * 实现multiColumn的映射,用于处理resultSet的一行
 * @author songlijun
 *
 */
public class MultiColumnRowMapper extends BeanPropertyRowMapper 
{
	private Class entityClass;
	private String sql;
	private Field[] fields;

	public MultiColumnRowMapper(String sql, Class entityClass) 
	{
		this.entityClass = entityClass;
		fields = entityClass.getDeclaredFields();
		this.sql=sql;
	}

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		List rsColumns = new ArrayList();//结果集中所有的列
		List customerColumns = new ArrayList();//用户sql中select的列

		//设置结果集列
		for (int column = 1; column <= rsmd.getColumnCount(); column++) 
		{
			String columnName = rsmd.getColumnName(column);
			rsColumns.add(columnName.toUpperCase());
		}

		//根据用户sql来设置客户所需要的列
		sql = sql.toUpperCase();
		String select = sql.substring(sql.indexOf("SELECT") + 7, sql.indexOf("FROM")).trim();
		if(select.equals("*"))
		{
			for (int column = 1; column <= rsmd.getColumnCount(); column++) 
			{
				String columnName = rsmd.getColumnName(column);
				customerColumns.add(columnName.toUpperCase());
			}
		}
		else
		{
			String[] selectColumn = StringUtils.delimitedListToStringArray(select,",");
			for (int i = 0; i < selectColumn.length; i++) 
			{
				customerColumns.add(fetchCustomerColumnsFromSelectColumn(selectColumn[i]));
			}
		}

		Object entity = newEntityInstance(entityClass);

		//为客户所需要的列从结果集ResultSet中设置value
		for (int i = 0; i < fields.length; i++) 
		{
			String fieldName = fields[i].getName();
	
			if(customerColumns.contains(fieldName.toUpperCase()))
			{
				Object value=null;
				if (rsColumns.contains(fieldName.toUpperCase()))
				{
					value=rs.getObject(fieldName);					
					//这里不用返回value的具体类型
					//int index=rs.findColumn(fieldName.toUpperCase());
					//value=getColumnValue(rs,index,rsmd.getColumnClassName(index).getClass());
				}
				BeanUtils.setDeclaredProperty(entity, fieldName, value);
			}
		}
		return entity;
	}
	
	protected String fetchCustomerColumnsFromSelectColumn(String selectColumn)
	{
		String customerColumn="";
		if(selectColumn.lastIndexOf("AS")!=-1)
		{
			customerColumn=selectColumn.substring(selectColumn.lastIndexOf("AS")+2).trim();
		}
		else
		{
			String[] temp=StringUtils.delimitedListToStringArray(selectColumn,".");
			if(temp.length==2)
				customerColumn=temp[1].trim().toUpperCase();
			else
				customerColumn=selectColumn.toUpperCase();
		}
		return customerColumn;
	}


	protected static Object newEntityInstance(Class entityClass)
	{
		try 
		{
			return Class.forName(entityClass.getName()).newInstance();
		} 
		catch (InstantiationException e) 
		{
			throw new BeanProcessException(entityClass,e);
		} 
		catch (IllegalAccessException e) 
		{
			throw new BeanProcessException(entityClass,e);
		}
		catch (ClassNotFoundException e) 
		{
			throw new BeanProcessException(entityClass,e);
		}
	}
}