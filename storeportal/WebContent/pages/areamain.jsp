<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal -  Area Management</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	function doSearch() {
		document.forms[0].action = 'AreaMain_search.do';
		document.forms[0].submit();
	}

	function deleteArea(val) {
		if(confirm('Are you sure?')){
			document.forms[0].action = 'AreaMain_delete.do?id='+val;
			document.forms[0].submit();
		}else{
			return false;
		}
	}
	
	function newArea() {
		document.forms[0].action = 'AreaDetail_add.do';
		document.forms[0].submit();
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

	function reset() {
		document.forms[0].storeCode.value="";
		document.forms[0].storeName.value="";
		document.forms[0].storeLocation.value="";
	}
	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();">
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
  	<div class="headingBg"><div class="heading" align="center"> <h1>Area Maintenance</h1> </div> </div>
	<s:form action="AreaMain_search" method="post" focus="lgnpwdo" theme="simple">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top" style="padding-top: 20px;">
							<table width="80%" border="0" cellpadding="4" cellspacing="0">
								<tr>
				                	<td align="center"colspan="2" class="errormsg">
				                	<s:actionerror />
									 <s:actionmessage /></td>
				                </tr>
				                <tr>
				                  <td align="center" colspan="2">
				                  		<s:url id="addurl" action="AreaDetail_add.do"/>
										<sj:dialog id="areaDetail" href="%{addurl}" title="Area : New"  autoOpen="false" modal="true" resizable="false" height="330" width="700"/>
	
				                  
				                  		<sj:textfield name="searchTxt" maxlength="30" cssClass="txtBox"  style="width:275px; height:25px;" id="searchTxt"  />&nbsp;&nbsp;&nbsp;&nbsp;
				                  		<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-search"> Search</sj:a>
				                  		<sj:a href="#" indicator="indicator" button="true"  openDialog="areaDetail" buttonIcon="ui-icon-circle-plus"> New</sj:a>
										
				                  </td>
				                 </tr>
				                 <tr><td>&nbsp;</td></tr>
								<tr>
			                  <td align="center" class="boxConTd">
			                  <div class="boxConOuter">
                			  <div class="boxCon">
			                  <display:table  pagesize="20" cellspacing="1" cellpadding="2"  class="dataTable" id="data" name="areaList" requestURI="" >
			                  	<display:column title="S.No" headerClass="c" style="text-align:center;">
						    		<s:label label="%{#attr.data_rowNum}"  value="%{#attr.data_rowNum}"/>
			 		  			</display:column> 
								<display:column title="Action">
			                    	<s:url id="editurl" action="AreaDetail_view.do">
			                    		<s:param name="id" value="%{#attr.data.id}" />
			                    	</s:url>
									<sj:dialog id="AreaDetail_view_%{#attr.data.id}" href="%{editurl}" title="Area : Edit"  autoOpen="false" modal="true" resizable="false"
										 height="350" width="700" />
									<sj:a href="#" indicator="indicator" openDialog="AreaDetail_view_%{#attr.data.id}">Edit</sj:a>|
									<sj:a href="#" indicator="indicator" onclick="deleteArea(%{#attr.data.id})">Del</sj:a>
			                    </display:column> 
			 		  			<display:column property="parentName" title="Area Cd"  />
								<display:column property="mstName" title="Area Name" />
			                    <display:column property="dtlCount" title="Store Count" headerClass="c" style="text-align: center;" />
								
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





