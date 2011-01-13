


//检察是否是输入数字,也可以有小数点
function checkDouble(){
	if ((event.keyCode > 47 && event.keyCode < 58) || event.keyCode == 46) {
		event.returnValue = true;
	}
	else
		event.returnValue = false;
}
/**
 *checknew中,复核员提交审批信息
 */
function checkNewSub(){          
	//检验复核员意见栏
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('请输入审批意见'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 3){//拒绝情况
		if(isEmpty(oper_sug_lmt.value)){
			alert("请输入建议额度");
			oper_sug_lmt.focus();
			return false;
		}
		
		//add by yly，建议额度校验
		if (!checkSugLmt(oper_sug_lmt)){
		
			return false;
		}

	}else{
		oper_sug_lmt.value = 0;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//若显示反馈意见栏,则反馈意见栏必填
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("请填写反馈意见");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("请填写反馈说明");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("反馈说明限制100个字");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	
	//若不同意降档，则复核员不能选择降档意见
	
		if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 2){
			alert("复核员意见有误(客户不同意降档发卡)");
			document.forms[0].oper_sug_kind[0].focus(); 
			return false; 
		}
		
	
	

	return true;
}

function checkNewSubST2(){          
	//检验复核员意见栏
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('请输入审批意见'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 3){//拒绝情况
		if(isEmpty(oper_sug_lmt.value)){
			alert("请输入建议额度");
			oper_sug_lmt.focus();
			return false;
		}
	//add by yly，建议额度校验
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}	 
			
	}else{
		oper_sug_lmt.value = 0;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//若显示反馈意见栏,则反馈意见栏必填
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("请填写反馈意见");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("请填写反馈说明");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("反馈说明限制100个字");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	
	//若申请金卡，且不同意降档，则复核员不能选择降档意见
	
		if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 2){
			alert("复核员意见有误(客户不同意降档发卡)");
			document.forms[0].oper_sug_kind[0].focus(); 
			return false; 
		}

	
	
	return true;
}

/**
 *checknew中,复核员退回审批信息
 */
function checkNewHandback(){ 
	document.forms[0].oper_sug_kind.value = '-';
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	if(oper_sug_lmt.value.length != 0){
	
		//add by yly，建议额度应为整数
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}

	}
	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{
		if(isEmpty(document.forms[0].return_rea_credit.value)&&isEmpty(document.forms[0].return_rea_judge.value)){
			alert("请填写退回原因");
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	return true;
}
/**
 *checknew中,复核员退回审批信息
 */
function checkNewHandbackBus(){ 
	
	document.forms[0].oper_sug_kind.value = '-';
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	if(oper_sug_lmt.value.length != 0){
		if(!IsDecimal(oper_sug_lmt.value)){
			alert("建议额度输入不合法");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value > 300){
			alert("建议额度不能超过300万元");
			oper_sug_lmt.focus();
			return false;
		}
	}
	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{
		if(isEmpty(document.forms[0].return_rea.value)){
			alert("请填写退回原因");
			document.forms[0].return_rea.focus();
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}

	return true;
}
/**
 *checknewSAttach中,复核员退回审批信息
 */
function checkNewSAttachHandback(){ 
	//检验复核员意见栏

	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{
		if(isEmpty(document.forms[0].return_rea.value)){
			alert("请填写退回原因");
			document.forms[0].return_rea.focus();
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}

	return true;
}

/**
 *checknewSAttach中,复核员提交审批信息
 */
function checkNewSAttachSub(){ 
	//检验复核员意见栏
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//若显示反馈意见栏,则反馈意见栏必填
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("请填写反馈意见");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("请填写反馈说明");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("反馈说明限制100个字");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	return true;
}

/**
 *abcChecknewSAttach中,总行复核员退回审批信息
 */
function abcCheckNewSAttachHandback(){ 
	//检验复核员意见栏
	if(isEmpty(document.forms[0].return_rea.value)){
		alert("请填写退回原因");
		document.forms[0].return_rea.focus();
		return false;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}

	return true;
}

/**
 *abcchecknew中,总行复核员退回审批信息
 */
function abcCheckNewHandback(){ 
	
	document.forms[0].oper_sug_kind.value = '-';
	var task_type = document.forms[0].task_type.value;
	if(task_type == 1)
	{	
		if(isEmpty(document.forms[0].return_rea_credit.value)&&isEmpty(document.forms[0].return_rea_judge.value)){
			alert("请填写退回原因");
			return false;
		}
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	return true;
}

/**
 *abcchecknew中,总行复核员提交审批信息，用于个人卡主卡总行复核
 */
function abcCheckNewSub(){ 
	//检验复核员意见栏
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('请输入审批意见'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}

	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	//个人卡情况
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//拒绝情况
		if(isEmpty(oper_sug_lmt.value)){
			alert("请输入建议额度");
			oper_sug_lmt.focus();
			return false;
		}
		
		//add by yly，建议额度检查
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}
	
	
	}else{
		oper_sug_lmt.value = 0;
	}
	
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	
	//若不同意降档，则复核员不能选择降档意见
	if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 1){
		alert("复核员意见有误(客户不同意降档发卡)");
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
		
	
	
	return true;
}

/**
 *abcchecknew中,总行复核员提交审批信息，用于个人卡主卡总行复核
 */
function abcCheckNewSubST2(){ 
	//检验复核员意见栏
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('请输入审批意见'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}

	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	//个人卡情况
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//拒绝情况
		if(isEmpty(oper_sug_lmt.value)){
			alert("请输入建议额度");
			oper_sug_lmt.focus();
			return false;
		}

		//add by yly，建议额度检查
		if (!checkSugLmt(oper_sug_lmt)){
			return false;
		}

	
	}else{
		oper_sug_lmt.value = 0;
	}
	
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	
	//若不同意降档，则复核员不能选择降档意见

		if(document.forms[0].normal_card.value == 0 && getSelectedButton(document.forms[0].oper_sug_kind) == 1){
			alert("复核员意见有误(客户不同意降档发卡)");
			document.forms[0].oper_sug_kind[0].focus(); 
			return false; 
		}
		
		
	
	
	return true;
}


/**
 *abcchecknew中,总行复核员提交审批信息，用于商务卡单位资料总行复核
 */
function abcCheckNewSubBus(){ 
	//检验复核员意见栏
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('请输入审批意见'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}

	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	//商务卡情况		
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//拒绝情况
		if(isEmpty(oper_sug_lmt.value)){
			alert("请输入建议额度");
			oper_sug_lmt.focus();
			return false;
		}
		if(!IsDecimal(oper_sug_lmt.value)){
			alert("建议额度输入不合法");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value > 500){
			alert("建议额度不能超过500万元");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value < 1){
			alert("贷记卡最低授信1万元");
			oper_sug_lmt.focus();
			return false;
		}
	}else{
		oper_sug_lmt.value = 0;
	}

	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	return true;
}

