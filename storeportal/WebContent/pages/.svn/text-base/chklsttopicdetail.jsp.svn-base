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
		document.forms['ChklstTopicDetail'].action = 'ChklstTopic_search.do';
		document.forms['ChklstTopicDetail'].submit();
	}
	
	function add_post_data(post_str, variable, value) {
        var str = post_str;
        if (str != "")
            str += "&";
        str += variable + "=" + value;
        return str;
    }
    
	
	function doDeleteTopic() {
	
		if(confirm('Are you sure?')){
			document.forms['ChklstTopicDetail'].action = 'ChklstTopic_detaildelete.do';
			document.forms['ChklstTopicDetail'].submit();
		}else{
			return false;
		}
	}
	function doCreateTopic() {
		var validCheck=true;
		var gid=document.getElementById("chklstGroupId").value;
		if(gid==""){
	   	document.getElementById("groupspan").innerHTML = "Select a Group";
	   	validCheck=false;
   		}else{
   		document.getElementById("groupspan").innerHTML = "";
   		}
		
		var tid=document.getElementById("templateId").value;
		if(tid==""){
	   	document.getElementById("tempspan").innerHTML = "Select a Template";
		validCheck=false;
   		}else{
   			
   		document.getElementById("tempspan").innerHTML ="";
   		}
		
		
		var forumName=document.getElementById("chklsttopicname").value;
		if(forumName==""){
		validCheck=false;
	   	document.getElementById("namespan").innerHTML = "Topic Name is empty";
	  	 
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
   		}
		if(validCheck){
		document.forms['ChklstTopicDetail'].action = 'ChklstTopic_detailcreate.do';
		document.forms['ChklstTopicDetail'].submit();
		}
	/*	
        var post_str = "";
        post_str = add_post_data(post_str, 'groupid', $('#groupid').val());
        post_str = add_post_data(post_str, 'chklsttopicname', $('#chklsttopicname').val());
        post_str = add_post_data(post_str, 'chklststatus', $("input[name='chklststatus']:checked").val());
        
        $.ajax({
            type: "POST",
            url: "ChklstTopic_detailcreate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#chklsttopicname").focus();
                	$("#chklsttopicname").select();
                } else {
                	doExit();
                }
            }
        });*/
	}
	
	function doUpdateTopic() {
		
		var validCheck=true;
		var gid=document.getElementById("chklstGroupId").value;
		if(gid==""){
	   	document.getElementById("groupspan").innerHTML = "Select a Group";
	   	validCheck=false;
   		}else{
   		document.getElementById("groupspan").innerHTML = "";
   		}
		
		var tid=document.getElementById("templateId").value;
		if(tid==""){
	   	document.getElementById("tempspan").innerHTML = "Select a Template";
		validCheck=false;
   		}else{
   			
   		document.getElementById("tempspan").innerHTML ="";
   		}
		
		
		var forumName=document.getElementById("chklsttopicname").value;
		if(forumName==""){
		validCheck=false;
	   	document.getElementById("namespan").innerHTML = "Topic Name is empty";
	  	 
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
   		}
		if(validCheck){
		document.forms['ChklstTopicDetail'].action = 'ChklstTopic_detailupdate.do';
		document.forms['ChklstTopicDetail'].submit();
   		}
       /* var post_str = "";
        post_str = add_post_data(post_str, 'groupid', $('#groupid').val());
        post_str = add_post_data(post_str, 'chklsttopicid', $('#chklsttopicid').val());
        post_str = add_post_data(post_str, 'chklsttopicname', $('#chklsttopicname').val());
        post_str = add_post_data(post_str, 'chklststatus', $("input[name='chklststatus']:checked").val());

        $.ajax({
            type: "POST",
            url: "ChklstTopic_detailupdate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#chklsttopicname").focus();
                	$("#chklsttopicname").select();
                } else {
                	doExit();
                }
            }
        });*/
    }

</script>
</head>
<body >
	<s:form id="ChklstTopicDetail" name="ChklstTopicDetail" action="TaskTopicMain_detailupdate" theme="simple" >
			<s:hidden name="chklsttopicid" id="chklsttopicid" value="%{chklsttopicid}" ></s:hidden>
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
								<td align="right"><label for="groupid">Select Group : </label></td>
								<td width="75%">
								<s:select id="chklstGroupId" name="chklstGroupId" list="groupAll" value="groupid"	
										listKey="chklstGroupId" listValue="chklstGroupName" emptyOption="false" style="width:200px"/>
										<span id="groupspan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="groupid">Select Template : </label></td>
								<td>
								<s:select id="templateId" name="templateId" list="templateAll" value="templateId"	
										listKey="templateId" listValue="templateName" emptyOption="false" style="width:200px"/>
										<span id="tempspan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="chklsttopicname">Topic Name : </label></td>
								<td><s:textfield name="chklsttopicname" id="chklsttopicname" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								<span id="namespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="chklststatus">Status : </label></td>
								<td>
									<sj:radio id="chklststatus" name="chklststatus" list="#{'Y':'Active','N':'Inactive'}" value="%{chklststatus}" />
								</td>
							</tr>						
						</table>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" valign="top" >
					<s:if test="%{editmode}">
						
						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdateTopic();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				    </s:if>
					<s:else>
						<sj:a href="#" indicator="indicator" button="true" onclick="doCreateTopic();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
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

