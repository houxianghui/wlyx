package com.huateng.blue.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.abc.logic.IbatisBO;
import com.eis.util.DateUtil;
import com.eis.util.Diff;
import com.eis.util.DiffInfo;
import com.projectmaintain.ChangeRecordVO;

public class WorkDistributeBO extends IbatisBO {

	@Override
	public void update(Object obj) throws Exception {
		WorkDistributeForm form = (WorkDistributeForm)obj;
		WorkDistribute wd = new WorkDistribute();
		BeanUtils.copyProperties(wd, form);
		
		ChangeRecordVO vo = new ChangeRecordVO();		
		vo.setProjectId(form.getWorkId());
		vo.setChangeDate(DateUtil.getDTStr());
		vo.setContent(getContent(wd));
		vo.setReason(form.getReason());
		vo.setUserId(form.getInputUser());
		vo.setId(form.getId());
		wd.setWorkStatus("N");		//修改后，任务状态恢复成未完成的正常状态
		
		dao.insert("ProjectMaintain.insertChangeRecord",vo);
		dao.updateByGenerate(namespace, wd);
	}
	private String getContent(WorkDistribute wd)throws Exception{
		WorkDistribute wd2 = (WorkDistribute)queryForObject(wd.getId());
		StringBuffer sb = new StringBuffer();
		List<DiffInfo> l = Diff.diff(wd2, wd, new String[]{"notifyDay","startDate","endDate","content"});
		for(DiffInfo df:l){
			sb.append(df.toString());
		}		
		return sb.toString();
	}
	
	@Override
	public void insert(Object obj) throws Exception {
		dao.insertByGenerate(namespace, obj);
	}
	public void finishMyWork(int id){
		WorkDistribute wd = new WorkDistribute();
		wd.setWorkStatus("F");
		wd.setId(id);
		dao.updateByGenerate(namespace, wd);
	}
	@Override
	public Object queryForObject(Object id) throws Exception {
		WorkDistribute wd = new WorkDistribute();
		
		if(id instanceof String){
			wd.setId((Integer.parseInt((String)id)));
		}else{
			wd.setId((Integer)id);
		}
		
		return dao.queryForObjectByGenerate(namespace, wd);
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		WorkDistributeExample e = new WorkDistributeExample();
		e.createCriteria().andWorkIdEqualTo((String)obj);
		return dao.queryForListByGenerate(namespace, e);
	}
	public void setPageList(HttpServletRequest request)throws Exception{
		String workId = request.getParameter("workId");
		WorkDistributeExample e = new WorkDistributeExample();
		e.createCriteria().andWorkIdEqualTo(workId);
		setPageList(namespace, e, request);
	}
	@Override
	public void delete(Object obj) throws Exception {
		if(obj instanceof String){
			obj = Integer.parseInt((String)obj);
		}
		WorkDistribute wd = new WorkDistribute();
		wd.setId((Integer)obj);
		dao.deleteByGenerate(namespace, wd);
	}
	public List getSelectedStuff(Object obj)throws Exception{
		return dao.queryForList(namespace+".getSelectedStuff",obj);
	}
	public List getNotSelectedStuff(Object obj)throws Exception{
		return dao.queryForList(namespace+".getNotSelectedStuff",obj);
	}
	public void transOperation(Object[] obj) throws Exception {
		if(obj.length  == 0){
			return;
		}
		//delete(obj[0]);
		for(int i = 0;i < obj.length;i++){
			insert(obj[i]);
		}
	}
}
