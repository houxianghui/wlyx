<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.maintainrecord.Maintain_recordVO" %> 
<%@ page  contentType="text/html; charset=GBK" %> 
 
<jsp:useBean id="pageResult" scope="request" class="com.eis.base.PageObject" /> 
 
<html> 
 <head>
<title>����֧�ַ����¼��ѯ</title> 
 
 
<% 
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
 
 
function doQuery() { 
	//��������������ѯ 
 
	//��������ѯ���� 
 
 
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
 </head>
<body> 
 
<html:form method="post" action="Maintain_record.do"> 
<input type=hidden name=act value="ql"> 
<input type=hidden name=requery > 
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Line"> 
    <table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
         <tr class="dtPanel_Top02"> 
            <td>&nbsp; ����ʱ�䣺
            	<html:text property="qus_date_f" styleClass="Textfield" size="20" maxlength="8" />&nbsp;��������
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
 
						<td>˳���</td> 
						<td>�������</td> 
						<td>����˻�������</td> 
						<td>��������</td> 
						<td>����Ա</td> 
						<td>״̬</td> 
						<td>���ù�ʱ</td> 
 
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
 
					</tr> 
 
				<% 
							} 
						} 
					} 
				%> 
				</table> 
			<% 
				//������ҳ��ע 
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
 

