<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.dictype.DicTypeForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="dicTypeForm" scope="request" class="com.eis.dic.dictype.DicTypeForm" /> 
 
<html> 
 
<title>字典归类信息信息</title>  

 
 
<body  class="body_bg_grey1"> 

 
<html:form method="post" action="DicType.do"> 

<%=ViewUtil.getTitle("字典归类信息")%>

    <table align="center" width="98%"  class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0">
       	     
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;归类码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=dicTypeForm.getType_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;名称：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=dicTypeForm.getType_name()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;级别标志：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=dicTypeForm.getDic_level_caption()%> 
         </td> 
       </tr>        
  </table>   
  
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	     
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back()">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

