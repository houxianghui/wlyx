
//检察是否是输入数字,也可以有小数点
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

	/**检查字段，若有回车，则报错*/
	//var fielde = new Array("cust_name","last_name","first_name","pre_addr","comp_addr","cert_no","comp_name"); 
	var fielde = new Array("app_lmt","cust_name","last_name","first_name","nationality","birthday","fami_member","cert_no","pre_addr","pre_city_code","pre_post","pre_years","pre_zone_no","pre_phone","reg_post","per_income","ave_income","comp_name","comp_addr","comp_city_code","comp_post","comp_zone_no","comp_phone","work_years","lm_name","lm_comp","lm_comp_zoneno","lm_comp_phone","lm_pre_addr","lm_pre_zoneno","lm_pre_phone","app_source","exam_node_name"); 
	for(var i=0;i<fielde.length;i++)	{
		var t = document.forms[0].elements[fielde[i]].value;
		document.forms[0].elements[fielde[i]].value = "";
		document.forms[0].elements[fielde[i]].value = rmlines(t);			
	} 
	/**end*/
	
	//定义变量MsgError，此变量存储一系列的错误提示字符串，这些错误都是可以强制通过的
	var MsgError = "";
	
	//若页面显示‘请输入若申请金卡未获批准，是否同意申请普通卡’单选框，则此项为必输项	 
	var card_kind = document.forms[0].card_kind.value;
	
	
	if(document.forms[0].card_single_code.value != "30"){//非公务员卡有以下校验
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
		        alert('请输入若申请金卡未获批准，是否同意申请普通卡'); 
				document.forms[0].normal_card[0].focus(); 
				return false; 
			}	
		}
	}
	
	
	//执行校验   文本框 
	
	var field = new Array("app_lmt","cust_name","last_name","first_name","nationality","birthday","fami_member","cert_no","pre_addr","pre_city_code","pre_post","pre_years","reg_post","per_income","ave_income","comp_name","comp_addr","comp_city_code","comp_post","work_years","lm_name","lm_comp","lm_comp_zoneno","lm_comp_phone","lm_pre_addr","app_source","exam_node_name"); 
	var info = new Array("拟申请额度","客户姓名","客户姓(拼音)","客户名(拼音)","客户国籍","客户出生日期","家庭人口","证件号码","现住址","现住址城市代码","现住址邮编","现住址居住年限","户籍地址邮编","个人年收入","家庭人均年收入","学校全称","学校地址","学校地址城市代码","学校邮编","在读年级","联系人姓名","联系人工作单位","工作单位电话区号","工作单位电话","联系人现住址","申请表来源","网点编号"); 
 
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
 	
	var fieldB = new Array("cust_sex","marr_stat","cert_type","edu_degr","lm_sex","exam_mode"); 
	var infoB = new Array("客户性别","婚姻状况","证件类型","教育程度","联系人性别","调查方式"); 
 
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
	
	/*检查拟申请额度==============================*/
	obj=document.forms[0].app_lmt;
	if(!checkYForGet(obj)){
		alert("拟申请金额不能为空,或以0开头");
		obj.focus();
		return false;
	}
		
	//金卡申请金额不能低于10000元
	if(document.forms[0].card_kind.value == 0){
		if(parseInt(obj.value,10) < 10000){
			alert("金卡最低授信人民币10000元!");
			obj.focus();
			return false;
		}	
	}
	//普卡申请金额不能低于2000元
	if(document.forms[0].card_kind.value==1){
		if(parseInt(obj.value,10) < 2000){
			alert("普卡最低授信人民币2000元!");
			obj.focus();
			return false;
		}	
	}
	
	//检查姓拼音长度为8,名拼音长度为16
	if(document.forms[0].last_name.value.length > 10){
		alert("姓拼音长度最大为10位");
		document.forms[0].last_name.focus();
		return false;
	}
	if(document.forms[0].first_name.value.length > 16){
		alert("名拼音长度最大为16位");
		document.forms[0].first_name.focus();
		return false;
	}
	if(!isLetter(document.forms[0].last_name.value)){
		alert("姓拼音应为大写英文字母");
		document.forms[0].last_name.focus();
		return false;
	}
	if(!isLetter(document.forms[0].first_name.value)){
		alert("名拼音应为大写英文字母");
		document.forms[0].first_name.focus();
		return false;
	}
	
	
	
	/*检查出生日期=======================================*/
	obj=document.forms[0].birthday;
	if(!checkdateNumber(obj)){
		alert("申请人出生日期应为8位");
		obj.focus();
		return false;
	}
	if(!checkDate(obj)){
		obj.focus();
		return false;
	}
	
	/*检查申请人年龄====================================*/	
    //年龄要求在18至99周岁之间
    currentDate = new Date();
    currentYear = parseInt(currentDate.getFullYear());
    
    strBirthday = document.forms[0].birthday.value;
    intAge = currentYear - parseInt(strBirthday.substring(0,4));
    if(intAge < 18){
		alert("年龄不符合发卡要求！");
		document.forms[0].birthday.focus();
		return false;
    }
    if(intAge > 99){
		alert("年龄不符合发卡要求！");
		document.forms[0].birthday.focus();
		return false;
    }
    
    //居住地年限不得大于申请人年龄
    var dwellspend = document.forms[0].pre_years.value;
    if(intAge < dwellspend){
    	alert("居住地年限不得大于申请人年龄");
    	document.forms[0].pre_years.focus();
    	return false;
    }
   	
   	//现单位工作年限不能大于(年龄-15),只是提示,可强行通过
    var workyears = document.forms[0].work_years.value;
    if(workyears <=0){
    	alert("在读年级有误");
    	document.forms[0].work_years.focus();
    	return false;   	
    }
    
    if((intAge-15) < workyears){
    	//alert("重新审查工作年限");
    	if(MsgError == ""){
			MsgError = MsgError + "重新审查在读年级";
		}
		else{
			MsgError = MsgError + "\n重新审查在读年级";
		}
    }
    
    /*检查证件号码=========================================*/
   if(getSelectedButton(document.forms[0].cert_type)== 0){
    	obj = document.forms[0].cert_no;
    	if(!isIDNO(obj)){
    		document.forms[0].cert_no.focus();	
    		return false;
    	}  			
	}
   
    
    /*检查电子信箱=========================================*/
    if(!isEmpty(document.forms[0].email.value)){
	    if(!isEmail(document.forms[0].email)){
	    	alert("电子信箱出错");
	    	document.forms[0].email.focus();
	    	return false;
	    }
	    if(!ChineseIn(document.forms[0].email.value)){
	    	alert("电子信箱有不合法字符");
	    	document.forms[0].email.focus();
	    	return false;	
	    }
    }
    
    /*检查邮编位数=======================================*/
	var field = new Array("pre_post","reg_post","comp_post"); 
	var info = new Array("现住址邮编","户籍地址邮编","学校邮编"); 
 
	var tmp; 
	for(var i=0;i<field.length;i++)	{ 
		tmp ="document.forms[0]."+field[i]+".value.length"; 
	    if((eval(tmp)) != 6) { 
	        alert(info[i]+'应为六位'); 
	        eval("document.forms[0]."+field[i]+".focus()"); 
	        return false; 
	    } 
	} 
	
	/*检查手机号=======================================*/
	var field = new Array("pre_mobile","lm_mobile"); 
	var info = new Array("申请人手机号","联系人手机号"); 
	
	var tmp;
	for(var i=0;i<field.length;i++){
		tmp = "document.forms[0]."+field[i]+".value";
		if(!isEmpty(eval(tmp))){
			if(!isMobile(eval(tmp))){
				alert(info[i]+'输入有误');
				eval("document.forms[0]."+field[i]+".focus()");
				return false;
			}
			//if(parseInt(trim(eval(tmp)))==0){
			if(isZero(trim(eval(tmp)))){
				alert(info[i]+'不能全输入0');
				eval("document.forms[0]."+field[i]+".focus()");
				return false;
			}
			tmp1 ="document.forms[0]."+field[i]+".value.length"; 
		    if((eval(tmp1)) < 11) { 
		        alert(info[i]+'应为11位数字'); 
		        eval("document.forms[0]."+field[i]+".focus()"); 
		        return false; 
		    } 
	    }
	}
	
	
	//选择证件类型,若为身份证,检查;若为其他,则"请注明"为必填项
	var cert_type = getSelectedButton(document.forms[0].cert_type);
	if(cert_type == 0){
		//号码和生日的一致性
		str = document.forms[0].cert_no.value;
		str2 = document.forms[0].birthday.value;
		if(str.length==15){
			strb=str.substring(6,12);
			str3=str2.substring(2,8);
			if(strb!=str3){
				alert("身份证号码与出生日期不符!");
				document.forms[0].cert_no.focus();
				return false;
			}
			var strsex=str.charAt(14);
		}
		if(str.length==18){
			strb=str.substring(6,14);
			if(strb!=str2) {
				alert("身份证号码与出生日期不符!");
				document.forms[0].cert_no.focus();
				return false;
			}
			var strsex=str.charAt(16);
		}
		//性别和号码的一致性
		if(strsex % 2==0) strsex="F";
		else strsex="M";
		obj = document.forms[0].cust_sex
		if(!((obj[0].checked && strsex=="M")||(obj[1].checked && strsex=="F"))){
			alert("身份证号码与性别不符!!");
			document.forms[0].cert_no.focus();
			return false;
		}
	    
	    //检查校验码
		if(str.length==18){
			//校验码
			var strjiaoyan=new Array("1","0","X","9","8","7","6","5","4","3","2");
			//加权因子
			var intQuan=new Array("7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2","1");
			var sum=0;
			for(var inum=0;inum<17;inum++)	sum+=str.substring(inum,inum+1) * intQuan[inum];
			var strresult=strjiaoyan[sum % 11];
			if(str.substring(17,18).toUpperCase()!=strresult) {
				alert("身份证号码校验码不正确!");
				document.forms[0].cert_no.focus();
				return false;
			}
		}
	}
	
	//若证件类型为其他
	if(cert_type == 1){
		if(isEmpty(document.forms[0].cert_name.value)){
			alert("请输入其他证件名称");
			document.forms[0].cert_name.focus();
			return false;
		}
	}
	//若证件类型不为"其他",则"请注明"文本框为空,不填值
	if(cert_type != 1){
		if(!isEmpty(document.forms[0].cert_name.value)){
			alert("证件类型冲突");
			document.forms[0].cert_name.focus();
			return false;
		}
	}
	


	
	//检查约定还款资料信息,还款卡号必须为19位数字
	var repay_card = document.forms[0].repay_card;
	if(!isEmpty(repay_card.value)){
		if(!(repay_card.value.length == 19 || repay_card.value.length == 16)){
			alert("卡号为16位或19位数字");
			repay_card.focus();
			return false;
		}
	}
	
	//检查约定还款资料信息;起始,终止日期
	var start_date = document.forms[0].start_date;
	var expire_date = document.forms[0].expire_date;
	if(!isEmpty(start_date.value)){    //是否是日期形式(yyyymmdd)
		if(!checkdateNumber(start_date)){
			alert("起始日期应为8位");
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
			alert("终止日期应为8位");
			expire_date.focus();
			return false;
		}
		if(!checkDate(expire_date)){
			expire_date.focus();
			return false;
		}
	}
	if(!(isEmpty(start_date.value) || isEmpty(expire_date.value))){//起始日期小于终止日期
		if(expire_date.value <= start_date.value){
			alert("截止日期应大于起始日期");
			expire_date.focus();
			return false;
		}	
	}	
	
	//校验预留信息,若填写了问题,则答案必输;若填写了答案,则问题必输
	var ques = document.forms[0].remain_ques;
	var ans = document.forms[0].remain_ans;
	if((!isEmpty(ques.value)) == isEmpty(ans.value)){
		alert("请将预留信息输入完整");
		ques.focus();
		return false;
	}
	
	//拟申请额度校验是否为合法的小数
	var app_lmt = document.forms[0].app_lmt;
	if(!IsDecimal(app_lmt.value)){
		alert("拟申请额度不合法");
		app_lmt.focus();
		return false;
	}



	
	/**应李健要求，校验v＋可能出现的错误  begin*/
	var fieldv = new Array("cust_name","cert_no","pre_addr","pre_phone","comp_name","comp_addr","comp_phone","remain_ques","remain_ans","lm_name","lm_pre_phone","reg_addr","car_no","car_type","lm_comp_phone","lm_pre_addr","cert_name"); 
	var infov = new Array("姓名(中文)","证件号码","现住址","现住址电话号码","学校全称","学校地址","学校电话号码","预留问题","预留答案","联系人姓名(中文)","联系人现住址电话号码","户籍地址","车牌号","车品牌","联系人单位电话号码","联系人现住址","其他证件名称"); 
 	var fvaluev = new Array(40,20,80,17,40,80,17,20,20,20,17,80,20,40,17,80,19); 
 
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
	
	/**校验金额，金额必须输入整数或1000.00，不能是1000.21  begin*/
	var fieldv = new Array("app_lmt"); 
	var infov = new Array("拟申请额度"); 
 
	//检察输入信息是否为空 
	var tmp; 
	for(var i=0;i<fieldv.length;i++)	{ 
	 	tmp ="document.forms[0]."+fieldv[i]+".value"; 
	    if(!isEmpty(eval(tmp))) {
	    	if( !isZ(eval(tmp))){
	    	 	alert(infov[i]+'输入不合法(应为2000.00)'); 
			    eval("document.forms[0]."+fieldv[i]+".focus()"); 
			    return false; 
		    }
	     } 
	}
	/**end*/ 
	

	
	/**校验约定还款，1、若还款卡号不为空，则还款方式为必输项
	*/
	if(!isEmpty(document.forms[0].repay_card.value)){

		if((document.forms[0].repay_mark.value == "")){
			alert("请选择还款方式！");
			document.forms[0].repay_mark.focus();
			return false;
		}
	}
	/**end*/
	
	/**校验：家庭人口大于0，现住址年限大于0*/
	var jtrk = document.forms[0].fami_member.value;
    if(jtrk <=0){
    	alert("家庭人口不能为0");
    	document.forms[0].fami_member.focus();
    	return false;   	
    }
    var xzznx = document.forms[0].pre_years.value;
    if(xzznx <=0){
    	alert("现住址年限不能为0");
    	document.forms[0].pre_years.focus();
    	return false;   	
    }
	/**end*/
		
	

	
	//证件号码18位或15位时，证件类型应为身份证或临时身份证，其他不符合标准的，
	//系统提示“证件类型错误”，但可以强制通过。
	str = document.forms[0].cert_no.value;
	if((str.length==15) || (str.length==18)){
		if(getSelectedButton(document.forms[0].cert_type) != 0){		
		//alert("证件类型错误!");
			if(MsgError == ""){
				MsgError = MsgError + "证件类型错误";
			}
			else{
				MsgError = MsgError + "\n证件类型错误";
			}
		}
	} 
	
	/*
	 * 同一笔申请资料中，如现住址城市代码与单位城市代码相同，
	 * 现住址电话区号与单位电话区号、现住址电话号码位数与单位固定电话号码位数、
	 * 现住址邮编前三位与单位邮编前三位应该保持一致，如不一致，
	 * 系统分别依次提示：“现住址电话区号或单位电话区号错误”，
	 * “现住址电话位数与单位电话位数不一致”、
	 * “现住址邮编前三位与单位邮编前三位不一致”，但可以强行通过。
	 *
	 * 注意：“现住址电话位数与单位电话位数不一致”的校验没有写
	 */
	if(document.forms[0].pre_city_code.value == document.forms[0].comp_city_code.value){
		if((document.forms[0].pre_zone_no.value) != document.forms[0].comp_zone_no.value){
			//alert("现住址电话区号或单位电话区号错误");
			if(MsgError == ""){
				MsgError = MsgError + "现住址电话区号或学校电话区号错误";}
			else{
				MsgError = MsgError + "\n现住址电话区号或学校电话区号错误";}
		}
		if((document.forms[0].pre_post.value).substring(0,3) != (document.forms[0].comp_post.value).substring(0,3)){
			//alert("现住址邮编前三位与单位邮编前三位不一致");
			if(MsgError == ""){
				MsgError = MsgError + "现住址邮编前三位与学校邮编前三位不一致";}
			else{
				MsgError = MsgError + "\n现住址邮编前三位与学校邮编前三位不一致";}
		}
	}
	if(MsgError != ""){//若MsgError不为空，则报错
		alert(MsgError);
	}
	  
	return true;	//检验结束,返回true
	
}


