<%@ include file = "/includes/common.jsp" %>
<jsp:useBean id="scoreInfoForm" scope="request" class="com.projectmaintain.ScoreInfoForm" />
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<head>
<title>����</title>
<script language="javascript"> 
var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}
function testCalc(){
	
	
	var url = "ScoreInfo.do?act=calc&projectId=<%=scoreInfoForm.getProjectId()%>&userId=<%=scoreInfoForm.getUserId().trim()%>&status=<%=scoreInfoForm.getStatus()%>";
    
    createXMLHttpRequest();
    xmlHttp.open("POST", url);
    xmlHttp.onreadystatechange = handleStateChange;   
    xmlHttp.send(null);
}
function handleStateChange() {	
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
             parseResults();
        }
    }
}
function parseResults() {
    var responseDiv = document.getElementById("totalScore");
    if(responseDiv.hasChildNodes()) {
        responseDiv.removeChild(responseDiv.childNodes[0]);
    }
    
    var responseText = document.createTextNode(xmlHttp.responseText);
    responseDiv.appendChild(responseText);
    responseDiv.focus();
}
</script>
</head>
<body>

<html:form method="post" action="ScoreInfo.do">
<input type=hidden name=act value="save">
<%=ViewUtil.getTitle("���ֹ���")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr class="dtPanel_Main" align="left">
			<td>��Ŀ���:<html:hidden property="projectId"/><%=scoreInfoForm.getProjectId()%></td>
			<td>��Ŀ�׶�:<html:hidden property="status"/><%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,scoreInfoForm.getStatus())%></td>
			<td>��Ա��<html:hidden property="userId"/><%=ReDefSDicMap.getDicItemVal("0003",scoreInfoForm.getUserId().trim())%></td>
		
			<td align="right">�ܵ÷֣�</td><td align="left"><div id="totalScore">&nbsp;&nbsp;</div></td>
			<td><input type="button" class="Button" value="����" onclick="testCalc()"/></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line3" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td>������</td><td>���ֹ���</td><td>����</td>
		</tr>
		
		<%
			String s = (String)request.getAttribute("table");
			out.println(s);
		%>
	</table>
	

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">		
			<html:submit value="ȷ��" styleClass="Button"></html:submit>
			
			<input type="button" class="Button" value="����" onclick="history.back()"/>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


