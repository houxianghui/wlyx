<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.key.pmkey.PmKeyVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>主键配置维护</title>  

 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="PmKey.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].tb_name.value == null ||document.forms[0].tb_name.value == "") { 
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
	if(document.forms[0].tb_name.value == null ||document.forms[0].tb_name.value == "") { 
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
 
function setPKey(tb_name_var) { 
	document.forms[0].tb_name.value=tb_name_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body  class="body_bg_grey1"> 
 
<html:form method="post" action="PmKey.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=tb_name> 
 
  <%=ViewUtil.getTitle("主键配置维护")%>
 
<table width="98%" align="center"   class=dtPanel_Line border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class=dtPanel_Top02 >    
    
    <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	  
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr > 
            <td>&nbsp; 数据库表名：<html:text property="tb_name_f" styleClass="Textfield" size="20" maxlength="20" />&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table>
        <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>	   
                
        </td> 
  </tr> 
</table> 
 
	
<table  class=heightspace_top3 ><tr><td></td></tr></table>       
        
	
	<table width="98%"  class="dtPanel_Line1" align="center" border="0"  cellpadding="0" cellspacing="1"> 
			<tr align="center" class="dtPanel_Top01">
 
						<td width="20%">数据库表名</td> 
						<td width="20%">主键字段</td> 
						<td width="20%">步长</td> 
						<td width="20%">当前最大值</td> 
						<td>选择</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						PmKeyVO vo = (PmKeyVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="PmKey.do?act=r&tb_name=<%=vo.getTb_name()%>"><%=vo.getTb_name()%></a></td> 
						<td><%=vo.getKey_field()%></td> 
						<td><%=vo.getStep_len()%></td> 
						<td><%=vo.getMax_val()%></td> 
 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getTb_name()%>')"> </label></td> 
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
			<table width="98%"  align="center"  border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<input	name="add" type="button" class="Button" value="增加" onClick="doAdd()"> &nbsp; 
						<input name="edit" type="button" class="Button" value="修改" onClick="doEdit()"> &nbsp;  
						<input name="delete" type="button" class="Button" value="删除" onClick="doDelete()"> 
						</td> 
					</tr> 
			</table> 
				
 
</html:form> 
 
</body> 
</html> 
 


