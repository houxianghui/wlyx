/*
 * 创建日期 2009-7-29
 *
 * Author Songlijun
 */
package com.abc.service;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.abc.dao.JdbcTemplateHelp;
import com.eis.base.CommonSqlMapDao;

public abstract class DaoService {

	protected JdbcTemplate jdbcTemplate;
	protected CommonSqlMapDao dao;
	

	public JdbcTemplateHelp getJdbcTemplate() {
		return (JdbcTemplateHelp) jdbcTemplate;
	}
	public void setDataSource(DataSource source) {
		jdbcTemplate = new JdbcTemplateHelp(source);
	}
	public void setDao(CommonSqlMapDao dao) {
		this.dao = dao;
	}
	
	public Object queryForObject(String statement, Object obj) {
		return dao.queryForObject(statement, obj);
	}
	public List queryForList(String statement, Object obj) {
		return dao.queryForList(statement, obj);
	}
	public List queryForList(String statement, Object obj, int skip, int max) {
		return dao.queryForList(statement, obj, skip, max);
	}
}
