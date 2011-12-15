<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.lx.Lx_infoVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head> 
<title>��ϵ��Ϣά��</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="Lx_info.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].lx_id.value == null ||document.forms[0].lx_id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].lx_id.value == null ||document.forms[0].lx_id.value == "") { 
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
 
function setPKey(lx_id_var) { 
	document.forms[0].lx_id.value=lx_id_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="Lx_info.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=lx_id> 
 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
            
          <tr class="dtPanel_Top02"> 
      
            <td>&nbsp;���ţ�
            <html:select property="depart_f"> 
	         	<html:optionsCollection name="lx_infoForm" property="depart_f_options" />               
	        </html:select>  
            ������<html:text property="name_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
         
    
        
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td >����</td> 
						<td >����</td> 
						<td >Ա�����</td>
						<td >�칫�绰</td> 
						<td >�ƶ��绰</td> 
						<td>Email��ַ</td> 
						<td>ѡ��</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							Lx_infoVO vo = (Lx_infoVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 					
						<td><%=SingleDicMap.getDicItemVal("9990", vo.getDepart())%></td>
						<td><%=vo.getName()%></td> 
						<td><%=vo.getStuff_id()%></td> 
						<td><%=vo.getPhone()%></td> 
						<td><%=vo.getMobile()%></td> 
						<td><%=vo.getEmail()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getLx_id()%>')"> </label></td> 
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
						<auth:button name="add" id="lx_info_c" value="����" onClick="doAdd()"/> &nbsp; 
						<auth:button name="edit" id="lx_info_u" value="�޸�" onClick="doEdit()"/> &nbsp;  
						<auth:button name="delete" id="lx_info_d" value="ɾ��" onClick="doDelete()"/> 
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

