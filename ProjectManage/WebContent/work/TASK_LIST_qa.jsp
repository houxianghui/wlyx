<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.work.TASK_LISTVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>工作列表查询</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//根据输入条件查询 
 
	//检查输入查询条件 
 
 
	document.forms[0].act.value = "qa"; 
	document.forms[0].requery.value='y'; 
	document.forms[0].submit(); 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "qa";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 <script type="text/javascript" src="js/calendar.js"></script>
 
<html:form method="post" action="TASK_LIST.do"> 
<input type=hidden name=act value="qa"> 
<input type=hidden name=requery > 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
    	<tr class="dtPanel_Top01">
    		<td align="center">历史项目列表</td>
    	</tr>
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 姓名：
            <html:select property="task_user_f">
            	<html:optionsCollection name="tASK_LISTForm" property="stuff"/>
            </html:select>
            &nbsp;项目编号：<html:text property="project_no_f" styleClass="Textfield" size="4" maxlength="4" />&nbsp; 
            工作日期：<html:text property="task_date_f" styleClass="Textfield" size="8"  readonly="true" onclick="new Calendar().show(this);"/>
            &nbsp;到：<html:text property="task_date_e" styleClass="Textfield" size="8"  readonly="true" onclick="new Calendar().show(this);"/>
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
												
						<td>项目编号</td>
						<td >项目阶段</td>
						<td>姓名</td> 
						<td >工作日期</td> 
						<td >工作内容</td> 					
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							TASK_LISTVO vo = (TASK_LISTVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						
						
						<td><%=vo.getProject_no()%></td>
						<td><%=SingleDicMap.getDicItemVal("9994",vo.getTask_step())%></td> 
						<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getTask_user())%></td>
						<td><a class=fontlink1 href="TASK_LIST.do?act=r&task_no=<%=vo.getTask_no()%>"><%=vo.getTask_date()%></a></td> 
						<td><%=vo.getTask_memo()%></td>  
						
 
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
					<td class="dtPanel_Pager"> <%=pageResult.getFooter()%> </td> 
				</tr> 
			</table> 
			<% 
				} 
			%> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

