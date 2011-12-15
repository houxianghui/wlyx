<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.huateng.blue.notice.Ep_noticeForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="noticeForm" scope="request" class="com.huateng.blue.notice.Ep_noticeForm" /> 
 
<html> 
 
<title>通知信息</title> 
 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="Ep_notice.do"> 
 
<%=ViewUtil.getTitle("通知信息")%> 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
       
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;通知内容：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=noticeForm.getNotice_comment()%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;上传文件：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=noticeForm.getFilename()%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;置顶：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=SingleDicMap.getDicItemVal("0000",noticeForm.getNotice_top())%>
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;录入员：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
           <%=ReDefSDicMap.getDicItemVal("0003",noticeForm.getOper_id())%> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;录入日期：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
           <%=noticeForm.getOper_date()%> 
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
 

