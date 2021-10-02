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
		document.forms['ChklstTempDetail'].action = 'ChklstTemplate_searchhead.do';
		document.forms['ChklstTempDetail'].submit();
	}

	

	function add_post_data(post_str, variable, value) {
        var str = post_str;
        if (str != "")
            str += "&";
        str += variable + "=" + value;
        return str;
    }
    
	function doCreateChklstTemp() {
		var tempName=document.getElementById("templateHeaders").value;
		if(tempName==""){
		
	   	document.getElementById("namespan").innerHTML = "Template Header is empty";
	  	 
   		}else{
		document.forms['ChklstTempDetail'].action = 'ChklstTemplate_createheader.do';
		document.forms['ChklstTempDetail'].submit();
   		}
     
	}
	
	function doUpdateChklstTemp() {
		var tempName=document.getElementById("templateHeaders").value;
		if(tempName==""){
		
	   	document.getElementById("namespan").innerHTML = "Template Header is empty";
	  	 
   		}else{
		document.forms['ChklstTempDetail'].action = 'ChklstTemplate_headerupdate.do';
		document.forms['ChklstTempDetail'].submit();
   		}
    }

</script>
</head>
<body >
	<s:form id="ChklstTempDetail" name="ChklstTempDetail" action="ChklstTemp_detailupdate" theme="simple" >
			<s:hidden name="templateId" id="templateId" value="%{templateId}" ></s:hidden>
			<s:hidden name="sno" id="sno" value="%{sno}" ></s:hidden>
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
								<td align="right"><label for="templateName">Header Name : </label></td>
								<td width="75%"><s:textfield name="templateHeaders" id="templateHeaders" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								<span id="namespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>	
							
							
							
									
						</table>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" valign="top" >
					<s:if test="%{actUpdate}">
						
						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdateChklstTemp();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				    </s:if>
					<s:else>
						<sj:a href="#" indicator="indicator" button="true" onclick="doCreateChklstTemp();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
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

