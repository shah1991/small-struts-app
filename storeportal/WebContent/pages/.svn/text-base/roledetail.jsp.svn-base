<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Role Detail</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>        
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">

	function doSearch() {
		document.forms[0].action = 'RoleMain_search.do';
		document.forms[0].submit();
	}

	function deleteRole() {
		if(confirm('Are you sure?')){
			document.forms[0].action = 'RoleMain_delete.do';
			document.forms[0].submit();
		}else{
			return false;
		}
	}
	
	function createRole() {
		 var validCheck=true;
			var start=document.getElementById("roleCd").value;
			if(start==""){
			   document.getElementById("codespan").innerHTML = "Code is empty";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("codespan").innerHTML ="";
		    }
			
			var end=document.getElementById("roleName").value;
			if(end==""){
			   document.getElementById("namespan").innerHTML = "Name is empty";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("namespan").innerHTML ="";
		    }
			if(validCheck){
			document.forms[0].action = 'RoleDetail_create.do';
			document.forms[0].submit();
		}
	}

	function updateRole() {
		 var validCheck=true;
			var start=document.getElementById("roleCd").value;
			if(start==""){
			   document.getElementById("codepan").innerHTML = "Code is empty";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("codespan").innerHTML ="";
		    }
			
			var end=document.getElementById("roleName").value;
			if(end==""){
			   document.getElementById("namespan").innerHTML = "Name is empty";
			   validCheck=false;
		        
		    }else{
		    	 document.getElementById("namespan").innerHTML ="";
		    }
			if(validCheck){
			document.forms[0].action = 'RoleDetail_update.do';
			document.forms[0].submit();
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
<body onload="document.forms[0].roleCd.focus(); document.forms[0].roleCd.select();" >
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
  	<div class="headingBg"><div class="heading" align="center"> <h1>Role Details</h1> </div> </div>
	<s:form id="RoleDetail" method="post"  theme="simple" onsubmit="return retfalse();">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="center" valign="top" style="padding: 10px;"  class="boxConTd">
				<table width="50%" border="0" cellspacing="2" cellpadding="2">
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
                  	<h2>Role Details</h2>
                  	<div class="boxConIn">
						<table width="100%" border="0" cellpadding="2" cellspacing="2">
							<tr>
								<td align="right"><label for="roleCd">Role Code : </label></td>
								<td width="75%"><s:textfield name="roleCd" id="roleCd"	maxlength="20" style="width:120px"  onblur="requireValue(this)" /> &nbsp;* &nbsp;
								 <span id="codespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right" nowrap="nowrap"><label for="roleName">Role Name : </label></td>
								<td nowrap="nowrap"><s:textfield name="roleName" id="roleName"	maxlength="50" style="width:210px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
									 <span id="namespan" class="errorspan"> </span>
								</td>
							</tr>
						</table>
						</div></div></div>
					</td>
				</tr>
							
				<tr>
			</tr>
			<tr>
					<s:hidden name="id"/>
					<s:hidden name="actionName"/>
					<td align="center" valign="middle" colspan="2" style="padding-top: 5px;">
					<s:if test="%{updateMode}">
						<sj:submit button="true" value="Update" onclick="updateRole();" />
					</s:if>
					<s:else>
						<sj:submit button="true" value="Create" onclick="createRole();" />
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
    <div class="footerBg">
      <div class="footer">
        <div class="footerLft">2012 &copy; Dairy Farm. All Rights Reserved.</div>
      </div>
    </div>
   </div>
</body>
</html>

