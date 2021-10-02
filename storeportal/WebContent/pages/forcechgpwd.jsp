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

<sj:head jquerytheme="south-street" />
 <link rel="shortcut icon" href="styles/images/favico.png" type="image/x-icon"/>
 <link rel="stylesheet" href="styles/style.css" />
 <link rel="stylesheet" href="styles/chat.css" />
<script type="text/javascript">
	function confirmChgPwd() {
		
		var validCheck=true;
		var userPass=document.getElementById("lgnpwdn").value;
		var userCPwd=document.getElementById("lgnpwdc").value;
		
		
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
//			 var pattern =/^[A-Z][A-Za-z0-9_-]{2,19}$/;
                        var pattern = /^[A-Z][\w\W]{2,19}$/;                        
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
                var usr='${sessionScope.loginUser.userLogin}';
		document.forms['UserDetail'].action = 'UserDetail_forceChangePwd.do?userLogin='+usr;
		document.forms['UserDetail'].submit();
		}
	}

	function cancelChgPwd() {
		document.forms[0].action = 'Login_login.do';
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
 
  	<div class="headingBg"><div class="heading" align="center"> <h1>Change Password</h1> </div> </div>
 <s:hidden name="userLogin" id="userLogin" value="%{userLogin}" ></s:hidden>
 <s:form id="UserDetail" method="post"  theme="simple" onsubmit="return retfalse()">
		
		<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="center" valign="top" style="padding: 10px;"  class="boxConTd">
				<table width="55%" border="0" cellspacing="2" cellpadding="2">
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
                  	<h2>Change Password</h2>
                  	<div class="boxConIn">
						<table width="100%" border="0" cellpadding="2" cellspacing="2">
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
						<sj:submit button="true" value="Home"  onclick="cancelChgPwd();" />
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

