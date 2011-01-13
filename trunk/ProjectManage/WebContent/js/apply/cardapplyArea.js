//以下脚本均为地区代码用
function areaSelChange(areaname)
{  
	nowarea=nowpro.data[areaname.value]
	setdiv(nowarea)
	//alert(form1.Area_code.value);
	//window.areadiv.focus();
}
function provinceSelChange(selname,provincename)
{	var index="";  
    //判断是哪个地方的省份发生变化
	   index=provincename.value;
	   nowpro=pList.data[index];
	   nowarea=nowpro.data[0];
	   setdiv(nowarea);
	   pList.getOptionAreasString(index,selname);
	   //window.areadiv.focus();
}
function setdiv(nowarea){
	window.areacode.innerHTML=nowarea.code;
	window.areazone.innerHTML=nowarea.zone;
	window.areaexzone.innerHTML=nowarea.exzone;
	window.areamail.innerHTML=nowarea.mail;
	window.areaexmail.innerHTML=nowarea.exmail;
}
function area(index,name,code,zone,exzone,mail,exmail) 
{
	this.index=index
   	this.name=name;
   	this.code=code;
   	this.zone=zone;
   	this.exzone=exzone;
   	this.mail=mail;
   	this.exmail=exmail;
   //this.getDescString=account_getDescString;
}

function province(name,code) 
{
   this.data=new Array();
   this.name=name;
   this.code=code;
   this.add=area_add;
   //this.getOptionString=accountList_getOptionString;
}
function provinceList() 
{
   this.data=new Array();
   this.add=province_add;
   this.addAt=province_addAt;
   this.getOptionString=provinceList_getOptionString;
   this.getOptionAreasString=provinceList_getAreasOptionString;
}
function area_add(area)
{
	this.data[this.data.length]=area;
}
function province_add(province)
{
	this.data[this.data.length]=province;
}
function province_addAt(i,area)
{
	var province=this.data[i];
	province.add(area);
}
function provinceList_getOptionString()
{
	var temp="";
	for(var i=0;i<this.data.length;i++)
		temp+="<option value="+i+">"+this.data[i].name;
	return temp;
}
function provinceList_getAreasOptionString(index,selname)
{
	var temp="";
	var prov=this.data[index];
	selname.length=0;
	for(var i=0;i<prov.data.length;i++)
	{
		selname.length++;
		selname.options[i].text=prov.data[i].name;
		selname.options[i].value=prov.data[i].index;
		temp+="<option value="+prov.data[i].index+">"+prov.data[i].name;
	}
	return temp;
}


