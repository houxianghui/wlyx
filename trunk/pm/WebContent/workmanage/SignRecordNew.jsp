<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectDistributeForm" scope="request" class="com.projectmaintain.ProjectDistributeForm" />
<jsp:useBean id="signRecordForm" scope="request" class="com.huateng.blue.work.SignRecordForm" />
<html> 
 <head>
<title>ǩ��ǩ��ά��</title> 
 </head>
<body> 
<SCRIPT type="text/javascript" src="js/move.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script language="javascript"> 
 
function doSave(){ 	
	var field = new Array("recordDate","recordTime"); 
	var info = new Array("��ǩ����","��ǩʱ��"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	var userId = document.forms[0].userId;
	var selectedUser = false;
	for(var i = 0;i<userId.length;i++){
		if(userId[i].selected){
			selectedUser = true;
			break;
		}
	}
	if(!selectedUser){
		alert("��ѡ��ǩ��Ա");
		return;
	}
	var recordType = document.forms[0].recordType;
	var typeSelected = false;
	for(var i = 0;i<recordType.length;i++){
		if(recordType[i].checked){
			typeSelected = true;
			break;
		}
	}
	if(!typeSelected){
		alert("��ѡ��ǩ����");
		return;
	}
	
	document.forms[0].submit();
}
 
</script>

<html:form method="post" action="SignRecord.do"> 
<input type=hidden name=act value="add"> 
<%=ViewUtil.getTitle("����ǩ��ǩ�˼�¼")%> 
	
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ǩ��Ա��</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:select property="userId" styleClass="Select" disabled="false">
         	<html:optionsCollection name="projectDistributeForm" property="stuff"/>
         </html:select>
         </td>
       </tr>         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ǩ���ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="recordDate" styleClass="Textfield" size="8" readonly="true" onclick="new Calendar().show(this);"/> 
         </td> 
       </tr> 
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ǩʱ�䣺</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:text property="recordTime" styleClass="Textfield" size="6" maxlength="6"></html:text>(hhmmss)
         </td> 
       </tr>    		
       <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; ��ǩ���ͣ�</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:radio property="recordType" value="I" styleClass="Radio">ǩ��</html:radio>
         <html:radio property="recordType" value="O" styleClass="Radio">ǩ��</html:radio>
         </td> 
       </tr>
      	
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 
					<html:button property="����" value="����" styleClass="Button" onclick="doSave()"></html:button>
					<input type="button" class="Button" value="����" onclick="history.back()">
		 		</td> 
	    </tr> 	   
	    
  </table> 
</html:form> 
 
</body> 
</html> 
 

