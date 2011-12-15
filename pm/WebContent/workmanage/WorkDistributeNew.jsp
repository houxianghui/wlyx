<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="workDistributeForm" scope="request" class="com.huateng.blue.work.WorkDistributeForm" />
<html> 
 <head>
<title>项目维护</title> 
 </head>
<body> 
<SCRIPT type="text/javascript" src="js/move.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script language="javascript"> 
 
function doSave(){ 	
	var field = new Array("startDate","endDate","notifyDay"); 
	var info = new Array("开始时间","结束时间","预提醒天数"); 
 
	//检察输入信息是否为空 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('请输入'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	var rolesRight=document.forms[0].rolesRight;
	for(var i=0;i<rolesRight.length;i++){
		rolesRight.options[i].selected=true;
	}
	document.forms[0].submit();
	
}
 
</script>

<html:form method="post" action="WorkDistribute.do"> 
<input type=hidden name=act value="add"> 
<html:hidden property="workId"/>
<%=ViewUtil.getTitle("任务分配")%> 
	
	<table width="98%" align=center class="dtPanel_Left" border="0"
		cellspacing="1" cellpadding="0">
		<tr align="center">
			<td align="center">未分配员工</td>
			<td></td>
			<td>已分配员工</td>
		</tr>
		<tr align="center">
			<td><select name="rolesLeft" id="rolesLeftId" size="10" multiple
				class="Select-Multiple" style="width: 150">
				<%

List notSelected = (ArrayList) request.getAttribute("notSelectedStuff");


if (notSelected != null) {
	Iterator iter = notSelected.iterator();

	while (iter.hasNext()) {
		
		String userId = (String) iter.next();
		String str = "<option ";
		str = str + " value='" + userId + "'>";
		str = str + ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,userId);
		str = str + "</option>";
		out.println(str);
	}

}%>
			</select></td>
			<td align="center"><input type='button' class='Button'
				onClick='moveSelected(document.forms[0].rolesLeft,document.forms[0].rolesRight)'
				value=" 增加 > "> <br>
			<br>
			<input type='button' class='Button'
				onClick='moveSelected(document.forms[0].rolesRight,document.forms[0].rolesLeft)'
				value=' < 删除 '> <br>
			<br>
			<input type='button' class='Button'
				onClick='moveAll(document.forms[0].rolesLeft,document.forms[0].rolesRight)'
				value='全增加>>'> <br>
			<br>
			<input type='button' class='Button'
				onClick='moveAll(document.forms[0].rolesRight,document.forms[0].rolesLeft)'
				value='<<全删除'></td>

			<td><select name="rolesRight" id="rolesRightId" size="10" multiple
				class="Select-Multiple" style="width: 150">

			</select></td>
		</tr>
	</table>
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
             
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 开始日期：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="startDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
         </td> 
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 结束日期：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="endDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/>
         </td> 
       </tr>     		
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 预提醒天数：</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="notifyDay" styleClass="Textfield" size="2" />
         </td> 
       </tr>
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; 备注：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="content" styleClass="Textarea" cols="40" rows="6"></html:textarea>
         </td> 
       </tr>     
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:button property="保存" value="保存" styleClass="Button" onclick="doSave()"></html:button>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 	   
	    
  </table> 
</html:form> 
 
</body> 
</html> 
 

