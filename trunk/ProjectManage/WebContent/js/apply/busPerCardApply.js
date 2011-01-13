

//检察是否是输入数字,也可以有小数点
function checkDouble(obj){
	/*if ((event.keyCode > 47 && event.keyCode < 58) || event.keyCode == 46) {
		event.returnValue = true;
	}
	else
		event.returnValue = false;
	*/
	if(obj.value!=obj.value.replace(/[^\u0030-\u0039\u002E]/g,''))
		obj.value=obj.value.replace(/[^\u0030-\u0039\u002E]/g,'');
	
}

function checkFormPer(){

	/**检查字段，若有回车,去掉回车*/
	var fielde = new Array("cdholder_name","cdholder_last_name","cdholder_first_ename","cdholder_cert_no","cdholder_addr1","lm_name"); 
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/**end*/

	var field = new Array("cdholder_name","cdholder_last_name","cdholder_first_ename","cdholder_birth","nationality","cdholder_cert_no","pre1","per_post","comp_zone_no","comp_phone","pre_zone_no","pre_phone","posi_dept","cdholder_app_lmt","lm_name","lm_zone_no","lm_phone","cdholder_addr1"); 
	var info = new Array("持卡人姓名","持卡人姓拼音","持卡人名拼音","持卡人出生日期","国籍","证件号码","住址地址城市代码","邮政编码","单位电话区号","单位电话号码","住址电话区号","住址电话","任职部门","单位授信额度","联系人姓名","联系人电话区号","联系人电话号码","住宅地址"); 
 
	//检察输入信息是否为空 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	     tmp ="document.forms[0]."+field[i]+".value"; 
	     if(isEmpty(eval(tmp))) { 
	      	alert('请输入'+info[i]); 
	      	eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	     }
	} 
 
  	//执行校验   单选按钮
	//var fieldB = new Array("cdholder_sex","marr_stat","cdholder_cert_type","cdholder_teac","bill_addr","relation_bet","note_flag","cdholder_posi"); 
	//var infoB = new Array("持卡人性别","婚姻状况","证件类型","教育程度","对账单邮寄地址","联系人与持卡人关系","短信通知服务","职务岗位"); 
    
    //modified by xmli 2007.08.21
 	var fieldB = new Array("cdholder_sex","marr_stat","cdholder_cert_type","cdholder_teac","bill_addr","relation_bet","cdholder_posi"); 
	var infoB = new Array("持卡人性别","婚姻状况","证件类型","教育程度","对账单邮寄地址","联系人与持卡人关系","职务岗位"); 
 
	//检察输入信息是否为空 
	var tmpB; 
	for(var i=0;i<fieldB.length;i++){ 
		tmpB ="document.forms[0]."+fieldB[i]+".length"; 
	    var flag = 0;
	    for(var j=0;j<eval("document.forms[0]."+fieldB[i]+".length");j++){ 	
	       	if(eval("document.forms[0]."+fieldB[i]+"["+j+"].checked")){
	        	flag = 1;
	        	break;
	        }
	    } 	
	        	
	    if(flag == 0){
	       	alert('请输入'+infoB[i]); 
			eval("document.forms[0]."+fieldB[i]+"[0].focus()"); 
			return false; 
		}	     	
	} 
	
		
	//检查姓拼音长度为8,名拼音长度为16
	if(document.forms[0].cdholder_last_name.value.length > 10){
		alert("姓拼音长度最大为10位");
		document.forms[0].cdholder_last_name.focus();
		return false;
	}
	if(document.forms[0].cdholder_first_ename.value.length > 16){
		alert("名拼音长度最大为16位");
		document.forms[0].cdholder_first_ename.focus();
		return false;
	}
	if(!isLetter(document.forms[0].cdholder_last_name.value)){
		alert("姓拼音应为大写英文字母");
		document.forms[0].cdholder_last_name.focus();
		return false;
	}
	if(!isLetter(document.forms[0].cdholder_first_ename.value)){
		alert("名拼音应为大写英文字母");
		document.forms[0].cdholder_first_ename.focus();
		return false;
	}
	
	/*检查出生日期=======================================*/
	obj=document.forms[0].cdholder_birth;
	if(!checkdateNumber(obj)){
		alert("出生日期应为8位");
		obj.focus();
		return false;
	}
	if(!checkDate(obj)){
		obj.focus();
		return false;
	}
	
	/*检查申请人年龄====================================*/	
    //年龄要求在18至99周岁之间
    currentDate = new Date();
    currentYear = parseInt(currentDate.getFullYear());
    
    strBirthday = document.forms[0].cdholder_birth.value;
    intAge = currentYear - parseInt(strBirthday.substring(0,4));
    if(intAge < 18){
		alert("持卡人年龄不符合发卡要求！");
		document.forms[0].cdholder_birth.focus();
		return false;
    }
    if(intAge > 99){
		alert("持卡人年龄不符合发卡要求！");
		document.forms[0].cdholder_birth.focus();
		return false;
    }
	      
    
    /*检查证件号码=========================================*/
   if(getSelectedButton(document.forms[0].cdholder_cert_type)== 0){
    	obj = document.forms[0].cdholder_cert_no;
    	if(!isIDNO(obj)){
    		document.forms[0].cdholder_cert_no.focus();	
    		return false;
    	}  			
	}
   
	/*检查 住址地址 不能超过40位*/
	var cdholder_addr1=document.forms[0].cdholder_addr1;
	if(getStrLength(cdholder_addr1.value) > 40){
		alert("住址地址不能超过40位");
		cdholder_addr1.focus();
		return false;
	}
    
    /*检查邮编位数=======================================*/
	var field = new Array("per_post"); 
	var info = new Array("持卡人住址邮编"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value.length"; 
	        if((eval(tmp)) < 6) { 
	        	alert(info[i]+'应为六位'); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return false; 
	       	} 
	} 
	
	/*检查手机号=======================================*/
	var field = new Array("pre_mobile","note_mobile"); 
	var info = new Array("持卡人移动电话","短信开通手机号码"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		if(!isEmpty(eval(tmp))){
			if(!isMobile(eval(tmp))){
				alert(info[i]+'输入有误');
				eval("document.forms[0]."+field[i]+".focus()");
				return false;
			}
		}
	}
	
	if(!isEmpty(document.forms[0].pre_mobile.value)){
		if(document.forms[0].pre_mobile.value.length != 11){
			alert("手机号码为11位数字");
			document.forms[0].pre_mobile.focus();
			return false;
		}
	}	
	if(!isEmpty(document.forms[0].note_mobile.value)){
		if(document.forms[0].note_mobile.value.length != 11){
			alert("手机号码为11位数字");
			document.forms[0].note_mobile.focus();
			return false;
		}
	}

	//电话号码不能小于7位
	var field = new Array("comp_phone","pre_phone","lm_phone"); 
	var info = new Array("单位固定电话","住址电话","联系人电话"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value.length";
		if(eval(tmp) < 7){
			alert(info[i]+'长度不能小于7位');
			eval("document.forms[0]."+field[i]+".focus()");
			return false;
		}
	}
	
	//若电话号码输入全零，将会报错
	var field = new Array("comp_zone_no","comp_phone","pre_zone_no","pre_phone","lm_zone_no","lm_phone"); 
	var info = new Array("持卡人单位电话区号","持卡人单位电话号码","持卡人住址电话区号","持卡人住址电话","联系人电话区号","联系人电话"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		if(isZero(trim(eval(tmp)))){
			alert(info[i]+'不能全输入0');
			eval("document.forms[0]."+field[i]+".focus()");
			return false;
		}
	}	
	
	//选择证件类型,若为身份证,检查;若为其他,则"请注明"为必填项
	var cert_type = getSelectedButton(document.forms[0].cdholder_cert_type);
	//号码和生日的一致性
	if(cert_type == 0){
		str = document.forms[0].cdholder_cert_no.value;
		str2 = document.forms[0].cdholder_birth.value;
		if(str.length==15){
			strb=str.substring(6,12);
			str3=str2.substring(2,8);
			if(strb!=str3){
				alert("身份证号码与出生日期不符!");
				document.forms[0].cdholder_cert_no.focus();
				return false;
			}
			var strsex=str.charAt(14);
		}
		if(str.length==18){
			strb=str.substring(6,14);
			if(strb!=str2) {
				alert("身份证号码与出生日期不符!");
				document.forms[0].cdholder_cert_no.focus();
				return false;
			}
			var strsex=str.charAt(16);
		}
		//性别和号码的一致性
		if(strsex % 2==0) strsex="F";
		else strsex="M";
		obj = document.forms[0].cdholder_sex
		if(!((obj[0].checked && strsex=="M")||(obj[1].checked && strsex=="F"))){
			alert("身份证号码与性别不符!!");
			document.forms[0].cdholder_cert_no.focus();
			return false;
		}
	    
	    //检查校验码
		if(str.length==18){
			//校验码
			var strjiaoyan=new Array("1","0","X","9","8","7","6","5","4","3","2");
			//加权因子
			var intQuan=new Array("7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2","1");
			var sum=0;
			for(var inum=0;inum<17;inum++)	sum+=str.substring(inum,inum+1) * intQuan[inum];
			var strresult=strjiaoyan[sum % 11];
			if(str.substring(17,18).toUpperCase()!=strresult) {
				alert("身份证号码校验码不正确!");
				document.forms[0].cdholder_cert_no.focus();
				return false;
			}
		}
	}
	
	//若证件类型为其他
	if(getSelectedButton(document.forms[0].cdholder_cert_type) == 2){
		
		if(isEmpty(document.forms[0].cert_name.value)){
			alert("请输入其他证件名称");
			document.forms[0].cert_name.focus();
			return false;
		}
	}
	//若证件类型不为"其他",则"请注明"文本框为空,不填值
	if(getSelectedButton(document.forms[0].cdholder_cert_type) != 2){
		if(!isEmpty(document.forms[0].cert_name.value)){
			alert("证件类型冲突");
			document.forms[0].cert_name.focus();
			return false;
		}
	}
	
	//若对帐单地址为其他
	/*if(getSelectedButton(document.forms[0].bill_addr) == 2){
		if(isEmpty(document.forms[0].bill_otheraddr.value)){
			alert("请输入对账单地址");
			document.forms[0].bill_otheraddr.focus();
			return false;
		}
	}
	//若对帐单地址不为其他
	if(getSelectedButton(document.forms[0].bill_addr) != 2){
		if(!isEmpty(document.forms[0].bill_otheraddr.value)){
			alert("对账单地址有误");
			document.forms[0].bill_otheraddr.focus();
			return false;
		}
	}*/
	
		
	//若账单地址为“住址”
	if(getSelectedButton(document.forms[0].bill_addr) == 1){
		if(isEmpty(document.forms[0].cdholder_addr1.value)){
			alert("请输入住宅地址");
			document.forms[0].cdholder_addr1.focus();
			return false;
		}
	}
	
	
	//校验预留信息,若填写了问题,则答案必输;若填写了答案,则问题必输
	var ques = document.forms[0].remain_ques;
	var ans = document.forms[0].remain_ans;
	if((!isEmpty(ques.value)) == isEmpty(ans.value)){
		alert("请将预留信息输入完整");
		ques.focus();
		return false;
	}
	
	/*手机提示=========================================*/
    if(getSelectedButton(document.forms[0].note_flag)== 0){
    	var tem ;
    	if( null == document.forms[0].note_flag.value ){
    		tem = "";
    	} else {
    		tem = document.forms[0].note_flag.value;
    	}
    	if(tem == "0" ){
    	if(isEmpty(document.forms[0].note_mobile.value)){
    		alert("未输入短信通知服务手机号");
    		document.forms[0].note_mobile.focus();	
    		return false;
    	}  
    	}			
	}
	
	/*单身持卡人不能有配偶联系人==================================*/
   if(getSelectedButton(document.forms[0].marr_stat)== 0){
    	if(getSelectedButton(document.forms[0].relation_bet)== 0){
    		alert("持卡人与联系人关系错误");
    		document.forms[0].relation_bet[0].focus();	
    		return false;
    	}  			
	}
	//单位授信额度校验是否为合法的小数
	var cdholder_app_lmt = document.forms[0].cdholder_app_lmt;
	if(!isEmpty(cdholder_app_lmt.value)){
		if(!IsDecimal(cdholder_app_lmt.value)){
			alert("单位授信额度不合法");
			cdholder_app_lmt.focus();
			return false;
		}
	}
	
	/**应李健要求，校验v＋可能出现的错误  begin*/
	var fieldv = new Array("cdholder_name","cdholder_cert_no","cert_name","cdholder_addr1","pre_phone","comp_phone","posi_dept","lm_name","lm_phone","remain_ques","remain_ans"); 
	var infov = new Array("姓名(中文)","证件号码","其他证件名称","住宅地址","住址电话号码","单位电话号码","任职部门","联系人姓名(中文)","联系人电话号码","预留问题","预留答案"); 
 	var fvaluev = new Array(40,20,20,80,17,17,10,40,20,20,20); 
 
	//检察输入信息是否为空 
	var tmp; 
	for(var i=0;i<fieldv.length;i++)	{ 
	 	tmp ="document.forms[0]."+fieldv[i]+".value"; 
	    if(!isEmpty(eval(tmp))) {
	    	if( getChineseStrLength(eval(tmp)) > fvaluev[i] ){
	    	 	alert(infov[i]+'长度超过限制'); 
			    eval("document.forms[0]."+fieldv[i]+".focus()"); 
			    return false; 
		    }
	     } 
	}
	/**end*/ 
	
	

	
	//证件号码18位或15位时，证件类型应为身份证或临时身份证，其他不符合标准的，
	//系统提示“证件类型错误”，但可以强制通过。
	str = document.forms[0].cdholder_cert_no.value;
	if((str.length==15) || (str.length==18)){
		if(getSelectedButton(document.forms[0].cdholder_cert_type) != 0){		
			alert("持卡人证件类型错误!");}
	}
 
    return true;
}


