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
		document.forms['ChklstTempDetail'].action = 'ChklstTemplate_search.do';
		document.forms['ChklstTempDetail'].submit();
	}

	

	    
	function doUpdateChklstTemp() {
		
		document.forms['ChklstTempDetail'].action = 'ChklstTemplate_detailupdate.do';
		document.forms['ChklstTempDetail'].submit();
     
	}
	
	

</script>
</head>
<body >
	<s:form id="ChklstTempDetail" name="ChklstTempDetail" action="ChklstTemp_detailupdate" theme="simple" >
			<s:hidden name="templateId" id="templateId" value="%{templateId}" ></s:hidden>
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
								<td align="right"><label for="templateName">Template Name : </label></td>
								<td><s:textfield name="templateName" id="templateName" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								</td>
							</tr>
							
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td align="right"><label for="templateName">Template Headers : </label></td><td></td>
								
							</tr>
							<tr>
								<td align="right" ><label for="templatehead">Header1</label></td>
								<td ><s:textfield name="tempHeaders[0]" id="tempHeaders" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td align="right"><label for="templatehead">Header2</label></td>
								<td ><s:textfield name="tempHeaders[1]" id="tempHeaders" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="templatehead">Header3</label></td>
								<td ><s:textfield name="tempHeaders[2]" id="tempHeaders" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="templatehead">Header4</label></td>
								<td ><s:textfield name="tempHeaders[3]" id="tempHeaders" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="templatehead">Header5</label></td>
								<td ><s:textfield name="tempHeaders[4]" id="tempHeaders" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><label for="templatehead">Header6</label></td>
								<td ><s:textfield name="tempHeaders[5]" id="tempHeaders" maxlength="50" style="width:200px"  onblur="requireValue(this)" />
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
					
						
						<sj:a href="#" indicator="indicator" button="true" onclick="doUpdateChklstTemp();" buttonIcon="ui-icon-disk" >Updatenn</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
				 
					
			  		</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

