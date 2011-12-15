// Node object

function Node(id, pid, name, url, leaf,title, target, icon, iconOpen, open) {

	this.id = id;

	this.pid = pid;

	this.name = name;

	this.url = url;
	
	this.leaf = leaf;

	this.title = title;

	this.target = target;

	this.icon = icon;

	this.iconOpen = iconOpen;

	this._io = open || false;

	this._is = false;

	this._ls = false;

	this._hc = false;

	this._ai = 0;

	this._p;

};



// Tree object

function dTree(objName,rootid,img) {

	this.config = {

		target					: null,
		
		linksAttr			        : null,//

		folderLinks			        : true,//文件夹上是否有链接

		useSelection		                : true,

		useCookies			        : false,

		useLines				: true,

		useIcons				: true,

		useStatusText		                : true,

		closeSameLevel	                        : true,
		
		//closeSameLevel                          : true,
		
		checkbox                                : false,
		
		radiobox                                : true,//只选择叶子
		 
		allnode                                 : true,//一次下载全部结点
		
		newLine                                 : 10, 
		
		inOrder					: false

	}

	this.icon = {
		point                                   : img+'images/DTreeIMG/point.gif',

		root                                    : img+'images/DTreeIMG/base.gif',

		folder                                  : img+'images/DTreeIMG/folder.gif',

		folderOpen                              : img+'images/DTreeIMG/folderopen.gif',

		node                                    : img+'images/DTreeIMG/page.gif',

		empty                                   : img+'images/DTreeIMG/empty.gif',

		line                                    : img+'images/DTreeIMG/line.gif',

		join                                    : img+'images/DTreeIMG/join.gif',

		joinBottom                              : img+'images/DTreeIMG/joinbottom.gif',

		plus                                    : img+'images/DTreeIMG/plus.gif',

		plusBottom                              : img+'images/DTreeIMG/plusbottom.gif',

		minus                                   : img+'images/DTreeIMG/minus.gif',

		minusBottom                             : img+'images/DTreeIMG/minusbottom.gif',
		
		enter                                   : img+'images/DTreeIMG/enter.gif',
		
		enterDown                               : img+'images/DTreeIMG/enterDown.gif',

		nlPlus                                  : img+'images/DTreeIMG/enter.gif',

		nlMinus                                 : img+'images/DTreeIMG/enterDown.gif'

	};

	this.obj = objName;

	this.aNodes = [];

	this.aIndent = [];

	this.root = new Node(-1);

	this.selectedNode = null;

	this.selectedFound = false;

	this.completed = false;
	this.rootid=rootid;

};



// Adds a new node to the node array

dTree.prototype.add = function(id, pid, name, url, leaf,title, target, icon, iconOpen, open) {

	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, leaf,title, target, icon, iconOpen, open);//增加一个节点，数组长度加一，下一次在数组最后加节点时长度又增加

};



// Open/close all nodes

dTree.prototype.openAll = function() {

	this.oAll(true);

};

dTree.prototype.closeAll = function() {

	this.oAll(false);

};



// Outputs the tree to the page

dTree.prototype.toString = function() {

	var str = '<table cellpadding=0  cellspacing=0 border=0 class="dtree">\n';

	if (document.getElementById) {

		if (this.config.useCookies) this.selectedNode = this.getSelected();

		str += this.addNode(this.root);

	} else str += 'Browser not supported.';

	str += '</table>';

	if (!this.selectedFound) this.selectedNode = null;

	this.completed = true;
	//test.value=str;
	return str;

};



// Creates the tree structure

dTree.prototype.addNode = function(pNode) {

	var str = '';

	var n=0;

	if (this.config.inOrder) n = pNode._ai;

	for (n; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == pNode.id) {

			var cn = this.aNodes[n];

			cn._p = pNode;

			cn._ai = n;
			this.setCS(cn);

			if (!cn.target && this.config.target) cn.target = this.config.target;

			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id);

			if (!this.config.folderLinks && cn._hc) cn.url = null;

			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) {

					cn._is = true;

					this.selectedNode = n;

					this.selectedFound = true;

			}

			str += this.node(cn, n);

			if (cn._ls) break;//最后一个子节点

		}

	}

	return str;

};



// Creates the node icon, url and text 节点部分,添加复选框(将复选框的名称作为参数，也可控制复选框的添加)