/*=============================以下为所用到的子函数====================*/

function rmlines(str){
	var s = '';
	for(var i = 0;i<str.length;i++){
		
		if(!(str.charAt(i) == '\x0a' || str.charAt(i) == '\x0d')){
			s += str.charAt(i);
		}
	}
	return s;
}

function checkEnter(str){
	for(var i = 0;i<str.length;i++){
		
		if(str.charAt(i) == '\x0a' || str.charAt(i) == '\x0d'){
			return true
		}
	}
	return false;
}

//对象不能为空或者开头为0
function checkYForGet(obj){
	str=obj.value;
	if(str.length==0) return false;
	if(str.charAt(0)=='0')return false;
	return true;
}

//得到单选框中选中的项
function getSelectedButton(buttonGroup){ 	
	for(var i=0;i<buttonGroup.length;i++){
		if(buttonGroup[i].checked){
			return i;
		}
	}
	return 0;
}

//检查生日
function checkDate(obj){
	str=obj.value;
	if(str.length==0){alert("日期输入不正确");return false;}
	Y=str.substring(0,4);
	M=str.substring(4,6);
	D=str.substring(6,8);
	if(Y<1900 || Y>2100){
		alert("日期输入不正确");
		return false;
	}
	Months= new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	Leap = false;
	if((Y % 4 == 0) && ((Y % 100 != 0) || (Y %400 == 0)))	Leap = true;
	if((D < 1) || (D > 31) || (M < 1) || (M > 12) || (Y < 0))	{alert("日期输入不正确");return false;}
	if((D > Months[M-1]) && !((M == 2) && (D > 28)))	{alert("日期输入不正确");return false;}
	if(!(Leap) && (M == 2) && (D > 28))	{alert("日期输入不正确");return false;} 
	if((Leap) && (M == 2) && (D > 29)) 	{alert("日期输入不正确");return false;} 
	return true;
}

