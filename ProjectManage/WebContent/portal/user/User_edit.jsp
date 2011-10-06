
<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="com.eis.portal.role.*" %>
<%@ page import="com.eis.portal.user.*" %>
<%@ page import="com.eis.cache.*" %> 
<%@ page import="com.eis.base.PageObject" %>
<jsp:useBean id="userForm" scope="request" class="com.eis.portal.user.UserForm" /> 
 
<title>�޸��û��б�</title>
 
<script language="javascript"> 
 
function doSave(){ 
	
	//ִ��У��
	var field = new Array("login_id","user_name","role_name","begin_dt","invalid_dt");
	var info = new Array("�û���","��������","����ɫ","��ʼ����","ʧЧ����");
	var tmp;
	
	//���������Ϣ�Ƿ�Ϊ��
	for(var i=0;i<field.length;i++)	
	{	
        tmp ="document.forms[0]."+field[i]+".value";
        if(isEmpty(eval(tmp))) {
        	alert('������'+info[i]);
        	eval("document.forms[0]."+field[i]+".focus()");
        	return;
        }		
	}
		//����ֶγ���
	if(getStrLength(document.forms[0].login_id.value)>10) { 		
	        	alert('�û��������벻Ҫ����10���ַ�!'); 
	        	document.forms[0].login_id.focus(); 
	        	return; 
	}
	if(getStrLength(document.forms[0].user_name.value)>18) { 		
	        	alert('�������������벻Ҫ����18���ַ�!'); 
	        	document.forms[0].user_name.focus(); 
	        	return; 
	}

	//λ������ʽ���
	if(!isEmpty(document.forms[0].phone.value)){
	   
		if(getStrLength(document.forms[0].phone.value) > 30){
			alert("��ϵ�绰λ��ӦС��30λ!");
			document.forms[0].phone.focus();
			return;
		}
	}

	if(!isEmpty(document.forms[0].mobile.value)){
		if(!isInteger(document.forms[0].mobile.value)){
			alert("�ֻ�����Ҫ����������!");
			document.forms[0].mobile.focus();
			return;
		}
		if(!((document.forms[0].mobile.value).length <= 11 )){
			alert("�ֻ�����Ӧ������11λ����!");
			document.forms[0].mobile.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].email.value)){
		if(!forEmail(document.forms[0].email)){
			alert("�����ʼ���ʽ��������!");
			document.forms[0].email.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].email.value)){
		if((document.forms[0].email.value).length > 60){
			alert("�����ʼ�����Ӧ������60���ַ�!");
			document.forms[0].email.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].postcode.value)){
		if(!isInteger(document.forms[0].postcode.value)){
			alert("�ʱ�Ҫ����������!");
			document.forms[0].postcode.focus();
			return;
		}
		if((document.forms[0].postcode.value).length != 6){
			alert("�ʱ�Ҫ��6λ����!");
			document.forms[0].email.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].address.value)){
		if(getStrLength(document.forms[0].address.value) > 200){
			alert("��ϵ��ַ������200���ַ�!");
			document.forms[0].address.focus();
			return;
		}
	}
	if(!isEmpty(document.forms[0].memo.value)){
		if(getStrLength(document.forms[0].memo.value) > 200){
			alert("��ע���Ȳ�����200���ַ�!");
			document.forms[0].memo.focus();
			return;
		}
	}
	//���ڼ��
	if(!isInteger(document.forms[0].begin_dt.value)){
		alert("��ʼ�����������֣���ʽΪyyyymmdd!");
		document.forms[0].begin_dt.focus();
		return;
	}
	if(document.forms[0].begin_dt.value.length != 8){
		alert("��ʼ����������8λ���֣���ʽΪyyyymmdd!");
		document.forms[0].begin_dt.focus();
		return;
	}
	if(!isInteger(document.forms[0].invalid_dt.value)){
		alert("ʧЧ�����������֣���ʽΪyyyymmdd!");
		document.forms[0].invalid_dt.focus();
		return;
	}
	if(document.forms[0].invalid_dt.value.length != 8){
		alert("ʧЧ��������8λ���֣���ʽΪyyyymmdd!");
		document.forms[0].invalid_dt.focus();
		return;
	}
	
	var begin_dt = document.forms[0].begin_dt.value;
	var invalid_dt = document.forms[0].invalid_dt.value;
	
	if(!forDate(begin_dt)){
		alert("��ʼ�������벻��ȷ��");
		document.forms[0].begin_dt.focus();
		return;
	}
	if(!forDate(invalid_dt)){
		alert("��ֹ�������벻��ȷ��");
		document.forms[0].invalid_dt.focus();
		return;
	}
	if(document.forms[0].begin_dt.value > document.forms[0].invalid_dt.value){
	    alert("��ֹ���ڱ��������ʼ���ڣ�");
		document.forms[0].invalid_dt.focus();
		return; 
	}		

	//�������ɫ����ѡ��ɫ��
	if(!checkMainRole(document.forms[0].role_name,document.forms[0].rolesRight))
	{
		return;
	}
	function checkMainRole(text,list){	
		//����ɫ���б��и�����бȽ�
		for(var i=0; i<list.options.length;i++)
		{
			if(list.options[i].text == text.value){				
				return true;
			}
		}
		//���б���δ��������ɫ
		alert("����ɫ������ѡ��ɫ�У�");
		list.focus();
		return false;
	}
	
	//�޸�  
	var rolesRight=document.forms[0].rolesRight;
	for(var i=0;i<rolesRight.length;i++){
		rolesRight.options[i].selected=true;
	}
	document.forms[0].submit();
	
}

