/*
�����¼���Ӧ�ĺ���
Ϊ��ʹ���ű���IE��NS���������У���Ԫ�صĵ���ʹ��document.forms[0].test1��ʽ
������form.test1��ʽ
*/

/*********************************************
  ��WINDOW.OPEN���������´���
  �������ṩͳһ�ĵ������ڵ���ʽ
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