var pList=new provinceList();
pList.add(new province("直辖市","ZX"));
pList.addAt(0,new area(0,"北京市","BJ","10","无","100000","101200-102600"));
pList.addAt(0,new area(1,"天津市","TJ","22","无","300000","300270-301800"));
pList.addAt(0,new area(2,"上海市","SH","21","无","200000","200100-202100"));
pList.addAt(0,new area(3,"重庆市","CQ","23","无","400000","400700-409900"));
pList.add(new province("河北省","HE"));
pList.addAt(1,new area(0,"石家庄市","SJW","311","无","050000","052300"));
pList.addAt(1,new area(1,"唐山市","TGS","315","无","063000","无"));
pList.addAt(1,new area(2,"秦皇岛市","SHP","335","无","066000","066100"));
pList.addAt(1,new area(3,"邯郸市","HDS","310","无","056000","无"));
pList.addAt(1,new area(4,"邢台市","XTS","319","无","054000","051800"));
pList.addAt(1,new area(5,"保定市","BDS","312","无","071000","无"));
pList.addAt(1,new area(6,"张家口市","ZJK","313","无","075000","072700,073000"));
pList.addAt(1,new area(7,"承德市","CDS","314","无","067000","无"));
pList.addAt(1,new area(8,"沧州市","CGZ","317","无","061000","062100,062500"));
pList.addAt(1,new area(9,"廊坊市","LFS","316","无","102800","无"));
pList.addAt(1,new area(10,"衡水市","HGS","318","无","053000","无"));
pList.add(new province("山西省","SX"));
pList.addAt(2,new area(0,"太原市","TUN","351","无","030000","无"));
pList.addAt(2,new area(1,"大同市","DTG","352","无","037000","无"));
pList.addAt(2,new area(2,"阳泉市","YQS","353","无","045000","无"));
pList.addAt(2,new area(3,"长治市","CZS","355","无","046000","无"));
pList.addAt(2,new area(4,"晋城市","JCG","356","无","048000","无"));
pList.addAt(2,new area(5,"朔州市","SZJ","349","无","无","无"));
pList.addAt(2,new area(6,"忻州地区","XZD","无","无","无","无"));
pList.addAt(2,new area(7,"忻州市","XZS","350","无","034000","无"));
pList.addAt(2,new area(8,"吕梁地区","LLD","无","无","无","无"));
pList.addAt(2,new area(9,"孝义市","XOY","358","无","无","无"));
pList.addAt(2,new area(10,"晋中地区","JZD","无","无","无","无"));
pList.addAt(2,new area(11,"榆次市","YCI","354","无","030600","无"));
pList.addAt(2,new area(12,"临汾地区","LFD","无","无","无","无"));
pList.addAt(2,new area(13,"临汾市","LFN","357","无","041000","043000"));
pList.addAt(2,new area(14,"运城市","YCJ","359","无","044000","无"));
pList.add(new province("内蒙古","NM"));
pList.addAt(3,new area(0,"呼和浩特市","HET","471","无","010000","无"));
pList.addAt(3,new area(1,"包头市","BTS","472","无","014000","无"));
pList.addAt(3,new area(2,"乌海市","WHM","473","无","016000","无"));
pList.addAt(3,new area(3,"赤峰市","CFS","476","无","024000","无"));
pList.addAt(3,new area(4,"呼伦贝尔盟","HBM","无","无","无","无"));
pList.addAt(3,new area(5,"海拉尔市","HLR","470","无","021000","01400,162600,022100"));
pList.addAt(3,new area(6,"兴安盟","HIN","无","无","无","无"));
pList.addAt(3,new area(7,"乌兰浩特市","ULO","482","无","137400","无"));
pList.addAt(3,new area(8,"哲里木盟","JIR","无","无","无","无"));
pList.addAt(3,new area(9,"通辽市","TLO","475","无","028000","029200"));
pList.addAt(3,new area(10,"锡林郭勒盟","XGO","无","无","无","无"));
pList.addAt(3,new area(11,"二连浩特市","ERC","479","无","012600","026000"));
pList.addAt(3,new area(12,"乌兰察布盟","ULM","无","无","无","无"));
pList.addAt(3,new area(13,"集宁市","JIN","474","无","012000","无"));
pList.addAt(3,new area(14,"伊克昭盟","IJU","无","无","无","无"));
pList.addAt(3,new area(15,"东胜市","DSS","477","无","017000","无"));
pList.addAt(3,new area(16,"巴彦淖尔盟","BAS","无","无","无","无"));
pList.addAt(3,new area(17,"临河市","LNH","478","无","015000","无"));
pList.addAt(3,new area(18,"阿拉善盟","ALM","无","无","无","无"));
pList.addAt(3,new area(19,"阿拉善左旗","ALZ","无","无","无","无"));
pList.addAt(3,new area(20,"阿拉善右旗","ALY","无","无","无","无"));
pList.add(new province("辽宁省","LN"));
pList.addAt(4,new area(0,"沈阳市","SHE","24","无","110000","无"));
pList.addAt(4,new area(1,"大连市","DLG","411","无","116000","116300"));
pList.addAt(4,new area(2,"鞍山市","ASN","412","无","114000","114200"));
pList.addAt(4,new area(3,"抚顺市","FSN","413","无","113000","无"));
pList.addAt(4,new area(4,"本溪市","BXS","414","无","117000","无"));
pList.addAt(4,new area(5,"丹东市","DDG","415","无","118000","无"));
pList.addAt(4,new area(6,"锦州市","JNZ","416","无","121000","无"));
pList.addAt(4,new area(7,"营口市","YIK","417","无","115000","无"));
pList.addAt(4,new area(8,"阜新市","FXS","418","无","123000","无"));
pList.addAt(4,new area(9,"辽阳市","LYL","419","无","111000","无"));
pList.addAt(4,new area(10,"盘锦市","PJS","427","无","124000","无"));
pList.addAt(4,new area(11,"铁岭市","TLS","410","无","112000","112700"));
pList.addAt(4,new area(12,"朝阳市","CYS","421","无","122000","122100"));
pList.addAt(4,new area(13,"葫芦岛市","HLD","429","无","121500","121600"));
pList.add(new province("吉林省","JL"));
pList.addAt(5,new area(0,"长春市","CGO","431","无","130000","无"));
pList.addAt(5,new area(1,"吉林市","JLS","423","4423","132000","132400"));
pList.addAt(5,new area(2,"四平市","SLH","434","47441","136000","136100"));
pList.addAt(5,new area(3,"辽源市","LYH","437","无","136200","无"));
pList.addAt(5,new area(4,"通化市","THS","435","4453,4454","134000","135000,134200"));
pList.addAt(5,new area(5,"白山市","BSN","无","4461","无","137100"));
pList.addAt(5,new area(6,"松原市","SYU","无","无","无","132100"));
pList.addAt(5,new area(7,"白城市","BCS","436","无","137000","无"));
pList.addAt(5,new area(8,"延边朝鲜族自治州","YBZ","无","无","无","无"));
pList.addAt(5,new area(9, "延吉市","YNJ","433","4438,4436,4435","133000","133400,133100,133700"));
pList.addAt(5,new area(10,"浑江市","无","134300","439","无","无"));
pList.addAt(5,new area(11,"阿城市","无","150300","450","无","无"));
pList.add(new province("黑龙江","HL"));
pList.addAt(6,new area(0,"哈尔滨市","HRB","451","无","150000","无"));
pList.addAt(6,new area(1,"齐齐哈尔市","NDG","452","无","161000","无"));
pList.addAt(6,new area(2,"鸡西市","JXI","4617","无","158100","无"));
pList.addAt(6,new area(3,"鹤岗市","HEG","468","无","154100","无"));
pList.addAt(6,new area(4,"双鸭山","SYS","4619","无","155100","无"));
pList.addAt(6,new area(5,"大庆市","DQG","459","无","163000","无"));
pList.addAt(6,new area(6,"伊春市","YCH","458","无","153000","无"));
pList.addAt(6,new area(7,"佳木斯市","JMU","454","无","156000","156400"));
pList.addAt(6,new area(8,"七台河市","QTH","4640","无","154600","无"));
pList.addAt(6,new area(9,"牡丹江市","MDG","453","4638","157000","157300"));
pList.addAt(6,new area(10,"黑河市","HEK","456","4661,4678","164300","164000,164500"));
pList.addAt(6,new area(11,"绥化地区","SHD","无","无","无","无"));
pList.addAt(6,new area(12,"绥化市","SUH","455","4616","152000","151100"));
pList.addAt(6,new area(13,"大兴安岭地区","DHL","无","无","无","无"));
pList.addAt(6,new area(14,"呼玛县","HUM","无","457","无","无"));
pList.add(new province("江苏省","JS"));
pList.addAt(7,new area(0,"南京市","NKG","25","无","210000","无"));
pList.addAt(7,new area(1,"无锡市","WUX","510","5218,5217","214000","214200,214400"));
pList.addAt(7,new area(2,"徐州市","XUZ","516","无","221000","无"));
pList.addAt(7,new area(3,"常州市","CZX","519","无","213000","无"));
pList.addAt(7,new area(4,"苏州市","SZH","512","无","215000","215500"));
pList.addAt(7,new area(5,"南通市","NTG","513","无","226000","无"));
pList.addAt(7,new area(6,"连云港市","LYG","518","无","222000","无"));
pList.addAt(7,new area(7,"淮阴市","HYS","517","无","223000","223200"));
pList.addAt(7,new area(8,"盐城市","YCK","515","5250","224000","224200"));
pList.addAt(7,new area(9,"扬州市","YZH","514","5291","225000","211400"));
pList.addAt(7,new area(10,"镇江市","ZHE","511","5211","212000","212300"));
pList.addAt(7,new area(11,"泰州市","TZS","5241","5245","225300","225700"));
pList.addAt(7,new area(12,"宿迁市","SUQ","527","无","223800","无"));
pList.add(new province("浙江省","ZJ"));
pList.addAt(8,new area(0,"杭州市","HGH","571","无","310000","311200"));
pList.addAt(8,new area(1,"宁波市","NGB","574","5844","315000","315400"));
pList.addAt(8,new area(2,"温州市","WNZ","577","5874","325000","325200"));
pList.addAt(8,new area(3,"嘉兴市","JIX","573","无","314000","314400"));
pList.addAt(8,new area(4,"湖州市","HZH","572","无","313000","无"));
pList.addAt(8,new area(5,"绍兴市","SXG","575","无","312000","无"));
pList.addAt(8,new area(6,"金华市","JHA","579","5892,5893,5896","321000","322000,322100,321100"));
pList.addAt(8,new area(7,"衢州市","QUZ","570","5801","324000","324100"));
pList.addAt(8,new area(8,"舟山市","ZOS","580","无","316000","无"));
pList.addAt(8,new area(9,"台州市","TZZ","576","无","无","317000"));
pList.addAt(8,new area(10,"丽水地区","LSD","无","无","无","无"));
pList.addAt(8,new area(11,"丽水市","LSS","579","无","323000","无"));
pList.addAt(8,new area(12,"椒江市","无","无","317700","5860","无"));
pList.add(new province("安徽省","AH"));
pList.addAt(9,new area(0,"合肥市","HFE","551","无","230000","无"));
pList.addAt(9,new area(1,"芜湖市","WHI","553","无","241000","无"));
pList.addAt(9,new area(2,"蚌埠市","BBU","552","无","233000","无"));
pList.addAt(9,new area(3,"淮南市","HNS","554","无","232000","无"));
pList.addAt(9,new area(4,"马鞍山市","MAA","555","无","243000","无"));
pList.addAt(9,new area(5,"淮北市","HBE","5600","无","235000","无"));
pList.addAt(9,new area(6,"铜陵市","TOL","5612","无","244000","无"));
pList.addAt(9,new area(7,"安庆市","AQG","556","无","246000","无"));
pList.addAt(9,new area(8,"黄山市","HSN","559","无","242700","245000"));
pList.addAt(9,new area(9,"滁州市","CUZ","550","无","239000","无"));
pList.addAt(9,new area(10,"阜阳市","FYS","558","5681","236000","236800"));
pList.addAt(9,new area(11,"宿州市","SUZ","557","无","234000","无"));
pList.addAt(9,new area(12,"六安地区","LAD","无","无","无","无"));
pList.addAt(9,new area(13,"六安市","LAW","5645","无","237000","无"));
pList.addAt(9,new area(14,"宣城地区","XCD","无","无","无","无"));
pList.addAt(9,new area(15,"宣城市","XZO","5631","无","242000","无"));
pList.addAt(9,new area(16,"巢湖地区","CHD","无","无","无","无"));
pList.addAt(9,new area(17,"巢湖市","CAH","5655","无","238000","无"));
pList.addAt(9,new area(18,"池洲地区","CZD","无","无","无","无"));
pList.addAt(9,new area(19,"贵池市","GCI","无","无","无","无"));
pList.add(new province("福建省","FJ"));
pList.addAt(10,new area(0,"福州市","FOC","591","无","350000","无"));
pList.addAt(10,new area(1,"厦门市","XMN","592","无","361000","无"));
pList.addAt(10,new area(2,"莆田市","PUT","594","无","351100","无"));
pList.addAt(10,new area(3,"三明市","SMS","598","5084","365000","366000"));
pList.addAt(10,new area(4,"泉州市","QZJ","595","无","362000","362700"));
pList.addAt(10,new area(5,"漳州市","ZZU","596","无","363000","无"));
pList.addAt(10,new area(6,"南平市","NPS","599","5906","353000","354000"));
pList.addAt(10,new area(7,"龙岩市","LYF","597","无","364000","无"));
pList.addAt(10,new area(8,"宁德地区","NDD","无","无","无","无"));
pList.addAt(10,new area(9,"宁德市","NDS","593","无","无","无"));
pList.add(new province("江西省","JX"));
pList.addAt(11,new area(0,"南昌市","KHN","791","无","330000","无"));
pList.addAt(11,new area(1,"景德镇市","JDZ","798","无","333000","无"));
pList.addAt(11,new area(2,"萍乡市","PXS","799","无","337000","无"));
pList.addAt(11,new area(3,"九江市","JIU","792","无","332000","无"));
pList.addAt(11,new area(4,"新余市","XYU","790","无","336500","无"));
pList.addAt(11,new area(5,"鹰潭市","YTS","701","无","335000","无"));
pList.addAt(11,new area(6,"赣州市","GZH","797","无","341000","无"));
pList.addAt(11,new area(7,"宜春地区","YCD","无","无","无","无"));
pList.addAt(11,new area(8,"宜春市","YCN","795","无","335000","无"));
pList.addAt(11,new area(9,"上饶市","SRD","793","无","334000","无"));
pList.addAt(11,new area(10,"吉安地区","JAD","无","无","无","无"));
pList.addAt(11,new area(11,"吉安市","JAS","796","7060","343000","343600"));
pList.addAt(11,new area(12,"抚州地区","FZD","无","无","无","无"));
pList.addAt(11,new area(13,"临川市","LCR","794","无","344100","无"));
pList.addAt(11,new area(14,"抚州市","无","无","344000","794","无"));
pList.add(new province("山东省","SD"));
pList.addAt(12,new area(0,"济南市","TNA","531","无","250000","无"));
pList.addAt(12,new area(1,"青岛市","TAO","532","无","266000","无"));
pList.addAt(12,new area(2,"淄博市","ZBO","533","无","255000","无"));
pList.addAt(12,new area(3,"枣庄市","ZZG","5471","5472","277100","277500"));
pList.addAt(12,new area(4,"东营市","DYG","5461","无","257000","无"));
pList.addAt(12,new area(5,"烟台市","YNT","535","5465，5469","264000","262200，262500"));
pList.addAt(12,new area(6,"潍坊市","WEF","536","无","261000","无"));
pList.addAt(12,new area(7,"济宁市","JNG","537","5437","272100","273100"));
pList.addAt(12,new area(8,"泰安市","TAI","538","5482","271000","271200"));
pList.addAt(12,new area(9,"威海市","WEH","896/631","无","264200","无"));
pList.addAt(12,new area(10,"日照市","RZH","5400/633","无","276800","无"));
pList.addAt(12,new area(11,"莱芜市","LWS","5481","无","271100","无"));
pList.addAt(12,new area(12,"临沂市","LYI","539","无","276000","无"));
pList.addAt(12,new area(13,"德州市","DZS","534","无","253000","无"));
pList.addAt(12,new area(14,"聊城市","LCH","5411","5412","252000","252600"));
pList.addAt(12,new area(15,"滨州地区","无","无","无","无","无"));
pList.addAt(12,new area(16,"滨州市","BNZ","5431","无","256600","无"));
pList.addAt(12,new area(17,"菏泽地区","无","无","无","无","无"));
pList.addAt(12,new area(18,"菏泽市","HZS","530","无","274000","无"));
pList.add(new province("河南省","HA"));
pList.addAt(13,new area(0,"郑州市","CGO","371","无","450000","无"));
pList.addAt(13,new area(1,"开封市","KFS","378","无","475000","无"));
pList.addAt(13,new area(2,"洛阳市","LYA","379","无","471000","无"));
pList.addAt(13,new area(3,"平顶山","PDS","375","无","467000","无"));
pList.addAt(13,new area(4,"安阳市","AYS","372","无","455000","无"));
pList.addAt(13,new area(5,"鹤壁市","HBS","3812","无","456600","无"));
pList.addAt(13,new area(6,"新乡市","XXS","373","无","453000","无"));
pList.addAt(13,new area(7,"焦作市","JZY","391","无","454100","无"));
pList.addAt(13,new area(8,"濮阳市","PYS","3829/393","无","457000","无"));
pList.addAt(13,new area(9,"许昌市","XCS","374","无","461000","无"));
pList.addAt(13,new area(10,"漯河市","LHS","3813","无","462000","无"));
pList.addAt(13,new area(11,"三门峡市","SMX","3891","3887","472000","472300"));
pList.addAt(13,new area(12,"南阳市","NYS","377","无","473000","无"));
pList.addAt(13,new area(13,"商丘市","SQZ","370","无","476000","无"));
pList.addAt(13,new area(14,"信阳市","XYG","376","无","464000","无"));
pList.addAt(13,new area(15,"周口地区","ZKD","无","无","无","无"));
pList.addAt(13,new area(16,"周口市","ZKS","3851/394","无","466000","无"));
pList.addAt(13,new area(17,"驻马店地区","ZMQ","无","无","无","无"));
pList.addAt(13,new area(18,"驻马店市","ZMD","3011","396","463000","无"));
pList.add(new province("湖北省","HB"));
pList.addAt(14,new area(0,"武汉市","WUH","27","无","430000","无"));
pList.addAt(14,new area(1,"黄石市","HIS","714","无","435000","无"));
pList.addAt(14,new area(2,"十堰市","SYE","719","7292","442000","441900"));
pList.addAt(14,new area(3,"宜昌市","YCO","717","无","443000","无"));
pList.addAt(14,new area(4,"襄樊市","XFN","710","无","437300","无"));
pList.addAt(14,new area(5,"鄂州市","EZS","711","无","436000","无"));
pList.addAt(14,new area(6,"荆门市","JMS","7267/714","无","434500","无"));
pList.addAt(14,new area(7,"孝感市","XGE","712","7225,7223","432100","432600,432400"));
pList.addAt(14,new area(8,"荆州市","JGZ","无","7213,7264","无","433200,434400"));
pList.addAt(14,new area(9,"黄冈市","HGE","713","7239,7232","无","436400,431600"));
pList.addAt(14,new area(10,"咸宁市","XNS","715","无","437000","无"));
pList.addAt(14,new area(11,"恩施土家苗族自治州","ESH","无","无","无","无"));
pList.addAt(14,new area(12,"恩施市","ESS","718","7287","445000","445400"));
pList.addAt(14,new area(13,"随州市","SZR","7202","无","441300","无"));
pList.addAt(14,new area(14,"仙桃市","XNT","7214","无","433000","无"));
pList.addAt(14,new area(15,"天门市","TMS","7261","无","431700","无"));
pList.addAt(14,new area(16,"荆沙市","无","无","716","无","无"));
pList.addAt(14,new area(17,"沙市市","无","434000","716","无","无"));
pList.addAt(14,new area(18,"枝城市","无","443300","7275","无","无"));
pList.addAt(14,new area(19,"蒲昕市","无","441000","7255","无","无"));
pList.addAt(14,new area(20,"老河口市","无","441800","7207","无","无"));
pList.add(new province("湖南省","HN"));
pList.addAt(15,new area(0,"长沙市","CSX","731","无","410000","无"));
pList.addAt(15,new area(1,"株洲市","ZZS","733","无","412000","无"));
pList.addAt(15,new area(2,"湘潭市","XGT","732","732","411100","411400"));
pList.addAt(15,new area(3,"衡阳市","HNY","734","无","421000","421800"));
pList.addAt(15,new area(4,"邵阳市","SYR","739","无","422000","无"));
pList.addAt(15,new area(5,"岳阳市","YYG","730","7409","414000","414400"));
pList.addAt(15,new area(6,"常德市","CDE","736","7464","415000","415400"));
pList.addAt(15,new area(7,"张家界","ZJJ","744","无","无","无"));
pList.addAt(15,new area(8,"益阳市","YYS","737","无","413000","无"));
pList.addAt(15,new area(9,"郴州市","CNZ","735","无","423000","无"));
pList.addAt(15,new area(10,"永州市","YZS","7401","7410/746","425000","425100"));
pList.addAt(15,new area(11,"怀化市","HHS","7402/745","7429","418000","418200"));
pList.addAt(15,new area(12,"娄底地区","LDD","无","无","无","无"));
pList.addAt(15,new area(13,"娄底市","LDI","738","7477,7479","417000","417500,417100"));
pList.addAt(15,new area(14,"湘西土家苗族自治州","XXZ","无","无","无","无"));
pList.addAt(15,new area(15,"吉首市","JSO","7481","无","416000","无"));
pList.addAt(15,new area(16,"大庸市","无","416600","7483","无","无"));
pList.add(new province("广东省","GD"));
pList.addAt(16,new area(0,"广州市","CAN","20","无","510000","510000"));
pList.addAt(16,new area(1,"韶关市","HSC","751","无","512000","512000"));
pList.addAt(16,new area(2,"深圳市","SZX","755","无","518000","518000"));
pList.addAt(16,new area(3,"珠海市","ZUH","756","无","519000","519000"));
pList.addAt(16,new area(4,"汕头市","SWA","754","无","515000","515000"));
pList.addAt(16,new area(5,"佛山市","FOS","757","无","528000","528000"));
pList.addAt(16,new area(6,"江门市","JMN","750","无","529000","529000"));
pList.addAt(16,new area(7,"湛江市","ZHA","759","无","524000","524000"));
pList.addAt(16,new area(8,"茂名市","MMI","7683","无","525000","525000"));
pList.addAt(16,new area(9,"肇庆市","ZQG","758","无","526000","526000"));
pList.addAt(16,new area(10,"惠州市","HUI","752","无","516000","516000"));
pList.addAt(16,new area(11,"梅州市","MXZ","753","无","514000","514000"));
pList.addAt(16,new area(12,"汕尾市","SWE","7647","无","516600","516600"));
pList.addAt(16,new area(13,"河源市","HEY","7623","无","517000","517000"));
pList.addAt(16,new area(14,"阳江市","YJI","7677","无","529500","529500"));
pList.addAt(16,new area(15,"清远市","QYN","7617","无","511500","511500"));
pList.addAt(16,new area(16,"东莞市","DGG","769","无","523000","523000"));
pList.addAt(16,new area(17,"中山市","ZSN","7654/760","无","528400","528400"));
pList.addAt(16,new area(18,"潮州市","CZY","7681","无","515600","515600"));
pList.addAt(16,new area(19,"揭阳市","JIY","663","无","522000","522000"));
pList.addAt(16,new area(20,"云浮市","YFS","766","无","527000","527300"));
pList.add(new province("广西","GX"));
pList.addAt(17,new area(0,"南宁市","NNG","771","无","530000","无"));
pList.addAt(17,new area(1,"柳州市","LZH","772","无","545000","无"));
pList.addAt(17,new area(2,"桂林市","KWL","773","无","541000","无"));
pList.addAt(17,new area(3,"梧州市","WUZ","774","无","543000","无"));
pList.addAt(17,new area(4,"北海市","BHY","779","无","536000","无"));
pList.addAt(17,new area(5,"防城港市","FAN","无","无","无","无"));
pList.addAt(17,new area(6,"钦州市","QZH","777","无","535000","无"));
pList.addAt(17,new area(7,"贵港市","GUG","无","无","无","无"));
pList.addAt(17,new area(8,"玉林市","YUL","775","无","537000","无"));
pList.addAt(17,new area(9,"南宁地区","NND","无","无","无","无"));
pList.addAt(17,new area(10,"凭祥市","PIN","7815","无","532600","无"));
pList.addAt(17,new area(11,"柳州地区","LZD","无","无","无","无"));
pList.addAt(17,new area(12,"合山市","HSS","无","无","无","无"));
pList.addAt(17,new area(13,"贺州地区","HZD","无","无","无","无"));
pList.addAt(17,new area(14,"贺州市","HZB","无","无","无","无"));
pList.addAt(17,new area(15,"百色地区","BSE","无","无","无","无"));
pList.addAt(17,new area(16,"百色市","BSS","776","无","533000","无"));
pList.addAt(17,new area(17,"河池地区","HCD","无","无","无","无"));
pList.addAt(17,new area(18,"河池市","HCS","778","无","547000","无"));
pList.addAt(17,new area(19,"台山市","无","无","无","546500","无"));
pList.add(new province("海南省","HI"));
pList.addAt(18,new area(0,"海口市","HAK","898","无","570000","无"));
pList.addAt(18,new area(1,"三亚市","SYX","899","890,8001","572100","572200"));
pList.add(new province("四川省","SC"));
pList.addAt(19,new area(0,"成都市","CTU","28","无","610000","611830,611130"));
pList.addAt(19,new area(1,"自贡市","ZGS","813","无","无","无"));
pList.addAt(19,new area(2,"攀枝花市","PZH","812","无","无","无"));
pList.addAt(19,new area(3,"泸州市","LUZ","830","无","无","无"));
pList.addAt(19,new area(4,"德阳市","DEY","838","无","无","无"));
pList.addAt(19,new area(5,"绵阳市","MYG","816","无","无","无"));
pList.addAt(19,new area(6,"广元市","GYC","无","无","无","无"));
pList.addAt(19,new area(7,"遂宁市","SNS","无","无","无","无"));
pList.addAt(19,new area(8,"内江市","NJS","832","无","无","无"));
pList.addAt(19,new area(9,"乐山市","LES","833","无","无","无"));
pList.addAt(19,new area(10,"南充市","NCO","无","无","无","无"));
pList.addAt(19,new area(11,"宜宾市","YBS","831","无","无","无"));
pList.addAt(19,new area(12,"广安市","GAC","无","无","无","无"));
pList.addAt(19,new area(13,"达川地区","DCC","无","无","无","无"));
pList.addAt(19,new area(14,"达川市","DCS","无","无","无","无"));
pList.addAt(19,new area(15,"雅安地区","YAD","无","无","无","无"));
pList.addAt(19,new area(16,"雅安市","YAS","无","无","无","无"));
pList.addAt(19,new area(17,"阿坝自治州","ABA","无","无","无","无"));
pList.addAt(19,new area(18,"汶川县","WNC","无","无","无","无"));
pList.addAt(19,new area(19,"甘孜自治州","GAZ","无","无","无","无"));
pList.addAt(19,new area(20,"康定县","KDX","无","无","无","无"));
pList.addAt(19,new area(21,"凉汕自治州","LSY","无","无","无","无"));
pList.addAt(19,new area(22,"西昌市","XCA","无","无","无","无"));
pList.addAt(19,new area(23,"巴中地区","BZD","无","无","无","无"));
pList.addAt(19,new area(24,"巴中市","BZS","无","无","无","无"));
pList.addAt(19,new area(25,"眉山地区","MSD","无","无","无","无"));
pList.addAt(19,new area(26,"眉山县","MSX","无","无","无","无"));
pList.addAt(19,new area(27,"资阳地区","ZYA","无","无","无","无"));
pList.addAt(19,new area(28,"资阳市","ZYS","无","无","无","无"));
pList.add(new province("贵州省","GZ"));
pList.addAt(20,new area(0,"贵阳市","KWE","851","无","550000","无"));
pList.addAt(20,new area(1,"六盘水市","LPS","858","无","553000","无"));
pList.addAt(20,new area(2,"遵义市","ZNY","852","无","563000","564700"));
pList.addAt(20,new area(3,"铜仁地区","TRD","无","无","无","无"));
pList.addAt(20,new area(4,"铜仁市","TRS","856","无","554300","无"));
pList.addAt(20,new area(5,"黔西南布依苗族自治州","QXZ","无","无","无","无"));
pList.addAt(20,new area(6,"兴义市","XYI","859","无","562400","无"));
pList.addAt(20,new area(7,"毕节市","BJE","无","无","无","无"));
pList.addAt(20,new area(8,"毕节地区","BJD","无","无","无","无"));
pList.addAt(20,new area(9,"安顺市","ASS","853","无","561000","无"));
pList.addAt(20,new area(10,"安顺地区","ASD","无","无","无","无"));
pList.addAt(20,new area(11,"黔东南苗族侗族自治州","QND","无","无","无","无"));
pList.addAt(20,new area(12,"黔南布依苗族自治州","QNZ","无","无","无","无"));
pList.addAt(20,new area(13,"凯里市","KLS","855","无","556000","无"));
pList.addAt(20,new area(14,"都匀市","DUY","854","无","558000","无"));
pList.add(new province("云南省","YN"));
pList.addAt(21,new area(0,"昆明市","KMG","871","无","650000","无"));
pList.addAt(21,new area(1,"曲靖市","QJS","874","无","655000","无"));
pList.addAt(21,new area(2,"玉溪市","YXS","无","877","无","无"));
pList.addAt(21,new area(3,"昭通地区","ZTD","无","无","无","无"));
pList.addAt(21,new area(4,"昭通市","ZTS","870","无","657000","无"));
pList.addAt(21,new area(5,"楚雄彝族自治州","CXD","无","无","无","无"));
pList.addAt(21,new area(6,"楚雄市","CXS","878","无","675000","无"));
pList.addAt(21,new area(7,"红河哈尼族彝族自治州","HHZ","无","无","无","无"));
pList.addAt(21,new area(8,"个旧市","GJU","873","8844","661400","661000"));
pList.addAt(21,new area(9,"文山壮族苗族自治州","WSZ","无","无","无","无"));
pList.addAt(21,new area(10,"文山县","WES","无","无","无","无"));
pList.addAt(21,new area(11,"思茅地区","SMD","无","无","无","无"));
pList.addAt(21,new area(12,"思茅市","SYM","879","无","无","无"));
pList.addAt(21,new area(13,"西双版纳傣族自治州","XSB","无","无","无","无"));
pList.addAt(21,new area(14,"景洪市","JHG","无","无","无","无"));
pList.addAt(21,new area(15,"大理白族自治州","DLZ","无","无","无","无"));
pList.addAt(21,new area(16,"大理市","DLS","872","无","671000","无"));
pList.addAt(21,new area(17,"保山地区","BSD","无","无","无","无"));
pList.addAt(21,new area(18,"保山市","BOS","875","无","648000","无"));
pList.addAt(21,new area(19,"德宏傣族景颇族自治州","DHG","无","无","无","无"));
pList.addAt(21,new area(20,"畹町市","WAN","无","无","无","无"));
pList.addAt(21,new area(21,"丽江地区","LJD","无","无","无","无"));
pList.addAt(21,new area(22,"丽江县","LIJ","888","无","无","无"));
pList.addAt(21,new area(23,"怒江傈僳族自治州","NUJ","无","无","无","无"));
pList.addAt(21,new area(24,"泸水县","LSX","无","无","无","无"));
pList.addAt(21,new area(25,"迪庆藏族自治州","DEZ","无","无","无","无"));
pList.addAt(21,new area(26,"中旬县","ZDX","无","无","无","无"));
pList.addAt(21,new area(27,"临沧地区","LCD","无","无","无","无"));
pList.addAt(21,new area(28,"临沧县","LCI","无","无","无","无"));
pList.addAt(21,new area(29,"东川市","无","654100","8811","无","无"));
pList.add(new province("西藏","XZ"));
pList.addAt(22,new area(0,"拉萨市","LXA","891","无","850000","无"));
pList.addAt(22,new area(1,"昌都地区","QAD","无","无","无","无"));
pList.addAt(22,new area(2,"昌都县","QAX","无","8059","无","无"));
pList.addAt(22,new area(3,"山南地区","SND","无","无","无","无"));
pList.addAt(22,new area(4,"乃东县","NED","无","无","无","无"));
pList.addAt(22,new area(5,"日喀则地区","XID","无","无","无","无"));
pList.addAt(22,new area(6,"日喀则市","XIG","892","8018","857000","无"));
pList.addAt(22,new area(7,"那区地区","NAD","无","无","无","无"));
pList.addAt(22,new area(8,"那区县","NAG","无","无","无","无"));
pList.addAt(22,new area(9,"阿里地区","NGD","897","无","无","无"));
pList.addAt(22,new area(10,"普兰县","BUR","无","无","无","无"));
pList.addAt(22,new area(11,"林芝地区","NYD","无","无","无","无"));
pList.addAt(22,new area(12,"林芝县","NYI","无","无","无","无"));
pList.add(new province("陕西省","SN"));
pList.addAt(23,new area(0,"西安市","SIA","29","无","710000","无"));
pList.addAt(23,new area(1,"铜川市","TCN","919","无","727000","无"));
pList.addAt(23,new area(2,"宝鸡市","BJI","917","无","721000","无"));
pList.addAt(23,new area(3,"咸阳市","XYS","910","无","712000","无"));
pList.addAt(23,new area(4,"渭南市","WNA","913","9238","714000","715400"));
pList.addAt(23,new area(5,"延安市","YNA","911","无","716000","无"));
pList.addAt(23,new area(6,"汉中市","HZJ","916","无","723000","无"));
pList.addAt(23,new area(7,"安康地区","AKG","无","无","无","无"));
pList.addAt(23,new area(8,"安康市","ANK","无","无","无","无"));
pList.addAt(23,new area(9,"商洛地区","SLD","无","无","无","无"));
pList.addAt(23,new area(10,"商州市","SZO","914","无","无","无"));
pList.addAt(23,new area(11,"榆林地区","YLD","无","无","无","无"));
pList.addAt(23,new area(12,"榆林市","YLN","无","无","无","无"));
pList.add(new province("甘肃省","GS"));
pList.addAt(24,new area(0,"兰州市","LHW","931","无","730000","无"));
pList.addAt(24,new area(1,"嘉峪关市","JYG","9477","无","735100","无"));
pList.addAt(24,new area(2,"金昌市","JCS","9455/935","无","737100","无"));
pList.addAt(24,new area(3,"白银市","BYS","9430","无","730900","无"));
pList.addAt(24,new area(4,"天水市","TSU","938","无","741000","无"));
pList.addAt(24,new area(5,"酒泉地区","JQD","无","无","无","无"));
pList.addAt(24,new area(6,"玉门市","YMS","9471/937","937","735200","735000"));
pList.addAt(24,new area(7,"张掖地区","ZYJ","无","无","无","无"));
pList.addAt(24,new area(8,"张掖市","ZYE","936","无","734000","无"));
pList.addAt(24,new area(9,"武威地区","WWD","无","无","无","无"));
pList.addAt(24,new area(10,"武威市","WWS","935","无","733000","无"));
pList.addAt(24,new area(11,"定西地区","DXD","无","无","无","无"));
pList.addAt(24,new area(12,"定西县","DXI","无","无","无","无"));
pList.addAt(24,new area(13,"陇南地区","LND","无","无","无","无"));
pList.addAt(24,new area(14,"武都县","WUD","无","无","无","无"));
pList.addAt(24,new area(15,"平凉地区","PLG","无","无","无","无"));
pList.addAt(24,new area(16,"平凉市","PLS","933","无","744000","无"));
pList.addAt(24,new area(17,"庆阳地区","QYD","无","无","无","无"));
pList.addAt(24,new area(18,"西峰市","XFS","934","无","745000","无"));
pList.addAt(24,new area(19,"临夏回族自治州","LXH","无","无","无","无"));
pList.addAt(24,new area(20,"临夏市","LXR","930","无","731100","无"));
pList.addAt(24,new area(21,"甘南藏族自治州","GNZ","无","无","无","无"));
pList.addAt(24,new area(22,"合作市","HEZ","无","无","无","无"));
pList.add(new province("青海省","QH"));
pList.addAt(25,new area(0,"西宁市","XNN","971","无","810000","无"));
pList.addAt(25,new area(1,"海东地区","HDD","无","无","无","无"));
pList.addAt(25,new area(2,"平安县","PAN","972","无","无","无"));
pList.addAt(25,new area(3,"海北藏族自治州","HBZ","无","无","无","无"));
pList.addAt(25,new area(4,"门源回族自治县","MY","无","无","无","无"));
pList.addAt(25,new area(5,"黄南藏族自治州","HNZ","无","无","无","无"));
pList.addAt(25,new area(6,"同仁县","TRN","无","无","无","无"));
pList.addAt(25,new area(7,"海南藏族自治州","HNN","无","无","无","无"));
pList.addAt(25,new area(8,"共和县","GHE","无","无","无","无"));
pList.addAt(25,new area(9,"果洛藏族自治州","GOL","无","无","无","无"));
pList.addAt(25,new area(10,"玛沁县","MAQ","975","无","无","无"));
pList.addAt(25,new area(11,"玉树藏族自治州","YSZ","无","无","无","无"));
pList.addAt(25,new area(12,"玉树县","YSK","无","无","无","无","无"));
pList.addAt(25,new area(13,"海西蒙古族藏族自治州","HXZ","无","无","无","无"));
pList.addAt(25,new area(14,"格尔木市","GOS","979","977","816000","817000"));
pList.add(new province("宁夏","NX"));
pList.addAt(26,new area(0,"银川市","INC","951","无","750000","无"));
pList.addAt(26,new area(1,"石嘴山市","SZS","952","无","753000","无"));
pList.addAt(26,new area(2,"吴忠市","WZS","953","无","751100","751600"));
pList.addAt(26,new area(3,"固原地区","GYD","无","无","无","无"));
pList.addAt(26,new area(4,"固原县","GYN","无","954","无","无"));
pList.add(new province("新疆","XJ"));
pList.addAt(27,new area(0,"乌鲁木齐市","URC","991","无","830000","无"));
pList.addAt(27,new area(1,"克拉玛依市","KAR","990","无","834000","无"));
pList.addAt(27,new area(2,"吐鲁番地区","TUD","无","无","无","无"));
pList.addAt(27,new area(3,"吐鲁番市","TUR","995","无","838000","无"));
pList.addAt(27,new area(4,"哈密地区","HND","无","无","无","无"));
pList.addAt(27,new area(5,"哈密市","HAM","9022","无","839000","无"));
pList.addAt(27,new area(6,"昌吉回族自治州","CJZ","无","无","无","无"));
pList.addAt(27,new area(7,"昌吉市","CJS","994","无","831100","无"));
pList.addAt(27,new area(8,"博尔塔拉蒙古自治州","BOR","无","无","无","无"));
pList.addAt(27,new area(9,"博乐市","BLE","9093","无","833400","无"));
pList.addAt(27,new area(10,"巴音郭楞蒙古自治州","BAG","无","无","无","无"));
pList.addAt(27,new area(11,"库尔勒市","KOR","996","无","841000","无"));
pList.addAt(27,new area(12,"阿克苏地区","AKD","无","无","无","无"));
pList.addAt(27,new area(13,"阿克苏市","AKS","997","无","843000","无"));
pList.addAt(27,new area(14,"克孜勒苏柯尔克孜自治州","KIZ","无","无","无","无"));
pList.addAt(27,new area(15,"阿图什市","ART","9081","无","845300","无"));
pList.addAt(27,new area(16,"喀什地区","KSI","无","无","无","无"));
pList.addAt(27,new area(17,"喀什市","KHG","998","无","844000","无"));
pList.addAt(27,new area(18,"和田地区","HO","无","无","无","无"));
pList.addAt(27,new area(19,"和田市","HTS","9032","无","848000","无"));
pList.addAt(27,new area(20,"伊犁哈萨克自治州","ILD","无","无","无","无"));
pList.addAt(27,new area(21,"奎屯市","KUY","992","无","833200","无"));
pList.addAt(27,new area(22,"伊犁地区","ILI","无","无","无","无"));
pList.addAt(27,new area(23,"伊宁市","YIN","999","无","835000","无"));
pList.addAt(27,new area(24,"塔城地区","TCD","无","无","无","无"));
pList.addAt(27,new area(25,"塔城市","TCS","9003","无","834700","无"));
pList.addAt(27,new area(26,"阿勒泰地区","ALD","无","无","无","无"));
pList.addAt(27,new area(27,"阿勒泰市","ALT","9009","无","836500","无"));
pList.addAt(27,new area(28,"石河子市","SHZ","993","无","832000","无"));

