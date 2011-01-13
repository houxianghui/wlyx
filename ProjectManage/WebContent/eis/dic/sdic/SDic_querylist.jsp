<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.sdic.SDicVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>单级字典查询</title> 
 
<link href="CSS/Style.css" rel="stylesheet" type="text/css"> 
 
<SCRIPT src="js/validate.js" type="text/javascript"></SCRIPT> 
<SCRIPT src="js/event.js" type="text/javascript"></SCRIPT> 
<SCRIPT src="js/func.js" type="text/javascript"></SCRIPT> 

 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "ql"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "ql";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
 
<body> 
 
<html:form method="post" action="SDic.do"> 
<input type=hidden name=act value="ql"> 
<input type=hidden name=requery > 
 
<table width="98%" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 系统编号：<html:text property="sys_id_f" styleClass="Textfield" size="12" maxlength="12" />&nbsp; <input	name="query" type="button" class="Button" value="查询" onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
 
		<br> 
		<table width="98%" border="0" cellspacing="1" align="center" cellpadding="0"> 
			<tr> 
				<td class="dtPanel_Line"> 
				<table width="100%" border="0" cellpadding="0" cellspacing="1"> 
					<tr align="center" class="dtPanel_Top01"> 
 
						<td width="20%">系统编号</td> 
						<td width="20%">归类码</td> 
						<td width="20%">选项编码</td> 
						<td width="20%">选项值</td> 
						<td width="20%">输出顺序</td> 
						<td width="20%">逻辑代码</td> 
						<td width="20%">状态</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						SDicVO vo = (SDicVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main"> 
						<td><a href="SDic.do?act=r&sys_id=<%=vo.getSys_id()%>"><%=vo.getSys_id()%></a></td> 
						<td><%=vo.getType_id()%></td> 
						<td><%=vo.getItem_code()%></td> 
						<td><%=vo.getItem_val()%></td> 
						<td><%=vo.getList_order()%></td> 
						<td><%=vo.getLogic_id()%></td> 
						<td><%=vo.getStatus()%></td> 
 
					</tr> 
 
				<% 
						} 
					} 
				%> 
				</table> 
				</td> 
			</tr> 
			<tr> 
			<td height="25"> 
               <%=pageResult.getFooter()%> 
				</td> 
			</tr> 
		</table> 
 
</html:form> 
 

</body> 
</html> 
 

