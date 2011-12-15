<%@page import="com.huateng.blue.work.WorkList"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>项目维护</title>
<script language="javascript"> 
function doQuery() {  
	document.forms[0].act.value = "qa";
	document.forms[0].submit(); 
} 
 
function setPKey(projectId_var) { 
	document.forms[0].workId.value=projectId_var; 
} 
function doDistribute(){ 
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	window.location.href="WorkDistribute.do?act=dl&&workId="+document.forms[0].workId.value;}  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qa";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="WorkList.do">
<input type=hidden name=act value="qa">
<html:hidden property="workId"/>
<%=ViewUtil.getTitle("任务维护")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			任务编号
			<html:text property="workId_f" styleClass="Textfield" size="4"></html:text>
			任务状态
			<html:select property="workStatus" styleClass="Select">
				<html:optionsCollection name="workListForm" property="statusCollection"/>
			</html:select>
			检查日期：<html:text property="endDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			检查天数：<html:text property="day" styleClass="Textfield" size='4'/>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>主次状态</td>
			<td nowrap>&nbsp;任务编号</td>
			<td>&nbsp;任务名称</td>
			<td nowrap>&nbsp;任务状态</td>
			<td>&nbsp;任务开始日期</td>
			<td>&nbsp;任务结束日期</td>
			<td>&nbsp;备注</td>
			<td align="center">选择</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			WorkList vo = (WorkList) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td>&nbsp;<%="N".equals(vo.getIsMain())?"一般":"主要" %></td>
			<td><a href="WorkList.do?act=view&workId=<%=vo.getWorkId()%>">&nbsp;<%=vo.getWorkId()%></a></td>
			<td>&nbsp;<%=vo.getWorkName()%></td>			
			<td>&nbsp;<%=SingleDicMap.getDicItemVal(SingleDic.WORK_STATUS, vo.getWorkStatus())%> </td>	
			<td>&nbsp;<%=vo.getStartDate()%></td>
			<td>&nbsp;<%=vo.getEndDate()%></td>
			<td>&nbsp;<%=vo.getContent()%></td>	
			<td align="center"><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getWorkId()%>')">
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
			
			<input type="button" value="任务分配" class="Button" onclick="doDistribute()"></td>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


