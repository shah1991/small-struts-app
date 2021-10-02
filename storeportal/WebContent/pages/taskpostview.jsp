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


<script type="text/javascript">
	
        //added by hafizd on Janusry 21st 2014
        
	function doUploadApprove(){
                
                    document.forms[0].action = 'TaskPostDetail_uploadDocApprove.do';
                    document.forms[0].submit();
	}
        
        //end added
        
	function doUpdatePost() {
	
		/*var taskRemark=document.getElementById("taskRemark").value;
		if(taskRemark==""){
	   	document.getElementById("resspan").innerHTML = "Response is empty";
	  	
   		}else{
    	 document.getElementById("resspan").innerHTML ="";*/
  	   
			if (validateRadio()==true){					
			document.forms[0].action = 'TaskPostDetail_updateresponse.do';
			document.forms[0].submit();
			}
			
   		//}
		
	}

	
	
	function doSearch(){

		document.forms[0].action = 'TaskPostMain_entry.do';
		document.forms[0].submit();
		
	}


	function validateRadio(){
		
			var radio_name ='taskResponse';
			var radios = document.getElementsByName(radio_name);
			var flag=0;
			for (var i = 0; i < radios.length; i++) {
			    if (radios[i].type == 'radio' && radios[i].checked) {
			        // get value, set checked flag or do whatever you need to
			        flag=1;       
			    }
			}
			
			if(flag==0)
			{
				
				document.getElementById("statusspan").innerHTML = "Please select a Status";
				//alert('Please select status ');
				return false;
			}
			
	return true;
	}
	
</script>
<style type="text/css">
label{
font-weight: bold;
font-size: 16x;

}

.tdborder{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 100px;height: 5px;
}
.tdres{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 180px;
}

.tdresult{

width: 180px;font-size: 10px;
}
</style>
</head>
<body >
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="TaskPostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="taskId" id="taskId"/>
	<s:hidden name="portfolioId" id="portfolioId"/>
	<s:hidden name="taskCode" id="taskCode"/>

	<s:hidden name="taskAreaId" id="taskAreaId"/>
	<s:hidden name="taskStoreId" id="taskStoreId"/>
	<s:hidden name="taskType" id="taskType"/>
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
            <h3>Response activity under "<s:property value="%{#session.breadCrumbtmy}"/> <s:property value="%{#session.breadCrumbtmm}"/> <s:property value="%{#session.breadCrumbtmw}"/>"</h3>
        </s:if>
        <s:else>
        <h3>Response activity under "${breadCrumbtm0}"</h3>
        </s:else>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
        
          <tr>
            <td class="reg_head" width="130"></td>
            <td class="reg_head">Task Details : &nbsp;<s:property value="taskSubject" /></td>
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
       	<tr><td colspan="2"><label for="taskContens">Contents</label></td></tr>
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
           <!-- added by hafizd on Jan 16th 2014 -->
           <tr>
            <td><label>Attach Proof</label></td>
            <td>
            <s:file name="taskApprove" id="taskApprove"  style="width: 300px" ></s:file>
               <sj:a href="#" indicator="indicator" button="true" onclick="doUploadApprove();" buttonIcon="ui-icon-disk" cssStyle="height:27px;font-size:14px">Upload</sj:a>
            </td>
          </tr>
            <tr><td colspan="2"><table>
          		<s:if test="%{uploadRes.isEmpty()}">
    
				</s:if>
				<s:else>
					<tr><td class="tdborder"><label>Attachment Proof Name</label></td> <td class="tdremove"><label>Remove Attachment</label></td></tr>
				</s:else>
          		<s:iterator  value="uploadRes" status="ures">
 			<tr>
    
          		<td class="tdborder"><img src=".<s:property value="approvePath"></s:property>" style="width:30px;height:30px;"></img>&nbsp;<s:property value="approveName"></s:property> </td>
    	 	
 		 	    <td class="tdremove">
 		 	    <s:if test="%{actUpdate}">
 		 				<a href="#" onclick="deleteEditUpload('<s:property value="#ures.index"/>')">Remove</a>
 		 		</s:if>
 		 		<s:else>
 		 				<a href="#" onclick="deleteUpload('<s:property value="#ures.index"/>')">Remove</a>
 		 		</s:else>
 		 		</td>
 		 	
 		 	</tr>
 		 	
          </s:iterator>
      		</table></td>
 		 </tr>      	  
    
           <!-- end added-->
          
          <tr style="height:40px"><td><label for="taskRemark">Response</label></td>
          <td ><s:textfield name="taskRemark" id="taskRemark" maxlength="100" style="width:200px;border:1px solid #a1a1a1;"  />&nbsp;<label></label> &nbsp;<span id="resspan" class="errorspan"/> 
            </td>
          </tr>
        <tr style="height:60px" valign="bottom">
          	<td ><label for="taskResponse">Status</label></td>
           <td class="tdresult" >
          		<sj:radio  list="#{'Y':'Yes','N':'No','A':'N/A'}" name="taskResponse" /> &nbsp;<label>*</label> &nbsp;<span id="statusspan" class="errorspan"/></td>
                  
          </tr>
          <tr>
            <td></td>
            <td>
           					<sj:a href="#" indicator="indicator" button="true" onclick="doUpdatePost();" buttonIcon="ui-icon-circle-plus" >Response</sj:a>&nbsp;&nbsp;
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

