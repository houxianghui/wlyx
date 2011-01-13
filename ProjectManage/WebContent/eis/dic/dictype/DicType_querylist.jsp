<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.dic.dictype.DicTypeVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 
<title>字典归类信息维护</title> 
 
 
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
 
<html:form method="post" action="DicType.do"> 
<input type=hidden name=act value="ql"> 
<input type=hidden name=requery > 
 
<table width="98%" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 归类码：<html:text property="type_id_f" styleClass="Textfield" size="20" maxlength="30" />&nbsp; <input	name="query" type="button" class="Button" value="查询" onClick="doQuery()"> &nbsp;</td> 
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
 
						<td width="20%">归类码</td> 
						<td width="20%">名称</td> 
						<td width="20%">级别标志</td> 
 
					</tr> 
			<% 
				List list = pageResult.getList(); 
				if(list != null ) { 
					Iterator iter = list.iterator(); 
					while (iter.hasNext()) { 
						DicTypeVO vo = (DicTypeVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main"> 
						<td><a href="DicType.do?act=r&type_id=<%=vo.getType_id()%>"><%=vo.getType_id()%></a></td> 
						<td><%=vo.getType_name()%></td> 
						<td><%=vo.getDic_level()%></td> 
 
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
 
