<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
 
<html> 
 
<title>����������Ϣ</title> 

 
 
<body   class="body_bg_grey1"> 
 
<html:form method="post" action="Org.do">

<%=ViewUtil.getTitle("������Ϣ")%>

    <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
                 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������룺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr>  
      
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ƣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;������ַ��</td> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;���ڳ��У�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�����˵绰��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�ϼ��������ƣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp;         
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�����ʱࣺ</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�����ˣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�������ͣ�</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;�����绰��</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;����ʱ�䣺</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
         </td> 
       </tr> 
  </table> 
   
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>   
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">   
        <tr> 
				<td colspan="2" height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="����" onClick="history.back();">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

