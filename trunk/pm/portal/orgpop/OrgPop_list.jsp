

<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.orgpop.OrgPopVO" %>
<%@ page import="com.eis.portal.orgpop.*" %>
<%@ page import="com.eis.cache.*" %> 
<%@ page import="com.eis.portal.UserContext" %> 
<%@ page import="javax.servlet.http.HttpSession" %> 
<%@ page import="java.util.*"%>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="orgPopForm" scope="request" class="com.eis.portal.orgpop.OrgPopForm" /> 

<html>

<title>������ѯ��������</title>



<script language="javascript">



function doQuery() {
	if(isEmpty(document.forms[0].amsd_name_line_1.value) && isEmpty(document.forms[0].amsd_store2.value)){
		alert('������������ƻ�������룡');
		document.forms[0].amsd_name_line_1.focus();
		return false;
	}
	//��������ѯ����
	if(!isEmpty(document.forms[0].amsd_name_line_1.value)){
		if(getStrLength(document.forms[0].amsd_name_line_1.value)>40) { 		
	        alert('�������Ƴ����벻Ҫ����40���ַ�!'); 
	        document.forms[0].amsd_name_line_1.focus(); 
	        return; 
		}
	}
	if(!isEmpty(document.forms[0].amsd_store2.value)){
		if(!isInteger(document.forms[0].amsd_store2.value)){
			alert("��������Ҫ����������!");
			document.forms[0].amsd_store2.focus();
			return;
		} 			
	}
	if(!isEmpty(document.forms[0].amsd_store2.value)){
		if(getStrLength(document.forms[0].amsd_store2.value)>40) { 		
	        	alert('�������볤���벻Ҫ����9λ!'); 
	        	document.forms[0].amsd_store2.focus(); 
	        	return; 
		}  			
	}
	
	

	document.forms[0].act.value = "queryl";
	document.forms[0].submit();
}


function setKey(id,name) {		
	opener.document.forms[0].<%=orgPopForm.getId_field()%>.value=id;
	opener.document.forms[0].<%=orgPopForm.getName_field()%>.value=name;	 

	//opener.document.forms[0].submit(); 	 	
	window.close();

}





</script>

<body   class="body_bg_grey1">


<html:form method="post" action="OrgPop.do">
<input type=hidden name=amsd_store>
<input type=hidden name=act value="list">

<html:hidden property="id_field"/>
<html:hidden property="name_field"/>

<table width="98%" class="dtPanel_Line"  align="center" border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td class="dtPanel_Top02">
            
          <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
    <table width="100%" >
       <tr >
          <td>&nbsp; �������ƣ�<html:text property="amsd_name_line_1" styleClass="Textfield" size="20" maxlength="30" /> 
            &nbsp; �������룺<html:text property="amsd_store2" styleClass="Textfield" size="15" maxlength="9" />&nbsp;&nbsp;            
          <input	name="query" type="button" class="Button" value="��ѯ" onClick="doQuery()"> &nbsp;</td>            
       </tr>          
     </table>
     <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
        </td>
  </tr>
</table>
									
	<table  class=heightspace_top3 ><tr><td></td></tr></table>
	

<table width="98%"    align="center"  border="0" cellpadding="0" cellspacing="1">
	<tr align="left" ><td>
			<a class=fontlink1 href=
						"OrgPop.do?act=listlower&
						id_field=<%=orgPopForm.getId_field()%>&
						name_field=<%=orgPopForm.getName_field()%>&
						amsd_storeNo=<%=orgPopForm.getUserorgid()%>&
						amsd_store_level_c2=<%=orgPopForm.getUserlevel()%>">
	<%=OrgMap.getOrgName(orgPopForm.getUserorgid())%></a>
 
	</td></tr>
</table>		
		<table  class=heightspace_top3 ><tr><td></td></tr></table>
		<table width="98%"  class="dtPanel_Line1"   align="center"  border="0" cellpadding="0" cellspacing="1">
					<tr align="center"  class="dtPanel_Top01">						
						<td width="15%">��������</td>
						<td width="28%">��������</td>
						<!--td width="28%">�ϼ�����</td-->
						<td width="10%">��������</td>
						<td width="10%">��������</td>
						<td width="9%">ѡ��</td>
					</tr>
			<% 
				//List list = pageResult.getList();
				List list = (List)request.getAttribute("List");		
				if(list != null ) {

					Iterator iter = list.iterator();
					String m_amsd_store = null;
					while (iter.hasNext()) {
						OrgPopVO vo = (OrgPopVO) iter.next();
						m_amsd_store = vo.getAmsd_store();
						
			%>
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">						
						
					<%
						if(vo.getAmsd_store_level_c2().equals("0")){
					%>	
							<td><%=m_amsd_store%></td>
					<%}else{%>
						<td><a class=fontlink1 href=
						"OrgPop.do?act=listlower&
						id_field=<%=orgPopForm.getId_field()%>&
						name_field=<%=orgPopForm.getName_field()%>&
						amsd_storeNo=<%=vo.getAmsd_store()%>&
						amsd_store_level_c2=<%=vo.getAmsd_store_level_c2()%>">
						<%=m_amsd_store%>
						</a></td>
					<%}%>	
						<td><%=vo.getAmsd_name_line_1()%></td>
											
						<td><%=vo.getAmsd_city_state()%></td>
						<td><%=SingleDicMap.getDicItemVal("0004",vo.getAmsd_store_level_c2())%></td>
						<td><label><input type="radio" name="role"  onClick="setKey('<%=m_amsd_store%>','<%=vo.getAmsd_name_line_1()%>')"> </label></td>
					</tr>


				<%
						}
					} 
				%>
			</table>
			
	
		
		
</html:form>
<p>&nbsp;</p>

</body>
</html>