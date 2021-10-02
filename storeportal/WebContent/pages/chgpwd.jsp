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
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	function confirmChgPwd() {
	var validCheck=true;
		var userOldPass=document.getElementById("lgnpwdo").value;
		var userPass=document.getElementById("lgnpwdn").value;
		var userCPwd=document.getElementById("lgnpwdc").value;
		
		
	if(userOldPass==""){
			
			document.getElementById("oldspan").innerHTML ="Current Password is empty";
			validCheck=false;
		}else{
			
			document.getElementById("oldspan").innerHTML ="";
		}
		if(userCPwd==""){
			
			document.getElementById("confirmspan").innerHTML ="Confirm Password is empty";
			validCheck=false;
		}else{
			
			document.getElementById("confirmspan").innerHTML ="";
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
		
			
		if(validCheck){
		document.forms[0].action = 'Login_changePwd.do';
		document.forms[0].submit();
		}
	}

	function cancelChgPwd() {
		document.forms[0].action = 'Menu.do?method=home';
		document.forms[0].submit();
	}
	
	
	function retfalse(){
		return false;
	}
</script>
</head>
<body>
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
  	<div class="headingBg"><div class="heading" align="center"> <h1>Profile Settings</h1> </div> </div>
	<s:form action="Login_changePwd" method="post" focus="lgnpwdo" theme="simple" onsubmit="return retfalse()">
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
                  	<h2>Change Password</h2>
                  	<div class="boxConIn">
						<table width="100%" border="0" cellpadding="2" cellspacing="2">
							<tr>
									<td align="right"><label for="lgnpwdo">Current Password:</label></td>
									<td width="70%"><s:password name="lgnpwdo" id="lgnpwdo"	maxlength="20" style="width:110px" /> &nbsp;* &nbsp;
										 <span id="oldspan" class="errorspan"> </span>
									</td>
								</tr>
								<tr>
									<td align="right"><label for="lgnpwdn">New
											Password:</label></td>
									<td><s:password name="lgnpwdn" id="lgnpwdn"
											maxlength="20" style="width:110px" /> &nbsp;* &nbsp;
											 <span id="pwdspan" class="errorspan"> </span>
									</td>
								</tr>
								<tr>
									<td align="right"><label for="lgnpwdc">Confirm
											New Password:</label></td>
									<td><s:password name="lgnpwdc" id="lgnpwdc"
											maxlength="20" style="width:110px" /> &nbsp;* &nbsp;
											<span id="confirmspan" class="errorspan"> </span>
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
					<td align="center" valign="middle" colspan="2">
						<sj:submit button="true" value="Confirm" onclick="confirmChgPwd();" />
						<sj:submit button="true" value="Cancel"  onclick="cancelChgPwd();" />
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

