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
		
		
		document.forms['TaskDetail'].action = 'TaskPostDetail_detailstore.do';
		document.forms['TaskDetail'].submit();
	}
	

	

	
</script>
</head>
<body >
	<s:form id="TaskDetail" name="TaskDetail" action="TaskPostDetail_detailarea" theme="simple" >
			<s:hidden name="recType" id="recType" value="A" ></s:hidden>
			<s:hidden name="portfolioId" id="portfolioId"/>
    		<div class="div-width" style="border-bottom:1px solid #ccc;  padding:0 0 0 0;  margin:2px 0 0 0;">
				<table width="80%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td class="boxConTd">
						<div class="boxConOuter">
                			  <div class="boxCon">
                       	 	    <display:table  pagesize="20" cellspacing="1" cellpadding="2"  class="dataTable" id="data" name="storeGrpList" requestURI="" >
			                  	<display:column title="S.No" headerClass="c" style="text-align:center;width:80px;">
						    		<s:label label="%{#attr.data_rowNum}"  value="%{#attr.data_rowNum}"/>
			 		  			</display:column> 
			 		  			<display:column  title="Select" style="text-align:center;color:black;width:80px;">
			 		  				<s:checkbox name="slist" id="slist" fieldValue="%{data.storeId}" ></s:checkbox>
			 		  			</display:column>
			 		  			<display:column property="areaName" title="Area Name" style="color:black"></display:column>
			 		  				
			 		  			</display:table>
			 		  		</div>
			 		  	</div>
			 		  			
			 		  			
                    		
                    		 
                   	
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" valign="top" >
					
						<sj:a href="#" indicator="indicator" button="true" onclick="doFetch();" buttonIcon="ui-icon-circle-plus" >Select</sj:a>&nbsp;&nbsp;
						<sj:a href="#" indicator="indicator" button="true" onclick="doExit();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
					
			  		</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

