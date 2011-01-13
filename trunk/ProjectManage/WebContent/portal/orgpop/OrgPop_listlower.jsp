

<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.orgpop.OrgPopVO" %>
<%@ page import="com.eis.portal.orgpop.*" %>
<%@ page import="com.eis.cache.*" %> 
<%@ page import="com.eis.portal.UserContext" %> 
<%@ page import="java.util.*"%>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="orgPopForm" scope="request" class="com.eis.portal.orgpop.OrgPopForm" /> 

<html>

<title>机构查询弹出窗口</title>


<script language="javascript">



function doQuery() {
	if(isEmpty(document.forms[0].amsd_name_line_1.value) && isEmpty(document.forms[0].amsd_store2.value)){
		alert('请输入机构名称或机构代码！');
		document.forms[0].amsd_name_line_1.focus();
		return false;
	}
	//检查输入查询条件
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
<input type=hidden name=act value="listlower">
<input type=hidden name=requery >
<html:hidden property="id_field"/>
<html:hidden property="name_field"/>



<table width="98%" class="dtPanel_Line"  align="center" border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td class="dtPanel_Top02">
            
          <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
    <table width="100%" >
       <tr >
          <td>&nbsp; 机构名称：<html:text property="amsd_name_line_1" styleClass="Textfield" size="20" maxlength="30" /> 
            &nbsp; 机构代码：<html:text property="amsd_store2" styleClass="Textfield" size="15" maxlength="9" />&nbsp;&nbsp;            
          <input	name="query" type="button" class="Button" value="查询" onClick="doQuery()"> &nbsp;</td>            
       </tr>          
     </table>
     <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
        </td>
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
						"OrgPop.do?act=listlower&
						id_field=<%=orgPopForm.getId_field()%>&
						name_field=<%=orgPopForm.getName_field()%>&
						amsd_storeNo=<%=ovo.getAmsd_storeNo()%>&
						amsd_store_level_c2=<%=ovo.getAmsd_store_level_c2()%>">
				<%=OrgMap.getOrgName(ovo.getAmsd_storeNo())%></a>
		
	<%		}
		
			for(int i=1; i< s; i++){
			
				OrientListVO ovo = (OrientListVO)orient_list.get(i) ;
		
	%>					<%=" --> "%>
						<a class=fontlink1 href=
						"OrgPop.do?act=listlower&
						id_field=<%=orgPopForm.getId_field()%>&
						name_field=<%=orgPopForm.getName_field()%>&
						amsd_storeNo=<%=ovo.getAmsd_storeNo()%>&
						amsd_store_level_c2=<%=ovo.getAmsd_store_level_c2()%>">
				<%=OrgMap.getOrgName(ovo.getAmsd_storeNo())%></a>
	<%	}
	}
	%></td>
	</tr>
</table>
		<table  class=heightspace_top3 ><tr><td></td></tr></table>
		<table width="98%"  class="dtPanel_Line1"   align="center"  border="0" cellpadding="0" cellspacing="1">
					<tr align="center"  class="dtPanel_Top01">						
						<td width="15%">机构代码</td>
						<td width="28%">机构名称</td>
						<!--td width="28%">上级机构</td-->
						<td width="10%">机构城市</td>
						<td width="10%">机构级别</td>
						<td width="9%">选择</td>
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
						<!--td><%=OrgMap.getOrgName(vo.getAmsd_store_pn3())%></td-->						
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
