
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

function checkForm(){

	/**����ֶΣ����лس����򱨴�*/
	//var fielde = new Array("cust_name","last_name","first_name","pre_addr","comp_addr","cert_no","comp_name"); 
	var fielde = new Array("app_lmt","cust_name","last_name","first_name","nationality","birthday","fami_member","cert_no","pre_addr","pre_city_code","pre_post","pre_years","pre_zone_no","pre_phone","reg_post","per_income","ave_income","comp_name","comp_addr","comp_city_code","comp_post","comp_zone_no","comp_phone","work_years","lm_name","lm_comp","lm_comp_zoneno","lm_comp_phone","lm_pre_addr","lm_pre_zoneno","lm_pre_phone","app_source","exam_node_name"); 
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);			
	} 
	/**end*/
	
	//�������MsgError���˱����洢һϵ�еĴ�����ʾ�ַ�������Щ�����ǿ���ǿ��ͨ����
	var MsgError = "";
	
	//��ҳ����ʾ���������������δ����׼���Ƿ�ͬ��������ͨ������ѡ�������Ϊ������	 
	var card_kind = document.forms[0].card_kind.value;
	
	
	if(document.forms[0].card_single_code.value != "30"){//�ǹ���Ա��������У��
		if(card_kind == "0" || card_kind == "2"){
			tmpB = document.forms[0].normal_card.length; 
		    var flag = 0;
		    for(var j=0;j<tmpB;j++){ 	
		      	if(eval("document.forms[0].normal_card["+j+"].checked")){
		        	flag = 1;
		       	 	break;
		        }
		    } 		        	
		    if(flag == 0){
		        alert('�������������δ����׼���Ƿ�ͬ��������ͨ��'); 
				document.forms[0].normal_card[0].focus(); 
				return false; 
			}	
		}
	}
	
	
	//ִ��У��   �ı��� 
	
	var field = new Array("app_lmt","cust_name","last_name","first_name","nationality","birthday","fami_member","cert_no","pre_addr","pre_city_code","pre_post","pre_years","reg_post","per_income","ave_income","comp_name","comp_addr","comp_city_code","comp_post","work_years","lm_name","lm_comp","lm_comp_zoneno","lm_comp_phone","lm_pre_addr","app_source","exam_node_name"); 
	var info = new Array("��������","�ͻ�����","�ͻ���(ƴ��)","�ͻ���(ƴ��)","�ͻ�����","�ͻ���������","��ͥ�˿�","֤������","��סַ","��סַ���д���","��סַ�ʱ�","��סַ��ס����","������ַ�ʱ�","����������","��ͥ�˾�������","ѧУȫ��","ѧУ��ַ","ѧУ��ַ���д���","ѧУ�ʱ�","�ڶ��꼶","��ϵ������","��ϵ�˹�����λ","������λ�绰����","������λ�绰","��ϵ����סַ","�������Դ","������"); 
 
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
 	
	var fieldB = new Array("cust_sex","marr_stat","cert_type","edu_degr","lm_sex","exam_mode"); 
	var infoB = new Array("�ͻ��Ա�","����״��","֤������","�����̶�","��ϵ���Ա�","���鷽ʽ"); 
 
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
	obj=document.forms[0].app_lmt;
	if(!checkYForGet(obj)){
		alert("���������Ϊ��,����0��ͷ");
		obj.focus();
		return false;
	}
		
	//��������ܵ���10000Ԫ
	if(document.forms[0].card_kind.value == 0){
		if(parseInt(obj.value,10) < 10000){
			alert("��������������10000Ԫ!");
			obj.focus();
			return false;
		}	
	}
	//�տ�������ܵ���2000Ԫ
	if(document.forms[0].card_kind.value==1){
		if(parseInt(obj.value,10) < 2000){
			alert("�տ�������������2000Ԫ!");
			obj.focus();
			return false;
		}	
	}
	
	//�����ƴ������Ϊ8,��ƴ������Ϊ16
	if(document.forms[0].last_name.value.length > 10){
		alert("��ƴ���������Ϊ10λ");
		document.forms[0].last_name.focus();
		return false;
	}
	if(document.forms[0].first_name.value.length > 16){
		alert("��ƴ���������Ϊ16λ");
		document.forms[0].first_name.focus();
		return false;
	}
	if(!isLetter(document.forms[0].last_name.value)){
		alert("��ƴ��ӦΪ��дӢ����ĸ");
		document.forms[0].last_name.focus();
		return false;
	}
	if(!isLetter(document.forms[0].first_name.value)){
		alert("��ƴ��ӦΪ��дӢ����ĸ");
		document.forms[0].first_name.focus();
		return false;
	}
	
	
	
	/*����������=======================================*/
	obj=document.forms[0].birthday;
	if(!checkdateNumber(obj)){
		alert("�����˳�������ӦΪ8λ");
		obj.focus();
		return false;
	}
	if(!checkDate(obj)){
		obj.focus();
		return false;
	}
	
	/*�������������====================================*/	
    //����Ҫ����18��99����֮��
    currentDate = new Date();
    currentYear = parseInt(currentDate.getFullYear());
    
    strBirthday = document.forms[0].birthday.value;
    intAge = currentYear - parseInt(strBirthday.substring(0,4));
    if(intAge < 18){
		alert("���䲻���Ϸ���Ҫ��");
		document.forms[0].birthday.focus();
		return false;
    }
    if(intAge > 99){
		alert("���䲻���Ϸ���Ҫ��");
		document.forms[0].birthday.focus();
		return false;
    }
    
    //��ס�����޲��ô�������������
    var dwellspend = document.forms[0].pre_years.value;
    if(intAge < dwellspend){
    	alert("��ס�����޲��ô�������������");
    	document.forms[0].pre_years.focus();
    	return false;
    }
   	
   	//�ֵ�λ�������޲��ܴ���(����-15),ֻ����ʾ,��ǿ��ͨ��
    var workyears = document.forms[0].work_years.value;
    if(workyears <=0){
    	alert("�ڶ��꼶����");
    	document.forms[0].work_years.focus();
    	return false;   	
    }
    
    if((intAge-15) < workyears){
    	//alert("������鹤������");
    	if(MsgError == ""){
			MsgError = MsgError + "��������ڶ��꼶";
		}
		else{
			MsgError = MsgError + "\n��������ڶ��꼶";
		}
    }
    
    /*���֤������=========================================*/
   if(getSelectedButton(document.forms[0].cert_type)== 0){
    	obj = document.forms[0].cert_no;
    	if(!isIDNO(obj)){
    		document.forms[0].cert_no.focus();	
    		return false;
    	}  			
	}
   
    
    /*����������=========================================*/
    if(!isEmpty(document.forms[0].email.value)){
	    if(!isEmail(document.forms[0].email)){
	    	alert("�����������");
	    	document.forms[0].email.focus();
	    	return false;
	    }
	    if(!ChineseIn(document.forms[0].email.value)){
	    	alert("���������в��Ϸ��ַ�");
	    	document.forms[0].email.focus();
	    	return false;	
	    }
    }
    
    /*����ʱ�λ��=======================================*/
	var field = new Array("pre_post","reg_post","comp_post"); 
	var info = new Array("��סַ�ʱ�","������ַ�ʱ�","ѧУ�ʱ�"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'ӦΪ��λ'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	} 
	
	/*����ֻ���=======================================*/
	var field = new Array("pre_mobile","lm_mobile"); 
	var info = new Array("�������ֻ���","��ϵ���ֻ���"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		if(!isEmpty(eval(tmp))){
			if(!isMobile(eval(tmp))){
				alert(info[i]+'��������');
				eval("document.forms[0]."+field[i]+".focus()");
				return false;
			}
			//if(parseInt(trim(eval(tmp)))==0){
			if(isZero(trim(eval(tmp)))){
				alert(info[i]+'����ȫ����0');
				eval("document.forms[0]."+field[i]+".focus()");
				return false;
			}
			tmp1 ="document.forms[0]."+field[i]+".value.length"; 
		    if((eval(tmp1)) < 11) { 
		        alert(info[i]+'ӦΪ11λ����'); 
		        eval("document.forms[0]."+field[i]+".focus()"); 
		        return false; 
		    } 
	    }
	}
	
	
	//ѡ��֤������,��Ϊ����֤,���;��Ϊ����,��"��ע��"Ϊ������
	var cert_type = getSelectedButton(document.forms[0].cert_type);
	if(cert_type == 0){
		//��������յ�һ����
		str = document.forms[0].cert_no.value;
		str2 = document.forms[0].birthday.value;
		if(str.length==15){
			strb=str.substring(6,12);
			str3=str2.substring(2,8);
			if(strb!=str3){
				alert("����֤������������ڲ���!");
				document.forms[0].cert_no.focus();
				return false;
			}
			var strsex=str.charAt(14);
		}
		if(str.length==18){
			strb=str.substring(6,14);
			if(strb!=str2) {
				alert("����֤������������ڲ���!");
				document.forms[0].cert_no.focus();
				return false;
			}
			var strsex=str.charAt(16);
		}
		//�Ա�ͺ����һ����
		if(strsex % 2==0) strsex="F";
		else strsex="M";
		obj = document.forms[0].cust_sex
		if(!((obj[0].checked && strsex=="M")||(obj[1].checked && strsex=="F"))){
			alert("����֤�������Ա𲻷�!!");
			document.forms[0].cert_no.focus();
			return false;
		}
	    
	    //���У����
		if(str.length==18){
			//У����
			var strjiaoyan=new Array("1","0","X","9","8","7","6","5","4","3","2");
			//��Ȩ����
			var intQuan=new Array("7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2","1");
			var sum=0;
			for(var inum=0;inum<17;inum++)	sum+=str.substring(inum,inum+1) * intQuan[inum];
			var strresult=strjiaoyan[sum % 11];
			if(str.substring(17,18).toUpperCase()!=strresult) {
				alert("����֤����У���벻��ȷ!");
				document.forms[0].cert_no.focus();
				return false;
			}
		}
	}
	
	//��֤������Ϊ����
	if(cert_type == 1){
		if(isEmpty(document.forms[0].cert_name.value)){
			alert("����������֤������");
			document.forms[0].cert_name.focus();
			return false;
		}
	}
	//��֤�����Ͳ�Ϊ"����",��"��ע��"�ı���Ϊ��,����ֵ
	if(cert_type != 1){
		if(!isEmpty(document.forms[0].cert_name.value)){
			alert("֤�����ͳ�ͻ");
			document.forms[0].cert_name.focus();
			return false;
		}
	}
	


	
	//���Լ������������Ϣ,����ű���Ϊ19λ����
	var repay_card = document.forms[0].repay_card;
	if(!isEmpty(repay_card.value)){
		if(!(repay_card.value.length == 19 || repay_card.value.length == 16)){
			alert("����Ϊ16λ��19λ����");
			repay_card.focus();
			return false;
		}
	}
	
	//���Լ������������Ϣ;��ʼ,��ֹ����
	var start_date = document.forms[0].start_date;
	var expire_date = document.forms[0].expire_date;
	if(!isEmpty(start_date.value)){    //�Ƿ���������ʽ(yyyymmdd)
		if(!checkdateNumber(start_date)){
			alert("��ʼ����ӦΪ8λ");
			start_date.focus();
			return false;
		}
		if(!checkDate(start_date)){
			start_date.focus();
			return false;
		}
	}
	if(!isEmpty(expire_date.value)){
		if(!checkdateNumber(expire_date)){
			alert("��ֹ����ӦΪ8λ");
			expire_date.focus();
			return false;
		}
		if(!checkDate(expire_date)){
			expire_date.focus();
			return false;
		}
	}
	if(!(isEmpty(start_date.value) || isEmpty(expire_date.value))){//��ʼ����С����ֹ����
		if(expire_date.value <= start_date.value){
			alert("��ֹ����Ӧ������ʼ����");
			expire_date.focus();
			return false;
		}	
	}	
	
	//У��Ԥ����Ϣ,����д������,��𰸱���;����д�˴�,���������
	var ques = document.forms[0].remain_ques;
	var ans = document.forms[0].remain_ans;
	if((!isEmpty(ques.value)) == isEmpty(ans.value)){
		alert("�뽫Ԥ����Ϣ��������");
		ques.focus();
		return false;
	}
	
	//��������У���Ƿ�Ϊ�Ϸ���С��
	var app_lmt = document.forms[0].app_lmt;
	if(!IsDecimal(app_lmt.value)){
		alert("�������Ȳ��Ϸ�");
		app_lmt.focus();
		return false;
	}



	
	/**Ӧ�Ҫ��У��v�����ܳ��ֵĴ���  begin*/
	var fieldv = new Array("cust_name","cert_no","pre_addr","pre_phone","comp_name","comp_addr","comp_phone","remain_ques","remain_ans","lm_name","lm_pre_phone","reg_addr","car_no","car_type","lm_comp_phone","lm_pre_addr","cert_name"); 
	var infov = new Array("����(����)","֤������","��סַ","��סַ�绰����","ѧУȫ��","ѧУ��ַ","ѧУ�绰����","Ԥ������","Ԥ����","��ϵ������(����)","��ϵ����סַ�绰����","������ַ","���ƺ�","��Ʒ��","��ϵ�˵�λ�绰����","��ϵ����סַ","����֤������"); 
 	var fvaluev = new Array(40,20,80,17,40,80,17,20,20,20,17,80,20,40,17,80,19); 
 
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
	
	/**У�������������������1000.00��������1000.21  begin*/
	var fieldv = new Array("app_lmt"); 
	var infov = new Array("��������"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	var tmp; 
	for(var i=0;i<fieldv.length;i++)	{ 
	 	tmp ="document.forms[0]."+fieldv[i]+".value"; 
	    if(!isEmpty(eval(tmp))) {
	    	if( !isZ(eval(tmp))){
	    	 	alert(infov[i]+'���벻�Ϸ�(ӦΪ2000.00)'); 
			    eval("document.forms[0]."+fieldv[i]+".focus()"); 
			    return false; 
		    }
	     } 
	}
	/**end*/ 
	

	
	/**У��Լ�����1��������Ų�Ϊ�գ��򻹿ʽΪ������
	*/
	if(!isEmpty(document.forms[0].repay_card.value)){

		if((document.forms[0].repay_mark.value == "")){
			alert("��ѡ�񻹿ʽ��");
			document.forms[0].repay_mark.focus();
			return false;
		}
	}
	/**end*/
	
	/**У�飺��ͥ�˿ڴ���0����סַ���޴���0*/
	var jtrk = document.forms[0].fami_member.value;
    if(jtrk <=0){
    	alert("��ͥ�˿ڲ���Ϊ0");
    	document.forms[0].fami_member.focus();
    	return false;   	
    }
    var xzznx = document.forms[0].pre_years.value;
    if(xzznx <=0){
    	alert("��סַ���޲���Ϊ0");
    	document.forms[0].pre_years.focus();
    	return false;   	
    }
	/**end*/
		
	

	
	//֤������18λ��15λʱ��֤������ӦΪ����֤����ʱ����֤�����������ϱ�׼�ģ�
	//ϵͳ��ʾ��֤�����ʹ��󡱣�������ǿ��ͨ����
	str = document.forms[0].cert_no.value;
	if((str.length==15) || (str.length==18)){
		if(getSelectedButton(document.forms[0].cert_type) != 0){		
		//alert("֤�����ʹ���!");
			if(MsgError == ""){
				MsgError = MsgError + "֤�����ʹ���";
			}
			else{
				MsgError = MsgError + "\n֤�����ʹ���";
			}
		}
	} 
	
	/*
	 * ͬһ�����������У�����סַ���д����뵥λ���д�����ͬ��
	 * ��סַ�绰�����뵥λ�绰���š���סַ�绰����λ���뵥λ�̶��绰����λ����
	 * ��סַ�ʱ�ǰ��λ�뵥λ�ʱ�ǰ��λӦ�ñ���һ�£��粻һ�£�
	 * ϵͳ�ֱ�������ʾ������סַ�绰���Ż�λ�绰���Ŵ��󡱣�
	 * ����סַ�绰λ���뵥λ�绰λ����һ�¡���
	 * ����סַ�ʱ�ǰ��λ�뵥λ�ʱ�ǰ��λ��һ�¡���������ǿ��ͨ����
	 *
	 * ע�⣺����סַ�绰λ���뵥λ�绰λ����һ�¡���У��û��д
	 */
	if(document.forms[0].pre_city_code.value == document.forms[0].comp_city_code.value){
		if((document.forms[0].pre_zone_no.value) != document.forms[0].comp_zone_no.value){
			//alert("��סַ�绰���Ż�λ�绰���Ŵ���");
			if(MsgError == ""){
				MsgError = MsgError + "��סַ�绰���Ż�ѧУ�绰���Ŵ���";}
			else{
				MsgError = MsgError + "\n��סַ�绰���Ż�ѧУ�绰���Ŵ���";}
		}
		if((document.forms[0].pre_post.value).substring(0,3) != (document.forms[0].comp_post.value).substring(0,3)){
			//alert("��סַ�ʱ�ǰ��λ�뵥λ�ʱ�ǰ��λ��һ��");
			if(MsgError == ""){
				MsgError = MsgError + "��סַ�ʱ�ǰ��λ��ѧУ�ʱ�ǰ��λ��һ��";}
			else{
				MsgError = MsgError + "\n��סַ�ʱ�ǰ��λ��ѧУ�ʱ�ǰ��λ��һ��";}
		}
	}
	if(MsgError != ""){//��MsgError��Ϊ�գ��򱨴�
		alert(MsgError);
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

//�������֤����
function isIDNO(obj){
	var s = trim(obj.value);
	if (!((s.length==18) || (s.length==15))){
		//alert(s.length);
		alert("����֤���볤��ֻ��Ϊ15��18λ");
		
		return false;
	}
	else if (s.length==15){
		if (!warnCharsInBag(s, "0123456789")){
			alert("����֤���볤Ϊ15λʱ,����ȫ��������");
			return false;
		}
	}
	else if (obj.value.length==18){
		if (!isNumber(s)){
			var sub=s.substring(1,17)
			if (!warnCharsInBag(sub, "0123456789")){
				alert("����֤���볤Ϊ18λʱ,ǰ17λ����ȫ��������");
				return false;
			}
			if(!warnCharsInBag(s, "0123456789")){							    
				if ((s.charAt(17)!="x") && (s.charAt(17)!="X")){
					alert("����֤����ֻ�������һλ���������ַ�X��x");
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
   //�������ַ�
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

//���s�Ƿ�Ϊ����
//1000��100.00��������1000.23
function isZ(s)
{
	var sLength = s.length;		//��У���ַ����ĳ���
		
	//���С��������λ
	var i = 0;
	for(;i<sLength;i++){
		if(s.charAt(i) == "."){
			break;
		}
	}
	if(i == sLength - 1){//���ַ���û��С����
		return true;
	}
	for(var j=i+1;j<sLength;j++){//���ַ�����С����
		if(s.charAt(j) != "0"){
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

//�����ָ�ֵ
function mainCardNationality(selObj){ //v3.0
	var selectedIndex = getSelectedButton(document.forms[0].select);
 	document.forms[0].nationality.value=selObj.options[selObj.selectedIndex].value;
  
}

var country_code;
country_code="<option value=\"\">--��ѡ�����--</option><option value=\"CHN\">CHN-�й�</option> <option value=\"HKG\">HKG-���</option> <option value=\"MAC\">MAC-����</option> <option value=\"TWN\">TWN-�й�̨��</option> <option value=\"USA\">USA-����</option> <option value=\"GBR\">GBR-Ӣ��</option> <option value=\"FRA\">FRA-����</option> <option value=\"DEU\">DEU-�¹�</option> <option value=\"ITA\">ITA-�����</option> <option value=\"GRC\">GRC-ϣ��</option> <option value=\"RUS\">RUS-����˹����</option> <option value=\"ESP\">ESP-������</option> <option value=\"PRT\">PRT-������</option> <option value=\"SWE\">SWE-���</option> <option value=\"CHE\">CHE-��ʿ</option> <option value=\"PRK\">PRK-����</option> <option value=\"KOR\">KOR-����</option> <option value=\"IND\">IND-ӡ��</option> <option value=\"AUS\">AUS-�Ĵ�����</option> <option value=\"ISR\">ISR-��ɫ��</option> <option value=\"JPN\">JPN-�ձ�</option> <option value=\"BRA\">BRA-����</option> <option value=\"AFG\">AFG-������</option> <option value=\"ALB\">ALB-����������</option> <option value=\"ADZ\">ADZ-����������</option> <option value=\"ASM\">ASM-������Ħ��Ⱥ��</option> <option value=\"AND\">AND-������</option> <option value=\"AGO\">AGO-������</option> <option value=\"AIA\">AIA-������</option> <option value=\"ATG\">ATG-����ϼ��Ͳ���</option> <option value=\"ARG\">ARG-����͢</option> <option value=\"ARM\">ARM-��������</option> <option value=\"ABW\">ABW-��¬��</option> <option value=\"AUT\">AUT-�µ���</option> <option value=\"AZE\">AZE-�����ݽ�</option> <option value=\"BHS\">BHS-�͹���</option> <option value=\"BHR\">BHR-����</option> <option value=\"BGD\">BGD-�ϼ���</option> <option value=\"BRB\">BRB-�ͰͶ�˹</option> <option value=\"BLR\">BLR-�׶���˹</option> <option value=\"BEL\">BEL-����ʱ</option> <option value=\"BLZ\">BLZ-������</option> <option value=\"BEN\">BEN-������</option> <option value=\"BMU\">BMU-��Ľ��Ⱥ��</option> <option value=\"BTN\">BTN-����</option> <option value=\"BOL\">BOL-����ά��</option> <option value=\"BIH\">BIH-��ʿ���Ǻ�����ά������</option> <option value=\"BWA\">BWA-��������</option> <option value=\"BRN\">BRN-����</option> <option value=\"BGR\">BGR-��������</option> <option value=\"BFA\">BFA-�����ɷ���</option> <option value=\"BDI\">BDI-��¡��</option> <option value=\"KHM\">KHM-����</option> <option value=\"CMR\">CMR-����¡���ϵĹ��͹�</option> <option value=\"CAN\">CAN-���ô�</option> <option value=\"TCD\">TCD-է��</option> <option value=\"CHL\">CHL-����</option> <option value=\"COL\">COL-���ױ���</option> <option value=\"COM\">COM-��Ħ��</option> <option value=\"COG\">COG-�չ�</option> <option value=\"COK\">COK-���Ⱥ��</option> <option value=\"CRI\">CRI-��˹�����</option> <option value=\"HRV\">HRV-���ް�����</option> <option value=\"CUB\">CUB-�Ű�</option> <option value=\"CYP\">CYP-������˹</option> <option value=\"CZE\">CZE-�ݿ�</option> <option value=\"DNK\">DNK-����</option> <option value=\"DJI\">DJI-������</option> <option value=\"DMA\">DMA-�����������</option> <option value=\"DOM\">DOM-������ӹ��͹�</option> <option value=\"TMP\">TMP-������</option> <option value=\"ECU\">ECU-��϶��</option> <option value=\"EGY\">EGY-����</option> <option value=\"SLV\">SLV-�����߶�</option> <option value=\"GNQ\">GNQ-���������</option> <option value=\"ERI\">ERI-��������</option> <option value=\"EST\">EST-��ɳ����</option> <option value=\"ETH\">ETH-����������</option> <option value=\"FJI\">FJI-쳼�</option> <option value=\"FIN\">FIN-����</option> <option value=\"GAB\">GAB-����</option> <option value=\"GMB\">GMB-�Ա���</option> <option value=\"GEO\">GEO-��������</option> <option value=\"GHA\">GHA-����</option> <option value=\"GIB\">GIB-ֱ������</option> <option value=\"GRL\">GRL-������</option> <option value=\"GRD\">GRD-�����ɴ�</option> <option value=\"GLP\">GLP-������嵺</option> <option value=\"GUM\">GUM-�ص�</option> <option value=\"GTM\">GTM-Σ������</option> <option value=\"GIN\">GIN-������</option> <option value=\"GNB\">GNB-�����Ǳ��ܹ��͹�</option> <option value=\"GUY\">GUY-������</option> <option value=\"HTI\">HTI-����</option> <option value=\"HMD\">HMD-HEARD AND MCDONALD IS</option> <option value=\"HND\">HND-�鶼��˹</option> <option value=\"HUN\">HUN-������</option> <option value=\"ISL\">ISL-����</option> <option value=\"IDN\">IDN-ӡ��</option> <option value=\"IRN\">IRN-��������</option> <option value=\"IRQ\">IRQ-������</option> <option value=\"IRL\">IRL-������</option> <option value=\"CIV\">CIV-��������</option> <option value=\"JAM\">JAM-�����</option> <option value=\"JOR\">JOR-Լ��</option> <option value=\"KAZ\">KAZ-������</option> <option value=\"KEN\">KEN-������</option> <option value=\"KIR\">KIR-������˹</option> <option value=\"KWT\">KWT-������</option> <option value=\"KGZ\">KGZ-������˹</option> <option value=\"LAO\">LAO-����</option> <option value=\"LVA\">LVA-����ά��</option> <option value=\"LBN\">LBN-�����</option> <option value=\"LSO\">LSO-������</option> <option value=\"LBR\">LBR-��������</option> <option value=\"LBY\">LBY-������</option> <option value=\"LIE\">LIE-��֧��ʿ��</option> <option value=\"LTU\">LTU-������</option> <option value=\"LUX\">LUX-¬ɭ��</option> <option value=\"MKD\">MKD-�����</option> <option value=\"MDG\">MDG-�����˹��</option> <option value=\"MWI\">MWI-����ά</option> <option value=\"MYS\">MYS-��������</option> <option value=\"MDV\">MDV-��������</option> <option value=\"MLI\">MLI-����</option> <option value=\"MLT\">MLT-������</option> <option value=\"MHL\">MHL-��Ъ����</option> <option value=\"MTQ\">MTQ-������˵�</option> <option value=\"MRT\">MRT-ë��������</option> <option value=\"MUS\">MUS-ë����˹</option> <option value=\"MYT\">MYT-��Լ��</option> <option value=\"MEX\">MEX-ī����</option> <option value=\"FSM\">FSM-�ܿ���������</option> <option value=\"MDA\">MDA-Ħ�����߹��͹�</option> <option value=\"MCO\">MCO-Ħ�ɸ�</option> <option value=\"MNG\">MNG-�ɹ���</option> <option value=\"MSR\">MSR-����ɫ��</option> <option value=\"MAR\">MAR-Ħ���</option> <option value=\"MOZ\">MOZ-Īɣ�ȿ�</option> <option value=\"MMR\">MMR-���</option> <option value=\"NAM\">NAM-���ױ���</option> <option value=\"NRU\">NRU-�³</option> <option value=\"NPL\">NPL-�Ჴ��</option> <option value=\"NLD\">NLD-����</option> <option value=\"ANT\">ANT-����������˹Ⱥ��</option> <option value=\"NCL\">NCL-�¼��ն�����</option> <option value=\"NZL\">NZL-������</option> <option value=\"NIC\">NIC-�������</option> <option value=\"NER\">NER-���ն�</option> <option value=\"NGA\">NGA-��������</option> <option value=\"NIU\">NIU-Ŧ����</option> <option value=\"NFK\">NFK-ŵ���˵�</option> <option value=\"MNP\">MNP-������������Ⱥ��</option> <option value=\"NOR\">NOR-Ų��</option> <option value=\"OMN\">OMN-����</option> <option value=\"PAK\">PAK-�ͻ�˹̹</option> <option value=\"PLW\">PLW-����</option> <option value=\"PAN\">PAN-������</option> <option value=\"PNG\">PNG-�Ͳ����¼�����</option> <option value=\"PRY\">PRY-������</option> <option value=\"PER\">PER-��³</option> <option value=\"PHL\">PHL-���ɱ����͹�</option> <option value=\"PCN\">PCN-Ƥ�ؿ�����</option> <option value=\"POL\">POL-����</option> <option value=\"PRI\">PRI-�������</option> <option value=\"QAT\">QAT-������</option> <option value=\"REU\">REU-��Բ</option> <option value=\"ROM\">ROM-��������</option> <option value=\"RWA\">RWA-¬����</option> <option value=\"WSM\">WSM-������Ħ��</option> <option value=\"SMR\">SMR-ʥ����ŵ</option> <option value=\"STP\">STP-ʥ��������������</option> <option value=\"SAU\">SAU-ɳ�ذ�����</option> <option value=\"SEN\">SEN-���ڼӶ�</option> <option value=\"SYC\">SYC-������Ⱥ��</option> <option value=\"SLE\">SLE-��������</option> <option value=\"SGP\">SGP-�¼���</option> <option value=\"SVK\">SVK-˹�工��</option> <option value=\"SVN\">SVN-˹��������</option> <option value=\"SLB\">SLB-�����ŵ�</option> <option value=\"SOM\">SOM-������</option> <option value=\"ZAF\">ZAF-�Ϸ�</option> <option value=\"SGS\">SGS-��³����</option> <option value=\"LKA\">LKA-˹������</option> <option value=\"SDN\">SDN-�յ�</option> <option value=\"SDA\">SDA-�յ����չ�˾</option> <option value=\"SUR\">SUR-������</option> <option value=\"SJM\">SJM-�䰶�������Ⱥ��</option> <option value=\"SWZ\">SWZ-˹��ʿ��</option> <option value=\"SYR\">SYR-������</option> <option value=\"TJK\">TJK-������</option> <option value=\"TZA\">TZA-̹ɣ�������ϵĹ��͹�</option> <option value=\"THA\">THA-̩��</option> <option value=\"TGO\">TGO-���</option> <option value=\"TKL\">TKL-�п���</option> <option value=\"TON\">TON-����</option> <option value=\"TTO\">TTO-ǧ����б��繲�͹�</option> <option value=\"TUN\">TUN-���ǵĹ��͹�</option> <option value=\"TUR\">TUR-������</option> <option value=\"TKM\">TKM-������</option> <option value=\"TUV\">TUV-ͼ��¬</option> <option value=\"UGA\">UGA-�ڸɴ�</option> <option value=\"UKR\">UKR-�ڿ���</option> <option value=\"ARE\">ARE-����������������</option> <option value=\"URY\">URY-������</option> <option value=\"UZB\">UZB-���ȱ��˹̹</option> <option value=\"VUT\">VUT-���Ƕ�</option> <option value=\"VAT\">VAT-������ͥ</option> <option value=\"VEN\">VEN-ί������</option> <option value=\"VNM\">VNM-Խ��</option> <option value=\"ESH\">ESH-��������</option> <option value=\"YEM\">YEM-Ҳ��</option> <option value=\"YUG\">YUG-��˹����</option> <option value=\"ZAR\">ZAR-������</option> <option value=\"ZMB\">ZMB-�ޱ���</option> <option value=\"ZWE\">ZWE-��Ͳ�Τ</option>";






