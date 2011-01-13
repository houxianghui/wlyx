<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.redefsdic.ReDefSDicVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>�Զ��嵥���ֵ�ά��</title> 

 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="ReDefSDic.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].type_id.value == null ||document.forms[0].type_id.value == "") { 
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
	if(document.forms[0].type_id.value == null ||document.forms[0].type_id.value == "") { 
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
 
function setPKey(type_id_var) { 
	document.forms[0].type_id.value=type_id_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="ReDefSDic.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=type_id> 
 
   <%=ViewUtil.getTitle("�Զ��嵥���ֵ�ά��")%>
 
<table width="98%"  class=dtPanel_Line align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class=dtPanel_Top02>     
    
    	<table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
    	<table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         	<tr > 
            	<td>&nbsp; �ֵ����ݣ�<html:text property="caption_f" styleClass="Textfield" size="20" maxlength="20" />&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;</td> 
          	</tr> 
     	</table> 
    	<table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
     
     
     </td> 
  </tr> 
</table> 
 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table>       
        		
	
		<table width="98%" class="dtPanel_Line1" align="center"  border="0" cellpadding="0" cellspacing="1"> 
			<tr align="center" class="dtPanel_Top01"> 
 
						<td width="10%">������</td> 
						<td width="20%">�ֵ�����</td> 
						<td width="35%">��ע</td> 
						<td width="10%">������Ա</td> 
						<td width="15%">��������</td> 
						<td width="10%">ѡ��</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						ReDefSDicVO vo = (ReDefSDicVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1  href="ReDefSDic.do?act=r&type_id=<%=vo.getType_id()%>"><%=vo.getType_id()%></a></td> 
						<td><%=vo.getCaption()%></td> 
						<td><%=vo.getMemo()%></td> 
						<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getUser_id())%></td> 
						<td><%=DateUtil.formatDate(vo.getReg_dt())%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getType_id()%>')"> </label></td> 
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
			
				<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<auth:button name="add" id="reDefSDic_c"  value="����" onClick="doAdd()"/>&nbsp;
						<auth:button name="edit" id="reDefSDic_u"  value="�޸�" onClick="doEdit()"/>&nbsp;
						<auth:button name="delete" id="reDefSDic_d"  value="ɾ��" onClick="doDelete()"/>
						</td> 
					</tr> 
				</table> 
		
 
</html:form> 
 
 
</body> 
</html> 
 

