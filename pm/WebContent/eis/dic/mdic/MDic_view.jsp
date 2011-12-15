<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
 
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.mdic.MDicForm" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="mDicForm" scope="request" class="com.eis.dic.mdic.MDicForm" /> 
 
<html> 
 
<title>多级字典信息</title> 
<script language="javascript"> 
function doAdd(){
	var item_level1 = document.forms[0].item_level.value
	var item_level = parseInt(item_level1) + 1;
	window.location="MDic.do?act=c&type_id="+document.forms[0].type_id.value+"&parent_id="+document.forms[0].sys_id.value+"&item_level="+item_level+"";
}

function doEdit(){
	window.location="MDic.do?act=u&sys_id="+document.forms[0].sys_id.value+"";
}

function doDel(){
	window.location="MDic.do?act=d&sys_id="+document.forms[0].sys_id.value+"";
}
</script> 
 
<body> 
<p>&nbsp;</p> 
 
<html:form method="post" action="MDic.do"> 
<input type=hidden name=type_id value=<%=mDicForm.getType_id()%> >
<input type=hidden name=parent_id value=<%=mDicForm.getParent_id()%>>
<input type=hidden name=sys_id value=<%=mDicForm.getSys_id()%>>
<input type=hidden name=item_level value=<%=mDicForm.getItem_level()%>>
 
<%=ViewUtil.getTitle("多级字典信息")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3"  border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;系统编码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getSys_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;归类码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getType_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;上级编码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getParent_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;选项编码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getItem_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;选项值：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getItem_val()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;输出顺序：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getList_order()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;级次：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getItem_level()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;逻辑代码：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%=mDicForm.getLogic_id()%> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left_v">&nbsp;状态：</td> 
         <td align="left" class="dtPanel_Main">&nbsp; 
          <%if(mDicForm.getStatus().equals("1")){%>
          	<%="正常"%>
         
          <%}else{%>
          	<%="作废"%>
          <%}%> 
         </td> 
       </tr> 
 
  </table> 
 
   <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
   <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
       <tr> 
		<td height="25" align="center" class="dtPanel_Bottom"> 
			<input name="return" type="button" class="Button" value="增加字典" onClick="doAdd()"> 
			<input name="return" type="button" class="Button" value="修改字典" onClick="doEdit()"> 
			<input name="return" type="button" class="Button" value="删除字典" onClick="doDel()"> 
			<!--input name="return" type="button" class="Button" value="返回" onClick="history.back()"-->   
		 </td> 
	   </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

