


//����Ƿ�����������,Ҳ������С����
function checkDouble(){
	if ((event.keyCode > 47 && event.keyCode < 58) || event.keyCode == 46) {
		event.returnValue = true;
	}
	else
		event.returnValue = false;
}
/**
 *checknew��,����Ա�ύ������Ϣ
 */
function checkNewSub(){          
	//���鸴��Ա�����
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('�������������'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 3){//�ܾ����
		if(isEmpty(oper_sug_lmt.value)){
			alert("�����뽨����");
			oper_sug_lmt.focus();
			return false;
		}
		
		//add by yly��������У��
		if (!checkSugLmt(oper_sug_lmt)){
		
			return false;
		}

	}else{
		oper_sug_lmt.value = 0;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//����ʾ���������,�������������
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("����д�������");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("����д����˵��");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("����˵������100����");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	
	//����ͬ�⽵�����򸴺�Ա����ѡ�񽵵����
	
		if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 2){
			alert("����Ա�������(�ͻ���ͬ�⽵������)");
			document.forms[0].oper_sug_kind[0].focus(); 
			return false; 
		}
		
	
	

	return true;
}

function checkNewSubST2(){          
	//���鸴��Ա�����
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('�������������'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 3){//�ܾ����
		if(isEmpty(oper_sug_lmt.value)){
			alert("�����뽨����");
			oper_sug_lmt.focus();
			return false;
		}
	//add by yly��������У��
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}	 
			
	}else{
		oper_sug_lmt.value = 0;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//����ʾ���������,�������������
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("����д�������");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("����д����˵��");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("����˵������100����");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	
	//������𿨣��Ҳ�ͬ�⽵�����򸴺�Ա����ѡ�񽵵����
	
		if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 2){
			alert("����Ա�������(�ͻ���ͬ�⽵������)");
			document.forms[0].oper_sug_kind[0].focus(); 
			return false; 
		}

	
	
	return true;
}

/**
 *checknew��,����Ա�˻�������Ϣ
 */
function checkNewHandback(){ 
	document.forms[0].oper_sug_kind.value = '-';
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	if(oper_sug_lmt.value.length != 0){
	
		//add by yly��������ӦΪ����
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}

	}
	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{
		if(isEmpty(document.forms[0].return_rea_credit.value)&&isEmpty(document.forms[0].return_rea_judge.value)){
			alert("����д�˻�ԭ��");
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	return true;
}
/**
 *checknew��,����Ա�˻�������Ϣ
 */
function checkNewHandbackBus(){ 
	
	document.forms[0].oper_sug_kind.value = '-';
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	if(oper_sug_lmt.value.length != 0){
		if(!IsDecimal(oper_sug_lmt.value)){
			alert("���������벻�Ϸ�");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value > 300){
			alert("�����Ȳ��ܳ���300��Ԫ");
			oper_sug_lmt.focus();
			return false;
		}
	}
	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{
		if(isEmpty(document.forms[0].return_rea.value)){
			alert("����д�˻�ԭ��");
			document.forms[0].return_rea.focus();
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}

	return true;
}
/**
 *checknewSAttach��,����Ա�˻�������Ϣ
 */
function checkNewSAttachHandback(){ 
	//���鸴��Ա�����

	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{
		if(isEmpty(document.forms[0].return_rea.value)){
			alert("����д�˻�ԭ��");
			document.forms[0].return_rea.focus();
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}

	return true;
}

/**
 *checknewSAttach��,����Ա�ύ������Ϣ
 */
function checkNewSAttachSub(){ 
	//���鸴��Ա�����
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//����ʾ���������,�������������
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("����д�������");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("����д����˵��");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("����˵������100����");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	return true;
}

/**
 *abcChecknewSAttach��,���и���Ա�˻�������Ϣ
 */
function abcCheckNewSAttachHandback(){ 
	//���鸴��Ա�����
	if(isEmpty(document.forms[0].return_rea.value)){
		alert("����д�˻�ԭ��");
		document.forms[0].return_rea.focus();
		return false;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}

	return true;
}

/**
 *abcchecknew��,���и���Ա�˻�������Ϣ
 */
function abcCheckNewHandback(){ 
	
	document.forms[0].oper_sug_kind.value = '-';
	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{	
		if(isEmpty(document.forms[0].return_rea_credit.value)&&isEmpty(document.forms[0].return_rea_judge.value)){
			alert("����д�˻�ԭ��");
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	return true;
}

/**
 *abcchecknew��,���и���Ա�ύ������Ϣ�����ڸ��˿��������и���
 */
function abcCheckNewSub(){ 
	//���鸴��Ա�����
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('�������������'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}

	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	//���˿����
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//�ܾ����
		if(isEmpty(oper_sug_lmt.value)){
			alert("�����뽨����");
			oper_sug_lmt.focus();
			return false;
		}
		
		//add by yly�������ȼ��
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}
	
	
	}else{
		oper_sug_lmt.value = 0;
	}
	
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	
	//����ͬ�⽵�����򸴺�Ա����ѡ�񽵵����
	if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 1){
		alert("����Ա�������(�ͻ���ͬ�⽵������)");
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
		
	
	
	return true;
}

/**
 *abcchecknew��,���и���Ա�ύ������Ϣ�����ڸ��˿��������и���
 */
function abcCheckNewSubST2(){ 
	//���鸴��Ա�����
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('�������������'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}

	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	//���˿����
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//�ܾ����
		if(isEmpty(oper_sug_lmt.value)){
			alert("�����뽨����");
			oper_sug_lmt.focus();
			return false;
		}

		//add by yly�������ȼ��
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}

	
	}else{
		oper_sug_lmt.value = 0;
	}
	
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	
	//����ͬ�⽵�����򸴺�Ա����ѡ�񽵵����

		if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 1){
			alert("����Ա�������(�ͻ���ͬ�⽵������)");
			document.forms[0].oper_sug_kind[0].focus(); 
			return false; 
		}
		
		
	
	
	return true;
}


