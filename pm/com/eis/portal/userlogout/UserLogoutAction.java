package com.eis.portal.userlogout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtilsBean;

import com.eis.base.*;
import com.eis.portal.UserContext;
import com.eis.factory.*;
import com.eis.util.*;
import com.eis.connectionPool.*;
import com.eis.portal.userlogin.*;
import com.eis.portal.oplog.*;

/** 
 * ˵����ǩ�˵Ŀ����� 
 */
public class UserLogoutAction extends Action {

	/** 
	 * ���캯�� 
	 */
	public UserLogoutAction() {
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
			//�ӡ�user���Ự�����л���û������Ķ���UserContext
			UserContext user =
				(UserContext) request.getSession().getAttribute("user");
				
			//�����û�ǩ�˵����ݶ���
			UserLogoutVO vo = new UserLogoutVO();
			//�����û�ǩ�˵�ҵ�����
			UserLogoutBO bo =
				(UserLogoutBO) BeanFactory.getBean("userlogout_bo");
			
			//û�ã�	
			new BeanUtilsBean().copyProperties(vo, form);

			String login_time_detail = DateUtil.getTimeStr(); //���ϵͳʱ��

			//���û������Ķ���UserContext�л��user_id
			String user_id = user.getUserID();

			/**ɾ����¼��¼ep_user_login���е��û���¼��
			 * ͬʱ��¼������־��ep_op_log��
			 * */
			
			//�����û���¼���ݶ���������user_id����Ϊ��¼�û�user_id
			UserLogoutVO userLogoutVO = new UserLogoutVO();
			//UserLoginVO userLogoutVO = new UserLoginVO();
			userLogoutVO.setUser_id(user_id);

			
			/**����������־���ݶ���OpLogVO������ֵ*/
			OpLogVO opLogVO = new OpLogVO();
			//�������¼�ʱ��Ϊϵͳʱ��
			opLogVO.setEvent_time(login_time_detail);
			//�������¼�����
			opLogVO.setEvent_type("01");
			//����
			opLogVO.setOrg_id(user.getOrgID());
			//����ɫ
			opLogVO.setRole_id(user.getRoleID());
			opLogVO.setUser_id(user.getUserID());
			//��������
			opLogVO.setOp_id("logout");
			opLogVO.setMemo("ǩ��");
			//����������Ϊ����
			opLogVO.setOp_key(user.getUserID());

			/**ִ��ҵ������exec(...)����, 
			 * ɾ����¼��¼ep_user_login���е��û���¼��
			 * ͬʱ��¼������־��ep_op_log��*/	
			bo.exec(userLogoutVO, opLogVO);

			//���������˳���־
			request.getSession().setAttribute("normal_exit", "1");

			request.getSession().invalidate();

		} catch (Throwable t) {
			SysLog.error("�û�ǩ��ʧ�ܣ�" + t.getMessage());
		}
		//��ת��ǩ�˳ɹ�ҳ��
		return mapping.findForward("exit");

	}

}