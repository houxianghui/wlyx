<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.sdic.SDicVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>单级字典维护</title> 

 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="SDic.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].sys_id.value == null ||document.forms[0].sys_id.value == "") { 
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
	if(document.forms[0].sys_id.value == null ||document.forms[0].sys_id.value == "") { 
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
 	//检查长度
	if(!isEmpty(document.forms[0].item_code_f.value)){
		if(getStrLength(document.forms[0].item_code_f.value)>12) { 		
	        alert('选项编码长度请不要超过12位!'); 
	        document.forms[0].item_code_f.focus(); 
	        return; 
		} 
	}
	
	if(!isEmpty(document.forms[0].item_val_f.value)){
		if(getStrLength(document.forms[0].item_val_f.value)>40) { 		
	        alert('选项值长度请不要超过40个字符!'); 
	        document.forms[0].item_val_f.focus(); 
	        return; 
		} 
	}
	
	if(!isEmpty(document.forms[0].logic_id_f.value)){
		if(getStrLength(document.forms[0].logic_id_f.value)>12) { 		
	        alert('逻辑代码长度请不要超过12位!'); 
	        document.forms[0].logic_id_f.focus(); 
	        return; 
		} 
	}
 
	document.forms[0].act.value = "list"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function setPKey(sys_id_var) { 
	document.forms[0].sys_id.value=sys_id_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body class="body_bg_grey1"> 
 
<html:form method="post" action="SDic.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=sys_id> 
<html:hidden property="type_id"/>
 
    <%=ViewUtil.getTitle("单级字典维护")%>
 
<table width="98%" class=dtPanel_Line  align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class=dtPanel_Top02 >     
    
    <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr  > 
            <td>&nbsp; 选项编码：<html:text property="item_code_f" styleClass="Textfield" size="20" maxlength="12" /> 
                &nbsp; 选项值：<html:text property="item_val_f" styleClass="Textfield" size="40" maxlength="40" /><br>
                &nbsp; 逻辑代码：<html:text property="logic_id_f" styleClass="Textfield" size="20" maxlength="12" />&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table>
        <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
         
           
        </td> 
  </tr> 
</table> 
 
 
	
<table  class=heightspace_top3 ><tr><td></td></tr></table>       
        	 
	
	<table width="98%" align="center" class="dtPanel_Line1" border="0"  cellpadding="0" cellspacing="1"> 
			<tr  align="center" class="dtPanel_Top01"> 
 
						<td width="15%">系统编号</td> 
						<td width="10%">归类码</td> 
						<td width="15%">选项编码</td> 
						<td width="15%">选项值</td> 
						<td width="10%">输出顺序</td> 
						<td width="15%">逻辑代码</td> 
						<td width="10%">状态</td> 
						<td>选择</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						SDicVO vo = (SDicVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1  href="SDic.do?act=r&sys_id=<%=vo.getSys_id()%>"><%=vo.getSys_id()%></a></td> 
						<td><%=vo.getType_id()%></td> 
						<td><%=vo.getItem_code()%></td> 
						<td><%=vo.getItem_val()%></td> 
						<td><%=vo.getList_order()%></td> 
						<td><%=vo.getLogic_id()%></td> 
						<td><%=vo.getStatus()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getSys_id()%>')"> </label></td> 
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
						<auth:button name="add" id="sDic_c"  value="增加" onClick="doAdd()"/>&nbsp;
						<auth:button name="edit" id="sDic_u"  value="修改" onClick="doEdit()"/>&nbsp;
						<auth:button name="delete" id="sDic_d"  value="删除" onClick="doDelete()"/>
						<input	name="back" type="button" class="Button" value="返回" onClick="location.href='DicType.do?act=list';"> &nbsp; 
						</td> 
				</tr> 
			</table> 
			
 
</html:form> 
 

</body> 
</html> 
 


