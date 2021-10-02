<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Grade detail</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>

<sj:head jquerytheme="eggplant" />
<script type="text/javascript">

	function doSearch() {
		document.forms["GradeDetail"].action = 'GradeMain_search.do';
		document.forms["GradeDetail"].submit();
	}
	
	function createGrade() {
		if(autocheck(document.forms["GradeDetail"])){
			document.forms["GradeDetail"].action = 'GradeMain_create.do';
			document.forms["GradeDetail"].submit();
		}
		
	}




	function updateGrade() {
		if(autocheck(document.forms["GradeDetail"])){
			
			document.forms["GradeDetail"].action = 'GradeMain_update.do';
			document.forms["GradeDetail"].submit();
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
<body onload="document.forms[0].gradeName.focus(); document.forms[0].gradeName.select();" >

  
  	<s:form id="GradeDetail" method="post" theme="simple">
		<s:bean name="java.lang.String" var="checkAll" >
			<s:param name="tag" value="<input type='checkbox' name='allbox' onclick='checkAll(this.form)'  style='margin: 0 0 0 4px; text-align:center;' />"></s:param>
		</s:bean>
		<table align="center" width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td align="right" valign="top" style="padding: 10px;"  class="boxConTd">
				<table border="0" cellspacing="2" cellpadding="2" width="100%">
				<tr>
	                	<td align="center"colspan="2" class="errormsg">
		                	<s:actionerror />
							 <s:actionmessage />
					    </td>
	                </tr>
				<tr>
					<td align="center" valign="top" style="padding-top: 5px;">
					<div class="boxConOuter">
                	<div class="boxCon">
                  	<h2>Grade Details</h2>
                  	<div class="boxConIn">
						<table border="0" cellpadding="2" cellspacing="2" width="100%">
							
							<tr>
								<td align="right"><label for="grade">Grade : </label></td>
								<td><s:textfield name="gradeName" id="gradeName"	maxlength="20" style="width:210px"  onblur="requireValue(this)"/> &nbsp;* &nbsp;
								</td>
							</tr>
							<tr>
								<td align="right"><label for="gradevalue">Grade Value : </label></td>
								
									<td><s:textfield name="gradeValue" id="gradeValue"	maxlength="20" style="width:210px"  onblur="requireValue(this)"/> &nbsp;* &nbsp;</td>
								
							</tr>								
							
						</table>
						</div></div></div>
					</td>
				</tr>
				
				<tr>
					<td align="center" valign="middle"  style="padding-top: 5px;">
					<s:if test="%{editmode}">
						<s:hidden name="gradeId"/>
						<sj:submit button="true" value="Update" onclick="updateGrade();" />
						<sj:submit button="true" value="Cancel" onclick="doSearch();" />
					</s:if>
					<s:else>
						<sj:submit button="true" value="Create" onclick="createGrade();" />
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

