/*
���ڲ������־�������Ϣ����֤
*/

/*
*���������֤
*
*/
/*
*��֤��Ϣ�����Ƿ�Ϸ�
*/
function validateInsDate(insDate){
	
			if(isEmpty(insDate))
		    {
		    	alert("��Ϣ���ڲ���Ϊ�գ�");
		    	 return false;
		    }
		    
		    var date =/^(\d{4})(\d{2})(\d{2})$/;
  			if(!date.test(insDate) && insDate!=""){
  				alert('���ڸ�ʽ�Ƿ�!');
  				return false;
  			}
  			
			Y=insDate.substring(0,4);
			M=insDate.substring(4,6);
			D=insDate.substring(6,8);
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
		    
  			//У����Ϣ�����Ƿ����ڵ�ǰ��������
            var currDate=getCurrDate();
			if(insDate > currDate)
			{
			  alert("��Ϣ���ڲ������ڼ������ڣ�");
			  return false;
			  }		
		   return true;
}

function validateInsDateStr(insDate){
	
			if(isEmpty(insDate))
		    {
		    	return "��Ϣ����Ϊ��!";
		    }
		    var date =/^(\d{4})(\d{2})(\d{2})$/;
  			if(!date.test(insDate) && insDate!=""){
  				return "���ڸ�ʽ�Ƿ�!";
  			}
  			
  			Y=insDate.substring(0,4);
			M=insDate.substring(4,6);
			D=insDate.substring(6,8);
			if(Y<1900 || Y>2100){
				return "�������벻��ȷ��";
			}
			Months= new Array(31,28,31,30,31,30,31,31,30,31,30,31);
			Leap = false;
			if((Y % 4 == 0) && ((Y % 100 != 0) || (Y %400 == 0)))	Leap = true;
			if((D < 1) || (D > 31) || (M < 1) || (M > 12) || (Y < 0))	{ return "�������벻��ȷ";}
			if((D > Months[M-1]) && !((M == 2) && (D > 28)))	{return "�������벻��ȷ";}
			if(!(Leap) && (M == 2) && (D > 28))	{return "�������벻��ȷ";} 
			if((Leap) && (M == 2) && (D > 29)) 	{return "�������벻��ȷ";} 
  			
  		//У����Ϣ�����Ƿ����ڵ�ǰ��������
            var currDate=getCurrDate();
			if(insDate > currDate)
			{
			 return "��Ϣ�������ڼ�������!" + currDate;
			  }		
		   return "";
}
 /*
 *����yyyyMMdd��ʽ��ǰϵͳʱ��
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
*��֤����
*/
function validateCardNo(cardNo){
		  if(isEmpty(cardNo))
		    {
				alert("���Ų���Ϊ��!");
				return false;
		    	}
		   else if((cardNo.length!=16)&&(cardNo.length!=19))
		   	{
		    	 alert("����ֻ��Ϊ16λ��19λ��");
		    	 return false;
		   		}
		   else if(!isNumber(cardNo)){
		    	 alert("����ֻ��Ϊ���֣�");
		    	 return false;
		  }
		  return true;
}


function validateCardNoStr(cardNo){
		  if(isEmpty(cardNo))
		    {
				return "����Ϊ��!";
		    	}
		   else if((cardNo.length!=16)&&(cardNo.length!=19))
		   	{
		    	return "����ֻ��16λ��19λ!";
		   		}
		   else if(!isNumber(cardNo)){
		    	return "����ֻ��Ϊ����!";
		  }
		  return "";
}
/* 

*��֤�ж�������
*/

function validateActionCode(actionCode)
{
			  if(isEmpty(actionCode))
		    {
		    	alert("�ж��벻��Ϊ�գ�");
		    	 return false;
		     }
		   return true;
}
 
/*
*��֤�����ཻ�׽��
*/

function validateFinancialAMT(financialAMT)
{
			if(isEmpty(financialAMT))
		    {
		    	alert("���׽���Ϊ�գ�");
		    	 return false;
		    }
		  else if(!isDouble(financialAMT)){ /*У���Ƿ�Ϊdouble С��λ2λ���� ��Ҫ�޸� 20090824*/
		    	alert("���׽�����ݸ�ʽ�Ƿ���");
		    	 return false;
		  }
		 return true;
		 
}


function validateadjustAmtStr(financialAMT)
{
			if(isEmpty(financialAMT))
		    {
		    	return "���׽��Ϊ��!";
		    }
		  else if(!isDouble(financialAMT)){ /*У���Ƿ�Ϊdouble С��λ2λ���� ��Ҫ�޸� 20090824*/
		    	return "���ݸ�ʽ�Ƿ�!";
		  }
		 return "";
		 
}
/*
*��֤���ֽ�����
*/

function validateFsAMT(fsAMT)
{
		if(isEmpty(fsAMT))
		    {
		    	alert("���ֽ���������Ϊ�գ�");
		    	 return false;
		    }
		  else if(!isNumber(fsAMT)){
		    	alert("���ֽ�����ֻ��Ϊ���֣�");
		    	 return false;
		  }
		 return true;
}


function validateFsAMTStr(fsAMT)
{
		if(isEmpty(fsAMT))
		    {
		    	return "���ֽ���������Ϊ��!";
		    }
		  else if(!isNumber(fsAMT)){
		    	return "���ֽ�����ֻ��Ϊ����!";
		  }
		 return "";
}

/*
У��˳���
*/
function validateSeqNo(seqno)
{
	var seq = trim(seqno)
	var re = /^[0-9]{0,2}$/;
	if(!re.test(seq))
	{
		return "˳��ŷǷ�";
	}
	return "";
}

/*
 �����ж������ͣ�ajax��ȡ��Ӧ���ж����б�ѡ��
*/
function getActionCode(){
  return;
}

//�Ƿ�Ϊ�����������7λ��С��λ��λ������
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
// �ж��Ƿ�Ϊ��.true Ϊ��
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
//�Ƿ�Ϊ���ַ���
function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}
/*
ȥ���ַ������ߵĿ��ַ�
����default.js
����ie��ok
*/
function trim(s)
{
	return trimRight(trimLeft(s))
}
function trimLeft(s) {
	while (s.charAt(0) ==" " ||s.charAt(0) =="��" ){
		s = s.substr(1,s.length-1)
	}
	return s;
}
function trimRight(s) {
	while (s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "��")	{
		s = s.substr(0,s.length-1)
	}
	return s;
}