package com.huateng.blue.work;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.abc.action.IbatisAction;
import com.eis.base.BaseForm;
import com.eis.portal.UserContext;

public class SignRecordAction extends IbatisAction {

	@Override
	public ActionForward process(ActionMapping mapping, BaseForm form,
			HttpServletRequest request, HttpServletResponse response,
			UserContext user) throws Exception {
		String act = getAct(form, request);
		request.setAttribute("act", act);
		if("signoff".equals(act)){
			return signOff(mapping, request, user);
		}
		if("list".equals(act)){
			return list(mapping,request,form,user);
		}
		if("info".equals(act)){
			return info(mapping,request,user);
		}
		if("new".equals(act)){
			return mapping.findForward("new");
		}
		if("add".equals(act)){
			return add(mapping,request,form,user);
		}
		if("edit".equals(act)){
			return edit(mapping,request,form);
		}
		if("update".equals(act)){
			return update(mapping, request, form, user);
		}
		if("delete".equals(act)){
			return delete(mapping, request, form, user);
		}
		return null;
	}
	private ActionForward signOff(ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((SignRecordBO)bo).signOff(user);
		return forwardSuccessPage(request, mapping, "签退成功",getInfoUrl());
	}
	private ActionForward list(ActionMapping mapping,HttpServletRequest request,BaseForm form,UserContext user)throws Exception{
		((SignRecordBO)bo).setSignListInfo(user, request,form);
		return mapping.findForward("list");
	}
	private ActionForward info(ActionMapping mapping,HttpServletRequest request,UserContext user)throws Exception{
		((SignRecordBO)bo).setMySignListInfo(user, request);
		return mapping.findForward("list");
	}
	private ActionForward add(ActionMapping mapping,HttpServletRequest request,BaseForm form,UserContext user)throws Exception{
		SignRecord sr = new SignRecord();
		copyProperties(sr, form);
		sr.setIsModified("Y");
		sr.setModifyUser(user.getUserID());
		bo.insert(sr);
		return forwardSuccessPage(request, mapping, "添加成功",getListUrl());
	}
	private String getListUrl(){
		return "SignRecord.do?act=list";
	}
	private String getInfoUrl(){
		return "SignRecord.do?act=info";
	}
	private ActionForward edit(ActionMapping mapping,HttpServletRequest request,BaseForm form)throws Exception{
		SignRecordKey key = new SignRecordKey();
		copyProperties(key, form);
		SignRecord sr = (SignRecord)bo.queryForObject(key);
		copyProperties(form, sr);
		return mapping.findForward("edit");
	}
	private ActionForward update(ActionMapping mapping,HttpServletRequest request,BaseForm form,UserContext user)throws Exception{
		SignRecord sr = new SignRecord();
		copyProperties(sr, form);
		sr.setIsModified("Y");
		sr.setModifyUser(user.getUserID());
		bo.update(sr);
		return forwardSuccessPage(request, mapping, "修改成功",getListUrl());
	}
	private ActionForward delete(ActionMapping mapping,HttpServletRequest request,BaseForm form,UserContext user)throws Exception{
		SignRecordKey key = new SignRecordKey();
		copyProperties(key, form);
		bo.delete(key);
		return forwardSuccessPage(request, mapping, "删除成功",getListUrl());
	} 
}
