/*
��Ҫ����ҳ��ͻ����������ݵĺϷ��Լ��
Ϊ��ʹ���ű���IE��NS���������У���Ԫ�صĵ���ʹ��document.forms[0].test1��ʽ
������form.test1��ʽ
*/


/*
�������������Ƿ��������ֺ���ĸ��ɵ��ַ���
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
 
//����Ƿ�����������
function checknum() {
	if (event.keyCode < 48 || event.keyCode > 57) {
		event.returnValue = false;
	}
	
}
//����Ƿ�������ʮ��������
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
// �ж��Ƿ�Ϊ��.
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



// �ж��ַ������ַ��Ƿ��ڸ������ַ�����Χ��
function warnCharsInBag(s,Bag)
{
   var SubString,ContainString;
   var NotContain=false; //�жϸ��ַ����Ƿ�������ڲ�
   var IsEqual=false; //�ж�ĳ���ַ��Ƿ�������ַ����
   
   SubString=new String("");
   ContainString=new String("");
   SubString=s;
   ContainString=Bag;
   //�����ַ�
   if (SubString.length=1)
   {
      SubString=SubString+"a";
      ContainString=ContainString+"a";
   }
   for(var SubIndex=0; SubIndex<SubString.length-1; SubIndex++)
   {
      IsEqual=false;//��������ַ����ڰ���
      for (var ContainIndex=0; 
           ContainIndex<ContainString.length-1; 
           ContainIndex++)
      {
           if(SubString.charAt(SubIndex)==ContainString.charAt(ContainIndex))
           {
               IsEqual=true;
               break;
           }
       }   //������ַ��ڰ��У�������������Ϊ���
      if (!(IsEqual))
      {//������ڰ��У�����ֱ���Ƴ�
          NotContain=true;
          return false;
          break;
      }
   }
   if(!NotContain)
   {
      return true;//ѭ���г��������û�з��أ���֤�������ڰ���
   }
   else
   {
      return false;
   }
}

//�Ƿ�Ϊ���ַ���
function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}
//�Ƿ�Ϊ����
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

//�Ƿ�Ϊ����
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

//�Ƿ�Ϊ��ĸ
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

//�Ƿ�Ϊ����������
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

//�Ƿ�Ϊ������
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

//�Ƿ�Ϊ�Ǹ�����
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

//�Ƿ�ΪС��
function isFloat (s){  
	//if(isNaN(parseFloat(s)))
		//return false;
	//else return true; 
	
	var floatStr = "0123456789."; //�������С�����ַ�
	var sLength = s.length;		//��У���ַ����ĳ���
	
	if(s.charAt(0) == "."){   //С����һλ������"."
		return false;
	}
	
	//С������������С����
	var dot = 0;
	for(var i=0;i<sLength;i++){
		if(s.charAt(i) == "."){
			dot++;
		}
	}
	if(dot > 1)return false;
	
	//С�������һλ������С����
	if(s.charAt(sLength-1) == ".")return false;
	
	//С�����ܵ�һ�ڶ�λͬʱΪ0
	if(sLength > 1 && s.charAt(0) == "0" && s.charAt(1) == "0")return false;
	
	//���ڶ�λ����С����Ļ�,��һλ������0
	if(sLength > 1 && s.charAt(0) == "0" && s.charAt(1) != ".")return false;
		
	//С���ı����ɺϷ����ַ����
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
********************������ʽ*******************************
���ͣ������
      string       �ַ���
      char_num     �ַ�������
      zip          ����������
	  money        ������
	  0-9          0-9��
	  int          ����     ����Ϊ����
	  float        ������   ����Ϊ����
	  email        �����ʼ���
		url 					��ַ��
	  date         ������   ��������month��day�������Ԫ�ص�����name��
	  pwd          ������   (������pwd2������У�������������
	  phone        �绰��
          phones	����绰������
	  idcard       ���֤��
���������������������������������б��������������������������������������
function boolean isMadeOf(value,str)   //�ж�value�е��ַ��Ƿ���str�е��ַ����
function boolean isSizeOf(value,max,min)//�ж�value�Ĵ�С�Ƿ��ɷ��ϱ�׼
function boolean isLengthOf(value,max,min)//�ж�value�ĳ����Ƿ��ɷ��ϱ�׼
function boolean isNotNull(value)//�ж�value�Ƿ�ջ�null
function trimSubStr(ATrimStr,ASubStr,AWhere)//ȥ�������������ַ�������ȥ�ո�


���������������������������亯���б��������������������������������������
function void packUp(obj)//�����������ֵ��У��v_�����ֵ�Ƿ���ϱ�׼

�������������������������������������ӿں����б����������������������������������������
function void forString(obj)//�ж�һ���ַ����ĳ���
function void forZip(obj)//�ж���������
function void forMoney(obj)//�жϽ��
function void for0-9(obj)//�ж��ַ��Ƿ���0��9���������
function void forInt(obj)//�ж��ַ��Ƿ���������ɣ�����Ϊ����
function void forFloat(obj)//�ж��ַ��Ƿ������֣�������С���㣬����
function void forEmail(obj)//�ж��ַ��ǺϷ��ĵ����ʼ�
function void forDate(obj1,obj2,obj2)//�ж��ַ��ǺϷ�������
function void forPwd(obj1,obj2)//�ж��ַ����룬�������������Ƿ����
function void forPhone(obj)//�ж��ַ��Ƿ��ǺϷ��ĵ绰021-55555555,(021)2222222,
function void forPhones(obj)//�ж��ַ��Ƿ��ǺϷ��Ķ���绰021-55555555;(021)2222222,
function void forMadeOf(obj,str)//�ж��ַ��Ƿ�����str����ַ����
function void forIdCard(obj1)//�ж��ַ��Ƿ��ǺϷ������֤��
*/
//*****************ȫ�ֱ���****************************
var numStr="0123456789";
var moneyStr = numStr + ".";//�������,�����������
var intStr = numStr+"-";
var floatStr = moneyStr+"-";
var phoneStr = "()-#" + numStr;//�绰����
var phonesStr = ";��()-#" + numStr;//����绰����
var flag = 0;

