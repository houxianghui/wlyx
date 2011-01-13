<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.key.pmkey.PmKeyVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>��������ά��</title>  

 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="PmKey.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].tb_name.value == null ||document.forms[0].tb_name.value == "") { 
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
	if(document.forms[0].tb_name.value == null ||document.forms[0].tb_name.value == "") { 
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
 
function setPKey(tb_name_var) { 
	document.forms[0].tb_name.value=tb_name_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="PmKey.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=tb_name> 
 
  <%=ViewUtil.getTitle("��������ά��")%>
 
<table width="98%" align="center"   class=dtPanel_Line border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class=dtPanel_Top02 >    
    
    <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr > 
            <td>&nbsp; ���ݿ������<html:text property="tb_name_f" styleClass="Textfield" size="20" maxlength="20" />&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table>
        <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	   
                
        </td> 
  </tr> 
</table> 
 
	
<table  class=heightspace_top3 ><tr><td></td></tr></table>       
        
	
	<table width="98%"  class="dtPanel_Line1" align="center" border="0"  cellpadding="0" cellspacing="1"> 
			<tr align="center" class="dtPanel_Top01">
 
						<td width="20%">���ݿ����</td> 
						<td width="20%">�����ֶ�</td> 
						<td width="20%">����</td> 
						<td width="20%">��ǰ���ֵ</td> 
						<td>ѡ��</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						PmKeyVO vo = (PmKeyVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="PmKey.do?act=r&tb_name=<%=vo.getTb_name()%>"><%=vo.getTb_name()%></a></td> 
						<td><%=vo.getKey_field()%></td> 
						<td><%=vo.getStep_len()%></td> 
						<td><%=vo.getMax_val()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getTb_name()%>')"> </label></td> 
					</tr> 
 
				<% 
						} 
					} 
				%> 
			</table> 
			
			
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td  class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
 
			<br>
			<table width="98%"  align="center"  border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp; 
						<input name="edit" type="button" class="Button" value="�޸�" onClick="doEdit()"> &nbsp;  
						<input name="delete" type="button" class="Button" value="ɾ��" onClick="doDelete()"> 
						</td> 
					</tr> 
			</table> 
				
 
</html:form> 
 
</body> 
</html> 
 