function moveItems(source,target,type){
	
	if(type == "selected") {
		moveSelected(source,target);
	}
	if (type == "all"){
		moveAll(source,target);
	}
	
	<%
	List collRoles = (ArrayList)request.getAttribute("collRoles");
	if(collRoles != null){
		Iterator iter = collRoles.iterator();
		while(iter.hasNext()){
			request.setAttribute("coll_id",iter.next());
	%>
	var coll_id = "<%=(String)request.getAttribute("coll_id")%>";
	var rolesRight=document.forms[0].rolesRight;
	for(var i = 0;i<rolesRight.options.length;i++){
		if(rolesRight.options[i].value == coll_id){
			document.getElementById("invalid_dt").readOnly=true;
			document.getElementById("invalid_dt").className="Textfield-READONLY";
			document.forms[0].invalid_dt.value="20991231";
			return;
		}
	}
	<%
	}
	}
	%>
	document.getElementById("invalid_dt").readOnly=false;
	document.getElementById("invalid_dt").className="Textfield";
	document.forms[0].invalid_dt.value="<%=userForm.getInvalid_dt()%>";
	return;
}


<% 
	String success = (String)request.getAttribute("success"); 
	if(null != success && success.equals("y"))	{		 
		%> 
		alert("�����޸ĳɹ���"); 
		<% 
	}
%> 

function doPopOrg() {
	openWin("OrgPop.do?act=list&id_field=amsd_store&name_field=org_name","user_org_pop");
}
<% 
	String org_id = (String)request.getSession().getAttribute("org_id"); 	
%>
 
</script>
 
<body  class="body_bg_grey1"> 
<html:form method="post" action="User.do"> 
<input type=hidden name=act value=u> 
<input type=hidden name=step value="commit">
<html:hidden  property="depart_id"/>
<html:hidden  property="password"/>
<html:hidden  property="status"/>
<html:hidden  property="reg_dt"/>
<html:hidden  property="st_chg_dt"/>
<html:hidden  property="admin_id"/> 
<html:hidden  property="login_id2"/> 


