<%@page import="com.huateng.blue.work.DailyIssue"%>
<%@page import="com.huateng.blue.work.DailyAchieve"%>
<%@page import="com.huateng.blue.work.WorkDaily"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
tr{
	background-color:#F7F7F7;
	COLOR:#333333;
	FONT-SIZE:9pt;
	FOTN-FAMILY:宋体,verdana,Arial, Helvetica;
	height:23px;
	text-align: center
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>日报信息</title>
</head>
<body>
	<script type="text/javascript" src="js/calendar.js"></script>
	<form action="WorkDaily.do" method="post">
		<input name='txtTRLastIndex' type='hidden' id='workContent.index' value="1" />
  		<input name='txtTRLastIndex' type='hidden' id='achieve.index' value="1" />
  		<input name='txtTRLastIndex' type='hidden' id='issue.index' value="1" />
  		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="left" class="dtPanel_Top01">
			<td align="left">填报日期:
  			<%=request.getAttribute("date")%>
  			</td>
  			</tr>
		</table>
		<br>
		<table id="workContent" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">序号</td><td>工作内容</td><td width="5%">&nbsp;</td>
			</tr>
			<%List work = (List)request.getAttribute("work");
			if(work != null){
				Iterator it = work.iterator();
				int i = 1;
				while(it.hasNext()){
					WorkDaily wd = (WorkDaily)it.next();%>
					<tr>
					<td><%=i++ %></td>
					<td><%=wd.getContent()%></td>
					<td></td>
					</tr>
			<%}} %>
			
		</table>
		<br>
		<table id="achieve" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">序号</td><td>工作成果</td><td width="5%">百分比</td>
			</tr>
			<%List achieve = (List)request.getAttribute("achieve");
			if(achieve != null){
				Iterator it = achieve.iterator();
				int i = 1;
				while(it.hasNext()){
					DailyAchieve da = (DailyAchieve)it.next();%>
					<tr>
					<td><%=i++ %></td>
					<td><%=da.getAchievement()%></td>
					<td><%=da.getAchievePercent()%></td>
					</tr>
			<%}} %>
		</table>
		<br>
		<table id="issue" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">序号</td><td>遗留问题</td><td width="5%">百分比</td>
			</tr>
			<%List issue = (List)request.getAttribute("issue");
			if(issue != null){
				Iterator it = issue.iterator();
				int i = 1;
				while(it.hasNext()){
					DailyIssue da = (DailyIssue)it.next();%>
					<tr>
					<td><%=i++ %></td>
					<td><%=da.getIssue()%></td>
					<td><%=da.getIssuePercent()%></td>
					</tr>
			<%}} %>
		</table>
	</form>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" class="Button" onclick="history.back()" value="返回"></button>
			</td>
		</tr>
	</table>
</body>
</html>