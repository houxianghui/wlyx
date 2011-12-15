package com.eis.key;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.eis.connectionPool.DBPoolManager;
import com.eis.factory.BeanFactory;
import com.eis.key.pmkey.PmKeyDAO;
import com.eis.key.pmkey.PmKeyVO;
import com.eis.util.SysLog;

/**
 * 说明：
 * 
 */
public class KeyInfo {
	private long keyMax;
	private long nextKey;
	private int poolSize;
	private String keyName;
	private Date lastModified;

	public KeyInfo(String keyName) throws Exception {
		this.keyName = keyName;
		retrieveFromDB();
		lastModified = new Date();
	}

	public long getKeyMax() {
		return keyMax;
	}

	public synchronized long getNextKey() throws Exception {
		if (nextKey > keyMax) {
			SysLog.debug("重新分配步长!");
			SysLog.debug("keyMax = " + keyMax);
			SysLog.debug("nextKey = " + nextKey);
			retrieveFromDB();
		}
		return nextKey++;
	}
	
	public synchronized long getDateNextKey() throws Exception{
		if(lastModified.before(new Date())){
			resetKey();
			return getNextKey();
		}else{
			return getNextKey();
		}
	}

	public synchronized void resetKey() throws Exception {
		SysLog.debug("Reset the key value");
		PmKeyVO vo = new PmKeyVO();
		vo.setKey_field("ID");
		vo.setStep_len(1);
		vo.setTb_name(keyName);
		vo.setMax_val(0L);
		
		PmKeyDAO dao = (PmKeyDAO) BeanFactory.getBean("pmkey_dao");
		dao.update(vo," where TB_NAME='"+ vo.getTb_name()+"'"); 
		retrieveFromDB();
	}
	

	private void retrieveFromDB() throws Exception {
		Connection connts = null;
		Statement stmt = null;
		try {
			connts = DBPoolManager.getConnection();
			connts.setAutoCommit(false);
			stmt = connts.createStatement();
			stmt.executeUpdate("update ep_pmkey set MAX_VAL = MAX_VAL+STEP_LEN where TB_NAME='" + keyName + "'");

			ResultSet rs = stmt.executeQuery("select * from ep_pmkey where TB_NAME='" + keyName + "'");
			if (rs.next()) {
				keyMax = rs.getLong("MAX_VAL");
				poolSize = rs.getInt("STEP_LEN");
				nextKey = keyMax - poolSize + 1;
				lastModified = new Date();
			}
			if (rs != null) {
				rs.close();
			}

			connts.commit();
		} catch (Exception e) {
			connts.rollback();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connts != null) {
				connts.close();
			}
		}

	}
}