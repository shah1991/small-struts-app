<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Store detail</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>

<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
var pass = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[(2([0-4]\d|5[0-5])|1?\d{1,2})(\.(2([0-4]\d|5[0-5])|1?\d{1,2})){3} \])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
	function doSearch() {
	
		document.forms["StoreDetail"].action = 'StoreMain_search.do';
		document.forms["StoreDetail"].submit();
	}
	
	function createStore() {
		var validCheck=true;
		var storeCode=document.getElementById("storeCode").value;
		if(storeCode==""){
	   	document.getElementById("codespan").innerHTML = "Store Code is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("codespan").innerHTML ="";
  	   }
		
		var storeName=document.getElementById("storeName").value;
		if(storeName==""){
		
	   	document.getElementById("namespan").innerHTML = "Store Name is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
  	   }
		
		var emailAddress=document.getElementById("emailAddress").value;
		if(emailAddress==""){}else{
		
			if(pass.test(emailAddress)){
				
				document.getElementById("emailspan").innerHTML ="";	
			}else{
			
			validCheck=false;
			document.getElementById("emailspan").innerHTML ="Email ID is not valid";
			}}
		
		if(validCheck){
			document.forms["StoreDetail"].action = 'StoreDetail_create.do';
			document.forms["StoreDetail"].submit();
		}
		
	}


	function updateStore() {
		var validCheck=true;
		var storeCode=document.getElementById("storeCode").value;
		if(storeCode==""){
	   	document.getElementById("codespan").innerHTML = "Store Code is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("codespan").innerHTML ="";
  	   }
		
		var storeName=document.getElementById("storeName").value;
		if(storeName==""){
	   	document.getElementById("namespan").innerHTML = "Store Name is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
  	   }
		var emailAddress=document.getElementById("emailAddress").value;
		if(emailAddress==""){}else{
			if(pass.test(emailAddress)){
				document.getElementById("emailspan").innerHTML ="";	
			}else{
			validCheck=false;
			document.getElementById("emailspan").innerHTML ="Email ID is not valid";
			}}
		
		
		if(validCheck){
			document.forms["StoreDetail"].action = 'StoreDetail_edit.do';
			document.forms["StoreDetail"].submit();
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
	function retfalse(){
		return false;
	}
	
</script>
</head>
<body onload="document.forms[0].storeCode.focus(); document.forms[0].storeCode.select();" >


	<s:form id="StoreDetail" method="post" theme="simple" onsubmit="return retfalse();">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="left" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="left" valign="top" style="padding: 10px;"  class="boxConTd">
				<table width="100%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td align="center" class="errormsg"> 
						<s:actionerror />
						 <s:actionmessage />
						 <s:fielderror />
					</td>
				</tr>
				<tr>
					<td align="left" valign="top" style="padding-top: 5px;">
					<div class="boxConOuter">
                	<div class="boxCon">
                  	<h2>Store Details</h2>
                  	<div class="boxConIn">
						<table width="90%" border="0" cellpadding="2" cellspacing="2">
							<tr>
								<td align="right"><label for="storeCode">Store Code : </label></td>
								<td width="68%"><s:textfield name="storeCode" id="storeCode"	maxlength="20" style="width:60px"  onblur="requireValue(this)"/> &nbsp;<label>*</label> &nbsp;<span id="codespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="storeName">Store Name : </label></td>
							<td><s:textfield name="storeName" id="storeName"	maxlength="50" style="width:210px"  onblur="requireValue(this)"/> &nbsp;<label>*</label> &nbsp;<span id="namespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="areaId">Area : </label></td>
								<td>
									<s:select name="areaId" id="areaId" list="areas" listKey="areaId"  listValue="areaName" style="width:160px"  onblur="requireValue(this)" />&nbsp;<label>*</label> &nbsp;
								</td>
							</tr>								
															
							<tr>
								<td align="right"><label for="storeLocation">Store Location : </label></td>
								<td><s:textfield name="storeLocation" id="storeLocation"	maxlength="50" style="width:210px" /> 
								</td>
							</tr>
							<tr>
								<td align="right"><label for="storeAddressLine01">Store Address Line1 : </label></td>
								<td><s:textfield name="storeAddressLine01" id="storeAddressLine01"	maxlength="50" style="width:210px" />
								</td>
							</tr>
							<tr>
								<td align="right"><label for="storeAddressLine02">Store Address Line2 : </label></td>
								<td><s:textfield name="storeAddressLine02" id="storeAddressLine02"	maxlength="50" style="width:210px" /> 
								</td>
							</tr>
							<tr>
								<td align="right"><label for="postalCode">Postal Code : </label></td>
								<td><s:textfield name="postalCode" id="postalCode"	maxlength="10" style="width:80px" /> 
								</td>
							</tr>
							<tr>
								<td align="right"><label for="country">Country : </label></td>
								<td><s:select name="country" id="country" list="#{'Singapore':'Singapore'}" maxlength="50" style="width:110px" /> 
								</td>
							</tr>
							<tr>
								<td align="right"><label for="telNo01">Store Telephone No : </label></td>
								<td><s:textfield name="telNo01" id="telNo01"	maxlength="20" style="width:210px" /> 
								</td>
							</tr>
							<tr>
								<td align="right"><label for="faxNo01">Store Fax No : </label></td>
								<td><s:textfield name="faxNo01" id="faxNo01"	maxlength="20" style="width:210px" /> 
								</td>
							</tr>	
							<tr>
								<td align="right"><label for="emailAddress">E-Mail ID : </label></td>
								<td><s:textfield name="emailAddress" id="emailAddress"	maxlength="50" style="width:160px" />
									<span id="emailspan" class="errorspan"> </span> 
								</td>
							</tr>
							<tr>
								<td align="right"><label for="storeStatus">Active : </label></td>
								<td>
									<sj:radio name="storeStatus" list="#{'Y':'Active','N':'Inactive'}" value="storeStatus" /> &nbsp;
								</td>
							</tr>	
						</table>
						</div></div></div>
					</td>
				</tr>
							
				<tr>
			</tr>
			</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle">
					<s:if test="%{updateMode}">
						<s:hidden name="id"/>
						<sj:submit button="true" value="Update" onclick="updateStore();" />
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />

					</s:if>
					<s:else>
						<sj:submit button="true" value="Create" onclick="createStore();" />
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</s:else>
					
					</td>
				</tr>					
			</table>
		</s:form>

   
</body>
</html>

