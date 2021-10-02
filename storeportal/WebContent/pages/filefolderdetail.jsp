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

	function doExit() {
		document.forms['FileFolderMain'].action = 'FileFolderMain_search.do';
		document.forms['FileFolderMain'].submit();
	}
	
	function add_post_data(post_str, variable, value) {
        var str = post_str;
        if (str != "")
            str += "&";
        str += variable + "=" + value;
        return str;
    }
    
	function doCreate() {
        var post_str = "";
        post_str = add_post_data(post_str, 'groupId', $('#groupId').val());
        post_str = add_post_data(post_str, 'folderName', $('#folderName').val());
        post_str = add_post_data(post_str, 'folderStatus', $("input[name='folderStatus']:checked").val());
        alert(post_str);
        $.ajax({
            type: "POST",
            url: "FileFolderMain_detailadd.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#folderName").focus();
                	$("#folderName").select();
                } else {
                	doExit();
                }
            }
        });
	}
	
	function doUpdate() {
        var post_str = "";
        post_str = add_post_data(post_str, 'folderId', $('#folderId').val());
        post_str = add_post_data(post_str, 'groupId', $('#groupId').val());
        post_str = add_post_data(post_str, 'folderName', $('#folderName').val());
        post_str = add_post_data(post_str, 'folderStatus', $("input[name='folderStatus']:checked").val());
  
        $.ajax({
            type: "POST",
            url: "FileFolderMain_detailupdate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#folderName").focus();
                	$("#folderName").select();
                } else {
                	doExit();
                }
            }
        });
    }

</script>
</head>
<body >
	<s:form id="FileFolderMain" name="FileFolderMain" action="FileFolderMain_create" theme="simple" >
			<s:hidden name="folderId" id="folderId" value="%{folderId}" ></s:hidden>
    		<div class="div-width" style="background:#f9f9f9; border-bottom:1px solid #ccc; border-right:1px solid #ccc; padding:0 0 0 0;  margin:2px 0 0 0">
				<table width="100%" border="0" cellspacing="5" cellpadding="5">
				<tr>
					<td align="left" valign="top" style="padding-top: 10px;">
						<table width="100%" border="0" cellpadding="4" cellspacing="0">
							<tr>
							<td>
								<span id="desc"></span>
							</td>
							</tr>
							<tr>
								<td align="right"><label for="groupId">Select Group : </label></td>
								<td>
								<s:select id="groupId" name="groupId" list="groupList" value="groupId"	
										listKey="groupId" listValue="groupName" emptyOption="false" style="width:200px"/>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="folderName">Folder Name : </label></td>
								<td><s:textfield name="folderName" id="folderName" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="folderStatus">Status : </label></td>
								<td>
									<sj:radio id="folderStatus" name="folderStatus" list="#{'Y':'Active','N':'Inactive'}" value="%{folderStatus}" />
								</td>
							</tr>						
						</table>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" valign="top" >
					<s:if test="%{actUpdate}">

						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdate();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				    </s:if>
					<s:else>
						<sj:a href="#" indicator="indicator" button="true" onclick="doCreate();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
					</s:else>
			  		</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