function checkdateNumber(obj){   
  var str;
      str=obj.value;
  if(str.length!=8){
	    return false;
  }
  return true
}

function checkOld(obj,minage,con){
	var str;
	var now=new Date();	
	str=obj.value;
	if(str.length==8){
	  	year=str.substring(0,4);
	  	old=now.getYear()-year
	  	if(old<minage){
	  		alert("年龄不满" + minage + "岁,不能申请" + con);
	  		obj.focus();
	  		return false;
		}
	}
	return true;
}

//检查身份证号码
function isIDNO(obj){
	var s = trim(obj.value);
	if (!((s.length==18) || (s.length==15))){
		//alert(s.length);
		alert("身份证号码长度只能为15或18位");
		
		return false;
	}
	else if (s.length==15){
		if (!warnCharsInBag(s, "0123456789")){
			alert("身份证号码长为15位时,必须全部是数字");
			return false;
		}
	}
	else if (obj.value.length==18){
		if (!isNumber(s)){
			var sub=s.substring(1,17)
			if (!warnCharsInBag(sub, "0123456789")){
				alert("身份证号码长为18位时,前17位必须全部是数字");
				return false;
			}
			if(!warnCharsInBag(s, "0123456789")){							    
				if ((s.charAt(17)!="x") && (s.charAt(17)!="X")){
					alert("身份证号码只能在最后一位可以输入字符X或x");
				    return false;
				}
			}
		}
	}
	return true;
  }
