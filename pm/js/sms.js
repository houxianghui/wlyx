/**短信模块js校验函数*/


/**订阅短信服务－－输入持卡人信息*/
function smsSubCheck(){
	//执行校验 
	var field = new Array("card_id","id"); 
	var info = new Array("卡号","证件号码"); 
 
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
	var fieldB = new Array("id_type"); 
	var infoB = new Array("证件类型"); 
 
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
	
	//若为身份证号，则校验
  	var id_type = getSelectedButton(document.forms[0].id_type);  
  	if(id_type == 0){  	
		if(!isIDNO(document.forms[0].id)){
			document.forms[0].id.focus(); 
			return false;
		}
  	}
  	
  	//卡号长度16或19位
  	if(!(document.forms[0].card_id.value.length == 16 ||document.forms[0].card_id.value.length == 19)){
		alert("卡号长度为16或19位");
		document.forms[0].card_id.focus();
		return false;
	}
	if(document.forms[0].card_id.value.length ==16){
		document.forms[0].card_id.value = "000"+document.forms[0].card_id.value;
	}
	
	return true;
}


/**撤销短信订阅－－输入持卡人信息*/
function smsCanCheck(){

	//执行校验 
	var field = new Array("card_id","id"); 
	var info = new Array("卡号","证件号码"); 
 
	//检察输入信息是否为空 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
	        tmp ="document.forms[0]."+field[i]+".value"; 
	        if(isEmpty(eval(tmp))) { 
	        	alert('请输入'+info[i]); 
	        	eval("document.forms[0]."+field[i]+".focus()"); 
	        	return; 
	        } 
	} 
	
	 //执行校验   单选按钮
	var fieldB = new Array("id_type"); 
	var infoB = new Array("证件类型"); 
 
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
	
	//若为身份证号，则校验
  	var id_type = getSelectedButton(document.forms[0].id_type);  
  	if(id_type == 0){  	
		if(!isIDNO(document.forms[0].id)){
			document.forms[0].id.focus(); 
			return false;
		}
  	}
  	
  	//卡号长度16或19位
  	if(!(document.forms[0].card_id.value.length == 16 ||document.forms[0].card_id.value.length == 19)){
		alert("卡号长度为16或19位");
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
			alert("卡号长度为16或19位");
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
		        alert('请输入证件类型'); 
				document.forms[0].id_type[0].focus();
				return false; 
			}	     	

	}
	
	 	/*检查日期=======================================*/
	obj1=document.forms[0].open_date_begin;
	if(!isEmpty(obj1.value)){
		if(!checkdateNumber(obj1)){
			alert("起始日期应为8位");
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
			alert("截止日期应为8位");
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
			alert("截止日期应大于起始日期");
			obj2.focus();
			return false;
		}
	}
	
	if(!(isEmpty(document.forms[0].ambs_crlim_low.value) || isEmpty(document.forms[0].ambs_crlim_high.value))){
		if(parseInt(document.forms[0].ambs_crlim_low.value,10) > parseInt(document.forms[0].ambs_crlim_high.value,10)){
			alert("额度范围有误");
			document.forms[0].ambs_crlim_low.focus();
			return false;
		}
	}
	
	return true;
}



function doRealSearch(){
	if(!isEmpty(document.forms[0].card_id.value)){
	  	if(!(document.forms[0].card_id.value.length == 16 ||document.forms[0].card_id.value.length == 19)){
			alert("卡号长度为16或19位");
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
		        alert('请输入证件类型'); 
				document.forms[0].id_type[0].focus();
				return false; 
			}	     	

	}
	return true;
}


