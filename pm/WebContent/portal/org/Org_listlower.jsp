<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.cache.*" %>  
<%@ page  contentType="text/html; charset=GBK" %> 
 
 
<html> 
 
<title>��������ά��</title> 
 
 
<script language="javascript"> 
 

function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ����
	if(isEmpty(document.forms[0].amsd_name_line_1.value) && isEmpty(document.forms[0].amsd_store2.value)){
		alert('������������ƻ�������룡');
		document.forms[0].amsd_name_line_1.focus();
		return false;
	}
	//����ֶγ���
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
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(amsd_store_var,amsd_storename_var) { 
	document.forms[0].amsd_store.value=amsd_store_var; 
	document.forms[0].amsd_storename.value=amsd_storename_var;
}   
 
function doOrgMenuPerm(){
	//�����˵�Ȩ�޹���
	
	//����Ƿ���ѡ�еļ�¼
	if(document.forms[0].amsd_store.value == null ||document.forms[0].amsd_store.value == "") {
		alert('����ѡ���¼');
		return;
	}
	
	//�������˵�����ҳ��		
	window.location="OrgMenu.do?act=u&amsd_store="+document.forms[0].amsd_store.value+"&amsd_storename="+document.forms[0].amsd_storename.value;
}
 
</script> 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="Org.do"> 
<input type=hidden name=amsd_store>
<input type=hidden name=amsd_storename> 
<input type=hidden name=act value="listlower">
<input type=hidden name=requery >
<html:hidden property="id_field"/>
<html:hidden property="name_field"/>

  <table width="98%"  align="center" class="dtPanel_Line"  border="0" cellspacing="1" cellpadding="0"> 
         <tr > 
            <td class="dtPanel_Top02">            
           
            <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
            <table width="98%"  border="0" cellspacing="1" cellpadding="0">    			
    			<tr>
    				<td> 
             &nbsp;�������룺<html:text property="amsd_store2" styleClass="Textfield" size="15" maxlength="9" />
             &nbsp;�������ƣ�<html:text property="amsd_name_line_1" styleClass="Textfield" size="36" maxlength="40" />           
            &nbsp;<input name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> 
            		</td>
            	</tr>            	
            </table>             
            <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
                      
          </tr> 
    </table> 
    
    	<table  class=heightspace_top3 ><tr><td></td></tr></table>
	

<table width="98%"    align="center"  border="0" cellpadding="0" cellspacing="1">
	<tr align="left"  ><td>
	<%
		List orient_list = (List)request.getAttribute("o_list");
		int s = orient_list.size();
		
		if(orient_list != null ) {
			if(s>=1){
	%>					<a class=fontlink1 href=
						"Org.do?act=listlower&
						amsd_storeNo=<%=ovo.getAmsd_storeNo()%>&
						amsd_store_level_c2=<%=ovo.getAmsd_store_level_c2()%>">
		
	<%		}
		
			for(int i=1; i< s; i++){
			
		
	%>					<%=" --> "%>
						<a class=fontlink1 href=
						"Org.do?act=listlower&
						amsd_storeNo=<%=ovo.getAmsd_storeNo()%>&
						amsd_store_level_c2=<%=ovo.getAmsd_store_level_c2()%>">
	<%	}
	}
	%></td>
	</tr>
</table>

<table  class=heightspace_top3 ><tr><td></td></tr></table>
	

		<table width="98%"  align="center"  class="dtPanel_Line1" border="0" cellpadding="0" cellspacing="1"> 
			<tr align="center" class="dtPanel_Top01"> 
 
						<td width="13%">��������</td>						
						<td width="24%">��������</td>
						<td width="10%">����</td>
						<td width="13%">�ϼ���������</td>
						<td width="24%">�ϼ���������</td>
						<td width="10%">����</td>
						<td width="6%">ѡ��</td> 
 
					</tr> 
			<% 
				//List list = pageResult.getList();
				List list = (List)request.getAttribute("List");		 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					String m_amsd_store = null;
					while (iter.hasNext()) { 
						m_amsd_store = vo.getAmsd_store();
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						
					<%
						if(vo.getAmsd_store_level_c2().equals("0")){
					%>	
							<td><%=m_amsd_store%></td>
					<%}else{%>
						<td><a class=fontlink1 href=
						"Org.do?act=listlower&
						amsd_storeNo=<%=vo.getAmsd_store()%>&
						amsd_store_level_c2=<%=vo.getAmsd_store_level_c2()%>">
						<%=m_amsd_store%>
						</a></td>
					<%}%>


						<td><a class=fontlink1 href="Org.do?act=r&amsd_store=<%=vo.getAmsd_store()%>"><%=vo.getAmsd_name_line_1()%></a></td>
						<td><%=SingleDicMap.getDicItemVal("0004",vo.getAmsd_store_level_c2())%></td> 
						<td><%=vo.getAmsd_store_pn3()%></td>
						<td><%=vo.getAmsd_city_state()%></td>

						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getAmsd_store()%>','<%=vo.getAmsd_name_line_1()%>')"> </label></td> 
					</tr> 
 
				<% 
						} 
					} 
				%> 
	</table> 

            
 	<br>
	<table width="98%"  align="center" border="0" cellspacing="0" cellpadding="0"> 
			<tr> 
						<td height="25" align="center"> 
						<auth:button name="orgMenuPerm" id="org_perm" value="Ȩ�޹���" onClick="doOrgMenuPerm()"/> &nbsp;		
						</td> 
			</tr> 
	</table> 
			
 
</html:form> 
 

</body> 
</html> 
 

