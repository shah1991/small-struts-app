<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - CheckList Management</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	function doSearch() {
		document.forms[0].action = 'ChklstTemplate_search.do';
		document.forms[0].submit();
	}
	
	function goTopics() {
		document.forms[0].action = 'ChklstTopic_search.do';
		document.forms[0].submit();
	}
	
	
	function goGroups() {
		document.forms[0].action = 'Chklst_search.do';
		document.forms[0].submit();
	}
	
	function doDeleteChklstGrp(val) {
		if(confirm('Are you sure?')){
			document.forms[0].action = 'ChklstTemplate_detaildelete.do?templateId='+val;
			document.forms[0].submit();
		}else{
			return false;
		}
	}
	
	function goTemplates() {
		document.forms[0].action = 'ChklstTemplate_search.do';
		document.forms[0].submit();
	}
	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();">
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
   	<div class="headingBg" >
   		<div style="float:left;" class="heading" align="center"> <h1>CheckList Management</h1></div> 
   			<div style="float:right; padding:2px; font-size:12px;  color:#e1e1de;">
	  <sj:a href="#" indicator="indicator" button="true" onclick="goTemplates()" buttonIcon="ui-icon-bookmark">Templates</sj:a>
	  </div>
   </div>
  	
	<s:form action="ChklstTemplate_search" method="post" theme="simple">
		<s:bean name="java.lang.String" var="checkAll" >
		<s:hidden name="tempHeaders" id="tempHeaders"/>
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top" style="padding-top: 20px;">
							<table width="60%" border="0" cellpadding="4" cellspacing="0">
								<tr>
			                	  <td align="center"colspan="2" class="errormsg">
				                	<s:actionerror />
									 <s:actionmessage />
							      </td>
			                    </tr>
				               
				                  <tr><td align="center"> <div style="float:center;" class="heading" align="center"> <h1><s:property  value="templateName"></s:property></h1></div> </td></tr>
				                <tr>
				                  <td align="right" colspan="2">
				                  <s:url id="addurl" action="ChklstTemplate_headeradd.do">
			                    		<s:param name="templateId" value="%{templateId}" />
			                    		
			                    	</s:url>
				                  		<sj:dialog id="ChklstTemplate_headeradd" href="%{addurl}" title="Header: Add"  autoOpen="false" modal="true" resizable="false"
										 height="300" width="700" />
										
									<sj:a href="#" indicator="indicator" openDialog="ChklstTemplate_headeradd" button="true"  buttonIcon="ui-icon-circle-plus">Add Header</sj:a>
								
				                  </td>
				                 </tr>
				               
								<tr>
			                  <td align="left" class="boxConTd">
			                  <div class="boxConOuter">
                			  <div class="boxCon">
			                   <display:table pagesize="20" cellspacing="1" cellpadding="5"  class="dataTable" id="data" name="headersList" requestURI="">
			                   	<display:column title="Action">
			                    	<s:url id="editurl" action="ChklstTemplate_headeredit.do" escapeAmp="false">
			                    		<s:param name="templateId" value="%{templateId}" />
			                    		<s:param name="sno"  value="%{#attr.data_rowNum}"/>
			                    	</s:url>
			                    	
									<sj:dialog id="ChklstTemplate_headeredit_%{#attr.data.id}" href="%{editurl}" title="Template: Edit"  autoOpen="false" modal="true" resizable="false"
										 height="300" width="600" />
									<sj:a href="#" indicator="indicator" openDialog="ChklstTemplate_headeredit_%{#attr.data.id}">Edit</sj:a>
								
			                    </display:column>
			                  	<display:column title="S.No" headerClass="c" style="text-align:center;">
						    		<s:label label="%{#attr.data_rowNum}"  value="%{#attr.data_rowNum}"/>
			 		  			</display:column> 
								
								<display:column  title="Header Name"  property="mstName" style="text-align:left;"  />
								
							</display:table>
			  			 	</div>
			  			 	</div>
				  			 </td></tr>
							</table>
					</td>
				</tr>
			</table>
		</s:form>
  	</div>
    <div class="footerBg">
      <div class="footer">
        <div class="footerLft">2012 &copy; Dairy Farm. All Rights Reserved.</div>
      </div>
    </div>
   </div>
</body>
</html>