function reviseCheck(){

	/**检查字段，若有回车,去掉回车*/
	//var fielde = new Array("last_name","first_name","comp_name","pre_post","reg_post","comp_post","pre_zone_no","comp_zone_no","lm_comp_zoneno","lm_pre_zoneno"); 
	//modified by Li xuemeng
	var fielde = new Array("last_name","first_name","comp_name","pre_post","comp_post","pre_zone_no","comp_zone_no","lm_pre_zoneno"); 
	
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/*end*/

	//执行校验   文本框 
	//var field = new Array("last_name","first_name","comp_name","pre_post","reg_post","comp_post","pre_zone_no","comp_zone_no","lm_comp_zoneno","lm_pre_zoneno"); 
	//var info = new Array("姓拼音","名拼音","单位名称","现住址邮编","户籍地址邮编","单位邮编","现住址电话区号","单位电话区号","联系人单位区号","联系人住址电话区号"); 
 	//modified by LI xuemeng
 	var field = new Array("last_name","first_name","comp_name","pre_post","comp_post","comp_zone_no"); 
	var info = new Array("姓拼音","名拼音","单位名称","现住址邮编","单位邮编","单位电话区号"); 
 
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
	
	/*检查邮编位数=======================================*/
	//var field = new Array("pre_post","reg_post","comp_post"); 
	//var info = new Array("现住址邮编","户籍地址邮编","单位邮编"); 
 	//modified by li xuemeng
 	var field = new Array("pre_post","comp_post"); 
	var info = new Array("现住址邮编","单位邮编");
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'应为六位'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	}
	//增加对拼音姓，拼音名长度和判断add by lixq 20090812
	if((document.forms[0].last_name.value.length+document.forms[0].first_name.value.length) > 16){
		alert("拼音姓、拼音名长度和最大为16位");
		document.forms[0].last_name.focus();
		return false;
	}
	
	//若电话号码输入全零，将会报错
	//modified by LI xuemeng 删除"lm_comp_zoneno","联系人单位电话区号",
	var field = new Array("comp_zone_no"); 
	var info = new Array("申请人单位电话区号"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		//if(parseInt(trim(eval(tmp)))==0){
		if(isZero(trim(eval(tmp)))){
			alert(info[i]+'不能全输入0');
			eval("document.forms[0]."+field[i]+".focus()");
			return false;
		}
	}
	
	/**应李健要求，校验v＋可能出现的错误  begin*/
	var fieldv = new Array("comp_name","first_name"); 
	var infov = new Array("单位全称","名拼音"); 
 	var fvaluev = new Array(40,16); 
 
	//检察输入信息是否为空 
	var tmp; 	
	for(var i=0;i<fieldv.length;i++)	{ 
	 	tmp ="document.forms[0]."+fieldv[i]+".value"; 
	    if(!isEmpty(eval(tmp))) {
	    	if( getChineseStrLength(eval(tmp)) > fvaluev[i] ){
	    	 	alert(infov[i]+'长度超过限制'); 
			    eval("document.forms[0]."+fieldv[i]+".focus()"); 
			    return false; 
		    }
	     } 
	}
	/**end*/ 
	
	return true;
}

