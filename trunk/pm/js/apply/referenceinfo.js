//�����Ƽ�����ϢУ��
function checkRef_info(obj) 
{
 var str=obj.value;
 var ref_type="";
 for(var i=0;i<document.forms[0].ref_type.length;i++)
  if(document.forms[0].ref_type[i].checked)
      { 
      	ref_type=document.forms[0].ref_type[i].value;
      } 
 document.forms[0].ref_no_id.value='';
 if(ref_type=="")
 {
 document.forms[0].ref_info.value="";
 alert("��ѡ���Ƽ����Ƽ�����");
 }
 if(str!=''){
/* if(str.charAt(0)!='N' && str.charAt(0)!='C')
 {
 document.forms[0].ref_info.value='';
 document.forms[0].ref_info.focus();
 return ; 
}
*/
if(ref_type=="N")
{
	var strCheck = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	var nLength = str.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = str.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			
			document.forms[0].ref_info.value=str.substring(0,i);
			document.forms[0].ref_info.focus();
			return false;
		}
	}
}

if(ref_type=="C")
{
	var strCheck = "1234567890";
	var nLength = str.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = str.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			
			document.forms[0].ref_info.value=str.substring(0,i);
			document.forms[0].ref_info.focus();
			return false;
		}
	}
}	
}
}

function checkRef_info_length(obj){
var str=obj.value;
 var ref_type='';
 for(var i=0;i<document.forms[0].ref_type.length;i++)
  if(document.forms[0].ref_type[i].checked)
      { 
      	ref_type=document.forms[0].ref_type[i].value;
      } 
document.forms[0].ref_no_id.value='';
if(str!='' && ref_type=='')
alert("��ѡ�������Ƽ�����");
if(str!=''){
if(ref_type=="C")
if(str.length!=16)
{
  var esrc = document.getElementById(obj);
  if (esrc == null) {
      esrc = event.srcElement;
  }
  var rtextRange = esrc.createTextRange();
  rtextRange.moveStart('character', esrc.value.length);
  rtextRange.collapse(true);
  rtextRange.select();
  alert("�Ƽ������Ϊ����ʱ������16λ������");
}
if(ref_type=="N")
if(str.length==1)
{
  var esrc = document.getElementById(obj);
  if (esrc == null) {
      esrc = event.srcElement;
  }
  var rtextRange = esrc.createTextRange();
  rtextRange.moveStart('character', esrc.value.length);
  rtextRange.collapse(true);
  rtextRange.select();
  alert("���ʵ�Ƽ��������Ϣ");
}
}
}
//�����Ƽ�����ϢУ��
function checkPer_ref_info(obj) 
{
 var str=obj.value;
  var ref_type="";
 for(var i=0;i<document.forms[0].per_ref_type.length;i++)
  if(document.forms[0].per_ref_type[i].checked)
      { 
      	ref_type=document.forms[0].per_ref_type[i].value;
      } 
 document.forms[0].per_ref_no_id.value='';
 if(ref_type=="")
 {
 document.forms[0].per_ref_info.value='';
 alert("��ѡ���Ƽ����Ƽ�����");
 }
 if(str!=''){
 /*
 if(str.charAt(0)!='N' && str.charAt(0)!='C')
 {
 document.forms[0].per_ref_info.value='';
 document.forms[0].per_ref_info.focus();
 return true; 
 }
 */
    if(ref_type=="N"){
	var strCheck = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	var nLength = str.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = str.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			
			document.forms[0].per_ref_info.value=str.substring(0,i);
			document.forms[0].per_ref_info.focus();
			return false;
		}
	}
	}
	if(ref_type=="C"){
	var strCheck = "1234567890";
	var nLength = str.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = str.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			document.forms[0].per_ref_info.value=str.substring(0,i);
			document.forms[0].per_ref_info.focus();
			return false;
		}
	}
	}
	
}
}

function checkPRef_info_length(obj){
var str=obj.value;
var ref_type='';
 for(var i=0;i<document.forms[0].per_ref_type.length;i++)
  if(document.forms[0].per_ref_type[i].checked)
      { 
      	ref_type=document.forms[0].per_ref_type[i].value;
      } 
document.forms[0].per_ref_no_id.value='';
if(str!='' && ref_type=='')
alert("��ѡ�񸽿��Ƽ�����");
if(str!=''){
if(ref_type=="C")
if(str.length!=16)
{
  var esrc = document.getElementById(obj);
  if (esrc == null) {
      esrc = event.srcElement;
  }
  var rtextRange = esrc.createTextRange();
  rtextRange.moveStart('character', esrc.value.length);
  rtextRange.collapse(true);
  rtextRange.select();
  alert("�Ƽ������Ϊ����ʱ������16λ������");
}
if(ref_type=="N")
if(str.length==1)
{
  var esrc = document.getElementById(obj);
  if (esrc == null) {
      esrc = event.srcElement;
  }
  var rtextRange = esrc.createTextRange();
  rtextRange.moveStart('character', esrc.value.length);
  rtextRange.collapse(true);
  rtextRange.select();
  alert("���ʵ�Ƽ��������Ϣ");
}
}
}

function checkPer_ref_infoN(obj) 
{
 var str=obj.value;
 if(str!=''){
	var strCheck = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	var nLength = str.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = str.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			
			document.forms[0].ref_member_id.value=str.substring(0,i);
			document.forms[0].ref_member_id.focus();
			return false;
		}
	}	
}
}

function checkPer_ref_infoR(obj) 
{
 var str=obj.value;
 if(str!=''){
	var strCheck = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	var nLength = str.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = str.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			
			document.forms[0].cert_no_ref.value=str.substring(0,i);
			document.forms[0].cert_no_ref.focus();
			return false;
		}
	}	
}
}

function getSelectedButtonVa(buttonGroup){ 	
	for(var i=0;i<buttonGroup.length;i++){
		if(buttonGroup[i].checked){
			return i;
		}
	}
	return 0;
}

function checkref_phone(obj) 
{
 var str=obj.value;
 if(str!=''){
	var strCheck = "1234567890-()+";
	var nLength = str.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = str.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			
			document.forms[0].ref_phone.value=str.substring(0,i);
			document.forms[0].ref_phone.focus();
			return false;
		}
	}	
}
}
//ͨ����ѯ�Ƽ��˵õ���Ϣ���ָ����Ƽ����ͣ���ʱ����Ƽ���id
function clean_per_no_id(obj)
{
	document.forms[0].per_ref_no_id.value='';
}
//ͨ����ѯ�Ƽ��˵õ���Ϣ���ָ����Ƽ����ͣ���ʱ����Ƽ���id
function clean_ref_no_id(obj)
{
	document.forms[0].ref_no_id.value='';
}