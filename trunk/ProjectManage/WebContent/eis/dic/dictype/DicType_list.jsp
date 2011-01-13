<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.dictype.DicTypeVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>�ֵ������Ϣά��</title> 
  
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="DicType.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].type_id.value == null ||document.forms[0].type_id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
		document.forms[0].act.value='u';	 
		document.forms[0].submit(); 
}

function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].type_id.value == null ||document.forms[0].type_id.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	
	//����ȷ����ʾ
	if(!confirm('��ȷ��ִ��ɾ��������')) {		
		return;
	}
	
	if(document.forms[0].dic_level.value == 1){//�����ֵ�ɾ��
		document.forms[0].act.value='d'; 
		document.forms[0].submit();
	} 
	if(document.forms[0].dic_level.value == 2){//�༶�ֵ�ɾ��
		document.forms[0].act.value='dm'; 
		document.forms[0].submit();
	} 
} 
 
function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ����
	if(!isEmpty(document.forms[0].type_id_f.value)){
		if(getStrLength(document.forms[0].type_id_f.value)>4) { 		
	        alert('�����볤���벻Ҫ����4λ!'); 
	        document.forms[0].type_id_f.focus(); 
	        return; 
		} 
	}
	//��鳤��
	if(!isEmpty(document.forms[0].type_name_f.value)){
		if(getStrLength(document.forms[0].type_name_f.value)>30) { 		
	        alert('���Ƴ����벻Ҫ����30���ַ�!'); 
	        document.forms[0].type_name_f.focus(); 
	        return; 
		} 
	}
 
 
	document.forms[0].act.value = "list"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(type_id_var,dic_level_var) { 
	document.forms[0].type_id.value=type_id_var; 
	document.forms[0].dic_level.value=dic_level_var;
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 

</script> 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="DicType.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=type_id> 
<input type=hidden name=dic_level> 
 
  <%=ViewUtil.getTitle("�����ֵ�ά��")%>
 
<table width="98%"  class=dtPanel_Line   align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02">
    
    <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr > 
            <td>
            
            &nbsp; �����룺<html:text property="type_id_f" styleClass="Textfield" size="8" maxlength="4" /> 
            &nbsp; ���ƣ�<html:text property="type_name_f" styleClass="Textfield" size="30" maxlength="30" />&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;
            
            </td>
          </tr> 
    </table>
    <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	
     
   </td> 
  </tr> 
</table> 

 
<table  class=heightspace_top3 ><tr><td></td></tr></table>       
        
		

	<table width="98%" border="0"    align="center" class="dtPanel_Line1"  cellpadding="0" cellspacing="1"> 
			<tr align="center"  class="dtPanel_Top01" > 
					<td width="20%">������</td> 
					<td width="45%">����</td> 
					<td width="25%">�����־</td> 
					<td >ѡ��</td> 
 
			</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						DicTypeVO vo = (DicTypeVO) iter.next(); 
			%> 
				<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1  href="DicType.do?act=r&type_id=<%=vo.getType_id()%>"><%=vo.getType_id()%></a></td> 
						<td><%=vo.getType_name()%></td> 
						
						<%if(vo.getDic_level().equals("1")){%>
						
							<td>�����ֵ�</td> 
						<%}else{%>
							<td>�༶�ֵ�</td>
						<%}%>
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getType_id()%>','<%=vo.getDic_level()%>')"> </label></td> 
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
	<table width="98%"   align="center" border="0" cellspacing="0" cellpadding="0"> 
		<tr> 
			<td height="25" align="center"> 
					 
					<auth:button name="add" id="dicType_c"  value="����" onClick="doAdd()"/>&nbsp;
					<auth:button name="edit" id="dicType_u"  value="�޸�" onClick="doEdit()"/>&nbsp;
					<auth:button name="delete" id="dicType_d"  value="ɾ��" onClick="doDelete()"/>
			</td> 
		</tr> 
	</table> 
	
 
</html:form> 
 
</body> 
</html> 
 


