<%@page import="com.huateng.blue.notice.Ep_noticeVO"%>
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<jsp:useBean id="pageResult" scope="request"
	class="com.eis.base.PageObject" />

<html>

<title>֪ͨά��</title>


<%int maxPage = 1;
if (pageResult != null)
    maxPage = pageResult.getTotalPage();
%>
<script language="javascript"> 
 
function doAdd(){ 
	//���� 
	window.location="Ep_notice.do?act=c"; 
} 
 
function doEdit(){ 
	//�޸� 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].notice_no.value == null ||document.forms[0].notice_no.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
	//�ύ�� 
	document.forms[0].act.value='u';	 
	document.forms[0].submit(); 
} 
 
function doDelete() { 
	//ɾ�� 
 
	//����Ƿ���ѡ�еļ�¼ 
	if(document.forms[0].notice_no.value == null ||document.forms[0].notice_no.value == "") { 
		alert('����ѡ���¼'); 
		return; 
	} 
 
	//����ȷ����ʾ 
	if(!confirm('��ȷ��ִ��ɾ��������')) { 
		return; 
	} 
	document.forms[0].act.value='d'; 
	document.forms[0].submit(); 
} 
 
function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ���� 
 
 
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
            <td>&nbsp; ¼��Ա��<html:text property="oper_id_f" styleClass="Textfield" size="10" maxlength="20" />&nbsp;
            ¼��ʱ��&nbsp�ӣ�<html:text property="start_date_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp;
            ����<html:text property="end_date_f" styleClass="Textfield" size="8" maxlength="8" />&nbsp;
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
			<td>���</td>
			<td>֪ͨ����</td>
			<td>�ϴ��ļ���</td>
			<td>�ö�</td>
			<td>¼��Ա</td>
			<td>¼��ʱ��</td>
			<td>ѡ��</td>

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
//������ҳ��ע 
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
				id="ep_notice_c" value="����" onClick="doAdd()" /> &nbsp; <auth:button
				name="edit" id="ep_notice_c" value="�޸�" onClick="doEdit()" />
			&nbsp; <auth:button name="delete" id="ep_notice_c" value="ɾ��"
				onClick="doDelete()" /></td>
		</tr>
	</table>

</html:form>

<p>&nbsp;</p>
</body>
</html>