// 判断字符串的字符是否在给定的字符串范围内
function warnCharsInBag(s,Bag)
{
   var SubString,ContainString;
   var NotContain=false; //判断该字符或串是否包含在内部
   var IsEqual=false; //判断某个字符是否与包中字符相等
   
   SubString=new String("");
   ContainString=new String("");
   SubString=s;
   ContainString=Bag;
   //处理单字符
   if (SubString.length=1)
   {
      SubString=SubString+"a";
      ContainString=ContainString+"a";
   }
   for(var SubIndex=0; SubIndex<SubString.length-1; SubIndex++)
   {
      IsEqual=false;//假设该子字符不在包中
      for (var ContainIndex=0; 
           ContainIndex<ContainString.length-1; 
           ContainIndex++)
      {
           if(SubString.charAt(SubIndex)==ContainString.charAt(ContainIndex))
           {
               IsEqual=true;
               break;
           }
       }   //如果该字符在包中，跳出，并设置为相等
      if (!(IsEqual))
      {//如果不在包中，可以直接推出
          NotContain=true;
          return false;
          break;
      }
   }
   if(!NotContain)
   {
      return true;//循环中出来如果还没有返回，这证明包含在包中
   }
   else
   {
      return false;
   }
}
//是否为数字
function isNumber(s)

