<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Area detail</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>

<sj:head jquerytheme="eggplant" />
<script type="text/javascript">

	function doSearch() {
		
		document.forms["DeptDetail"].action ='Department_search.do';
		document.forms["DeptDetail"].submit();
	}
	
	function createDept() {
		

	    var validCheck=true;
		var deptcd=document.getElementById("deptcd").value;
		if(deptcd==""){
			   document.getElementById("deptcdspan").innerHTML = "Department code is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("deptcdspan").innerHTML ="";
	    }
		
		var deptname=document.getElementById("deptname").value;
		if(deptname==""){
			   document.getElementById("deptnamespan").innerHTML = "Department code is empty";
			   validCheck=false;
		    }else {
		    	document.getElementById("deptnamespan").innerHTML ="";
	    }
		
		var deptmngrid=document.getElementById("deptmngrid").value;
		if(deptmngrid==""){
			   document.getElementById("deptmngspan").innerHTML = "Select a Department Manager";
			   validCheck=false;
		    }else {
		    	document.getElementById("deptmngspan").innerHTML ="";
	    }
		
		var postfid=document.getElementById("postfid").value;
		if(postfid==""){
			   document.getElementById("forumspan").innerHTML = "Select a Forum";
			   validCheck=false;
		    }else {
		    	document.getElementById("forumspan").innerHTML ="";
	    }
		
		var taskpid=document.getElementById("taskpid").value;
		if(taskpid==""){
			   document.getElementById("taskspan").innerHTML = "Select a Task";
			   validCheck=false;
		    }else {
		    	document.getElementById("taskspan").innerHTML ="";
	    }
		
		var chklstgid=document.getElementById("chklstgid").value;
		if(chklstgid==""){
			   document.getElementById("checkspan").innerHTML = "Select a Checklist";
			   validCheck=false;
		    }else {
		    	document.getElementById("checkspan").innerHTML ="";
	    }
	
		if(validCheck){
			document.forms["DeptDetail"].action = 'Department_create.do';
			document.forms["DeptDetail"].submit();
		
		}
	}

	function updateDept() {
		if(autocheck(document.forms['DeptDetail'])){
			document.forms["DeptDetail"].action = 'Department_update.do';
			document.forms["DeptDetail"].submit();
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
	function retfalse(){
		return false;
	}
	
</script>
</head>
<body onload="document.forms[0].deptcd.focus(); document.forms[0].deptcd.select();" >

	<s:form id="DeptDetail" method="post" theme="simple" onsubmit="return retfalse();">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="center" valign="top" style="padding: 10px;"  class="boxConTd">
				<table border="0" cellspacing="2" cellpadding="2" width="100%">
				<tr>
					<td align="center" class="errormsg"> 
						<s:actionerror />
						 <s:actionmessage />
						 <s:fielderror />
					</td>
				</tr>
				<tr>
					<td align="left" valign="top" style="padding-top: 5px;"  >
					<div class="boxConOuter">
                	<div class="boxCon">
                  	<h2>Department Details</h2>
                  	<div class="boxConIn">
						<table border="0" cellpadding="2" cellspacing="2">
							<tr>
								<td align="right"><label for="deptcd">Department Code : </label></td>
								<td width="70%"><s:textfield name="deptcd" id="deptcd"	maxlength="20" style="width:60px"  /> <label>&nbsp;* &nbsp;</label>
								<span id="deptcdspan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="deptname">Department Name : </label></td>
								<td><s:textfield name="deptname" id="deptname"	maxlength="20" style="width:210px"  onblur="requireValue(this)"/> <label>&nbsp;* &nbsp;</label>
								<span id="deptnamespan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="deptmngrid">Department Manager: </label></td>
								<td>
									<s:select name="deptmngrid" id="deptmngrid" list="deptManagers" listKey="userId"  listValue="userName" style="width:160px"  onblur="requireValue(this)" /><label>&nbsp;* &nbsp;</label>
									<span id="deptmngspan" class="errorspan"> </span>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="postfid">Post Forum : </label></td>
								<td>
									<s:select name="postfid" id="postfid" list="postForums" listKey="id"  listValue="forumName" style="width:160px" headerKey="" headerValue="--- select ---"/><label>&nbsp;* &nbsp;</label>
									<span id="forumspan" class="errorspan"> </span>
								</td>
							</tr>									
							<tr>
								<td align="right"><label for="taskpid">Task Portfolio : </label></td>
								<td>
									<s:select name="taskpid" id="taskpid" list="taskPortfolios" listKey="portfolioId"  listValue="portfolioName" style="width:160px"  headerKey="" headerValue="--- select ---" /><label>&nbsp;* &nbsp;</label>
									<span id="taskspan" class="errorspan"> </span>
								</td>
							</tr>									
							<tr>
								<td align="right"><label for="chklstgid">Checklist Group : </label></td>
								<td>
									<s:select name="chklstgid" id="chklstgid" list="chklstGroups" listKey="chklstGroupId"  listValue="chklstGroupName" style="width:160px" headerKey="" headerValue="--- select ---"  /><label>&nbsp;* &nbsp;</label>
									<span id="checkspan" class="errorspan"> </span>
								</td>
							</tr>									
							<tr>
								<td align="right"><label for="deptstatus">Active : </label></td>
								<td>
									<sj:radio name="deptstatus" list="#{'Y':'Active','N':'Inactive'}" value="deptstatus" />
								</td>
							</tr>	
						</table>
						</div></div></div>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle"  style="padding-top: 5px;">
					<s:if test="%{editmode}">
						<s:hidden name="deptid"/>
						<sj:submit button="true" value="Update" onclick="updateDept();" />
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</s:if>
					<s:else>
						<sj:submit button="true" value="Create" onclick="createDept();" />
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</s:else>
					
					</td>
					
					
				</tr>	
			</table>
			</td>
			</tr>
		</table>
		</s:form>
  
</body>
</html>

