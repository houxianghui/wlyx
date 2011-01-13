<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.PROJECT_LISTVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>��ǰ��Ŀά��</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="PROJECT_LIST.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
function doProcess(){
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 	
	var project_no = document.forms[0].project_no.value;
	var step = document.forms[0].curr_step.value;
	window.location="TASK_LIST.do?act=c&project_no="+project_no+"&curr_step="+step;
}
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[0].act.value='d'; 
	document.forms[0].submit(); 
} 
 
function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ���� 
 
 
	document.forms[0].act.value = "list"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(user_id_var,project_no_var,curr_step_var) { 
	document.forms[0].user_id.value=user_id_var; 
	document.forms[0].project_no.value=project_no_var; 
	document.forms[0].curr_step.value=curr_step_var;
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="PROJECT_LIST.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=user_id> 
<input type=hidden name=project_no> 
<input type=hidden name=curr_step>
 
 
<table width="98%" class="dtPanel_Line1" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line1"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top01">
         <td align="center">��ǰ��Ŀά��</td>
         </tr>
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						
						<td>��Ŀ���</td> 
						<td>��Ŀ����</td> 
						<td>��ǰ�׶�</td> 
						<td>��ʼ����</td> 
						<td>��������</td> 
						<td>ѡ��</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							PROJECT_LISTVO vo = (PROJECT_LISTVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="PROJECT_LIST.do?act=r&user_id=<%=vo.getUser_id()%>&project_no=<%=vo.getProject_no()%>"><%=vo.getProject_no()%></a></td> 
						<td><%=vo.getProject_name()%></td> 
						<td><%=SingleDicMap.getDicItemVal("9994",vo.getCurr_step())%></td> 
						<td><%=vo.getStart_date()%></td> 
						<td><%=vo.getEnd_date()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getUser_id()%>','<%=vo.getProject_no()%>','<%=vo.getCurr_step()%>')"> </label></td> 
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
				</table> 
			<% 
				//������ҳ��ע 
				if(pageResult != null) { 
			%> 
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
			<% 
				} 
			%> 
			<br> 
 
				<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<auth:button name="add" id="pROJECT_LIST_c" value="����" onClick="doAdd()"/> &nbsp; 
						<auth:button name="edit" id="pROJECT_LIST_c" value="�޸�" onClick="doEdit()"/> &nbsp;  
						<auth:button name="delete" id="pROJECT_LIST_c" value="ɾ��" onClick="doDelete()"/> &nbsp;
						<auth:button name="process" id="tASK_LIST_c" value="ִ��" onClick="doProcess()"></auth:button>
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

