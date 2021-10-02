<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
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
	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();">
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   <s:form action="PostMain_search" method="post" theme="simple">
   <s:hidden name="forumId" id="forumId"/>
   <s:hidden name="topicId" id="topicId"/>
   <s:hidden name="postYear" id="postYear"/>
   <s:hidden name="postMonth" id="postMonth"/>
   <s:hidden name="postWeek" id="postWeek"/>
   <s:hidden name="postLevel" id="postLevel"/>
   
   
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
        
		

        <h3>BROWSE CHECKLIST</h3>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="buy1_table">
          <tr>
            <td class="reg_head" width="10%" align="center">No</td>
            <td class="reg_head">BROWSE CHECKLIST</td>
            <td class="reg_head" width="80" align="center"></td>
          </tr>
          <tr class="forum_tr">
            <td></td>
            <td></td>
            <td></td>
          </tr>
        	<s:iterator value="postList" status="post">
        		<tr>
        		<td align="center"><s:property  value="#post.index+1"/></td>
        		<td>
        			<s:url id="viewpost" action="ChklstPostDetail_adminview" escapeAmp="false">
        				<s:param name="chklstId" value="chklstId"></s:param>
        			</s:url>
        			<s:a href="%{viewpost}">
        				<span><strong><s:property value="chklstSubject"/></strong></span>
        			</s:a>
                </td>
        		<td>
        			<s:property value="dtlCount"/>
        		</td>
        		</tr>
        	</s:iterator>
          <tr class="forum_tr">
            
            <td >
                        
               <sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-arrowreturnthick-1-w" cssStyle="width:110px;" onclick="goBack()">back</sj:a>
              
            
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
   </s:form>
</div>
</body>
</html>

