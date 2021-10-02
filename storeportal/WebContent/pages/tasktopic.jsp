<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Topic Management</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	function doSearch() {
		document.forms[0].action = 'TaskTopicMain_search.do';
		document.forms[0].submit();
	}

	
	function goTasks() {
		document.forms[0].action = 'TaskMain_search.do';
		document.forms[0].submit();
	}
	
	
	function goPosts() {
		alert("post");
		document.forms[0].action = 'TaskPostMain_search.do';
		document.forms[0].submit();
	}
	
	function doDeleteTopic(val) {
		if(confirm('Are you sure?')){
			document.forms[0].action = 'TaskTopicMain_detaildelete.do?topicId='+val;
			document.forms[0].submit();
		}else{
			return false;
		}
	}
	
	function checkAll(theForm) { // check all the checkboxes in the list
	  for (var i=0;i<theForm.elements.length;i++) {
	    var e = theForm.elements[i];
			var eName = e.name;
	    	if (eName != 'allbox' && 
	            (e.type.indexOf("checkbox") == 0)) {
	        	e.checked = theForm.allbox.checked;		
			}
		} 
	}
	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();">
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
   	<div class="headingBg" >
   		<div style="float:left;" class="heading" align="center"> <h1>Topics Management</h1></div> 
	  	<div style="float:right; padding:2px; font-size:12px;  color:#e1e1de;">
			<s:url id="addurl" action="TaskTopicMain_detailadd.do"/>
			<sj:dialog id="TaskTopicDetail" href="%{addurl}" title="Topic : New"  autoOpen="false" modal="true" resizable="false" height="265" width="420"/>
	  		<sj:a href="#" indicator="indicator" button="true" openDialog="TaskTopicDetail" buttonIcon="ui-icon-circle-plus">New Topic</sj:a>
	  		<sj:a href="#" indicator="indicator" button="true" onclick="goTasks()" buttonIcon="ui-icon-bookmark">Task</sj:a>
	  	</div> 
   </div>
  	
	<s:form action="TaskTopicMain_search" method="post" theme="simple">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top" style="padding-top: 20px;">
							<table width="60%" border="0" cellpadding="4" cellspacing="0">
								<tr>
				                	<td align="right" colspan="2">
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
			                   <display:table pagesize="20" cellspacing="1" cellpadding="5"  class="dataTable" id="data" name="topicList" requestURI="">
			                  	<display:column title="S.No" headerClass="c" style="text-align:center;">
						    		<s:label label="%{#attr.data_rowNum}"  value="%{#attr.data_rowNum}"/>
			 		  			</display:column> 
								<display:column title="Action">
			                    	<s:url id="editurl" action="TaskTopicMain_detailedit.do">
			                    		<s:param name="topicId" value="%{#attr.data.id}" />
			                    	</s:url>
									<sj:dialog id="TopicDetail_update_%{#attr.data.id}" href="%{editurl}" title="Topic : Edit"  autoOpen="false" modal="true" resizable="false"
										 height="265" width="420" />
									<sj:a href="#" indicator="indicator" openDialog="TopicDetail_update_%{#attr.data.id}">Edit</sj:a>|
									<sj:a href="#" indicator="indicator" onclick="doDeleteTopic(%{#attr.data.id})">Del</sj:a>
			                    </display:column>
			                    <display:column property="parentName" title="Task" style="text-align:left;" />
								<display:column property="mstName" title="Topic" paramId="topicId" paramProperty="id" href="./PostMain_search.do" style="text-align:left;" />
								<display:column property="dtlCount" title="Posts" headerClass="c" style="text-align:center;"/>
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