var nowpro=pList.data[0];
var nowarea=nowpro.data[0];
function setiniArea(){
	temp=""
	for(var i=0;i<nowpro.data.length;i++)
	{
		temp+="<option value="+nowpro.data[i].index+">"+nowpro.data[i].name;
	}
	return temp
}
var inover=false;
var targeobj
function show_area(eP,obj){
	targeobj=obj
	window.areadiv.style.display="";
	window.areadiv.style.zIndex=99
	
	var s,cur_d
	var eT = eP.offsetTop; 
	//eT=5;
	var eH = eP.offsetHeight+eT;  
	var dH = window.areadiv.style.pixelHeight; 
	var sT = document.body.scrollTop; 
	var sL = document.body.scrollLeft; 
	var tt=0
	event.cancelBubble=true;
	window.areadiv.style.posLeft = event.clientX-event.offsetX+sL-5;  
	window.areadiv.style.posTop = event.clientY-event.offsetY+eH+sT-5 - tt;
	if (window.areadiv.style.posLeft+window.areadiv.clientWidth>document.body.clientWidth) window.areadiv.style.posLeft+=eP.offsetWidth-window.areadiv.clientWidth;
	//if (window.areadiv.style.posTop+window.areadiv.clientHeight>document.body.clientHeight) window.areadiv.style.posTop=window.areadiv.style.posTop-(eP.offsetHeight+window.areadiv.clientHeight+5)-tt;
	
	
	/*设置数值*/

	window.areadiv.style.display="block";
	/***************2002-02-01 MODIFY BY WING ***********/
	window.areadiv.focus();
	/****************MODIFY END**************************/
}

