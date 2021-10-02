<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Dairy Farm - Store Portal</title>
	<link href="css/screen.css" type="text/css" rel="stylesheet" />
	<sj:head jquerytheme="eggplant" />

    <link rel="shortcut icon" href="styles/images/favico.png" type="image/x-icon"/>
    <link rel="stylesheet" href="styles/style.css" />
    <link rel="stylesheet" href="styles/chat.css" />
    
    <script type="text/javascript" src="scripts/cufon-yui.js"></script>
	<script type="text/javascript" src="scripts/Eurostile_LT_400-Eurostile_LT_700-Eurostile_LT_oblique_400.font.js"></script>
    <script type="text/javascript">
        Cufon.replace('h1', {hover: true});
        Cufon.replace('h2', {hover: true, fontWeight: 700});
        Cufon.replace('h3', {hover: true});
        Cufon.replace('h4', {hover: true});
        Cufon.replace('h5', {hover: true});
        Cufon.replace('h6', {hover: true});
		Cufon.replace('.top_menu li a', {hover: true});
    </script>


<script type="text/javascript">
	function userLogin() {
		document.forms[0].action = 'Login_process.do';
		document.forms[0].submit();
	}

	function cancelLogin() {
		document.forms[0].action = 'Login_cancel.do';
		document.forms[0].submit();
	}
	
</script>
</head>
<body>
<div class="container_16">
<jsp:include page="/include/userheader.jsp"/>  
   
	<div class="grid_12">
		<h2>HOME</h2>
	
        <h3>Notification</h3>
        <ul class="checklist_btn">
      		 <s:iterator value="linkList" status="linkList">
       			 <li>
                    	<a href="<s:property value="url"/>">
                    
                    		<span><img src=".<s:property value="iconPath"/>" width="128" height="128" alt="" 
                            title="<s:property value="title"/>" /></span>
                        	<span class="icon_title"><s:property value="title"/></span>
                        </a>
                         <s:if test="%{count!=null}">
                       	 	 <a href="<s:property value="url"/>" class="iconNotify">
                            	<strong>${count}</strong>
                        	 </a>
                         </s:if>
                         
                 </li>
        	
      		  </s:iterator>
				
	  </ul>
        
     
        <p>&nbsp;</p>
        <!-- content end -->
	</div>
	 <div style="height: 20px;">&nbsp;</div>
	<!-- end .grid_11 -->
	<div class="grid_16">
        <div class="footer">
            Copyright &copy; 2011 7-eleven.
        </div>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>


 

