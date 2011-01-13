
<%@ include file = "/includes/common.jsp" %> 
<%@ page import="com.eis.portal.oplog.OpLogVO" %> 
<%@ page import="com.eis.base.PageObject" %>
<%@ page  contentType="text/html; charset=GBK" %> 
 

 
<html> 
 
<title>操作日志查询</title> 
 

 
 
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
	//根据输入条件查询 

	if(!isEmpty(document.forms[0].event_date_begin.value) && document.forms[0].event_date_begin.value.length != 8){
		alert("起始日期项输入8位数字，格式为yyyymmdd!");
		document.forms[0].event_date_begin.focus();
		return;
	}
	if(!isEmpty(document.forms[0].event_date_end.value) && document.forms[0].event_date_end.value.length != 8){
		alert("终止日期项输入8位数字，格式为yyyymmdd!");
		document.forms[0].event_date_end.focus();
		return;
	}
	var beginDate = document.forms[0].event_date_begin.value;
	var endDate = document.forms[0].event_date_end.value;
	
	if(!isEmpty(beginDate) && !forDate(beginDate)){
		alert("起始日期输入不正确！");
		document.forms[0].event_date_begin.focus();
		return;
	}
	if(!isEmpty(endDate) && !forDate(endDate)){
		alert("终止日期输入不正确！");
		document.forms[0].event_date_end.focus();
		return;
	}
	if(!isEmpty(beginDate) && !isEmpty(endDate) && document.forms[0].event_date_begin.value > document.forms[0].event_date_end.value){
	    alert("终止日期必须大于起始日期！");
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

 <%=ViewUtil.getTitle("操作日志查询")%>
 
<table width="98%" class="dtPanel_Line" align="center" border="0" cellspacing="1" cellpadding="0"> 
  <tr> 
    <td class="dtPanel_Top02"> 
    <table width="100%"  border="0" cellspacing="1" cellpadding="0"> 
         <tr> 
            <td>&nbsp; 日期：<html:text property="event_date_begin" styleClass="Textfield" size="8" maxlength="8"  onblur="onlyNum(this)" onkeyup="onlyNum(this)"/>&nbsp;
            			到：<html:text property="event_date_end" styleClass="Textfield" size="8" maxlength="8" onblur="onlyNum(this)" onkeyup="onlyNum(this)" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            事件类型：
            <html:select property="event_type" >
            	<html:optionsCollection name="opLogForm" property="event_type_options" />
            </html:select>
            &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
            操作：
            <html:select property="op_id" >
            	<html:optionsCollection name="opLogForm" property="op_id_options" />
            </html:select>
          </td> 
          </tr> 
          <tr> 
            <td>&nbsp; 机构：<html:text property="branch_name" styleClass="Textfield" size="20" maxlength="40" onclick="doPopOrg();" readonly="true"/>&nbsp;
            <html:hidden property="branch_id"/>
            <input type="button" name="xz2" value="选择机构" class="Button" onclick="doPopOrg();"> 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            用户：<html:text property="login_id" styleClass="Textfield" size="10" maxlength="10" />&nbsp; &nbsp;&nbsp;
          
           <input	name="query" type="button" class="Button_Search"  onClick="doQuery()">  &nbsp;</td> 
          </tr> 
     </table> 
        </td> 
  </tr> 
</table> 
  
<table  class=heightspace_top3 ><tr><td></td></tr></table> 
 
		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1" align="center" cellpadding="0"> 
					<tr align="center" class="dtPanel_Top01"> 
 
						<td width="10%">用户名</td>
						<td width="10%">中文姓名</td> 
						<td width="20%">事件时间</td> 
						<td width="10%">事件类型</td> 
						<td width="30%">机构</td> 						
						<td width="20%">备注</td> 
 
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
						<td><%=OrgMap.getOrgName(vo.getOrg_id())%></td> 						
						<td><%=vo.getMemo()%></td> 
 
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
 


