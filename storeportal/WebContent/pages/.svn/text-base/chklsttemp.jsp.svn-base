<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<sj:head jquerytheme="redmond"  jqueryui="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>        
<script type="text/javascript">
	
	function doFetch() {
		
		document.forms['ChklstDetail'].action = 'ChklstPostDetail_detailtemp.do';
		document.forms['ChklstDetail'].submit();
	}
	
	function doExit(){
		document.forms['ChklstDetail'].action = 'ChklstPostDetail_hqcreate.do';
		document.forms['ChklstDetail'].submit();
	}
	
</script>
</head>
<body >
	<s:form id="ChklstDetail" name="ChklstDetail" action="ChklstPostDetail" theme="simple" >
			<s:hidden name="tempHeader" id="tempHeader"/>
		
			
			<s:hidden name="sessCount"></s:hidden>
			<s:hidden name="slist" id="slist"/>
    		<div class="div-width" style="background:#f9f9f9; border-bottom:1px solid #ccc;  padding:0 0 0 0;  margin:2px 0 0 0">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				 <tr>
                  <td colspan="2"><table><tr><s:iterator status="header" value="header"><td style="width: 100px;color:#000000"><s:property/>&nbsp;</td> </s:iterator>   </tr></table>        
				 </td>
         	  </tr>
          
 			 <tr>
                  <td colspan="2"><table><tr><s:iterator status="header" value="header"><td><s:textfield name="headercol"  style="width: 100px"></s:textfield>&nbsp;</td> </s:iterator>   </tr></table>        
			</td>
          </tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" valign="top" >
					
						<sj:a href="#" indicator="indicator" button="true" onclick="doFetch();" buttonIcon="ui-icon-circle-plus" >Create</sj:a>&nbsp;&nbsp;
						
					
			  		</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
		</div>
	</s:form>
  </body>
</html>