<%=ViewUtil.getTitle("�޸��û���Ϣ")%>

    <table   width="98%"  align=center class="dtPanel_Line3" border="0" cellspacing="1" cellpadding="0">
          
       
     
      
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;�û���ţ�</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="user_id" styleClass="Textfield-READONLY" readonly="true" size="16" maxlength="8" /> 
         </td> 
       </tr>
       
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>����������</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="user_name" styleClass="Textfield"  size="26" maxlength="18"  onblur="onlyName(this)" onkeyup="onlyName(this)"/> </td> 
       </tr>   
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;�û�����</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="login_id"  styleClass="Textfield-READONLY" readonly="true"  size="20" maxlength="10" /> </td> 
       </tr> 
 
 
        <tr bgcolor="#FFFFFF"> 
          <td " align="right" class="dtPanel_Left">&nbsp;��ϵ�绰��</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="phone" styleClass="Textfield"  size="38" maxlength="30" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;�ֻ���</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="mobile" styleClass="Textfield"   onkeypress="checknum()" onkeyup="onlyNum(this)" size="19" maxlength="11" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;�����ʼ���</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="email" styleClass="Textfield"  size="62" maxlength="60" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;�ʱࣺ</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="postcode" styleClass="Textfield"   onkeypress="checknum()" onkeyup="onlyNum(this)" size="20" maxlength="10" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;��ϵ��ַ��</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="address" styleClass="Textfield"  size="62" maxlength="200" /> 
         </td> 
       </tr>  

       
               <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;�û���ɫ��</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">
         <table>
                 	<tr><td  align="center">��ѡ��ɫ</td><td width=70 colspan="2" align="center"></td><td  align="center"><%=ViewUtil.must()%>��ѡ��ɫ</td></tr>
         	<tr>
         		<td  valign="top">
         		<table  border="0" cellpadding="0" cellspacing="2">
         		<tr><td>        			
         		
         		<select name="rolesLeft" id="rolesLeftId" size="14"  multiple  class="Select-Multiple"	style="width:150">
	    <%
	    		//��action�����û�����ѡ��δѡ��Ľ�ɫȨ�޼���
				List rolesNotSelected=(ArrayList)request.getAttribute("rolesNotSelected");	    		
				
				//���rolesNotSelected��Ϊ��,�����б��ж���ȡ�����ֵ,
				//Ȼ�����б�����ʽ��ʾ
				if(rolesNotSelected != null ) {
					Iterator iter = rolesNotSelected.iterator();
					String role_id = null;		//��ɫ����
					String role_name = null;		//��ɫ����					
					
						while (iter.hasNext()) {
							UserRoleVO vo = (UserRoleVO)iter.next();
							role_id = vo.getRole_id();
							role_name=vo.getRole_name();	
						
							String str="<option ";
							str=str+" value='"+role_id+"'>";
							str=str+role_name;
							str=str+"</option>";
							out.println(str);
						}
				}					
			%>         
  				</select> 
  				</td></tr></table>
  				       
         		</td>         		
         		<td  valign="top" width=88 colspan="2" align="center">

		���� 		<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesLeft,document.forms[0].rolesRight,"selected")'  value=" ���� > "> 
			    	<br><br>
			    	<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesRight,document.forms[0].rolesLeft,"selected")'  value=' < ɾ�� '>
			    	<br><br>
			    	<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesLeft,document.forms[0].rolesRight,"all")'  value='ȫ����>>'>
			    	<br><br>
			    	<input type='button' class='Button'  onClick='moveItems(document.forms[0].rolesRight,document.forms[0].rolesLeft,"all")'  value='<<ȫɾ��'>	    	
	
      			</td>
         		
         		<td   valign="top">
         		
         		<table  border="0" cellpadding="0" cellspacing="2">
         		<tr><td>
         		
         		<select name="rolesRight"  id="rolesRightId"  size="11"  multiple  class="Select-Multiple"	style="width:150">
 <%
	    		//��action�����û���ѡ��Ľ�ɫȨ�޼���	
	    		List rolesSelected=(ArrayList)request.getAttribute("rolesSelected");					
				
				//���rolesSelected��Ϊ��,�����б��ж���ȡ�����ֵ,
				//Ȼ�����б�����ʽ��ʾ
				if(rolesSelected!= null ) {
					Iterator iter = rolesSelected.iterator();
					String role_id = null;		//��ɫ����
					String role_name = null;		//��ɫ����					
					
					while (iter.hasNext()) {
						UserRoleVO vo = (UserRoleVO)iter.next();
						role_id = vo.getRole_id();
						role_name=vo.getRole_name();	
						
						String str="<option ";
						str=str+" value='"+role_id+"'>";
						str=str+role_name;
						str=str+"</option>";
						out.println(str);
						}
				}					
	%>
	</select> 
	</td></tr>
	<tr><td>
	<input type="button"  style="width:149"  class=Button5    
		onClick="moveOneSelectedToText(document.forms[0].rolesRight,document.forms[0].role_name,document.forms[0].role_id)"  value="��Ϊ����ɫ">
	</td></tr>
	<tr><td>	
		<%=ViewUtil.must()%>����ɫ <input type=text name="role_name"  readonly class="Textfield-READONLY" value="<%=ReDefSDicMap.getDicItemVal("0002",userForm.getRole_id())%>" size="15"/>			
		<html:hidden  property="role_id" />
		</td></tr>
		</table>
         		</td>
         	</tr>
         </table>
          
         </td> 
       </tr>       
   
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ʼ���ڣ�</td> 
         <td align="left" class="dtPanel_Main2">&nbsp;          
         <html:text property="begin_dt" styleClass="Textfield"   onkeypress="checknum()" onkeyup="onlyNum(this)"  size="8" maxlength="8"  /> 
         &nbsp;����ʽ��yyyymmdd��
         </td> 
         <td width="18%" align="right" class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>ʧЧ���ڣ�</td>
         <td align="left" class="dtPanel_Main2">&nbsp; 
         <%
          String collCheck = (String)request.getAttribute("collCheck");
          if(collCheck != null && collCheck.equals("0")){
          %>
          <html:text property="invalid_dt" styleId="invalid_dt" onkeypress="checknum()" onkeyup="onlyNum(this)"  styleClass="Textfield-READONLY" readonly="true" size="8" maxlength="8"  value="20991231" />          
          <%} else{%>
          <html:text property="invalid_dt" styleId="invalid_dt" onkeypress="checknum()" onkeyup="onlyNum(this)"  styleClass="Textfield" size="8" maxlength="8" />
          <%}%>
          &nbsp;����ʽ��yyyymmdd��
         </td>
       </tr>
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;����ʧЧ���ڣ�</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="modify_dt"  onkeyup="onlyNum(this)" styleClass="Textfield-READONLY" readonly="true" size="8" maxlength="8" /> 
         </td> 
       </tr> 
 
        <tr bgcolor="#FFFFFF"> 
          <td  align="right" class="dtPanel_Left">&nbsp;��ע��</td> 
         <td colspan="3" align="left" class="dtPanel_Main2">&nbsp; 
         <html:text property="memo" styleClass="Textfield"  size="62" maxlength="200" /> 
         </td> 
       </tr>  
  </table>  

  	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table   align=center width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr> 
				<td height="25" align="center"  class="dtPanel_Bottom"> 
						<input	name="update" type="button" class="Button" value="����" onClick="doSave()"> &nbsp; 
						<html:reset value="����" styleClass="Button"/>  &nbsp; 
						<input name="return" type="button" class="Button" value="����" onClick="location.href='User.do?act=list';">
						  
		 		</td> 
	    </tr> 
  </table> 
 
</html:form> 
 
</body> 
</html> 
<SCRIPT LANGUAGE="JavaScript" src="js/move.js">
<!--
//-->
</SCRIPT>

 
