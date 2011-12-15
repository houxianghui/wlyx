
<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.user.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page import="com.eis.cache.*" %> 
<%@ page  contentType="text/html; charset=GBK" %>


 

<html>

<title>�û�ά��</title>


<%
	PageObject pageResult = (PageObject) request.getAttribute("pageResult");
	
	int maxPage = 1;
	if(pageResult != null)
		maxPage = pageResult.getTotalPage();
	
%>
<script language="javascript">

function doAdd(){
	//����
	window.location="User.do?act=c";
	
	
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

function doTerminate(){
	//�ϳ�
	
	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('����ѡ���¼');
		return;
	}
	
	//�ύ��
	document.forms[0].act.value='t';	
	document.forms[0].submit();
}

function doAble(){
	//����
	
	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('����ѡ���¼');
		return;
	}
	
	//�ύ��
	document.forms[0].act.value='able';	
	document.forms[0].submit();
}

function doDisable(){
	//ͣ��
	
	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('����ѡ���¼');
		return;
	}
	
	//�ύ��
	document.forms[0].act.value='disable';	
	document.forms[0].submit();
}
function exclude(){
	//ͣ��
	
	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('����ѡ���¼');
		return;
	}
	
	//�ύ��
	document.forms[0].act.value='exclude';	
	document.forms[0].submit();
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
	//ɾ��
	
	//��������ѯ����
	//����ֶγ���
	if(getStrLength(document.forms[0].user_name_f.value)>18) { 		
	        	alert('�������������벻Ҫ����18���ַ�!'); 
	        	document.forms[0].user_name_f.focus(); 
	        	return; 
	} 

	document.forms[0].act.value = "list";
	document.forms[0].requery.value='y';
	document.forms[0].submit();
}

function doSetPWD(){

	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].user_id.value == null ||document.forms[0].user_id.value == "") {
		alert('����ѡ���¼');
		return;
	}
	//״̬����������
	if(document.forms[0].status.value != 1 ) {
		alert('���ܶ�ͣ�û����ϵ��û���������');
		return;
	}
	//����ȷ����ʾ
	if(!confirm('ȷ��ҪΪ'+document.forms[0].user_name.value+'����������')) {		
		return;
	}
	document.forms[0].act.value='setpwd';
	document.forms[0].submit();

}

function setKey(rid,user_name,status) {
	
	document.forms[0].user_id.value=rid;
	
	document.forms[0].user_name.value=user_name;
	document.forms[0].status.value=status;
}



function turnPage( pagenm ) {  	 
	document.forms[0].act.value = "list";
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
}

function doPopOrg() {
	openWin("OrgPop.do?act=list&id_field=amsd_store_f&name_field=org_name","user_org_pop");
}




</script>

<body class="body_bg_grey1">


<html:form method="post" action="User.do">
<input type=hidden name=user_id>
<input type=hidden name=act value="list">
<input type=hidden name=requery >

<input type=hidden name=user_name>
<input type=hidden name=status>


<!--��ѯ���벿��-->
<table width="98%"   class=dtPanel_Line  align="center" border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td class=dtPanel_Top02 >
    	
    	<table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    	
    	<table width="100%" cellpadding="1">    		
    		
    		<tr >    			
    			<td class=BlueFont> 
    			&nbsp; ����������<html:text property="user_name_f" styleClass="Textfield" size="20" maxlength="18" />&nbsp;		
     			</td>
     			<td>�û�״̬��<html:select property="status_f" >
          		 <html:optionsCollection name="userForm" property="status_options" />
          		</html:select>
    			</td>
    			<td align=left>
    			<input	name="query" type="button" class="Button_Search" value="   " onClick="doQuery()"> &nbsp;    			
    			</td>    			
    		</tr>     			
    		
    	</table>
    	<table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
        
        </td>
  </tr>
</table>

<table  class=heightspace_top3 ><tr><td></td></tr></table>       
        


	
		<!--�б�-->
		<table width="98%"  align="center"  class="dtPanel_Line1" border="0" cellpadding="0" cellspacing="1"  >
				<tr align="center" class="dtPanel_Top01"  height="28">
					<td width="9%" >�û���</td>
					<td width="9%" >��������</td>						
					<td width="12%" >����ɫ</td>
					<td width="7%" >״̬</td>
					<td width="11%">��ʼ����</td>
					<td width="11%">ʧЧ����</td>						
					<td width="11%">��������</td>
					<td width="6%">ѡ��</td>
				</tr>
			<%
				List list = pageResult.getList();
				if(list != null ) {
					Iterator iter = list.iterator();
					String m_user_id = null;
					while (iter.hasNext()) {
						UserVO vo = (UserVO) iter.next();
						m_user_id = vo.getUser_id();
						
			%>
				<tr align="center" class="dtPanel_Main"  onclick="_clickTr( this )">						
					<td><a class=fontlink1 href="User.do?act=r&user_id=<%=m_user_id%>"><%=vo.getLogin_id()%></td>
					
					<td><%=vo.getUser_name()%></td>
					<td><%=ReDefSDicMap.getDicItemVal("0002",vo.getRole_id())%> </td>
					<td><%=SingleDicMap.getDicItemVal("0001",vo.getStatus())%> </td>
			
					<td><%=DateUtil.formatDate(vo.getBegin_dt())%></td>
					<td><%=DateUtil.formatDate(vo.getInvalid_dt())%></td>
					<td><%=DateUtil.formatDate(vo.getReg_dt())%></td>						
										
					<td><label><input type="radio" name="user"  onClick="setKey('<%=m_user_id%>','<%=vo.getUser_name()%>','<%=vo.getStatus()%>')"> </label></td>
				</tr>

				<%
						}
					}
				%>
		</table>
		
		<!--ҳ���ҳ��-->		
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td  class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
        
		
		<!--���ܰ�ť-->
		<br>
		<table width="98%"  align="center" border="0" cellspacing="1" cellpadding="0">			
			<tr >
				<td   height="28" align="center">	
						<auth:button  name="reset_pwd" id="reset_pwd"  value="��������" onClick="doSetPWD()"/>					
						<auth:button  name="add" id="user_c"  value="����" onClick="doAdd()"/>
						<auth:button  name="edit" id="user_u" value="�޸�" onClick="doEdit()"/> 			
						<auth:button  name="able" id="user_enable"  value="����" onClick="doAble()"/>
						<auth:button  name="able" id="user_enable"  value="������" onClick="exclude()"/>
						<auth:button  name="disable" id="user_disable" value="ͣ��" onClick="doDisable()"/>
						<auth:button  name="terminate" id="user_terminate" value="����" onClick="doTerminate()"/>	
						<auth:button  name="delete"  id="user_d" value="����ɾ��" onClick="doDelete()"/>												
				</td>
			</tr>	
		</table>
		
</html:form>

</body>
</html>
