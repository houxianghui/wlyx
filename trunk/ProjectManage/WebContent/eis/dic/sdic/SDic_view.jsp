<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.sdic.SDicForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="sDicForm" scope="request" class="com.eis.dic.sdic.SDicForm" /> 
 
<html> 
 
<title>�����ֵ���Ϣ</title> 
 
 
<body   class="body_bg_grey1"> 

 
<html:form method="post" action="SDic.do"> 
  
<%=ViewUtil.getTitle("�����ֵ���Ϣ")%>
   
    <table align="center" width="98%"  class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
                 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;ϵͳ��ţ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=sDicForm.getSys_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�����룺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=sDicForm.getType_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;ѡ����룺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=sDicForm.getItem_code()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;ѡ��ֵ��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=sDicForm.getItem_val()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;���˳��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=sDicForm.getList_order()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�߼����룺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=sDicForm.getLogic_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;״̬��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=sDicForm.getStatus()%> 
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
 

