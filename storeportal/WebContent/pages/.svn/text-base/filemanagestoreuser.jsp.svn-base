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
		document.forms[0].action = 'PostMain_entry.do';
		document.forms[0].submit();
	}

	function doCreatePost() {
		
		document.forms[0].action = 'FilePostDetail_detailadd.do';
		document.forms[0].submit();
	
  }

	

	
	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();">
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   <s:form action="FilePostMain_search" method="post" theme="simple">
   <s:hidden name="groupId" id="groupId"/>
   <div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>

            </ul>
        </div>
        <div class="clearfix" /></div>
        
	<h2>${breadCrumbfs0}</h2>	

        <h3>Manage All File Share</h3>
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

                <a href="FilePostMain_storeuser.do" class="btnNorm2">&laquo; Manage File Share</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <a href="FilePostMain_filepoststore.do?postLevel=Y" class="btnNorm2">&laquo; Archive File Share</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
               
             </td>
           
          </tr>
        </table>
                
        <table width="100%" border="0" cellspacing="1" cellpadding="5" class="buy1_table">
         
          <tr>
            <td class="reg_head" width="10%" align="center">No</td>
            <td class="reg_head">File Share</td>
            <td class="reg_head">Folder Name</td>
            <td class="reg_head">Store Code</td>
            <td class="reg_head">Created By</td>
            <td class="reg_head">Uploaded Date</td>
          
          </tr>
          <tr class="forum_tr">
            <td colspan="4"></td>
           
          </tr>
        	<s:iterator value="fileListStore" status="post">
        		<tr>
        		<td align="center"><s:property  value="#post.index+1"/></td>
        		<td>
        			
        				<span><strong><a href="<s:property value="filePath"/>" target="_blank"><s:property value="fileName"/></a></strong></span>
        			
                </td>               	
             	 <td><s:property value="fileFolderName"/></td>                
               	<td >
               	   <s:property value="storeCode"/>
               	</td>               	
               	 <td >
               	   <s:property value="createdBy"/>
               	</td>
               	
             	 <td >
               	   <s:property value="createdDate"/>
               	</td>

        		</tr>
        	</s:iterator>
         <tr class="forum_tr">
           
           		 <td>
                 
                 </td>
                  <td colspan="5">
                          
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-home" cssStyle="width:110px;" onclick="goHome()">home</sj:a>
            
            
                 </td>
               
              
          </tr>
          
        </table>
        <p>&nbsp;</p>
        <!-- content end -->
   </s:form>
</div>
</body>
</html>

