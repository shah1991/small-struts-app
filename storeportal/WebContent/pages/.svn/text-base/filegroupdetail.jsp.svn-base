<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<sj:head jquerytheme="eggplant"  jqueryui="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>        
<script type="text/javascript">
function doSearch() {
		document.forms["FileGroupDetail"].action = 'FileGroup_search.do';
		document.forms["FileGroupDetail"].submit();
	}
	
	function createFileGroup() {
		 var gname=document.getElementById("groupName").value;
		 if(gname==""){
		    document.getElementById("groupspan").innerHTML ="Group Name is empty";
		 }else{
			document.forms["FileGroupDetail"].action = 'FileGroup_create.do';
			document.forms["FileGroupDetail"].submit();
		 }
		
		
	}

	function updateFileGroup() {
		 var gname=document.getElementById("groupName").value;
		 if(gname==""){
		    document.getElementById("groupspan").innerHTML ="Group Name is empty";
		 }else{
			document.forms["FileGroupDetail"].action = 'FileGroup_update.do';
			document.forms["FileGroupDetail"].submit();
		 }
		
	}
	function retfalse(){
		return false;
	}
</script>
</head>
<body onload="document.forms[0].GroupName.focus(); document.forms[0].GroupName.select();" >
		<s:form id="FileGroupDetail" method="post" theme="simple"  onsubmit="return retfalse()">
			<s:hidden name="groupId" id="groupId" value="%{groupId}" ></s:hidden>
    		<div class="div-width" style="background:#f9f9f9; border-bottom:1px solid #ccc; border-right:1px solid #ccc; padding:0 0 0 0;  margin:2px 0 0 0">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top" style="padding-top: 10px;">
						<table width="100%" border="0" cellpadding="4" cellspacing="0">
							<tr>
							<td>
								<span id="desc"></span>
							</td>
							</tr>
							<tr>
								<td align="right"><label for="groupName">File Group Name : </label></td>
								<td width="70%"><s:textfield name="groupName" id="groupName" maxlength="50" style="width:200px"  />
								<span id="groupspan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="status">Status : </label></td>
								<td>
									<sj:radio id="status" name="status" list="#{'Y':'Active','N':'Inactive'}" value="%{status}" />
								</td>
							</tr>						
						</table>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" valign="top" >
					<s:if test="%{editmode}">
					
						<sj:submit button="true" value="Update" onclick="updateFileGroup();" />
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</s:if>
					<s:else>
						<sj:submit button="true" value="Create" onclick="createFileGroup();" />
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</s:else>
					
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

