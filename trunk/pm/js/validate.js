/*
主要用于页面客户端输入内容的合法性检查
为了使本脚本在IE和NS下无误运行，表单元素的调用使用document.forms[0].test1方式
而不用form.test1方式
*/


/*
检查输入的内容是否是由数字和字母组成的字符串
*/	

function isString(s){
	var l = s.length;
	var i;
	for(i = 0;i < l; i++){
		if(s.charCodeAt(i) != 32){
		if(s.charCodeAt(i)<48 || ( s.charCodeAt(i)>57 && s.charCodeAt(i)<65 )  || ( s.charCodeAt(i)>90 && s.charCodeAt(i)<97 )  ||  s.charCodeAt(i)>122 ){	
			
			return false;
		}
	}
	}
	return true;
	
}
 
//检察是否是输入数字
function checknum() {
	if (event.keyCode < 48 || event.keyCode > 57) {
		event.returnValue = false;
	}
	
}
//检察是否是输入十进制整数
function checkdec( varthis ) {
    if ( event.keyCode == 46 && varthis.value.indexOf( '.' ) == -1 )
        event.returnValue = true;
    else {
        if ( event.keyCode >= 48 && event.keyCode <= 57 ) {
            event.returnValue = true;
        } else
            event.returnValue = false;
    }
}

var whitespace = " \t\n\r";
var defaultEmptyOK=false;
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

//是否为数字符号
function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}
//是否为数字
function isInteger(s)

