<%@ include file = "/includes/common.jsp" %> 
<%@ page contentType="text/html; charset=GBK"%> 
<jsp:useBean id="projectMaintainForm" scope="request" class="com.projectmaintain.ProjectMaintainForm" />
<html> 
 <head>
<title>项目维护</title> 
<script language="javascript"> 
function doCommit(){
	var s = prompt('请输入修改原因','');
	if(s == null || s==""){
		alert("请输入修改原因");
		return;
	}
	document.forms[0].reason.value=s;
	document.forms[0].submit();
}	
var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}
function handleStateChange() {	
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
             parseResults();
        }
    }
}
function parseResults() {
	clearList();

    var models = document.getElementsByName("projectClass")[0];
    var key = xmlHttp.responseXML.getElementsByTagName("key");
    var v =  xmlHttp.responseXML.getElementsByTagName("value"); 
  
    var option = null;
    for(var i = 0; i < key.length; i++) {
        option = document.createElement("option");       
           
        option.value=key[i].childNodes[0].nodeValue;
        option.appendChild(document.createTextNode(v[i].childNodes[0].nodeValue));
        models.appendChild(option);
    }
    
}
function showSubMenu(){
	
	var t = document.getElementsByName("owning");
	var c = t[0].options[t[0].selectedIndex].value;
	var url="ProjectMaintain.do?act=d&projectClass="+c;
	
	if(c == "--" || c== ""){
		clearList();
		return;
	}
	
	createXMLHttpRequest();
    xmlHttp.open("POST", url);
    xmlHttp.onreadystatechange = handleStateChange;   
    xmlHttp.send(null);
}
function clearList() {
    var models = document.getElementsByName("projectClass")[0];
    while(models.childNodes.length > 0) {
        models.removeChild(models.childNodes[0]);
    }
}
function showValue(){
	var t = document.getElementsByName("projectClass")[0];
	document.forms[0].projectClass.value=t.value;
}
</script> 
 </head>
<body> 
<p>&nbsp;</p> 

<html:form method="post" action="ProjectMaintainUpdate.do"> 
<input type=hidden name=act value="up"> 
<html:hidden property="reason"/>
<%=ViewUtil.getTitle("项目维护")%> 
 
    <table align="center" width="98%" class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0"> 
        
         
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectMaintainForm.projectId.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectId" styleClass="Textfield-READONLY" size="4" readonly="true"/> 
         <html:messages property="projectId" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td>
       </tr> 
        <tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectMaintainForm.issId.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="issId" styleClass="Textarea" cols="40" rows="4"></html:textarea>
		 <html:messages property="issId" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td> 
       </tr>     
		<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectMaintainForm.projectName.display" bundle="projectMaintain"/>：</td> 
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="projectName" styleClass="Textfield" size="40"/> 
         <html:messages property="projectName" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td>
       </tr>           
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<bean:message key="projectMaintainForm.status.display" bundle="projectMaintain"/>：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="status" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="statusCollection"/>
				</html:select>
				<html:messages property="status" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
			</td>
		</tr>
			<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<bean:message key="projectMaintainForm.isInContract.display" bundle="projectMaintain"/>：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="isInContract" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="isInContractCollection"/>
				</html:select>
				<html:messages property="isInContract" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<bean:message key="projectMaintainForm.owning.display" bundle="projectMaintain"/>：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="owning" styleClass="Select" onchange="showSubMenu()">
					<html:optionsCollection name="projectMaintainForm" property="owningCollection"/>
				</html:select>
				<html:messages property="owning" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
				<html:select property="projectClass" styleClass="Select" onchange="showValue()" >
					<html:optionsCollection name="projectMaintainForm" property="projectClassCollection"/>
				</html:select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td width="35%" align="right" class="dtPanel_Left">&nbsp;<bean:message key="projectMaintainForm.needDev.display" bundle="projectMaintain"/>：</td>
			<td align="left" class="dtPanel_Main2">&nbsp; 			
				<html:select property="needDev" styleClass="Select">
					<html:optionsCollection name="projectMaintainForm" property="needDevCollection"/>
				</html:select>
				<html:messages property="needDev" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
			</td>
		</tr>
      	<tr bgcolor="#FFFFFF"> 
          <td width="35%" align="right" class="dtPanel_Left">&nbsp; <bean:message key="projectMaintainForm.memo.display" bundle="projectMaintain"/>：</td>
         <td align="left" class="dtPanel_Main2">&nbsp;         
         <html:textarea property="memo" styleClass="Textarea" cols="40" rows="2"></html:textarea>
		 <html:messages property="memo" id="error"><font color="red"><bean:write name="error" /></font></html:messages>
         </td> 
       </tr>    
  </table> 
 
    <table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table> 
 
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0"> 
        <tr> 
				<td height="25" align="center" class="dtPanel_Bottom"> 					
					<html:button styleClass="Button" value="保存" property="baocun" onclick="doCommit()"></html:button>
					<input type="button" class="Button" value="返回" onclick="history.back()">
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
 

