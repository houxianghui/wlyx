
<%@ page contentType="text/html; charset=GBK"%>

<!-- 记载行动码字典数据集 生成 js文件 -->

<script type="text/javascript">	
 function isExistActCode(type,code){

  var fiMap=<%=AcctsAdjustActCodeCache.getMap("1")%>;
  var fsMap=<%=AcctsAdjustActCodeCache.getMap("2")%>;
  var tag=0;
 	if((type=="1")&&(fiMap!=null)){
 	  tag=0;
       for(var key in fiMap){
         if(key==code)
           {
           tag=1;
           break;
           }
       }
 	}
 	else if((type=="2")&&(fsMap!=null))
 	{
 	   tag=0;
        for(var key in fsMap){
         if(key==code)
           {
           tag=1;
           break;
           }
       }
 	}
 	if(tag==1)
 	  return true;
 	else
 	  return false;
 }
</script>



