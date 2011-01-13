<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.org.OrgVO" %>
<%@ page import="com.eis.portal.org.*" %>
<%@ page import="com.eis.cache.*" %>  
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="orgForm" scope="request" class="com.eis.portal.org.OrgForm" /> 
 
<html> 
 
<title>机构管理维护</title> 
 
 
<script language="javascript"> 
 

function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件
	if(isEmpty(document.forms[0].amsd_name_line_1.value) && isEmpty(document.forms[0].amsd_store2.value)){
		alert('请输入机构名称或机构代码！');
		document.forms[0].amsd_name_line_1.focus();
		return false;
	}
	//检查字段长度
	if(!isEmpty(document.forms[0].amsd_name_line_1.value)){
		if(getStrLength(document.forms[0].amsd_name_line_1.value)>40) { 		
	        alert('机构名称长度请不要超过40个字符!'); 
	        document.forms[0].amsd_name_line_1.focus(); 
	        return; 
		}			
	}
	
	if(!isEmpty(document.forms[0].amsd_store2.value)){
		if(!isInteger(document.forms[0].amsd_store2.value)){
			alert("机构代码要求输入数字!");
			document.forms[0].amsd_store2.focus();
			return;
		} 			
	}
	if(!isEmpty(document.forms[0].amsd_store2.value)){
		if(getStrLength(document.forms[0].amsd_store2.value)>40) { 		
	        	alert('机构代码长度请不要超过9位!'); 
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
	//机构菜单权限管理
	
	//检查是否有选中的纪录
	if(document.forms[0].amsd_store.value == null ||document.forms[0].amsd_store.value == "") {
		alert('请先选择纪录');
		return;
	}
	
	//到机构菜单管理页面		
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
             &nbsp;机构代码：<html:text property="amsd_store2" styleClass="Textfield" size="15" maxlength="9" />
             &nbsp;机构名称：<html:text property="amsd_name_line_1" styleClass="Textfield" size="36" maxlength="40" />           
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
				OrientListVO ovo = (OrientListVO)orient_list.get(0) ;
	%>					<a class=fontlink1 href=
						"Org.do?act=listlower&
						id_field=<%=orgForm.getId_field()%>&
						name_field=<%=orgForm.getName_field()%>&
						amsd_storeNo=<%=ovo.getAmsd_storeNo()%>&
						amsd_store_level_c2=<%=ovo.getAmsd_store_level_c2()%>">
				<%=OrgMap.getOrgName(ovo.getAmsd_storeNo())%></a>
		
	<%		}
		
			for(int i=1; i< s; i++){
			
				OrientListVO ovo = (OrientListVO)orient_list.get(i) ;
		
	%>					<%=" --> "%>
						<a class=fontlink1 href=
						"Org.do?act=listlower&
						id_field=<%=orgForm.getId_field()%>&
						name_field=<%=orgForm.getName_field()%>&
						amsd_storeNo=<%=ovo.getAmsd_storeNo()%>&
						amsd_store_level_c2=<%=ovo.getAmsd_store_level_c2()%>">
				<%=OrgMap.getOrgName(ovo.getAmsd_storeNo())%></a>
	<%	}
	}
	%></td>
	</tr>
</table>

<table  class=heightspace_top3 ><tr><td></td></tr></table>
	

		<table width="98%"  align="center"  class="dtPanel_Line1" border="0" cellpadding="0" cellspacing="1"> 
			<tr align="center" class="dtPanel_Top01"> 
 
						<td width="13%">机构代码</td>						
						<td width="24%">机构名称</td>
						<td width="10%">类型</td>
						<td width="13%">上级机构代码</td>
						<td width="24%">上级机构名称</td>
						<td width="10%">城市</td>
						<td width="6%">选择</td> 
 
					</tr> 
			<% 
				//List list = pageResult.getList();
				List list = (List)request.getAttribute("List");		 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					String m_amsd_store = null;
					while (iter.hasNext()) { 
						OrgVO vo = (OrgVO) iter.next(); 
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
						id_field=<%=orgForm.getId_field()%>&
						name_field=<%=orgForm.getName_field()%>&
						amsd_storeNo=<%=vo.getAmsd_store()%>&
						amsd_store_level_c2=<%=vo.getAmsd_store_level_c2()%>">
						<%=m_amsd_store%>
						</a></td>
					<%}%>


						<td><a class=fontlink1 href="Org.do?act=r&amsd_store=<%=vo.getAmsd_store()%>"><%=vo.getAmsd_name_line_1()%></a></td>
						<td><%=SingleDicMap.getDicItemVal("0004",vo.getAmsd_store_level_c2())%></td> 
						<td><%=vo.getAmsd_store_pn3()%></td>
						<td><%=OrgMap.getOrgName(vo.getAmsd_store_pn3())%></td>
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
						<auth:button name="orgMenuPerm" id="org_perm" value="权限管理" onClick="doOrgMenuPerm()"/> &nbsp;		
						</td> 
			</tr> 
	</table> 
			
 
</html:form> 
 

</body> 
</html> 
 

