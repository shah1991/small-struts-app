<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Post Management</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="south-street" />

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
    function goHome() {
	document.forms[0].action = 'Login_home.do';
	document.forms[0].submit();
	}

	function goBack()
	 {
	window.history.back();
	}
	function doSearch() {
		document.forms[0].action = 'PostMain_search.do';
		document.forms[0].submit();
	}

	function goCreatePost() {
		document.forms[0].action = 'PostDetail_detailcreate.do';
		document.forms[0].submit();
	}
	
	function goTopics() {
		document.forms[0].action = 'TopicMain_search.do';
		document.forms[0].submit();
	}
	
	function doDeletePost(val) {
		if(confirm('Are you sure?')){
			document.forms[0].action = 'PostMain_detaildelete.do?topicId='+val;
			document.forms[0].submit();
		}else{
			return false;
		}
	}
	
	function checkAll(theForm) { // check all the checkboxes in the list
	  for (var i=0;i<theForm.elements.length;i++) {
	    var e = theForm.elements[i];
			var eName = e.name;
	    	if (eName != 'allbox' && 
	            (e.type.indexOf("checkbox") == 0)) {
	        	e.checked = theForm.allbox.checked;		
			}
		} 
	}
	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();">
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   <s:form action="PostMain_search" method="post" theme="simple">
   <s:hidden name="chklstGroupId" id="chklstGroupId"/>
   
   <div class="container_16">
	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <li><?php echo $activity['activity']; ?></li>
            </ul>
        </div>
        <div class="clearfix" /></div>
        
		<h2>Check List Management</h2>

        <h3>Browse Topics</h3>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="buy1_table">
          <tr>
            <td class="reg_head" width="10%" align="center">No</td>
            <td class="reg_head" colspan="2">BROWSE TOPICS</td>
            <!--<td class="reg_head">AREA</td>
            <td class="reg_head">STORE</td>-->
           
          </tr>
          <tr class="forum_tr">
            <td></td>
            <td colspan="2"></td>
            <!--<td></td>
            <td></td>-->
          </tr>
        	<s:iterator value="topicList" status="topic">
        		<tr>
        		<td align="center"><s:property  value="#topic.index+1"/></td>
        		<td colspan="2">
        			<s:url id="viewpost" action="ChklstPostMain_entry" escapeAmp="false">
        				<s:param name="topicId" value="topicId"></s:param>
        			
        			</s:url>
        			<s:a href="%{viewpost}">
        				<span style="text-transform: uppercase;"><strong><s:property value="topicName"/></strong></span>
        			</s:a>
                </td>
                            <!--<td>0</td>
                            <td><s:property value="dtlCount"/></td>-->
        	        
              	</tr>
        	</s:iterator>
          <tr class="forum_tr">
         
            <td >
                        
             
              
            
                 </td>
                  <td >
                          
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-home" cssStyle="width:110px;" onclick="goHome()">home</sj:a>
            
            
                 </td><td></td>
                 
          </tr>
          <tr class="forum_tr">
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </table>
        <p>&nbsp;</p>
        <!-- content end -->
	</div>
	<div class="grid_16">
        <div class="footer">
            Copyright &copy; 2011 7-eleven.
        </div>
	</div>
	<div class="clear"></div>
   </s:form>
</div>
</body>
</html>

