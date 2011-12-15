<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.TASK_LISTVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>�����б�ά��</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="TASK_LIST.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	if(document.forms[0].type.value == 'T'){
 		window.location.href="WorkDaily.do?act=edit&date="+document.forms[0].date.value;
 		return;
 	}
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].task_no.value == null ||document.forms[0].task_no.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
 
function doDelete() { 
	//ɾ�� 
 	if(document.forms[0].type.value == 'T'){
 		window.location.href="WorkDaily.do?act=delete&date="+document.forms[0].date.value;
 		return;
 	}
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].task_no.value == null ||document.forms[0].task_no.value == "") { 
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
 
function setPKey(task_no_var,date_var,type_var) { 
	document.forms[0].task_no.value=task_no_var; 
	document.forms[0].date.value=date_var;
	document.forms[0].type.value=type_var;
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="TASK_LIST.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=task_no> 
<input type="hidden" name="date">
 <input type="hidden" name="type">
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top01"> 
            <td align="center">���ܹ���ά��</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td >�������</td> 
						<td>��������</td> 
						<td>��Ŀ���</td>
						<td >��Ŀ�׶�</td> 
						
						<td >������ɶ�</td> 
						<td >��ʱ</td> 
						<td>ѡ��</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							TASK_LISTVO vo = (TASK_LISTVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<%if("P".equals(vo.getType())){ %>
							<td><%=SingleDicMap.getDicItemVal("9992",vo.getTask_type())%></td> 
							<td><a class=fontlink1 href="TASK_LIST.do?act=r&task_no=<%=vo.getTask_no()%>"><%=vo.getTask_date()%></a></td>
						<%}else{ %>
							<td>������</td> 
							<td><a class=fontlink1 href="WorkDaily.do?act=view&date=<%=vo.getTask_date()%>"><%=vo.getTask_date()%></a></td>
						<%} %>
						 
						<td><%=vo.getProject_no()%></td>
						<td><%=SingleDicMap.getDicItemVal("9994",vo.getTask_step())%></td> 
						
						<td><%=SingleDicMap.getDicItemVal("9991",vo.getTask_goal())%></td> 
						<td><%=vo.getTask_cost()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getTask_no()%>','<%=vo.getTask_date()%>','<%=vo.getType()%>')"> </label></td> 
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
						<auth:button name="edit" id="tASK_LIST_c" value="�޸�" onClick="doEdit()"/> &nbsp;  
						<auth:button name="delete" id="tASK_LIST_c" value="ɾ��" onClick="doDelete()"/> 
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

