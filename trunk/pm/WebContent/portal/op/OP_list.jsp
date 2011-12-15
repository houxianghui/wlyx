
<%@ include file = "/includes/common.jsp" %>

<%@ page import="com.eis.portal.op.OPVO" %>
<%@ page import="com.eis.portal.op.*" %>
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %>



<html>

<title>操作权限定义维护</title>


<%
	PageObject pageResult = (PageObject) request.getAttribute("pageResult");
	
	int maxPage = 1;
	if(pageResult != null)
		maxPage = pageResult.getTotalPage();
		
	
%>
<script language="javascript">

function doAdd(){
	//增加	
	document.forms[0].act.value='c';	
	document.forms[0].submit();	
	
	
	
}
function doEdit(){
	//修改
	
	//检查是否有选中的纪录
	if(document.forms[0].op_code.value == null ||document.forms[0].op_code.value == "") {
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
	if(document.forms[0].op_code.value == null ||document.forms[0].op_code.value == "") {
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
	//删除
	
	//检查输入查询条件
	//检查字段长度
	if(getStrLength(document.forms[0].caption.value)>40) { 		
	        	alert('操作权限名称长度请不要超过40个字符!'); 
	        	document.forms[0].caption.focus(); 
	        	return; 
	}
	

	document.forms[0].act.value = "list";
	document.forms[0].requery.value='y';
	document.forms[0].submit();
}


function setKey(opcode) {
	
	document.forms[0].op_code.value=opcode;
	

}



function turnPage( pagenm ) {  
	document.forms[0].act.value = "list"; 
    document.forms[0].pageNO.value = pagenm;    
    document.forms[0].submit();
}




</script>

<body  class="body_bg_grey1">


<html:form method="post" action="OP.do">
<input type=hidden name=op_code>
<input type=hidden name=act value="list">
<input type=hidden name=requery >
<html:hidden  property="menu_id"/>
<html:hidden  property="menu_name"/>



<table width="99%"   align="center"  class=dtPanel_Line  border="0" cellspacing="1" cellpadding="0">
     <tr >
         <td   align="center"  class=dtPanel_Top02>
         
         	
         	<table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>       
         	 
            <table  width="98%"   align="center"   border="0" cellspacing="1" cellpadding="0">          	
          		<tr>
          			<td class=BlueFont>
            			&nbsp;&nbsp;操作权限名称：<html:text property="caption" styleClass="Textfield" size="30" maxlength="40" />
            			&nbsp; <input	name="query" type="button" class="Button_Search" value="  " onClick="doQuery()"> &nbsp;
           			</td>            
          		</tr>          
     		</table>
     		<table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>      
        	
        	
        </td>
  	</tr>
</table>
	
		
		<table width="99%" border="0"   align="center" class="dtPanel_Line1"  cellpadding="0" cellspacing="1">
					<tr align="center" class="dtPanel_Top01">
						<td width="35%">功能菜单</td>
						<td width="18%">权限码</td>
						<td width="37%">操作名称</td>						
						<td width="10%">选择</td>
					</tr>
			<%
				List list = pageResult.getList();
				if(list != null ) {
					Iterator iter = list.iterator();
					String op_code = null;					
					
					while (iter.hasNext()) {
						OPVO vo = (OPVO) iter.next();
						op_code = vo.getOp_code();										
						
			%>
					<tr align="center" class="dtPanel_Main"  onclick="_clickTr( this )">
						<td><%=vo.getMenu_name()%></td>
						<td><%=op_code%></td>
						<td><%=vo.getCaption()%></td>						
						<td><label><input type="radio" name="op"  onClick="setKey('<%=op_code%>')"> </label></td>
					</tr>


				<%
						}
					}
				%>
				
			</table>
		
			
				
								
		<table width="99%"   align="center" border="0" cellspacing="0" cellpadding="0">
              <tr class="dtPanel_Pager">
                <td>&nbsp;&nbsp;<%=pageResult.getResultFooter()%></td>
                <td align="right"><%=pageResult.getPageFooter()%>
        </table>
         
        <br>            
		<table width="100%" border="0"   align="center" cellspacing="0" cellpadding="0">
			<tr>
					<td height="25" align="center">						
						<auth:button name="add" id="op_c" value="增加操作权" onClick="doAdd()"/> &nbsp;
						<auth:button name="edit" id="op_u" value="修改操作权" onClick="doEdit()"/>&nbsp; 						
						<auth:button name="delete" id="op_d" value="删除操作权" onClick="doDelete()"/>&nbsp;
						
					</td>
			</tr>
		</table>

		
</html:form>
<p>&nbsp;</p>

</body>
</html>
