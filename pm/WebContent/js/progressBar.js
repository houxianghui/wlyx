// JavaScript Document

var progressEnd = 10;		// set to number of progress <span>'s.
var progressColor = "#87CEFA";	// set to progress bar color
var progressInterval = 200;	// set to time between updates (milli-seconds)
var progressbg= "images/progressBar/bg_blue1.gif";


var progressAt = progressEnd;
var progressTimer;

function progressBar(){
progress_update();
}
function progress_clear() {
	for (var i = 1; i <= progressEnd; i++) document.getElementById('progress'+i).style.background = '';
	progressAt = 0;
}
function progress_update() {
	document.all.calender.style.visibility ="visible";
	document.all.calender.style.position ="absolute";
	progressAt++;
	if (progressAt > progressEnd) progress_clear();
	else //document.getElementById('progress'+progressAt).style.background = 'url("images/progressBar/bg_blue1.gif")';
		document.getElementById('progress'+progressAt).style.backgroundColor = progressColor;
	progressTimer = setTimeout('progress_update()',100);
}
function progress_stop() {
	document.all.calender.style.visibility ="hidden";
	document.all.calender.style.position ="absolute";
	clearTimeout(progressTimer);
	progress_clear();
}


function showBar(){

	var appName = navigator.appName;   //???????
	//if(appName == "Microsoft Internet Explorer"){	
	//	var str = "<div id=progress_bar style=position:absolute;left:0px;top:0px;><table name=adfasdf border=1 bordercolor=#FFD700 cellpadding=0 cellspacing=0 width=300 height=30 id=calender style=visibility:hidden;position:absolute> <tr>"+
	//			 " <td name=ddffd id=test bgcolor=#FFDEAD  height=40 width=300 align=left style=visibility:hidden;position:absolute><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ÕýÔÚ²éÑ¯£º"+
	//			   "<span id=progress1 >&nbsp;&nbsp;</span><span id=progress2>&nbsp;&nbsp;</span>&nbsp;"+
	//			  "<span id=progress3>&nbsp;&nbsp;</span><span id=progress4>&nbsp;&nbsp;</span>"+
	//			"  <span id=progress5>&nbsp;&nbsp;</span><span id=progress6>&nbsp;&nbsp;</span>"+
	//			"   <span id=progress7>&nbsp;&nbsp;</span><span id=progress8>&nbsp;&nbsp;</span>"+
	//			"  <span id=progress9>&nbsp;&nbsp;</span><span id=progress10>&nbsp;&nbsp;</span><br>"+
	//				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> </tr> </table></div>";
	//	document.write(str);
	//	progress_update();
	
	
	//}
	//else
	//{
		var str =  
		"<div id=progress_bar style=position:absolute;left:0px;top:0px;><table cellpadding=0 cellspacing=0 width=0 height=0 id=calender style=visibility:hidden;position:absolute>"+
		"<tr><td name=ddffd id=test style=visibility:hidden;position:absolute>"+
		"<span id=progress1><img src=images/Loading.gif ></img></span><br></td></tr></table></div>";
		document.write(str);		
	//	}
}
function beginLoading(){
	var appName = navigator.appName;
	if(appName == "Microsoft Internet Explorer"){
		var winHeight = document.body.clientHeight;
		var winWidth = document.body.clientWidth;		
		
		var x = winWidth/2 - 110;//for pic
		var y = winHeight/2 -60;//for pic
		
		//var x = winWidth/2 - 150;for span
		//var y = winHeight/2 - 20-60;for span
		document.getElementById('progress_bar').style.left = x+"px";
		document.getElementById('progress_bar').style.top = y+"px";
		
		document.getElementById('calender').style.visibility = 'visible';
		document.getElementById('test').style.visibility = 'visible';
	}
	else{
			var winHeight = document.body.clientHeight;
			var winWidth = document.body.clientWidth;

			var x = (winWidth/2 +180)/2;//for mozilla
			var y = (winHeight/2 +100)/2;//for mozilla
			//var x = (winWidth/2 - 150+40)/2;//for netscape
			//var y = (winHeight/2 - 50-80+60)/2;//for netscape			
			
			document.getElementById('progress_bar').style.left = x+"px";
			document.getElementById('progress_bar').style.top = y+"px";
			document.getElementById('calender').style.visibility = 'visible';
			document.getElementById('test').style.visibility = 'visible';
		}
}

function showPic(){
	
	var str =  
	"<div><table cellpadding=0 cellspacing=0 width=400 height=30 id=calender style=visibility:hidden;position:absolute>"+
	"<tr><td name=ddffd id=test style=visibility:hidden;position:absolute>"+
	"<span id=progress1><img src=images/loading.gif ></img></span><br></td></tr></table></div>";
	document.write(str);
	}
function picOut(){
	document.getElementById('calender').style.visibility = 'visible';
	document.getElementById('test').style.visibility = 'visible';

	}