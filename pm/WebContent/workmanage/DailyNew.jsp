<%@ include file = "/includes/common.jsp" %>
<%@ page  contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
tr{
	background-color:#F7F7F7;
	COLOR:#333333;
	FONT-SIZE:9pt;
	FOTN-FAMILY:����,verdana,Arial, Helvetica;
	height:23px;
	text-align: center
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>�����ձ�</title>
<script type="text/javascript">
function addWorkContent(){
	var tbName = "workContent";
	 var rowIndex = findObj(tbName+".index",document);
	 var rowID = parseInt(rowIndex.value);
	innerHtml1 = "<textarea name='work_"+ rowID + "' id='work_" + rowID + "'  class='TextField' rows='3' cols='100'/>";
	
	addRow(tbName,new Array(innerHtml1));
}
function addAchieveContent(){
	var tbName = "achieve";
	 var rowIndex = findObj(tbName+".index",document);
	 var rowID = parseInt(rowIndex.value);
	innerHtml1 = "<textarea name='achieve_"+ rowID + "' id='achieve_" + rowID + "'  class='TextField' rows='3' cols='100'/>";
	innerHtml2 = "<input name='achieve_"+ rowID + "_percent' id='achieve_" + rowID + "_percent' type='text' class='TextField' size='3' maxLength='3' onblur=onlyNum(this) onkeyup=onlyNum(this) onkeydown=onlyNum(this) value='100'/>";
	addRow(tbName,new Array(innerHtml1,innerHtml2));
}
function addIssue(){
	var tbName = "issue";
	 var rowIndex = findObj(tbName+".index",document);
	 var rowID = parseInt(rowIndex.value);
	innerHtml1 = "<textarea name='issue_"+ rowID + "' id='issue_" + rowID + "'  class='TextField' rows='3' cols='100'/>";
	innerHtml2 = "<input name='issue_"+ rowID + "_percent' id='issue_" + rowID + "_percent' type='text' class='TextField' size='3' maxLength='3' onblur=onlyNum(this) onkeyup=onlyNum(this) onkeydown=onlyNum(this) value='100'/>";
	addRow(tbName,new Array(innerHtml1,innerHtml2));
}
function doSave(){
	document.forms[0].submit();
}
</script>
</head>
<body>
	<script type="text/javascript" src="js/calendar.js"></script>
	<form action="WorkDaily.do" method="post">
		<html:hidden property="act" value="add"/>
		
		<input name='txtTRLastIndex' type='hidden' id='workContent.index' value="1" />
  		<input name='txtTRLastIndex' type='hidden' id='achieve.index' value="1" />
  		<input name='txtTRLastIndex' type='hidden' id='issue.index' value="1" />
  		<table width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="left" class="dtPanel_Top01">
			<td align="left">�����
  			<input name="date" class="Textfield" maxlength="8" size="8" onclick="new Calendar().show(this)" readonly="true" value="<%=request.getAttribute("date")%>">
  			</td>
  			</tr>
		</table>
		<br>
		<table id="workContent" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
		
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">���</td><td>��������<input type="button" class="Button" onclick="addWorkContent()" value="��ӵ��չ���"></button></td><td width="10%">����</td>
			</tr>
		</table>
		<br>
		<table id="achieve" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">���</td><td>�����ɹ�<input type="button" class="Button" onclick="addAchieveContent()" value="��ӵ��ճɹ�"></button></td><td width="5%">�ٷֱ�</td><td width="5%">����</td>
			</tr>
		</table>
		<br>
		<table id="issue" width="98%" class="dtPanel_Line1" border="0" cellspacing="1"
		align="center" cellpadding="0">
			<tr align="center" class="dtPanel_Top01">
			<td width="5%">���</td><td>��������<input type="button" class="Button" onclick="addIssue()" value="�����������"></button></td><td width="5%">�ٷֱ�</td><td width="5%">����</td>
			</tr>
		</table>
	</form>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center">
			<input type="button" class="Button" onclick="doSave()" value="����"></button>
			<input type="button" class="Button" onclick="history.back()" value="����"></button>
			</td>
		</tr>
	</table>
</body>
</html>