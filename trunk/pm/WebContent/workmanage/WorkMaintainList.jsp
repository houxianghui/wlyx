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
<title>����ά��</title>

<script language="javascript"> 
function findConflick(){
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	document.forms[0].act.value='gcp';	 
	document.forms[0].submit(); 	
}
function editProgram(){
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	window.location.href="WorkList.do?act=edit&&workId="+document.forms[0].workId.value;
}
function doAdd(){ 
	//���� 
	window.location="WorkListInit.do"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='initEdit';	 
	document.forms[0].submit(); 
} 
function doEditPreProject(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='epw';	 
	document.forms[0].submit(); 
} 
function doDistribute(){ 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	window.location.href="WorkDistribute.do?act=dl&&workId="+document.forms[0].workId.value;
} 
function requireChange(){ 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	window.location.href="RequireChange.do?act=list&&projectId="+document.forms[0].workId.value;
} 
function doDelete() { 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].workId.value == null ||document.forms[0].workId.value == "") { 
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
	document.forms[0].act.value = "list";
	document.forms[0].submit(); 
} 
 
function setPKey(projectId_var) { 
	document.forms[0].workId.value=projectId_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
</head>
<body>
<script type="text/javascript" src="js/calendar.js"></script>

<html:form method="post" action="WorkList.do">
<input type=hidden name=act value="list">
<html:hidden property="workId"/>
<%=ViewUtil.getTitle("����ά��")%>
	
	<table class=heightspace_top3 width="98%" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr>
			<td>
			������
			<html:text property="workId_f" styleClass="Textfield" size="4"></html:text>
			
			������ڣ�<html:text property="endDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
			���������<html:text property="day" styleClass="Textfield" size='4'/>
			<input	name="query" type="button" class="Button_Search"  onclick="doQuery()">
			</td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="left" class="dtPanel_Top01" height="28">
			<td nowrap>�����л�</td>
			<td nowrap>&nbsp;������</td>
			<td nowrap>&nbsp;��������</td>
			<td nowrap>&nbsp;����״̬</td>
			<td nowrap>&nbsp;����ʼ����</td>
			<td nowrap>&nbsp;�����������</td>
			<td>&nbsp;��ע</td>
			<td align="center">ѡ��</td>

		</tr>
		<%if (pageResult != null) {
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			WorkList vo = (WorkList) iter.next();%>
		<tr align="left" class="dtPanel_Main" onclick="_clickTr( this )">
			<td>&nbsp;<input type="button" class="Button" value="<%="N".equals(vo.getIsMain())?"һ��":"��Ҫ" %>" onclick="window.location.href='WorkList.do?act=setMain&workId=<%=vo.getWorkId()%>&isMain=<%=vo.getIsMain()%>'"></td>
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
//������ҳ��ע 
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
			<input type="button" value="�л�����״̬" class="Button" onclick="setMain()">
			<input type="button" value=������ class="Button" onclick="requireChange()"/>
			<input type="button" value="��������" class="Button" onClick="doAdd()"/>
			<input type="button" value="�޸�����" class="Button" onClick="doEdit()"/>
			<input type="button" value="ɾ������" class="Button" onclick="doDelete()"/>
			<input type="button" value="�޸�ǰ������" class="Button" onclick="doEditPreProject()"/>
			
			<input type="button" value="�������" class="Button" onclick="doDistribute()"></td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


