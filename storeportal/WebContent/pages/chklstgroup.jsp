<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Checklist Management</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	function doSearch() {
		document.forms[0].action = 'ChklstGroup_search.do';
		document.forms[0].submit();
	}
	
	function goTopics() {
		document.forms[0].action = 'ChklstTopic_search.do';
		document.forms[0].submit();
	}
	
	
	function goTemplates() {
		document.forms[0].action = 'ChklstTemplate_search.do';
		document.forms[0].submit();
	}
	
	function doDeleteChklstGrp(val) {
		if(confirm('Are you sure?')){
			document.forms[0].action = 'ChklstGroup_detaildelete.do?chklstgrpid='+val;
			document.forms[0].submit();
		}else{
			return false;
		}
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
			<s:url id="addurl" action="ChklstGroup_detailadd.do"/>
			<sj:dialog id="ChklstGrpDetail" href="%{addurl}" title="Group : New"  autoOpen="false" modal="true" resizable="false" height="230" width="700"/>
	  		<sj:a href="#" indicator="indicator" button="true" openDialog="ChklstGrpDetail" buttonIcon="ui-icon-circle-plus">New Group</sj:a>
	  		<sj:a href="#" indicator="indicator" button="true" onclick="goTopics()" buttonIcon="ui-icon-note">Topics</sj:a>
	  		<sj:a href="#" indicator="indicator" button="true" onclick="goTemplates()" buttonIcon="ui-icon-bookmark">Templates</sj:a>
	  	</div> 
   </div>
  	
	<s:form action="ChklstGroup_search" method="post" theme="simple">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top" style="padding-top: 20px;">
							<table width="60%" border="0" cellpadding="4" cellspacing="0">
								<tr>
				                	<td align="center" colspan="2" class="errormsg">
				                	<s:actionerror />
									 <s:actionmessage /></td>
				                </tr>
				                <tr>
				                  <td align="center" colspan="2">
				                  		<sj:textfield name="searchTxt" maxlength="30" cssClass="txtBox"  style="width:400px; height:25px;" id="searchTxt"  />&nbsp;&nbsp;&nbsp;&nbsp;
				                  		<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-search"> Search</sj:a>
				                  </td>
				                 </tr>
				                 <tr><td>&nbsp;</td></tr>
								<tr>
			                  <td align="center" class="boxConTd">
			                  <div class="boxConOuter">
                			  <div class="boxCon">
			                   <display:table pagesize="20" cellspacing="1" cellpadding="5"  class="dataTable" id="data" name="groupList" requestURI="">
			                  	<display:column title="S.No" headerClass="c" style="text-align:center;">
						    		<s:label label="%{#attr.data_rowNum}"  value="%{#attr.data_rowNum}"/>
			 		  			</display:column> 
								<display:column title="Action" headerClass="c" style="text-align:center;">
			                    	<s:url id="editurl" action="ChklstGroup_detailedit.do">
			                    		<s:param name="chklstgrpid" value="%{#attr.data.id}" />
			                    	</s:url>
									<sj:dialog id="ChklstGrpDetail_update_%{#attr.data.id}" href="%{editurl}" title="Group: Edit"  autoOpen="false" modal="true" resizable="false"
										 height="230" width="700" />
									<sj:a href="#" indicator="indicator" openDialog="ChklstGrpDetail_update_%{#attr.data.id}">Edit</sj:a>|
									<sj:a href="#" indicator="indicator" onclick="doDeleteChklstGrp(%{#attr.data.id})">Del</sj:a>
			                    </display:column>
			                    <display:column property="id" title="Group ID" paramId="id" headerClass="c" style="text-align:center;" />
								<display:column property="mstName" title="Group Name" paramId="id" style="text-align:left;" />
								<display:column property="dtlCount" title="Topics" headerClass="c" style="text-align:center;"/>
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

