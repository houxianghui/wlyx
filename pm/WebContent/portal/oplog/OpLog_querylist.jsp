
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.oplog.OpLogVO" %> 
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %> 
 

 
<html> 
 
<title>������־��ѯ</title> 
 

 
 
<% 
    PageObject pageResult = (PageObject)request.getAttribute("pageResult");
    
	int maxPage = 1; 
	if(pageResult != null) 
		maxPage = pageResult.getTotalPage(); 
 
%> 
 
<script language="javascript"> 
function doPopOrg() {
	openWin("OrgPop.do?act=list&id_field=branch_id&name_field=branch_name","org_pop");
}
 
function doQuery() { 
	//��������������ѯ 

	if(!isEmpty(document.forms[0].event_date_begin.value) && document.forms[0].event_date_begin.value.length != 8){
		alert("��ʼ����������8λ���֣���ʽΪyyyymmdd!");
		document.forms[0].event_date_begin.focus();
		return;
	}
	if(!isEmpty(document.forms[0].event_date_end.value) && document.forms[0].event_date_end.value.length != 8){
		alert("��ֹ����������8λ���֣���ʽΪyyyymmdd!");
		document.forms[0].event_date_end.focus();
		return;
	}
	var beginDate = document.forms[0].event_date_begin.value;
	var endDate = document.forms[0].event_date_end.value;
	
	if(!isEmpty(beginDate) && !forDate(beginDate)){
		alert("��ʼ�������벻��ȷ��");
		document.forms[0].event_date_begin.focus();
		return;
	}
	if(!isEmpty(endDate) && !forDate(endDate)){
		alert("��ֹ�������벻��ȷ��");
		document.forms[0].event_date_end.focus();
		return;
	}
	if(!isEmpty(beginDate) && !isEmpty(endDate) && document.forms[0].event_date_begin.value > document.forms[0].event_date_end.value){
	    alert("��ֹ���ڱ��������ʼ���ڣ�");
		document.forms[0].event_date_end.focus();
		return; 
	} 
 
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
 
<body class="body_bg_grey1"> 
 
<html:form method="post" action="OpLog.do"> 
<input type=hidden name=act value="ql"> 
<input type=hidden name=requery > 

 <%=ViewUtil.getTitle("������־��ѯ")%>
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr> 
            <td>&nbsp; ���ڣ�<html:text property="event_date_begin" styleClass="Textfield" size="8" maxlength="8"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>&nbsp;
            			����<html:text property="event_date_end" styleClass="Textfield" size="8" maxlength="8" onblur="onlyNum(this)" onkeyup="onlyNum(this)" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            �¼����ͣ�
            <html:select property="event_type" >
            	<html:optionsCollection name="opLogForm" property="event_type_options" />
            </html:select>
            &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
            ������
            <html:select property="op_id" >
            	<html:optionsCollection name="opLogForm" property="op_id_options" />
            </html:select>
          </td> 
          </tr> 
          <tr> 
            <td>&nbsp; ������<html:text property="branch_name" styleClass="Textfield" size="20" maxlength="40" onclick="doPopOrg();" readonly="true"/>&nbsp;
            <html:hidden property="branch_id"/>
            <input type="button" name="xz2" value="ѡ�����" class="Button" onclick="doPopOrg();"> 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            �û���<html:text property="login_id" styleClass="Textfield" size="10" maxlength="10" />&nbsp; &nbsp;&nbsp;
          
           <input	name="query" type="button" class="Button_Search"  onClick="doQuery()">  &nbsp;</td> 
          </tr> 
     </table> 
        </td> 
  </tr> 
</table> 
  
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
 
						<td width="10%">�û���</td>
						<td width="10%">��������</td> 
						<td width="20%">�¼�ʱ��</td> 
						<td width="10%">�¼�����</td> 
						<td width="30%">����</td> 						
						<td width="20%">��ע</td> 
 
					</tr> 
			<% 
				if(pageResult != null)  { 
					List list = pageResult.getList(); 
					if(list != null ) { 
						Iterator iter = list.iterator(); 
						while (iter.hasNext()) { 
						OpLogVO vo = (OpLogVO) iter.next(); 
			%> 
					<tr align="center" class="dtPanel_Main" onclick="_clickTr( this )"> 
						<td><a href="OpLog.do?act=r&sys_id=<%=vo.getSys_id()%>"><%=vo.getUser_login_id()%></a></td> 
						<td><%=vo.getUser_name()%></td>
						<td><%=DateUtil.formatDateTime(vo.getEvent_time())%></td> 
						<td><%=SingleDicMap.getDicItemVal("0006",vo.getEvent_type())%></td> 
						<td><%=vo.getMemo()%></td> 
 
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
 


