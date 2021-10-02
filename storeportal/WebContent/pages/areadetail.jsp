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
		document.forms["AreaDetail"].action = 'AreaMain_search.do';
		document.forms["AreaDetail"].submit();
	}
	
	function createArea() {
		var validCheck=true;
		var areaCode=document.getElementById("areaCode").value;
		if(areaCode==""){
	   	document.getElementById("codespan").innerHTML = "Store Code is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("codespan").innerHTML ="";
  	   }
		
		var areaName=document.getElementById("areaName").value;
		if(areaName==""){
	   	document.getElementById("namespan").innerHTML = "Store Name is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
  	   }
				
		if(validCheck){
			document.forms["AreaDetail"].action = 'AreaDetail_create.do';
			document.forms["AreaDetail"].submit();
		}
		
	}

	function updateArea() {
		var validCheck=true;
		var areaCode=document.getElementById("areaCode").value;
		if(areaCode==""){
	   	document.getElementById("codespan").innerHTML = "Store Code is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("codespan").innerHTML ="";
  	   }
		
		var areaName=document.getElementById("areaName").value;
		if(areaName==""){
	   	document.getElementById("namespan").innerHTML = "Store Name is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
  	   }
			
		if(validCheck){
			document.forms["AreaDetail"].action = 'AreaDetail_edit.do';
			document.forms["AreaDetail"].submit();
		}
		
	}

	function deleteArea() {
		document.forms["AreaDetail"].action = 'AreaDetail_delete.do';
		document.forms["AreaDetail"].submit();
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
<body onload="document.forms[0].areaCode.focus(); document.forms[0].areaCode.select();" >

  
  	<s:form id="AreaDetail" method="post" theme="simple" onsubmit="return retfalse();">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="left" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="right" valign="top" style="padding: 10px;"  class="boxConTd">
				<table border="0" cellspacing="2" cellpadding="2" width="100%">
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
                  	<h2>Area Details</h2>
                  	<div class="boxConIn">
						<table border="0" cellpadding="2" cellspacing="2" width="100%">
							<tr>
								<td align="right"><label for="areaCode">Area Code : </label></td>
								<td width="75%"><s:textfield name="areaCode" id="areaCode"	maxlength="20" style="width:60px"  onblur="requireValue(this)"/> &nbsp;<label>*</label> &nbsp;
								<span id="codespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="areaName">Area Name : </label></td>
								<td><s:textfield name="areaName" id="areaName"	maxlength="50" style="width:210px"  onblur="requireValue(this)"/> &nbsp;<label>*</label> &nbsp;
								<span id="namespan" class="errorspan"> </span>
								</td>
							</tr>
														
							<tr>
								<td align="right"><label for="active">Active : </label></td>
								<td>
									<sj:radio name="active" list="#{'Y':'Active','N':'Inactive'}" value="active" />
								</td>
							</tr>	
						</table>
						</div></div></div>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle"  style="padding-top: 5px;">
					<s:if test="%{updateMode}">
						<s:hidden name="id"/>
						<sj:submit button="true" value="Update" onclick="updateArea();" />
					
					</s:if>
					<s:else>
						<sj:submit button="true" value="Create" onclick="createArea();" />
					</s:else>
					<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</td>
				</tr>	
			</table>
			</td>
			</tr>
			</table>
			</s:form>
			
			
		
</body>
</html>

