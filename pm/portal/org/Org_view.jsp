<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.org.OrgForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="orgForm" scope="request" class="com.eis.portal.org.OrgForm" /> 
 
<html> 
 
<title>机构管理信息</title> 

 
 
<body   class="body_bg_grey1"> 
 
<html:form method="post" action="Org.do">

<%=ViewUtil.getTitle("机构信息")%>

    <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
                 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;机构代码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_store()%> 
         </td> 
       </tr>  
      
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;机构名称：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_name_line_1()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;机构地址：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_addr_line_1()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;所在城市：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_city_state()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;负责人电话：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_phone_nbr_2()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;上级机构名称：</td> 
         <td align="left" class="dtPanel_Main">&nbsp;         
          <%=OrgMap.getOrgName(orgForm.getAmsd_store_pn3())%>
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;机构邮编：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_pstl_cd()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;负责人：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_contact_1()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;机构类型：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("0004",orgForm.getAmsd_store_level_c2())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;机构电话：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=orgForm.getAmsd_store_phone()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;更新时间：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=DateUtil.formatDateTime(orgForm.getAmsd_upd_time())%> 
         </td> 
       </tr> 
  </table> 
   
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>   
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">   
        <tr> 
				<td colspan="2" height="25" align="center" class="dtPanel_Bottom"> 
						<input name="return" type="button" class="Button" value="返回" onClick="history.back();">   
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

