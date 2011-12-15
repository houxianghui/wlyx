/*********************************************************
 * File: LoginAction.java
 * 
 * Version 1.0
 * 
 * Date     2005-9-19
 * 
 * Author   lihaibao
 * 
 ********************************************************/

package com.eis.portal.login;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.eis.base.BaseDAO;
import com.eis.base.BaseForm;
import com.eis.cache.ReDefSDicMap;
import com.eis.exception.MessageException;
import com.eis.factory.BeanFactory;
import com.eis.portal.UserContext;
import com.eis.portal.oplog.OpLogVO;
import com.eis.portal.user.UserBO;
import com.eis.portal.user.UserRoleVO;
import com.eis.portal.userlogin.UserLoginDAO;
import com.eis.portal.userlogin.UserLoginVO;
import com.eis.util.DateUtil;
import com.eis.util.MD5Util;
import com.eis.util.SysLog;
import com.huateng.blue.work.SignRecordBO;

/**
 * ˵����
 * 
 */
public class LoginAction extends Action {
	
	private SignRecordBO signRecordBO;

	/**
	 * ���캯�� 
	 */
	public LoginAction() {
		super();

	}

	/** 
	* ִ�������� 
	*/
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		try {
			HttpSession session = request.getSession();

			session.setMaxInactiveInterval(3600);
			LoginVO vo = new LoginVO();

			new BeanUtilsBean().copyProperties(vo, form);

			//�洢�ͻ��˷ֱ���					
			String pixel = request.getParameter("PIXEL");			
			session.setAttribute("PIXEL", pixel);

			//���			
			String checknum = (String) session.getAttribute("checknum");
			String num = request.getParameter("random");
			

			validateLoginId(vo.getLogin_id());

			if (checknum != null && checknum.equals(num)) {

				//����
				String password = MD5Util.digest(vo.getPassword());

				//���ݿ��ѯ��֤
				//���ҵ����� 
				LoginBO bo = (LoginBO) BeanFactory.getBean("login_bo");

				LoginVO userVO =
					(LoginVO) bo.retrieve(
						" where LOGIN_ID='"
							+ vo.getLogin_id()
							+ "' and PASSWORD='"
							+ password
							+ "'",
						null);
				

				if (userVO == null
					|| userVO.getUser_id().trim().length() <= 0) {
					//û�м�¼
					return forwardError(request, mapping, "�û������������!");

				}
				if(!password.equals(userVO.getPassword())){
					return forwardError(request,mapping,"�û������������!");
				}
				String user_id = userVO.getUser_id().trim();
				String login_time = DateUtil.getTimeStr(); //ȡ��ϵͳʱ��

				//����û�״̬
				String st = userVO.getStatus().trim(); //ȡ���û�״̬

				if (st.equals("2")) {
					return forwardError(
						request,
						mapping,
						"���û�����ͣ��״̬�������ú���ܵ�¼��");
				}
				if (st.equals("0")) {
					return forwardError(request, mapping, "���û���������״̬�����ܵ�¼��");
				}

				//����û��Ƿ����
				//String expire_dt = userVO.getInvalid_dt(); //ȡ���û�ʧЧ����

				int expire_dt =
					java.lang.Integer.parseInt(userVO.getInvalid_dt());
				int begin_dt = 
					java.lang.Integer.parseInt(userVO.getBegin_dt());//ȡ���û���ʼ����

				String login_dt = login_time.substring(0, 8); //ȡ��ϵͳʱ��

				if (expire_dt < Integer.parseInt(login_dt) || begin_dt > Integer.parseInt(login_dt)) {

					return this.forwardError(
						request,
						mapping,
						"���������Ч������ϵͳ����Ա��ϵ��");
				}

				//��USERCONTEXT����ֵ
				UserContext user = new UserContext();
				//���û����				
				user.setUserID(userVO.getUser_id());
				session.setAttribute("user", user);

				//��������Ƿ���ϵͳĬ������00000000�����ǣ���ת�������޸�ҳ��
				if (password.equals(MD5Util.digest("00000000"))) {
					session.setAttribute("user_id", userVO.getUser_id());
					return forwardChangePassword(
						request,
						mapping,
						"��������ΪĬ��ֵ�������޸����룡");
				}

				//��������Ƿ����
				String modify_dt = userVO.getModify_dt(); //ȡ������ʧЧ����

				int modify_dt_int = java.lang.Integer.parseInt(modify_dt);

				int login_time_int = java.lang.Integer.parseInt(login_dt);

				if (login_time_int > modify_dt_int) {
					session.setAttribute("user_id", userVO.getUser_id());
					return forwardChangePassword(
						request,
						mapping,
						"���������ѹ��ڣ������޸����룡");
				}

				//���û�����
				user.setUserName(userVO.getUser_name());

				//���û����
				user.setUserID(user_id);

				//����½���
				user.setLoginID(userVO.getLogin_id());

				//����������
				user.setOrgID(userVO.getAmsd_store());


				//������ɫ���
				user.setRoleID(userVO.getRole_id());

				//������ɫ����
				
				user.setRoleName(
					ReDefSDicMap.getDicItemVal("0002", userVO.getRole_id()));				
						
				
				//����ɫ���	

				//��ȡUserContext����Ϊuser���û�����ѡ��ɫ�б�

				UserBO userbo = (UserBO) BeanFactory.getBean("user_bo");
				List roleslist = userbo.queryRolesList(user);

				int length = 0; //��ɫ�б��ȱ���	
				String[] roles = null; //�洢��ɫ�����б�ı���		

				if (roleslist != null) {
					Iterator iter = roleslist.iterator();
					while (iter.hasNext()) {
						iter.next();
						length = length + 1;
					}

					//��������Ϊlength������
					roles = new String[length];

					//����ɫ�б��еĽ�ɫ��Ŵ�������roles
					iter = roleslist.iterator();

					int i = 0;
					while (iter.hasNext()) {
						roles[i] = ((UserRoleVO) (iter.next())).getRole_id();
						SysLog.debug("��ɫ��" + roles[i]);
						i++;
					}

				}

				user.setRole(roles);

				session.setAttribute("user", user);
				//ǩ��
				signRecordBO.signOn(user);
				
				//��¼��¼��־��ǩ����Ϣ,�����������			

				//��¼ǩ����Ϣ
				UserLoginVO userLoginVO = new UserLoginVO();
				UserLoginDAO userLoginDAO =
					(UserLoginDAO) getBean("userlogin_dao");

				userLoginVO.setUser_id(user_id);
				userLoginVO.setLogin_time(login_time);
				userLoginVO.setClient_ip(request.getRemoteAddr());

////				UserLoginVO vo1 =
////					(UserLoginVO) userLoginDAO.retrieve(
////						userLoginDAO.getQuerySQL()
////							+ " where USER_ID='"
////							+ userLoginVO.getUser_id()
////							+ "'");
////				//��֤�Ƿ��ظ���¼
////				if (vo1 != null) {
////					return forwardError(request, mapping, "�����ظ���¼��");
////				}

				//��¼������־

				OpLogVO opLogVO = new OpLogVO();
				
				opLogVO.setEvent_time(login_time);
				opLogVO.setEvent_type("01");
				opLogVO.setOrg_id(user.getOrgID());
				opLogVO.setRole_id(user.getRoleID());
				opLogVO.setUser_id(user.getUserID());
				opLogVO.setOp_id("login");
				opLogVO.setMemo(request.getRemoteAddr()+"��¼");//opLogVO.setMemo("��¼");
				opLogVO.setOp_key(user.getUserID());
				
				
//				bo.add(userLoginVO, opLogVO, user);

				//���ݽ�ɫģ�������ҳ
				BaseDAO homepageDAO = (BaseDAO) getBean("homepage_dao");
				
				String homepage =
					homepageDAO.querySingle(
						"select URL from ep_homepage where TEMPL_ID in (select TEMPL_ID from ep_role where ROLE_ID='"
							+ user.getRoleID()
							+ "')");

				if (homepage == null || homepage.trim().length() <= 0) {
					session.setAttribute("homepage", "/blank.htm");
				} else {
					session.setAttribute("homepage", homepage);
				}

				//���ص�Ĭ����ҳ	
				return new ActionRedirect("/main.jsp");
				//return mapping.findForward("default_homepage");

			} else {
				return forwardError(request, mapping, "�Ƿ���¼!");
			}

		} catch(MessageException e){
			throw e;
		}catch (Throwable t) {
			t.printStackTrace();
			SysLog.debug("��¼�������");
			SysLog.error(t.getMessage());

		}
		return null;

	}

	/** 
	 * ��ѯ��ϸ��Ϣ 
	 */
	public boolean retrieve(
		ActionMapping mapping,
		BaseForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		UserContext user)
		throws Exception {

		return false;
	}
	private void validateLoginId(String s)throws Exception{
		if(s == null || s.trim().length() == 0){
			throw new MessageException("�������û���");
		}
		char[] c = s.trim().toCharArray();
		for(int i = 0;i<c.length;i++){
			if(c[i] == ' '){
				throw new MessageException("�û������ܰ����ո�");
			}
		}
	}

	/**
	 * ת�򵽴�����Ϣ��ʾҳ��
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 * 
	 */
	public ActionForward forwardError(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("default_error"));
	}

	/**
	 * ת�������޸���Ϣ��ʾҳ��
	 * @param request
	 * @param mapping
	 * @param message
	 * @return ActionForward
	 * 
	 */
	public ActionForward forwardChangePassword(
		HttpServletRequest request,
		ActionMapping mapping,
		String message) {

		request.setAttribute("message", message);

		return (mapping.findForward("chgpwd"));
	}

	/**
	 * ����bean ID���ʵ������
	 * @param name - bean ID	 
	 * @return bean��ʵ������
	 *
	 */
	public Object getBean(String name) throws Exception {

		return BeanFactory.getBean(name);
	}

	public SignRecordBO getSignRecordBO() {
		return signRecordBO;
	}

	public void setSignRecordBO(SignRecordBO signRecordBO) {
		this.signRecordBO = signRecordBO;
	}
	
	

	

	
}