{   var i;

    if (isEmpty(s)) 
       if (isInteger.arguments.length == 1) return false;
       else return (isInteger.arguments[1] == true);


    for (i = 0; i < s.length; i++)
    {   

        var c = s.charAt(i);

        if (!isDigit(c)) return false;
    }

    // All characters are numbers.
    return true;
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

//是否为字母
function isLetter(s)

{   
  	var i;
	var strCheck = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	var nLength = s.length;
	for ( i = 0 ; i < nLength ; i ++ )
	{
		var va = s.charAt( i );
		var nPos = strCheck.indexOf( va );
		if ( nPos < 0 )
		{
			return false;
		}
	}
	return true;
}

//是否为带符号数字
function isSignedInteger (s)

{   if (isEmpty(s)) 
       if (isSignedInteger.arguments.length == 1) return defaultEmptyOK;
       else return (isSignedInteger.arguments[1] == true);

    else {
        var startPos = 0;
        var secondArg = defaultEmptyOK;

        if (isSignedInteger.arguments.length > 1)
            secondArg = isSignedInteger.arguments[1];

        // skip leading + or -
        if ( (s.charAt(0) == "-") || (s.charAt(0) == "+") )
           startPos = 1;    
        return (isInteger(s.substring(startPos, s.length), secondArg))
    }
}

//是否为正整数
function isPositiveInteger(s)
{   var secondArg = defaultEmptyOK;

    if (isPositiveInteger.arguments.length > 1)
        secondArg = isPositiveInteger.arguments[1];

    // The next line is a bit byzantine.  What it means is:
    // a) s must be a signed integer, AND
    // b) one of the following must be true:
    //    i)  s is empty and we are supposed to return true for
    //        empty strings
    //    ii) this is a positive, not negative, number

    return (isSignedInteger(s, secondArg)
         && ( (isEmpty(s) && secondArg)  || (parseInt (s) > 0) ) );
}

//是否为非负整数
function isNonnegativeInteger(s)
{   var secondArg = defaultEmptyOK;

    if (isNonnegativeInteger.arguments.length > 1)
        secondArg = isNonnegativeInteger.arguments[1];

    // The next line is a bit byzantine.  What it means is:
    // a) s must be a signed integer, AND
    // b) one of the following must be true:
    //    i)  s is empty and we are supposed to return true for
    //        empty strings
    //    ii) this is a number >= 0

    return (isSignedInteger(s, secondArg)
         && ( (isEmpty(s) && secondArg)  || (parseInt (s) >= 0) ) );
}

//是否为小数
function isFloat (s){  
	//if(isNaN(parseFloat(s)))
		//return false;
	//else return true; 
	
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
 

/*
********************基本格式*******************************
类型：（必填）
      string       字符型
      char_num     字符数字型
      zip          邮政编码型
	  money        货币型
	  0-9          0-9型
	  int          整型     可以为负数
	  float        浮点型   可以为负数
	  email        电子邮件型
		url 					网址型
	  date         日期型   （属性由month，day，输入表单元素的名称name）
	  pwd          密码型   (属性有pwd2，输入校验密码的域名）
	  phone        电话型
          phones	多个电话号码型
	  idcard       身份证型
××××××××××××基本函数列表×××××××××××××××××××
function boolean isMadeOf(value,str)   //判断value中的字符是否由str中的字符组成
function boolean isSizeOf(value,max,min)//判断value的大小是否由符合标准
function boolean isLengthOf(value,max,min)//判断value的长度是否由符合标准
function boolean isNotNull(value)//判断value是否空或null
function trimSubStr(ATrimStr,ASubStr,AWhere)//去掉符合条件的字符，用于去空格


××××××××××××扩充函数列表×××××××××××××××××××
function void packUp(obj)//整理域的属性值，校验v_×域的值是否符合标准

××××××××××××××××××接口函数列表××××××××××××××××××××
function void forString(obj)//判断一般字符串的长度
function void forZip(obj)//判断邮政编码
function void forMoney(obj)//判断金额
function void for0-9(obj)//判断字符是否由0－9个数字组成
function void forInt(obj)//判断字符是否是整数组成，可以为负数
function void forFloat(obj)//判断字符是否是数字，可以有小数点，负数
function void forEmail(obj)//判断字符是合法的电子邮件
function void forDate(obj1,obj2,obj2)//判断字符是合法的日期
function void forPwd(obj1,obj2)//判断字符密码，两次输入密码是否相等
function void forPhone(obj)//判断字符是否是合法的电话021-55555555,(021)2222222,
function void forPhones(obj)//判断字符是否是合法的多个电话021-55555555;(021)2222222,
function void forMadeOf(obj,str)//判断字符是否是由str里的字符组成
function void forIdCard(obj1)//判断字符是否是合法的身份证号
*/
//*****************全局变量****************************
var numStr="0123456789";
var moneyStr = numStr + ".";//金额的组成,浮点数的组成
var intStr = numStr+"-";
var floatStr = moneyStr+"-";
var phoneStr = "()-#" + numStr;//电话号码
var phonesStr = ";；()-#" + numStr;//多个电话号码
var flag = 0;

//*********************基本函数***************************
function isMadeOf(val,str)
{

	var jj;
	var chr;
	for (jj=0;jj<val.length;++jj){
		chr=val.charAt(jj);
		if (str.indexOf(chr,0)==-1)
			return false;
	}

	return true;
}


function isNotNull(val)
{
	if (val =="")
		return (false);
	if (val == null)
		return (false);

		return (true);
}

function isSizeOf(val,min,max)
{
    var maxval = parseFloat(max);
	var minval = parseFloat(min);
	var selval = parseFloat(val);

	if (isNaN(selval)){
	  return false;
	}

	if (!isNaN(maxval)){
	  if (selval > maxval){
	    return false;
	  }
	}
	if (!isNaN(minval)){
	  if (selval < minval){
	    return false;
	  }
	}

    /*
	if (val < min || val > max){
	  return false;
	}
	*/

	return (true);
}


function isLengthOf(val,min,max)
{

	var minlen = parseInt(min);
	var maxlen = parseInt(max);

	if (!isNaN(maxlen)){
	  if (val.length > maxlen){
	    return false;
	  }
	}
	if (!isNaN(minlen)){
	  if (val.length < minlen){
	    return false;
	  }
	}

	/*
	if (val.length < minlen || val.lengh > maxlen ){
	  return false;
	}
	*/
	return true;
}

//判断小数点
function isValid_dot(val)
{

    var subvalue;

    if (val.indexOf(".",0) != -1){

	   subvalue = val.substring(val.indexOf(".",0)+1);

	  if (subvalue.indexOf(".",0) != -1){
	    return false;
	  }

	}

	return true;

}

//判断负号
function isValid_negative(val)
{

    var subvalue;

    if (val.indexOf("-",0) != -1){

	   if (val.indexOf("-",0) > 0){
	     return false;
	   }

	   subvalue = val.substring(val.indexOf("-",0)+1);

	  if (subvalue.indexOf("-",0) != -1){
	    return false;
	  }

	}

	return true;


}

//判断合适长度
function isRight_length(val,num)
{
     var len = parseInt(num);

	 if (isNaN(len)){ return true;}

	 if (val.length != len){
	   return false;
	 }

     return true;

}

//***************************************************************************************************
//--- 从ATrimStr中去掉符合条件的字符串ASubStr
//--- 参数：
//---    ATrimStr：要去掉符合条件字符串的字符串
//---    ASubStr：要去掉的子字符串
//---    AWhere 0：去掉所有的, 1:去掉左边相连的,2:去掉右面的相连的
//--- 返回: 处理完的字符串
//---	创建日期:       2001-07-27		最近更新日期:   2001-07-27
//--- 	创建程序员:	xg_delayth		最近更新程序员: xg_delayth
//--- 	维护程序员:     xg_delayth
//--- 举例： trimSubStr(" usj  29 "," ",0) 返回 "usj29"
  function trimSubStr(ATrimStr,ASubStr,AWhere){
    var tTrLength,tSbLength,tempLength;
    var tempString;
    var i;

    tTrLength = ATrimStr.length;
    tSbLength = ASubStr.length;

    if (tSbLength == 0){return ATrimStr;}
    if (tSbLength > tTrLength){return ATrimStr;}

    tempString = ATrimStr;
    switch(AWhere){
      case 0://所有
        do{
          tempLength = tempString.length;
          tempString = tempString.replace(ASubStr,"");
        } while(tempLength != tempString.length);
        break;
      case 1://左
        while (true){
          if (tempString.length < tSbLength) break;
          for (i = 0;i < tSbLength;i++)
            if (ASubStr.charAt(i) != tempString.charAt(i))
              return tempString;
          tempString = tempString.replace(ASubStr,"");
        };
      case 2://右
        while(true){
          tempLength = tempString.length;
          if (tempLength < tSbLength){return tempString;}
          for (i = 0;i < tSbLength;i ++){
            if (ASubStr.charAt(i) != tempString.charAt(tempLength - tSbLength+i)){
              return tempString;
            }
          }
          tempString = tempString.substr(0,tempLength-tSbLength);
        };
      default:
        return tempString;
    }
    return tempString;
  }
//***************************************************************************************************


//××××××××××××扩充函数列表×××××××××××××××××××
//整理域的属性值，校验v_×域的值是否符合标准
//*********************扩充函数***************************
function forDate(date)
{	
	var myyear = date.substring(0,4);
	var mymonth = date.substring(4,6);
	var myday = date.substring(6,8);
	//myyear=parseInt(useryear);
	//mymonth=parseInt(usermonth);
	//myday=parseInt(userday);

	if (myyear < 1900 || myyear > 2100 ||mymonth < 1 ||mymonth > 12 || myday < 1 || myday > 31)
		return (false);
        if(mymonth==4 || mymonth==6 || mymonth==9 || mymonth==11)
        {
        	if(myday>30)
            		return (false);
        }
	if(myyear%4==0)
	{
	 	if((myyear%100==0 && myyear%400==0) || myyear%100!=0)
          	{
            		if(mymonth==2 && myday>29)
             			return (false);
            		else
	     			return (true);
          	}
        }
	else
	{
          	if(mymonth==2 && myday>28)
             		return (false);
             	else
             		return (true);
	}
}
 
function forZip(obj)//判断邮政编码
{
    if (!forString(obj)){
	  flag = 1;
	  obj.focus();
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}

    if (!isMadeOf(obj.value,numStr)){
        flag = 1;
        alert("'" + obj.title + "'的值不正确！请输入数字！");
	    obj.focus();
	    return false;
      }

    if (!isRight_length(obj.value,"6")){
      flag = 1;
      alert("'"+obj.title+"'的值不正确！长度有错误！(6位数字)"); 
	  obj.focus();
	  return false;
    }

    return true;
}

function forMoney(obj)//判断金额
{
    if (!forString(obj)){	  
	  obj.focus();
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}

    if (!isMadeOf(obj.value,moneyStr)){      
	  obj.focus();
	  return false;
    }

	if (!isValid_dot(obj.value)){	    
	    obj.focus();
	    return false;
	}

	return true;

}
 


function forInt(obj)//判断字符是否是整数组成，可以为负数
{
    if (!forString(obj)){
	  flag = 1;
	  obj.focus();
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}

    if (!isMadeOf(obj.value,intStr)){
      flag = 1;
      alert("'" + obj.title + "'的值不正确！请输入数字！");
	  obj.focus();
	  return false;
    }

	if (!isValid_negative(obj.value)){
      flag = 1;
      alert("'" + obj.title + "'的值不正确！'-'符号有错误！");
	  obj.focus();
	  return false;
    }

	if (!isSizeOf(obj.value,obj.v_minvalue,obj.v_maxvalue)){
	  flag = 1;
	  alert("'" + obj.title + "'的值不正确！超出规定范围！");
	  obj.focus();
	  return false;
	}

   return true;

}


function forFloat(obj)//判断字符是否是数字，可以有小数点，负数
{
    if (!forString(obj)){
	  flag = 1;
	  obj.focus();
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}

    if (!isMadeOf(obj.value,floatStr)){
      flag = 1;
      alert("'" + obj.title + "'的值不正确！请输入数字！");
	  obj.focus();
	  return false;
    }

	if (!isValid_dot(obj.value)){
	    flag = 1;
	    alert("'" + obj.title + "'的值不正确！小数点有错误！");
	    obj.focus();
	    return false;
	}

	if (!isValid_negative(obj.value)){
      flag = 1;
      alert("'" + obj.title + "'的值不正确！'-'符号有错误！");
	  obj.focus();
	  return false;
    }

	if (!isSizeOf(obj.value,obj.v_minvalue,obj.v_maxvalue)){
	  flag = 1;
	  alert("'" + obj.title + "'的值不正确！超出规定范围！");
	  obj.focus();
	  return false;
	}

   return true;

}

function forPhone(obj)//判断字符是否是合法的电话021-55555555,(021)2222222,
{
    if (!forString(obj)){
	  flag = 1;
	  obj.focus();
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}

	if (!isMadeOf(obj.value,phoneStr)){
	  flag = 1;
	  alert("'" + obj.title + "'的值不正确！请填写数字,可以包含(,),－,#符号");
	  obj.focus();
	  return false;
	}

}

function forPhones(obj)//判断字符是否是合法的电话021-55555555;(021)2222222,
{
    if (!forString(obj)){
	  flag = 1;
	  obj.focus();
	  return false;
	}else{
	  if (obj.value.length == 0){
	    return true;
	  }
	}

	if (!isMadeOf(obj.value,phonesStr)){
	  flag = 1;
	  alert("'" + obj.title + "'的值不正确！请填写数字,可以包含;；(,),－,#符号");
	  obj.focus();
	  return false;
	}

}

function forEmail(obj)//判断字符是合法的电子邮件
{
 
    var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
    if(myReg.test(obj.value)){
	  return true;
	}
 
    return false;

}
function checkdateNumber(obj){   
  var str;
      str=obj.value;
  if(str.length!=8){
	    return false;
  }
  return true
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
 




