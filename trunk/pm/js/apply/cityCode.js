function createHTTPObject() {
	var xmlhttp;

	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			xmlhttp = false;
		}
	}

	if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
		try {
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			xmlhttp = false;
		}
	}

	if (!xmlhttp && window.createRequest) {
		try {
			xmlhttp = window.createRequest();
		} catch (e) {
			xmlhttp = false;
		}
	}

	return xmlhttp;
}
function getCityZone(city, reqId) {
	var url = "CityCode.do?act=getCityZone&city=" + city.value+"&timeStamp="+Math.random();
	var xmlHttp = createHTTPObject();
	xmlHttp.open("POST", url);
	xmlHttp.onreadystatechange = function() {
		setCityZone(xmlHttp,reqId);
	};
	xmlHttp.send(null);
}
function setCityZone(xmlHttp,reqId) {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var code = document.getElementById(reqId);
			code.value = xmlHttp.responseText;
		}
	}
}
