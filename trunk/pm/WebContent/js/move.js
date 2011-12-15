/***************************************************************************************************************
 * �ļ�����������list�б���һЩ���߷���
 *
 * ��Ҫ������
 *          1�� moveUp(oSelect,isToTop) ������������������������ �����ƶ�һ��list�б���ѡ����Ŀ��
 *                                                                ����֧�ֶ�ѡ�ƶ������������Ƿ��ƶ�������
 *          2�� moveDown(oSelect,isToBottom)�������������������� �����ƶ�һ��list�б���ѡ����Ŀ��
 *                                                                ����֧�ֶ�ѡ�ƶ������������Ƿ��ƶ����ײ�
 *          3�� moveSelected(oSourceSel,oTargetSel) ������������ �������б��֮��ת������
 *          4�� moveAll(oSourceSel,oTargetSel)������������������ ת�������б��֮���ȫ������
 *          5�� deleteSelectItem(oSelect) ���������������������� ɾ����ѡ����Ŀ
 * 
 ****************************************************************************************************************/
 
/**
 * ʹѡ�е���Ŀ����
 *
 * oSelect: Դ�б��
 * isToTop: �Ƿ�����ѡ������ˣ������������ƣ�
 *          trueΪ�ƶ������ˣ�false��֮��Ĭ��Ϊfalse
 */
function moveUp(oSelect,isToTop)
{
    //Ĭ��״̬�����ƶ�������
    if(isToTop == null)
        var isToTop = false;
        
    //����Ƕ�ѡ------------------------------------------------------------------
    if(oSelect.multiple)
    {
        for(var selIndex=0; selIndex<oSelect.options.length; selIndex++)
        {
            //����������ƶ������˱�־
            if(isToTop)
            {
                if(oSelect.options[selIndex].selected)
                {
                    var transferIndex = selIndex;
                    while(transferIndex > 0 && !oSelect.options[transferIndex - 1].selected)
                    {
                        oSelect.options[transferIndex].swapNode(oSelect.options[transferIndex - 1]);
                        transferIndex --;
                    }
                }
            }
            //û�������ƶ������˱�־
            else
            {
                if(oSelect.options[selIndex].selected)
                {
                    if(selIndex > 0)
                    {
                        if(!oSelect.options[selIndex - 1].selected)
                            oSelect.options[selIndex].swapNode(oSelect.options[selIndex - 1]);
                    }
                }
            }
        }
    }
    //����ǵ�ѡ--------------------------------------------------------------------
    else
    {
        var selIndex = oSelect.selectedIndex;
        if(selIndex <= 0)
            return;
        //����������ƶ������˱�־
        if(isToTop)
        {
            while(selIndex > 0)
            {
                oSelect.options[selIndex].swapNode(oSelect.options[selIndex - 1]);
                selIndex --;
            }
        }
        //û�������ƶ������˱�־
        else        
            oSelect.options[selIndex].swapNode(oSelect.options[selIndex - 1]);
    }
}

/**
 * ʹѡ�е���Ŀ����
 *
 * oSelect: Դ�б��
 * isToTop: �Ƿ�����ѡ����׶ˣ������������ƣ�
 *          trueΪ�ƶ����׶ˣ�false��֮��Ĭ��Ϊfalse
 */
