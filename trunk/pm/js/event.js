/*
用于事件响应的函数
为了使本脚本在IE和NS下无误运行，表单元素的调用使用document.forms[0].test1方式
而不用form.test1方式
*/

/*********************************************
  用WINDOW.OPEN方法弹出新窗口
  本方法提供统一的弹出窗口的样式
 ********************************************/
function openWin( url, winName ) {

	return window.open( url, winName, 
			"left = 50, top = 50, width = 900, height = 525, scrollbars = yes, toolbar = no, location = no, menubar = no, resizable = no, status = yes" );
}


var pSelectTr = null;
var pSelectCls = "";
function _clickTr( obj ) {
    try {
    	var selectTr = obj;
        if ( pSelectTr != null) {
            pSelectTr.className = pSelectCls;
        }
        pSelectTr = selectTr;
        pSelectCls = selectTr.className;
        selectTr.className = "selectTr";
    } catch ( e ) {
        alert( "Error in selectTr()." );
    }
}

