<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - User Detail</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>        
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	var pass = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[(2([0-4]\d|5[0-5])|1?\d{1,2})(\.(2([0-4]\d|5[0-5])|1?\d{1,2})){3} \])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
	function doSearch() {
		document.forms[0].action = 'UserMain_search.do';
		document.forms[0].submit();
	}

	function deleteUser() {
		if(confirm('Are you sure?')){
			document.forms[0].action = 'UserMain_delete.do';
			document.forms[0].submit();
		}else{
			return false;
		}
	}

	function createUser() {
		if(autocheck(document.forms['UserDetail'])){
			document.forms[0].action = 'UserDetail_create.do';
			document.forms[0].submit();
		}
	}

	function addFranchiseeDuration() {
		var noOfMonth = parseInt(document.getElementById("franchiseeDuration").value);
		var d1 = document.getElementById("franchiseeStartDate").value;
		var d = new Date(d1);
		//var d = new Date();
		//alert("d = " + d);
		if (noOfMonth>84) {			
			alert("Franchisee duration not more than 7 years!");			
		} else {
			d.setMonth( d.getMonth( ) + noOfMonth);
			document.getElementById("franchiseeExpiryDate").value = d.getFullYear( ) + '-' + ( d.getMonth( ) + 1 ) + '-' + d.getDate( ) + ' 00:00';
		}
	}

	function addDate(){
		date = new Date();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var year = date.getFullYear();

		if (document.getElementById('franchiseeStartDate').value == ''){
		//document.getElementById('franchiseeStartDate').value = day + '-' + month + '-' + year;
		document.getElementById("franchiseeStartDate").value = year + '-' + month + '-' + day + ' 00:00';
		}
	}
		
	function createUser_old() {
		var validCheck=true;
		
		var nric=document.getElementById("nric").value;
			if(nric==""){
		   document.getElementById("nricspan").innerHTML = "NRIC is empty";
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
		    
		    	 document.getElementById("dobspan").innerHTML ="";
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
		
		var roleId=document.getElementById("userRole").value;
	
		if(roleId==""){
			   document.getElementById("rolespan").innerHTML = "Select a Role";
				validCheck=false;
		    }else{
		    
		    	 document.getElementById("rolespan").innerHTML ="";
		    	 if(roleId!=3){
		    	 var areaId=document.getElementById("areaId").value;
		 		if(areaId==""){
		 			   document.getElementById("areaspan").innerHTML = "Select a area";
		 				validCheck=false;
		 		    }else{
		 		    
		 		    	 document.getElementById("areaspan").innerHTML ="";
		 		   }}
		   }
		
		
		var userLogin=document.getElementById("userLogin").value;
		if(userLogin==""){
			   document.getElementById("userspan").innerHTML = "User Login is empty";
			   validCheck=false;
		    }else if(userLogin.length>4){
		    	document.getElementById("userspan").innerHTML ="";
		    	
				
		    }else{
		    	validCheck=false;
		    	 document.getElementById("userspan").innerHTML ="Login ID must be more than 4 character ";
		   }
		
		
		

		var userPass=document.getElementById("userPwd").value;
		var userCPwd=document.getElementById("userCfmPwd").value;
		
		if(userCPwd==""){
			
			document.getElementById("cpwdspan").innerHTML ="ConfirmPassword is empty";
			validCheck=false;
		}else{
			
			document.getElementById("cpwdspan").innerHTML ="";
		}
		if(userPass==""){
			document.getElementById("pwdspan").innerHTML ="Password is empty";
			validCheck=false;
		}else{
		if(userPass.length>7){
			 var pattern =/^[A-Z][A-Za-z0-9_-]{2,19}$/;
		        if (pattern.test(userPass)) {
		        	 var m = userPass.match(/\d+/g);
		        	
		 			if(m==null){
		 				document.getElementById("pwdspan").innerHTML ="Give atleast one numeric value";
		 				
		 				validCheck=false;
		 			}else{
		 				
		 				if(userPass==userCPwd){
		 					document.getElementById("pwdspan").innerHTML ="";
		 				}else{
		 					document.getElementById("pwdspan").innerHTML ="Password and Confirm Password not match";
			 				validCheck=false;		
		 				
		 				}
		 			}
		 			
		           
		        } else {
		        	document.getElementById("pwdspan").innerHTML ="Password first letter should be Capital";
		          
		            validCheck=false;
		        }
		}
		else{
			 validCheck=false;
			 document.getElementById("pwdspan").innerHTML ="Password atleast 8 character";
		
		}}
	
			if(validCheck)
			{
				document.forms[0].action = 'UserDetail_create.do';
				document.forms[0].submit();
			}
		
	}

	function updateUser() {
		if(autocheck(document.forms['UserDetail'])){
			document.forms[0].action = 'UserDetail_edit.do';
			document.forms[0].submit();
		}
	}	
	
	function updateUser_old() {
		var validCheck=true;
		
		var nric=document.getElementById("nric").value;
			if(nric==""){
		   document.getElementById("nricspan").innerHTML = "NRIC is empty";
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

		var userDob=document.getElementById("userDoj").value;
		if(userDob==""){
			   document.getElementById("dojspan").innerHTML = "Date Of Joining is empty";
				validCheck=false;
		    }else{
		    
		    	 document.getElementById("dojspan").innerHTML ="";
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
		
		var roleId=document.getElementById("userRole").value;
	
		if(roleId==""){
			   document.getElementById("rolespan").innerHTML = "Select a Role";
				validCheck=false;
		    }else{
		    
		    	 document.getElementById("rolespan").innerHTML ="";
		    	 if(roleId!=3){
		    	 var areaId=document.getElementById("areaId").value;
		 		if(areaId==""){
		 			   document.getElementById("areaspan").innerHTML = "Select a area";
		 				validCheck=false;
		 		    }else{
		 		    
		 		    	 document.getElementById("areaspan").innerHTML ="";
		 		   }}
		   }
		
		
		var userLogin=document.getElementById("userLogin").value;
		if(userLogin==""){
			   document.getElementById("userspan").innerHTML = "User Login is empty";
			   validCheck=false;
		    }else if(userLogin.length>4){
		    	document.getElementById("userspan").innerHTML ="";
		    	
				
		    }else{
		    	validCheck=false;
		    	 document.getElementById("userspan").innerHTML ="Login ID must be more than 4 character ";
		   }
		
		
		

		var userPass=document.getElementById("userPwd").value;
		var userCPwd=document.getElementById("userCfmPwd").value;
		
		if(userCPwd==""){
			
			document.getElementById("cpwdspan").innerHTML ="Confirm Password is empty";
			validCheck=false;
		}else{
			
			document.getElementById("cpwdspan").innerHTML ="";
		}
		if(userPass==""){
			document.getElementById("pwdspan").innerHTML ="Password is empty";
			validCheck=false;
		}else{
		if(userPass.length>7){
			 var pattern =/^[A-Z][A-Za-z0-9_-]{2,19}$/;
		        if (pattern.test(userPass)) {
		        	 var m = userPass.match(/\d+/g);
		        	
		 			if(m==null){
		 				document.getElementById("pwdspan").innerHTML ="Give atleast one numeric value";
		 				
		 				validCheck=false;
		 			}else{
		 				
		 				if(userPass==userCPwd){
		 					document.getElementById("pwdspan").innerHTML ="";
		 				}else{
		 					document.getElementById("pwdspan").innerHTML ="Password and Confirm Password not match";
			 				validCheck=false;		
		 				
		 				}
		 			}
		 			
		           
		        } else {
		        	document.getElementById("pwdspan").innerHTML ="Password first letter should be Capital";
		          
		            validCheck=false;
		        }
		}
		else{
			 validCheck=false;
			 document.getElementById("pwdspan").innerHTML ="Password atleast 8 character";
		
		}}
			if(validCheck)
			{
				document.forms[0].action = 'UserDetail_edit.do';
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
	
	function chooseArea(){
		var val=document.getElementById("userRole");		
		if(val.value==2){
			document.getElementById("store").style.display="none";
			document.getElementById("storelabel").style.display="none";
			document.getElementById("area").style.display="block";
			document.getElementById("arealabel").style.display="block";
		}else if((val.value==1)||(val.value==4)){
			document.getElementById("area").style.display="block";
			document.getElementById("store").style.display="block";
			document.getElementById("arealabel").style.display="block";
			document.getElementById("storelabel").style.display="block";
			//document.getElementById("storearea").style.display="block";
		}else{
			document.getElementById("arealabel").style.display="none";
			document.getElementById("area").style.display="none";
			document.getElementById("store").style.display="none";
			document.getElementById("storelabel").style.display="none";
		}
		
	}
	
	function chooseStore(){
		var val=document.getElementById("userRole");
		if((val.value==1)||(val.value==4)){
			
			document.getElementById("store").style.display="block";
			document.getElementById("storelabel").style.display="block";
			document.forms[0].action = 'UserDetail_selectstore.do';
			document.forms[0].submit();
		}else{
			document.getElementById("store").style.display="none";
			document.getElementById("storelabel").style.display="none";
		}
		
	}
	
	function selectStore(){
		var val=document.getElementById("userRole");
		if(val.value==1){
			
			document.getElementById("store").style.display="block";
			document.getElementById("storelabel").style.display="block";
			
		}
	}
	
	function retfalse(){
		return false;
	}
</script>
</head>
<body onload="chooseArea();selectStore();" >
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
  	<div class="headingBg"><div class="heading" align="center"> <h1>User Details</h1> </div> </div>
	<s:form id="UserDetail" method="post"  theme="simple" onsubmit="return retfalse()">
	<s:hidden name="updateMode"></s:hidden>
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="center" valign="top" style="padding: 10px;"  class="boxConTd">
				<table width="70%" border="0" cellspacing="2" cellpadding="2">
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
                  	<h2 align="center">User Details</h2>
                  	<div class="boxConIn">
                  		
                  		<table width="100%" border="0" cellpadding="2" cellspacing="2">
                  			<tr>
                  				<td align="right"><label for="nric" >NRIC : </label></td>
                  				<td><s:textfield name="nric" id="nric" onblur="requireValue(this)" maxlength="15" />&nbsp;* &nbsp;
                  				<span id="nricspan" class="errorspan"> </span>
                  				</td>
                  				
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
								<td align="right"><label for="gender">Gender :</label></td>
								<td><s:textfield name="gender" id="gender"	maxlength="20" style="width:160px"  />
								</td>								                  				
                  			</tr>                  			
                  			<tr>
								<td align="right"><label for="userEmail">User EmailID :</label></td>
								<td><s:textfield name="userEmail" id="userEmail"	maxlength="50" style="width:210px" /> 
									<span id="emailspan" class="errorspan"> </span>
								</td>   
								<td align="right"><label for="userRole">Role : </label></td>
								<td>
									<s:select name="userRole" id="userRole" list="roles" listKey="roleId"  listValue="roleName" headerKey="" headerValue="Select Role" style="width:160px"  onblur="requireValue(this)" onchange="chooseArea()"/>&nbsp;<label>*</label> &nbsp;
									<span id="rolespan" class="errorspan"> </span>
								</td>								               			
                  			</tr>
                  			<tr>
								<td align="right"><label for="rcbName">RCB Name :</label></td>
								<td><s:textfield name="rcbName" id="rcbName"	maxlength="20" style="width:160px"  />
								</td>                   			
								<td align="right"><label for="rcbNo">RCB No :</label></td>
								<td><s:textfield name="rcbNo" id="rcbNo"	maxlength="20" style="width:160px"  />
								</td>                   			
                  			</tr>
                  			<tr>
								<td align="right"><label for="gstNo">GST No :</label></td>
								<td><s:textfield name="gstNo" id="gstNo"	maxlength="20" style="width:160px"  />
								</td>                   			
								<td align="right"><label for="userPhone">User Phone :</label></td>
								<td><s:textfield name="userPhone" id="userPhone"	maxlength="20" style="width:160px"  /></td>                  			
                  			</tr>      
                  			<tr>
								<td align="right"><label for="userDoj">Date Of Joining :</label></td>
								<td><sj:datepicker name="userDoj" id="userDoj" cssClass="txtBox" changeMonth="true" changeYear="true" displayFormat="dd-M-yy" buttonImageOnly="true" yearRange="1900" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
								<span id="dojspan" class="errorspan"> </span>
								</td>
								<td align="right">&nbsp;</td>
								<td>&nbsp;</td>								                  				
                  			</tr>                   			            			                  			
                  			<tr>
								<td align="right"><label for="userMobile">User Mobile :</label></td>
								<td><s:textfield name="userMobile" id="userMobile"	maxlength="20" style="width:160px"  />
								</td>                   			
								<td align="right"><label for="franchiseeStartDate">Franchisee Start Date:</label></td>
								<td><sj:datepicker name="franchiseeStartDate" id="franchiseeStartDate" cssClass="txtBox" changeMonth="true" changeYear="true" displayFormat="dd-M-yy" buttonImageOnly="true" yearRange="1900" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
								<span id="franchiseespan" class="errorspan"> </span>
								</td>                 			
                  			</tr>
                  			<tr>
								<td align="right"><label for="franchiseeDuration">Franchisee Duration :</label></td>
								<td><s:textfield name="franchiseeDuration" id="franchiseeDuration"	maxlength="3" style="width:160px" onChange="addFranchiseeDuration()" /> 
								</td>                   			
								<td align="right"><label for="franchiseeExpiryDate">Franchisee Expiry Date:</label></td>
								<td><s:textfield name="franchiseeExpiryDate" id="franchiseeExpiryDate" cssClass="txtBox" maxlength="3" style="width:160px" /> 
								</td>																                  			
                  			</tr>                  		
                  			<tr>
								<td align="right"><div id="arealabel" style="display: none"><label for="areaId">Area : </label>	</div></td>
								<td>
									<div id="area" style="display: none"><s:select name="areaId" id="areaId" list="areas" listKey="areaId"  listValue="areaName" headerKey="" headerValue="Select Area" style="width:160px"  onblur="requireValue(this)"  onselect="chooseStore()" onChange="chooseStore()"/>&nbsp;<label>*</label> 
									<span id="areaspan" class="errorspan"> </span>&nbsp; &nbsp;</div>
								</td>
								<td align="right"><div id="storelabel" style="display: none"><label for="storeId">Store : </label></div>	</td>
								<td><div id="store" style="display: none">
									<s:select name="storeId" id="storeId" list="storeList" listKey="storeId"  listValue="storeName" style="width:160px"  onblur="requireValue(this)" />&nbsp;<label>*</label> &nbsp;&nbsp;&nbsp;&nbsp;
									<span id="storespan" class="errorspan"> </span>
									</div>
								</td>								                  			
                  			</tr>
                  			
                  			<tr>
								<td align="right"><label for="userLogin">Login ID : </label></td>
								<td><s:textfield name="userLogin" id="userLogin"	maxlength="20" style="width:160px"  /> &nbsp;* &nbsp;
									<span id="userspan" class="errorspan"> </span>
								</td>
								<td align="right"><label for="userPwd">Login Password : </label></td>
								<td>
								
									<s:password name="userPwd" id="userPwd" maxlength="20" style="width:160px" showPassword="true"/> &nbsp;* &nbsp;
									<span id="pwdspan" class="errorspan"> </span>
								</td>								                  			
                  			</tr>
                  			<tr>
								<td align="right"><label for="userCfmPwd">Confirm Password : </label></td>
								<td>
										<s:password name="userCfmPwd" id="userCfmPwd" maxlength="20" style="width:160px" showPassword="true" /> &nbsp;* &nbsp;
										<span id="cpwdspan" class="errorspan"> </span>
								</td>
								<td align="right"><label for="userRemarks">User Remarks : </label></td>
								<td colspan="3"><s:textarea  name="userRemarks" id="userRemarks" onblur="requireValue(this)" style="width:250px;height:90px;"/>
								</td>             			
                  			</tr>
                  			<tr>
								<td align="right"><label for="readOnly">Access : </label></td>
								<td>
									<sj:radio name="readOnly" list="#{'Y':'Read','N':'All'}" value="readOnly" />
								</td>                  			
								<td align="right"><label for="active">Active : </label></td>
								<td>
									<sj:radio name="active" list="#{'Y':'Active','N':'Inactive'}" value="active" />
								</td>
																								                  			
                  			</tr>
                  		
                  		</table>
						</div></div></div>
					</td>
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
					<s:else>
						<sj:submit button="true" value="Create" onclick="createUser();" />
					</s:else>
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
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

