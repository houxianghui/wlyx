<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.redefsdic.ReDefSDicVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>自定义单级字典维护</title> 

 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="ReDefSDic.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].type_id.value == null ||document.forms[0].type_id.value == "") { 
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
	if(document.forms[0].type_id.value == null ||document.forms[0].type_id.value == "") { 
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
 
   <%=ViewUtil.getTitle("自定义单级字典维护")%>
 
<table width="98%"  class=dtPanel_Line align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class=dtPanel_Top02>     
    
    	<table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
    	<table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         	<tr > 
            	<td>&nbsp; 字典内容：<html:text property="caption_f" styleClass="Textfield" size="20" maxlength="20" />&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;</td> 
          	</tr> 
     	</table> 
    	<table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
     
     
     </td> 
  </tr> 
</table> 
 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table>       
        		
	
		<table width="98%" class="dtPanel_Line1" align="center"  border="0" cellpadding="0" cellspacing="1"> 
			<tr align="center" class="dtPanel_Top01"> 
 
						<td width="10%">规类码</td> 
						<td width="20%">字典内容</td> 
						<td width="35%">备注</td> 
						<td width="10%">更新人员</td> 
						<td width="15%">更新日期</td> 
						<td width="10%">选择</td> 
 
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
						<auth:button name="add" id="reDefSDic_c"  value="增加" onClick="doAdd()"/>&nbsp;
						<auth:button name="edit" id="reDefSDic_u"  value="修改" onClick="doEdit()"/>&nbsp;
						<auth:button name="delete" id="reDefSDic_d"  value="删除" onClick="doDelete()"/>
						</td> 
					</tr> 
				</table> 
		
 
</html:form> 
 
 
</body> 
</html> 
 