function moveDown(oSelect,isToBottom)
{
    //Ĭ��״̬�����ƶ�������
    if(isToBottom == null)
        var isToBottom = false;
        
    var selLength = oSelect.options.length - 1;
    
    //����Ƕ�ѡ------------------------------------------------------------------
    if(oSelect.multiple)
    {
        for(var selIndex=oSelect.options.length - 1; selIndex>= 0; selIndex--)
        {
            //����������ƶ������˱�־
            if(isToBottom)
            {
                if(oSelect.options[selIndex].selected)
                {
                    var transferIndex = selIndex;
                    while(transferIndex < selLength && !oSelect.options[transferIndex + 1].selected)
                    {
                        oSelect.options[transferIndex].swapNode(oSelect.options[transferIndex + 1]);
                        transferIndex ++;
                    }
                }
            }
            //û�������ƶ������˱�־
            else
            {
                if(oSelect.options[selIndex].selected)
                {
                    if(selIndex < selLength)
                    {
                        if(!oSelect.options[selIndex + 1].selected)
                            oSelect.options[selIndex].swapNode(oSelect.options[selIndex + 1]);
                    }
                }
            }
        }
    }
    //����ǵ�ѡ--------------------------------------------------------------------
    else
    {
        var selIndex = oSelect.selectedIndex;
        if(selIndex >= selLength - 1)
            return;
        //����������ƶ������˱�־
        if(isToBottom)
        {
            while(selIndex < selLength - 1)
            {
                oSelect.options[selIndex].swapNode(oSelect.options[selIndex + 1]);
                selIndex ++;
            }
        }
        //û�������ƶ������˱�־
        else        
            oSelect.options[selIndex].swapNode(oSelect.options[selIndex + 1]);
    }
}

/**
 * �ƶ�select�Ĳ�������,�������value���˺�����valueΪ��׼�����ƶ�
 *
 * oSourceSel: Դ�б����� 
 * oTargetSel: Ŀ���б�����
 */
function moveSelected(oSourceSel,oTargetSel)
{
    //�����洢value��text�Ļ�������
    var arrSelValue = new Array();
    var arrSelText = new Array();
    //���������ѡ�е�options����value����Ӧ
    var arrValueTextRelation = new Array();
    var index = 0;//��������������������
    
    //�洢Դ�б�������е����ݵ������У�������value��ѡ��option�Ķ�Ӧ��ϵ
    for(var i=0; i<oSourceSel.options.length; i++)
    {
        if(oSourceSel.options[i].selected)
        {
            //�洢
            arrSelValue[index] = oSourceSel.options[i].value;
            arrSelText[index] = oSourceSel.options[i].text;
            //����value��ѡ��option�Ķ�Ӧ��ϵ
            arrValueTextRelation[arrSelValue[index]] = oSourceSel.options[i];
            index ++;
        }
    }
    
	var appName = navigator.appName; 
	if(appName == "Microsoft Internet Explorer"){
					
		//���ӻ�������ݵ�Ŀ���б���У���ɾ��Դ�б���еĶ�Ӧ��
    	for(var i=0; i<arrSelText.length; i++)  {
        	//����
        	var oOption = document.createElement("option");
        	oOption.text = arrSelText[i];
        	oOption.value = arrSelValue[i];
        	oTargetSel.add(oOption);
        	//ɾ��Դ�б���еĶ�Ӧ��
        	oSourceSel.removeChild(arrValueTextRelation[arrSelValue[i]]);
    	} 			
	}
	else{
		//���ӻ�������ݵ�Ŀ���б���У���ɾ��Դ�б���еĶ�Ӧ��
    	for(var i=0; i<arrSelText.length; i++)  {
        	//����
        	var oOption = document.createElement("option");
        	oOption.text = arrSelText[i];
        	oOption.value = arrSelValue[i];       
			var para = document.getElementById(oTargetSel.id);
    		para.appendChild(oOption);
        	//ɾ��Դ�б���еĶ�Ӧ��
			para = document.getElementById(oSourceSel.id);
			para.removeChild(arrValueTextRelation[arrSelValue[i]]);
	    }	
	}

    

}

/**
 * �ƶ�select��ѡ����һ������,�������value���˺�����valueΪ��׼�����ƶ�
 *
 * oSourceSel: Դ�б����� 
 * oTargetId: Ŀ�����ض���
 * oTargetName��Ŀ���ı������
 */
