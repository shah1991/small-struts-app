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
	
	function doFetch() {
		document.forms['TaskDetail'].action = 'PostDetail_areastore.do';
		document.forms['TaskDetail'].submit();
	}
	
	
	function doExit() {
		document.forms['TaskDetail'].action = 'PostDetail_detailareacreate.do';
		document.forms['TaskDetail'].submit();
	}
	
	
</script>
</head>
<body >
	<s:form id="TaskDetail" name="TaskDetail" action="TaskPostDetail_detailstore" theme="simple" >
			<s:hidden name="recType" id="recType" value="S" ></s:hidden>
			<s:hidden name="topicId" id="topicId"/>
    		<div class="div-width" style="background:#f9f9f9; border-bottom:1px solid #ccc;  padding:0 0 0 0;  margin:2px 0 0 0">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top" style="padding-top: 10px;">
					<p style="height:100px; overflow: auto; border: 1px ;border-color:#104E8B;border-style:solid; border-radius: 5px;background-color:#FAFEFE;color:#000000;  margin-bottom: 1.5em;width:500px;padding-left: 10px;">
                       		&nbsp;&nbsp;<b>Store Manager</b>  <br/><br/>
                    		<s:iterator value="storeList">		
                    		&nbsp;&nbsp;<s:checkbox name="slist" id="slist" fieldValue="%{storeId}"></s:checkbox>  <s:property value="storeName"></s:property><br/><br/>
                    		</s:iterator>
                    		 
                   	</p>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" valign="top" >
					
						<sj:a href="#" indicator="indicator" button="true" onclick="doFetch();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
					
			  		</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

