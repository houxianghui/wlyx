<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.PROJECT_LISTForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pROJECT_LISTForm" scope="request" class="com.work.PROJECT_LISTForm" /> 
 
<html> 
 
<title>��Ŀ��Ϣ</title> 
 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="PROJECT_LIST.do"> 
 
<%=ViewUtil.getTitle("��Ŀ��Ϣ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp��Ŀ��ţ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getProject_no()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��Ŀ���ƣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getProject_name()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ǰ�׶Σ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("9994",pROJECT_LISTForm.getCurr_step())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ʼ���ڣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getStart_date()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ڣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pROJECT_LISTForm.getEnd_date()%> 
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
 