/*********** 2002-02-01 MODIFY BY WING **ADD THREE FUNCTION TO CONTROL THE DIV FOCUS***/
function hilayer_areadiv()
{
        if (inover==false)
        {
                var lay=document.all.areadiv;
                lay.style.display="none";
        }
}
function getlayerfocus_areadiv()
{
        inover=true;
}
function lostlayerfocus_areadiv()
{
        inover=false;
}
/***************************MODIFY END************************************************/
function giveProvinceFocus(obj){
	provinceSelChange(window.Area_code,obj)
	window.areadiv.focus();
}

function giveAreaFocus(obj){
	areaSelChange(obj);
	window.areadiv.focus();
}

function overcolor_areadiv(obj)
{
  if (obj.style.cursor=="hand") obj.style.color = "#FFFFFF";
  /*********** 2002-02-01 MODIFY BY WING *****/
  inover=true;
   window.areadiv.focus();
  /************* MODIFY END ******************/
}

function outcolor_areadiv(obj)
{
  obj.style.color = "#000000";
  /*********** 2002-02-01 MODIFY BY WING *****/
  inover=false;
  /************* MODIFY END ******************/
}
function tr_click_areadiv(){
	str=window.areacode.innerHTML
	targeobj.value=str;	//t_object.innerHTML
	window.areadiv.style.display="none";
}

