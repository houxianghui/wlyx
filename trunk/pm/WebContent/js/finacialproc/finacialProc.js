/*
���ڲ������־�������Ϣ����֤
*/

/*
*���������֤
*
*/

/*
*��֤��Ϣ����
*/
function validateInsDate( insDate){
	
			if(isEmpty(insDate))
		    {
		    	alert("��Ϣ���ڲ���Ϊ�գ�");
		    	 return false;
		    }
		   return true;
}

/*
*��֤����
*/
function validateCardNo( cardNo){
		  if(isEmpty(cardNo))
		    {
		    	alert("���Ų���Ϊ�գ�");
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
		  else if(!isNumber(financialAMT)){
		    	alert("���׽��ֻ��Ϊ���֣�");
		    	 return false;
		  }
		 return true;
		 
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

/*
 �����ж������ͣ�ajax��ȡ��Ӧ���ж����б�ѡ��
*/
function getActionCode(){
  return;
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