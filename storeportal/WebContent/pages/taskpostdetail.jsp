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
		document.forms[0].action = 'TaskPostMain_entry.do';
		document.forms[0].submit();
	}
	 
	
	function doCreatePost() {
	 var validCheck=true;
		
		var start=document.getElementById("taskStartDate").value;
		if(start==""){
		   document.getElementById("sdatespan").innerHTML = "Select a Start Date";
		   validCheck=false;
	        
	    }else{
	    	 document.getElementById("sdatespan").innerHTML ="";
	    }
		
		var end=document.getElementById("taskEndDate").value;
		if(end==""){
		   document.getElementById("edatespan").innerHTML = "Select a End Date";
		   validCheck=false;
	        
	    }else{
	    	 document.getElementById("edatespan").innerHTML ="";
	    }
		
		var sub=document.getElementById("taskSubject").value;
		if(sub==""){
		   document.getElementById("subspan").innerHTML = "Subject is empty";
		   validCheck=false;
	        
	    }else{
	    	 document.getElementById("subspan").innerHTML ="";
	    }
		
		
		if(validateAreaStore()==false)
		{
			validCheck=false;
		}
		
		
		if(validCheck){
			document.forms[0].action = 'TaskPostDetail_detailadd.do';
			document.forms[0].submit();
		}
		
	}

	function doUpdatePost() {
		 var validCheck=true;

			var start=document.getElementById("taskStartDate").value;
			if(start==""){
			   document.getElementById("sdatespan").innerHTML = "Select a Start Date";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("sdatespan").innerHTML ="";
		    }
			
			var end=document.getElementById("taskEndDate").value;
			if(end==""){
			   document.getElementById("edatespan").innerHTML = "Select a End Date";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("edatespan").innerHTML ="";
		    }
			
			var sub=document.getElementById("taskSubject").value;
			if(sub==""){
			   document.getElementById("subspan").innerHTML = "Subject is empty";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("subspan").innerHTML ="";
		    }
			
		
			
			if(validCheck){
			document.forms[0].action = 'TaskPostDetail_detailupdate.do';
			document.forms[0].submit();
		}
	}

	function doDeletePost() {
		document.forms[0].action = 'TaskPostDetail_delete.do';
		document.forms[0].submit();
	}
	
	function doEditUpload(){
		document.forms[0].action = 'TaskPostDetail_uploadEditDoc.do';
		document.forms[0].submit();
		
		
	}
	
	function doUpload(){
                
                //added by hafizd on tuesday december 17th 2013
               
//		var alist=document.getElementById("alist").checked;
                
//                var slist=document.getElementById("slist").checked;
                
//               //alert(alist);       
//                if(alist=="" && slist==""){
//			   alert("Please Select Add Recipients");
//			   //validCheck=false;
		        
//		}else{
//                    document.forms[0].action = 'TaskPostDetail_uploadDoc.do';
//                    document.forms[0].submit();
//		}	



		if(validateAreaStore()==false)
		{
			validCheck=false;
//			alert("Please Select Recipients");
		}
		else
		{
            document.forms[0].action = 'TaskPostDetail_uploadDoc.do';
            document.forms[0].submit();
		}
        		
		
	}
	
	
	function deleteUpload(id){
		document.forms[0].action = 'TaskPostDetail_deleteDoc.do?delId='+id;
		document.forms[0].submit();
		
	}
	
	function deleteEditUpload(id){
		document.forms[0].action = 'TaskPostDetail_deleteEditDoc.do?delId='+id;
		document.forms[0].submit();
		
	}

	function validateAreaStore(){
		var area_name ='alist';
		var areas = document.getElementsByName(area_name);

		var store_name ='slist';
		var stores = document.getElementsByName(store_name);
		
		
		var flag=0;
		for (var i = 0; i < areas.length; i++) {
		    if (areas[i].type == 'checkbox' && areas[i].checked) {
		        flag=1;       
		    }
		}

		for (var j = 0; j < stores.length; j++) {
		    if (stores[j].type == 'checkbox' && stores[j].checked) {
		        flag=1;       
		    }
		}
		
		if(flag==0)
		{
			//document.getElementById("statusspan").innerHTML = "Please select a Status";
			alert('Please select Area(s) or Store(s) ');
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
width: 300px;height: 5px;
}
.tdremove{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 150px;height: 5px;
}
</style>
</head>
<body>
  <br style="clear:both"/>
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="TaskPostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="taskId" id="taskId"/>
	<s:hidden name="portfolioId" id="portfolioId"/>

	<s:hidden name="recType" id="recType"/>
	   
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
        
<%--	        <h2>${prevUrltm11} ${breadCrumbtm0}</h2>		--%>
        <s:if test="%{#session.prevUrltmw !=null}">                
            <h3>Add activity under "<s:property value="%{#session.breadCrumbtmy}"/> <s:property value="%{#session.breadCrumbtmm}"/> <s:property value="%{#session.breadCrumbtmw}"/>"</h3>
        </s:if>
        <s:else>
        <h3>Add activity under "${breadCrumbtm0}"</h3>
        </s:else>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
         
          <s:else>
         <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">Add recipients</td>
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
						<s:checkbox name="alist" id="alist" fieldValue="%{areaId}" value="true"/>
						</s:if>
						<s:else>
						<s:checkbox name="alist" id="alist" fieldValue="%{areaId}" value="false"/>
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
						<s:checkbox name="slist" id="slist" fieldValue="%{storeId}" value="true"/>
						</s:if>
						<s:else>
						<s:checkbox name="slist" id="slist" fieldValue="%{storeId}" value="false"/>
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

        
         </s:else>
          <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">New Task</td>
          </tr>
          <tr>
            <td><label for="taskStartDate">Start Date</label></td>
            <td><sj:datepicker name="taskStartDate" id="taskStartDate" cssClass="txtBox" displayFormat="dd-M-yy" buttonImageOnly="true" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
            	<span id="sdatespan" class="errorspan"> </span>
            </td>
          </tr>
          <tr>
            <td><label for="taskEndDate">End Date</label></td>
            <td><sj:datepicker name="taskEndDate" id="taskEndDate" cssClass="txtBox" displayFormat="dd-M-yy" buttonImageOnly="true" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
                <span id="edatespan" class="errorspan"> </span>
            </td>
           
          </tr>
          <tr>
          	<td><label for="taskSubject">Subject</label></td>
            <td><s:textfield name="taskSubject" id="taskSubject" maxlength="100" style="width:500px" onblur="requireValue(this)" />&nbsp;* &nbsp;
            	<span id="subspan" class="errorspan"> </span>
            </td>
          </tr>
      	 <tr>
          	<td colspan="2"><label for="taskContents">Content </label></td>
           
          </tr>
          <tr>
            <td colspan="2"><sjr:tinymce 
								id="taskContents" 
								name="taskContents" 
								rows="10" 
								cols="80" 
								width="700"
								editorTheme="advanced"
								toolbarLocation="top"
								toolbarButtonsRow1="cut,copy,paste,pastetext,pasteword,|,bold,italic,underline,strikethrough,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,justifyleft,justifycenter,justifyright,justifyfull,|fontselect,fontsizeselect,|,forecolor,backcolor"
								toolbarButtonsRow2=" "
								toolbarAlign="left"	
								onblur="requireValue(this)"
								/>
				
            </td>
          </tr>
          <s:if test="%{actUpdate}">
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
          
          </s:if>
     
          <tr>
            <td><label>Attachment</label></td>
            <td>
            <s:file name="taskAttach" id="taskAttach"  style="width: 300px" ></s:file>
             <s:if test="%{actUpdate}">
            	<sj:a href="#" indicator="indicator" button="true" onclick="doEditUpload();" buttonIcon="ui-icon-disk" cssStyle="height:27px;font-size:14px">Upload</sj:a>
          	</s:if>
            <s:else>
               <sj:a href="#" indicator="indicator" button="true" onclick="doUpload();" buttonIcon="ui-icon-disk" cssStyle="height:27px;font-size:14px">Upload</sj:a>
            </s:else>
			</td>
          </tr>
   			
          <tr><td colspan="2"><table>
          		<s:if test="%{uploadRes.isEmpty()}">
    
				</s:if>
				<s:else>
					<tr><td class="tdborder"><label>Attachment Name</label></td> <td class="tdremove"><label>Remove Attachment</label></td></tr>
				</s:else>
          		<s:iterator  value="uploadRes" status="ures">
 			<tr>
    
          		<td class="tdborder"><img src=".<s:property value="attachPath"></s:property>" style="width:30px;height:30px;"></img>&nbsp;<s:property value="attachName"></s:property> </td>
    	 	
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
    
          <tr>
            <td></td>
            <td>
            
            <s:if test="%{actUpdate}">
									
									<sj:a href="#" indicator="indicator" button="true" onclick="doUpdatePost();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
									<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
							    </s:if>
								<s:else>
									<sj:a href="#" indicator="indicator" button="true" onclick="doCreatePost();" buttonIcon="ui-icon-circle-plus" >Submit</sj:a>&nbsp;&nbsp;
									<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
								</s:else>
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

