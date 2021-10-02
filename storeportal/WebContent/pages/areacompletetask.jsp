<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal -Manage Task</title>
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
   <s:hidden name="portfolioId" id="portfolioId"/>
   <s:hidden name="taskId" id="taskId"/>
   <s:hidden name="postYear" id="postYear"/>
   <s:hidden name="postMonth" id="postMonth"/>
   <s:hidden name="postWeek" id="postWeek"/>
   <s:hidden name="postLevel" id="postLevel"/>
   <s:hidden name="storeId" id="storeId"/>
   <s:hidden name="areaId" id="areaId"/>

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
        
	<h2>${breadCrumbtm0}</h2>	  

        <h3>Completed Task</h3>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        <table>
        	<tr >
            <td width="30"></td>
            <td colspan="8">
                <a href="TaskPostDetail_areacreate.do" class="btnNorm2">&laquo; Create New Task</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="TaskPostMain_areatask.do" class="btnNorm2">&laquo; Manage Task</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="TaskPostMain_areapending.do" class="btnNorm2">&laquo; Pending Task</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
				<a href="TaskPostMain_areafail.do" class="btnNorm2">&laquo; Failed Task</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;             </td>
           
          </tr>
        </table>
        
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="buy1_table">
     
          <tr>
            <td class="reg_head" width="10%">No</td>
            <td class="reg_head">Task</td>
           	<td class="reg_head">Start</td>
          	<td class="reg_head">End</td>
          	<td class="reg_head">Response</td>
          </tr>
          <s:iterator value="areaCompletedList" status="post">
          <tr>
        		<td align="center"><s:property  value="#post.index+1"/></td>
        		<td>
        			<s:url id="viewpost" action="TaskPostDetail_areaview.do" escapeAmp="false">
        				<s:param name="taskId" value="taskId"></s:param>
        				<s:param name="taskAreaId" value="taskAreaId"></s:param>
        			</s:url>
        			<s:a href="%{viewpost}">
        				<span><strong><s:property value="taskSubject"/></strong></span>
        			</s:a>
                </td>
                  
        	
        		<td><s:property value="taskStartDate"/></td>
        		<td><s:property value="taskEndDate"/></td>
        		<td><s:property value="taskResponseType" /></td>        		
                </tr>
                </s:iterator>
          <tr class="forum_tr">
            <td></td>
            <td></td>
            <td></td>
             <td></td>
             <td></td>
          </tr>
        	
          <tr class="forum_tr">
            <td >
                        
               <sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-arrowreturnthick-1-w" cssStyle="width:110px;" onclick="goBack()">back</sj:a>
              
            
                 </td>
                  <td >
                          
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-home" cssStyle="width:110px;" onclick="goHome()">home</sj:a>
            
            
                 </td>
                <td colspan="7"></td>
              
          </tr>
          
        </table>
        <p>&nbsp;</p>
        <!-- content end -->
	</div>
   </s:form>
</div>
<div class="grid_16">
        <div class="footer">
            Copyright &copy; 2011 7-eleven.
        </div>
	</div>
	<div class="clear"></div>
</body>
</html>

