<%@page import="com.huateng.blue.work.MainProblem"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String tbName = "problem"; %>
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
<title>主要问题</title>
<script type="text/javascript">
function addProblem(){
	var tbName = "<%=tbName %>";
	 var rowIndex = findObj(tbName+".index",document);
	 var rowID = parseInt(rowIndex.value);
	innerHtml1 = "<textarea name='<%=tbName %>_"+ rowID + "' id='<%=tbName %>_" + rowID + "'  class='TextField' rows='3' cols='100'/>";
	
	addRow(tbName,new Array(innerHtml1));
}

function doSave(){
	document.forms[0].submit();
}
</script>
</head>
<body>
	<script type="text/javascript" src="js/calendar.js"></script>
	<form action="MainProblem.do" method="post">
		<html:hidden property="act" value="update"/>
		
		<table id="<%=tbName %>" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">序号</td><td>主要问题<input type="button" class="Button" onclick="addProblem()" value="添加主要问题"></button></td><td width="10%">操作</td>
			</tr>
			<%List problem = (List)request.getAttribute("list");
			int size = 0;
			if(problem != null){
				size = problem.size();
			}
			if(problem != null){
				Iterator it = problem.iterator();
				int i = 1;
				while(it.hasNext()){
					MainProblem m = (MainProblem)it.next();
					String name="problem_"+i;
			%>
			<tr id="<%=tbName %>_tr_<%=i%>">
			<td><%=i%></td>
			<td><textarea name="<%=name%>" id="<%=name%>" rows="3" cols="100" class="Textfield"><%=m.getContent() %></textarea></td>
			<td><div align='center' style='width:40px'><a href='javascript:;' onclick="DeleteRow('<%=tbName %>_tr_<%=i %>','problem')">删除</a></div></td>
			</tr>
			<%
					i++;
				}
			} %>
		</table>
		<br>
		
		<input name='txtTRLastIndex' type='hidden' id='<%=tbName %>.index' value="<%=size+1 %>"/>
	</form>
		
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" class="Button" onclick="doSave()" value="保存"></button>
			<input type="button" class="Button" onclick="history.back()" value="返回"></button>
			</td>
		</tr>
	</table>
</body>
</html>