function reviseCheckST(){

	/**检查字段，若有回车,去掉回车*/
	//modified by li xuemeng 删除"reg_post","lm_comp_zoneno",
	var fielde = new Array("last_name","first_name","comp_name","pre_post","comp_post","pre_zone_no","comp_zone_no","lm_pre_zoneno"); 
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/*end*/
	//执行校验   文本框 
	//modified by Li xuemeng 删除"reg_post","户籍地址邮编","lm_comp_zoneno","联系人单位区号",
	var field = new Array("last_name","first_name","comp_name","pre_post","comp_post"); 
	var info = new Array("姓拼音","名拼音","学校名称","现住址邮编","学校邮编"); 
 
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
	
	/*检查邮编位数=======================================*/
	//modified by Li xuemeng 删除"reg_post","户籍地址邮编",
	var field = new Array("pre_post","comp_post"); 
	var info = new Array("现住址邮编","学校邮编"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'应为六位'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	}
	
	
	
	/**应李健要求，校验v＋可能出现的错误  begin*/
	var fieldv = new Array("comp_name","first_name"); 
	var infov = new Array("学校全称","名拼音"); 
 	var fvaluev = new Array(40,16); 
 
	//检察输入信息是否为空 
	var tmp; 	
	for(var i=0;i<fieldv.length;i++)	{ 
	 	tmp ="document.forms[0]."+fieldv[i]+".value"; 
	    if(!isEmpty(eval(tmp))) {
	    	if( getChineseStrLength(eval(tmp)) > fvaluev[i] ){
	    	 	alert(infov[i]+'长度超过限制'); 
			    eval("document.forms[0]."+fieldv[i]+".focus()"); 
			    return false; 
		    }
	     } 
	}
	/**end*/ 
	
	return true;
}

function reviseCheckST2(){

	/**检查字段，若有回车,去掉回车*/
	var fielde = new Array("last_name","first_name","comp_name","pre_post", "comp_post","pre_zone_no","lm_pre_zoneno"); 
	for(var i=0;i<fielde.length;i++)	{

		var t = document.forms[0].elements[fielde[i]].value;
		
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);	
	} 
	/*end*/
	//执行校验   文本框 
	var field = new Array("last_name","first_name","comp_name","comp_post","pre_post"); 
	var info = new Array("姓拼音","名拼音","学校名称","学校邮编","现住址邮编"); 
 
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
	
	//增加对拼音姓，拼音名长度和判断add by lixq 20090812
	if((document.forms[0].last_name.value.length+document.forms[0].first_name.value.length) > 16){
		alert("拼音姓、拼音名长度和最大为16位");
		document.forms[0].last_name.focus();
		return false;
	}
	/*检查邮编位数=======================================*/
	var field = new Array("pre_post","comp_post"); 
	var info = new Array("现住址邮编","学校邮编"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'应为六位'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	}
	
	
	
	/**应李健要求，校验v＋可能出现的错误  begin*/
	var fieldv = new Array("comp_name","first_name"); 
	var infov = new Array("学校全称","名拼音"); 
 	var fvaluev = new Array(40,16); 
 
	//检察输入信息是否为空 
	var tmp; 	
	for(var i=0;i<fieldv.length;i++)	{ 
	 	tmp ="document.forms[0]."+fieldv[i]+".value"; 
	    if(!isEmpty(eval(tmp))) {
	    	if( getChineseStrLength(eval(tmp)) > fvaluev[i] ){
	    	 	alert(infov[i]+'长度超过限制'); 
			    eval("document.forms[0]."+fieldv[i]+".focus()"); 
			    return false; 
		    }
	     } 
	}
	/**end*/ 
	
	return true;
}

