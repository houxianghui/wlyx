/*
用于处理输入数据的函数
为了使本脚本在IE和NS下无误运行，表单元素的调用使用document.forms[0].test1方式
而不用form.test1方式
*/
 

//只允许数字-use
function onlyNum(obj){
	if(obj.value!=obj.value.replace(/[^\u0030-\u0039]/g,''))
	obj.value=obj.value.replace(/[^\u0030-\u0039]/g,'');
}
//只运行英文,并自动将英文转化为大写-use
function onlyEngToUpperCase(obj){
	obj.value=obj.value.replace(/[^\u0041-\u007A]/g,'');
	obj.value=obj.value.toUpperCase();
}
//中文姓名-use
function onlyName(obj){
	if(obj.value!=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0041-\u005A\u0061-\u007A\u0020]/g,''))
		obj.value=obj.value.replace(/[^\u4E00-\u9FA5\u00B7\u0041-\u005A\u0061-\u007A\u0020]/g,'');
}
  
//不能输入中文
function noChinese(obj){
	if(obj.value!=obj.value.replace(/[\u4E00-\u9FA5\u00B7\u0020]/g,''))
		obj.value=obj.value.replace(/[\u4E00-\u9FA5\u00B7\u0020]/g,'');
}
/*
去除字符串两边的空字符
*/
function trim(s)
{
	return trimRight(trimLeft(s))
}
/****************************************/
/* Trim the left blank of the string    */
/****************************************/
function trimLeft(s) {
	while (s.charAt(0) ==" " ||s.charAt(0) =="" ){
		s = s.substr(1,s.length-1)
	}
	return s;
}
/*****************************************/
/* Trim the right blank of the string    */
/*****************************************/
function trimRight(s) {
	while (s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "")	{
		s = s.substr(0,s.length-1)
	}
	return s;
}

/**
得到字符串的长度，中文字符长度为2
*/
function getStrLength( str ) {
    num = str.length;
    var arr = str.match(/[^\x00-\x80]/ig);
    if( arr != null )
        num += arr.length;
    return num;
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


/**
翻页页码检查函数
*/
function checkPageNO( pageNO, maxPageNO ) {
    if ( pageNO == "" ) {
        alert( "请填写页码！" );
        return false;
    } else if ( !isInteger( pageNO ) ) {
        alert( "您填写的页码不正确！" );
        return false;
    } else if ( parseInt( pageNO ) <= 0 ) {
        alert( "页码不能小于0！" );
        return false;
    } else if ( parseInt( pageNO ) > parseInt( maxPageNO ) ) {
        alert( "页码不能大于最大页码！" );
        return false;
    }
    return true;
}


/**
 *用于在列表页面显示带滚动条的固定区域，showScrollHeader方法显示头脚本，showScrollFooter方法显示尾脚本
 *作者：辛勇
 *时间：200603018
 */
function showScrollHeader() {
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.write("<div align=\"center\"><div   align=\"center\"   style=\"overflow:auto;width:98%\">");
	} else {
		document.write("<table width=\"98%\"  align=\"center\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">	<tr>");
		document.write("<td valign=\"top\"  align=\"left\">");
		document.write("<div   align=\"center\"   style=\"overflow:auto;width:100%\">");
	}
}

function showScrollFooter() {
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.write("</div>	</div>");
	} else {
		document.write("</div>	</td>	</tr></table>");
	}
}
function findObj(theObj, theDoc){
    var p, i, foundObj;
    if(!theDoc) theDoc = document;  
    if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)  {
        theDoc = parent.frames[theObj.substring(p+1)].document;    
        theObj = theObj.substring(0,p);  
    }  
    if(!(foundObj = theDoc[theObj]) && theDoc.all) foundObj = theDoc.all[theObj];  
    for(i=0; !foundObj && i < theDoc.forms.length; i++)  foundObj = theDoc.forms[i][theObj];  
    for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++) foundObj = findObj(theObj,theDoc.layers[i].document);  
    if(!foundObj && document.getElementById) foundObj = document.getElementById(theObj); return foundObj;
}
function addRow(tbName,innerHtmls){ //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var rowIndex = findObj(tbName+".index",document);
    var rowID = parseInt(rowIndex.value);
   
    var frame = findObj(tbName,document);
    //添加行
    var newTR = frame.insertRow(frame.rows.length);
    newTR.id = tbName + "_tr_"+rowID;
    var newNameTD=newTR.insertCell(0);
	newNameTD.innerHTML = newTR.rowIndex.toString();
	var i = 0;j=1;
   	for(;i<innerHtmls.length;i++,j++){
	   	var newNameTD=newTR.insertCell(j);
	     //添加列内容
	    newNameTD.innerHTML = innerHtmls[i];
   	}
    //添加列:删除按钮
    var newDeleteTD=newTR.insertCell(j);
    //添加列内容
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteRow('"+tbName+"_tr_" + rowID + "','"+tbName+"')\">删除</a></div>";
   
    //将行号推进下一行
    rowIndex.value = (rowID + 1).toString() ;
}
//删除指定行
function DeleteRow(rowid,tbName){
     var signFrame = findObj(tbName,document);
     var signItem = findObj(rowid,document);
    
     //获取将要删除的行的Index
     var rowIndex = signItem.rowIndex;
    
     //删除指定Index的行
     signFrame.deleteRow(rowIndex);
    
     // 重新排列序号，如果没有序号，这一步省略
     for(i=rowIndex;i<signFrame.rows.length;i++){
      signFrame.rows[i].cells[0].innerHTML = i.toString();
     }
}        
