package com.huateng.blue.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.abc.logic.IbatisBO;
import com.eis.base.BaseForm;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.exception.MessageException;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.Diff;
import com.eis.util.DiffInfo;
import com.projectmaintain.ChangeRecordVO;
import com.projectmaintain.ProjectMaintainVO;

public class WorkListBO extends IbatisBO {
	public static final String NORMAL = "N";
	public static final String FINISHED = "F";
	public static final String DELETED = "D";
	
	
	@Override
	public void update(Object obj) throws Exception {
		WorkList wl = new WorkList();
		BeanUtils.copyProperties(wl, obj);
		
		ChangeRecordVO vo = new ChangeRecordVO();
		vo.setProjectId(wl.getWorkId());
		vo.setChangeDate(DateUtil.getDTStr());
		vo.setContent(getContent(wl));
		vo.setReason(((WorkListForm)obj).getReason());
		vo.setUserId(wl.getInputUser());
		vo.setId(-1);
		
		dao.insert("ProjectMaintain.insertChangeRecord",vo);		
		dao.updateByGenerate(namespace,wl);
	}
	private String getContent(WorkList wl)throws Exception{
		WorkList wl2 = (WorkList)queryForObject(wl.getWorkId());
		StringBuffer sb = new StringBuffer();
		List<DiffInfo> l = Diff.diff(wl2, wl, new String[]{"workName","startDate","endDate","content"});
		for(DiffInfo df:l){
			sb.append(df.toString());
		}		
		return sb.toString();
	}
	
	@Override
	public void insert(Object obj) throws Exception {
		WorkList wl = (WorkList)obj;
		wl.setWorkStatus(NORMAL);
		wl.setInputDate(DateUtil.getDTStr());
		dao.insertByGenerate(namespace, wl);
	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		WorkList work = new WorkList();
		work.setWorkId((String)obj);
		return dao.queryForObjectByGenerate(namespace,work);
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		return dao.queryForListByGenerate(namespace,(WorkListExample)obj);
	}

	@Override
	public void delete(Object obj) throws Exception {
		WorkList wl = new WorkList();
		wl.setWorkId((String)obj);
		wl.setWorkStatus(DELETED);
		dao.updateByGenerate(namespace, wl);
//		dao.deleteByGenerate(namespace, (String)obj);
	}
	public void queryForPage(HttpServletRequest request,BaseForm form,UserContext user)throws Exception{
		WorkListExample example = new WorkListExample();
		WorkListExample.Criteria c = example.createCriteria();
		if(form.getAct().equals("list")){
			c.andWorkStatusEqualTo(NORMAL);
		}
		if(form.getAct().equals("gmiw")){
			c.andInputUserEqualTo(user.getUserID()).andWorkStatusEqualTo(NORMAL);
		}
		WorkListForm wf = (WorkListForm)form;
		if(!CheckUtil.isEmptry(wf.getWorkId_f())){
			c.andWorkIdEqualTo(wf.getWorkId_f());
		}
		if(!CheckUtil.isEmptry(wf.getWorkStatus())){
			c.andWorkStatusEqualTo(wf.getWorkStatus());
		}
		if(!CheckUtil.isEmptry(wf.getEndDate())){
			String endDate = wf.getEndDate();
			if(!CheckUtil.isEmptry(wf.getDay())){
				endDate = DateUtil.getDateAfter(wf.getEndDate(), Integer.parseInt(wf.getDay()));
			}
			c.andEndDateLessThanOrEqualTo(endDate);
		}
		setPageList(namespace, example, request);
	}
	public void getFinishedWorks(HttpServletRequest request,BaseForm form,UserContext user)throws Exception{
	
		setPageList(namespace+".getDoneWorkDistributesCount",namespace+".getDoneWorkDistributes", user.getUserID(), request);
	}
	public void updateBySelective(Object obj){
		dao.updateByGenerate(namespace,(WorkList)obj);
	}

	public void validateUpdateStatus(String workId,UserContext user)throws Exception{
		WorkList wl = (WorkList)queryForObject(workId);
		if(!user.getUserID().equals(wl.getInputUser())){
			throw new MessageException("您不能对其他人录入的资料进行操作");
		}
	}
	public List getSelectedWorks(String workId){
		return dao.queryForList(namespace+".getSelectedProjects", workId);
	}
	public List getNotSelectedWorks(String workId){
		return dao.queryForList(namespace+".getNotSelectedWorks", workId);
	}
	public void deletePreProjects(Object obj)throws Exception{
		dao.delete("ProjectMaintain.deleteProjectRelation",obj);
	}
	public void transOperation(Object[] obj) throws Exception {
		dao.delete("ProjectMaintain.deleteProjectRelation",obj[0]);
		for(int i=0;i < obj.length;i++){
			dao.insert("ProjectMaintain.insertProjectRelation",obj[i]);
		}
	}
	public void doFinishWork(String workId){
		WorkList wl = new WorkList();
		wl.setWorkId(workId);
		wl.setWorkStatus(FINISHED);
		dao.updateByGenerate(namespace,wl);
	}
	public void setMainWork(String workId,String isMain){
		WorkList wl = new WorkList();
		wl.setWorkId(workId);
		if("N".equals(isMain)){
			wl.setIsMain("Y");
		}else{
			wl.setIsMain("N");
		}
		dao.updateByGenerate(namespace, wl);
	}
}