function moveOneSelectedToText(oSourceSel,oTargetName,oTargetId)
{
       var selValue= null;
       var selText=null;
       
       //�洢Դ�б����ѡ�е�һ�����ݵ����������
       for(var i=0; i<oSourceSel.options.length; i++)
       {
       		if(oSourceSel.options[i].selected)
       		{
       			//�洢
       			selValue = oSourceSel.options[i].value;
       			selText = oSourceSel.options[i].text;
       		}
       	}
       	if(selValue == null){
       	alert("��ѡ������ɫ��");       	
       	}else{
        oTargetName.value=selText;       
       	oTargetId.value=selValue;
       	}

}



/**
 * �ƶ�select����������
 *
 * oSourceSel: Դ�б����� 
 * oTargetSel: Ŀ���б�����
 */
function moveAll(oSourceSel,oTargetSel)
{
    //�����洢value��text�Ļ�������
    var arrSelValue = new Array();
    var arrSelText = new Array();
    
    //�洢����Դ�б�����ݵ���������
    for(var i=0; i<oSourceSel.options.length; i++)
    {
        arrSelValue[i] = oSourceSel.options[i].value;
        arrSelText[i] = oSourceSel.options[i].text;
    }
    
	var appName = navigator.appName; 
	if(appName == "Microsoft Internet Explorer"){
		//������������������ӵ�Ŀ��select��
    	for(var i=0; i<arrSelText.length; i++)  {
        	var oOption = document.createElement("option");
        	oOption.text = arrSelText[i];
        	oOption.value = arrSelValue[i];
        	oTargetSel.add(oOption);
    	}					
	} else {
		//������������������ӵ�Ŀ��select��
    	for(var i=0; i<arrSelText.length; i++)  {
        	var oOption = document.createElement("option");
        	oOption.text = arrSelText[i];
        	oOption.value = arrSelValue[i];
			var para = document.getElementById(oTargetSel.id);
    		para.appendChild(oOption);        
    	}	
	}
	    
    //���Դ�б�����ݣ�����ƶ�
    oSourceSel.innerHTML = "";
}

/**
 * ɾ��ѡ����Ŀ
 *
 * oSelect: Դ�б����� 
 */
function deleteSelectItem(oSelect)
{
    for(var i=0; i<oSelect.options.length; i++)
    {
        if(i>=0 && i<=oSelect.options.length-1 && oSelect.options[i].selected)
        {
            oSelect.options[i] = null;
            i --;
        }
    }
}

/**
 *����¼��ɫ�Ƿ�����ѡ�û�����Ȩ���б�����
 *���ߣ����
 һ���б�һ���ı�
 *ʱ�䣺20051012
 */
function checkMenu(text,list){
	
	//�ҵ��˵���ѡ����
	//for(var j=0;j<menu.length;j++)
	//{
		//if(menu[j].selected == true)
		//	var jj = j;
	//}
	//alert(menu[jj].text);
	
	//�˵�ѡ�������б��и�����бȽ�
	for(var i=0; i<list.options.length;i++)
	{
		if(list.options[i].text == text.value){
			//alert("right");
			//location.href='user_list.htm';
			return true;
		}
	}
	//��û���ҵ���Ӧ����
	alert("��¼��ɫ����ѡ����Ȩ�޲�ƥ�䣡");
	list.focus();
	return false;
}

/**
 *����¼��ɫ�Ƿ�����ѡ�û�����Ȩ���б�����
 *���ߣ����
 *ʱ�䣺20051012
 ������һ���б�һ���˵�
 */
function checkMenu1(menu,list){
	
	//�ҵ��˵���ѡ����
	for(var j=0;j<menu.length;j++)
	{
		if(menu[j].selected == true)
			var jj = j;
	}
	//alert(menu[jj].text);
	
	//�˵�ѡ�������б��и�����бȽ�
	for(var i=0; i<list.options.length;i++)
	{
		if(list.options[i].value == menu[jj].value && list.options[i].text == menu[jj].text){
			//alert("right");
			location.href='user_list.htm';
			return;
		}
	}
	//��û���ҵ���Ӧ����
	alert("��¼��ɫ����ѡ����Ȩ�޲�ƥ�䣡");
	list.focus();
}


//js�ļ����