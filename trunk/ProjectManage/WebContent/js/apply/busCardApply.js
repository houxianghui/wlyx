

//ֻ���������ģ����ڡ������������С��ļ��
function onlyChinese(obj){
	if(obj.value!=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0020]/g,''))
		obj.value=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0020]/g,'');
}


//����Ƿ�����������,Ҳ������С����
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

function checkBusForm(){

	/**����ֶΣ����лس�,ȥ���س�*/
	var fielde = new Array("comp_name","comp_ename","comp_id","license_code","comp_addr1","dele_cert_no","other_code"); 
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/**end*/
	
	//�������MsgError���˱����洢һϵ�еĴ�����ʾ�ַ�������Щ�����ǿ���ǿ��ͨ����
	//var MsgError = "";

	//ִ��У��   �ı��� 
	var field = new Array("app_lmt","comp_name","comp_ename","license_code","comp_addr1","comp_city_code","comp_post","comp_zone_no","comp_phone","base_acc_license","base_acc_bank","base_account","exam_node_name","app_source","dele_name","dele_cert_no","dele_zone_no","dele_phone","lm_name","lm_zone_no","lm_phone"); 
	var info = new Array("��������","��λ����","��λ����(ƴ��)","��λӪҵִ�ձ��","��λ��ַ","��λ��ַ���д���","��λ�ʱ�","��λ�绰����","��λ�绰����","�������֤��","������������","�������˺�","������","�������Դ","���˴�������","���˴���֤����","���˴���绰����","���˴���绰","��ϵ������","��ϵ�˵绰����","��ϵ�˵绰"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	 	tmp ="document.forms[0]."+field[i]+".value"; 
	    if(isEmpty(eval(tmp))) { 
		 	alert('������'+info[i]); 
		    eval("document.forms[0]."+field[i]+".focus()"); 
		    return false; 
	     	} 
	} 
 
 	//ִ��У��   ��ѡ��ť
	var fieldB = new Array("comp_char","credit_level","pre2","exam_mode","dele_cert_type","lm_posi","bill_date","comp_id_type"); 
	var infoB = new Array("��λ����","��ũ�����õȼ�","�����ۺ����Ŷ��","���鷽ʽ","���˴���֤������","��ϵ��ְ��","�˵���","��λ��������"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
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
	        alert('������'+infoB[i]); 
			eval("document.forms[0]."+fieldB[i]+"[0].focus()"); 
			return false; 
		}	     	
	}
	
	/*�����������==============================*/
	var obj=document.forms[0].app_lmt;
	if(!checkYForGet(obj)){
		alert("���������Ϊ��,����0��ͷ");
		obj.focus();
		return false;
	}
		
	//������ܸ���5000000Ԫ
	//if(parseInt(obj.value,10) > 100){
	if(parseInt(obj.value*100,10) > 50000){
		//alert(obj.value*100);
		alert("�������Ȳ��ܳ���500��Ԫ!");
		obj.focus();
		return false;
	}	
	//������ܵ���10000Ԫ
	if(parseInt(obj.value,10) < 1){
		alert("�������Ȳ��ܵ���10000Ԫ!");
		obj.focus();
		return false;
	}	
	
		
	/*��鵥λ���Ʋ��ܳ���40λ��20������*/
	var comp_name=document.forms[0].comp_name;
	if(getStrLength(comp_name.value) > 40){
		alert("��λ���Ʋ��ܳ���40λ");
		comp_name.focus();
		return false;
	}
	if(!isLetter(document.forms[0].comp_ename.value)){
		alert("��λƴ��ӦΪ��дӢ����ĸ");
		document.forms[0].comp_ename.focus();
		return false;
	}	
	
	
	//��λ��֯�������룺����Ǹ���������ɲ���д����������±���
	if(getSelectedButton(document.forms[0].comp_char) != 2 && getSelectedButton(document.forms[0].comp_char) != 3){
		if(isEmpty(document.forms[0].comp_id.value)){
			alert("�����뵥λ��֯��������");
			document.forms[0].comp_id.focus();
			return false;
		}
	}
	
	//ע���ʽ�У���Ƿ�Ϊ�Ϸ���С��
	var reg_capital = document.forms[0].reg_capital.value;
	if(!isEmpty(reg_capital)){
	
		if(!IsDecimal(reg_capital)){
			alert("ע���ʽ𲻺Ϸ�");
			document.forms[0].reg_capital.focus();
			return false;
		}
	//	if(!checkYForGet(reg_capital)){
	//		alert("ע���ʽ���Ϊ��,����0��ͷ");
	//		reg_capital.focus();
	//		return false;
	//	}		
		//ע���ʽ��ܵ���100000Ԫ
		if(parseInt(reg_capital,10) < 10&&parseInt(reg_capital,10) != 0){
			alert("ע���ʽ��ܵ���100000Ԫ!");
			document.forms[0].reg_capital.focus();
			return false;
		}	
	}
	
	//ע�����޴���5
	if(!isEmpty(document.forms[0].reg_term.value)){
		if(parseInt(document.forms[0].reg_term.value,10) < 5&&parseInt(document.forms[0].reg_term.value,10) != 0){
			alert("ע�����޲���С��5��!");
			document.forms[0].reg_term.focus();
			return false;
		}
	}	
	
	/*����ʱ�λ��=======================================*/
	var field = new Array("comp_post"); 
	var info = new Array("��λ�ʱ�"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value.length"; 
	        if((eval(tmp)) < 6) { 
	        	alert(info[i]+'ӦΪ��λ'); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return false; 
	       	} 
	} 	
	
	/*��� ��λ��֯�������� ���ܳ���30λ*/
	var comp_id=document.forms[0].comp_id;
	if(!isEmpty(comp_id.value)){
		if(getStrLength(comp_id.value) > 30){
			alert("��λ���벻�ܳ���30λ");
			comp_id.focus();
			return false;
		}
	}
	
	/*��� Ӫҵִ�ձ�� ���ܳ���30λ*/
	var license_code=document.forms[0].license_code;
	if(!isEmpty(license_code.value)){
		if(getStrLength(license_code.value) > 30){
			alert("Ӫҵִ�ձ�Ų��ܳ���30λ");
			license_code.focus();
			return false;
		}
	}
	
	/*��� ��λ��ַ ���ܳ���40λ*/
	var comp_addr1=document.forms[0].comp_addr1;
	if(getStrLength(comp_addr1.value) > 40){
		alert("��λ��ַ���ܳ���40λ");
		comp_addr1.focus();
		return false;
	}
	
	/*��� ��ϵ������ְ�� ���ܳ���10λ*/
	var lm_posi_remark=document.forms[0].lm_posi_remark;
	if(getStrLength(lm_posi_remark.value) > 10){
		alert("��ϵ������ְ���ܳ���10λ");
		lm_posi_remark.focus();
		return false;
	}
	
	/*��� �������֤�� ���ܳ���18λ*/
	var base_acc_license=document.forms[0].base_acc_license;
	if(getStrLength(base_acc_license.value) > 18){
		alert("�������֤�Ų��ܳ���18λ");
		base_acc_license.focus();
		return false;
	}
	
			
	/*�������������в��ܳ���40λ��20������*/
	var base_acc_bank=document.forms[0].base_acc_bank;
	if(getStrLength(base_acc_bank.value) > 40){
		alert("�����������в��ܳ���40λ");
		base_acc_bank.focus();
		return false;
	}
	
	//У��:����--������������ũ�У�����д�� ũ�п�����;�˺�
	var accbank_inabc = document.forms[0].accbank_inabc;
	var acc_inabc = document.forms[0].acc_inabc;
	if((!isEmpty(accbank_inabc.value)) == isEmpty(acc_inabc.value)){
		alert("�������˺Ż򿪻�����Ϣ");
		accbank_inabc.focus();
		return false;
	}
	
	//���������ۺ����Ŷ�ȣ����ۺ����Ŷ��������Ƿ񸽺��߼�
	var credit_lmt = document.forms[0].credit_lmt.value;
	var app_lmt=document.forms[0].app_lmt;
	//�������ۺ�����Ϊ���ޡ�
	if(getSelectedButton(document.forms[0].pre2) == 0){
		if(!isEmpty(credit_lmt)){
			if(parseInt(credit_lmt,10) > 0){
				alert("�ۺ����Ŷ������");
				document.forms[0].credit_lmt.focus();
				return false;
			}
		}
		//������ܳ���100��Ԫ
		if(parseInt(app_lmt.value,10) > 100){
			alert("���ۺ�����ʱ�������Ȳ��ܳ���100��Ԫ!");
			app_lmt.focus();
			return false;
		}
	}
	//�������ۺ�����Ϊ���С�
	if(getSelectedButton(document.forms[0].pre2) == 1){
		if(isEmpty(credit_lmt)){
			alert("�������ۺ����Ŷ��");
			document.forms[0].credit_lmt.focus();
			return false;
		}
		if(!isEmpty(credit_lmt)){
			if(parseInt(credit_lmt,10) < 1){
				alert("�ۺ����Ŷ������");
				document.forms[0].credit_lmt.focus();
				return false;
			}
		}
		//��������߲������ۺ����Ŷ�ȵ�3%
		//credit_lmt*3%
		if(parseInt(app_lmt.value*100/3,10) > credit_lmt){
		//if(parseInt(app_lmt.value,10) > 10){
			alert("���ۺ�����ʱ����������߲������ۺ����Ŷ�ȵ�3%!");
			app_lmt.focus();
			return false;
		}
	}
	
	/*����ֻ���=======================================*/
	var field = new Array("dele_mobile","lm_mobile"); 
	var info = new Array("���˴����ƶ��绰","��ϵ���ƶ��绰"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		if(!isEmpty(eval(tmp))){
			if(!isMobile(eval(tmp))){
				alert(info[i]+'��������');
				eval("document.forms[0]."+field[i]+".focus()");
				return false;
			}
		}
	}
	
	if(!isEmpty(document.forms[0].dele_mobile.value)){
		if(document.forms[0].dele_mobile.value.length != 11){
			alert("���˴����ֻ�����Ϊ11λ����");
			document.forms[0].dele_mobile.focus();
			return false;
		}
	}	
	if(!isEmpty(document.forms[0].lm_mobile.value)){
		if(document.forms[0].lm_mobile.value.length != 11){
			alert("��ϵ���ֻ�����Ϊ11λ����");
			document.forms[0].lm_mobile.focus();
			return false;
		}
	}

	//�绰���벻��С��7λ
	var field = new Array("comp_phone","dele_phone","lm_phone"); 
	var info = new Array("��λ�绰����","���˴���绰����","��ϵ�˵绰"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value.length";
		if(eval(tmp) < 7){
			alert(info[i]+'���Ȳ���С��7λ');
			eval("document.forms[0]."+field[i]+".focus()");
			return false;
		}
	}
	
	//���绰��������ȫ�㣬���ᱨ��
	var field = new Array("comp_zone_no","comp_phone","dele_zone_no","dele_phone","lm_zone_no","lm_phone"); 
	var info = new Array("��λ�绰����","��λ�绰����","���˴���绰����","���˴���绰","��ϵ�˵绰����","��ϵ�˵绰"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		if(isZero(trim(eval(tmp)))){
			alert(info[i]+'����ȫ����0');
			eval("document.forms[0]."+field[i]+".focus()");
			return false;
		}
	}	
	
	/*��鷨�˴������֤֤������=========================================*/
   if(getSelectedButton(document.forms[0].dele_cert_type)== 0){
    	obj = document.forms[0].dele_cert_no;
    	if(!isIDNO(obj)){
    		document.forms[0].dele_cert_no.focus();	
    		return false;
    	}  			
	}
	
	//�����˴������֤֤������Ϊ����
	if(getSelectedButton(document.forms[0].dele_cert_type) == 2){
		if(isEmpty(document.forms[0].dele_cert_name.value)){
			alert("����������֤������");
			document.forms[0].dele_cert_name.focus();
			return false;
		}
	}
	//��֤�����Ͳ�Ϊ"����",��"��ע��"�ı���Ϊ��,����ֵ
	if(getSelectedButton(document.forms[0].dele_cert_type) != 2){
		if(!isEmpty(document.forms[0].dele_cert_name.value)){
			alert("֤�����ͳ�ͻ");
			document.forms[0].dele_cert_name.focus();
			return false;
		}
	}
	
	//����ϵ��ְ��Ϊ����
	if(getSelectedButton(document.forms[0].lm_posi) == 2){
		if(isEmpty(document.forms[0].lm_posi_remark.value)){
			alert("��������ϵ��ְ��");
			document.forms[0].lm_posi_remark.focus();
			return false;
		}
	}
	//����ϵ��ְ��Ϊ����������ְ����
	if(getSelectedButton(document.forms[0].lm_posi) != 2){
		if(!isEmpty(document.forms[0].lm_posi_remark.value)){
			alert("��ϵ��ְ���д���");
			document.forms[0].lm_posi_remark.focus();
			return false;
		}
	}
	
	//�������������У���Ƿ�Ϊ�Ϸ���С��
	var app_lmt = document.forms[0].app_lmt;
	if(!IsDecimal(app_lmt.value)){
		alert("�������ȣ�����ң����Ϸ�");
		app_lmt.focus();
		return false;
	}
	//����������ԪУ���Ƿ�Ϊ�Ϸ���С��
	//var app_uslmt = document.forms[0].app_uslmt;
	//if(!IsDecimal(app_uslmt.value)){
	//	alert("�������ȣ���Ԫ�����Ϸ�");
	//	app_uslmt.focus();
	//	return false;
	//}
	//�ۺ����Ŷ��У���Ƿ�Ϊ�Ϸ���С��
	var credit_lmt = document.forms[0].credit_lmt;
	if(!isEmpty(credit_lmt.value)){
		if(!IsDecimal(credit_lmt.value)){
			alert("�ۺ����Ŷ�Ȳ��Ϸ�");
			credit_lmt.focus();
			return false;
		}
	}
	//����˺��Ƿ���19λ
	var base_account = document.forms[0].base_account;//�������˺ţ�����
	if(base_account.value.length != 19){
		alert("�˺�ӦΪ19λ����");
		base_account.focus();
		return false;
	}
	var acc_inabc = document.forms[0].acc_inabc;//ũ�п����˺�
	if(!isEmpty(acc_inabc.value)){
		if(acc_inabc.value.length != 19){
			alert("�˺�ӦΪ19λ����");
			acc_inabc.focus();
			return false;
		}
	}
	
	/*����������=========================================*/
    if(!isEmpty(document.forms[0].lm_email.value)){
	    if(!isEmail(document.forms[0].lm_email)){
	    	alert("�����������");
	    	document.forms[0].lm_email.focus();
	    	return false;
	    }
	    if(!ChineseIn(document.forms[0].lm_email.value)){
	    	alert("���������в��Ϸ��ַ�");
	    	document.forms[0].lm_email.focus();
	    	return false;	
	    }
    }
    
    /**Ӧ�Ҫ��У��v�����ܳ��ֵĴ���  begin*/
	var fieldv = new Array("comp_ename","lm_phone","dele_phone","accbank_inabc","comp_addr1","other_code","license_code","comp_id","comp_name","comp_phone","base_acc_license","base_acc_bank","accbank_inabc","dele_name","dele_phone","dele_cert_no","dele_cert_name","lm_name","lm_phone","lm_posi_remark"); 
	var infov = new Array("��λ���ƣ�ƴ����Ӣ�ģ�","��ϵ�˵绰","���˴���绰","ũ�п�����","��λ��ַ","����ע�����ͼ�����","��λӪҵִ�ձ���","��֯��������","��λȫ��(����)","��λ�绰����","�������֤��","������������","ũ�п�����","���˴�������","���˴���绰����","���˴���֤������","���˴�������֤������","��ϵ������(����)","��ϵ�˵绰����","����ְ������"); 
 	var fvaluev = new Array(16,17,17,40,80,30,30,30,40,17,18,40,40,40,17,20,20,40,17,10); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	var tmp; 
	for(var i=0;i<fieldv.length;i++)	{ 
	 	tmp ="document.forms[0]."+fieldv[i]+".value"; 
	    if(!isEmpty(eval(tmp))) {
	    	if( getChineseStrLength(eval(tmp)) > fvaluev[i] ){
	    	 	alert(infov[i]+'���ȳ�������'); 
			    eval("document.forms[0]."+fieldv[i]+".focus()"); 
			    return false; 
		    }
	     } 
	}
	/**end*/ 
	
	

	
	//֤������18λ��15λʱ��֤������ӦΪ���֤����ʱ���֤�����������ϱ�׼�ģ�
	//ϵͳ��ʾ��֤�����ʹ��󡱣�������ǿ��ͨ����
	str = document.forms[0].dele_cert_no.value;
	if((str.length==15) || (str.length==18)){
		if(getSelectedButton(document.forms[0].dele_cert_type) != 0){		
			alert("���˴���֤�����ʹ���!");}
	}	
	
	return true;	//�������,����true
	
}


