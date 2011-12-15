/*
用于财务处理部分具体域信息的验证
*/

/*
*具体域的验证
*
*/

/*
*验证起息日期
*/
function validateInsDate( insDate){
	
			if(isEmpty(insDate))
		    {
		    	alert("起息日期不能为空！");
		    	 return false;
		    }
		   return true;
}

/*
*验证卡号
*/
function validateCardNo( cardNo){
		  if(isEmpty(cardNo))
		    {
		    	alert("卡号不能为空！");
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
		  else if(!isNumber(financialAMT)){
		    	alert("交易金额只能为数字！");
		    	 return false;
		  }
		 return true;
		 
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

/*
 根据行动码类型，ajax获取对应的行动码列表供选择
*/
function getActionCode(){
  return;
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