{   var i;

    if (isEmpty(s)) 
       return false;

    // Search through string's characters one by one
    // until we find a non-numeric character.
    // When we do, return false; if we don't, return true.

    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);

        if (!isDigit(c)) return false;
    }

    // All characters are numbers.
    return true;
}
// 判断是否为空.
function isEmpty(s)
{   
	if(s == null || s.length == 0 || trim(s) == "")
	{		
		return true;
	}
	else
	{		
		return false;
	}
}
//是否为数字符号
function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}
/*
去除字符串两边的空字符
来自default.js
测试ie下ok
*/
function trim(s)
{
	return trimRight(trimLeft(s))
}
function trimLeft(s) {
	while (s.charAt(0) ==" " ||s.charAt(0) =="" ){
		s = s.substr(1,s.length-1)
	}
	return s;
}
function trimRight(s) {
	while (s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "")	{
		s = s.substr(0,s.length-1)
	}
	return s;
}
//检查电子信箱
function isEmail(obj) 
{ 
  	var email=obj.value; 
 	// valid format "a@b.cd" 
 	invalidChars = " /;,:{}[]|*%$#!()`<>?"; 
	if (email == "") return true; 

	for (i=0; i< invalidChars.length; i++)  { 
	  	badChar = invalidChars.charAt(i) ;
	  	if (email.indexOf(badChar,0) > -1)	return false; 
    } 
	atPos = email.indexOf("@",1) 
 	// there must be one "@" symbol 
	if (atPos == -1) return false; 
	if (email.indexOf("@", atPos+1) != -1) { 
 		// and only one "@" symbol 
 		return false; 
 	} 
 	periodPos = email.indexOf(".",atPos) 
	if(periodPos == -1)     { 
 		// and at least one "." after the "@" 
  		return false; 
    } 
	if ( atPos +2 > periodPos)  {
		// and at least one character between "@" and "."
  		return false; 
 	} 
 	if ( periodPos +3 > email.length) { 
  		return false; 
 	} 
	return true; 
} 

//校验手机号码：必须以数字开头，除数字外，可含有“-” 
function isMobile(s) 
{ 
	var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/; 
	if (!patrn.exec(s)) return false ;
	return true ;
} 

/*
检查输入字符串是否是英文字母
*/
function isLetter( inputVal )
{
	//inputVal = inputVal.toUpperCase();
	var strCheck = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	var nLength = inputVal.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = inputVal.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			return false;
		}
	}
	return true;
}
//判断字符串中是否包括中文或其他不合法的字符 
function ChineseIn(inputVal){
	
	var strCheck = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@_.-";

	var nLength = inputVal.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = inputVal.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			return false;
		}
	}
	return true;
}
//判断字符串是否全是0 ,全0返回true
function isZero(inputVal){
	
	var strCheck = "0";

	var nLength = inputVal.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var s = inputVal.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			return false;
		}
	}
	return true;
}
//检查s是否为正小数
//若需求为负小数,那么可以去掉"-",然后用此函数校验
function IsDecimal(s)
{
	var floatStr = "0123456789."; //可以组成小数的字符
	var sLength = s.length;		//待校验字符串的长度
	
	if(s.charAt(0) == "."){   //小数第一位不能是"."
		return false;
	}
	
	//小数不能有两个小数点
	var dot = 0;
	for(var i=0;i<sLength;i++){
		if(s.charAt(i) == "."){
			dot++;
		}
	}
	if(dot > 1)return false;
	
	//小数的最后一位不能是小数点
	if(s.charAt(sLength-1) == ".")return false;
	
	//小数不能第一第二位同时为0
	if(sLength > 1 && s.charAt(0) == "0" && s.charAt(1) == "0")return false;
	
	//若第二位不是小数点的话,第一位不能是0
	if(sLength > 1 && s.charAt(0) == "0" && s.charAt(1) != ".")return false;
		
	//小数的必须由合法的字符组成
	for(var i=0;i<sLength;i++){
		var flag = 0;
		for(var j=0;j<11;j++){
			if(s.charAt(i) == floatStr.charAt(j)){
				flag = 1;
			}
		}
		if(flag == 0){
			return false;
		}
	}
			
	return true;
	
}

/**
得到字符串的长度，中文字符长度为2
*/
function getStrLength( str ) {
    num = str.length;
    var arr = str.match(/[^\x00-\x80]/ig);
    if( arr != null )
        num += arr.length;
    return num;
}

