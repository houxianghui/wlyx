<%@ include file = "/includes/common.jsp" %>

<%@ page contentType="text/html; charset=GBK"%>

<html>

<title>���Ӳ˵�</title>


<script language="javascript">

function doAdd(){
	//ִ��У��
	var field = new Array("menu_name","list_order");
	var info = new Array("�˵�����","��ʾ˳��");
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
	if(getStrLength(document.forms[0].menu_name.value)>40) { 		
	        	alert('�˵����Ƴ����벻Ҫ����40���ַ�!'); 
	        	document.forms[0].menu_name.focus(); 
	        	return; 
	}
	//����ֶγ���
	if(getStrLength(document.forms[0].menu_url.value)>200) { 		
	        	alert('�˵�URL�����벻Ҫ����200���ַ�!'); 
	        	document.forms[0].menu_url.focus(); 
	        	return; 
	}	

	if(!isInteger(document.forms[0].list_order.value)){
		alert("��ʾ˳��Ҫ����������!");
		document.forms[0].list_order.focus();
		return;
	}
	//����	
	document.forms[0].submit();
	
}


</script>
<body  class="body_bg_grey1">


<p>&nbsp;</p>
<form name="menuForm" method="post"  action="Menu.do">
<input type=hidden name=act value="c">
<input type=hidden name=step value="commit">
 
 <%=ViewUtil.getTitle("���Ӳ˵�")%>
 
    <table align="center"  class="dtPanel_Line3" width="98%" border="0" cellspacing="1" cellpadding="0">      
       
		<tr>
          <td width="40%" align="right"  class="dtPanel_Left">&nbsp;�ϼ��˵���</td>
          <td align="left" class="dtPanel_Main2">&nbsp;                 
           <input type="text" name="parent_name" maxlength="40" size="20" value="<%= request.getParameter("menu_name")%>"  readonly="true"  class="Textfield-READONLY">
           <input type=hidden name=parent_id  value="<%= request.getParameter("menu_id")%>"  >   
          </td>
        </tr>
        <tr >
          <td  align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>�˵����ƣ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="menu_name" maxlength="40" size="20" value="" class="Textfield">
         </td>
        </tr>
        
        <%
        	 String level=request.getParameter("menu_level");
        	 level=Integer.toString(Integer.parseInt(level)+1);        	 
        %>
        <tr >
          <td " align="right"  class="dtPanel_Left">&nbsp;�˵����Σ�</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="menu_level" maxlength="16" size="20" readonly="true" value="<%=level%>" class="Textfield-READONLY">
          </td>
        </tr>
        <tr >
          <td  align="right"  class="dtPanel_Left">&nbsp;<%=ViewUtil.must()%>��ʾ˳��</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="list_order" maxlength="16" size="20" value="" onkeypress="checknum()"  onkeyup="onlyNum(this)"  class="Textfield">
        </td>
        </tr>         
        <tr >
          <td  align="right"  class="dtPanel_Left">&nbsp;�˵�URL��</td>
          <td align="left" class="dtPanel_Main2">&nbsp;
          <input type="text" name="menu_url" maxlength="200" size="36" value="" class="Textfield">
        </td>
        </tr>
  </table>        
     
     
  	<table  class=heightspace_bottom1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
    <table align="center" width="98%" border="0" cellspacing="0" cellpadding="0">  
           <tr>				
			<td height="25" align="center"  class="dtPanel_Bottom">
				<input	name="add" type="button" class="Button" value="����" onClick="doAdd()"> &nbsp;
				<input name="return" type="button" class="Button" value="����" onClick="history.back()">  
						
		  </td>
	  </tr>
  </table>
			
		
</form>

<p>&nbsp;</p>

</body>
</html>
