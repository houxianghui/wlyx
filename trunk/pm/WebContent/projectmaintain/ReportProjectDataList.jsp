<%@ include file = "/includes/common.jsp" %>
<%@ page import="com.projectmaintain.ReportProjectDataListVO" %>
<%@ page contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>
<%int maxPage = 1;
  if (pageResult != null) maxPage = pageResult.getTotalPage();
%>
<head>
<title>项目统计报表</title>
<script language="javascript">
	function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
	}
	function doQuery() {  
		document.forms[0].act.value = "list";
		document.forms[0].submit(); 
	} 
</script>
</head>

<body>
<html:form method="post" action="ReportProjectDataList.do">
<input type=hidden name=act>
<%=ViewUtil.getTitle("项目统计报表")%>
<table class=heightspace_top3 width="98%" border="0" cellspacing="1" align="center" cellpadding="0">
	<tr>
		<td>
		项目编号：
		<html:text property="projectId" styleClass="Textfield" size="4"></html:text>
		项目名称：
		<html:text property="projectName" styleClass="Textfield" size="4"></html:text>
		立项日期：
		<html:text property="startDate" styleClass="Textfield" size="4"></html:text>
		项目阶段：
		<html:text property="status" styleClass="Textfield" size="4"></html:text>
		<input name="query" type="button" class="Button_Search"  onclick="doQuery()">
		<input type="button" class="Button" value="返回" onclick="history.back()">
		</td>
	</tr>
</table>
<table class="dtPanel_Line1" width="98%" border="0" cellspacing="1" align="center" cellpadding="0">
	<tr align="left" class="dtPanel_Top01" height="28">
		<td rowspan="2">开发编号</td>
	    <td rowspan="2">项目编号</td>
	    <td rowspan="2">项目名称</td>
	    <td rowspan="2">立项时间</td>
	    <td rowspan="2">当前项目阶段</td>
	    <td colspan="5">计划完成时间</td>
	    <td colspan="5">实际完成时间</td>
	    <td rowspan="2">计划投入工时</td>
	    <td rowspan="2">实际发生工时</td>
	    <td rowspan="2">偏差率</td>
	</tr>
    <tr align="left" class="dtPanel_Top01" height="28">
	    <td>BRD</td>
	    <td>FTS</td>
	    <td>DEV</td>
	    <td>SIT</td>
	    <td>UAT</td>
	    <td>BRD</td>
	    <td>FTS</td>
	    <td>DEV</td>
	    <td>SIT</td>
	    <td>UAT</td>
    </tr>
	<%if (pageResult != null) {
		List list = pageResult.getList();
		if (list != null) {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				ReportProjectDataListVO vo = (ReportProjectDataListVO) iter.next();%>
				<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
					<td>&nbsp;<%=vo.getProjectId()%></td>
					<td>&nbsp;<%=vo.getIssId()%></td>			
					<td>&nbsp;<%=vo.getProjectName()%></td>	
					<td>&nbsp;<%=vo.getStartDate()%></td>
				    <td>&nbsp;<%=vo.getStatus()%></td>
				    <td>&nbsp;<%=vo.getDesignBRD()%></td>
				    <td>&nbsp;<%=vo.getDesignFTS()%></td>
				    <td>&nbsp;<%=vo.getDesignDEV()%></td>
				    <td>&nbsp;<%=vo.getDesignSIT()%></td>
				    <td>&nbsp;<%=vo.getDesignUAT()%></td>
				    <td>&nbsp;<%=vo.getFinalBRD()%></td>
				    <td>&nbsp;<%=vo.getFinalFTS()%></td>
				    <td>&nbsp;<%=vo.getFinalDEV()%></td>
				    <td>&nbsp;<%=vo.getFinalSIT()%></td>
				    <td>&nbsp;<%=vo.getFinalUAT()%></td>
				    <td>&nbsp;<%=vo.getDesignDay()%></td>
				    <td>&nbsp;<%=vo.getFinalDay()%></td>
				    <td>&nbsp;<%=vo.getErrorRate()%></td>
				</tr>
			<%}
		}
	}%>
</table>
<%if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
<%}%>
</html:form>
</body>
</html>