/**
*处理字符串，返回字符串长度。
*作用：控制带汉字的字符串的长度，汉字块的两端需要附加一个字节
*str为输入的字符串
*如：“123你好456”长度为10，而在此函数处理中，返回长度为12，因为在“你好”两端需附加一个字节
*/
function getChineseStrLength(str ) {
    num = str.length;
    var arr = str.match(/[^\x00-\x80]/ig);
    if( arr != null )
        num += arr.length;

	var flag=0;
	var ChineseBlock=0;
	for(var i=0;i<num;i++){
		var c = str.charAt(i);
		if(c.match(/[^\x00-\x80]/ig) && flag==0){
			flag=1;
			ChineseBlock++;
		}
		if(!c.match(/[^\x00-\x80]/ig) && flag==1){
			flag=0;
		}
		else{
			;
		}
	}
	return num+2*ChineseBlock;
}

//国籍分赋值
function attachCardNationality(selObj){ //v3.0
	var selectedIndex = getSelectedButton(document.forms[0].select);
  	document.forms[0].nationality.value=selObj.options[selObj.selectedIndex].value;
  
}

var country_code;
country_code="<option value=\"\">--请选择国籍--</option><option value=\"CHN\">CHN-中国</option> <option value=\"HKG\">HKG-香港</option> <option value=\"MAC\">MAC-澳门</option> <option value=\"TWN\">TWN-中国台湾</option> <option value=\"USA\">USA-美国</option> <option value=\"GBR\">GBR-英国</option> <option value=\"FRA\">FRA-法国</option> <option value=\"DEU\">DEU-德国</option> <option value=\"ITA\">ITA-意大利</option> <option value=\"GRC\">GRC-希腊</option> <option value=\"RUS\">RUS-俄罗斯联邦</option> <option value=\"ESP\">ESP-西班牙</option> <option value=\"PRT\">PRT-葡萄牙</option> <option value=\"SWE\">SWE-瑞典</option> <option value=\"CHE\">CHE-瑞士</option> <option value=\"PRK\">PRK-朝鲜</option> <option value=\"KOR\">KOR-韩国</option> <option value=\"IND\">IND-印度</option> <option value=\"AUS\">AUS-澳大利亚</option> <option value=\"ISR\">ISR-以色列</option> <option value=\"JPN\">JPN-日本</option> <option value=\"BRA\">BRA-巴西</option> <option value=\"AFG\">AFG-阿富汗</option> <option value=\"ALB\">ALB-阿尔巴尼亚</option> <option value=\"ADZ\">ADZ-阿尔及利亚</option> <option value=\"ASM\">ASM-美属萨摩亚群岛</option> <option value=\"AND\">AND-安道尔</option> <option value=\"AGO\">AGO-安哥拉</option> <option value=\"AIA\">AIA-安圭拉</option> <option value=\"ATG\">ATG-安提瓜及巴布达</option> <option value=\"ARG\">ARG-阿根廷</option> <option value=\"ARM\">ARM-亚美尼亚</option> <option value=\"ABW\">ABW-阿卢巴</option> <option value=\"AUT\">AUT-奥地利</option> <option value=\"AZE\">AZE-阿塞拜疆</option> <option value=\"BHS\">BHS-巴哈马</option> <option value=\"BHR\">BHR-巴林</option> <option value=\"BGD\">BGD-孟加拉</option> <option value=\"BRB\">BRB-巴巴多斯</option> <option value=\"BLR\">BLR-白俄罗斯</option> <option value=\"BEL\">BEL-比利时</option> <option value=\"BLZ\">BLZ-伯利兹</option> <option value=\"BEN\">BEN-贝宁湾</option> <option value=\"BMU\">BMU-百慕达群岛</option> <option value=\"BTN\">BTN-不丹</option> <option value=\"BOL\">BOL-玻利维亚</option> <option value=\"BIH\">BIH-波士尼亚赫塞哥维纳联邦</option> <option value=\"BWA\">BWA-博茨瓦纳</option> <option value=\"BRN\">BRN-汶莱</option> <option value=\"BGR\">BGR-保加利亚</option> <option value=\"BFA\">BFA-布基纳法索</option> <option value=\"BDI\">BDI-布隆迪</option> <option value=\"KHM\">KHM-高棉</option> <option value=\"CMR\">CMR-喀麦隆联合的共和国</option> <option value=\"CAN\">CAN-加拿大</option> <option value=\"TCD\">TCD-乍得</option> <option value=\"CHL\">CHL-智利</option> <option value=\"COL\">COL-哥伦比亚</option> <option value=\"COM\">COM-科摩洛</option> <option value=\"COG\">COG-刚果</option> <option value=\"COK\">COK-库克群岛</option> <option value=\"CRI\">CRI-哥斯达黎加</option> <option value=\"HRV\">HRV-克罗埃西亚</option> <option value=\"CUB\">CUB-古巴</option> <option value=\"CYP\">CYP-赛普勒斯</option> <option value=\"CZE\">CZE-捷克</option> <option value=\"DNK\">DNK-丹麦</option> <option value=\"DJI\">DJI-吉布提</option> <option value=\"DMA\">DMA-多米尼加联邦</option> <option value=\"DOM\">DOM-多米尼加共和国</option> <option value=\"TMP\">TMP-东帝汶</option> <option value=\"ECU\">ECU-厄瓜多尔</option> <option value=\"EGY\">EGY-埃及</option> <option value=\"SLV\">SLV-萨尔瓦多</option> <option value=\"GNQ\">GNQ-赤道几内亚</option> <option value=\"ERI\">ERI-依利垂亚</option> <option value=\"EST\">EST-爱沙尼亚</option> <option value=\"ETH\">ETH-埃塞俄比亚</option> <option value=\"FJI\">FJI-斐济</option> <option value=\"FIN\">FIN-芬兰</option> <option value=\"GAB\">GAB-加彭</option> <option value=\"GMB\">GMB-冈比亚</option> <option value=\"GEO\">GEO-乔治亚州</option> <option value=\"GHA\">GHA-迦纳</option> <option value=\"GIB\">GIB-直布罗陀</option> <option value=\"GRL\">GRL-格陵兰</option> <option value=\"GRD\">GRD-格林纳达</option> <option value=\"GLP\">GLP-哥德普洛岛</option> <option value=\"GUM\">GUM-关岛</option> <option value=\"GTM\">GTM-危地马拉</option> <option value=\"GIN\">GIN-几内亚</option> <option value=\"GNB\">GNB-几内亚比绍共和国</option> <option value=\"GUY\">GUY-圭亚那</option> <option value=\"HTI\">HTI-海地</option> <option value=\"HMD\">HMD-HEARD AND MCDONALD IS</option> <option value=\"HND\">HND-洪都拉斯</option> <option value=\"HUN\">HUN-匈牙利</option> <option value=\"ISL\">ISL-冰岛</option> <option value=\"IDN\">IDN-印尼</option> <option value=\"IRN\">IRN-伊朗王国</option> <option value=\"IRQ\">IRQ-伊拉克</option> <option value=\"IRL\">IRL-爱尔兰</option> <option value=\"CIV\">CIV-象牙海岸</option> <option value=\"JAM\">JAM-牙买加</option> <option value=\"JOR\">JOR-约旦</option> <option value=\"KAZ\">KAZ-哈萨克</option> <option value=\"KEN\">KEN-肯尼亚</option> <option value=\"KIR\">KIR-吉尔巴斯</option> <option value=\"KWT\">KWT-科威特</option> <option value=\"KGZ\">KGZ-吉尔吉斯</option> <option value=\"LAO\">LAO-老挝</option> <option value=\"LVA\">LVA-拉脱维亚</option> <option value=\"LBN\">LBN-黎巴嫩</option> <option value=\"LSO\">LSO-莱索托</option> <option value=\"LBR\">LBR-利比里亚</option> <option value=\"LBY\">LBY-利比亚</option> <option value=\"LIE\">LIE-列支敦士登</option> <option value=\"LTU\">LTU-立陶宛</option> <option value=\"LUX\">LUX-卢森堡</option> <option value=\"MKD\">MKD-马其顿</option> <option value=\"MDG\">MDG-马达加斯加</option> <option value=\"MWI\">MWI-马拉维</option> <option value=\"MYS\">MYS-马来西亚</option> <option value=\"MDV\">MDV-马尔代夫</option> <option value=\"MLI\">MLI-马里</option> <option value=\"MLT\">MLT-马尔他</option> <option value=\"MHL\">MHL-马歇尔岛</option> <option value=\"MTQ\">MTQ-马提尼克岛</option> <option value=\"MRT\">MRT-毛里塔尼亚</option> <option value=\"MUS\">MUS-毛里求斯</option> <option value=\"MYT\">MYT-马约特</option> <option value=\"MEX\">MEX-墨西哥</option> <option value=\"FSM\">FSM-密克罗尼西亚</option> <option value=\"MDA\">MDA-摩尔多瓦共和国</option> <option value=\"MCO\">MCO-摩纳哥</option> <option value=\"MNG\">MNG-蒙古人</option> <option value=\"MSR\">MSR-蒙特色纳</option> <option value=\"MAR\">MAR-摩洛哥</option> <option value=\"MOZ\">MOZ-莫桑比克</option> <option value=\"MMR\">MMR-缅甸</option> <option value=\"NAM\">NAM-那米比亚</option> <option value=\"NRU\">NRU-瑙鲁</option> <option value=\"NPL\">NPL-尼泊尔</option> <option value=\"NLD\">NLD-荷兰</option> <option value=\"ANT\">ANT-荷兰安的列斯群岛</option> <option value=\"NCL\">NCL-新加勒多尼亚</option> <option value=\"NZL\">NZL-新西兰</option> <option value=\"NIC\">NIC-尼加拉瓜</option> <option value=\"NER\">NER-尼日尔</option> <option value=\"NGA\">NGA-尼日利亚</option> <option value=\"NIU\">NIU-纽鄂岛</option> <option value=\"NFK\">NFK-诺福克岛</option> <option value=\"MNP\">MNP-北方马里亚纳群岛</option> <option value=\"NOR\">NOR-挪威</option> <option value=\"OMN\">OMN-阿曼</option> <option value=\"PAK\">PAK-巴基斯坦</option> <option value=\"PLW\">PLW-帛琉</option> <option value=\"PAN\">PAN-巴拿马</option> <option value=\"PNG\">PNG-巴布亚新几内亚</option> <option value=\"PRY\">PRY-巴拉圭</option> <option value=\"PER\">PER-秘鲁</option> <option value=\"PHL\">PHL-菲律宾共和国</option> <option value=\"PCN\">PCN-皮特凯恩岛</option> <option value=\"POL\">POL-波兰</option> <option value=\"PRI\">PRI-波多黎各</option> <option value=\"QAT\">QAT-卡塔尔</option> <option value=\"REU\">REU-团圆</option> <option value=\"ROM\">ROM-罗马尼亚</option> <option value=\"RWA\">RWA-卢旺达</option> <option value=\"WSM\">WSM-美属萨摩亚</option> <option value=\"SMR\">SMR-圣马力诺</option> <option value=\"STP\">STP-圣多美和普林西比</option> <option value=\"SAU\">SAU-沙特阿拉伯</option> <option value=\"SEN\">SEN-塞内加尔</option> <option value=\"SYC\">SYC-塞锡尔群岛</option> <option value=\"SLE\">SLE-塞拉利昂</option> <option value=\"SGP\">SGP-新加坡</option> <option value=\"SVK\">SVK-斯洛伐克</option> <option value=\"SVN\">SVN-斯洛文尼亚</option> <option value=\"SLB\">SLB-所罗门岛</option> <option value=\"SOM\">SOM-索马里</option> <option value=\"ZAF\">ZAF-南非</option> <option value=\"SGS\">SGS-格鲁吉亚</option> <option value=\"LKA\">LKA-斯里兰卡</option> <option value=\"SDN\">SDN-苏丹</option> <option value=\"SDA\">SDA-苏丹航空公司</option> <option value=\"SUR\">SUR-苏里南</option> <option value=\"SJM\">SJM-冷岸及央麦恩群岛</option> <option value=\"SWZ\">SWZ-斯威士兰</option> <option value=\"SYR\">SYR-叙利亚</option> <option value=\"TJK\">TJK-塔吉克</option> <option value=\"TZA\">TZA-坦桑尼亚联合的共和国</option> <option value=\"THA\">THA-泰国</option> <option value=\"TGO\">TGO-多哥</option> <option value=\"TKL\">TKL-托克劳</option> <option value=\"TON\">TON-汤加</option> <option value=\"TTO\">TTO-千里达托贝哥共和国</option> <option value=\"TUN\">TUN-北非的共和国</option> <option value=\"TUR\">TUR-土耳其</option> <option value=\"TKM\">TKM-土库曼</option> <option value=\"TUV\">TUV-图瓦卢</option> <option value=\"UGA\">UGA-乌干达</option> <option value=\"UKR\">UKR-乌克兰</option> <option value=\"ARE\">ARE-阿拉伯联合酋长国</option> <option value=\"URY\">URY-乌拉圭</option> <option value=\"UZB\">UZB-乌兹别克斯坦</option> <option value=\"VUT\">VUT-万那度</option> <option value=\"VAT\">VAT-罗马教庭</option> <option value=\"VEN\">VEN-委内瑞拉</option> <option value=\"VNM\">VNM-越南</option> <option value=\"ESH\">ESH-西撒哈拉</option> <option value=\"YEM\">YEM-也门</option> <option value=\"YUG\">YUG-南斯拉夫</option> <option value=\"ZAR\">ZAR-扎伊尔</option> <option value=\"ZMB\">ZMB-赞比亚</option> <option value=\"ZWE\">ZWE-津巴布韦</option>";




