<%@ include file = "/includes/common.jsp" %> 

<%@ page  contentType="text/html; charset=GBK" %> 


<html>

<title>锁定码调整结果列表</title>


<body  class="body_bg_grey1" BGPROPERTIES="fixed" >

<form method="post" action="Lock.do"> 
 
<%=ViewUtil.getTitle("锁定码调整结果")%>  

  <table width="98%"  align="center" class="dtPanel_Line"  border="0" cellspacing="1" cellpadding="0"> 
         <tr > 
            <td class="dtPanel_Top02">            
           
            <table  class=heightspace_top1  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
            <table width="98%"  border="0" cellspacing="1" cellpadding="0">    			
    			<tr>
    				<td>     				
             &nbsp;账号:<input name="账号" type="text" value="0004041170000026648"  size="19" maxlength="19" />
             &nbsp;&nbsp;调整结果:&nbsp;
               <select name="select">
                 <option value="0">--</option>
                 <option value="1">已通过</option>
                 <option value="2">已执行</option>
                 <option value="3">已否决</option>                 
               </select>
			   &nbsp;&nbsp;<input type="submit" name="Submit" value="搜索"> 
           		</td>
            	</tr>            	
            </table>             
            <table  class=heightspace_top2  border="0" cellspacing="0" cellpadding="0"><tr><td></td></tr></table>
                      
          </tr> 
    </table> 

	
<table  class=heightspace_top3 ><tr><td></td></tr></table>


