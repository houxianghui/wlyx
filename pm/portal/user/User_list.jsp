
<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.user.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page import="com.eis.cache.*" %> 
<%@ page  contentType="text/html; charset=GBK" %>


 

<html>

<title>用户维护</title>


<%
	PageObject pageResult = (PageObject) request.getAttribute("pageResult");
	
	int maxPage = 1;
	if(pageResult != null)
		maxPage = pageResult.getTotalPage();
	
%>
<script language="javascript">

function doAdd(){
	//增加
	window.location="User.do?act=c";
	
	
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

function doTerminate(){
	//废除
	
	//检查是否有选中的纪录
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('请先选择纪录');
		return;
	}
	
	//提交表单
	document.forms[0].act.value='t';	
	document.forms[0].submit();
}

function doAble(){
	//启用
	
	//检查是否有选中的纪录
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('请先选择纪录');
		return;
	}
	
	//提交表单
	document.forms[0].act.value='able';	
	document.forms[0].submit();
}

function doDisable(){
	//停用
	
	//检查是否有选中的纪录
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('请先选择纪录');
		return;
	}
	
	//提交表单
	document.forms[0].act.value='disable';	
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
	//删除
	
	//检查输入查询条件
	//检查字段长度
	if(getStrLength(document.forms[0].user_name_f.value)>18) { 		
	        	alert('中文姓名长度请不要超过18个字符!'); 
	        	document.forms[0].user_name_f.focus(); 
	        	return; 
	} 

	document.forms[0].act.value = "list";
	document.forms[0].requery.value='y';
	document.forms[0].submit();
}

function doSetPWD(){

	//检查是否有选中的纪录
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('请先选择纪录');
		return;
	}
	//状态必须是启用
	if(document.forms[0].status.value != 1 ) {
		alert('不能对停用或作废的用户重置密码');
		return;
	}
	//进行确认提示
	if(!confirm('确认要为'+document.forms[0].user_name.value+'重置密码吗？')) {		
		return;
	}
	document.forms[0].act.value='setpwd';
	document.forms[0].submit();

}

function setKey(rid,user_name,status) {
	
	document.forms[0].user_id.value=rid;
	
	document.forms[0].user_name.value=user_name;
	document.forms[0].status.value=status;
}



function turnPage( pagenm ) {  	 
	document.forms[0].act.value = "list";
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
}

function doPopOrg() {
	openWin("OrgPop.do?act=list&id_field=amsd_store_f&name_field=org_name","user_org_pop");
}




</script>

<body class="body_bg_grey1">


<html:form method="post" action="User.do">
<input type=hidden name=user_id>
<input type=hidden name=act value="list">
<input type=hidden name=requery >

<input type=hidden name=user_name>
<input type=hidden name=status>


<!--查询输入部分-->
<table width="98%"   class=dtPanel_Line  align="center" border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td class=dtPanel_Top02 >
    	
    	<table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    	
    	<table width="100%" cellpadding="1">    		
    		
    		<tr >    			
    			<td class=BlueFont> 
    			&nbsp; 中文姓名：<html:text property="user_name_f" styleClass="Textfield" size="20" maxlength="18" />&nbsp;		
     			</td>
     			<td>用户状态：<html:select property="status_f" >
          		 <html:optionsCollection name="userForm" property="status_options" />
          		</html:select>
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
					<td width="9%" >用户名</td>
					<td width="9%" >中文姓名</td>						
					<td width="12%" >主角色</td>
					<td width="7%" >状态</td>
					<td width="11%">起始日期</td>
					<td width="11%">失效日期</td>						
					<td width="11%">建立日期</td>
					<td width="6%">选择</td>
				</tr>
			<%
				List list = pageResult.getList();
				if(list != null ) {
					Iterator iter = list.iterator();
					String m_user_id = null;
					while (iter.hasNext()) {
						UserVO vo = (UserVO) iter.next();
						m_user_id = vo.getUser_id();
						
			%>
				<tr align="center" class="dtPanel_Main"  onclick="_clickTr( this )">						
					<td><a class=fontlink1 href="User.do?act=r&user_id=<%=m_user_id%>"><%=vo.getLogin_id()%></td>
					
					<td><%=vo.getUser_name()%></td>
					<td><%=ReDefSDicMap.getDicItemVal("0002",vo.getRole_id())%> </td>
					<td><%=SingleDicMap.getDicItemVal("0001",vo.getStatus())%> </td>
			
					<td><%=DateUtil.formatDate(vo.getBegin_dt())%></td>
					<td><%=DateUtil.formatDate(vo.getInvalid_dt())%></td>
					<td><%=DateUtil.formatDate(vo.getReg_dt())%></td>						
										
					<td><label><input type="radio" name="user"  onClick="setKey('<%=m_user_id%>','<%=vo.getUser_name()%>','<%=vo.getStatus()%>')"> </label></td>
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
        
		
		<!--功能按钮-->
		<br>
		<table width="98%"  align="center" border="0" cellspacing="1" cellpadding="0">			
			<tr >
				<td   height="28" align="center">	
						<auth:button  name="reset_pwd" id="reset_pwd"  value="重置密码" onClick="doSetPWD()"/>					
						<auth:button  name="add" id="user_c"  value="增加" onClick="doAdd()"/>
						<auth:button  name="edit" id="user_u" value="修改" onClick="doEdit()"/> 			
						<auth:button  name="able" id="user_enable"  value="启用" onClick="doAble()"/>
						<auth:button  name="disable" id="user_disable" value="停用" onClick="doDisable()"/>
						<auth:button  name="terminate" id="user_terminate" value="作废" onClick="doTerminate()"/>	
						<auth:button  name="delete"  id="user_d" value="永久删除" onClick="doDelete()"/>												
				</td>
			</tr>	
		</table>
		
</html:form>

</body>
</html>
