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
<script src="Scripts/jquery.maskedinput-1.2.2.min.js" type="text/javascript"> </script>

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

	/*function createUser() {
		if(autocheck(document.forms['UserDetail'])){
			document.forms[0].action = 'UserDetail_create.do';
			document.forms[0].submit();
		}
	}*/

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

/*
 * Satheesh added the new script on 05/06/2013 - Automatic franchisee duration calculation based on the month entered
 */

 Date.isLeapYear = function (year) { 
	    return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0)); 
	};

	Date.getDaysInMonth = function (year, month) {
	    return [31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
	};

	Date.prototype.isLeapYear = function () { 
	    var y = this.getFullYear(); 
	    return (((y % 4 === 0) && (y % 100 !== 0)) || (y % 400 === 0)); 
	};

	Date.prototype.getDaysInMonth = function () { 
	    return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
	};

	Date.prototype.addMonths = function (value) {
	    var n = this.getDate();
	    this.setDate(1);
	    this.setMonth(this.getMonth() + value);
	    this.setDate(Math.min(n, this.getDaysInMonth()));
	   return this;
	};

	function addFranchiseeDuration() {		
		var noOfMonth = parseInt(document.getElementById("franchiseeDuration").value);
		var d1 = document.getElementById("franchiseeStartDate").value;
		
		if(d1==""){
			alert("Franchisee Start date is empty");
			document.getElementById("franchiseeStartDate").focus();
			document.getElementById("franchiseeDuration").value="";
			return false;
		}
		var d = new Date(d1);
		d.addMonths(noOfMonth);

		//var result2 = d.addMonths(noOfMonth);
		//alert(">>>>result2: " + result2);

		if (noOfMonth>84) {			
			alert("Franchisee duration not more than 7 years!");			
		} else {
			d.setDate(d.getDate()-1);
			document.getElementById("franchiseeExpiryDate").value = d.getFullYear( ) + '-' + ( d.getMonth( ) + 1 ) + '-' + d.getDate( ) ;
		}
	}
 /*
  * Satheesh added the new script on 05/06/2013
  */

  function isNumberKey(evt)
  {
     var charCode = (evt.which) ? evt.which : event.keyCode;
     if (charCode != 46 && charCode > 31 
       && (charCode < 48 || charCode > 57))
        return false;

     return true;
  }

	function createUser() {
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

		/*var franchiseeExpiryDate=document.getElementById("franchiseeExpiryDate").value;
                    if(franchiseeExpiryDate==""){
			   document.getElementById("franchiseespan").innerHTML = "Franchisee Expiry is empty";
				validCheck=false;
		    }else{
		    
		    	 document.getElementById("dobspan").innerHTML ="";
		   }*/
		//alert('aaa');
                var roleId=document.getElementById("userRole").value;
                // alert (roleId);   
                var franchiseeStartDate=document.getElementById("franchiseeStartDate").value;
                 if(roleId==4){
                    if(franchiseeStartDate==""){
			   document.getElementById("franchiseespan").innerHTML = "Franchisee Start Date is empty";
				validCheck=false;
		    }else{
		    
		    	 document.getElementById("franchiseespan").innerHTML ="";
		   }  
                } 
		
        
                var userEmail=document.getElementById("userEmail").value;
		if(userEmail===""){
			document.getElementById("emailspan").innerHTML ="";	
		}else{
			if(pass.test(userEmail)){
				document.getElementById("emailspan").innerHTML ="";	
			}else{
			validCheck=false;
			document.getElementById("emailspan").innerHTML ="Email ID is not valid";
			}}
		
		
		if(roleId===""){
			   document.getElementById("rolespan").innerHTML = "Select a Role";
				validCheck=false;
		    }else{
		    
		    	 document.getElementById("rolespan").innerHTML ="";
		    	 if(roleId!=3 && roleId!=13){
		    	 var areaId=document.getElementById("areaId").value;
		 		if(areaId==""){
		 			   document.getElementById("areaspan").innerHTML = "Select a area";
		 				validCheck=false;
		 		    }else{
		 		    
		 		    	 document.getElementById("areaspan").innerHTML ="";
		 		   }}
		   }
		
		//alert('nnn');
		var userLogin=document.getElementById("userLogin").value;
		if(userLogin===""){
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
		
		if(userCPwd===""){
			validCheck=false;
			document.getElementById("cpwdspan").innerHTML ="ConfirmPassword is empty";
			
		}else{
			
			document.getElementById("cpwdspan").innerHTML ="";
		}
		
                if(userPass===""){
                    validCheck=false;
			document.getElementById("pwdspan").innerHTML ="Password is empty";
			
		}
                
                
                //alert(userPass);
                               
                 var pattern =/^[A-Z][A-Za-z0-9_.,^&*$%#@!~+=;:|-]{2,19}$/;
		        if (pattern.test(userPass)) {
		        	 var m = userPass.match(/\d+/g);
		        	
		 			if(m==null){
                                            validCheck=false;
		 				document.getElementById("pwdspan").innerHTML ="Give atleast one numeric value";
		 				
		 				
		 			}else{
		 				
		 				if(userPass==userCPwd){
		 					document.getElementById("pwdspan").innerHTML ="";
		 				}else{
                                                        validCheck=false;
		 					document.getElementById("pwdspan").innerHTML ="Password and Confirm Password not match";
			 						
		 				
		 				}
		 			}
		 			
		           
		        } else {
                            
                            validCheck=false;
                            
		        	document.getElementById("pwdspan").innerHTML ="Password first letter should be Capital";
		          
		            
		        }
                        
                if(userPass.length<=7){
		
                         validCheck=false;  	
			 document.getElementById("pwdspan").innerHTML ="Password atleast 8 character";
		 
		}
                
                if(validCheck===true)
			{
                                //alert('lll');
				document.forms[0].action = 'UserDetail_create.do';
				document.forms[0].submit();
			}
		/*else{
                        //alert('hhhh');
                         //validCheck=t; 
                         document.forms[0].action = 'UserDetail_create.do';
				document.forms[0].submit();
		}*/
                /*else{
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
		
		}}*/
	
			/*if(validCheck==true)
			{
                                alert('lll');
				document.forms[0].action = 'UserDetail_create.do';
				document.forms[0].submit();
			}*/
		
	}

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

		/*var franchiseeExpiryDate=document.getElementById("franchiseeExpiryDate").value;
                    if(franchiseeExpiryDate==""){
			   document.getElementById("franchiseespan").innerHTML = "Franchisee Expiry is empty";
				validCheck=false;
		    }else{
		    
		    	 document.getElementById("dobspan").innerHTML ="";
		   }*/
		//alert('aaa');
        var roleId=document.getElementById("userRole").value;
                // alert (roleId);   
        var franchiseeStartDate=document.getElementById("franchiseeStartDate").value;
            if(roleId==4){
               if(franchiseeStartDate==""){
			   	document.getElementById("franchiseespan").innerHTML = "Franchisee Start Date is empty";
				validCheck=false;
		    	}else{		    
		    	 document.getElementById("franchiseespan").innerHTML ="";
		   		}  
            } 
		
        
        var userEmail=document.getElementById("userEmail").value;
			if(userEmail===""){
				document.getElementById("emailspan").innerHTML ="";	
			}else{
				if(pass.test(userEmail)){
					document.getElementById("emailspan").innerHTML ="";	
				}else{
					validCheck=false;
					document.getElementById("emailspan").innerHTML ="Email ID is not valid";
				}
			}
		
		
		if(roleId===""){
			   document.getElementById("rolespan").innerHTML = "Select a Role";
				validCheck=false;
		}else{
		    
		    	document.getElementById("rolespan").innerHTML ="";
		    	if(roleId!=3 && roleId!=13){
		    	 	var areaId=document.getElementById("areaId").value;
		 			if(areaId==""){
		 			    document.getElementById("areaspan").innerHTML = "Select a area";
		 				validCheck=false;
		 		    }else{
		 		    
		 		    	 document.getElementById("areaspan").innerHTML ="";
		 		    }
		 		}
		   }
		
		//alert('nnn');
		var userLogin=document.getElementById("userLogin").value;
		if(userLogin===""){
			   document.getElementById("userspan").innerHTML = "User Login is empty";
			   validCheck=false;
		}else if(userLogin.length>3){
		    	document.getElementById("userspan").innerHTML ="";
	    }else{
		    	validCheck=false;
		    	 document.getElementById("userspan").innerHTML ="Login ID must be more than 3 character ";
	    }

/*		var userPass=document.getElementById("userPwd").value;
		var userCPwd=document.getElementById("userCfmPwd").value;
		
		if(userCPwd===""){			
			document.getElementById("cpwdspan").innerHTML ="ConfirmPassword is empty";
			validCheck=false;
		}else{			
			document.getElementById("cpwdspan").innerHTML ="";
		}
		
        if(userPass===""){
			document.getElementById("pwdspan").innerHTML ="Password is empty";
			validCheck=false;
		}                
                
                //alert(userPass);
                               
        var pattern =/^[A-Z][A-Za-z0-9_.,^&*$%#@!~+=;:|-]{2,19}$/;
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
                        
        if(userPass.length<=7){
             validCheck=false;   
			 document.getElementById("pwdspan").innerHTML ="Password atleast 8 character";		 
		}
        */
                
        if(validCheck===true)
		{
                //alert('lll');
				//document.forms[0].action = 'UserDetail_create.do';
				document.forms[0].action = 'UserDetail_edit.do';
				document.forms[0].submit();
		}
		/*else{
                        //alert('hhhh');
                         //validCheck=t; 
                         document.forms[0].action = 'UserDetail_edit.do';
			 document.forms[0].submit();
		}*/
                /*else{
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
		
		}}*/
	
			/*if(validCheck==true)
			{
                                alert('lll');
				document.forms[0].action = 'UserDetail_create.do';
				document.forms[0].submit();
			}*/
		//if(autocheck(document.forms['UserDetail'])){
			//document.forms[0].action = 'UserDetail_edit.do';
			//document.forms[0].submit();
		//}
	}	
	
	function updateUser_old() {
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
				}
		}
		
		var roleId=document.getElementById("userRole").value;
	
		if(roleId==""){
			   document.getElementById("rolespan").innerHTML = "Select a Role";
				validCheck=false;
		    }else{		    
		    	 document.getElementById("rolespan").innerHTML ="";
		    if(roleId!=3 || roleId!=13)
			{
		    	var areaId=document.getElementById("areaId").value;
		 		if(areaId==""){
		 			document.getElementById("areaspan").innerHTML = "Select a area";
		 			validCheck=false;
		 		}else{
 		    	 document.getElementById("areaspan").innerHTML ="";
 		   		}
	 		}
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
		
		}
		}
			if(validCheck===true)
			{
                alert('aa');
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
			
		} else {
			document.getElementById("store").style.display="none";
			document.getElementById("storelabel").style.display="none";
		}
	}

	function retfalse(){
		return false;
	}
	
	function doChangePwd() {
		document.forms[0].action = 'UserDetail_doChangePwd.do';
		document.forms[0].submit();
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
				<table width="80%" border="0" cellspacing="2" cellpadding="2">
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
                  				<td align="right"><label for="nric" >STAFF-ID : </label></td>
                  				<td><s:textfield name="nric" id="nric" onblur="requireValue(this)" maxlength="15" />&nbsp;* &nbsp;
                  				<span id="nricspan" class="errorspan"> </span>
                  				</td>
                  				
                  				<td align="right"><label for="userName">User Full Name : </label></td>
                  				<td><s:textfield name="userName" id="userName"	maxlength="50" style="width:210px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
								<!-- <span id="unamespan" class="errorspan"> </span> -->
								<span class="errorMessage" id="unamespan"><s:fielderror fieldName="userName" /> </span>
								</td>
                  			</tr>
                  			<tr>
								<td align="right"><label for="userDob">User DOB :</label></td>
								<td><sj:datepicker name="userDob" id="userDob" cssClass="txtBox" changeMonth="true"  changeYear="true" displayFormat="dd-M-yy" buttonImageOnly="true" yearRange="1900" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)"/> &nbsp;* &nbsp;
								<span id="dobspan" class="errorspan"> </span>
								</td>
								<td align="right"><label for="gender">Gender :</label></td>
								<td><s:textfield name="gender" id="gender"	maxlength="20" style="width:160px"  />
								</td>								                  				
                  			</tr>                  			
                  			<tr>
								<td align="right"><label for="userEmail">User Email ID :</label></td>
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
								<td align="right"><label for="franchiseeScheme">Franchisee Scheme :</label></td>
								<td><s:textfield name="franchiseeScheme" id="franchiseeScheme"	maxlength="20" style="width:160px"  /></td>                  			
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
								<td><sj:datepicker name="franchiseeStartDate" id="franchiseeStartDate" cssClass="txtBox" changeMonth="true" changeYear="true" displayFormat="yy-mm-dd" buttonImageOnly="true" yearRange="1900"  showButtonPanel="true"  maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;
								<span id="franchiseespan" class="errorspan"> </span>
								</td>                 			
                  			</tr>
                  			<tr>
								<td align="right"><label for="franchiseeDuration">Franchisee Duration (in Months):</label></td>
								<td><s:textfield name="franchiseeDuration" id="franchiseeDuration"	maxlength="3" style="width:160px" onkeypress="return isNumberKey(event)" onChange="addFranchiseeDuration()" onblur="requireValue(this)" />  &nbsp;* &nbsp;
								</td>                   			
								<td align="right"><label for="franchiseeExpiryDate">Franchisee Expiry Date:</label></td>
								<td><s:textfield name="franchiseeExpiryDate" id="franchiseeExpiryDate" cssClass="txtBox" maxlength="3" style="width:160px" />  &nbsp;* &nbsp;
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
									<s:select name="storeId" id="storeId" list="stores" listKey="storeId"  listValue="storeName" style="width:160px"  onblur="requireValue(this)" />&nbsp;<label>*</label> &nbsp;&nbsp;&nbsp;&nbsp;
									<span id="storespan" class="errorspan"> </span>
									</div>
								</td>								                  			
                  			</tr>
                  			
                  			<tr>
								<td align="right"><label for="userLogin">Login ID : </label></td>
								<td><s:textfield name="userLogin" id="userLogin"	maxlength="20" style="width:160px"   onblur="requireValue(this)" /> &nbsp;* &nbsp;
									<span id="userspan" class="errorspan"> </span>
								</td>
								<%--<td align="right"><label for="userPwd">Login Password : </label></td>
								<td>
								
									<s:password name="userPwd" id="userPwd" maxlength="20" style="width:160px" showPassword="true"  onblur="requireValue(this)" /> &nbsp;* &nbsp;
									<span id="pwdspan" class="errorspan"> </span>
								</td>--%>								                  			
                  			</tr>
                                        <s:if test="%{updateMode==false}">
                  			<tr>
                                            <td align="right"><label for="userPwd">Login Password : </label></td>
								<td>
								
									<s:password name="userPwd" id="userPwd" maxlength="20" style="width:160px" showPassword="true"  onblur="requireValue(this)" /> &nbsp;* &nbsp;
									<span id="pwdspan" class="errorspan"> </span>
								</td>
                                                                        <tr>
                                                                        </tr>
								
								<td align="right"><label for="userCfmPwd">Confirm Password : </label></td>
								<td>
										<s:password name="userCfmPwd" id="userCfmPwd" maxlength="20" style="width:160px" showPassword="true"   onblur="requireValue(this)" /> &nbsp;* &nbsp;
										<span id="cpwdspan" class="errorspan"> </span>
								</td>
                                                     </tr>                                     
					</s:if>         
                                        <tr>
								<td align="right"><label for="userRemarks">User Remarks : </label></td>
								<td colspan="3"><s:textarea  name="userRemarks" id="userRemarks" style="width:250px;height:90px;"/>
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
                                                <sj:submit button="true" value="Change Password" onclick="doChangePwd();" />
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

