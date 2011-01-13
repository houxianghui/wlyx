

<%@ taglib uri = "http://struts.apache.org/tags-bean" prefix = "bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
 <%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib uri = "/WEB-INF/tld/eis-auth.tld" prefix = "auth" %>
<%@ taglib uri = "/WEB-INF/tld/eis-htm.tld" prefix = "htm" %>


<%@ page import = "org.apache.struts.Globals" %>
<%@ page import = "org.apache.struts.action.Action" %>
<%@ page import = "org.apache.struts.action.ActionErrors" %>
<%@ page import = "org.apache.struts.validator.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>

<%@ page import="com.eis.cache.*" %> 
<%@ page import = "com.eis.portal.UserContext" %>
<%@ page import = "com.eis.util.*" %>


<%@ page errorPage="/common/error.jsp" %>

<%

UserContext user = ( UserContext )session.getAttribute("user");
try {
        
    if ( user == null ) {
    
%>

<script language="JavaScript">
window.alert('�Ự��ʱ�������µ�¼');
parent.parent.parent.location="<%=request.getContextPath()%>/index.jsp";
</script>

<%
	return;
    }
    
} catch ( Throwable t ) {
    t.printStackTrace();
}
%>


<%
	String css="Style.css";
	String css_temp= (String)session.getAttribute("css"); 
	if(null != css_temp){
		css=css_temp;	 
	} 	
%>



<link href="CSS/<%=css%>" rel="stylesheet" type="text/css">

<SCRIPT src="js/validate.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/event.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/func.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/progressBar.js" type="text/javascript"></SCRIPT>


