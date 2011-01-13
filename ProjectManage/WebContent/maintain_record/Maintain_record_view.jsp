<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.maintainrecord.Maintain_recordForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="maintain_recordForm" scope="request" class="com.maintainrecord.Maintain_recordForm" /> 
 
<html> 
 
<title>技术支持服务记录信息</title> 
 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="Maintain_record.do"> 
 
<%=ViewUtil.getTitle("技术支持服务记录信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;提出日期：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=maintain_recordForm.getQus_date()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;提出人机构姓名：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=maintain_recordForm.getQus_user()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;问题描述：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=maintain_recordForm.getQus_info()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;答复人员：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=ReDefSDicMap.getDicItemVal("0003",maintain_recordForm.getRes_user())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;答复结果：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("9993",maintain_recordForm.getRes_result())%> 
         </td> 
       </tr> 
 		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;答复时间：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=maintain_recordForm.getRes_time()%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;备注：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=maintain_recordForm.getRes_memo()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;所用工时：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=maintain_recordForm.getRes_cost()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;录入员：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=ReDefSDicMap.getDicItemVal("0003",maintain_recordForm.getInput_user())%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;录入时间：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=maintain_recordForm.getInput_time()%> 
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
 

