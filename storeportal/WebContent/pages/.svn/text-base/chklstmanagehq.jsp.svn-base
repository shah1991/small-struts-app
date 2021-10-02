<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal -Manage CheckList</title>
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
	
	function hqDelete(val) {
		
		if(confirm('Are you sure?')){
			document.forms[0].action = 'ChklstPostDetail_admindelete.do?chklstId='+val;
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
	function goExport(){
		document.forms[0].action = 'ChklstPostMain_hqAreaExcel.do';
		document.forms[0].submit();
	}
	
	window.history.forward(); function noBack() { window.history.forward();
	setTimeout("preventBack()", 0);

    window.onunload=function(){null};} 
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();noBack();" >
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   <s:form action="PostMain_search" method="post" theme="simple">
   <s:hidden name="portfolioId" id="portfolioId"/>
   <s:hidden name="chklstId" id="chklstId"/>
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
                <li><a href="ChklstPostMain_topic.do?chklstGroupId=${chklstGroupId }"> ${topicName}</a> &raquo;</li>
            </ul>
        </div>
        <div class="clearfix" /></div>
        
		<h2>CheckList Management</h2>

        <h3>Manage All CheckList &nbsp; &nbsp;( ${topicName})</h3>
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
                <a href="ChklstPostMain_chklstpost.do?postLevel=Y" class="btnNorm2">&laquo; CheckList Calendar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="ChklstPostDetail_hqcreate.do" class="btnNorm2">&laquo; Create New CheckList</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="ChklstPostMain_hqcomplete.do" class="btnNorm2">&laquo; Completed CheckList</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     
               
             </td>
           
          </tr>
        </table>
        
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="buy1_table">
         
          <tr>
            <td class="reg_head" width="10%">No</td>
            <td class="reg_head">CheckList</td>
           	<td class="reg_head">Area Response</td>
          	<td class="reg_head">Store Response</td>
          	<td class="reg_head">Start</td>
          	<td class="reg_head">End</td>
          	<td class="reg_head" colspan="3" align="center">Action</td>
          </tr>
          <tr class="forum_tr">
            <td colspan="9"></td>
            
          </tr>
        	<s:iterator value="chklstAllList" status="post">
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
                
                 <td align="center">
                	 <s:url id="area" action="ChklstPostMain_arearesponse" escapeAmp="false">
        				<s:param name="chklstId" value="chklstId"></s:param>
        			</s:url>
        			<s:a href="%{area}">
                 		<span><strong><s:property value="areaCount"/></strong></span>
                 	</s:a>
                 	
                 </td>
                
        	  <td align="center"> <s:url id="store" action="ChklstPostMain_storeresponse" escapeAmp="false">
        				<s:param name="chklstId" value="chklstId"></s:param>
        			</s:url>
        			<s:a href="%{store}">
                 		<span><strong><s:property value="storeCount"/></strong></span>
                 	</s:a></td>
        		<td><s:property value="chklstStartDate"/></td>
        		<td><s:property value="chklstEndDate"/></td>
        		<td>
        			
        			
        			<s:url id="copypost" action="ChklstPostDetail_detailcopy" escapeAmp="false">
        				<s:param name="chklstId" value="chklstId"></s:param>
        			</s:url>
        		
        			<s:a href="%{copypost}">
        			
        				<img src="images/copy-icon.gif" style="width:30px; height: 25px;" title="Copy"></img>
        			</s:a>
        			
        			</td>
        		<td>
        			
        			<s:url id="editpost" action="ChklstPostDetail_detailedit" escapeAmp="false">
        				<s:param name="chklstId" value="chklstId"></s:param>
        			</s:url>
        			
        			
        		
        			<s:a href="%{editpost}">
        			
        				<img src="images/edit.gif" style="width:30px; height: 25px;" title="Edit"></img>
        			</s:a>
        			
        			</td><td>
        			<s:url id="deletepost" action="ChklstPostDetail_admindelete" escapeAmp="false">
        				<s:param name="chklstId" value="chklstId"></s:param>
        			</s:url>
					<s:a href="%{deletepost}">
        				<img src="images/delete.gif" style="width: 25px;" title="Delete" ></img>
        			</s:a>	
                </td>
        		
        		</tr>
        	</s:iterator>
           <tr class="forum_tr">
          
            <td >
                        
               <sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-arrowreturnthick-1-w" cssStyle="width:110px;" onclick="goBack()">back</sj:a>
              
            
                 </td>
                  <td >
                          
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-home" cssStyle="width:110px;" onclick="goHome()">home</sj:a>
            
            
                 </td>
                 <td colspan="7">&nbsp;<!--<sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-document" cssStyle="width:160px;" onclick="goExport()">Export To Excel</sj:a>--></td>
              
          </tr>
          
        </table>
        <p>&nbsp;</p>
        <!-- content end -->
	</div>
   </s:form>
</div>
</body>
</html>

