/**����ģ��jsУ�麯��*/


/**���Ķ��ŷ��񣭣�����ֿ�����Ϣ*/
function smsSubCheck(){
	//ִ��У�� 
	var field = new Array("card_id","id"); 
	var info = new Array("����","֤������"); 
 
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
	var fieldB = new Array("id_type"); 
	var infoB = new Array("֤������"); 
 
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
	
	//��Ϊ���֤�ţ���У��
  	var id_type = getSelectedButton(document.forms[0].id_type);  
  	if(id_type == 0){  	
		if(!isIDNO(document.forms[0].id)){
			document.forms[0].id.focus(); 
			return false;
		}
  	}
  	
  	//���ų���16��19λ
  	if(!(document.forms[0].card_id.value.length == 16 ||document.forms[0].card_id.value.length == 19)){
		alert("���ų���Ϊ16��19λ");
		document.forms[0].card_id.focus();
		return false;
	}
	if(document.forms[0].card_id.value.length ==16){
		document.forms[0].card_id.value = "000"+document.forms[0].card_id.value;
	}
	
	return true;
}


/**�������Ŷ��ģ�������ֿ�����Ϣ*/
function smsCanCheck(){

	//ִ��У�� 
	var field = new Array("card_id","id"); 
	var info = new Array("����","֤������"); 
 
	//���������Ϣ�Ƿ�Ϊ�� 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('������'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	
	 //ִ��У��   ��ѡ��ť
	var fieldB = new Array("id_type"); 
	var infoB = new Array("֤������"); 
 
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
	
	//��Ϊ���֤�ţ���У��
  	var id_type = getSelectedButton(document.forms[0].id_type);  
  	if(id_type == 0){  	
		if(!isIDNO(document.forms[0].id)){
			document.forms[0].id.focus(); 
			return false;
		}
  	}
  	
  	//���ų���16��19λ
  	if(!(document.forms[0].card_id.value.length == 16 ||document.forms[0].card_id.value.length == 19)){
		alert("���ų���Ϊ16��19λ");
		document.forms[0].card_id.focus();
		return false;
	}
	
	if(document.forms[0].card_id.value.length ==16){
		document.forms[0].card_id.value = "000"+document.forms[0].card_id.value;
	}
	
	return true;

}

function doBatchSearch(){
	if(!isEmpty(document.forms[0].card_id.value)){
	  	if(!(document.forms[0].card_id.value.length == 16 ||document.forms[0].card_id.value.length == 19)){
			alert("���ų���Ϊ16��19λ");
			document.forms[0].card_id.focus();
			return false;
		}
	}
	if(document.forms[0].card_id.value.length ==16){
		document.forms[0].card_id.value = "000"+document.forms[0].card_id.value;
	}
	
	if(!isEmpty(document.forms[0].id.value)){
		    var flag = 0;
		    for(var j=0;j<document.forms[0].id_type.length;j++){ 	
		      	if(document.forms[0].id_type[j].checked){
		        	flag = 1;
	       	 		break;
		        }
		    } 		        	
		    if(flag == 0){
		        alert('������֤������'); 
				document.forms[0].id_type[0].focus();
				return false; 
			}	     	

	}
	
	 	/*�������=======================================*/
	obj1=document.forms[0].open_date_begin;
	if(!isEmpty(obj1.value)){
		if(!checkdateNumber(obj1)){
			alert("��ʼ����ӦΪ8λ");
			obj1.focus();
			return false;
		}
		if(!checkDate(obj1)){
			obj1.focus();
			return false;
		}
	}
	obj2=document.forms[0].open_date_end;
	if(!isEmpty(obj2.value)){
		if(!checkdateNumber(obj2)){
			alert("��ֹ����ӦΪ8λ");
			obj2.focus();
			return false;
		}
		if(!checkDate(obj2)){
			obj2.focus();
			return false;
		}
	}
	if(!(isEmpty(obj1.value) || isEmpty(obj2.value))){
		if(obj1.value > obj2.value){
			alert("��ֹ����Ӧ������ʼ����");
			obj2.focus();
			return false;
		}
	}
	
	if(!(isEmpty(document.forms[0].ambs_crlim_low.value) || isEmpty(document.forms[0].ambs_crlim_high.value))){
		if(parseInt(document.forms[0].ambs_crlim_low.value,10) > parseInt(document.forms[0].ambs_crlim_high.value,10)){
			alert("��ȷ�Χ����");
			document.forms[0].ambs_crlim_low.focus();
			return false;
		}
	}
	
	return true;
}



function doRealSearch(){
	if(!isEmpty(document.forms[0].card_id.value)){
	  	if(!(document.forms[0].card_id.value.length == 16 ||document.forms[0].card_id.value.length == 19)){
			alert("���ų���Ϊ16��19λ");
			document.forms[0].card_id.focus();
			return false;
		}
	}
	if(document.forms[0].card_id.value.length ==16){
		document.forms[0].card_id.value = "000"+document.forms[0].card_id.value;
	}
	
	if(!isEmpty(document.forms[0].id.value)){
		    var flag = 0;
		    for(var j=0;j<document.forms[0].id_type.length;j++){ 	
		      	if(document.forms[0].id_type[j].checked){
		        	flag = 1;
	       	 		break;
		        }
		    } 		        	
		    if(flag == 0){
		        alert('������֤������'); 
				document.forms[0].id_type[0].focus();
				return false; 
			}	     	

	}
	return true;
}


