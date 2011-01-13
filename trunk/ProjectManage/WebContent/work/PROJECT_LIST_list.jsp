<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.PROJECT_LISTVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>当前项目维护</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="PROJECT_LIST.do?act=c"; 
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
function doProcess(){
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	
	var project_no = document.forms[0].project_no.value;
	var step = document.forms[0].curr_step.value;
	window.location="TASK_LIST.do?act=c&project_no="+project_no+"&curr_step="+step;
}
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
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
 
function setPKey(user_id_var,project_no_var,curr_step_var) { 
	document.forms[0].user_id.value=user_id_var; 
	document.forms[0].project_no.value=project_no_var; 
	document.forms[0].curr_step.value=curr_step_var;
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="PROJECT_LIST.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=user_id> 
<input type=hidden name=project_no> 
<input type=hidden name=curr_step>
 
 
<table width="98%" class="dtPanel_Line1" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line1"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top01">
         <td align="center">当前项目维护</td>
         </tr>
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						
						<td>项目编号</td> 
						<td>项目名称</td> 
						<td>当前阶段</td> 
						<td>开始日期</td> 
						<td>结束日期</td> 
						<td>选择</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							PROJECT_LISTVO vo = (PROJECT_LISTVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="PROJECT_LIST.do?act=r&user_id=<%=vo.getUser_id()%>&project_no=<%=vo.getProject_no()%>"><%=vo.getProject_no()%></a></td> 
						<td><%=vo.getProject_name()%></td> 
						<td><%=SingleDicMap.getDicItemVal("9994",vo.getCurr_step())%></td> 
						<td><%=vo.getStart_date()%></td> 
						<td><%=vo.getEnd_date()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getUser_id()%>','<%=vo.getProject_no()%>','<%=vo.getCurr_step()%>')"> </label></td> 
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
				</table> 
			<% 
				//产生翻页脚注 
				if(pageResult != null) { 
			%> 
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
			<% 
				} 
			%> 
			<br> 
 
				<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<auth:button name="add" id="pROJECT_LIST_c" value="增加" onClick="doAdd()"/> &nbsp; 
						<auth:button name="edit" id="pROJECT_LIST_c" value="修改" onClick="doEdit()"/> &nbsp;  
						<auth:button name="delete" id="pROJECT_LIST_c" value="删除" onClick="doDelete()"/> &nbsp;
						<auth:button name="process" id="tASK_LIST_c" value="执行" onClick="doProcess()"></auth:button>
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

