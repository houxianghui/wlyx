<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.maintainrecord.Maintain_recordVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
<head>
<title>技术支持服务记录维护</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="Maintain_record.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].seq_no.value == null ||document.forms[0].seq_no.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
function doResp(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].seq_no.value == null ||document.forms[0].seq_no.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
	//提交表单 
	document.forms[0].act.value='p';	 
	document.forms[0].submit(); 
} 
function doDelete() { 
	//删除 
 
	//检查是否有选中的纪录 
	if(document.forms[0].seq_no.value == null ||document.forms[0].seq_no.value == "") { 
		alert('请先选择纪录'); 
		return; 
	} 
 
	//进行确认提示 
	if(!confirm('您确认执行删除操作吗？')) { 
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
 
function setPKey(seq_no_var) { 
	document.forms[0].seq_no.value=seq_no_var; 
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script> 
</head>
<body> 
 
<html:form method="post" action="Maintain_record.do"> 
<input type=hidden name=act value="list"> 
<input type=hidden name=requery > 
<input type=hidden name=seq_no> 
 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 请求时间：
            	<html:text property="qus_date_f" styleClass="Textfield" size="20" maxlength="8" />&nbsp;处理结果：
            	<html:select property="res_result_f">
		         	<html:optionsCollection name="maintain_recordForm" property="res_result_c"/>
		        </html:select> 
             	<input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 
 
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
						<td>顺序号</td> 
						<td>提出日期</td> 
						<td>提出人机构姓名</td> 
						<td>问题描述</td> 
						<td>答复人员</td> 
						<td>状态</td> 
						<td>所用工时</td> 			
						<td>选择</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
							Maintain_recordVO vo = (Maintain_recordVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a class=fontlink1 href="Maintain_record.do?act=r&seq_no=<%=vo.getSeq_no()%>"><%=vo.getSeq_no()%></a></td> 
						<td><%=vo.getQus_date()%></td> 
						<td><%=vo.getQus_user()%></td> 
						<td><%=vo.getQus_info()%></td> 
						<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getRes_user())%></td> 
						<td><%=SingleDicMap.getDicItemVal("9993",vo.getRes_result())%></td> 
						<td><%=vo.getRes_cost()%></td> 
	 
						<td><label><input type="radio" name="param"  onClick="setPKey('<%=vo.getSeq_no()%>')"> </label></td> 
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
			<br> 
 
				<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
					<tr> 
						<td height="25" align="center"> 
						<auth:button name="add" id="maintain_record_c" value="增加" onClick="doAdd()"/> &nbsp; 
						<auth:button name="edit" id="maintain_record_c" value="修改" onClick="doEdit()"/> &nbsp;  
						<auth:button name="delete" id="maintain_record_c" value="删除" onClick="doDelete()"/>&nbsp;
						<auth:button name="resp" id="maintain_record_p" value="答复" onClick="doResp()"/> &nbsp;
						</td> 
					</tr> 
				</table> 
 
</html:form> 
 
<p>&nbsp;</p> 
</body> 
</html> 
 