/*=============================以下为所用到的子函数====================*/
function rmlines(str){
	var s = '';
	for(var i = 0;i<str.length;i++){
		
		if(!(str.charAt(i) == '\x0a' || str.charAt(i) == '\x0d')){
			s += str.charAt(i);
		}
	}
	return s;
}

//对象不能为空或者开头为0
function checkYForGet(obj){
	str=obj.value;
	if(str.length==0) return false;
	if(str.charAt(0)=='0')return false;
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

//检查生日
function checkDate(obj){
	str=obj.value;
	if(str.length==0){alert("日期输入不正确");return false;}
	Y=str.substring(0,4);
	M=str.substring(4,6);
	D=str.substring(6,8);
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

//检查身份证号码
function isIDNO(obj){
	var s = trim(obj.value);
	if (!((s.length==18) || (s.length==15))){
		//alert(s.length);
		alert("身份证号码长度只能为15或18位");
		
		return false;
	}
	else if (s.length==15){
		if (!warnCharsInBag(s, "0123456789")){
			alert("身份证号码长为15位时,必须全部是数字");
			return false;
		}
	}
	else if (obj.value.length==18){
		if (!isNumber(s)){
			var sub=s.substring(1,17)
			if (!warnCharsInBag(sub, "0123456789")){
				alert("身份证号码长为18位时,前17位必须全部是数字");
				return false;
			}
			if(!warnCharsInBag(s, "0123456789")){							    
				if ((s.charAt(17)!="x") && (s.charAt(17)!="X")){
					alert("身份证号码只能在最后一位可以输入字符X或x");
				    return false;
				}
			}
		}
	}
	return true;
  }
// 判断字符串的字符是否在给定的字符串范围内
function warnCharsInBag(s,Bag)
{
   var SubString,ContainString;
   var NotContain=false; //判断该字符或串是否包含在内部
   var IsEqual=false; //判断某个字符是否与包中字符相等
   
   SubString=new String("");
   ContainString=new String("");
   SubString=s;
   ContainString=Bag;
   //处理单字符
   if (SubString.length=1)
   {
      SubString=SubString+"a";
      ContainString=ContainString+"a";
   }
   for(var SubIndex=0; SubIndex<SubString.length-1; SubIndex++)
   {
      IsEqual=false;//假设该子字符不在包中
      for (var ContainIndex=0; 
           ContainIndex<ContainString.length-1; 
           ContainIndex++)
      {
           if(SubString.charAt(SubIndex)==ContainString.charAt(ContainIndex))
           {
               IsEqual=true;
               break;
           }
       }   //如果该字符在包中，跳出，并设置为相等
      if (!(IsEqual))
      {//如果不在包中，可以直接推出
          NotContain=true;
          return false;
          break;
      }
   }
   if(!NotContain)
   {
      return true;//循环中出来如果还没有返回，这证明包含在包中
   }
   else
   {
      return false;
   }
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
// 判断是否为空.
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
//检查电子信箱
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

//校验手机号码：必须以数字开头，除数字外，可含有“-” 
function isMobile(s){ 
	var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/; 
	if (!patrn.exec(s)) return false ;
	return true ;
} 

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

//检查s是否为整数
//1000或100.00，不能是1000.23
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

/*
检查输入字符串是否是英文字母
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

//将字符串中的空滤掉
function KillSpace(s){
    var s1="";
     if (isEmpty(s))//判断是否为空
     {	
	    return s;
     }else{//不为空
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
//判断字符串中是否包括中文或其他不合法的字符 
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

//国籍分赋值
function mainCardNationality(selObj){ //v3.0
	var selectedIndex = getSelectedButton(document.forms[0].select);
 	document.forms[0].nationality.value=selObj.options[selObj.selectedIndex].value;
  
}

var country_code;
country_code="<option value=\"\">--请选择国籍--</option><option value=\"CHN\">CHN-中国</option> <option value=\"HKG\">HKG-香港</option> <option value=\"MAC\">MAC-澳门</option> <option value=\"TWN\">TWN-中国台湾</option> <option value=\"USA\">USA-美国</option> <option value=\"GBR\">GBR-英国</option> <option value=\"FRA\">FRA-法国</option> <option value=\"DEU\">DEU-德国</option> <option value=\"ITA\">ITA-意大利</option> <option value=\"GRC\">GRC-希腊</option> <option value=\"RUS\">RUS-俄罗斯联邦</option> <option value=\"ESP\">ESP-西班牙</option> <option value=\"PRT\">PRT-葡萄牙</option> <option value=\"SWE\">SWE-瑞典</option> <option value=\"CHE\">CHE-瑞士</option> <option value=\"PRK\">PRK-朝鲜</option> <option value=\"KOR\">KOR-韩国</option> <option value=\"IND\">IND-印度</option> <option value=\"AUS\">AUS-澳大利亚</option> <option value=\"ISR\">ISR-以色列</option> <option value=\"JPN\">JPN-日本</option> <option value=\"BRA\">BRA-巴西</option> <option value=\"AFG\">AFG-阿富汗</option> <option value=\"ALB\">ALB-阿尔巴尼亚</option> <option value=\"ADZ\">ADZ-阿尔及利亚</option> <option value=\"ASM\">ASM-美属萨摩亚群岛</option> <option value=\"AND\">AND-安道尔</option> <option value=\"AGO\">AGO-安哥拉</option> <option value=\"AIA\">AIA-安圭拉</option> <option value=\"ATG\">ATG-安提瓜及巴布达</option> <option value=\"ARG\">ARG-阿根廷</option> <option value=\"ARM\">ARM-亚美尼亚</option> <option value=\"ABW\">ABW-阿卢巴</option> <option value=\"AUT\">AUT-奥地利</option> <option value=\"AZE\">AZE-阿塞拜疆</option> <option value=\"BHS\">BHS-巴哈马</option> <option value=\"BHR\">BHR-巴林</option> <option value=\"BGD\">BGD-孟加拉</option> <option value=\"BRB\">BRB-巴巴多斯</option> <option value=\"BLR\">BLR-白俄罗斯</option> <option value=\"BEL\">BEL-比利时</option> <option value=\"BLZ\">BLZ-伯利兹</option> <option value=\"BEN\">BEN-贝宁湾</option> <option value=\"BMU\">BMU-百慕达群岛</option> <option value=\"BTN\">BTN-不丹</option> <option value=\"BOL\">BOL-玻利维亚</option> <option value=\"BIH\">BIH-波士尼亚赫塞哥维纳联邦</option> <option value=\"BWA\">BWA-博茨瓦纳</option> <option value=\"BRN\">BRN-汶莱</option> <option value=\"BGR\">BGR-保加利亚</option> <option value=\"BFA\">BFA-布基纳法索</option> <option value=\"BDI\">BDI-布隆迪</option> <option value=\"KHM\">KHM-高棉</option> <option value=\"CMR\">CMR-喀麦隆联合的共和国</option> <option value=\"CAN\">CAN-加拿大</option> <option value=\"TCD\">TCD-乍得</option> <option value=\"CHL\">CHL-智利</option> <option value=\"COL\">COL-哥伦比亚</option> <option value=\"COM\">COM-科摩洛</option> <option value=\"COG\">COG-刚果</option> <option value=\"COK\">COK-库克群岛</option> <option value=\"CRI\">CRI-哥斯达黎加</option> <option value=\"HRV\">HRV-克罗埃西亚</option> <option value=\"CUB\">CUB-古巴</option> <option value=\"CYP\">CYP-赛普勒斯</option> <option value=\"CZE\">CZE-捷克</option> <option value=\"DNK\">DNK-丹麦</option> <option value=\"DJI\">DJI-吉布提</option> <option value=\"DMA\">DMA-多米尼加联邦</option> <option value=\"DOM\">DOM-多米尼加共和国</option> <option value=\"TMP\">TMP-东帝汶</option> <option value=\"ECU\">ECU-厄瓜多尔</option> <option value=\"EGY\">EGY-埃及</option> <option value=\"SLV\">SLV-萨尔瓦多</option> <option value=\"GNQ\">GNQ-赤道几内亚</option> <option value=\"ERI\">ERI-依利垂亚</option> <option value=\"EST\">EST-爱沙尼亚</option> <option value=\"ETH\">ETH-埃塞俄比亚</option> <option value=\"FJI\">FJI-斐济</option> <option value=\"FIN\">FIN-芬兰</option> <option value=\"GAB\">GAB-加彭</option> <option value=\"GMB\">GMB-冈比亚</option> <option value=\"GEO\">GEO-乔治亚州</option> <option value=\"GHA\">GHA-迦纳</option> <option value=\"GIB\">GIB-直布罗陀</option> <option value=\"GRL\">GRL-格陵兰</option> <option value=\"GRD\">GRD-格林纳达</option> <option value=\"GLP\">GLP-哥德普洛岛</option> <option value=\"GUM\">GUM-关岛</option> <option value=\"GTM\">GTM-危地马拉</option> <option value=\"GIN\">GIN-几内亚</option> <option value=\"GNB\">GNB-几内亚比绍共和国</option> <option value=\"GUY\">GUY-圭亚那</option> <option value=\"HTI\">HTI-海地</option> <option value=\"HMD\">HMD-HEARD AND MCDONALD IS</option> <option value=\"HND\">HND-洪都拉斯</option> <option value=\"HUN\">HUN-匈牙利</option> <option value=\"ISL\">ISL-冰岛</option> <option value=\"IDN\">IDN-印尼</option> <option value=\"IRN\">IRN-伊朗王国</option> <option value=\"IRQ\">IRQ-伊拉克</option> <option value=\"IRL\">IRL-爱尔兰</option> <option value=\"CIV\">CIV-象牙海岸</option> <option value=\"JAM\">JAM-牙买加</option> <option value=\"JOR\">JOR-约旦</option> <option value=\"KAZ\">KAZ-哈萨克</option> <option value=\"KEN\">KEN-肯尼亚</option> <option value=\"KIR\">KIR-吉尔巴斯</option> <option value=\"KWT\">KWT-科威特</option> <option value=\"KGZ\">KGZ-吉尔吉斯</option> <option value=\"LAO\">LAO-老挝</option> <option value=\"LVA\">LVA-拉脱维亚</option> <option value=\"LBN\">LBN-黎巴嫩</option> <option value=\"LSO\">LSO-莱索托</option> <option value=\"LBR\">LBR-利比里亚</option> <option value=\"LBY\">LBY-利比亚</option> <option value=\"LIE\">LIE-列支敦士登</option> <option value=\"LTU\">LTU-立陶宛</option> <option value=\"LUX\">LUX-卢森堡</option> <option value=\"MKD\">MKD-马其顿</option> <option value=\"MDG\">MDG-马达加斯加</option> <option value=\"MWI\">MWI-马拉维</option> <option value=\"MYS\">MYS-马来西亚</option> <option value=\"MDV\">MDV-马尔代夫</option> <option value=\"MLI\">MLI-马里</option> <option value=\"MLT\">MLT-马尔他</option> <option value=\"MHL\">MHL-马歇尔岛</option> <option value=\"MTQ\">MTQ-马提尼克岛</option> <option value=\"MRT\">MRT-毛里塔尼亚</option> <option value=\"MUS\">MUS-毛里求斯</option> <option value=\"MYT\">MYT-马约特</option> <option value=\"MEX\">MEX-墨西哥</option> <option value=\"FSM\">FSM-密克罗尼西亚</option> <option value=\"MDA\">MDA-摩尔多瓦共和国</option> <option value=\"MCO\">MCO-摩纳哥</option> <option value=\"MNG\">MNG-蒙古人</option> <option value=\"MSR\">MSR-蒙特色纳</option> <option value=\"MAR\">MAR-摩洛哥</option> <option value=\"MOZ\">MOZ-莫桑比克</option> <option value=\"MMR\">MMR-缅甸</option> <option value=\"NAM\">NAM-那米比亚</option> <option value=\"NRU\">NRU-瑙鲁</option> <option value=\"NPL\">NPL-尼泊尔</option> <option value=\"NLD\">NLD-荷兰</option> <option value=\"ANT\">ANT-荷兰安的列斯群岛</option> <option value=\"NCL\">NCL-新加勒多尼亚</option> <option value=\"NZL\">NZL-新西兰</option> <option value=\"NIC\">NIC-尼加拉瓜</option> <option value=\"NER\">NER-尼日尔</option> <option value=\"NGA\">NGA-尼日利亚</option> <option value=\"NIU\">NIU-纽鄂岛</option> <option value=\"NFK\">NFK-诺福克岛</option> <option value=\"MNP\">MNP-北方马里亚纳群岛</option> <option value=\"NOR\">NOR-挪威</option> <option value=\"OMN\">OMN-阿曼</option> <option value=\"PAK\">PAK-巴基斯坦</option> <option value=\"PLW\">PLW-帛琉</option> <option value=\"PAN\">PAN-巴拿马</option> <option value=\"PNG\">PNG-巴布亚新几内亚</option> <option value=\"PRY\">PRY-巴拉圭</option> <option value=\"PER\">PER-秘鲁</option> <option value=\"PHL\">PHL-菲律宾共和国</option> <option value=\"PCN\">PCN-皮特凯恩岛</option> <option value=\"POL\">POL-波兰</option> <option value=\"PRI\">PRI-波多黎各</option> <option value=\"QAT\">QAT-卡塔尔</option> <option value=\"REU\">REU-团圆</option> <option value=\"ROM\">ROM-罗马尼亚</option> <option value=\"RWA\">RWA-卢旺达</option> <option value=\"WSM\">WSM-美属萨摩亚</option> <option value=\"SMR\">SMR-圣马力诺</option> <option value=\"STP\">STP-圣多美和普林西比</option> <option value=\"SAU\">SAU-沙特阿拉伯</option> <option value=\"SEN\">SEN-塞内加尔</option> <option value=\"SYC\">SYC-塞锡尔群岛</option> <option value=\"SLE\">SLE-塞拉利昂</option> <option value=\"SGP\">SGP-新加坡</option> <option value=\"SVK\">SVK-斯洛伐克</option> <option value=\"SVN\">SVN-斯洛文尼亚</option> <option value=\"SLB\">SLB-所罗门岛</option> <option value=\"SOM\">SOM-索马里</option> <option value=\"ZAF\">ZAF-南非</option> <option value=\"SGS\">SGS-格鲁吉亚</option> <option value=\"LKA\">LKA-斯里兰卡</option> <option value=\"SDN\">SDN-苏丹</option> <option value=\"SDA\">SDA-苏丹航空公司</option> <option value=\"SUR\">SUR-苏里南</option> <option value=\"SJM\">SJM-冷岸及央麦恩群岛</option> <option value=\"SWZ\">SWZ-斯威士兰</option> <option value=\"SYR\">SYR-叙利亚</option> <option value=\"TJK\">TJK-塔吉克</option> <option value=\"TZA\">TZA-坦桑尼亚联合的共和国</option> <option value=\"THA\">THA-泰国</option> <option value=\"TGO\">TGO-多哥</option> <option value=\"TKL\">TKL-托克劳</option> <option value=\"TON\">TON-汤加</option> <option value=\"TTO\">TTO-千里达托贝哥共和国</option> <option value=\"TUN\">TUN-北非的共和国</option> <option value=\"TUR\">TUR-土耳其</option> <option value=\"TKM\">TKM-土库曼</option> <option value=\"TUV\">TUV-图瓦卢</option> <option value=\"UGA\">UGA-乌干达</option> <option value=\"UKR\">UKR-乌克兰</option> <option value=\"ARE\">ARE-阿拉伯联合酋长国</option> <option value=\"URY\">URY-乌拉圭</option> <option value=\"UZB\">UZB-乌兹别克斯坦</option> <option value=\"VUT\">VUT-万那度</option> <option value=\"VAT\">VAT-罗马教庭</option> <option value=\"VEN\">VEN-委内瑞拉</option> <option value=\"VNM\">VNM-越南</option> <option value=\"ESH\">ESH-西撒哈拉</option> <option value=\"YEM\">YEM-也门</option> <option value=\"YUG\">YUG-南斯拉夫</option> <option value=\"ZAR\">ZAR-扎伊尔</option> <option value=\"ZMB\">ZMB-赞比亚</option> <option value=\"ZWE\">ZWE-津巴布韦</option>";







