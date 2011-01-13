package com.abc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import com.abc.exception.PageOutOfIndexException;
import com.eis.base.PageObject;

/**
 * 继承ResultSetExtractor,用于处理ResultSet
 * @author songlijun
 *
 */
public class PageRowMapperResultSetExtractor implements ResultSetExtractor
{
	private final RowMapper rowMapper;
	private PageObject pageObject;
	private final int rowsExpected;

	
	public PageRowMapperResultSetExtractor(RowMapper rowMapper,PageObject pageObject) 
	{
		this(rowMapper,0,pageObject);
		
	}

	/**
	 * Create a new RowMapperResultSetExtractor.
	 * @param rowMapper the RowMapper which creates an object for each row
	 * @param rowsExpected the number of expected rows
	 * @param pageObject
	 * (just used for optimized collection handling)
	 */
	public PageRowMapperResultSetExtractor(RowMapper rowMapper, int rowsExpected,PageObject pageObject) {
		Assert.notNull(rowMapper, "RowMapper is required");
		this.rowMapper = rowMapper;
		this.rowsExpected = rowsExpected;
		this.pageObject=pageObject;
	}


	public Object extractData(ResultSet rs) throws SQLException 
	{
		rs.last();
		pageObject.setTotalRecord(rs.getRow());
		rs.first();
		
		List results = (this.rowsExpected > 0 ? new ArrayList(this.rowsExpected) : new ArrayList());
		
		int curPage=pageObject.getCurPage();
		if(curPage<0||curPage>pageObject.getTotalPage())
			throw new PageOutOfIndexException(curPage,pageObject.getTotalPage());
		if(pageObject.getCurPage()==0)
			pageObject.setCurPage(1);
		
		//开始和终止位置
		int endPosition = pageObject.getEndPosition();
		int startPosition=pageObject.getStartPosition();
		
		//结果集定位
		for (int i=1;i<startPosition;i++)
			rs.next();
		for (int i = startPosition; i <= endPosition; i++) 
		{
			results.add(this.rowMapper.mapRow(rs, 0));
			rs.next();
		}
		return results;
	}
}
