<%@page import="com.huateng.blue.work.SignRecord"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="projectDistributeForm" scope="request" class="com.projectmaintain.ProjectDistributeForm" />

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
String act = (String)request.getAttribute("act");
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>我的项目</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "<%=act%>";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doProcess(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	
	document.forms[0].act.value="addTask";
	document.forms[0].submit();
}
function doFinish(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	
	if(!confirm('执行完成操作后，你将无法继续填写此阶段的工时，确定完成吗？')) { 
		return; 
	} 
	document.forms[0].act.value="doFinish";
	document.forms[0].submit();
}
function doQuery(){
	document.forms[0].act.value="<%=act%>";
	document.forms[0].submit();
}
function signOff(){
	window.location.href="SignRecord.do?act=signoff";
}
function doAdd(){
	window.location.href="SignRecord.do?act=new";
}
function doEdit(){
	if(document.forms[0].userId.value == null ||document.forms[0].userId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	
	
	document.forms[0].act.value="edit";
	document.forms[0].submit();
}
function doDelete(){
	if(document.forms[0].userId.value == null ||document.forms[0].userId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 	
	if(!confirm('确定要删除该记录吗？')) { 
		return; 
	} 
	document.forms[0].act.value="delete";
	document.forms[0].submit();
}
function setPKey(projectId_var,status_var,id_var) { 
	document.forms[0].recordDate.value=projectId_var; 
	document.forms[0].recordType.value=status_var;
	document.forms[0].userId.value=id_var;	
} 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="SignRecord.do">
<input type=hidden name=act value="<%=act%>">
<html:hidden property="recordDate"/>
<html:hidden property="userId"/>
<html:hidden property="recordType"/>

<%=ViewUtil.getTitle("签到签退")%>
	
	<table class="heightspace_top3" width="98%" border="0" cellspacing="1"
		align="left" cellpadding="0">
		<tr align="left">
			<td>
			&nbsp;&nbsp;查询日期：<html:text property="date_f" size="8" readonly="true" styleClass="Textfield" onclick="new Calendar().show(this)"/>
			<%if("list".equals(act)){ %>
			检查人员： <html:select property="userId_f" styleClass="Select" disabled="false">
	         	<html:optionsCollection name="projectDistributeForm" property="stuff"/>
	         </html:select>
	         <%} %>
			<input type="button" class="Button" value="查询" onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" >
			<td nowrap>姓名</td>
			<td nowrap>日期</td>			
			<td nowrap>时间</td>
			<td nowrap>操作类型</td>	
			<td>代签</td>
			<td>代签人</td>
			<td>选择</td>
		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			SignRecord vo = (SignRecord)iter.next();%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><%=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getUserId())%></td>
			<td><%=vo.getRecordDate()%></td>
			<td><%=Format.formatTime(vo.getRecordTime())%> </td>
			<td><%="I".equals(vo.getRecordType())?"签到":"签退"%></td>
			<td><%="Y".equals(vo.getIsModified())?"是":"否"%></td>
			<td><%=vo.getModifyUser()==null?"":ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER, vo.getModifyUser())%></td>
			
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getRecordDate()%>','<%=vo.getRecordType()%>','<%=vo.getUserId()%>')">
			</label></td>
		</tr>

		<%}
	}
}%>

</table>
	<%
//产生翻页脚注 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<auth:button value="新增" onClick="doAdd()" name="signmanage" id="signmanage"></auth:button>
			<auth:button value="修改" onClick="doEdit()" name="signmanage" id="signmanage"></auth:button>	
			<auth:button value="删除" onClick="doDelete()" name="signmanage" id="signmanage"></auth:button>			
			<auth:button value="签退" onClick="signOff()" name="signoff" id="signoff"></auth:button>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


