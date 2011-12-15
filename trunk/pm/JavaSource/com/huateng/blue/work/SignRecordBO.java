package com.huateng.blue.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.abc.logic.IbatisBO;
import com.eis.base.BaseForm;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;

public class SignRecordBO extends IbatisBO {
	public static final String SIGN_ON = "I";
	public static final String SIGN_OFF = "O";
	
	@Override
	public void update(Object obj) throws Exception {
		dao.updateByGenerate(namespace, obj);

	}

	@Override
	public void insert(Object obj) throws Exception {
		dao.insertByGenerate(namespace, obj);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		
		return dao.queryForObjectByGenerate(namespace, obj);
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		return dao.queryForListByGenerate(namespace, obj);
	}
	public void setMySignListInfo(UserContext user,HttpServletRequest request)throws Exception{
		SignRecordExample example = new SignRecordExample();
		
		SignRecordExample.Criteria c = example.createCriteria();
		c.andUserIdEqualTo(user.getUserID());
		
		example.setOrderByClause(" RECORD_DATE desc,USER_ID");
		setPageList(namespace, example, request);
	}
	public void setSignListInfo(UserContext user,HttpServletRequest request,BaseForm form)throws Exception{
		SignRecordExample example = new SignRecordExample();
		SignRecordExample.Criteria c = example.createCriteria();
		SignRecordForm sf = (SignRecordForm)form;
		if(!CheckUtil.isEmptry(sf.getDate_f())){
			c.andRecordDateEqualTo(sf.getDate_f());
		}
		if(!CheckUtil.isEmptry(sf.getUserId_f())){
			c.andUserIdEqualTo(sf.getUserId_f());
		}
		example.setOrderByClause(" RECORD_DATE desc,USER_ID");
		setPageList(namespace, example, request);
	}
	@Override
	public void delete(Object obj) throws Exception {
		dao.deleteByGenerate(namespace, obj);
	}
	public void signOn(UserContext user)throws Exception{
		SignRecordKey srk = new SignRecordKey();
		srk.setRecordDate(DateUtil.getDTStr());
		srk.setRecordType(SIGN_ON);
		srk.setUserId(user.getUserID());
		Object o = queryForObject(srk);
		if(o != null){
			return;
		}
		SignRecord sr = makeSignRecord(SIGN_ON, user);
		insert(sr);
	}
	public void signOff(UserContext user)throws Exception{
		SignRecordKey srk = new SignRecordKey();
		srk.setRecordDate(DateUtil.getDTStr());
		srk.setRecordType(SIGN_OFF);
		srk.setUserId(user.getUserID());
		Object o = queryForObject(srk);
		if(o != null){
			return;
		}
		SignRecord sr = makeSignRecord(SIGN_OFF, user);
		insert(sr);
	}
	private SignRecord makeSignRecord(String actType,UserContext user){
		SignRecord sr = new SignRecord();
		sr.setRecordDate(DateUtil.getDTStr());
		sr.setRecordTime(DateUtil.getTimeOnly());
		sr.setUserId(user.getUserID());
		sr.setRecordType(actType);
		return sr;
	}
}