<table width="98%"   align="center"  border="0" cellpadding="0" cellspacing="0"> 
	<tr>
 		<td valign="top"  align="left"  >
			<div   align="center"   style="overflow:auto;width:100%;height:100%">	
		
		<table width="98%"  align="center"  class="dtPanel_Line1" border="0" cellpadding="0" cellspacing="1"> 
			<tr align="center" class="dtPanel_Top01"> 
						<td  nowrap>调整类型</td>
						<td  nowrap>机构号</td>
						<td  nowrap>账户号</td>
						<td  nowrap>调整级别</td>
						<td  nowrap>添加锁定码</td>
						<td  nowrap>解除锁定码</td>
						<td  nowrap>原因</td>
						<td  nowrap>分行名称</td>
						<td  nowrap>网点编号</td>
						<td  nowrap>经办</td>
						<td  nowrap>复核</td>
						<td  nowrap>提交时间</td>						
					</tr>
					
					
						<tr  align="center" class="dtPanel_Main" onclick="_clickTr( this )">
							<TD align="right" valign="middle">&nbsp;1</TD>
							<TD align="right" valign="middle">&nbsp;MASTER  人民币个人普通卡</TD>
							<td align="right" valign="middle">&nbsp;2005244112911</td>
							<td align="right" valign="middle">&nbsp;EDUYI</td>
							<TD align="right" valign="middle">&nbsp;I00000000001</TD>
							<TD align="right" valign="middle">&nbsp;20000</TD>
							<TD align="right" valign="middle">&nbsp;1000</TD>
							<TD align="right" valign="middle">&nbsp;20000</TD>
							<TD align="right" valign="middle">&nbsp;0005194124444000452</TD>
							<TD align="right" valign="middle">&nbsp;0005194124444000452</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>
							<TD align="right" valign="middle">&nbsp;0</TD>							
						</TR>
					
						<tr  align="center" class="dtPanel_Main" onclick="_clickTr( this )">
							<TD align="right" valign="middle">&nbsp;2</TD>
							<TD align="right" valign="middle">&nbsp;MASTER 双币种个人金卡</TD>
							<td align="right" valign="middle">&nbsp;2005244113457</td>
							<td align="right" valign="middle">&nbsp;EDUER</td>
							<TD align="right" valign="middle">&nbsp;10000000</TD>
							<TD align="right" valign="middle">&nbsp;20000</TD>
							<TD align="right" valign="middle">&nbsp;10000</TD>
							<TD align="right" valign="middle">&nbsp;20000</TD>
							<TD align="right" valign="middle">&nbsp;0005200834444000276</TD>
							<TD align="right" valign="middle">&nbsp;0005200834444000276</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>
							<TD align="right" valign="middle">&nbsp;0</TD>							
						</TR>
					
						<tr  align="center" class="dtPanel_Main" onclick="_clickTr( this )">
							<TD align="right" valign="middle">&nbsp;3</TD>
							<TD align="right" valign="middle">&nbsp;VISA 双币种个人金卡</TD>
							<td align="right" valign="middle">&nbsp;2005244113959</td>
							<td align="right" valign="middle">&nbsp;EDUSAN</td>
							<TD align="right" valign="middle">&nbsp;00022</TD>
							<TD align="right" valign="middle">&nbsp;0</TD>
							<TD align="right" valign="middle">&nbsp;0</TD>
							<TD align="right" valign="middle">&nbsp;0</TD>
							<TD align="right" valign="middle">&nbsp;0004041174444000257</TD>
							<TD align="right" valign="middle">&nbsp;0004041174444000257</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>							
						</TR>
					
						<tr  align="center" class="dtPanel_Main" onclick="_clickTr( this )">
							<TD align="right" valign="middle">&nbsp;4</TD>
							<TD align="right" valign="middle">&nbsp;MASTER  人民币个人普通卡</TD>
							<td align="right" valign="middle">&nbsp;2005248153714</td>
							<td align="right" valign="middle">&nbsp;WIP1</td>
							<TD align="right" valign="middle">&nbsp;I123456789123456005</TD>
							<TD align="right" valign="middle">&nbsp;50000</TD>
							<TD align="right" valign="middle">&nbsp;100000</TD>
							<TD align="right" valign="middle">&nbsp;50000</TD>
							<TD align="right" valign="middle">&nbsp;0005194124444000676</TD>
							<TD align="right" valign="middle">&nbsp;0005194124444000676</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>
							<TD align="right" valign="middle">&nbsp;0</TD>							
						</TR>
					
						<tr  align="center" class="dtPanel_Main" onclick="_clickTr( this )">
							<TD align="right" valign="middle">&nbsp;5</TD>
							<TD align="right" valign="middle">&nbsp;MASTER 人民币个人金卡</TD>
							<td align="right" valign="middle">&nbsp;2005248160222</td>
							<td align="right" valign="middle">&nbsp;WIP3</td>
							<TD align="right" valign="middle">&nbsp;S 警字 123456789M 宏</TD>
							<TD align="right" valign="middle">&nbsp;36000</TD>
							<TD align="right" valign="middle">&nbsp;80000</TD>
							<TD align="right" valign="middle">&nbsp;36000</TD>
							<TD align="right" valign="middle">&nbsp;0005200824444000186</TD>
							<TD align="right" valign="middle">&nbsp;0005200824444000186</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>
							<TD align="right" valign="middle">&nbsp;0</TD>							
						</TR>
					
						<tr  align="center" class="dtPanel_Main" onclick="_clickTr( this )">
							<TD align="right" valign="middle">&nbsp;6</TD>
							<TD align="right" valign="middle">&nbsp;MASTER 双币种个人普通卡无担保</TD>
							<td align="right" valign="middle">&nbsp;2005248160002</td>
							<td align="right" valign="middle">&nbsp;WIP2</td>
							<TD align="right" valign="middle">&nbsp;S 警字 12345678910000</TD>
							<TD align="right" valign="middle">&nbsp;5000</TD>
							<TD align="right" valign="middle">&nbsp;100000</TD>
							<TD align="right" valign="middle">&nbsp;5000</TD>
							<TD align="right" valign="middle">&nbsp;0005194134444000295</TD>
							<TD align="right" valign="middle">&nbsp;0005194134444000295</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>
							<TD align="right" valign="middle">&nbsp;1</TD>							
						</TR>
				</table>
		
			</div>
		</td>
	</tr>
</table>

			
<table width="98%"   align="center"  border="0" cellspacing="0" cellpadding="0">
        <tr  class="dtPanel_Pager">
          	<td>第1到8条记录 共8条记录 1/1页 跳转到</td> 
		</tr> 
</table> 
		
	
		
	
</form>
<p>&nbsp;</p>

</body>
</html>