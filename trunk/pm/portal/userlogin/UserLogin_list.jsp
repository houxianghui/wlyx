<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.userlogin.UserLoginVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>管理用户登录记录表维护</title>  

 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="UserLogin.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
 
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	
	//进行确认提示
	if(!confirm('您确认执行删除操作？')) {		
		return;
	}
	
	document.forms[0].act.value='d'; 
	document.forms[0].submit(); 
} 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "list"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(user_id_var) { 
	document.forms[0].user_id.value=user_id_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="UserLogin.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=user_id> 
 
 
<table width="98%" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <!--td>&nbsp; 用户编号：<html:text property="user_id_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp; <input	name="query" type="button" class="Button" value="查询" onClick="doQuery()"> &nbsp;</td--> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
 
		
	
		<table width="98%" class="dtPanel_Line1"  border="0" cellpadding="0" cellspacing="1"> 
					<tr align="center" class="dtPanel_Top01"> 
 
						<td width="20%">用户编号</td> 
						<td width="20%">登录时间</td> 
						<td>选择</td> 
 
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
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getUser_id()%>')"> </label></td> 
					</tr> 
 
				<% 
						} 
					} 
				%> 
			</table> 
			
			
		<!--页码等页脚-->		
		<table width="98%"   align="center"   cellspacing="0" cellpadding="1" border="0">
            <tr  class="dtPanel_Pager">
                <td><%=pageResult.getFooter()%></td>                
            </tr>
        </table>
		
				 
		
 
		<br>
		<table width="98%"  align="center" border="0" cellspacing="1" cellpadding="0">			
			<tr >
						<td height="25" align="center"> 
						<input	name="add" type="button" class="Button" value="增加" onClick="doAdd()"> &nbsp; 
						<input name="edit" type="button" class="Button" value="修改" onClick="doEdit()"> &nbsp;  
						<input name="delete" type="button" class="Button" value="删除" onClick="doDelete()"> 
						</td> 
					</tr> 
		</table> 
	
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

