<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.redefsdic.ReDefSDicForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="reDefSDicForm" scope="request" class="com.eis.dic.redefsdic.ReDefSDicForm" /> 
 
<html> 
 
<title>�Զ��嵥���ֵ���Ϣ</title>
 
 
<body  class="body_bg_grey1"> 

 
<html:form method="post" action="ReDefSDic.do"> 
 
 <%=ViewUtil.getTitle("�Զ��嵥���ֵ���Ϣ")%>
  
    <table align="center" width="98%"  class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
           
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�����룺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=reDefSDicForm.getType_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�ֵ����ݣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=reDefSDicForm.getCaption()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;���ݲ�ѯ��䣺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=reDefSDicForm.getStmt()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ע��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=reDefSDicForm.getMemo()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;������Ա��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=ReDefSDicMap.getDicItemVal("0003",reDefSDicForm.getUser_id())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ڣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=DateUtil.formatDate(reDefSDicForm.getReg_dt())%> 
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
 

