<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.lx.Lx_infoForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="lx_infoForm" scope="request" class="com.lx.Lx_infoForm" /> 
 
<html> 
 
<title>��ϵ��Ϣ��Ϣ</title> 
 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="Lx_info.do"> 
 
<%=ViewUtil.getTitle("��ϵ��Ϣ��ϸ")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;��ϵ�˱�ţ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getLx_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;���ţ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
            <%=SingleDicMap.getDicItemVal("9990",lx_infoForm.getDepart())%>
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;������</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getName()%> 
         </td> 
       </tr> 
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;Ա����ţ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getStuff_id()%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�칫�绰��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getPhone()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�ƶ��绰��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getMobile()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;Email��ַ��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=lx_infoForm.getEmail()%> 
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
 

