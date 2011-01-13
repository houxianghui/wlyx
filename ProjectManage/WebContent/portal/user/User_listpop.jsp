<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.eis.portal.user.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page import="com.eis.cache.*" %> 
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="userForm" scope="request" class="com.eis.portal.user.UserForm" /> 


<html>

<title>用户列表弹出窗口</title>


<%
	PageObject pageResult = (PageObject) request.getAttribute("pageResult");
	
	int maxPage = 1;
	if(pageResult != null)
		maxPage = pageResult.getTotalPage();
	
%>
<script language="javascript">

function doQuery() {
	//删除
	
	//检查输入查询条件
	//检查字段长度
	if(getStrLength(document.forms[0].user_name_f.value)>18) { 		
	        	alert('中文姓名长度请不要超过18个字符!'); 
	        	document.forms[0].user_name_f.focus(); 
	        	return; 
	} 

	document.forms[0].act.value = "listpop";
	document.forms[0].requery.value='y';
	document.forms[0].submit();
}


function setKey(id,name) {
    opener.document.forms[0].<%=userForm.getId_field()%>.value=id;
	opener.document.forms[0].<%=userForm.getName_field()%>.value=name;
	window.close();

}



function turnPage( pagenm ) {  
	document.forms[0].act.value = "listpop"; 
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
}

function doPopOrg() {
	openWin("OrgPop.do?act=list&id_field=amsd_store_f&name_field=org_name","user_org_pop");
}


</script>

<body class="body_bg_grey1">


<html:form method="post" action="User.do">
<input type=hidden name=act value="listpop">
<input type=hidden name=requery >

<html:hidden property="id_field"/>
<html:hidden property="name_field"/>


<!--查询输入部分-->
<table width="98%"   class=dtPanel_Line  align="center" border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td class = dtPanel_Top02 >
    	
    	<table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    	
    	<table width="100%" cellpadding="1">    		
    		<tr>    			
    			<td colspan=3 class=BlueFont>&nbsp; 所属机构：<html:text property="org_name" size="38" readonly="true"  maxlength="40" styleClass="Textfield" />  
          						<input type="button" value="查询机构"   class="Button"  onClick="doPopOrg()"> 
          						<html:hidden property="amsd_store_f"/>
          		</td>
    		</tr>    		
    		<tr >    			
    			<td class=BlueFont> 
    			&nbsp; 中文姓名：<html:text property="user_name_f" styleClass="Textfield" size="20" maxlength="18" />&nbsp;		
     			</td>
     			
    			<td align=left>
    			<input	name="query" type="button" class="Button_Search" value="   " onClick="doQuery()"> &nbsp;    			
    			</td>    			
    		</tr>     			
    		
    	</table>
    	<table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
        
        </td>
  </tr>
</table>


<table  class=heightspace_top3 ><tr><td></td></tr></table>      

		<!--列表-->
		<table width="98%"  align="center"  class="dtPanel_Line1" border="0" cellpadding="0" cellspacing="1"  >
				<tr align="center" class="dtPanel_Top01"  height="28">
					<td width="9%">用户名</td>	
					<td width="9%">中文姓名</td>						
					<td width="24%">联系电话</td>						
					<td width="12%">手机</td>					
					<td width="11%">起始日期</td>
					<td width="11%">失效日期</td>						
					<td width="11%">建立日期</td>
					<td width="6%">选择</td>
				</tr>
			<%
				List list = pageResult.getList();
				if(list != null ) {
					Iterator iter = list.iterator();
					while (iter.hasNext()) {
						UserVO vo = (UserVO) iter.next();						
			%>
				<tr align="center" class="dtPanel_Main"  onclick="_clickTr( this )">						
					<td><%=vo.getLogin_id()%></td>
					<td><%=vo.getUser_name()%></td>
					<td><%=vo.getPhone()%></td>
					<td><%=vo.getMobile()%> </td>
					<td><%=vo.getBegin_dt()%></td>
					<td><%=vo.getInvalid_dt()%></td>
					<td><%=vo.getReg_dt()%></td>						
										
					<td><label><input type="radio" name="user"  onClick="setKey('<%=vo.getUser_id()%>','<%=vo.getUser_name()%>')"> </label></td>
				</tr>

				<%
						}
					}
				%>
		</table>
		
		<!--页码等页脚-->		
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td  class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 

		
</html:form>

</body>
</html>
