<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.TASK_LISTForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="tASK_LISTForm" scope="request" class="com.work.TASK_LISTForm" /> 
 
<html> 
 
<title>�����б���Ϣ</title> 
 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="TASK_LIST.do"> 
 
<%=ViewUtil.getTitle("�����б���Ϣ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��Ŀ��ţ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=tASK_LISTForm.getProject_no()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;���ڣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=tASK_LISTForm.getTask_date()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��Ŀ�׶Σ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("9994",tASK_LISTForm.getTask_step())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ͣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("9992",tASK_LISTForm.getTask_type())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ʱ��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=tASK_LISTForm.getTask_cost()%> 
         </td> 
       </tr> 
 
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ݣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=tASK_LISTForm.getTask_memo()%> 
         </td> 
       </tr> 
 
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