dTree.prototype.node = function(node, nodeId) {
	var str='<tr><td>';
	    str += '<table cellpadding=0  cellspacing=0 border=0 class="dTreeNode"> <tr><td valign="top">' + this.indent(node, nodeId);
	    str +='</td><td><table cellpadding=0 cellspacing=0 border=0 ><tr><td>';
	if(this.config.checkbox)
		str+=' <input type="checkbox" name="chkbox"  value="'+node.id+'" id="chk'+node.id+'"> ';
	if(this.config.radiobox&&!node._hc)
		str+=' <input type="radio" name="radiobox"  value="'+node.id+'" id="chk'+node.id+'"> ';

	if (this.config.useIcons) {

		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);

		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;

		if (this.root.id == node.pid) {

			node.icon = this.icon.root;

			node.iconOpen = this.icon.root;

		}

		str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.icon) + '" alt="" />';

	}
	
	if (node.url) {
		//alert(node.url);
		//str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + this.config.linksAttr+'&root_id='+this.rootid+'&docId='+node.id+'"';
		str += '<a onfocus="blur()" id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url+'"';
		
		
		if (node.title) str += ' title="' + node.title + '"';

		if (node.target) str += ' target="' + node.target + '"';

		if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';

		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))

			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';

		str += '>';

	}

	else{ 
		str += '<a onfocus="blur()" id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' +this.config.linksAttr+'javascript: ' + this.obj + '.o(' + nodeId + ');"';

		if (node.title) str += ' title="' + node.title + '"';

		if (node.target) str += ' target="' + node.target + '"';

		if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';

		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))

			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';

		str += '>';
		
		//if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id){//非根节点，非叶子节点,不要文件夹链接或没有联接

		//str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">';//如果是文件夹可以展开
		//}
	}

	str += this.brString(node.name,this.config.newLine);

	//if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>';

	str += '</a></td></tr></table></td></tr></table></td></tr>';

	if (node._hc) {
		str +='<tr><td>';

		str += '<table id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';

		str += this.addNode(node);//递归添加子节点

		str += '</table></td></tr>';

	}

	this.aIndent.pop();

	return str;

};//树分两层,根节点和直接子节点，再下层节点就是以子节点为根的树

dTree.prototype.brString= function(str,n) {
	var len=str.length;
	var strOut=str;
	if(len>n){
		var str1=str.substring(0,n);
		strOut=str1+"<br>";
		var str2=str.substring(n,len);
		strOut+=this.brString(str2,n);
	}
	return str;
};

// Adds the empty and line icons 节点前的空格图标部分，展开关闭操作部分

dTree.prototype.indent = function(node, nodeId) {

	var str = '';

	if (this.root.id != node.pid) {

		for (var n=0; n<this.aIndent.length; n++)

			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" width="5" height="8"/>';

		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);

		if (node._hc) {

			str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');"><img id="j' + this.obj + nodeId + '" src="';

			if (!this.config.useLines) str += (node._io) ? (this.config.allnode ? this.icon.enterDown : this.icon.nlMinus) : (this.config.allnode ? this.icon.enter : this.icon.nlPlus);

			else str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );

			str +='" alt=""/></a>';

		} else {
			if(!this.config.allnode){
				if(node.leaf!="true"){
					str += '<a href="javascript: enterNext(\'' + node.id + '\',\''+node.pid+'\');"><img id="j' + this.obj + nodeId + '" src="';
				    str += this.icon.enter;
					str += '" alt="" /></a>';
				}else{
					str +="<img src='"+this.icon.point+"'>";
				}
			}
			else{
				str +="<img src='"+this.icon.point+"'>";
			}
		}
	} 

	return str;

};
	

dTree.prototype.getNodeName=function(nodeId){
	var name;
   for (var n=0; n<this.aNodes.length; n++) {
			if (this.aNodes[n].id == nodeId) {
				name=this.aNodes[n].name;
				break;
			}
		}
	return name;

};
dTree.prototype.getCheckedValue=function(){
   var value=[];
   var node=document.getElementsByTagName("INPUT");
   for(i=0;i<node.length;i++)if(node[i].checked&&node[i].name=="chkbox")
	   value[value.length]=node[i].value;
   return value;

};
dTree.prototype.getRadioValue=function(){
   var value=[];
   var node=document.getElementsByTagName("INPUT");
   for(i=0;i<node.length;i++)if(node[i].checked&&node[i].name=="radiobox")
	   value[value.length]=node[i].value;
   if(value.length==0)return -1;
   return value;

};
dTree.prototype.checkChild=function(nodeId){
   var value=[];
   var node=document.getElementsByTagName("INPUT");
   for(i=0;i<node.length;i++)if(node[i].checked&&node[i].name=="chkbox")
	   value[value.length]=node[i].value;
   return value;

};



// Checks if a node has any children and if it is the last sibling

dTree.prototype.setCS = function(node) {

	var lastId;

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id) node._hc = true;//该节点有子节点

		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id;

	}

	if (lastId==node.id) node._ls = true;

};



// Returns the selected node

dTree.prototype.getSelected = function() {

	var sn = this.getCookie('cs' + this.obj);

	return (sn) ? sn : null;

};



// Highlights the selected node