//*********************��������***************************
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

//�ж�С����
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

//�жϸ���
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

//�жϺ��ʳ���
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
//--- ��ATrimStr��ȥ�������������ַ���ASubStr
//--- ������
//---    ATrimStr��Ҫȥ�����������ַ������ַ���
//---    ASubStr��Ҫȥ�������ַ���
//---    AWhere 0��ȥ�����е�, 1:ȥ�����������,2:ȥ�������������
//--- ����: ��������ַ���
//---	��������:       2001-07-27		�����������:   2001-07-27
//--- 	��������Ա:	xg_delayth		������³���Ա: xg_delayth
//--- 	ά������Ա:     xg_delayth
//--- ������ trimSubStr(" usj  29 "," ",0) ���� "usj29"
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
      case 0://����
        do{
          tempLength = tempString.length;
          tempString = tempString.replace(ASubStr,"");
        } while(tempLength != tempString.length);
        break;
      case 1://��
        while (true){
          if (tempString.length < tSbLength) break;
          for (i = 0;i < tSbLength;i++)
            if (ASubStr.charAt(i) != tempString.charAt(i))
              return tempString;
          tempString = tempString.replace(ASubStr,"");
        };
      case 2://��
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


//���������������������������亯���б��������������������������������������
//�����������ֵ��У��v_�����ֵ�Ƿ���ϱ�׼
//*********************���亯��***************************
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
 
function forZip(obj)//�ж���������
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
        alert("'" + obj.title + "'��ֵ����ȷ�����������֣�");
	    obj.focus();
	    return false;
      }

    if (!isRight_length(obj.value,"6")){
      flag = 1;
      alert("'"+obj.title+"'��ֵ����ȷ�������д���(6λ����)"); 
	  obj.focus();
	  return false;
    }

    return true;
}

function forMoney(obj)//�жϽ��
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
 


function forInt(obj)//�ж��ַ��Ƿ���������ɣ�����Ϊ����
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
      alert("'" + obj.title + "'��ֵ����ȷ�����������֣�");
	  obj.focus();
	  return false;
    }

	if (!isValid_negative(obj.value)){
      flag = 1;
      alert("'" + obj.title + "'��ֵ����ȷ��'-'�����д���");
	  obj.focus();
	  return false;
    }

	if (!isSizeOf(obj.value,obj.v_minvalue,obj.v_maxvalue)){
	  flag = 1;
	  alert("'" + obj.title + "'��ֵ����ȷ�������涨��Χ��");
	  obj.focus();
	  return false;
	}

   return true;

}


function forFloat(obj)//�ж��ַ��Ƿ������֣�������С���㣬����
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
      alert("'" + obj.title + "'��ֵ����ȷ�����������֣�");
	  obj.focus();
	  return false;
    }

	if (!isValid_dot(obj.value)){
	    flag = 1;
	    alert("'" + obj.title + "'��ֵ����ȷ��С�����д���");
	    obj.focus();
	    return false;
	}

	if (!isValid_negative(obj.value)){
      flag = 1;
      alert("'" + obj.title + "'��ֵ����ȷ��'-'�����д���");
	  obj.focus();
	  return false;
    }

	if (!isSizeOf(obj.value,obj.v_minvalue,obj.v_maxvalue)){
	  flag = 1;
	  alert("'" + obj.title + "'��ֵ����ȷ�������涨��Χ��");
	  obj.focus();
	  return false;
	}

   return true;

}

function forPhone(obj)//�ж��ַ��Ƿ��ǺϷ��ĵ绰021-55555555,(021)2222222,
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
	  alert("'" + obj.title + "'��ֵ����ȷ������д����,���԰���(,),��,#����");
	  obj.focus();
	  return false;
	}

}

function forPhones(obj)//�ж��ַ��Ƿ��ǺϷ��ĵ绰021-55555555;(021)2222222,
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
	  alert("'" + obj.title + "'��ֵ����ȷ������д����,���԰���;��(,),��,#����");
	  obj.focus();
	  return false;
	}

}

function forEmail(obj)//�ж��ַ��ǺϷ��ĵ����ʼ�
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
 
 //�������
function checkDate(obj){
	str=obj.value;
	if(str.length==0){alert("�������벻��ȷ");return false;}
	Y=str.substring(0,4);
	M=str.substring(4,6);
	D=str.substring(6,8);
	if(Y<1900 || Y>2100){
		alert("�������벻��ȷ");
		return false;
	}
	Months= new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	Leap = false;
	if((Y % 4 == 0) && ((Y % 100 != 0) || (Y %400 == 0)))	Leap = true;
	if((D < 1) || (D > 31) || (M < 1) || (M > 12) || (Y < 0))	{alert("�������벻��ȷ");return false;}
	if((D > Months[M-1]) && !((M == 2) && (D > 28)))	{alert("�������벻��ȷ");return false;}
	if(!(Leap) && (M == 2) && (D > 28))	{alert("�������벻��ȷ");return false;} 
	if((Leap) && (M == 2) && (D > 29)) 	{alert("�������벻��ȷ");return false;} 
	return true;
}
 