/**
 *abcchecknew��,���и���Ա�ύ������Ϣ���������񿨵�λ�������и���
 */
function abcCheckNewSubBus(){ 
	//���鸴��Ա�����
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('�������������'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}

	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	//�������		
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//�ܾ����
		if(isEmpty(oper_sug_lmt.value)){
			alert("�����뽨����");
			oper_sug_lmt.focus();
			return false;
		}
		if(!IsDecimal(oper_sug_lmt.value)){
			alert("���������벻�Ϸ�");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value > 500){
			alert("�����Ȳ��ܳ���500��Ԫ");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value < 1){
			alert("���ǿ��������1��Ԫ");
			oper_sug_lmt.focus();
			return false;
		}
	}else{
		oper_sug_lmt.value = 0;
	}

	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	return true;
}

function reviseCheck(){

	/**����ֶΣ����лس�,ȥ���س�*/
	//var fielde = new Array("last_name","first_name","comp_name","pre_post","reg_post","comp_post","pre_zone_no","comp_zone_no","lm_comp_zoneno","lm_pre_zoneno"); 
	//modified by Li xuemeng
	var fielde = new Array("last_name","first_name","comp_name","pre_post","comp_post","pre_zone_no","comp_zone_no","lm_pre_zoneno"); 
	
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/*end*/

	//ִ��У��   �ı��� 
	//var field = new Array("last_name","first_name","comp_name","pre_post","reg_post","comp_post","pre_zone_no","comp_zone_no","lm_comp_zoneno","lm_pre_zoneno"); 
	//var info = new Array("��ƴ��","��ƴ��","��λ����","��סַ�ʱ�","������ַ�ʱ�","��λ�ʱ�","��סַ�绰����","��λ�绰����","��ϵ�˵�λ����","��ϵ��סַ�绰����"); 
 	//modified by LI xuemeng
 	var field = new Array("last_name","first_name","comp_name","pre_post","comp_post","comp_zone_no"); 
	var info = new Array("��ƴ��","��ƴ��","��λ����","��סַ�ʱ�","��λ�ʱ�","��λ�绰����"); 
 
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
	
	/*����ʱ�λ��=======================================*/
	//var field = new Array("pre_post","reg_post","comp_post"); 
	//var info = new Array("��סַ�ʱ�","������ַ�ʱ�","��λ�ʱ�"); 
 	//modified by li xuemeng
 	var field = new Array("pre_post","comp_post"); 
	var info = new Array("��סַ�ʱ�","��λ�ʱ�");
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'ӦΪ��λ'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	}
	//���Ӷ�ƴ���գ�ƴ�������Ⱥ��ж�add by lixq 20090812
	if((document.forms[0].last_name.value.length+document.forms[0].first_name.value.length) > 16){
		alert("ƴ���ա�ƴ�������Ⱥ����Ϊ16λ");
		document.forms[0].last_name.focus();
		return false;
	}
	
	//���绰��������ȫ�㣬���ᱨ��
	//modified by LI xuemeng ɾ��"lm_comp_zoneno","��ϵ�˵�λ�绰����",
	var field = new Array("comp_zone_no"); 
	var info = new Array("�����˵�λ�绰����"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		//if(parseInt(trim(eval(tmp)))==0){
		if(isZero(trim(eval(tmp)))){
			alert(info[i]+'����ȫ����0');
			eval("document.forms[0]."+field[i]+".focus()");
			return false;
		}
	}
	
	/**Ӧ�Ҫ��У��v�����ܳ��ֵĴ���  begin*/
	var fieldv = new Array("comp_name","first_name"); 
	var infov = new Array("��λȫ��","��ƴ��"); 
 	var fvaluev = new Array(40,16); 
 
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
	
	return true;
}