/**
 *商务卡－复核员提交复核
 *checknew中,复核员提交审批信息
 */
function checkNewSubBus(){          
	//检验复核员意见栏
	var tmp =document.forms[0].oper_sug_kind.length; 
	var flag = 0;
	for(var j=0;j< tmp;j++){ 	
	 	if(eval("document.forms[0].oper_sug_kind"+"["+j+"].checked")){
	    	flag = 1;
	    	break;
	  	}
	} 	
	if(flag == 0){
	    alert('请输入审批意见'); 
		document.forms[0].oper_sug_kind[0].focus(); 
		return false; 
	}
	var oper_sug_lmt = document.forms[0].oper_sug_lmt;	
	
	if(getSelectedButton(document.forms[0].oper_sug_kind) != 2){//拒绝情况
		if(isEmpty(oper_sug_lmt.value)){
			alert("请输入建议额度");
			oper_sug_lmt.focus();
			return false;
		}
		if(!IsDecimal(oper_sug_lmt.value)){
			alert("建议额度输入不合法");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value > 500){
			alert("建议额度不能超过500万元");
			oper_sug_lmt.focus();
			return false;
		}
		if(oper_sug_lmt.value < 1){
			alert("贷记卡最低授信1万元");
			oper_sug_lmt.focus();
			return false;
		}
	}else{
		oper_sug_lmt.value = 0;
	}
	if(!isEmpty(document.forms[0].oper_sug.value)){
		if(document.forms[0].oper_sug.value.length > 100){
			alert("备注意见限制100个字");
			document.forms[0].oper_sug.focus();
			return false;
		}
	}
	var flag = document.forms[0].feed_back_flag.value;
	var task_type = document.forms[0].task_type.value;
	if((task_type == 2)&&(flag == 1)){		//若显示反馈意见栏,则反馈意见栏必填
		if(isEmpty(document.forms[0].feedback_sug.value)){
			alert("请填写反馈意见");
			document.forms[0].feedback_sug.focus();
			return false;
		}	
		if(isEmpty(document.forms[0].feedback_exp.value)){
			alert("请填写反馈说明");
			document.forms[0].feedback_exp.focus();
			return false;
		}
		if(document.forms[0].feedback_exp.value.length > 100){
			alert("反馈说明限制100个字");
			document.forms[0].feedback_exp.focus();
			return;
		}
	}
	return true;
}





/*===================子函数============================*/
//检查s是否为正小数
//若需求为负小数,那么可以去掉"-",然后用此函数校验
function IsDecimal(s)
{
	var floatStr = "0123456789."; //可以组成小数的字符
	var sLength = s.length;		//待校验字符串的长度
	
	if(s.charAt(0) == "."){   //小数第一位不能是"."
		return false;
	}
	
	//小数不能有两个小数点
	var dot = 0;
	for(var i=0;i<sLength;i++){
		if(s.charAt(i) == "."){
			dot++;
		}
	}
	if(dot > 1)return false;
	
	//小数的最后一位不能是小数点
	if(s.charAt(sLength-1) == ".")return false;
	
	//小数不能第一第二位同时为0
	if(sLength > 1 && s.charAt(0) == "0" && s.charAt(1) == "0")return false;
	
	//若第二位不是小数点的话,第一位不能是0
	if(sLength > 1 && s.charAt(0) == "0" && s.charAt(1) != ".")return false;
		
	//小数的必须由合法的字符组成
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
*处理字符串，返回字符串长度。
*作用：控制带汉字的字符串的长度，汉字块的两端需要附加一个字节
*str为输入的字符串
*如：“123你好456”长度为10，而在此函数处理中，返回长度为12，因为在“你好”两端需附加一个字节
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

//判断字符串是否全是0 ,全0返回true
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
//判断是否为整数
function isZ(s)
{
	var sLength = s.length;		//待校验字符串的长度
		
	//检查小数点所在位
	var i = 0;
	for(;i<sLength;i++){
		if(s.charAt(i) == "."){
			break;
		}
	}
	if(i == sLength - 1){//若字符串没有小数点
		return true;
	}
	for(var j=i+1;j<sLength;j++){//若字符串有小数点
		if(s.charAt(j) != "0"){
			return false;
		}
	}
	return true;
}

//得到单选框中选中的项
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
			alert("建议额度输入不合法");
			oper_sug_lmt.focus();
			return false;
		}
	if(!isEmpty(oper_sug_lmt.value)) {
	    if( !isZ(oper_sug_lmt.value)){
	    	alert("建议额度应为整数"); 
			oper_sug_lmt.focus(); 
			flag = false; 
		}
	} 

	return flag;
	
}