dTree.prototype.s = function(id) {

	if (!this.config.useSelection) return;

	var cn = this.aNodes[id];

	if (cn._hc && !this.config.folderLinks) return;

	if (this.selectedNode != id) {

		if (this.selectedNode || this.selectedNode==0) {

			eOld = document.getElementById("s" + this.obj + this.selectedNode);

			eOld.className = "node";

		}

		eNew = document.getElementById("s" + this.obj + id);

		eNew.className = "nodeSel";

		this.selectedNode = id;

		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);

	}

};



// Toggle Open or close

dTree.prototype.o = function(id) {

	var cn = this.aNodes[id];

	this.nodeStatus(!cn._io, id, cn._ls);

	cn._io = !cn._io;

	if (this.config.closeSameLevel) this.closeLevel(cn);

	if (this.config.useCookies) this.updateCookie();

};



// Open or close all nodes

dTree.prototype.oAll = function(status) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {

			this.nodeStatus(status, n, this.aNodes[n]._ls)

			this.aNodes[n]._io = status;

		}

	}

	if (this.config.useCookies) this.updateCookie();

};



// Opens the tree to a specific node

dTree.prototype.openTo = function(nId, bSelect, bFirst) {

	if (!bFirst) {

		for (var n=0; n<this.aNodes.length; n++) {

			if (this.aNodes[n].id == nId) {

				nId=n;

				break;

			}

		}

	}

	var cn=this.aNodes[nId];

	if (cn.pid==this.root.id || !cn._p) return;

	cn._io = true;

	cn._is = bSelect;

	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);

	if (this.completed && bSelect) this.s(cn._ai);

	else if (bSelect) this._sn=cn._ai;

	this.openTo(cn._p._ai, false, true);

};



// Closes all nodes on the same level as certain node

dTree.prototype.closeLevel = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {

			this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);

		}

	}

}



// Closes all children of a node

dTree.prototype.closeAllChildren = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {

			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);		

		}

	}

}



// Change the status of a node(open or closed)

dTree.prototype.nodeStatus = function(status, id, bottom) {

	eDiv	= document.getElementById('d' + this.obj + id);

	eJoin	= document.getElementById('j' + this.obj + id);

	if (this.config.useIcons) {

		eIcon	= document.getElementById('i' + this.obj + id);

		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;

	}

	eJoin.src = (this.config.useLines)?

	//((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):
	((status)?((this.config.allnode)?this.icon.minusBottom:this.icon.minus):((this.config.allnode)?this.icon.plusBottom:this.icon.plus)):

	((status)?(this.config.allnode ? this.icon.enterDown : this.icon.nlMinus):(this.config.allnode ? this.icon.enter : this.icon.nlPlus));

	eDiv.style.display = (status) ? 'block': 'none';

};





// [Cookie] Clears a cookie

dTree.prototype.clearCookie = function() {

	var now = new Date();

	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);

	this.setCookie('co'+this.obj, 'cookieValue', yesterday);

	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);

};



// [Cookie] Sets value in a cookie

dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {

	document.cookie =

		escape(cookieName) + '=' + escape(cookieValue)

		+ (expires ? '; expires=' + expires.toGMTString() : '')

		+ (path ? '; path=' + path : '')

		+ (domain ? '; domain=' + domain : '')

		+ (secure ? '; secure' : '');

};



// [Cookie] Gets a value from a cookie

dTree.prototype.getCookie = function(cookieName) {

	var cookieValue = '';

	var posName = document.cookie.indexOf(escape(cookieName) + '=');

	if (posName != -1) {

		var posValue = posName + (escape(cookieName) + '=').length;

		var endPos = document.cookie.indexOf(';', posValue);

		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));

		else cookieValue = unescape(document.cookie.substring(posValue));

	}

	return (cookieValue);

};



// [Cookie] Returns ids of open nodes as a string

dTree.prototype.updateCookie = function() {

	var str = '';

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {

			if (str) str += '.';

			str += this.aNodes[n].id;

		}

	}

	this.setCookie('co' + this.obj, str);

};



// [Cookie] Checks if a node id is in a cookie

dTree.prototype.isOpen = function(id) {

	var aOpen = this.getCookie('co' + this.obj).split('.');

	for (var n=0; n<aOpen.length; n++)

		if (aOpen[n] == id) return true;

	return false;

};



// If Push and pop is not implemented by the browser

if (!Array.prototype.push) {

	Array.prototype.push = function array_push() {

		for(var i=0;i<arguments.length;i++)

			this[this.length]=arguments[i];

		return this.length;

	}

};

if (!Array.prototype.pop) {

	Array.prototype.pop = function array_pop() {

		lastElement = this[this.length-1];

		this.length = Math.max(this.length-1,0);

		return lastElement;

	}

};