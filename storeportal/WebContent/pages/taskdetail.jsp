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
	function doCreateTask() {
		var topicName=document.getElementById("portfolioName").value;
		if(topicName==""){
	   	 document.getElementById("namespan").innerHTML = "Portfolio Name is empty";
	  	 
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
        var post_str = "";
        post_str = add_post_data(post_str, 'portfolioName', $('#portfolioName').val());
        post_str = add_post_data(post_str, 'status', $("input[name='status']:checked").val());
        $.ajax({
            type: "POST",
            url: "TaskDetail_detailadd.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#portfolioName").focus();
                	$("#portfolioName").select();
                } else {
                	doExit();
                }
            }
        });}
	}
	function add_post_data(post_str, variable, value) {
        var str = post_str;
        if (str != "")
            str += "&";
        str += variable + "=" + value;
        return str;
    }
	function doExit() {
		document.forms['TaskDetail'].action = 'TaskMain_search.do';
		document.forms['TaskDetail'].submit();
	}

	function doDeleteMng() {
		if(confirm('Are you sure?')){
			document.forms['TaskDetail'].action = 'ManageMain_detaildelete.do';
			document.forms['TaskDetail'].submit();
		}else{
			return false;
		}
	}
	function doUpdateTask() {
		var topicName=document.getElementById("portfolioName").value;
		if(topicName==""){
	   	 document.getElementById("namespan").innerHTML = "Portfolio Name is empty";
	  	 
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
        var post_str = "";
        post_str = add_post_data(post_str, 'portfolioId', $('#portfolioId').val());
        post_str = add_post_data(post_str, 'portfolioName', $('#portfolioName').val());
        post_str = add_post_data(post_str, 'status', $("input[name='status']:checked").val());

        $.ajax({
            type: "POST",
            url: "TaskDetail_detailupdate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#portfolioName").focus();
                	$("#portfolioName").select();
                } else {
                	doExit();
                }
            }
        });}
    }
	

</script>
</head>
<body >
	<s:form id="TaskDetail" name="TaskDetail" action="TaskMain_detailupdate" theme="simple" >
			<s:hidden name="portfolioId" id="portfolioId" value="%{portfolioId}" ></s:hidden>
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
								<td align="right"><label for="portfolioName">Portfolio Name : </label></td>
								<td width="75%"><s:textfield name="portfolioName" id="portfolioName" maxlength="50" style="width:200px"  /> &nbsp;<label>*</label> &nbsp;
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
						
						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdateTask();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				    </s:if>
					<s:else>
						<sj:a href="#" indicator="indicator" button="true" onclick="doCreateTask();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
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

