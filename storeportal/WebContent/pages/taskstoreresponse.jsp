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

function goExport() {
	document.forms[0].action = 'TaskPostMain_storeexcel.do';
	document.forms[0].submit();
} 

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
   <s:form action="TaskPostMain_search" method="post" theme="simple">
   <s:hidden name="portfolioId" id="portfolioId"/>
   <s:hidden name="taskId" id="taskId"/>
   
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

          <h3>${taskSubject }</h3>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
      
        
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="buy1_table">
         
          <tr>
            <td class="reg_head" width="30" align="center">No</td>
  
          	<td class="reg_head">Recipient</td>
          	<td class="reg_head">Status</td>
          	<td class="reg_head">Response</td>
          	<td class="reg_head">Response Date</td>
          
          </tr>
          <tr class="forum_tr">
            <td colspan="5"></td>
            
          </tr>
        	<s:iterator value="storeResponseList" status="post">
        		<tr>
        		<td align="center"><s:property  value="#post.index+1"/></td>
        		<td>
        			<s:url id="viewpost" action="TaskPostDetail_storeview" escapeAmp="false">
        				<s:param name="taskId" value="taskId"></s:param>
        				<s:param name="taskStoreId" value="taskStoreId"></s:param>
        			</s:url>
        			<s:a href="%{viewpost}">
        				<span><strong><s:property value="recipient" /></strong> </span>
        			</s:a>
                </td>
               
             
        		
        		  <td><s:property value="taskResponseType" /></td>
        		 <td><s:property value="taskRemark" /></td>
        		 <td><s:property value="responseDate" /></td>
        		 
        		</tr>
        	</s:iterator>
          <tr class="forum_tr">
            
            <td >
                        
               <sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-arrowreturnthick-1-w" cssStyle="width:110px;" onclick="goBack()">back</sj:a>
              
            
                 </td>
                  <td >
                          
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-home" cssStyle="width:110px;" onclick="goHome()">home</sj:a>
            
            
                 </td>
                <td colspan="6"><sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-document" cssStyle="width:200px;" onclick="goExport()">Export To  Excel</sj:a></td>
          </tr>
          
        </table>
        <p>&nbsp;</p>
        <!-- content end -->
	</div>
   </s:form>
</div>
</body>
</html>