function initarea(){
	document.writeln("<div name=\"areadiv\" id=\"areadiv\"  style=\"display:none\" style=\"LEFT: 515px; POSITION: absolute; TOP: 347px; Z-INDEX:99; width: 180px; height: 112px;\" onClick=\"event.cancelBubble=true;\" onBlur=\"hilayer_areadiv()\" onMouseout=\"lostlayerfocus_areadiv()\">&nbsp;</div>");
	window.areadiv.innerHTML=""
	temp="<select name=\"province\" id=\"province\"  onchange=\"giveProvinceFocus(this)\"  onmouseover=\"getlayerfocus_areadiv()\" onblur=\"getlayerfocus_areadiv()\" style=\"font-size: 9pt; border: 1px #666666 outset; background-color: #F4F8FB\">";
	temp+=pList.getOptionString();
	temp+="</select><select name=\"Area_code\" id=\"Area_code\" onchange=\"giveAreaFocus(this)\" onmouseover=\"getlayerfocus_areadiv()\" onblur=\"getlayerfocus_areadiv()\" style=\"font-size: 9pt; border: 1px #666666 outset; background-color: #F4F8FB\">";
	temp+=setiniArea()+"</select>"; 
	temp+="<table  border=\"1\" bgcolor=\"#B9D3EE\" bordercolor=\"white\" style=\"font-size: 9pt; border: 1px #666666 outset\">";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">地区代码:</td><td bgcolor=\"#7EC0EE\" width=\"101\" id=\"areacode\" >&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">区号:</td><td bgcolor=\"#7EC0EE\" id=\"areazone\">&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">例外区号:</td><td bgcolor=\"#7EC0EE\" id=\"areaexzone\" >&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">特征邮编:</td><td bgcolor=\"#7EC0EE\"  id=\"areamail\">&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">例外邮编:</td><td bgcolor=\"#7EC0EE\"  id=\"areaexmail\">&nbsp;</td></tr></table>";
	window.areadiv.innerHTML=temp
}
initarea();
setdiv(nowarea);