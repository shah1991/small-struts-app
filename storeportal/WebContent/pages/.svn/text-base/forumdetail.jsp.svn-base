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
		document.forms['ForumDetail'].action = 'ForumMain_search.do';
		document.forms['ForumDetail'].submit();
	}

	function doDeleteMng() {
		if(confirm('Are you sure?')){
			document.forms['ForumDetail'].action = 'ManageMain_detaildelete.do';
			document.forms['ForumDetail'].submit();
		}else{
			return false;
		}
	}
	
	function doCreateMng() {
		if(autocheck(document.forms['ForumDetail'])){
			document.forms['ForumDetail'].action = 'ManageMain_detailcreate.do';
			document.forms['ForumDetail'].submit();
		}
	}

	function doEditMng() {
		document.forms['ForumDetail'].action = 'ManageMain_detailedit.do';
		document.forms['ForumDetail'].submit();
	}

	function doUpdateMng1() {

		if(autocheck(document.forms['ForumDetail'])){
			document.forms['ForumDetail'].action = 'ManageMain_detailupdate.do';
			document.forms['ForumDetail'].submit();
		}
	}


	function add_post_data(post_str, variable, value) {
        var str = post_str;
        if (str != "")
            str += "&";
        str += variable + "=" + value;
        return str;
    }
    
	function doCreateForum() {
		
		var validCheck=true;
		var forumName=document.getElementById("forumName").value;
		
		if(forumName==""){
	   	document.getElementById("namespan").innerHTML = "Forum Name is empty";
	    document.getElementById("namespan").style.color="#8A0829";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
  	
        var post_str = "";
        post_str = add_post_data(post_str, 'forumName', $('#forumName').val());
        post_str = add_post_data(post_str, 'status', $("input[name='status']:checked").val());
        $.ajax({
            type: "POST",
            url: "ForumDetail_detailadd.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#forumName").focus();
                	$("#forumName").select();
                } else {
                	doExit();
                }
            }
        });
		}
	}
	
	function doUpdateForum() {
		var validCheck=true;
		var forumName=document.getElementById("forumName").value;
		
		if(forumName==""){
	   	document.getElementById("namespan").innerHTML = "Forum Name is empty";
	  	 validCheck=false;
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
        var post_str = "";
        post_str = add_post_data(post_str, 'forumId', $('#forumId').val());
        post_str = add_post_data(post_str, 'forumName', $('#forumName').val());
        post_str = add_post_data(post_str, 'status', $("input[name='status']:checked").val());

        $.ajax({
            type: "POST",
            url: "ForumDetail_detailupdate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#forumName").focus();
                	$("#forumName").select();
                } else {
                	doExit();
                }
            }
        });}
    }
	function retfalse(){
		return false;
	}
</script>
</head>
<body >
	<s:form id="ForumDetail" name="ForumDetail" action="ForumMain_detailupdate" theme="simple" onsubmit="return retfalse();">
			<s:hidden name="forumId" id="forumId" value="%{forumId}" ></s:hidden>
    		<div class="div-width" style="background:#f9f9f9; border-bottom:1px solid #ccc; border-right:1px solid #ccc; padding:0 0 0 0;  margin:2px 0 0 0">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top" style="padding-top: 10px;">
						<table width="100%" border="0" cellpadding="4" cellspacing="0">
							<tr>
							<td colspan="2" align="right">
								<span id="namespan"></span>
							</td>
							</tr>
							<tr>
								<td align="right"><label for="forumName">Forum Name : </label></td>
								<td width="75%"><s:textfield name="forumName" id="forumName" maxlength="50" style="width:200px" /> &nbsp;<label>*</label> &nbsp;
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
					
						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdateForum();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				    </s:if>
					<s:else>
						<sj:a href="#" indicator="indicator" button="true" onclick="doCreateForum();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
					</s:else>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

