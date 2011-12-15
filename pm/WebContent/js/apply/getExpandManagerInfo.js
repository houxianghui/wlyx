var xmlHttp;
var completeDiv;
var inputField;
var nameTable;
var nameTableBody;
var managerId;
function createXMLHttpRequest() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}

function initVars() {
	inputField = document.getElementById("managerName");
	nameTable = document.getElementById("name_table");
	completeDiv = document.getElementById("popup");
	nameTableBody = document.getElementById("name_table_body");
	managerId = document.getElementById("managerId");
}

function findNames() {
	if(true){
		return;
	}
	initVars();
	if (inputField.value.length > 0) {
		createXMLHttpRequest();
		var time= new Date();
		var url = "Ep_expand_manager_info.do?act=info&name="+inputField.value+"&time="+time;
		xmlHttp.open("GET", url, true);
		xmlHttp.onreadystatechange = callback;
		xmlHttp.send(null);
	} else {
		clearNames();
	}
}

function callback() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			setNames(xmlHttp.responseXML.getElementsByTagName("cert"));
			
		} else if (xmlHttp.status == 204) {
			clearNames();
		}
	}
}

function setNames(the_names) {
	clearNames();
	var size = the_names.length;
	setOffsets();
	if(size == 1){
		setManagerId(the_names);
		return;		
	}
	if(size == 0){
		clearNames();
		return;
	}
	var row, cell, txtNode;
	for ( var i = 0; i < size; i++) {
		var nextNode = the_names[i].firstChild.data;		
		row = document.createElement("tr");
		cell = document.createElement("td");

		cell.onmouseout = function() {
			this.className = 'mouseOver';
		};
		cell.onmouseover = function() {
			this.className = 'mouseOut';
		};
	
		cell.setAttribute("bgcolor", "#FFFAFA");
		cell.setAttribute("border", "0");
		cell.onclick = function() {
			//window.location.href="CardReq.do?act=newInit&prod="+nextValue;
			populateName(this);
			
		};

		txtNode = document.createTextNode(nextNode);
		cell.appendChild(txtNode);
		row.appendChild(cell);
		nameTableBody.appendChild(row);
	}
}

function setOffsets() {
	var end = inputField.offsetWidth;
	var left = calculateOffsetLeft(inputField);
	var top = calculateOffsetTop(inputField) + inputField.offsetHeight;

	completeDiv.style.border = "black 1px solid";
	completeDiv.style.left = left + "px";
	completeDiv.style.top = top + "px";
	nameTable.style.width = end + "px";
}

function calculateOffsetLeft(field) {
	return calculateOffset(field, "offsetLeft");
}

function calculateOffsetTop(field) {
	return calculateOffset(field, "offsetTop");
}

function calculateOffset(field, attr) {
	var offset = 0;
	while (field) {
		offset += field[attr];
		field = field.offsetParent;
	}
	return offset;
}

function populateName(cell) {
	var str = cell.firstChild.nodeValue;
	var list = str.split(",");
	clearNames();
	managerId.value=list[1];
}
function setManagerId(the_names){
	clearNames();
	var str = the_names[0].firstChild.data;
	var list = str.split(",");
	managerId.value = list[1];
}

function clearNames() {
	var ind = nameTableBody.childNodes.length;
	for ( var i = ind - 1; i >= 0; i--) {
		nameTableBody.removeChild(nameTableBody.childNodes[i]);
	}
	completeDiv.style.border = "none";
	managerId.value = "";
}


