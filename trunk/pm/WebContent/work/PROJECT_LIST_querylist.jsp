<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.PROJECT_LISTVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>��Ŀ�б��ѯ</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ���� 
 
 
	document.forms[0].act.value = "ql"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "ql";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="PROJECT_LIST.do"> 
<input type=hidden name=act value="ql"> 
<input type=hidden name=requery > 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
    	<tr class="dtPanel_Top01">
    		<td align="center">��ʷ��Ŀ��ѯ</td>
    	</tr>
         <tr class="dtPanel_Top02"> 
         <td>&nbsp;��Ŀ��ţ�<html:text property="project_no_f" maxlength="4" styleClass="TextField"/>&nbsp;��Ŀ�׶Σ�
         <html:select property="curr_step_f">
         	<html:optionsCollection name="pROJECT_LISTForm" property="curr_step_c"/>
         </html:select>
         &nbsp;<input name="query" type="button" class="Button" value="����" onClick="doQuery()">
         </td>
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
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

