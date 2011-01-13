<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.maintainrecord.Maintain_recordVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>����֧�ַ����¼ά��</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="Maintain_record.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].seq_no.value == null ||document.forms[0].seq_no.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
function doResp(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].seq_no.value == null ||document.forms[0].seq_no.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='p';	 
	document.forms[0].submit(); 
} 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].seq_no.value == null ||document.forms[0].seq_no.value == "") { 
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
 
function setPKey(seq_no_var) { 
	document.forms[0].seq_no.value=seq_no_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="Maintain_record.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=seq_no> 
 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; ����ʱ�䣺
            	<html:text property="qus_date_f" styleClass="Textfield" size="20" maxlength="8" />&nbsp;��������
            	<html:select property="res_result_f">
		         	<html:optionsCollection name="maintain_recordForm" property="res_result_c"/>
		        </html:select> 
             	<input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td>˳���</td> 
						<td>�������</td> 
						<td>����˻�������</td> 
						<td>��������</td> 
						<td>����Ա</td> 
						<td>״̬</td> 
						<td>���ù�ʱ</td> 			
						<td>ѡ��</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							Maintain_recordVO vo = (Maintain_recordVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="Maintain_record.do?act=r&seq_no=<%=vo.getSeq_no()%>"><%=vo.getSeq_no()%></a></td> 
						<td><%=vo.getQus_date()%></td> 
						<td><%=vo.getQus_user()%></td> 
						<td><%=vo.getQus_info()%></td> 
						<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getRes_user())%></td> 
						<td><%=SingleDicMap.getDicItemVal("9993",vo.getRes_result())%></td> 
						<td><%=vo.getRes_cost()%></td> 
	 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getSeq_no()%>')"> </label></td> 
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
						<auth:button name="add" id="maintain_record_c" value="����" onClick="doAdd()"/> &nbsp; 
						<auth:button name="edit" id="maintain_record_c" value="�޸�" onClick="doEdit()"/> &nbsp;  
						<auth:button name="delete" id="maintain_record_c" value="ɾ��" onClick="doDelete()"/>&nbsp;
						<auth:button name="resp" id="maintain_record_p" value="��" onClick="doResp()"/> &nbsp;
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

