<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.user.UserVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>用户列表查询</title>  


 
 
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
 
<html:form method="post" action="User.do"> 
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
						<td width="20%">主角色代码</td> 
						<td width="20%">用户名</td> 
						<td width="20%">中文姓名</td> 
						<td width="20%">联系电话</td> 
						<td width="20%">手机</td> 
						<td width="20%">邮编</td> 
						<td width="20%">联系地址</td> 
						<td width="20%">起始日期</td> 
						<td width="20%">失效日期</td> 
						<td width="20%">备注</td> 
						<td width="20%">网点号</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						UserVO vo = (UserVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main"> 
						<td><a href="User.do?act=r&user_id=<%=vo.getUser_id()%>"><%=vo.getUser_id()%></a></td> 
						<td><%=vo.getRole_id()%></td> 
						<td><%=vo.getLogin_id()%></td> 
						<td><%=vo.getUser_name()%></td> 
						<td><%=vo.getPhone()%></td> 
						<td><%=vo.getMobile()%></td> 
						<td><%=vo.getPostcode()%></td> 
						<td><%=vo.getAddress()%></td> 
						<td><%=vo.getBegin_dt()%></td> 
						<td><%=vo.getInvalid_dt()%></td> 
						<td><%=vo.getMemo()%></td> 
						<td><%=vo.getAmsd_store()%></td> 
 
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
 
</body> 
</html> 
 

