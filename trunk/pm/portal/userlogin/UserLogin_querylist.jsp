<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.userlogin.UserLoginVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>管理用户登录记录表查询</title> 
 
<link href="CSS/Style.css" rel="stylesheet" type="text/css"> 
 
<SCRIPT src="js/validate.js" type="text/javascript"></SCRIPT> 
<SCRIPT src="js/event.js" type="text/javascript"></SCRIPT> 
<SCRIPT src="js/func.js" type="text/javascript"></SCRIPT> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "ql"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "ql";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body> 
 
<html:form method="post" action="UserLogin.do"> 
<input type=hidden name=act value="ql"> 
<input type=hidden name=requery > 
 
<table width="98%" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 用户编号：<html:text property="user_id_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp; <input	name="query" type="button" class="Button" value="查询" onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
 
		<br> 
		<table width="98%" border="0" cellspacing="1" align="center" cellpadding="0"> 
			<tr> 
				<td class="dtPanel_Line"> 
				<table width="100%" border="0" cellpadding="0" cellspacing="1"> 
					<tr align="center" class="dtPanel_Top01"> 
 
						<td width="20%">用户编号</td> 
						<td width="20%">登录时间</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						UserLoginVO vo = (UserLoginVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onmousedown="_clickTr( this )"> 
						<td><a href="UserLogin.do?act=r&user_id=<%=vo.getUser_id()%>"><%=vo.getUser_id()%></a></td> 
						<td><%=vo.getLogin_time()%></td> 
 
					</tr> 
 
				<% 
						} 
					} 
				%> 
				</table> 
				</td> 
			</tr> 
			<tr> 
			<td height="25"> 
               <%=pageResult.getFooter()%> 
				</td> 
			</tr> 
		</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

