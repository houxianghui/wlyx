<%@page import="com.huateng.blue.work.WorkDistribute"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>����ά��</title>
<%
	String workId = (String)request.getAttribute("workId");
%>
<script language="javascript"> 


function doAdd(){ 
	//���� 
	window.location.href="WorkDistribute.do?act=new&workId=<%=workId%>"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='edit';	 
	document.forms[0].submit(); 
} 

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[0].act.value='delete'; 
	document.forms[0].submit(); 
} 
 
function doQuery() {  
	document.forms[0].act.value = "dl"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(id_var,workId_var) { 
	document.forms[0].id.value=id_var;
	document.forms[0].workId.value=workId_var;
} 
 
function turnPage( pagenm ) {   
	document.forms[0].act.value = "dl";  
	document.forms[0].pageNO.value = pagenm;     
	document.forms[0].submit(); 
} 
function getWorkTable(){
	window.open("ProjectDistribute.do?act=display");
}
function checkScore(){
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	document.forms[0].act.value = "score"; 
	document.forms[0].submit(); 
}
function recordIssue(){
	if(document.forms[0].id.value == null ||document.forms[0].id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	window.location.href="ProjectDistribute.do?act=issueRecord&projectId="+document.forms[0].workId.value+"&id="+document.forms[0].id.value;
}
</script>
</head>
<body>

<html:form method="post" action="WorkDistribute.do">
<input type=hidden name=act value="dl">
<html:hidden property="workId"/>
<input type=hidden name=id>

<%=ViewUtil.getTitle("�������")%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td nowrap>������</td>
			<td>��Ա</td>
			<td>��ʼ����</td>
			<td>��������</td>
			<td>����״̬</td>
			<td>��ע</td>
			<td>ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			WorkDistribute vo = (WorkDistribute) iter.next();%>
		<%
		if(vo.getWorkStatus().equals("F")){
			out.print("<tr align='center' bgcolor=\"DarkSeaGreen\"");
		}else{
			out.print("<tr align=\"center\" class=\"dtPanel_Main\" onclick=\"_clickTr( this )\"");
		}
		%>>
			<td><a href="WorkDistribute.do?act=detail&id=<%=vo.getId()%>"><%=vo.getWorkId()%></a></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUserId())%></td>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			<td><%=SingleDicMap.getDicItemVal(SingleDic.WORK_STATUS, vo.getWorkStatus())%> </td>	
			<td><%=vo.getContent()%></td>	
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getId()%>','<%=vo.getWorkId()%>')">
			</label></td>
		</tr>

		<%}
}
}%>

</table>
	<%

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
			<input type="button" value="��Ա��������" class="Button" onclick="getWorkTable()"/>
			<input type="button" value="����" class="Button" onClick="doAdd()"/>
			<input type="button" value="�޸�" class="Button" onClick="doEdit()"/>
			<input type="button" value="ɾ��" class="Button" onclick="doDelete()"/>	
			<input type="button" value="�����¼" class="Button" onClick="recordIssue()"/>				
			<input type="button" value="����" class="Button" onclick="history.back()"/>	
			</td>		
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


