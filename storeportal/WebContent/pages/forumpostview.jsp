<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="/struts-jquery-richtext-tags" prefix="sjr"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Post Detail</title>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>	
<link type="text/css" rel="stylesheet" href="css/checktree.css" />
<script type="text/javascript" src="js/checktree.js"></script>

<script type="text/javascript">
	            var $checktree;
	            $(function(){
	                $checktree = $("ul.tree").checkTree();
	            });
</script>	
<script type="text/javascript">
	
	function doSearch() {
		document.forms[0].action = 'PostMain_entry.do';
		document.forms[0].submit();
	}
	
	
	function doCreatePost() {
		if(autocheck(document.forms['PostDetail'])){
			document.forms[0].action = 'PostDetail_detailadd.do';
			document.forms[0].submit();
		}
	}

	function doUpdatePost() {
		if(autocheck(document.forms['PostDetail'])){
			document.forms[0].action = 'PostDetail_detailupdate.do';
			document.forms[0].submit();
		}
	}

	function doDeletePost() {
		document.forms[0].action = 'PostDetail_delete.do';
		document.forms[0].submit();
	}
</script>

</head>
<body>
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="PostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="id" id="id"/>
	<s:hidden name="topicId" id="topicId"/> 
        <s:hidden name="breadCrumbt1" id="breadCrumbt1"/>     
   
   <div class="container_16">
	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <li><a href="${prevUrl0}">${breadCrumbt0}</a> &raquo;</li>    
                <s:if test="%{#session.prevUrlw !=null}">                
                    <li><a href=<s:property value="%{#session.prevUrl1}"/>>${breadCrumbt1}</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrly}"/>>Year</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrlm}"/>>Month</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrlw}"/>>Week</a> &raquo;</li>        
                </s:if>         
                <s:else>
                    <li><a href="${prevUrl11}">${breadCrumbt1}</a> &raquo;</li>                            
                </s:else>
            </ul>
        </div>
        <div class="clearfix" /></div>   
        <s:if test="%{#session.prevUrlw !=null}">                
            <h3>View activity under "<s:property value="%{#session.breadCrumby}"/> <s:property value="%{#session.breadCrumbm}"/> <s:property value="%{#session.breadCrumbw}"/>"</h3>
        </s:if>
        <s:else>
            <h3>View activity under "${breadCrumbt1}"</h3>
        </s:else>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
          <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">View activity</td>
          </tr>
          <tr>
            <td><label for="fromUser">From </label></td>
            <td >
			<s:property value="fromUser" />
         	</td>
          </tr>
    <!-- -->     
         <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">Recipients</td>
          </tr>
    
    <tr>
         <td colspan="2" width="100%">
         <table >
         <tr>
         <td style="width: 250px;">
     
        
       
    	 <ul class="tree">
            <li>
                <input type="checkbox"/> <label> Areas </label>
                <ul>
                
                        <s:iterator value="areaList">
                    	<li>
                    	<s:if test="%{areaId in alist}">
                            <s:checkbox name="alist" id="alist" fieldValue="%{areaId}" value="true" disabled="true"/>
                        </s:if>
                        <s:else>
                            <s:checkbox name="alist" id="alist" fieldValue="%{areaId}" value="false" disabled="true"/>
                        </s:else>
                        <label>${areaName}</label>
                        </li>                			  
                        </s:iterator>
                </ul>
            </li>
        </ul>
         </td>
         <td style="width: 250px;">
    	 <ul class="tree">
            <li>
                <input type="checkbox"/> <label> Stores </label>
                <ul>
                        <s:iterator value="areaList">
                    	<li>
                        <input type="checkbox" />
                        <label>${areaName}</label>
                        <ul>
                        <s:iterator value="tblStoreList">
                                <li>
                                <s:if test="%{storeId in slist}">
                                    <s:checkbox name="slist" id="slist" fieldValue="%{storeId}" value="true" disabled="true"/>
                                </s:if>
                                <s:else>
                                    <s:checkbox name="slist" id="slist" fieldValue="%{storeId}" value="false" disabled="true"/>
                                </s:else>
                                <label>${storeCode} - ${storeName}</label>

                                </li>
                        </s:iterator>
                        </ul>
			</li>                			  
                        </s:iterator>
                </ul>
            </li>
        </ul>
         </td>
         </tr>
         </table>
         </td>
         </tr>                  
           <!-- -->     
          <tr>
            <td><label for="postSubject">Subject </label></td>
            <td>
			<s:property value="postSubject" />
         	</td>
          </tr>
          <tr>
            <td><label for="postContents">Content</label></td>
            <td></td>
          </tr>
          <tr>
            <td colspan="2">
              <sjr:tinymce 
								id="postContents" 
								name="postContents" 
								rows="5" 
								cols="80" 
								width="790"
								readonly="true"
								toolbarButtonsRow1=" "
								toolbarButtonsRow4=" "
								toolbarButtonsRow3=" "
								toolbarButtonsRow2=" "
								toolbarAlign="left"	
								onblur="requireValue(this)"
								/>
        			  
            
         
            </td>
          </tr>
                     <tr><td colspan="2">Attachment file</td></tr>
             <tr>
            
            <td colspan="2">
            <s:iterator value="uploadList">
                
                   <a href=".<s:property value="postAttachPath"/>" target="_blank" style="width:200px"> 
            			 <s:property  value="postAttachFilename" />
           			</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </s:iterator>
             
			</td>
          </tr>
          <tr>
            <td colspan="2">
               <s:iterator value="uploadList" >
                   
                   
                   <img src=".<s:property value="postAttachPath"/>" width="100px" height="100px;" title="${postAttachFilename }"></img>&nbsp;&nbsp;&nbsp;
               </s:iterator>
            </td>
          </tr>
          <tr>
            <td align="center" colspan="2">
          		<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
			</td>
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

