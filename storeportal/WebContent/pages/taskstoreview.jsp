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
<script type="text/javascript">
	function goHome() {
		document.forms[0].action = 'Login_home.do';
		document.forms[0].submit();
	}
	
	function goBack()
	  {
	  window.history.back();
	  }
	
	function doUpdatePost() {
		if(autocheck(document.forms['TaskPostDetail'])){
			document.forms[0].action = 'TaskPostDetail_updateResponse.do';
			document.forms[0].submit();
		}
	}

	
	
</script>

</head>
<body >
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="TaskPostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="id" id="id"/>
	<s:hidden name="portfolioId" id="portfolioId"/>
	<s:hidden name="taskCode" id="taskCode"/>
	<s:hidden name="taskContents"/>
   <div class="container_16">
	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <s:if test="%{#session.prevUrltmw !=null}">                
                     <li><a href="${prevUrltm0}">${breadCrumbtm0}</a> &raquo;</li>    
                    <li><a href=<s:property value="%{#session.prevUrltmy}"/>>Year</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrltmm}"/>>Month</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrltmw}"/>>Week</a> &raquo;</li>        
                </s:if>         
                <s:else>
                    <li><a href="${prevUrltm11}">${breadCrumbtm0}</a> &raquo;</li>                                                
                </s:else>
                    
            </ul>
        </div>
        <div class="clearfix" /></div>
        <s:if test="%{#session.prevUrltmw !=null}">                
            <h3>View Task "<s:property value="%{#session.breadCrumbtmy}"/> <s:property value="%{#session.breadCrumbtmm}"/> <s:property value="%{#session.breadCrumbtmw}"/>"</h3>
        </s:if>
        <s:else>        
        <h3>View Task "${breadCrumbtm0}"</h3>
        </s:else>
       
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
        
          <tr>
            <td class="reg_head" width="130"></td>
            <td class="reg_head">Task Details: &nbsp;<s:property value="taskSubject" /></td>
          </tr>
           <tr>
            <td><label for="fromUser">From </label></td>
            <td>
			<s:property value="fromUser" />
         	</td>
          </tr>
          <tr>
            <td><label for="startDate">Start Date</label></td>
            <td> <s:property value="taskStartDate"></s:property></td>
          </tr>
          <tr>
            <td><label for="endDate">End Date</label></td>
            <td><s:property value="taskEndDate"></s:property></td>
           
          </tr>
          <tr>
          	<td><label for="taskName">Subject</label></td>
            <td> <s:property value="taskSubject"></s:property></td>
                  
          </tr>
       	<tr><td colspan="2"><label for="taskContens">Content</label></td></tr>
          <tr>
          <td></td> <td>
        			  <sjr:tinymce 
								id="taskContents" 
								name="taskContents" 
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
          
 
          <tr>
      	  	<td><label for="response">Response</label></td> <td><s:property value="taskRemark"></s:property>
          	</td>
          </tr>
          
          <tr>
      	  	<td><label for="response">Status</label></td> <td><s:property value="taskResponse"></s:property>
          	</td>
          </tr>
           <tr><td colspan="2">Attachment File</td></tr>
             <tr>
            
            <td colspan="2">
            <s:iterator value="uploadList">
                
                   <a href=".<s:property value="taskAttachPath"/>" target="_blank" style="width:200px"> 
            			 <s:property  value="taskAttachFilename" />
           			</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </s:iterator>
             
			</td>
          </tr>
            <tr>
            <td colspan="2">
               <s:iterator value="uploadList" >
                   
                   
                   <img src=".<s:property value="taskAttachPath"/>" width="100px" height="100px;" title="${taskAttachFilename }"></img>&nbsp;&nbsp;&nbsp;
               </s:iterator>
            </td>
          </tr>
          
          
<!-- added by Thilagar on Dec 18th 2014 -->

            <tr>
            <td colspan="2">
	    
    				<tr><td colspan="2">Attachment Proof Name</td></tr>
    				<tr>
                                    <td colspan="2">	 
                                        <s:iterator value="taskResponseList">      
                                            
                                            <a href=".<s:property value="taskProofPath"/>" target="_blank" style="width:200px"> 
                                                      <s:property  value="taskProofFilename" />
                                            </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </s:iterator>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <s:iterator value="taskResponseList">                                        
                                             <img src=".<s:property value="taskProofPath"/>" width="100px" height="100px;" title="${taskProofFilename }"></img>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </s:iterator>                                        
                                    </td>
    			 	</tr>          
            </td>
            </tr>      	  
    
<!-- end added-->
          
          <tr class="forum_tr">
            
            <td >
                        
               <sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-arrowreturnthick-1-w" cssStyle="width:110px;" onclick="goBack()">back</sj:a>
              
            
                 </td>
                  <td >
                          
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-home" cssStyle="width:110px;" onclick="goHome()">home</sj:a>
            
            
                 </td>
                 <td colspan="6">&nbsp;</td>
              
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

