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
		document.forms['TopicDetail'].action = 'TopicMain_search.do';
		document.forms['TopicDetail'].submit();
	}
	
	function add_post_data(post_str, variable, value) {
        var str = post_str;
        if (str != "")
            str += "&";
        str += variable + "=" + value;
        return str;
    }
    
	function doCreateTopic() {
	var topicName=document.getElementById("topicName").value;
		if(topicName==""){
	   	 document.getElementById("namespan").innerHTML = "Topic Name is empty";
	  	 
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
        var post_str = "";
        post_str = add_post_data(post_str, 'forumId', $('#forumId').val());
        post_str = add_post_data(post_str, 'topicName', $('#topicName').val());
        post_str = add_post_data(post_str, 'status', $("input[name='status']:checked").val());
        
        $.ajax({
            type: "POST",
            url: "TopicDetail_detailadd.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#topicName").focus();
                	$("#topicName").select();
                } else {
                	doExit();
                }
            }
        });}
	}
	
	function doUpdateTopic() {
		var topicName=document.getElementById("topicName").value;
		
		if(topicName==""){
	   	 document.getElementById("namespan").innerHTML = "Topic Name is empty";
	  	 
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
        var post_str = "";
        post_str = add_post_data(post_str, 'forumId', $('#forumId').val());
        post_str = add_post_data(post_str, 'topicId', $('#topicId').val());
        post_str = add_post_data(post_str, 'topicName', $('#topicName').val());
        post_str = add_post_data(post_str, 'status', $("input[name='status']:checked").val());

        $.ajax({
            type: "POST",
            url: "TopicDetail_detailupdate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#topicName").focus();
                	$("#topicName").select();
                } else {
                	doExit();
                }
            }
        });}
    }

</script>
</head>
<body >
	<s:form id="TopicDetail" name="TopicDetail" action="TopicMain_detailupdate" theme="simple" >
			<s:hidden name="topicId" id="topicId" value="%{topicId}" ></s:hidden>
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
								<td align="right"><label for="forumId">Select Forum : </label></td>
								<td width="75%">
								<s:select id="forumId" name="forumId" list="forumList" value="forumId"	
										listKey="id" listValue="forumName" emptyOption="false" style="width:200px"/>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="topicName">Topic Name : </label></td>
								<td><s:textfield name="topicName" id="topicName" maxlength="50" style="width:200px"  onblur="requireValue(this)" />&nbsp;<label>*</label> &nbsp;
									<span id="namespan" class="errorspan"> </span>
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
					<s:if test="%{actUpdate}">

						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdateTopic();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				    </s:if>
					<s:else>
						<sj:a href="#" indicator="indicator" button="true" onclick="doCreateTopic();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</s:else>
			  		</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

