/*
���ڴ����������ݵĺ���
Ϊ��ʹ���ű���IE��NS���������У���Ԫ�صĵ���ʹ��document.forms[0].test1��ʽ
������form.test1��ʽ
*/
 

//ֻ��������-use
function onlyNum(obj){
	if(obj.value!=obj.value.replace(/[^\u0030-\u0039]/g,''))
	obj.value=obj.value.replace(/[^\u0030-\u0039]/g,'');
}
//ֻ����Ӣ��,���Զ���Ӣ��ת��Ϊ��д-use
function onlyEngToUpperCase(obj){
	obj.value=obj.value.replace(/[^\u0041-\u007A]/g,'');
	obj.value=obj.value.toUpperCase();
}
//��������-use
function onlyName(obj){
	if(obj.value!=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0041-\u005A\u0061-\u007A\u0020]/g,''))
		obj.value=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0041-\u005A\u0061-\u007A\u0020]/g,'');
}
  
//������������
function noChinese(obj){
	if(obj.value!=obj.value.replace(/[\u4E00-\u9FA5\u00B7\u0020]/g,''))
		obj.value=obj.value.replace(/[\u4E00-\u9FA5\u00B7\u0020]/g,'');
}
/*
ȥ���ַ������ߵĿ��ַ�
*/
function trim(s)
{
	return trimRight(trimLeft(s))
}
/****************************************/
/* Trim the left blank of the string    */
/****************************************/
function trimLeft(s) {
	while (s.charAt(0) ==" " ||s.charAt(0) =="��" ){
		s = s.substr(1,s.length-1)
	}
	return s;
}
/*****************************************/
/* Trim the right blank of the string    */
/*****************************************/
function trimRight(s) {
	while (s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "��")	{
		s = s.substr(0,s.length-1)
	}
	return s;
}

/**
�õ��ַ����ĳ��ȣ������ַ�����Ϊ2
*/
function getStrLength( str ) {
    num = str.length;
    var arr = str.match(/[^\x00-\x80]/ig);
    if( arr != null )
        num += arr.length;
    return num;
}


  //���ַ����еĿ��˵�
function KillSpace(s){
    var s1="";
     if (isEmpty(s))//�ж��Ƿ�Ϊ��
     {	
	    return s;
     }else{//��Ϊ��
	    var len=s.length;
		for (var i=0;i<len;i++)
		{
		   if (!warnCharsInBag(s.charAt(i), " "))
		   {
		      s1=s1+s.charAt(i);
		   }
		}
	 }
	 return s1;
  }


/**
��ҳҳ���麯��
*/
function checkPageNO( pageNO, maxPageNO ) {
    if ( pageNO == "" ) {
        alert( "����дҳ�룡" );
        return false;
    } else if ( !isInteger( pageNO ) ) {
        alert( "����д��ҳ�벻��ȷ��" );
        return false;
    } else if ( parseInt( pageNO ) <= 0 ) {
        alert( "ҳ�벻��С��0��" );
        return false;
    } else if ( parseInt( pageNO ) > parseInt( maxPageNO ) ) {
        alert( "ҳ�벻�ܴ������ҳ�룡" );
        return false;
    }
    return true;
}


/**
 *�������б�ҳ����ʾ���������Ĺ̶�����showScrollHeader������ʾͷ�ű���showScrollFooter������ʾβ�ű�
 *���ߣ�����
 *ʱ�䣺200603018
 */
function showScrollHeader() {
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.write("<div align=\"center\"><div   align=\"center\"   style=\"overflow:auto;width:98%\">");
	} else {
		document.write("<table width=\"98%\"  align=\"center\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">	<tr>");
		document.write("<td valign=\"top\"  align=\"left\">");
		document.write("<div   align=\"center\"   style=\"overflow:auto;width:100%\">");
	}
}

function showScrollFooter() {
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.write("</div>	</div>");
	} else {
		document.write("</div>	</td>	</tr></table>");
	}
}

