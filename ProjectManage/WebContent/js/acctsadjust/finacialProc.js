/*
用于财务处理部分具体域信息的验证
*/

/*
*具体域的验证
*
*/
/*
*验证起息日期是否合法
*/
function validateInsDate(insDate){
	
			if(isEmpty(insDate))
		    {
		    	alert("起息日期不能为空！");
		    	 return false;
		    }
		    
		    var date =/^(\d{4})(\d{2})(\d{2})$/;
  			if(!date.test(insDate) && insDate!=""){
  				alert('日期格式非法!');
  				return false;
  			}
  			
			Y=insDate.substring(0,4);
			M=insDate.substring(4,6);
			D=insDate.substring(6,8);
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
		    
  			//校验起息日期是否晚于当前记账日期
            var currDate=getCurrDate();
			if(insDate > currDate)
			{
			  alert("起息日期不能晚于记账日期！");
			  return false;
			  }		
		   return true;
}

function validateInsDateStr(insDate){
	
			if(isEmpty(insDate))
		    {
		    	return "起息日期为空!";
		    }
		    var date =/^(\d{4})(\d{2})(\d{2})$/;
  			if(!date.test(insDate) && insDate!=""){
  				return "日期格式非法!";
  			}
  			
  			Y=insDate.substring(0,4);
			M=insDate.substring(4,6);
			D=insDate.substring(6,8);
			if(Y<1900 || Y>2100){
				return "日期输入不正确！";
			}
			Months= new Array(31,28,31,30,31,30,31,31,30,31,30,31);
			Leap = false;
			if((Y % 4 == 0) && ((Y % 100 != 0) || (Y %400 == 0)))	Leap = true;
			if((D < 1) || (D > 31) || (M < 1) || (M > 12) || (Y < 0))	{ return "日期输入不正确";}
			if((D > Months[M-1]) && !((M == 2) && (D > 28)))	{return "日期输入不正确";}
			if(!(Leap) && (M == 2) && (D > 28))	{return "日期输入不正确";} 
			if((Leap) && (M == 2) && (D > 29)) 	{return "日期输入不正确";} 
  			
  		//校验起息日期是否晚于当前记账日期
            var currDate=getCurrDate();
			if(insDate > currDate)
			{
			 return "起息日期晚于记账日期!" + currDate;
			  }		
		   return "";
}
 /*
 *返回yyyyMMdd格式当前系统时间
 */
function getCurrDate(){
    var today = new Date();

    var year =today.getFullYear().toString();
    var month =(today.getMonth() +1).toString();
    var day =today.getDate().toString();

    month =(month <10)?"0"+ month:month;
    day =(day <10)?"0"+ day:day;
    return year + month + day;
}

/*
*验证卡号
*/
function validateCardNo(cardNo){
		  if(isEmpty(cardNo))
		    {
				alert("卡号不能为空!");
				return false;
		    	}
		   else if((cardNo.length!=16)&&(cardNo.length!=19))
		   	{
		    	 alert("卡号只能为16位或19位！");
		    	 return false;
		   		}
		   else if(!isNumber(cardNo)){
		    	 alert("卡号只能为数字！");
		    	 return false;
		  }
		  return true;
}


function validateCardNoStr(cardNo){
		  if(isEmpty(cardNo))
		    {
				return "卡号为空!";
		    	}
		   else if((cardNo.length!=16)&&(cardNo.length!=19))
		   	{
		    	return "卡号只能16位或19位!";
		   		}
		   else if(!isNumber(cardNo)){
		    	return "卡号只能为数字!";
		  }
		  return "";
}
/* 

*验证行动码输入
*/

function validateActionCode(actionCode)
{
			  if(isEmpty(actionCode))
		    {
		    	alert("行动码不能为空！");
		    	 return false;
		     }
		   return true;
}
 
/*
*验证金融类交易金额
*/

function validateFinancialAMT(financialAMT)
{
			if(isEmpty(financialAMT))
		    {
		    	alert("交易金额不能为空！");
		    	 return false;
		    }
		  else if(!isDouble(financialAMT)){ /*校验是否为double 小数位2位数字 需要修改 20090824*/
		    	alert("交易金额数据格式非法！");
		    	 return false;
		  }
		 return true;
		 
}


function validateadjustAmtStr(financialAMT)
{
			if(isEmpty(financialAMT))
		    {
		    	return "交易金额为空!";
		    }
		  else if(!isDouble(financialAMT)){ /*校验是否为double 小数位2位数字 需要修改 20090824*/
		    	return "数据格式非法!";
		  }
		 return "";
		 
}
/*
*验证积分金额点数
*/

function validateFsAMT(fsAMT)
{
		if(isEmpty(fsAMT))
		    {
		    	alert("积分金额点数不能为空！");
		    	 return false;
		    }
		  else if(!isNumber(fsAMT)){
		    	alert("积分金额点数只能为数字！");
		    	 return false;
		  }
		 return true;
}


function validateFsAMTStr(fsAMT)
{
		if(isEmpty(fsAMT))
		    {
		    	return "积分金额点数不能为空!";
		    }
		  else if(!isNumber(fsAMT)){
		    	return "积分金额点数只能为数字!";
		  }
		 return "";
}

/*
校验顺序号
*/
function validateSeqNo(seqno)
{
	var seq = trim(seqno)
	var re = /^[0-9]{0,2}$/;
	if(!re.test(seq))
	{
		return "顺序号非法";
	}
	return "";
}

/*
 根据行动码类型，ajax获取对应的行动码列表供选择
*/
function getActionCode(){
  return;
}

//是否为整数部分最大7位，小数位两位的数字
function isDouble(s)
{   var i;

    if (isEmpty(s)) 
       return false;

    var  re  =/^(([1-9]\d{0,6})|(0))(\.\d{1,2})?$/;
    if(re.test(s))
	{
		var zoRe = /^(0{1,7})(\.0{1,2})?$/;
		if(zoRe.test(s))
			return 0;
		return 1;
	}
    else
       return 0;
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
// 判断是否为空.true 为空
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