
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="grid_16" id="header">
    <h1><a>&nbsp;</a></h1>
   
   
			<div class="login_link">
			<br/>
			Logged in as: <a><%--<a href="UserDetail_userview.do"> --%><s:property value="%{#session.loginUser.userName}"/>&nbsp;
			<s:if test="%{#session.loginUser.tblRole.roleId==\"1\"}">(<s:property value="%{#session.tblStore.storeCode}"/> - <s:property value="%{#session.tblStore.storeName}"/>)</s:if>
			<s:elseif test="%{#session.loginUser.tblRole.roleId==\"4\"}">(<s:property value="%{#session.tblStore.storeCode}"/> - <s:property value="%{#session.tblStore.storeName}"/>)</s:elseif>
			<s:elseif test="%{#session.loginUser.tblRole.roleId==\"2\"}">(<s:property value="%{#session.tblArea.areaCd}"/> - <s:property value="%{#session.tblArea.areaName}"/>)</s:elseif>
			</a>	&nbsp;|&nbsp;

			<%-- SSO Changes :: START --%>
 			<%-- <a href="UserDetail_userPwd.do" class="login">Change Password</a>&nbsp; |&nbsp; --%>
 			<%-- SSO Changes :: END --%>
            <a href="Menu.do?method=logout" class="login">Log Out</a>
            </div>
             <a href="Login_home.do"><img src="styles/images/icohome.png" title="home" /></a>
       
</div>


