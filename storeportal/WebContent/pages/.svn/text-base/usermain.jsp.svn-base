<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - User Management</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="eggplant" />
<script type="text/javascript">
	function doSearch() {
		document.forms[0].action = 'UserMain_search.do';
		document.forms[0].submit();
	}

	function deleteUser() {
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
			alert("Select atleast one User to delete");
			return false;
		}
		
		if(confirm('Are you sure?')){
			document.forms[0].action = 'UserMain_delete.do';
			document.forms[0].submit();
		}else{
			alert("test");
			return false;
		}
	}
	
	function newUser() {
		document.forms[0].action = 'UserDetail_add.do';
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

	function goExport(){
		document.forms[0].action = 'UserMain_exportUserListExcel.do';
		document.forms[0].submit();
	}
	

	
</script>
</head>
<body onload="document.forms[0].searchTxt.focus(); document.forms[0].searchTxt.select();" >

<div class="wrapper">
<jsp:include page="/include/header.jsp"/>  
   <div class="main">
  	<div class="headingBg"><div class="heading" align="center"> <h1>Portal User List</h1> </div> </div>
	<s:form action="UserMain_search" method="post" focus="searchTxt" theme="simple">
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
			                  		<sj:textfield name="searchTxt" maxlength="30" cssClass="txtBox"  style="width:275px; height:25px;" id="searchTxt"  />&nbsp;&nbsp;&nbsp;&nbsp;
			                  		<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-search"> Search</sj:a>
			                  		<sj:a href="#" indicator="indicator" button="true" onclick="newUser();" buttonIcon="ui-icon-circle-plus"> New</sj:a>
									<sj:a href="#" indicator="indicator" button="true" onclick="deleteUser();" buttonIcon="ui-icon-trash">Delete</sj:a>									
			                  </td>
			                 </tr>
							<tr>
			                  <td align="left" class="boxConTd" style="padding: 10px;">
			                  <div class="boxConOuter">
                			  <div class="boxCon">
			                  <display:table pagesize="20" cellspacing="1" cellpadding="5"  class="dataTable" id="data" name="userList" requestURI="" >
			                  	<display:column title="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 0px; text-align:left;' />">
						    		<s:checkbox name="deleteList" id="deleteList" fieldValue="%{#attr.data.userId}" style="margin: 0 8 0 6px; text-align:center;"/>
			 		  			</display:column> 
								<display:column property="userLogin" title="Login Id" paramId="id" paramProperty="userId" href="./UserDetail_view.do"  />
			                    <display:column property="userName" title="User Name" />
			                     <display:column property="tblRole.roleName" title="Role" />
			                     <display:column property="franchiseeExpiryDate" title="Franchisee Expiry On" />		
								<%--<display:column property="tblStore.storeCode" title="Store Code" />
								<display:column property="tblStore.storeShortName" title="Store Name" />
				  			 --%></display:table>
				  			 	</div>
				  			 	</div>
				  			 </td>				  			 
				  			 </tr>
							</table>
							<table>
							<tr>
								<td>
									<sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-document" onclick="goExport()">Print</sj:a>
								</td>
							</tr>
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

