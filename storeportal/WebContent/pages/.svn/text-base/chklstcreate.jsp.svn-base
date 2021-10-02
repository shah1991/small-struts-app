<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="/struts-jquery-richtext-tags" prefix="sjr"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Store Portal - Post Detail</title>
<link href="css/screen.css" type="text/css" rel="stylesheet" />
<link href="css/displaytag.css" media="all" rel="stylesheet" type="text/css" />
<sj:head jquerytheme="redmond" />
 <link rel="shortcut icon" href="styles/images/favico.png" type="image/x-icon"/>
 <link rel="stylesheet" href="styles/style.css" />
 <link rel="stylesheet" href="styles/chat.css" />
   
  <script type="text/javascript" src="scripts/cufon-yui.js"></script>
  <script type="text/javascript" src="scripts/Eurostile_LT_400-Eurostile_LT_700-Eurostile_LT_oblique_400.font.js"></script>
  <script type="text/javascript">
      Cufon.replace('h1', {hover: true});
      Cufon.replace('h2', {hover: true, fontWeight: 700});
      Cufon.replace('h3', {hover: true});
      Cufon.replace('h4', {hover: true});
      Cufon.replace('h5', {hover: true});
      Cufon.replace('h6', {hover: true});
	  Cufon.replace('.top_menu li a', {hover: true});
  </script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>	
<script type="text/javascript">
	
	function doSearch() {
		document.forms[0].action = 'PostMain_search.do';
		document.forms[0].submit();
	}
	
	
	function doCreatePost() {
		alert("Hi");
		
		if(autocheck(document.forms['PostDetail'])){
			alert("inside");
			document.forms[0].action = 'PostDetail_detailadd.do';
			document.forms[0].submit();
		}
	}

	function doUpdatePost() {
		if(autocheck(document.forms['PostDetail'])){
			document.forms[0].action = 'PostDetail_detailupdate.do';
			document.forms[0].submit();
		}
	}

	function doDeletePost() {
		document.forms[0].action = 'PostDetail_delete.do';
		document.forms[0].submit();
	}
</script>

</head>
<body>
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="PostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="id" id="id"/>
	<s:hidden name="topicId" id="topicId"/>
   
   <div class="container_16">
	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <li><?php echo $activity['activity']; ?></li>
            </ul>
        </div>
        <div class="clearfix" /></div>
        
		<h2>Marketing</h2>

        <h3>Add activity under <?php echo $subactivity['subactivity']; ?></h3>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
          <tr>
            <td class="reg_head" width="130"></td>
            <td class="reg_head">New Task</td>
          </tr>
          <tr>
            <td><label for="taskStart">Start Date</label></td>
            <td><sj:datepicker name="taskStart" id="taskStart" cssClass="txtBox" displayFormat="dd-M-yy" buttonImageOnly="true" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;</td>
          </tr>
          <tr>
            <td><label for="taskEnd">End Date</label></td>
            <td><sj:datepicker name="taskEnd" id="taskEnd" cssClass="txtBox" id="scheduleStart" displayFormat="dd-M-yy" buttonImageOnly="true" timepickerFormat="hh:mm" showButtonPanel="true" timepicker="true" maxlength="50" style="width:160px" onblur="requireValue(this)" /> &nbsp;* &nbsp;</td>
           
          </tr>
          <tr>
          	<td><label for="tashName">Subject</label></td>
            <td><s:textfield name="taskName" id="taskName" maxlength="100" style="width:500px" onblur="requireValue(this)" readonly="true"/></td>
          </tr>
          <tr>
            <td colspan="2"><sjr:tinymce 
								id="taskContents" 
								name="taskContents" 
								rows="15" 
								cols="80" 
								width="700"
								editorTheme="advanced"
								toolbarLocation="top"
								toolbarButtonsRow1="cut,copy,paste,pastetext,pasteword,|,bold,italic,underline,strikethrough,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,justifyleft,justifycenter,justifyright,justifyfull,|fontselect,fontsizeselect,|,forecolor,backcolor"
								toolbarButtonsRow2=" "
								toolbarAlign="left"	
								onblur="requireValue(this)"
								/>
            </td>
          </tr>
          <tr>
            <td>Attachment</td>
            <td>
            <s:file name="postAttach" id="postAttach"  style="width: 300px" ></s:file>
			</td>
          </tr>
          <tr>
            <td></td>
            <td>
            <s:if test="%{actUpdate}">
									<sj:a href="#" indicator="indicator" button="true" onclick="doDeletePost();" buttonIcon="ui-icon-trash" >Delete</sj:a>&nbsp;&nbsp;
									<sj:a href="#" indicator="indicator" button="true" onclick="doUpdatePost();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
									<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
							    </s:if>
								<s:else>
									<sj:a href="#" indicator="indicator" button="true" onclick="doCreatePost();" buttonIcon="ui-icon-circle-plus" >Submit-s</sj:a>&nbsp;&nbsp;
									<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
								</s:else>
            </td>
          </tr>
        </table>
        
        
        <p>&nbsp;</p>
        <!-- content end -->
	</div>
	<div class="grid_16">
        <div class="footer">
            Copyright &copy; 2011 7-eleven.
        </div>
	</div>
	<div class="clear"></div>
   </s:form>
</div>
</body>
</html>