function reviseCheckST(){

	/**����ֶΣ����лس�,ȥ���س�*/
	//modified by li xuemeng ɾ��"reg_post","lm_comp_zoneno",
	var fielde = new Array("last_name","first_name","comp_name","pre_post","comp_post","pre_zone_no","comp_zone_no","lm_pre_zoneno"); 
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/*end*/
	//ִ��У��   �ı��� 
	//modified by Li xuemeng ɾ��"reg_post","������ַ�ʱ�","lm_comp_zoneno","��ϵ�˵�λ����",
	var field = new Array("last_name","first_name","comp_name","pre_post","comp_post"); 
	var info = new Array("��ƴ��","��ƴ��","ѧУ����","��סַ�ʱ�","ѧУ�ʱ�"); 
 
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
	
	/*����ʱ�λ��=======================================*/
	//modified by Li xuemeng ɾ��"reg_post","������ַ�ʱ�",
	var field = new Array("pre_post","comp_post"); 
	var info = new Array("��סַ�ʱ�","ѧУ�ʱ�"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'ӦΪ��λ'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	}
	
	
	
	/**Ӧ�Ҫ��У��v�����ܳ��ֵĴ���  begin*/
	var fieldv = new Array("comp_name","first_name"); 
	var infov = new Array("ѧУȫ��","��ƴ��"); 
 	var fvaluev = new Array(40,16); 
 
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
	
	return true;
}

function reviseCheckST2(){

	/**����ֶΣ����лس�,ȥ���س�*/
	var fielde = new Array("last_name","first_name","comp_name","pre_post", "comp_post","pre_zone_no","lm_pre_zoneno"); 
	for(var i=0;i<fielde.length;i++)	{

		var t = document.forms[0].elements[fielde[i]].value;
		
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/*end*/
	//ִ��У��   �ı��� 
	var field = new Array("last_name","first_name","comp_name","comp_post","pre_post"); 
	var info = new Array("��ƴ��","��ƴ��","ѧУ����","ѧУ�ʱ�","��סַ�ʱ�"); 
 
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
	
	//���Ӷ�ƴ���գ�ƴ�������Ⱥ��ж�add by lixq 20090812
	if((document.forms[0].last_name.value.length+document.forms[0].first_name.value.length) > 16){
		alert("ƴ���ա�ƴ�������Ⱥ����Ϊ16λ");
		document.forms[0].last_name.focus();
		return false;
	}
	/*����ʱ�λ��=======================================*/
	var field = new Array("pre_post","comp_post"); 
	var info = new Array("��סַ�ʱ�","ѧУ�ʱ�"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'ӦΪ��λ'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	}
	
	
	
	/**Ӧ�Ҫ��У��v�����ܳ��ֵĴ���  begin*/
	var fieldv = new Array("comp_name","first_name"); 
	var infov = new Array("ѧУȫ��","��ƴ��"); 
 	var fvaluev = new Array(40,16); 
 
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
	
	return true;
}

/**
 *���񿨣�����Ա�ύ����
 *checknew��,����Ա�ύ������Ϣ
 */
function checkNewSubBus(){          
	//���鸴��Ա�����
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('�������������'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//�ܾ����
		if(isEmpty(oper_sug_lmt.value)){
			alert("�����뽨����");
			oper_sug_lmt.focus();
			return false;
		}
		if(!IsDecimal(oper_sug_lmt.value)){
			alert("���������벻�Ϸ�");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value > 500){
			alert("�����Ȳ��ܳ���500��Ԫ");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value < 1){
			alert("���ǿ��������1��Ԫ");
			oper_sug_lmt.focus();
			return false;
		}
	}else{
		oper_sug_lmt.value = 0;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("��ע�������100����");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//����ʾ���������,�������������
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("����д�������");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("����д����˵��");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("����˵������100����");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	return true;
}





/*===================�Ӻ���============================*/
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
//�ж��Ƿ�Ϊ����
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

//�õ���ѡ����ѡ�е���
function getSelectedButton(buttonGroup){ 	
	for(var i=0;i<buttonGroup.length;i++){
		if(buttonGroup[i].checked){
			return i;
		}
	}
	return 0;
}
function rmlines(str){
	var s = '';
	for(var i = 0;i<str.length;i++){
		
		if(!(str.charAt(i) == '\x0a' || str.charAt(i) == '\x0d')){
			s += str.charAt(i);
		}
	}
	return s;
}
function checkSugLmt(oper_sug_lmt){
	var flag = true;
	if(!IsDecimal(oper_sug_lmt.value)){
			alert("���������벻�Ϸ�");
			oper_sug_lmt.focus();
			return false;
		}
	if(!isEmpty(oper_sug_lmt.value)) {
	    if( !isZ(oper_sug_lmt.value)){
	    	alert("������ӦΪ����"); 
			oper_sug_lmt.focus(); 
			flag = false; 
		}
	} 

	return flag;
	
}

