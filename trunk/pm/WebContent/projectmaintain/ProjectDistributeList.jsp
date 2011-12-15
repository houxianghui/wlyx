<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" />

<html>

<%int maxPage = 1;
if (pageResult != null)
	maxPage = pageResult.getTotalPage();
%>
<head>
<title>�ҵ���Ŀ</title>
<script language="javascript">  
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qmp";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
function doProcess(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	}
	if(document.forms[0].type.value == "T"){	//��д���������͹�ʱ
		addTaskDaily();
		return;
	}
	document.forms[0].act.value="addTask";
	document.forms[0].submit();
}
function doFinish(){
	if(document.forms[0].projectId.value == null ||document.forms[0].projectId.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 	
	if(!confirm('ִ����ɲ������㽫�޷�������д������Ĺ�ʱ��ȷ����')) { 
		return; 
	} 
	document.forms[0].act.value="doFinish";
	document.forms[0].submit();
}
function doOther(){
	
	var projectId = document.forms[0].projectId.value;
	var step = document.forms[0].status.value;
	window.location.href="TASK_LIST.do?act=c&project_no=��&curr_step=8";
}
function addTaskDaily(){
	window.location.href="WorkDaily.do?act=new";
}
function setPKey(projectId_var,status_var,id_var,type_var) { 
	document.forms[0].projectId.value=projectId_var; 
	document.forms[0].status.value=status_var;
	document.forms[0].id.value=id_var;	
	document.forms[0].type.value=type_var;
} 
</script>
</head>
<body>

<html:form method="post" action="ProjectDistribute.do">
<input type=hidden name=act value="qmp">
<html:hidden property="projectId"/>
<html:hidden property="id"/>
<html:hidden property="status"/>
<html:hidden property="type"/>

<%=ViewUtil.getTitle("�ҵ�����")%>
<%
	java.util.Calendar c = java.util.Calendar.getInstance();
	java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyyMMdd");
	String today = sf.format(c.getTime());
	String msg = "";
%>
	
	<table class=heightspace_top3>
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01" >
			<td nowrap>�������</td>			
			<td nowrap>��ʼʱ��</td>
			<td nowrap>����ʱ��</td>
			<td nowrap>��Ŀ�׶�</td>	
			<td> <bean:message key="projectDistributeForm.memo.display" bundle="projectMaintain"/></td>
			<td>����</td>	
			<td>ѡ��</td>
		</tr>
		<%if (pageResult != null) {
			StringBuffer warning = new StringBuffer("����");
			boolean needAlert = false;
	List list = pageResult.getList();
	if (list != null) {
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			com.projectmaintain.ProjectDistributeVO vo = (com.projectmaintain.ProjectDistributeVO) iter.next();%>
		<tr align="center" <%
			if(vo.getEndDate().compareTo(today)<0){
				msg = "�����Ѿ������ޣ����븺���˳�������ԭ��";
				out.print("bgcolor=\"red\"");
			}else if(vo.getEndDate().compareTo(today) == 0){
				msg = "������쵽�ڣ��뾡�����";
				out.print("bgcolor=\"yellow\"");
			}else{			
				if("1".equals(vo.getReady())){
					msg = "�����ڿ�ִ�н׶�";
					out.print("bgcolor=\"lightgreen\"");
				}else{
					msg = "";
					out.print(" class=\"dtPanel_Main\" onclick=\"_clickTr( this )\"");
				}
			}
			long days = DateUtil.getDays(today,vo.getEndDate());
			if(days<=vo.getNotifyDay() && days >=0){
				needAlert = true;
				warning.append(vo.getProjectId()+" ");
			}
		%>>
			<%
			boolean isProject = false;
			if("P".equals(vo.getType())){
				isProject = true;
			}
			if(isProject){ %>
				<td><a href="ProjectMaintain.do?act=qp&projectId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
			<%}else{%>
				<td><a href="WorkList.do?act=view&workId=<%=vo.getProjectId()%>"><%=vo.getProjectId()%></a></td>
			<%} %>
			<td><%=vo.getStartDate()%></td>
			<td><%=vo.getEndDate()%></td>
			<%if(isProject){ %>
				<td><%=SingleDicMap.getDicItemVal(SingleDic.PROJECT_MANAGE_COLLECTION,vo.getStatus())%> </td>
			<%}else{ %>
				<td><%=SingleDicMap.getDicItemVal(SingleDic.WORK_STATUS,vo.getStatus())%> </td>
			<%} %>
			<td><%=vo.getMemo()%></td>
			<td><%=msg%></td>
			<td><label><input type="radio" name="param"	onClick="setPKey('<%=vo.getProjectId()%>','<%=vo.getStatus()%>','<%=vo.getId()%>','<%=vo.getType()%>')">
			</label></td>
		</tr>

		<%}
}
	if(needAlert){
		String first = (String)request.getSession().getAttribute("firstTime");
		if(CheckUtil.isEmptry(first)){
			warning.append("�������ڣ���ע�����");
			request.getSession().setAttribute("firstTime","done");
		%>
		<script language="javascript">
		alert('<%=warning %>');
		</script>
		<%
		}
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
			<input type="button" value="��д��ʱ" class="Button" onClick="doProcess()"/>	
			<input type="button" value="������ʱ" class="Button" onClick="doOther()"/>
			<input type="button" value="�������" class="Button" onClick="doFinish()"/>
			<input type="button" value="��д����" class="Button" onclick="addTaskDaily()">
			</td>
		</tr>
	</table>
</html:form>

<p>&nbsp;</p>
</body>
</html>


