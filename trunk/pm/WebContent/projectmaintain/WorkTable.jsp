<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<html>

<head>
<style type="text/css">
a:LINK{
	color:white;
	text-decoration: none;
}

</style>

<title>员工工作表</title>
<%
	String date = (String)request.getAttribute("date");
	String worktable = (String)request.getAttribute("worktable");
%>
<script type="text/javascript">
function showTable(){
	var value = document.forms[0].date.value;
	document.forms[0].submit();
}
function next(event){
	if(event.keyCode == 37){
		window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=pre";
	}else if(event.keyCode==39){
		window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=next";
	}else if(event.keyCode == 36){
		window.location.href="ProjectDistribute.do?act=display";
	}
}
function prevWeek(){
	window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=pre";
}
function nextWeek(){
	window.location.href="ProjectDistribute.do?act=display&date=<%=date%>&flag=next";
}
</script>
</head>
<body onkeydown="next(event)">
<script type="text/javascript" src="js/calendar.js"></script>
<form action="ProjectDistribute.do" method="post">
<input type="hidden" value="display" name="act">
<table align="center" cellpadding="10">
	
	 <table align="center" width="98%"   class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
     <tr align="center" class="dtPanel_Top01"  >
		<td class="dtPanel_Top01">
			<input type="button" class="Button" value="<<上周" onclick="prevWeek()">
			员工工作表
			<input type="text" name="date" id="date" class="Textfield" readonly="true" size="8" onclick="new Calendar().show(this);"  value="<%=date%>" >
			<input type="button" class="Button" value="搜索" onclick="showTable()">
			<input type="button" class="Button" value="下周>>" onclick="nextWeek()">
		</td>
       
      </tr>

     </table>
	<%
		out.println(worktable);
	%>
</table>
</form>


</body>
</html>


