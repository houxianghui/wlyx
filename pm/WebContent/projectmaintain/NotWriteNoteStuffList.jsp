
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<jsp:useBean id="projectDistributeForm" class="com.projectmaintain.ProjectDistributeForm"></jsp:useBean>
<head>
<title>未填写工时员工</title>
<script type="text/javascript">
function download(){
	var date = document.getElementById("date").value;
	if(date == ''){
		alert('请选择日期');
		return;
	}
	var title = document.forms[0].title.value;
	if(title==''){
		alert('请选择报表头名称');
		return;
	}
	window.location.href="WorkDaily.do?act=download&date="+date+"&title="+title;
}
function preView(){
	var date = document.getElementById("date").value;
	if(date == ''){
		alert('请选择日期');
		return;
	}
	var title = document.forms[0].title.value;
	if(title==''){
		alert('请选择报表头名称');
		return;
	}
	openWin("WorkDaily.do?act=preview&date="+date+"&title="+title);
}
</script>
</head>
<body class="body_bg_grey1">
<script type="text/javascript" src="js/calendar.js"></script>
<html:form method="post" action="NotWriteNoteStuff.do">

	<%=ViewUtil.getTitle("未填写工时员工")%>

	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr><td>检查日期：
		<html:text property="date" styleId="date" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"></html:text>
		<html:submit value="搜索" styleClass="Button"></html:submit>
		报表头名称
		<html:select property="title" styleClass="Select">
			<html:optionsCollection name="projectDistributeForm" property="titleCollection"/>
		</html:select>
		<input type="button" class="Button" value="预览" onclick="preView()">
		<input	name="query" type="button" class="Button" value="下载" onclick="download()">
		</td></tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" height="28">
		<td colspan="5"></td>
		</tr>
		
			<%
				if(request.getAttribute("stuff")!=null && request.getAttribute("stuff").toString().trim().length()!=0){
					java.util.List l = (List)request.getAttribute("stuff");
					java.util.Iterator it = l.iterator();
					int itemPerRow = 5;
					%>
					<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
					<%
					while(it.hasNext()){
					
						if(itemPerRow-- >0){
							out.print("<td>"+ReDefSDicMap.getDicItemVal("0003",(String)it.next())+"</td>");
						}else{
							itemPerRow = 5;
							out.print("</tr>");
							%>
							<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
							<%
						}
						
					}
					while(itemPerRow-- >0){
						out.println("<td></td>");
					}
				}
			%>
			
		</tr>
	</table>
	<table align=center width="98%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td height="25" align="center" class="dtPanel_Bottom">
			 <input name="return" type="button"	class="Button" value="返回" onClick="history.back()"></td>
		</tr>
	</table>

</html:form>

</body>
</html>
