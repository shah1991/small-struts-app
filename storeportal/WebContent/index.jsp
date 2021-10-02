<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Store Portal</title>
	<link href="css/screen.css" type="text/css" rel="stylesheet" />
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

<%-- Skip login page if SSOFilter is enabled --%>
<s:if test="%{@com.dfsg.sso.filter.SSOFilter@IS_ENABLED}">
	<script type="text/javascript">
		location.href = "Login_login.do";
	</script>
</s:if>

<script type="text/javascript">
	function userLogin() {
		document.forms[0].action = 'Login_process.do';
		document.forms[0].submit();
	}

	function userLogin_dob() {
		var dob=document.getElementById("userDob").value.length;	
		if(dob==8){
			document.forms[0].action = 'Login_process.do';
			document.forms[0].submit();
		}else{
		alert("Please Enter Date Format (DDMMYYYY)");
	
		}
		
	}
		
	function cancelLogin() {
		document.forms[0].action = 'Login_cancel.do';
		document.forms[0].submit();
	}
	
</script>
</head>

<body style="background-position:left top" onload="document.forms[0].userName.focus(); document.forms[0].userName.select();">
    <div class="container_16">
	<div class="grid_16" id="header">
        <h1><a>&nbsp;</a></h1>
    </div>
    <!-- end .grid_16 -->
    <div class="clear"></div>
    
	<div class="grid_12">
	    
        <div class="clearfix" /></div>
        <div class="login_left">&nbsp;
        </div>
        <div class="login_right">
            <h2>Login</h2>
            <s:fielderror/>
            <p>
            Use login form below to access the pages.
            </p>
            <p>
            Only authorized users should login and all unauthorized 
            access will be prosecuted under any applicable laws.
            </p>            
            <s:form action="Login_process" focusElement="userName" theme="simple">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
              <tr valign="top">
                <td colspan="3" class="reg_head">Username &amp; Password</td>
              </tr>
              <tr valign="top">
                <td width="150">Username</td>
                <td width="10"> : </td>
                <td>
                <sj:textfield name="userName" maxlength="30"  cssClass="txtBox"  style="width:110px" id="userName" /></td>
                
                	
              </tr>
              <tr valign="top">
                <td>Password</td>
                <td> : </td>
                <td><s:password name="passWord" label="Password" cssClass="txtBox"  maxlength="20" style="width:110px"/> &nbsp;</td>
                   
              </tr>
             
              <!--
              <tr valign="top">
                <td>Date Of Birth</td>
                <td> : </td>
                <td><s:password name="userDob" id="userDob" maxlength="8"  cssClass="txtBox"  style="width:110px"  />&nbsp;&nbsp;(DDMMYYYY)</td>
                   
              </tr>
              -->

             <tr valign="top">
                <td>
                    Captcha
                </td>
                <td> : </td>
                <td><s:textfield name="captchaText" label="Code" cssClass="txtBox"  maxlength="20" style="width:110px"/> &nbsp;
                <br/><br/><img src="Captcha.jpg" border="0">
                </td>
                   
              </tr>
                            
              
              <tr valign="top">
                <td></td>
                <td></td>
                <td>
                    <sj:submit indicator="indicator" button="true" onclick="userLogin();" buttonIcon="ui-icon-person" value="Login"/>
                </td>
              </tr>
    
            </table>
            </s:form>
            <p>&nbsp;</p>
        </div>
        <!-- content end -->
	</div>
	<!-- end .grid_11 -->
    
    <div class="grid_16">
    <div class="footer">
        Copyright &copy; 2012 7-eleven.
    </div>
</div>
<div class="clear"></div>
 </body>
</html>


 

