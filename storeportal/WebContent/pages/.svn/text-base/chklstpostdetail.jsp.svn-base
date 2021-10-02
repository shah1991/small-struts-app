<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="/struts-jquery-richtext-tags" prefix="sjr"%>

<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
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


	function deleteeditTemp(id){
		
		document.forms[0].action = 'ChklstPostDetail_deleteedittemp.do?id='+id;
		document.forms[0].submit();
		
	}
	function doSearch() {
		document.forms[0].action = 'ChklstPostMain_entry.do';
		document.forms[0].submit();
	}
	
	
	function doCreatePost() {
		 var validCheck=true;

			var start=document.getElementById("chklstStartDate").value;
			if(start==""){
			   document.getElementById("sdatespan").innerHTML = "Select a Start Date";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("sdatespan").innerHTML ="";
		    }
			
			var end=document.getElementById("chklstEndDate").value;
			if(end==""){
			   document.getElementById("edatespan").innerHTML = "Select a End Date";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("edatespan").innerHTML ="";
		    }
			
			var sub=document.getElementById("chklstSubject").value;
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
			document.forms[0].action = 'ChklstPostDetail_hqadd.do';
			document.forms[0].submit();
		}
		
	}

	function doUpdatePost() {
		 var validCheck=true;
			var start=document.getElementById("chklstStartDate").value;
			if(start==""){
			   document.getElementById("sdatespan").innerHTML = "Select a Start Date";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("sdatespan").innerHTML ="";
		    }
			
			var end=document.getElementById("chklstEndDate").value;
			if(end==""){
			   document.getElementById("edatespan").innerHTML = "Select a End Date";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("edatespan").innerHTML ="";
		    }
			
			var sub=document.getElementById("chklstSubject").value;
			if(sub==""){
			   document.getElementById("subspan").innerHTML = "Subject is empty";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("subspan").innerHTML ="";
		    }
		if(validCheck){
			document.forms[0].action = 'ChklstPostDetail_detailupdate.do';
			document.forms[0].submit();
		}
	}

	function doDeletePost() {
		document.forms[0].action = 'ChklstPostDetail_delete.do';
		document.forms[0].submit();
	}
	
	function addHeader(){
		var head=document.forms['ChklstPostDetail'].headercol;
		var flag=false;
		for (i=0; i<head.length; i++){
			if(head[i].value==""){
				
			}else{
				flag=true;
			}
		}
	if(flag){
		document.forms[0].action = 'ChklstPostDetail_detailtemp.do';
		document.forms[0].submit();
	}
	}
	
	function editHeader(){
		var head=document.forms['ChklstPostDetail'].headercol;
		var flag=false;
		for (i=0; i<head.length; i++){
			if(head[i].value==""){
				
			}else{
				flag=true;
			}
		}
	if(flag){
		document.forms[0].action = 'ChklstPostDetail_edittemp.do';
		document.forms[0].submit();
	}
	}
	
	
	function uploadEditExcel(){
		document.forms[0].action = 'ChklstPostDetail_uploadeditexcel.do';
		document.forms[0].submit();
	}
	function uploadExcel(){
		document.forms[0].action = 'ChklstPostDetail_uploadexcel.do';
		document.forms[0].submit();
	}
	
	function deleteTemp(id){
		
		document.forms[0].action = 'ChklstPostDetail_deletetemp.do?id='+id;
		document.forms[0].submit();
		
	}

	
	function ChangeFlag(){
	var val=document.getElementsByName("flag");
	
		if(val[0].checked){
		document.getElementById("areaDiv").style.display="block";
		document.getElementById("storeDiv").style.display="none";
			
		}else{
		document.getElementById("areaDiv").style.display="none";
		document.getElementById("storeDiv").style.display="block";
		}
		
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
width: 100px;height: 5px;
}
</style>
</head>
<body onload="ChangeFlag()">
  <br style="clear:both"/>
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="ChklstPostDetail" id="ChklstPostDetail"  name="ChklstPostDetail" method="post" enctype="multipart/form-data" theme="simple">
    <s:hidden name="chklstId" id="chklstId"/>
	<s:hidden name="tempHeader" id="tempHeader"/>
	<s:hidden name="recType" id="recType"/>
   <div class="container_16">
	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <li><?php echo $activity['activity']; ?></li>
                 <li><a href="ChklstPostMain_topic.do?chklstGroupId=${chklstGroupId }"> ${topicName}</a> &raquo;</li>
                 <li><a href="ChklstPostMain_entry.do?topicId=${topicId }">Check List</a> &raquo;</li>
            </ul>
        </div>
        <div class="clearfix" /></div>
        
		<h2>CheckList Posting</h2>

        <h3>Add activity under <?php echo $subactivity['subactivity']; ?></h3>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
        
         
         <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">Add  recipients</td>
          </tr>
         <tr><td><label>Choose Area / Store</label></td><td>
         <s:if test="%{flag==\"A\"}">
         	<input type="radio" name="flag" id="flag" value="A" checked="checked" onchange="ChangeFlag(this)"/>Area 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="flag" id="flag" value="S" onchange="ChangeFlag(this)"/>Store
          </s:if>
         <s:elseif test="%{flag==\"S\"}">
         <input type="radio" name="flag" id="flag" value="A"  onchange="ChangeFlag(this)"/>Area 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="flag" id="flag" value="S" checked="checked" onchange="ChangeFlag(this)"/>Store
         </s:elseif>
         <s:else>
        
         	<input type="radio" name="flag" id="flag" value="A" checked="checked" onchange="ChangeFlag(this)"/>Area 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="flag" id="flag" value="S" onchange="ChangeFlag(this)"/>Store
         
         </s:else>
          </td>
         </tr>
         <tr>
         <td colspan="2" style="width:100%;border:0;">
         <table >
         <tr>
         <td style="width: 124px;border:0;">
	   	   
         </td>
         <td style="width: 250px;border:0;">
          <div id="areaDiv" >
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
        	</div>
    		<div id="storeDiv" style="display:none">
    		 <ul class="tree">
            <li>
                <input type="checkbox"/> <label> Stores </label>
                <ul>
						<s:iterator value="areaList">
                    	<li>
                        <s:checkbox name="aslist" id="aslist  " fieldValue="%{areaId}" value="false"/>
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
        </div>
         </td>
         </tr>
         </table>
         </td>
         </tr>   
         
        
     
          <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">New CheckList</td>
          </tr>
          <tr>
            <td><label for="chklstStartDate">Start Date</label></td>
            <td><sj:datepicker name="chklstStartDate" id="chklstStartDate" cssClass="txtBox" displayFormat="dd-M-yy" buttonImageOnly="true" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
                <span id="sdatespan" class="errorspan"> </span>
            </td>
          </tr>
          <tr>
            <td><label for="chklstEndDate">End Date</label></td>
            <td><sj:datepicker name="chklstEndDate" id="chklstEndDate" cssClass="txtBox" displayFormat="dd-M-yy" buttonImageOnly="true" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
                <span id="edatespan" class="errorspan"> </span>
            </td>
           
          </tr>
          <tr>
          	<td><label for="chklstSubject">Subject</label></td>
            <td><s:textfield name="chklstSubject" id="chklstSubject" maxlength="100" style="width:400px" onblur="requireValue(this)" />
                &nbsp;* &nbsp;   <span id="subspan" class="errorspan"> </span>
            </td>
          </tr>
       
          <tr>
            <td colspan="2"  style="border:0 ;"><sjr:tinymce 
								id="chklstContents" 
								name="chklstContents" 
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
         
 		<tr style="height: 10px;" valign="top"><td colspan="2" style="border:0 ; height: 5px;"> <table  >
        <tr><s:iterator status="header" value="header"><td class="tdborder"><label><s:property/></label>&nbsp;<br></br><s:textfield name="headercol"  style="width: 100px"></s:textfield></td> </s:iterator> 
                 <td class="tdborder"><br></br><sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-circle-plus" onclick="editHeader()"  cssStyle="height:25px;font-size:11px">Add</sj:a></td>  </tr>        
				
          		
          <s:iterator status="final" value="finalList">
 			<tr  style="height: 5px;" valign="top" >
          	<s:iterator value="tempList" >
          		<td class="tdborder"><s:property></s:property></td>
    	 	</s:iterator>
 		 	<td class="tdborder"><a href="#" onclick="deleteeditTemp('<s:property value="#final.index"/>')">Remove</a></td>
 		 	</tr>
 		 	
          </s:iterator>
      		</table></td>
 		 </tr>
 		 <tr>
            <td><label>Excel Upload</label></td>
            <td valign="top">
            <s:file name="chklstAttach" id="chklstAttach"  style="width: 300px" ></s:file> <sj:a href="#" indicator="indicator" button="true" onclick="uploadEditExcel();" buttonIcon="ui-icon-disk" >Upload</sj:a>
			</td>
          </tr>
      	</s:if>   
          <s:else>
     
        
     
        <tr style="height: 10px;" valign="top"><td colspan="2" style="border:0 ; height: 5px;"> <table  >
        <tr><s:iterator status="header" value="header"><td class="tdborder"><label><s:property/></label>&nbsp;<br></br><s:textfield name="headercol"  style="width: 100px"></s:textfield></td> </s:iterator> 
                 <td class="tdborder"><br></br><sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-circle-plus" onclick="addHeader()"  cssStyle="height:25px;font-size:11px">Add</sj:a></td>  </tr>        
				
          		
          <s:iterator status="final" value="finalList">
 			<tr  style="height: 5px;" valign="top" >
          	<s:iterator value="tempList" >
          		<td class="tdborder"><s:property></s:property></td>
    	 	</s:iterator>
 		 	<td class="tdborder"><a href="#" onclick="deleteTemp('<s:property value="#final.index"/>')">Remove</a></td>
 		 	</tr>
 		 	
          </s:iterator>
      		</table></td>
 		 </tr>
      		
      		
      	
      	<tr>
             <td><label>Excel Upload</label></td>
            <td valign="top">
            <s:file name="chklstAttach" id="chklstAttach"  style="width: 300px" ></s:file> <sj:a href="#" indicator="indicator" button="true" onclick="uploadExcel();" buttonIcon="ui-icon-disk" cssStyle="height:30px;">Upload</sj:a>
			</td>
          </tr>
           </s:else>
          
         
          
 			   <tr style="height:40px;" valign="bottom"><td style="border:0;">&nbsp;</td></tr>
 		
           <tr style="height:40px;" valign="bottom">
            <td>&nbsp;</td>
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

