<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.key.pmkey.PmKeyForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="pmKeyForm" scope="request" class="com.eis.key.pmkey.PmKeyForm" /> 
 
<html> 
 
<title>����������Ϣ</title> 
 
 
<body  class="body_bg_grey1"> 

 
<html:form method="post" action="PmKey.do"> 

 <%=ViewUtil.getTitle("����������Ϣ")%>
 
    <table align="center" width="98%"  class="dtPanel_Line3"   border="0" cellspacing="1" cellpadding="0">  
             
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;���ݿ������</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pmKeyForm.getTb_name()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�����ֶΣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pmKeyForm.getKey_field()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;������</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pmKeyForm.getStep_len()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ǰ���ֵ��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=pmKeyForm.getMax_val()%> 
         </td> 
       </tr> 
  </table> 
  
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">      
        <tr> 
				<td height="25" align="center"  class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="����" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

