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
		document.forms['ChklstGrpDetail'].action = 'ChklstGroup_search.do';
		document.forms['ChklstGrpDetail'].submit();
	}

	function doDeleteChklstGrp() {
		if(confirm('Are you sure?')){
			document.forms['ChklstGrpDetail'].action = 'ChklstGroup_detaildelete.do';
			document.forms['ChklstGrpDetail'].submit();
		}else{
			return false;
		}
	}

	function add_post_data(post_str, variable, value) {
        var str = post_str;
        if (str != "")
            str += "&";
        str += variable + "=" + value;
        return str;
    }
    
	function doCreateChklstGrp() {
	var forumName=document.getElementById("chklstgrpname").value;
		if(forumName==""){
	   	document.getElementById("namespan").innerHTML = "Group Name is empty";
	  	
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
        var post_str = "";
        post_str = add_post_data(post_str, 'chklstgrpname', $('#chklstgrpname').val());
        post_str = add_post_data(post_str, 'chklststatus', $("input[name='chklststatus']:checked").val());
        $.ajax({
            type: "POST",
            url: "ChklstGroup_detailcreate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#chklstgrpname").focus();
                	$("#chklstgrpname").select();
                } else {
                	doExit();
                }
            }
        });}
	}
	
	function doUpdateChklstGrp() {
		var forumName=document.getElementById("chklstgrpname").value;
		if(forumName==""){
	   	document.getElementById("namespan").innerHTML = "Group Name is empty";
	  	
   		}else{
    	 document.getElementById("namespan").innerHTML ="";
        var post_str = "";
        post_str = add_post_data(post_str, 'chklstgrpid', $('#chklstgrpid').val());
        post_str = add_post_data(post_str, 'chklstgrpname', $('#chklstgrpname').val());
        post_str = add_post_data(post_str, 'chklststatus', $("input[name='chklststatus']:checked").val());

        $.ajax({
            type: "POST",
            url: "ChklstGroup_detailupdate.do",
            data: post_str,
            success: function(msg){
                if (msg.substr(0,10) == "###Fail###") {
                	alert(msg.substr(10));
                	$("#chklstgrpname").focus();
                } else {
                	$("#chklstgrpname").select();
                	doExit();
                }
            }
        });}
    }

</script>
</head>
<body >
	<s:form id="ChklstGrpDetail" name="ChklstGrpDetail" action="ChklstGroup_detailupdate" theme="simple" >
			<s:hidden name="chklstgrpid" id="chklstgrpid" value="%{chklstgrpid}" ></s:hidden>
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
								<td align="right"><label for="chklstgrpname">Group Name : </label></td>
								<td width="75%"><s:textfield name="chklstgrpname" id="chklstgrpname" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
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
						
						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdateChklstGrp();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				    </s:if>
					<s:else>
						<sj:a href="#" indicator="indicator" button="true" onclick="doCreateChklstGrp();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
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

