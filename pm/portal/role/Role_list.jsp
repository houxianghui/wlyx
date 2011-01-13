
<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.cache.*" %>
<%@ page import="com.eis.portal.role.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %>


<html>

<title>角色维护</title>


<%
	PageObject pageResult = (PageObject) request.getAttribute("pageResult");
	
	String checked="";
	if(request.getAttribute("checked") != null){
	checked = request.getAttribute("checked").toString();
	System.out.println("***checked:***"+checked);
	}
	
	int maxPage = 1;
	if(pageResult != null)
		maxPage = pageResult.getTotalPage();
	
%>
<script language="javascript">

function doAdd(){
	//增加
	window.location="Role.do?act=c";
	
	
}
function doEdit(){
	//修改
	
	//检查是否有选中的纪录
	if(document.forms[0].role_id.value == null ||document.forms[0].role_id.value == "") {
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
	if(document.forms[0].role_id.value == null ||document.forms[0].role_id.value == "") {
		alert('请先选择纪录');
		return;
	}
	
	//提交表单
	document.forms[0].act.value='t';	
	document.forms[0].submit();
}


function doPerm(){
	//权限管理
	
	//检查是否有选中的纪录
	if(document.forms[0].role_id.value == null ||document.forms[0].role_id.value == "") {
		alert('请先选择纪录');
		return;
	}
	if(document.forms[0].status.value=="作废"){
		alert('不能对已废除角色进行权限管理！');
		return;
	}
	
	//到角色操作权限管理页面		
	window.location="RoleMenu.do?act=u&role_id="+document.forms[0].role_id.value;
}

function doDelete() {
	//删除
	
	//检查是否有选中的纪录
	if(document.forms[0].role_id.value == null ||document.forms[0].role_id.value == "") {
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
	if(getStrLength(document.forms[0].role_name.value)>40) { 		
	        	alert('角色名称长度请不要超过40个字符!'); 
	        	document.forms[0].role_name.focus(); 
	        	return; 
	}

	document.forms[0].act.value = "list";
	document.forms[0].requery.value='y';
	document.forms[0].submit();
}


function setKey(rid,stat) {
	
	document.forms[0].role_id.value=rid;
	document.forms[0].status.value=stat;
}


function turnPage( pagenm ) {  
	document.forms[0].act.value = "list"; 
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
}




</script>

<body  class="body_bg_grey1">


<html:form method="post" action="Role.do">
<input type=hidden name=role_id>
<input type=hidden name=status>
<input type=hidden name=act value="list">
<input type=hidden name=requery >

    
    <table width="98%"   class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0">    	  
          <tr  >
          <td class="dtPanel_Top02">          
         
          <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
          	<table width="98%">
          		<tr height="1"><td ></td></tr> 
            	<tr><td >&nbsp;&nbsp;角色名称：<html:text property="role_name" styleClass="Textfield" size="40" maxlength="40" /> 
            		&nbsp;&nbsp;<input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;
            		</td>            	
          	</table>
          	<table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
                    	
          </td>
         </tr>          
        </table>
	
<table  class=heightspace_top3 ><tr><td></td></tr></table>
	
		<table width="98%"  align="center" class="dtPanel_Line1" border="0" cellpadding="0" cellspacing="1">
				<tr align="center" class="dtPanel_Top01"  height="26">
					<td width="12%">角色代码</td>
					<td width="22%">角色名称</td>
					<td width="22%">首页模板</td>						
					<td width="7%">状态</td>
					<td width="10%">角色级别</td>
					<td width="5%">选择</td>
				</tr>
			<%
				List list = pageResult.getList();
				if(list != null ) {
					Iterator iter = list.iterator();
					String m_role_id = null;
					while (iter.hasNext()) {
						RoleVO vo = (RoleVO) iter.next();
						m_role_id = vo.getRole_id();
						
			%>
					<tr align="center"   class="dtPanel_Main" onclick="_clickTr( this )">
						<td><a class=fontlink1 href="Role.do?act=r&role_id=<%=m_role_id%>"><%=m_role_id%></a></td>						
						<td><%=vo.getRole_name()%></td>
						<td><%=ReDefSDicMap.getDicItemVal("0001",String.valueOf(vo.getTempl_id()))%></td>
						<td><%=vo.getStatus()%></td>
						<td><%=SingleDicMap.getDicItemVal("0004", vo.getRole_level())%></td>
						<td><label><input type="radio" name="role" <%=checked%> onClick="setKey('<%=m_role_id%>','<%=vo.getStatus()%>')"> </label></td>
						<%
						if(checked !=null || checked.length()>0){
						%>
						<script>
						setKey('<%=m_role_id%>','<%=vo.getStatus()%>');
						</script>
						<%
						}
						%>
					</tr>


				<%
						}
					}
				%>
		</table>
			
		
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td  class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
		
		<br>
		
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">					
				<tr >
					<td  height="28" align="center"  >
					<auth:button name="perm" id="role_perm"  value="权限管理" onClick="doPerm()"/> &nbsp;
					<auth:button name="add" id="role_c" value="增加角色" onClick="doAdd()"/> &nbsp;
					<auth:button name="edit" id="role_u"  value="修改角色" onClick="doEdit()"/>&nbsp; 						
					<auth:button name="terminate" id="role_terminate" value="角色作废" onClick="doTerminate()"/>&nbsp;
					<auth:button name="delete" id="role_d" value="永久删除" onClick="doDelete()"/>&nbsp;
					
					</td>
				</tr>
		</table>
		
</html:form>
<p>&nbsp;</p>

</body>
</html>
