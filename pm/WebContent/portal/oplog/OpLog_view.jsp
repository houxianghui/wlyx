<%@ include file = "/includes/common.jsp" %> 

<%@ page import="com.eis.portal.oplog.OpLogForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="opLogForm" scope="request" class="com.eis.portal.oplog.OpLogForm" /> 
 
<html> 
 
<title>������־��Ϣ</title> 
 
 
<body class="body_bg_grey1"> 
<p>&nbsp;</p> 
 
<html:form method="post" action="OpLog.do"> 

<%=ViewUtil.getTitle("������־��Ϣ")%>
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�û�����</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=opLogForm.getUser_login_id()%> 
         </td> 
       </tr> 
       
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;����������</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=opLogForm.getUser_name()%> 
         </td> 
       </tr> 
        
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�¼�ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=DateUtil.formatDateTime(opLogForm.getEvent_time())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�¼����ͣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("0006",opLogForm.getEvent_type())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;������</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ɫ��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=ReDefSDicMap.getDicItemVal("0002",opLogForm.getRole_id())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ͣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("0005",opLogForm.getOp_id())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ע��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=opLogForm.getMemo()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�ؼ��֣�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=opLogForm.getOp_key()%> 
         </td> 
       </tr> 
  </table> 
   
  <table align="center" width="98%" border="0" cellspacing="1" cellpadding="0">         
        <tr> 
				<td  height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