/*=============================����Ϊ���õ����Ӻ���====================*/
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


//������Ϊ�ջ��߿�ͷΪ0
function checkYForGet(obj){
	str=obj.value;
	if(str.length==0) return false;
	if(str.charAt(0)=='0')return false;
	return true;
}

//�õ���ѡ����ѡ�е���
function getSelectedButton(buttonGroup){ 	
	for(var i=0;i<buttonGroup.length;i++){
		if(buttonGroup[i].checked){
			return i;
		}
	}
	return 0;
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

function checkdateNumber(obj){   
  var str;
      str=obj.value;
  if(str.length!=8){
	    return false;
  }
  return true
}

//������֤����
function isIDNO(obj){
	var s = trim(obj.value);
	if (!((s.length==18) || (s.length==15))){
		//alert(s.length);
		alert("���֤���볤��ֻ��Ϊ15��18λ");
		
		return false;
	}
	else if (s.length==15){
		if (!warnCharsInBag(s, "0123456789")){
			alert("���֤���볤Ϊ15λʱ,����ȫ��������");
			return false;
		}
	}
	else if (obj.value.length==18){
		if (!isNumber(s)){
			var sub=s.substring(1,17)
			if (!warnCharsInBag(sub, "0123456789")){
				alert("���֤���볤Ϊ18λʱ,ǰ17λ����ȫ��������");
				return false;
			}
			if(!warnCharsInBag(s, "0123456789")){							    
				if ((s.charAt(17)!="x") && (s.charAt(17)!="X")){
					alert("���֤����ֻ�������һλ���������ַ�X��x");
				    return false;
				}
			}
		}
	}
	return true;
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
//����������
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

//У���ֻ����룺���������ֿ�ͷ���������⣬�ɺ��С�-�� 
function isMobile(s){ 
	var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/; 
	if (!patrn.exec(s)) return false ;
	return true ;
} 

//���s�Ƿ�Ϊ��С��
//������Ϊ��С��,��ô����ȥ��"-",Ȼ���ô˺���У��
function IsDecimal(s)
{
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
��������ַ����Ƿ���Ӣ����ĸ
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
//�ж��ַ������Ƿ�������Ļ��������Ϸ����ַ� 
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
//�ж��ַ����Ƿ�ȫ��0 ,ȫ0����true
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

//�����ָ�ֵ
function mainCardNationality(selObj){ //v3.0
	var selectedIndex = getSelectedButton(document.forms[0].select);
 	document.forms[0].nationality.value=selObj.options[selObj.selectedIndex].value;
  
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

/**
*�����ַ����������ַ������ȡ�
*���ã����ƴ����ֵ��ַ����ĳ��ȣ����ֿ��������Ҫ����һ���ֽ�
*strΪ������ַ���
*�磺��123���456������Ϊ10�����ڴ˺��������У����س���Ϊ12����Ϊ�ڡ���á������踽��һ���ֽ�
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

var country_code;
country_code="<option value=\"\">--��ѡ�����--</option><option value=\"CHN\">CHN-�й�</option> <option value=\"HKG\">HKG-���</option> <option value=\"MAC\">MAC-����</option> <option value=\"TWN\">TWN-�й�̨��</option> <option value=\"USA\">USA-����</option> <option value=\"GBR\">GBR-Ӣ��</option> <option value=\"FRA\">FRA-����</option> <option value=\"DEU\">DEU-�¹�</option> <option value=\"ITA\">ITA-�����</option> <option value=\"GRC\">GRC-ϣ��</option> <option value=\"RUS\">RUS-����˹����</option> <option value=\"ESP\">ESP-������</option> <option value=\"PRT\">PRT-������</option> <option value=\"SWE\">SWE-���</option> <option value=\"CHE\">CHE-��ʿ</option> <option value=\"PRK\">PRK-����</option> <option value=\"KOR\">KOR-����</option> <option value=\"IND\">IND-ӡ��</option> <option value=\"AUS\">AUS-�Ĵ�����</option> <option value=\"ISR\">ISR-��ɫ��</option> <option value=\"JPN\">JPN-�ձ�</option> <option value=\"BRA\">BRA-����</option> <option value=\"AFG\">AFG-������</option> <option value=\"ALB\">ALB-����������</option> <option value=\"ADZ\">ADZ-����������</option> <option value=\"ASM\">ASM-������Ħ��Ⱥ��</option> <option value=\"AND\">AND-������</option> <option value=\"AGO\">AGO-������</option> <option value=\"AIA\">AIA-������</option> <option value=\"ATG\">ATG-����ϼ��Ͳ���</option> <option value=\"ARG\">ARG-����͢</option> <option value=\"ARM\">ARM-��������</option> <option value=\"ABW\">ABW-��¬��</option> <option value=\"AUT\">AUT-�µ���</option> <option value=\"AZE\">AZE-�����ݽ�</option> <option value=\"BHS\">BHS-�͹���</option> <option value=\"BHR\">BHR-����</option> <option value=\"BGD\">BGD-�ϼ���</option> <option value=\"BRB\">BRB-�ͰͶ�˹</option> <option value=\"BLR\">BLR-�׶���˹</option> <option value=\"BEL\">BEL-����ʱ</option> <option value=\"BLZ\">BLZ-������</option> <option value=\"BEN\">BEN-������</option> <option value=\"BMU\">BMU-��Ľ��Ⱥ��</option> <option value=\"BTN\">BTN-����</option> <option value=\"BOL\">BOL-����ά��</option> <option value=\"BIH\">BIH-��ʿ���Ǻ�����ά������</option> <option value=\"BWA\">BWA-��������</option> <option value=\"BRN\">BRN-����</option> <option value=\"BGR\">BGR-��������</option> <option value=\"BFA\">BFA-�����ɷ���</option> <option value=\"BDI\">BDI-��¡��</option> <option value=\"KHM\">KHM-����</option> <option value=\"CMR\">CMR-����¡���ϵĹ��͹�</option> <option value=\"CAN\">CAN-���ô�</option> <option value=\"TCD\">TCD-է��</option> <option value=\"CHL\">CHL-����</option> <option value=\"COL\">COL-���ױ���</option> <option value=\"COM\">COM-��Ħ��</option> <option value=\"COG\">COG-�չ�</option> <option value=\"COK\">COK-���Ⱥ��</option> <option value=\"CRI\">CRI-��˹�����</option> <option value=\"HRV\">HRV-���ް�����</option> <option value=\"CUB\">CUB-�Ű�</option> <option value=\"CYP\">CYP-������˹</option> <option value=\"CZE\">CZE-�ݿ�</option> <option value=\"DNK\">DNK-����</option> <option value=\"DJI\">DJI-������</option> <option value=\"DMA\">DMA-�����������</option> <option value=\"DOM\">DOM-������ӹ��͹�</option> <option value=\"TMP\">TMP-������</option> <option value=\"ECU\">ECU-��϶��</option> <option value=\"EGY\">EGY-����</option> <option value=\"SLV\">SLV-�����߶�</option> <option value=\"GNQ\">GNQ-���������</option> <option value=\"ERI\">ERI-��������</option> <option value=\"EST\">EST-��ɳ����</option> <option value=\"ETH\">ETH-���������</option> <option value=\"FJI\">FJI-쳼�</option> <option value=\"FIN\">FIN-����</option> <option value=\"GAB\">GAB-����</option> <option value=\"GMB\">GMB-�Ա���</option> <option value=\"GEO\">GEO-��������</option> <option value=\"GHA\">GHA-����</option> <option value=\"GIB\">GIB-ֱ������</option> <option value=\"GRL\">GRL-������</option> <option value=\"GRD\">GRD-�����ɴ�</option> <option value=\"GLP\">GLP-������嵺</option> <option value=\"GUM\">GUM-�ص�</option> <option value=\"GTM\">GTM-Σ������</option> <option value=\"GIN\">GIN-������</option> <option value=\"GNB\">GNB-�����Ǳ��ܹ��͹�</option> <option value=\"GUY\">GUY-������</option> <option value=\"HTI\">HTI-����</option> <option value=\"HMD\">HMD-HEARD AND MCDONALD IS</option> <option value=\"HND\">HND-�鶼��˹</option> <option value=\"HUN\">HUN-������</option> <option value=\"ISL\">ISL-����</option> <option value=\"IDN\">IDN-ӡ��</option> <option value=\"IRN\">IRN-��������</option> <option value=\"IRQ\">IRQ-������</option> <option value=\"IRL\">IRL-������</option> <option value=\"CIV\">CIV-��������</option> <option value=\"JAM\">JAM-�����</option> <option value=\"JOR\">JOR-Լ��</option> <option value=\"KAZ\">KAZ-������</option> <option value=\"KEN\">KEN-������</option> <option value=\"KIR\">KIR-������˹</option> <option value=\"KWT\">KWT-������</option> <option value=\"KGZ\">KGZ-������˹</option> <option value=\"LAO\">LAO-����</option> <option value=\"LVA\">LVA-����ά��</option> <option value=\"LBN\">LBN-�����</option> <option value=\"LSO\">LSO-������</option> <option value=\"LBR\">LBR-��������</option> <option value=\"LBY\">LBY-������</option> <option value=\"LIE\">LIE-��֧��ʿ��</option> <option value=\"LTU\">LTU-������</option> <option value=\"LUX\">LUX-¬ɭ��</option> <option value=\"MKD\">MKD-�����</option> <option value=\"MDG\">MDG-����˹��</option> <option value=\"MWI\">MWI-����ά</option> <option value=\"MYS\">MYS-��������</option> <option value=\"MDV\">MDV-�������</option> <option value=\"MLI\">MLI-����</option> <option value=\"MLT\">MLT-�����</option> <option value=\"MHL\">MHL-��Ъ����</option> <option value=\"MTQ\">MTQ-������˵�</option> <option value=\"MRT\">MRT-ë��������</option> <option value=\"MUS\">MUS-ë����˹</option> <option value=\"MYT\">MYT-��Լ��</option> <option value=\"MEX\">MEX-ī����</option> <option value=\"FSM\">FSM-�ܿ���������</option> <option value=\"MDA\">MDA-Ħ�����߹��͹�</option> <option value=\"MCO\">MCO-Ħ�ɸ�</option> <option value=\"MNG\">MNG-�ɹ���</option> <option value=\"MSR\">MSR-����ɫ��</option> <option value=\"MAR\">MAR-Ħ���</option> <option value=\"MOZ\">MOZ-Īɣ�ȿ�</option> <option value=\"MMR\">MMR-���</option> <option value=\"NAM\">NAM-���ױ���</option> <option value=\"NRU\">NRU-�³</option> <option value=\"NPL\">NPL-�Ჴ��</option> <option value=\"NLD\">NLD-����</option> <option value=\"ANT\">ANT-����������˹Ⱥ��</option> <option value=\"NCL\">NCL-�¼��ն�����</option> <option value=\"NZL\">NZL-������</option> <option value=\"NIC\">NIC-�������</option> <option value=\"NER\">NER-���ն�</option> <option value=\"NGA\">NGA-��������</option> <option value=\"NIU\">NIU-Ŧ����</option> <option value=\"NFK\">NFK-ŵ���˵�</option> <option value=\"MNP\">MNP-������������Ⱥ��</option> <option value=\"NOR\">NOR-Ų��</option> <option value=\"OMN\">OMN-����</option> <option value=\"PAK\">PAK-�ͻ�˹̹</option> <option value=\"PLW\">PLW-����</option> <option value=\"PAN\">PAN-������</option> <option value=\"PNG\">PNG-�Ͳ����¼�����</option> <option value=\"PRY\">PRY-������</option> <option value=\"PER\">PER-��³</option> <option value=\"PHL\">PHL-���ɱ����͹�</option> <option value=\"PCN\">PCN-Ƥ�ؿ�����</option> <option value=\"POL\">POL-����</option> <option value=\"PRI\">PRI-�������</option> <option value=\"QAT\">QAT-������</option> <option value=\"REU\">REU-��Բ</option> <option value=\"ROM\">ROM-��������</option> <option value=\"RWA\">RWA-¬����</option> <option value=\"WSM\">WSM-������Ħ��</option> <option value=\"SMR\">SMR-ʥ����ŵ</option> <option value=\"STP\">STP-ʥ��������������</option> <option value=\"SAU\">SAU-ɳ�ذ�����</option> <option value=\"SEN\">SEN-���ڼӶ�</option> <option value=\"SYC\">SYC-������Ⱥ��</option> <option value=\"SLE\">SLE-��������</option> <option value=\"SGP\">SGP-�¼���</option> <option value=\"SVK\">SVK-˹�工��</option> <option value=\"SVN\">SVN-˹��������</option> <option value=\"SLB\">SLB-�����ŵ�</option> <option value=\"SOM\">SOM-������</option> <option value=\"ZAF\">ZAF-�Ϸ�</option> <option value=\"SGS\">SGS-��³����</option> <option value=\"LKA\">LKA-˹������</option> <option value=\"SDN\">SDN-�յ�</option> <option value=\"SDA\">SDA-�յ����չ�˾</option> <option value=\"SUR\">SUR-������</option> <option value=\"SJM\">SJM-�䰶�������Ⱥ��</option> <option value=\"SWZ\">SWZ-˹��ʿ��</option> <option value=\"SYR\">SYR-������</option> <option value=\"TJK\">TJK-������</option> <option value=\"TZA\">TZA-̹ɣ�������ϵĹ��͹�</option> <option value=\"THA\">THA-̩��</option> <option value=\"TGO\">TGO-���</option> <option value=\"TKL\">TKL-�п���</option> <option value=\"TON\">TON-����</option> <option value=\"TTO\">TTO-ǧ����б��繲�͹�</option> <option value=\"TUN\">TUN-���ǵĹ��͹�</option> <option value=\"TUR\">TUR-������</option> <option value=\"TKM\">TKM-������</option> <option value=\"TUV\">TUV-ͼ��¬</option> <option value=\"UGA\">UGA-�ڸɴ�</option> <option value=\"UKR\">UKR-�ڿ���</option> <option value=\"ARE\">ARE-����������������</option> <option value=\"URY\">URY-������</option> <option value=\"UZB\">UZB-���ȱ��˹̹</option> <option value=\"VUT\">VUT-���Ƕ�</option> <option value=\"VAT\">VAT-�����ͥ</option> <option value=\"VEN\">VEN-ί������</option> <option value=\"VNM\">VNM-Խ��</option> <option value=\"ESH\">ESH-��������</option> <option value=\"YEM\">YEM-Ҳ��</option> <option value=\"YUG\">YUG-��˹����</option> <option value=\"ZAR\">ZAR-������</option> <option value=\"ZMB\">ZMB-�ޱ���</option> <option value=\"ZWE\">ZWE-��Ͳ�Τ</option>";







