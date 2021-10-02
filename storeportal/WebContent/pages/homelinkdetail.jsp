<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Area detail</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>

<sj:head jquerytheme="eggplant" />
<script type="text/javascript">

	function doSearch() {
		document.forms["HomeLinks"].action = 'HomeLinks_search.do';
		document.forms["HomeLinks"].submit();
	}
	
	function createLink() {
		
		var validCheck=true;
		var title=document.getElementById("title").value;
		if(title==""){
			   document.getElementById("titlespan").innerHTML = "Title is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("titlespan").innerHTML ="";
	    }
		
		var url=document.getElementById("url").value;
		if(url==""){
			   document.getElementById("urlspan").innerHTML = "URL is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("urlspan").innerHTML ="";
	    }
		
		var order=document.getElementById("orderNo").value;
		if(order==""){
			   document.getElementById("orderspan").innerHTML = "Order No is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("orderspan").innerHTML ="";
	    }
		
		var iconAttach=document.getElementById("iconAttach").value;
		if(iconAttach==""){
			   document.getElementById("iconAttachspan").innerHTML = "Icon Path is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("iconAttachspan").innerHTML ="";
	    }
		
			if(validCheck){
			document.forms["HomeLinks"].action = 'HomeLinks_create.do';
			document.forms["HomeLinks"].submit();
			}
		
		
	}

	function updateLink() {
		var validCheck=true;
		var title=document.getElementById("title").value;
		if(title==""){
			   document.getElementById("titlespan").innerHTML = "Title is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("titlespan").innerHTML ="";
	    }
		
		var url=document.getElementById("url").value;
		if(url==""){
			   document.getElementById("urlspan").innerHTML = "URL is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("urlspan").innerHTML ="";
	    }
		
		var order=document.getElementById("orderNo").value;
		if(order==""){
			   document.getElementById("orderspan").innerHTML = "Order No is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("orderspan").innerHTML ="";
	    }
		
	
		
			if(validCheck){
			
			document.forms["HomeLinks"].action = 'HomeLinks_detailupdate.do';
			document.forms["HomeLinks"].submit();
		}
		
	}

	function deleteArea() {
		document.forms["HomeLinks"].action = 'HomeLinks_delete.do';
		document.forms["HomeLinks"].submit();
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

	function retfalse(){
		return false;
	}
	
	function chooseOption(){
		var urlOption=document.getElementById("urlOption").value;
		
		if(urlOption==""){
			document.getElementById("urllabel").style.display="none";
			document.getElementById("urlid").style.display="none";
			document.getElementById("url").value="";
		}else{
		
		if(urlOption=='O'){
			document.getElementById("urllabel").style.display="none";
			document.getElementById("urlid").style.display="none";
			document.getElementById("url").value="http://";
		}else{
			document.getElementById("url").value="";
			document.forms["HomeLinks"].action = 'HomeLinks_selecturl.do';
			document.forms["HomeLinks"].submit();
		
			document.getElementById("urllabel").style.display="block";
			document.getElementById("urlid").style.display="block";
		}}
		
	}
	
	function chooseupdateOption(){
		var urlOption=document.getElementById("urlOption").value;
		
		if(urlOption==""){
			document.getElementById("urllabel").style.display="none";
			document.getElementById("urlid").style.display="none";
			document.getElementById("url").value="";
		}else{
		
		if(urlOption=='O'){
			document.getElementById("urllabel").style.display="none";
			document.getElementById("urlid").style.display="none";
			document.getElementById("url").value="";
		}else{
			
			document.forms["HomeLinks"].action = 'HomeLinks_selectupdateurl.do';
			document.forms["HomeLinks"].submit();
		
			document.getElementById("urllabel").style.display="block";
			document.getElementById("urlid").style.display="block";
		}}
		
	}
	
	
	function selectOption(){
		var urlOption=document.getElementById("urlOption").value;
		if(urlOption==""){
			document.getElementById("urllabel").style.display="none";
			document.getElementById("urlid").style.display="none";
			
		}else{
		
		if(urlOption=='O'){
			document.getElementById("urllabel").style.display="none";
			document.getElementById("urlid").style.display="none";
			
		}else{
			document.getElementById("urllabel").style.display="block";
			document.getElementById("urlid").style.display="block";
		}
	}
}
	
	
	function chooseURL(){
		var urlOption=document.getElementById("urlOption").value;
		var id=document.getElementById("id").value;
		if(urlOption=='C'){
			document.getElementById("url").value="ChklstPostMain_topic.do?chklstGroupId="+id;
		}else if(urlOption=='T'){
			document.getElementById("url").value="TaskPostMain_entry.do?portfolioId="+id;
		}else if(urlOption=='F'){
			document.getElementById("url").value="PostMain_topic.do?forumId="+id;
		}else if(urlOption=='S'){
			document.getElementById("url").value="FilePostMain_entry.do?groupId="+id;
		}else{
			document.getElementById("url").value="";
		}
		
		
	}
	
	
	
</script>
</head>
<body  onload="selectOption();">
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
  	<div class="headingBg"><div class="heading" align="center"> <h1> Home Link</h1> </div> </div>
  
  	<s:form id="HomeLinks" method="post" theme="simple" enctype="multipart/form-data" onsubmit="return retfalse()">
	
		<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="center" valign="top" style="padding: 10px;"  class="boxConTd">
				<table border="0" cellspacing="2" cellpadding="2" width="70%" align="center">
					<tr>
			                <td align="center"colspan="2" class="errormsg">
				                	<s:actionerror />
									 <s:actionmessage />
							    </td>
			         </tr>
				<tr>
					<td align="left" valign="top" style="padding-top: 5px;">
					<div class="boxConOuter">
                	<div class="boxCon">
                  	<h2>Link Details</h2>
                  	<div class="boxConIn">
						<table border="0" cellpadding="2" cellspacing="2" width="100%" align="left">
							<tr>
								<td align="right"><label for="title">Title : </label></td>
								<td width="75%"><s:textfield name="title" id="title"	style="width:210px"  /> &nbsp;* &nbsp;
								<span id="titlespan" class="errorspan"> </span>
								</td>
							</tr>
							
							
							<tr>
								<td align="right"><label for="url">URL Option: </label></td>
								<td>
								<s:if test="%{updateMode}">
									<s:select name="urlOption" id="urlOption"  headerKey="" headerValue="Select URL Option"  list="#{'C':'CheckList','F':'Forum','S':'File Share','T':'Task','O':'Others'}" style="width:160px"  onchange="chooseupdateOption()"/>&nbsp;<label>*</label> &nbsp;
								</s:if>
								<s:else>
								   <s:select name="urlOption" id="urlOption"  headerKey="" headerValue="Select URL Option"  list="#{'C':'CheckList','F':'Forum','S':'File Share','T':'Task','O':'Others'}" style="width:160px"  onchange="chooseOption()"/>&nbsp;<label>*</label> &nbsp;
								</s:else>
								<span id="urloptionspan" class="errorspan"> </span>
								</td>
							</tr>
					
						
								
							<tr>
								
								<td align="right"><div id="urllabel" style="display: none"><label for="urlId">URL ID : </label>	</div></td>
								<td>
									<div id="urlid" style="display: none"><s:select name="id" id="id" list="listUrl" listKey="id"  listValue="mstName" headerKey="" headerValue="Select URL ID" style="width:160px"    onChange="chooseURL()"/>&nbsp;<label>*</label> 
									<span id="urlidspan" class="errorspan"> </span>&nbsp; &nbsp;</div>
								</td>
							</tr>	
								
							
							
							<tr>
								<td align="right"><label for="url">URL: </label></td>
								<td><s:textfield name="url" id="url"	 style="width:320px"/> &nbsp;* &nbsp;
								<span id="urlspan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="url">Order No: </label></td>
								<td><s:textfield name="orderNo" id="orderNo"	 style="width:210px"  /> &nbsp;* &nbsp;
								<span id="orderspan" class="errorspan"> </span>
								</td>
							</tr>	
							<tr>
								<td align="right"><label for="iconPathFile">Icon Path : </label></td>
								<td> 
									<s:if test="%{updateMode}">
									  <a href=".<s:property value="iconPath"/>" target="_blank"> <s:property value="iconPath"/></a>
									  <img src=".<s:property value="iconPath"/>"  width="30px"></img>
									 <br/> <br/>
									 <s:file name="iconAttach" id="iconAttach"  style="width: 300px" ></s:file>
									</s:if>
								<s:else>
								 <s:file name="iconAttach" id="iconAttach"  style="width: 300px" ></s:file>&nbsp;* &nbsp;
								 <span id="iconAttachspan" class="errorspan"> </span>
								</s:else>
								 
								</td>
							</tr>								
							<tr>
								<td align="right"><label for="active">Active : </label></td>
								<td>
									<sj:radio name="active" list="#{'Y':'Active','N':'Inactive'}" value="active" />
								</td>
							</tr>	
						</table>
						</div></div></div></td></tr>
				<tr>
					<td align="center" valign="middle"  style="padding-top: 5px;">
					<s:if test="%{updateMode}">
						<s:hidden name="linkId"/>
						<sj:submit button="true" value="Update" onclick="updateLink();" />
					
					</s:if>
					<s:else>
						<sj:submit button="true" value="Create" onclick="createLink();" />
					</s:else>
					<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</td>
				</tr>	
			</table>
			</td>
			</tr>
			</table>
			</s:form>
			</div>
			</div>
			
		
</body>
</html>

