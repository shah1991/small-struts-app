<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Change Password</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>        
<sj:head jquerytheme="south-street" />
 <link rel="shortcut icon" href="styles/images/favico.png" type="image/x-icon"/>
 <link rel="stylesheet" href="styles/style.css" />
 <link rel="stylesheet" href="styles/chat.css" />
<script type="text/javascript">
var pass = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[(2([0-4]\d|5[0-5])|1?\d{1,2})(\.(2([0-4]\d|5[0-5])|1?\d{1,2})){3} \])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
	function updateUser() {
	var validCheck=true;
	
	var nric=document.getElementById("nric").value;
		if(nric==""){
	   document.getElementById("nricspan").innerHTML = "STAFF-ID is empty";
	   validCheck=false;
        
    }else{
    	 document.getElementById("nricspan").innerHTML ="";
    }
	
	
	var userName=document.getElementById("userName").value;
	if(userName==""){
	   document.getElementById("unamespan").innerHTML = "User Name is empty";
	   validCheck=false;
    }else{
    	
    	 document.getElementById("unamespan").innerHTML ="";
    }
	
	
	var userDob=document.getElementById("userDob").value;
	if(userDob==""){
		   document.getElementById("dobspan").innerHTML = "Date Of Birth is empty";
			validCheck=false;
	    }else{
	    
	    	 document.getElementById("dobspan").innerHTML ="";
	   }

	var franchiseeExpiryDate=document.getElementById("franchiseeExpiryDate").value;
	if(franchiseeExpiryDate==""){
		   document.getElementById("franchiseespan").innerHTML = "Franchisee Expiry is empty";
			validCheck=false;
	    }else{
	    
	    	 document.getElementById("franchiseespan").innerHTML ="";
	   }
	   	
	var userEmail=document.getElementById("userEmail").value;
	if(userEmail==""){
		document.getElementById("emailspan").innerHTML ="";	
	}else{
		if(pass.test(userEmail)){
			document.getElementById("emailspan").innerHTML ="";	
		}else{
		validCheck=false;
		document.getElementById("emailspan").innerHTML ="Email ID is not valid";
		}}
			if(validCheck)
		{
			
			document.forms[0].action = 'UserDetail_useredit.do';
			document.forms[0].submit();
		}
		
	}

	function doSearch() {
		document.forms[0].action = 'Login_home.do';
		document.forms[0].submit();
	}
	
	function retfalse(){
		return false;
	}
</script>
</head>
<body>
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
 
  	<div class="headingBg"><div class="heading" align="center"> <h1>User Profile</h1> </div> </div>
	<s:form id="UserDetail" method="post"  theme="simple" onsubmit="return retfalse()">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		
	<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr><td>&nbsp;* &nbsp;</td></tr>
	</table>
	
	<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="center" valign="top" style="padding: 10px;"  class="boxConTd">
				<table width="60%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td align="center"> 
						<s:actionerror />
						 <s:actionmessage />
						 <s:fielderror />
					</td>
				</tr>
				<tr>
					<td align="left" valign="top" style="padding-top: 5px;">
					<div class="boxConOuter">
                	<div class="boxCon">
                  	<h2>User Details</h2>
                  	<div class="boxConIn">
						<table width="100%" border="0" cellpadding="2" cellspacing="2">
							<tr>
								<td align="right"><label for="userName">STAFF-ID : </label></td>
								<td width="75%"><s:textfield name="nric" id="nric" onblur="requireValue(this)" maxlength="7" />&nbsp;* &nbsp;

									<span id="nricspan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="userName">User Full Name : </label></td>
								<td><s:textfield name="userName" id="userName"	maxlength="50" style="width:210px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
									<span id="unamespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>

								<td align="right"><label for="userDob">User DOB :</label></td>
								<td><sj:datepicker name="userDob" id="userDob" cssClass="txtBox" changeMonth="true" changeYear="true" displayFormat="dd-M-yy" buttonImageOnly="true" yearRange="1900" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
								  <span id="dobspan" class="errorspan"> </span>
								</td>

							</tr>
								<tr>
								
								<td align="right"><label for="franchiseeExpiryDate">Franchisee Expiry Date: </label></td>
								<td>
									<sj:datepicker name="franchiseeExpiryDate" id="franchiseeExpiryDate" cssClass="txtBox" changeMonth="true" changeYear="true" displayFormat="dd-M-yy" buttonImageOnly="true" yearRange="1900" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" />&nbsp;<label>*</label> 
									<span id="franchiseespan" class="errorspan"> </span>
								</td>
								</tr>						
							<tr>

								<td align="right"><label for="userPhone">User Phone :</label></td>
								<td><s:textfield name="userPhone" id="userPhone"	maxlength="20" style="width:160px"  /> 
									
								</td>
							</tr>
							<tr>

								<td align="right"><label for="userEmail">User EmailID :</label></td>
								<td><s:textfield name="userEmail" id="userEmail"	maxlength="50" style="width:210px" /> 
									<span id="emailspan" class="errorspan"> </span>
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
					<s:hidden name="id"/>
					<s:hidden name="actionName"/>
					<td align="center" valign="middle" colspan="2">
					<s:if test="%{updateMode}">
						<sj:submit button="true" value="Update" onclick="updateUser();" />
					</s:if>
					
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</td>
				</tr>					
			</table>
		</s:form>
  
 
    <div class="grid_16">
        <div class="footer">
            Copyright &copy; 2011 7-eleven.
        </div>
	</div>
	<div class="clear"></div>
    	</div>
</body>
</html>

