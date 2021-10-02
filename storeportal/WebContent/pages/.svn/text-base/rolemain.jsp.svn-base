<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Role Management</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	function doSearch() {
		document.forms[0].action = 'RoleMain_search.do';
		document.forms[0].submit();
	}

	function deleteRole() {
		var theForm = document.forms[0];
		
		var atLeastOneSelected = false;

		 for (var i=0;i<theForm.elements.length;i++) {
			    var e = theForm.elements[i];
				var eName = e.name;
		    	if (eName == 'deleteList' && (e.type.indexOf("checkbox") == 0)) {
		        	if(e.checked == true) {
		        		atLeastOneSelected = true;
		        		break;
			        }
				}
			} 
		if(!atLeastOneSelected) {
			alert("Select atleast one Role to delete");
			return false;
		}
		
		if(confirm('Are you sure?')){
			alert("delete role");
			document.forms[0].action = 'RoleMain_delete.do';
			document.forms[0].submit();
		}else{
			return false;
		}
	}
	
	function newRole() {
		document.forms[0].action = 'RoleDetail_add.do';
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
	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();" >
<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
  	<div class="headingBg"><div class="heading" align="center"> <h1>Portal Role List</h1> </div> </div>
	<s:form action="RoleMain_search" method="post" focus="searchTxt" theme="simple">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top" style="padding: 20px;">
							<table width="60%" border="0" cellpadding="2" cellspacing="0">
							<tr>
			                	<td align="center"colspan="2" class="errormsg">
				                	<s:actionerror />
									 <s:actionmessage />
							    </td>
			                </tr>
			                <tr>
			                  <td align="center" colspan="2">
			                  		<sj:textfield name="searchTxt" maxlength="30" cssClass="txtBox"  style="width:270px; height:25px;" id="searchTxt"  />&nbsp;&nbsp;&nbsp;&nbsp;
			                  		<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-search"> Search</sj:a>
			                  		<sj:a href="#" indicator="indicator" button="true" onclick="newRole();" buttonIcon="ui-icon-circle-plus"> New</sj:a>
									<sj:a href="#" indicator="indicator" button="true" onclick="deleteRole();" buttonIcon="ui-icon-trash"> Delete</sj:a>
			                  		
			                  </td>
			                 </tr>
							<tr>
			                  <td align="left" class="boxConTd" style="padding: 10px;">
			                  <div class="boxConOuter">
                			  <div class="boxCon">
			                  <display:table pagesize="20" cellspacing="1" cellpadding="5"  class="dataTable" id="data" name="roleList" requestURI="" >
				                  	<display:column title="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 0px; text-align:left;' />">
							    		<s:checkbox name="deleteList" id="deleteList" fieldValue="%{#attr.data.id}" style="margin: 0 8 0 6px; text-align:center;"/>
				 		  			</display:column> 
				 		  			<display:column property="id" title="Role Id"  style='text-align:center;'></display:column>
				 		  			<display:column property="parentName" title="Role Code" paramId="id" paramProperty="id" href="./RoleDetail_view.do"  />
									<display:column property="mstName" title="Role Name" />
				                    <display:column property="dtlCount" title="User Count" headerClass="c" style="text-align: center;" />
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

