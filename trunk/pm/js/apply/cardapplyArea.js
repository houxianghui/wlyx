//���½ű���Ϊ����������
function areaSelChange(areaname)
{  
	nowarea=nowpro.data[areaname.value]
	setdiv(nowarea)
	//alert(form1.Area_code.value);
	//window.areadiv.focus();
}
function provinceSelChange(selname,provincename)
{	var index="";  
    //�ж����ĸ��ط���ʡ�ݷ����仯
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
pList.add(new province("ֱϽ��","ZX"));
pList.addAt(0,new area(0,"������","BJ","10","��","100000","101200-102600"));
pList.addAt(0,new area(1,"�����","TJ","22","��","300000","300270-301800"));
pList.addAt(0,new area(2,"�Ϻ���","SH","21","��","200000","200100-202100"));
pList.addAt(0,new area(3,"������","CQ","23","��","400000","400700-409900"));
pList.add(new province("�ӱ�ʡ","HE"));
pList.addAt(1,new area(0,"ʯ��ׯ��","SJW","311","��","050000","052300"));
pList.addAt(1,new area(1,"��ɽ��","TGS","315","��","063000","��"));
pList.addAt(1,new area(2,"�ػʵ���","SHP","335","��","066000","066100"));
pList.addAt(1,new area(3,"������","HDS","310","��","056000","��"));
pList.addAt(1,new area(4,"��̨��","XTS","319","��","054000","051800"));
pList.addAt(1,new area(5,"������","BDS","312","��","071000","��"));
pList.addAt(1,new area(6,"�żҿ���","ZJK","313","��","075000","072700,073000"));
pList.addAt(1,new area(7,"�е���","CDS","314","��","067000","��"));
pList.addAt(1,new area(8,"������","CGZ","317","��","061000","062100,062500"));
pList.addAt(1,new area(9,"�ȷ���","LFS","316","��","102800","��"));
pList.addAt(1,new area(10,"��ˮ��","HGS","318","��","053000","��"));
pList.add(new province("ɽ��ʡ","SX"));
pList.addAt(2,new area(0,"̫ԭ��","TUN","351","��","030000","��"));
pList.addAt(2,new area(1,"��ͬ��","DTG","352","��","037000","��"));
pList.addAt(2,new area(2,"��Ȫ��","YQS","353","��","045000","��"));
pList.addAt(2,new area(3,"������","CZS","355","��","046000","��"));
pList.addAt(2,new area(4,"������","JCG","356","��","048000","��"));
pList.addAt(2,new area(5,"˷����","SZJ","349","��","��","��"));
pList.addAt(2,new area(6,"���ݵ���","XZD","��","��","��","��"));
pList.addAt(2,new area(7,"������","XZS","350","��","034000","��"));
pList.addAt(2,new area(8,"��������","LLD","��","��","��","��"));
pList.addAt(2,new area(9,"Т����","XOY","358","��","��","��"));
pList.addAt(2,new area(10,"���е���","JZD","��","��","��","��"));
pList.addAt(2,new area(11,"�ܴ���","YCI","354","��","030600","��"));
pList.addAt(2,new area(12,"�ٷڵ���","LFD","��","��","��","��"));
pList.addAt(2,new area(13,"�ٷ���","LFN","357","��","041000","043000"));
pList.addAt(2,new area(14,"�˳���","YCJ","359","��","044000","��"));
pList.add(new province("���ɹ�","NM"));
pList.addAt(3,new area(0,"���ͺ�����","HET","471","��","010000","��"));
pList.addAt(3,new area(1,"��ͷ��","BTS","472","��","014000","��"));
pList.addAt(3,new area(2,"�ں���","WHM","473","��","016000","��"));
pList.addAt(3,new area(3,"�����","CFS","476","��","024000","��"));
pList.addAt(3,new area(4,"���ױ�����","HBM","��","��","��","��"));
pList.addAt(3,new area(5,"��������","HLR","470","��","021000","01400,162600,022100"));
pList.addAt(3,new area(6,"�˰���","HIN","��","��","��","��"));
pList.addAt(3,new area(7,"����������","ULO","482","��","137400","��"));
pList.addAt(3,new area(8,"����ľ��","JIR","��","��","��","��"));
pList.addAt(3,new area(9,"ͨ����","TLO","475","��","028000","029200"));
pList.addAt(3,new area(10,"���ֹ�����","XGO","��","��","��","��"));
pList.addAt(3,new area(11,"����������","ERC","479","��","012600","026000"));
pList.addAt(3,new area(12,"�����첼��","ULM","��","��","��","��"));
pList.addAt(3,new area(13,"������","JIN","474","��","012000","��"));
pList.addAt(3,new area(14,"��������","IJU","��","��","��","��"));
pList.addAt(3,new area(15,"��ʤ��","DSS","477","��","017000","��"));
pList.addAt(3,new area(16,"�����׶���","BAS","��","��","��","��"));
pList.addAt(3,new area(17,"�ٺ���","LNH","478","��","015000","��"));
pList.addAt(3,new area(18,"��������","ALM","��","��","��","��"));
pList.addAt(3,new area(19,"����������","ALZ","��","��","��","��"));
pList.addAt(3,new area(20,"����������","ALY","��","��","��","��"));
pList.add(new province("����ʡ","LN"));
pList.addAt(4,new area(0,"������","SHE","24","��","110000","��"));
pList.addAt(4,new area(1,"������","DLG","411","��","116000","116300"));
pList.addAt(4,new area(2,"��ɽ��","ASN","412","��","114000","114200"));
pList.addAt(4,new area(3,"��˳��","FSN","413","��","113000","��"));
pList.addAt(4,new area(4,"��Ϫ��","BXS","414","��","117000","��"));
pList.addAt(4,new area(5,"������","DDG","415","��","118000","��"));
pList.addAt(4,new area(6,"������","JNZ","416","��","121000","��"));
pList.addAt(4,new area(7,"Ӫ����","YIK","417","��","115000","��"));
pList.addAt(4,new area(8,"������","FXS","418","��","123000","��"));
pList.addAt(4,new area(9,"������","LYL","419","��","111000","��"));
pList.addAt(4,new area(10,"�̽���","PJS","427","��","124000","��"));
pList.addAt(4,new area(11,"������","TLS","410","��","112000","112700"));
pList.addAt(4,new area(12,"������","CYS","421","��","122000","122100"));
pList.addAt(4,new area(13,"��«����","HLD","429","��","121500","121600"));
pList.add(new province("����ʡ","JL"));
pList.addAt(5,new area(0,"������","CGO","431","��","130000","��"));
pList.addAt(5,new area(1,"������","JLS","423","4423","132000","132400"));
pList.addAt(5,new area(2,"��ƽ��","SLH","434","47441","136000","136100"));
pList.addAt(5,new area(3,"��Դ��","LYH","437","��","136200","��"));
pList.addAt(5,new area(4,"ͨ����","THS","435","4453,4454","134000","135000,134200"));
pList.addAt(5,new area(5,"��ɽ��","BSN","��","4461","��","137100"));
pList.addAt(5,new area(6,"��ԭ��","SYU","��","��","��","132100"));
pList.addAt(5,new area(7,"�׳���","BCS","436","��","137000","��"));
pList.addAt(5,new area(8,"�ӱ߳�����������","YBZ","��","��","��","��"));
pList.addAt(5,new area(9, "�Ӽ���","YNJ","433","4438,4436,4435","133000","133400,133100,133700"));
pList.addAt(5,new area(10,"�뽭��","��","134300","439","��","��"));
pList.addAt(5,new area(11,"������","��","150300","450","��","��"));
pList.add(new province("������","HL"));
pList.addAt(6,new area(0,"��������","HRB","451","��","150000","��"));
pList.addAt(6,new area(1,"���������","NDG","452","��","161000","��"));
pList.addAt(6,new area(2,"������","JXI","4617","��","158100","��"));
pList.addAt(6,new area(3,"�׸���","HEG","468","��","154100","��"));
pList.addAt(6,new area(4,"˫Ѽɽ","SYS","4619","��","155100","��"));
pList.addAt(6,new area(5,"������","DQG","459","��","163000","��"));
pList.addAt(6,new area(6,"������","YCH","458","��","153000","��"));
pList.addAt(6,new area(7,"��ľ˹��","JMU","454","��","156000","156400"));
pList.addAt(6,new area(8,"��̨����","QTH","4640","��","154600","��"));
pList.addAt(6,new area(9,"ĵ������","MDG","453","4638","157000","157300"));
pList.addAt(6,new area(10,"�ں���","HEK","456","4661,4678","164300","164000,164500"));
pList.addAt(6,new area(11,"�绯����","SHD","��","��","��","��"));
pList.addAt(6,new area(12,"�绯��","SUH","455","4616","152000","151100"));
pList.addAt(6,new area(13,"���˰������","DHL","��","��","��","��"));
pList.addAt(6,new area(14,"������","HUM","��","457","��","��"));
pList.add(new province("����ʡ","JS"));
pList.addAt(7,new area(0,"�Ͼ���","NKG","25","��","210000","��"));
pList.addAt(7,new area(1,"������","WUX","510","5218,5217","214000","214200,214400"));
pList.addAt(7,new area(2,"������","XUZ","516","��","221000","��"));
pList.addAt(7,new area(3,"������","CZX","519","��","213000","��"));
pList.addAt(7,new area(4,"������","SZH","512","��","215000","215500"));
pList.addAt(7,new area(5,"��ͨ��","NTG","513","��","226000","��"));
pList.addAt(7,new area(6,"���Ƹ���","LYG","518","��","222000","��"));
pList.addAt(7,new area(7,"������","HYS","517","��","223000","223200"));
pList.addAt(7,new area(8,"�γ���","YCK","515","5250","224000","224200"));
pList.addAt(7,new area(9,"������","YZH","514","5291","225000","211400"));
pList.addAt(7,new area(10,"����","ZHE","511","5211","212000","212300"));
pList.addAt(7,new area(11,"̩����","TZS","5241","5245","225300","225700"));
pList.addAt(7,new area(12,"��Ǩ��","SUQ","527","��","223800","��"));
pList.add(new province("�㽭ʡ","ZJ"));
pList.addAt(8,new area(0,"������","HGH","571","��","310000","311200"));
pList.addAt(8,new area(1,"������","NGB","574","5844","315000","315400"));
pList.addAt(8,new area(2,"������","WNZ","577","5874","325000","325200"));
pList.addAt(8,new area(3,"������","JIX","573","��","314000","314400"));
pList.addAt(8,new area(4,"������","HZH","572","��","313000","��"));
pList.addAt(8,new area(5,"������","SXG","575","��","312000","��"));
pList.addAt(8,new area(6,"����","JHA","579","5892,5893,5896","321000","322000,322100,321100"));
pList.addAt(8,new area(7,"������","QUZ","570","5801","324000","324100"));
pList.addAt(8,new area(8,"��ɽ��","ZOS","580","��","316000","��"));
pList.addAt(8,new area(9,"̨����","TZZ","576","��","��","317000"));
pList.addAt(8,new area(10,"��ˮ����","LSD","��","��","��","��"));
pList.addAt(8,new area(11,"��ˮ��","LSS","579","��","323000","��"));
pList.addAt(8,new area(12,"������","��","��","317700","5860","��"));
pList.add(new province("����ʡ","AH"));
pList.addAt(9,new area(0,"�Ϸ���","HFE","551","��","230000","��"));
pList.addAt(9,new area(1,"�ߺ���","WHI","553","��","241000","��"));
pList.addAt(9,new area(2,"������","BBU","552","��","233000","��"));
pList.addAt(9,new area(3,"������","HNS","554","��","232000","��"));
pList.addAt(9,new area(4,"��ɽ��","MAA","555","��","243000","��"));
pList.addAt(9,new area(5,"������","HBE","5600","��","235000","��"));
pList.addAt(9,new area(6,"ͭ����","TOL","5612","��","244000","��"));
pList.addAt(9,new area(7,"������","AQG","556","��","246000","��"));
pList.addAt(9,new area(8,"��ɽ��","HSN","559","��","242700","245000"));
pList.addAt(9,new area(9,"������","CUZ","550","��","239000","��"));
pList.addAt(9,new area(10,"������","FYS","558","5681","236000","236800"));
pList.addAt(9,new area(11,"������","SUZ","557","��","234000","��"));
pList.addAt(9,new area(12,"��������","LAD","��","��","��","��"));
pList.addAt(9,new area(13,"������","LAW","5645","��","237000","��"));
pList.addAt(9,new area(14,"���ǵ���","XCD","��","��","��","��"));
pList.addAt(9,new area(15,"������","XZO","5631","��","242000","��"));
pList.addAt(9,new area(16,"��������","CHD","��","��","��","��"));
pList.addAt(9,new area(17,"������","CAH","5655","��","238000","��"));
pList.addAt(9,new area(18,"���޵���","CZD","��","��","��","��"));
pList.addAt(9,new area(19,"�����","GCI","��","��","��","��"));
pList.add(new province("����ʡ","FJ"));
pList.addAt(10,new area(0,"������","FOC","591","��","350000","��"));
pList.addAt(10,new area(1,"������","XMN","592","��","361000","��"));
pList.addAt(10,new area(2,"������","PUT","594","��","351100","��"));
pList.addAt(10,new area(3,"������","SMS","598","5084","365000","366000"));
pList.addAt(10,new area(4,"Ȫ����","QZJ","595","��","362000","362700"));
pList.addAt(10,new area(5,"������","ZZU","596","��","363000","��"));
pList.addAt(10,new area(6,"��ƽ��","NPS","599","5906","353000","354000"));
pList.addAt(10,new area(7,"������","LYF","597","��","364000","��"));
pList.addAt(10,new area(8,"���µ���","NDD","��","��","��","��"));
pList.addAt(10,new area(9,"������","NDS","593","��","��","��"));
pList.add(new province("����ʡ","JX"));
pList.addAt(11,new area(0,"�ϲ���","KHN","791","��","330000","��"));
pList.addAt(11,new area(1,"��������","JDZ","798","��","333000","��"));
pList.addAt(11,new area(2,"Ƽ����","PXS","799","��","337000","��"));
pList.addAt(11,new area(3,"�Ž���","JIU","792","��","332000","��"));
pList.addAt(11,new area(4,"������","XYU","790","��","336500","��"));
pList.addAt(11,new area(5,"ӥ̶��","YTS","701","��","335000","��"));
pList.addAt(11,new area(6,"������","GZH","797","��","341000","��"));
pList.addAt(11,new area(7,"�˴�����","YCD","��","��","��","��"));
pList.addAt(11,new area(8,"�˴���","YCN","795","��","335000","��"));
pList.addAt(11,new area(9,"������","SRD","793","��","334000","��"));
pList.addAt(11,new area(10,"��������","JAD","��","��","��","��"));
pList.addAt(11,new area(11,"������","JAS","796","7060","343000","343600"));
pList.addAt(11,new area(12,"���ݵ���","FZD","��","��","��","��"));
pList.addAt(11,new area(13,"�ٴ���","LCR","794","��","344100","��"));
pList.addAt(11,new area(14,"������","��","��","344000","794","��"));
pList.add(new province("ɽ��ʡ","SD"));
pList.addAt(12,new area(0,"������","TNA","531","��","250000","��"));
pList.addAt(12,new area(1,"�ൺ��","TAO","532","��","266000","��"));
pList.addAt(12,new area(2,"�Ͳ���","ZBO","533","��","255000","��"));
pList.addAt(12,new area(3,"��ׯ��","ZZG","5471","5472","277100","277500"));
pList.addAt(12,new area(4,"��Ӫ��","DYG","5461","��","257000","��"));
pList.addAt(12,new area(5,"��̨��","YNT","535","5465��5469","264000","262200��262500"));
pList.addAt(12,new area(6,"Ϋ����","WEF","536","��","261000","��"));
pList.addAt(12,new area(7,"������","JNG","537","5437","272100","273100"));
pList.addAt(12,new area(8,"̩����","TAI","538","5482","271000","271200"));
pList.addAt(12,new area(9,"������","WEH","896/631","��","264200","��"));
pList.addAt(12,new area(10,"������","RZH","5400/633","��","276800","��"));
pList.addAt(12,new area(11,"������","LWS","5481","��","271100","��"));
pList.addAt(12,new area(12,"������","LYI","539","��","276000","��"));
pList.addAt(12,new area(13,"������","DZS","534","��","253000","��"));
pList.addAt(12,new area(14,"�ĳ���","LCH","5411","5412","252000","252600"));
pList.addAt(12,new area(15,"���ݵ���","��","��","��","��","��"));
pList.addAt(12,new area(16,"������","BNZ","5431","��","256600","��"));
pList.addAt(12,new area(17,"�������","��","��","��","��","��"));
pList.addAt(12,new area(18,"������","HZS","530","��","274000","��"));
pList.add(new province("����ʡ","HA"));
pList.addAt(13,new area(0,"֣����","CGO","371","��","450000","��"));
pList.addAt(13,new area(1,"������","KFS","378","��","475000","��"));
pList.addAt(13,new area(2,"������","LYA","379","��","471000","��"));
pList.addAt(13,new area(3,"ƽ��ɽ","PDS","375","��","467000","��"));
pList.addAt(13,new area(4,"������","AYS","372","��","455000","��"));
pList.addAt(13,new area(5,"�ױ���","HBS","3812","��","456600","��"));
pList.addAt(13,new area(6,"������","XXS","373","��","453000","��"));
pList.addAt(13,new area(7,"������","JZY","391","��","454100","��"));
pList.addAt(13,new area(8,"�����","PYS","3829/393","��","457000","��"));
pList.addAt(13,new area(9,"�����","XCS","374","��","461000","��"));
pList.addAt(13,new area(10,"�����","LHS","3813","��","462000","��"));
pList.addAt(13,new area(11,"����Ͽ��","SMX","3891","3887","472000","472300"));
pList.addAt(13,new area(12,"������","NYS","377","��","473000","��"));
pList.addAt(13,new area(13,"������","SQZ","370","��","476000","��"));
pList.addAt(13,new area(14,"������","XYG","376","��","464000","��"));
pList.addAt(13,new area(15,"�ܿڵ���","ZKD","��","��","��","��"));
pList.addAt(13,new area(16,"�ܿ���","ZKS","3851/394","��","466000","��"));
pList.addAt(13,new area(17,"פ������","ZMQ","��","��","��","��"));
pList.addAt(13,new area(18,"פ�����","ZMD","3011","396","463000","��"));
pList.add(new province("����ʡ","HB"));
pList.addAt(14,new area(0,"�人��","WUH","27","��","430000","��"));
pList.addAt(14,new area(1,"��ʯ��","HIS","714","��","435000","��"));
pList.addAt(14,new area(2,"ʮ����","SYE","719","7292","442000","441900"));
pList.addAt(14,new area(3,"�˲���","YCO","717","��","443000","��"));
pList.addAt(14,new area(4,"�差��","XFN","710","��","437300","��"));
pList.addAt(14,new area(5,"������","EZS","711","��","436000","��"));
pList.addAt(14,new area(6,"������","JMS","7267/714","��","434500","��"));
pList.addAt(14,new area(7,"Т����","XGE","712","7225,7223","432100","432600,432400"));
pList.addAt(14,new area(8,"������","JGZ","��","7213,7264","��","433200,434400"));
pList.addAt(14,new area(9,"�Ƹ���","HGE","713","7239,7232","��","436400,431600"));
pList.addAt(14,new area(10,"������","XNS","715","��","437000","��"));
pList.addAt(14,new area(11,"��ʩ��������������","ESH","��","��","��","��"));
pList.addAt(14,new area(12,"��ʩ��","ESS","718","7287","445000","445400"));
pList.addAt(14,new area(13,"������","SZR","7202","��","441300","��"));
pList.addAt(14,new area(14,"������","XNT","7214","��","433000","��"));
pList.addAt(14,new area(15,"������","TMS","7261","��","431700","��"));
pList.addAt(14,new area(16,"��ɳ��","��","��","716","��","��"));
pList.addAt(14,new area(17,"ɳ����","��","434000","716","��","��"));
pList.addAt(14,new area(18,"֦����","��","443300","7275","��","��"));
pList.addAt(14,new area(19,"�����","��","441000","7255","��","��"));
pList.addAt(14,new area(20,"�Ϻӿ���","��","441800","7207","��","��"));
pList.add(new province("����ʡ","HN"));
pList.addAt(15,new area(0,"��ɳ��","CSX","731","��","410000","��"));
pList.addAt(15,new area(1,"������","ZZS","733","��","412000","��"));
pList.addAt(15,new area(2,"��̶��","XGT","732","732","411100","411400"));
pList.addAt(15,new area(3,"������","HNY","734","��","421000","421800"));
pList.addAt(15,new area(4,"������","SYR","739","��","422000","��"));
pList.addAt(15,new area(5,"������","YYG","730","7409","414000","414400"));
pList.addAt(15,new area(6,"������","CDE","736","7464","415000","415400"));
pList.addAt(15,new area(7,"�żҽ�","ZJJ","744","��","��","��"));
pList.addAt(15,new area(8,"������","YYS","737","��","413000","��"));
pList.addAt(15,new area(9,"������","CNZ","735","��","423000","��"));
pList.addAt(15,new area(10,"������","YZS","7401","7410/746","425000","425100"));
pList.addAt(15,new area(11,"������","HHS","7402/745","7429","418000","418200"));
pList.addAt(15,new area(12,"¦�׵���","LDD","��","��","��","��"));
pList.addAt(15,new area(13,"¦����","LDI","738","7477,7479","417000","417500,417100"));
pList.addAt(15,new area(14,"������������������","XXZ","��","��","��","��"));
pList.addAt(15,new area(15,"������","JSO","7481","��","416000","��"));
pList.addAt(15,new area(16,"��ӹ��","��","416600","7483","��","��"));
pList.add(new province("�㶫ʡ","GD"));
pList.addAt(16,new area(0,"������","CAN","20","��","510000","510000"));
pList.addAt(16,new area(1,"�ع���","HSC","751","��","512000","512000"));
pList.addAt(16,new area(2,"������","SZX","755","��","518000","518000"));
pList.addAt(16,new area(3,"�麣��","ZUH","756","��","519000","519000"));
pList.addAt(16,new area(4,"��ͷ��","SWA","754","��","515000","515000"));
pList.addAt(16,new area(5,"��ɽ��","FOS","757","��","528000","528000"));
pList.addAt(16,new area(6,"������","JMN","750","��","529000","529000"));
pList.addAt(16,new area(7,"տ����","ZHA","759","��","524000","524000"));
pList.addAt(16,new area(8,"ï����","MMI","7683","��","525000","525000"));
pList.addAt(16,new area(9,"������","ZQG","758","��","526000","526000"));
pList.addAt(16,new area(10,"������","HUI","752","��","516000","516000"));
pList.addAt(16,new area(11,"÷����","MXZ","753","��","514000","514000"));
pList.addAt(16,new area(12,"��β��","SWE","7647","��","516600","516600"));
pList.addAt(16,new area(13,"��Դ��","HEY","7623","��","517000","517000"));
pList.addAt(16,new area(14,"������","YJI","7677","��","529500","529500"));
pList.addAt(16,new area(15,"��Զ��","QYN","7617","��","511500","511500"));
pList.addAt(16,new area(16,"��ݸ��","DGG","769","��","523000","523000"));
pList.addAt(16,new area(17,"��ɽ��","ZSN","7654/760","��","528400","528400"));
pList.addAt(16,new area(18,"������","CZY","7681","��","515600","515600"));
pList.addAt(16,new area(19,"������","JIY","663","��","522000","522000"));
pList.addAt(16,new area(20,"�Ƹ���","YFS","766","��","527000","527300"));
pList.add(new province("����","GX"));
pList.addAt(17,new area(0,"������","NNG","771","��","530000","��"));
pList.addAt(17,new area(1,"������","LZH","772","��","545000","��"));
pList.addAt(17,new area(2,"������","KWL","773","��","541000","��"));
pList.addAt(17,new area(3,"������","WUZ","774","��","543000","��"));
pList.addAt(17,new area(4,"������","BHY","779","��","536000","��"));
pList.addAt(17,new area(5,"���Ǹ���","FAN","��","��","��","��"));
pList.addAt(17,new area(6,"������","QZH","777","��","535000","��"));
pList.addAt(17,new area(7,"�����","GUG","��","��","��","��"));
pList.addAt(17,new area(8,"������","YUL","775","��","537000","��"));
pList.addAt(17,new area(9,"��������","NND","��","��","��","��"));
pList.addAt(17,new area(10,"ƾ����","PIN","7815","��","532600","��"));
pList.addAt(17,new area(11,"���ݵ���","LZD","��","��","��","��"));
pList.addAt(17,new area(12,"��ɽ��","HSS","��","��","��","��"));
pList.addAt(17,new area(13,"���ݵ���","HZD","��","��","��","��"));
pList.addAt(17,new area(14,"������","HZB","��","��","��","��"));
pList.addAt(17,new area(15,"��ɫ����","BSE","��","��","��","��"));
pList.addAt(17,new area(16,"��ɫ��","BSS","776","��","533000","��"));
pList.addAt(17,new area(17,"�ӳص���","HCD","��","��","��","��"));
pList.addAt(17,new area(18,"�ӳ���","HCS","778","��","547000","��"));
pList.addAt(17,new area(19,"̨ɽ��","��","��","��","546500","��"));
pList.add(new province("����ʡ","HI"));
pList.addAt(18,new area(0,"������","HAK","898","��","570000","��"));
pList.addAt(18,new area(1,"������","SYX","899","890,8001","572100","572200"));
pList.add(new province("�Ĵ�ʡ","SC"));
pList.addAt(19,new area(0,"�ɶ���","CTU","28","��","610000","611830,611130"));
pList.addAt(19,new area(1,"�Թ���","ZGS","813","��","��","��"));
pList.addAt(19,new area(2,"��֦����","PZH","812","��","��","��"));
pList.addAt(19,new area(3,"������","LUZ","830","��","��","��"));
pList.addAt(19,new area(4,"������","DEY","838","��","��","��"));
pList.addAt(19,new area(5,"������","MYG","816","��","��","��"));
pList.addAt(19,new area(6,"��Ԫ��","GYC","��","��","��","��"));
pList.addAt(19,new area(7,"������","SNS","��","��","��","��"));
pList.addAt(19,new area(8,"�ڽ���","NJS","832","��","��","��"));
pList.addAt(19,new area(9,"��ɽ��","LES","833","��","��","��"));
pList.addAt(19,new area(10,"�ϳ���","NCO","��","��","��","��"));
pList.addAt(19,new area(11,"�˱���","YBS","831","��","��","��"));
pList.addAt(19,new area(12,"�㰲��","GAC","��","��","��","��"));
pList.addAt(19,new area(13,"�ﴨ����","DCC","��","��","��","��"));
pList.addAt(19,new area(14,"�ﴨ��","DCS","��","��","��","��"));
pList.addAt(19,new area(15,"�Ű�����","YAD","��","��","��","��"));
pList.addAt(19,new area(16,"�Ű���","YAS","��","��","��","��"));
pList.addAt(19,new area(17,"����������","ABA","��","��","��","��"));
pList.addAt(19,new area(18,"�봨��","WNC","��","��","��","��"));
pList.addAt(19,new area(19,"����������","GAZ","��","��","��","��"));
pList.addAt(19,new area(20,"������","KDX","��","��","��","��"));
pList.addAt(19,new area(21,"����������","LSY","��","��","��","��"));
pList.addAt(19,new area(22,"������","XCA","��","��","��","��"));
pList.addAt(19,new area(23,"���е���","BZD","��","��","��","��"));
pList.addAt(19,new area(24,"������","BZS","��","��","��","��"));
pList.addAt(19,new area(25,"üɽ����","MSD","��","��","��","��"));
pList.addAt(19,new area(26,"üɽ��","MSX","��","��","��","��"));
pList.addAt(19,new area(27,"��������","ZYA","��","��","��","��"));
pList.addAt(19,new area(28,"������","ZYS","��","��","��","��"));
pList.add(new province("����ʡ","GZ"));
pList.addAt(20,new area(0,"������","KWE","851","��","550000","��"));
pList.addAt(20,new area(1,"����ˮ��","LPS","858","��","553000","��"));
pList.addAt(20,new area(2,"������","ZNY","852","��","563000","564700"));
pList.addAt(20,new area(3,"ͭ�ʵ���","TRD","��","��","��","��"));
pList.addAt(20,new area(4,"ͭ����","TRS","856","��","554300","��"));
pList.addAt(20,new area(5,"ǭ���ϲ�������������","QXZ","��","��","��","��"));
pList.addAt(20,new area(6,"������","XYI","859","��","562400","��"));
pList.addAt(20,new area(7,"�Ͻ���","BJE","��","��","��","��"));
pList.addAt(20,new area(8,"�Ͻڵ���","BJD","��","��","��","��"));
pList.addAt(20,new area(9,"��˳��","ASS","853","��","561000","��"));
pList.addAt(20,new area(10,"��˳����","ASD","��","��","��","��"));
pList.addAt(20,new area(11,"ǭ�������嶱��������","QND","��","��","��","��"));
pList.addAt(20,new area(12,"ǭ�ϲ�������������","QNZ","��","��","��","��"));
pList.addAt(20,new area(13,"������","KLS","855","��","556000","��"));
pList.addAt(20,new area(14,"������","DUY","854","��","558000","��"));
pList.add(new province("����ʡ","YN"));
pList.addAt(21,new area(0,"������","KMG","871","��","650000","��"));
pList.addAt(21,new area(1,"������","QJS","874","��","655000","��"));
pList.addAt(21,new area(2,"��Ϫ��","YXS","��","877","��","��"));
pList.addAt(21,new area(3,"��ͨ����","ZTD","��","��","��","��"));
pList.addAt(21,new area(4,"��ͨ��","ZTS","870","��","657000","��"));
pList.addAt(21,new area(5,"��������������","CXD","��","��","��","��"));
pList.addAt(21,new area(6,"������","CXS","878","��","675000","��"));
pList.addAt(21,new area(7,"��ӹ���������������","HHZ","��","��","��","��"));
pList.addAt(21,new area(8,"������","GJU","873","8844","661400","661000"));
pList.addAt(21,new area(9,"��ɽ׳������������","WSZ","��","��","��","��"));
pList.addAt(21,new area(10,"��ɽ��","WES","��","��","��","��"));
pList.addAt(21,new area(11,"˼é����","SMD","��","��","��","��"));
pList.addAt(21,new area(12,"˼é��","SYM","879","��","��","��"));
pList.addAt(21,new area(13,"��˫���ɴ���������","XSB","��","��","��","��"));
pList.addAt(21,new area(14,"������","JHG","��","��","��","��"));
pList.addAt(21,new area(15,"�������������","DLZ","��","��","��","��"));
pList.addAt(21,new area(16,"������","DLS","872","��","671000","��"));
pList.addAt(21,new area(17,"��ɽ����","BSD","��","��","��","��"));
pList.addAt(21,new area(18,"��ɽ��","BOS","875","��","648000","��"));
pList.addAt(21,new area(19,"�º���徰����������","DHG","��","��","��","��"));
pList.addAt(21,new area(20,"����","WAN","��","��","��","��"));
pList.addAt(21,new area(21,"��������","LJD","��","��","��","��"));
pList.addAt(21,new area(22,"������","LIJ","888","��","��","��"));
pList.addAt(21,new area(23,"ŭ��������������","NUJ","��","��","��","��"));
pList.addAt(21,new area(24,"��ˮ��","LSX","��","��","��","��"));
pList.addAt(21,new area(25,"�������������","DEZ","��","��","��","��"));
pList.addAt(21,new area(26,"��Ѯ��","ZDX","��","��","��","��"));
pList.addAt(21,new area(27,"�ٲ׵���","LCD","��","��","��","��"));
pList.addAt(21,new area(28,"�ٲ���","LCI","��","��","��","��"));
pList.addAt(21,new area(29,"������","��","654100","8811","��","��"));
pList.add(new province("����","XZ"));
pList.addAt(22,new area(0,"������","LXA","891","��","850000","��"));
pList.addAt(22,new area(1,"��������","QAD","��","��","��","��"));
pList.addAt(22,new area(2,"������","QAX","��","8059","��","��"));
pList.addAt(22,new area(3,"ɽ�ϵ���","SND","��","��","��","��"));
pList.addAt(22,new area(4,"�˶���","NED","��","��","��","��"));
pList.addAt(22,new area(5,"�տ������","XID","��","��","��","��"));
pList.addAt(22,new area(6,"�տ�����","XIG","892","8018","857000","��"));
pList.addAt(22,new area(7,"��������","NAD","��","��","��","��"));
pList.addAt(22,new area(8,"������","NAG","��","��","��","��"));
pList.addAt(22,new area(9,"�������","NGD","897","��","��","��"));
pList.addAt(22,new area(10,"������","BUR","��","��","��","��"));
pList.addAt(22,new area(11,"��֥����","NYD","��","��","��","��"));
pList.addAt(22,new area(12,"��֥��","NYI","��","��","��","��"));
pList.add(new province("����ʡ","SN"));
pList.addAt(23,new area(0,"������","SIA","29","��","710000","��"));
pList.addAt(23,new area(1,"ͭ����","TCN","919","��","727000","��"));
pList.addAt(23,new area(2,"������","BJI","917","��","721000","��"));
pList.addAt(23,new area(3,"������","XYS","910","��","712000","��"));
pList.addAt(23,new area(4,"μ����","WNA","913","9238","714000","715400"));
pList.addAt(23,new area(5,"�Ӱ���","YNA","911","��","716000","��"));
pList.addAt(23,new area(6,"������","HZJ","916","��","723000","��"));
pList.addAt(23,new area(7,"��������","AKG","��","��","��","��"));
pList.addAt(23,new area(8,"������","ANK","��","��","��","��"));
pList.addAt(23,new area(9,"�������","SLD","��","��","��","��"));
pList.addAt(23,new area(10,"������","SZO","914","��","��","��"));
pList.addAt(23,new area(11,"���ֵ���","YLD","��","��","��","��"));
pList.addAt(23,new area(12,"������","YLN","��","��","��","��"));
pList.add(new province("����ʡ","GS"));
pList.addAt(24,new area(0,"������","LHW","931","��","730000","��"));
pList.addAt(24,new area(1,"��������","JYG","9477","��","735100","��"));
pList.addAt(24,new area(2,"�����","JCS","9455/935","��","737100","��"));
pList.addAt(24,new area(3,"������","BYS","9430","��","730900","��"));
pList.addAt(24,new area(4,"��ˮ��","TSU","938","��","741000","��"));
pList.addAt(24,new area(5,"��Ȫ����","JQD","��","��","��","��"));
pList.addAt(24,new area(6,"������","YMS","9471/937","937","735200","735000"));
pList.addAt(24,new area(7,"��Ҵ����","ZYJ","��","��","��","��"));
pList.addAt(24,new area(8,"��Ҵ��","ZYE","936","��","734000","��"));
pList.addAt(24,new area(9,"��������","WWD","��","��","��","��"));
pList.addAt(24,new area(10,"������","WWS","935","��","733000","��"));
pList.addAt(24,new area(11,"��������","DXD","��","��","��","��"));
pList.addAt(24,new area(12,"������","DXI","��","��","��","��"));
pList.addAt(24,new area(13,"¤�ϵ���","LND","��","��","��","��"));
pList.addAt(24,new area(14,"�䶼��","WUD","��","��","��","��"));
pList.addAt(24,new area(15,"ƽ������","PLG","��","��","��","��"));
pList.addAt(24,new area(16,"ƽ����","PLS","933","��","744000","��"));
pList.addAt(24,new area(17,"��������","QYD","��","��","��","��"));
pList.addAt(24,new area(18,"������","XFS","934","��","745000","��"));
pList.addAt(24,new area(19,"���Ļ���������","LXH","��","��","��","��"));
pList.addAt(24,new area(20,"������","LXR","930","��","731100","��"));
pList.addAt(24,new area(21,"���ϲ���������","GNZ","��","��","��","��"));
pList.addAt(24,new area(22,"������","HEZ","��","��","��","��"));
pList.add(new province("�ຣʡ","QH"));
pList.addAt(25,new area(0,"������","XNN","971","��","810000","��"));
pList.addAt(25,new area(1,"��������","HDD","��","��","��","��"));
pList.addAt(25,new area(2,"ƽ����","PAN","972","��","��","��"));
pList.addAt(25,new area(3,"��������������","HBZ","��","��","��","��"));
pList.addAt(25,new area(4,"��Դ����������","MY","��","��","��","��"));
pList.addAt(25,new area(5,"���ϲ���������","HNZ","��","��","��","��"));
pList.addAt(25,new area(6,"ͬ����","TRN","��","��","��","��"));
pList.addAt(25,new area(7,"���ϲ���������","HNN","��","��","��","��"));
pList.addAt(25,new area(8,"������","GHE","��","��","��","��"));
pList.addAt(25,new area(9,"�������������","GOL","��","��","��","��"));
pList.addAt(25,new area(10,"������","MAQ","975","��","��","��"));
pList.addAt(25,new area(11,"��������������","YSZ","��","��","��","��"));
pList.addAt(25,new area(12,"������","YSK","��","��","��","��","��"));
pList.addAt(25,new area(13,"�����ɹ������������","HXZ","��","��","��","��"));
pList.addAt(25,new area(14,"���ľ��","GOS","979","977","816000","817000"));
pList.add(new province("����","NX"));
pList.addAt(26,new area(0,"������","INC","951","��","750000","��"));
pList.addAt(26,new area(1,"ʯ��ɽ��","SZS","952","��","753000","��"));
pList.addAt(26,new area(2,"������","WZS","953","��","751100","751600"));
pList.addAt(26,new area(3,"��ԭ����","GYD","��","��","��","��"));
pList.addAt(26,new area(4,"��ԭ��","GYN","��","954","��","��"));
pList.add(new province("�½�","XJ"));
pList.addAt(27,new area(0,"��³ľ����","URC","991","��","830000","��"));
pList.addAt(27,new area(1,"����������","KAR","990","��","834000","��"));
pList.addAt(27,new area(2,"��³������","TUD","��","��","��","��"));
pList.addAt(27,new area(3,"��³����","TUR","995","��","838000","��"));
pList.addAt(27,new area(4,"���ܵ���","HND","��","��","��","��"));
pList.addAt(27,new area(5,"������","HAM","9022","��","839000","��"));
pList.addAt(27,new area(6,"��������������","CJZ","��","��","��","��"));
pList.addAt(27,new area(7,"������","CJS","994","��","831100","��"));
pList.addAt(27,new area(8,"���������ɹ�������","BOR","��","��","��","��"));
pList.addAt(27,new area(9,"������","BLE","9093","��","833400","��"));
pList.addAt(27,new area(10,"���������ɹ�������","BAG","��","��","��","��"));
pList.addAt(27,new area(11,"�������","KOR","996","��","841000","��"));
pList.addAt(27,new area(12,"�����յ���","AKD","��","��","��","��"));
pList.addAt(27,new area(13,"��������","AKS","997","��","843000","��"));
pList.addAt(27,new area(14,"�������տ¶�����������","KIZ","��","��","��","��"));
pList.addAt(27,new area(15,"��ͼʲ��","ART","9081","��","845300","��"));
pList.addAt(27,new area(16,"��ʲ����","KSI","��","��","��","��"));
pList.addAt(27,new area(17,"��ʲ��","KHG","998","��","844000","��"));
pList.addAt(27,new area(18,"�������","HO","��","��","��","��"));
pList.addAt(27,new area(19,"������","HTS","9032","��","848000","��"));
pList.addAt(27,new area(20,"���������������","ILD","��","��","��","��"));
pList.addAt(27,new area(21,"������","KUY","992","��","833200","��"));
pList.addAt(27,new area(22,"�������","ILI","��","��","��","��"));
pList.addAt(27,new area(23,"������","YIN","999","��","835000","��"));
pList.addAt(27,new area(24,"���ǵ���","TCD","��","��","��","��"));
pList.addAt(27,new area(25,"������","TCS","9003","��","834700","��"));
pList.addAt(27,new area(26,"����̩����","ALD","��","��","��","��"));
pList.addAt(27,new area(27,"����̩��","ALT","9009","��","836500","��"));
pList.addAt(27,new area(28,"ʯ������","SHZ","993","��","832000","��"));

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
	
	
	/*������ֵ*/

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
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">��������:</td><td bgcolor=\"#7EC0EE\" width=\"101\" id=\"areacode\" >&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">����:</td><td bgcolor=\"#7EC0EE\" id=\"areazone\">&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">��������:</td><td bgcolor=\"#7EC0EE\" id=\"areaexzone\" >&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">�����ʱ�:</td><td bgcolor=\"#7EC0EE\"  id=\"areamail\">&nbsp;</td></tr>";
	temp+="<tr onmouseover=\"overcolor_areadiv(this)\" onmouseout=\"outcolor_areadiv(this)\" style=\"CURSOR: hand;COLOR:#000000\" onclick=\"tr_click_areadiv()\"><td  bgcolor=\"#7EC0EE\">�����ʱ�:</td><td bgcolor=\"#7EC0EE\"  id=\"areaexmail\">&nbsp;</td></tr></table>";
	window.areadiv.innerHTML=temp
}
initarea();
setdiv(nowarea);