<%@page import="com.huateng.blue.notice.Ep_noticeVO"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request"
	class="com.eis.base.PageObject" />

<html>

<title>通知维护</title>


<%int maxPage = 1;
if (pageResult != null)
    maxPage = pageResult.getTotalPage();
%>
<script language="javascript"> 
 
function doAdd(){ 
	//增加 
	window.location="Ep_notice.do?act=c"; 
} 
 
function doEdit(){ 
	//修改 
	//检查是否有选中的纪录 
	if(document.forms[0].notice_no.value == null ||document.forms[0].notice_no.value == "") { 
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
	if(document.forms[0].notice_no.value == null ||document.forms[0].notice_no.value == "") { 
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
 
function setPKey(notice_no_var,fliename_var) { 
	document.forms[0].notice_no.value=notice_no_var; 
	document.forms[0].filename.value=fliename_var;
} 
 
function turnPage( pagenm ) {   
    	document.forms[0].act.value = "list";  
    	document.forms[0].pageNO.value = pagenm;     
    	document.forms[0].submit(); 
} 
 
</script>
<body>

<html:form method="post" action="Ep_notice.do">
	<input type=hidden name=act value="list">
	<input type=hidden name=requery>
	<input type=hidden name=notice_no>
	<input type=hidden name=filename>

<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; 录入员：<html:text property="oper_id_f" styleClass="Textfield" size="10" maxlength="20" />&nbsp;
            录入时间&nbsp从：<html:text property="start_date_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp;
            到：<html:text property="end_date_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp;
            <input	name="query" type="button" class="Button_Search"  onClick="doQuery()"> &nbsp;</td> 
          </tr> 
        </table> 
        </td> 
  </tr> 
</table> 

	<table class=heightspace_top3 width="98%">
		<tr>
			<td></td>
		</tr>
	</table>

	<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		<tr align="center" class="dtPanel_Top01">
			<td>编号</td>
			<td>通知内容</td>
			<td>上传文件名</td>
			<td>置顶</td>
			<td>录入员</td>
			<td>录入时间</td>
			<td>选择</td>

		</tr>
		<%if (pageResult != null) {
    List list = pageResult.getList();
    int i = 1;
    if (list != null) {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            Ep_noticeVO vo = (Ep_noticeVO)iter.next();%>
		<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )">
			<td><a class=fontlink1
				href="Ep_notice.do?act=r&notice_no=<%=vo.getNotice_no()%>"><%=i++%></a></td>
			<td><%=vo.getNotice_comment()%></td>
			<td><a target="blank" class=fontlink1 href="Ep_notice.do?act=download&fileName=<%=vo.getFilename()%>" ><%=vo.getFilename()%></a></td> 
			<td><%=SingleDicMap.getDicItemVal("0000",String.valueOf(vo.getNotice_top()))%></td>
			<td><%=ReDefSDicMap.getDicItemVal("0003",vo.getOper_id())%></td>
			<td><%=vo.getOper_date()%></td>
			<td><label><input type="radio" name="param"
				onClick="setPKey('<%=vo.getNotice_no()%>','<%=vo.getFilename()%>')"> </label></td>
		</tr>

		<%}
}
}%>
	</table>
	<%
//产生翻页脚注 
if (pageResult != null) {%>
	<table width="98%" align="center" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td class="dtPanel_Pager"><%=pageResult.getFooter()%></td>
		</tr>
	</table>
	<%}%>
	<br>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center"><auth:button name="add"
				id="ep_notice_c" value="增加" onClick="doAdd()" /> &nbsp; <auth:button
				name="edit" id="ep_notice_c" value="修改" onClick="doEdit()" />
			&nbsp; <auth:button name="delete" id="ep_notice_c" value="删除"
				onClick="doDelete()" /></td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


