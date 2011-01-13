<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.homepage.HomePageVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>首页模板维护</title> 
 

 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="HomePage.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].templ_id.value == null ||document.forms[0].templ_id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
 
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].templ_id.value == null ||document.forms[0].templ_id.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	
	//进行确认提示
	if(!confirm('您确认执行删除操作？')) {		
		return;
	}
	
	document.forms[0].act.value='d'; 
	document.forms[0].submit(); 
} 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 	//检查字段长度
	if(getStrLength(document.forms[0].caption.value)>40) { 		
	        	alert('模板名称长度请不要超过40个字符!'); 
	        	document.forms[0].caption.focus(); 
	        	return; 
	}
 
	document.forms[0].act.value = "list"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(templ_id_var) { 
	document.forms[0].templ_id.value=templ_id_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body class="body_bg_grey1"> 
 
<html:form method="post" action="HomePage.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=templ_id> 
 
 

    <table width="98%" class="dtPanel_Line"   align="center"  border="0" cellspacing="1" cellpadding="0"> 
         <tr > 
            <td align=center class="dtPanel_Top02">
            
            <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
            <table width="98%"  border="0" cellspacing="1" cellpadding="0">    			
    			<tr>
    				<td > 
            		&nbsp;&nbsp; 模板名称：<html:text property="caption" styleClass="Textfield" size="30" maxlength="40" />&nbsp;&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;
            		</td> 
          		</tr>           
          	</table>          	
          	<table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
          	
          	</td>
         </tr>
     </table>  
				
	<table  class=heightspace_top3 ><tr><td></td></tr></table>

		<table width="98%"  class="dtPanel_Line1"  align="center"  border="0" cellpadding="0" cellspacing="1"> 
					<tr align="center" class="dtPanel_Top01"> 
 
						<td width="15%">模板编号</td> 
						<td width="20%">模板名称</td> 
						<td >URL</td> 
						<td width="10%">选择</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  {
					List list = pageResult.getList(); 
					if(list != null && list.size()>0) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							HomePageVO vo = (HomePageVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="HomePage.do?act=r&templ_id=<%=vo.getTempl_id()%>"><%=vo.getTempl_id()%></a></td> 
						<td><%=vo.getCaption()%></td> 
						<td><%=vo.getUrl()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getTempl_id()%>')"> </label></td> 
					</tr> 
 
				<% 
							} 
						} 
					}
				%> 
		</table> 
				
		
		<% 
				//产生翻页脚注				
				if(pageResult != null) {
			%> 						
				
			<table width="98%"  align="center"   border="0" cellspacing="0" cellpadding="0"> 
				<tr> 
					<td  class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table>          
				
			<% 					
				} 
			%> 
 
 		<br>
				<table width="98%"  align="center"  border="0" cellpadding="0" cellspacing="1"> 
					<tr>					
						<td  height="28" align="center"> 
						<auth:button	name="add" id="homepage_c" value="增加" onClick="doAdd()"/> &nbsp; 
						<auth:button name="edit" id="homepage_u" value="修改" onClick="doEdit()"/> &nbsp;  
						<auth:button name="delete"  id="homepage_d" value="删除" onClick="doDelete()"/> 
						</td> 
					</tr> 
				</table> 
			
 
</html:form> 
 

</body> 
</html> 
 

