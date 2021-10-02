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
<sj:head jquerytheme="south-street" />
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
	
function goHome() {
	document.forms[0].action = 'Login_home.do';
	document.forms[0].submit();
}

function goBack()
  {
  window.history.back();
  }
  
function goExport(){
	document.forms[0].action = 'ChklstPostDetail_exportExcel.do';
	document.forms[0].submit();
}

function updateresponsesm() {
	document.forms[0].action = 'ChklstPostDetail_updateresponsesm.do';
	document.forms[0].submit();
		
	}
	
</script>
<style type="text/css">
label{
font-weight: bold;
font-size: 17x;

}

.tdborder{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 150px;height: 5px;
}
.tdres{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 180px;
}

.tdresult{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 100px;font-size: 10px;
}


.tdchoice{
	border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
	width: 200px;font-size: 10px;
}
</style>
</head>
<body >
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="ChklstPostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="id" id="id"/>
	<s:hidden name="topicId" id="topicId"/>
	<s:hidden name="chklstStoreId" id="chklstStoreId"/>
	<s:hidden name="chklstId" id="chklstId"/>
	<s:hidden name="chklstContents"/>
   <div class="container_16">
	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <li><?php echo $activity['activity']; ?></li>
                <li><a href="ChklstPostMain_topic.do?chklstGroupId=${chklstGroupId }"> ${topicName}</a> &raquo;</li>
                 <li><a href="ChklstPostMain_entry.do?topicId=${topicId }">Check List</a> &raquo;</li>
            </ul>
        </div>
        <div class="clearfix" /></div>
        
        <h3>View CheckList <?php echo $subactivity['subactivity']; ?></h3>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
        
          <tr>
            <td class="reg_head" width="130"></td>
            <td class="reg_head">CheckList Details : &nbsp;<s:property value="chklstSubject" /></td>
          </tr>
          <tr>
            <td><label for="startDate">Start Date</label></td>
            <td> <s:property value="chklstStartDate"></s:property></td>
          </tr>
          <tr>
            <td><label for="endDate">End Date</label></td>
            <td><s:property value="chklstEndDate"></s:property></td>
           
          </tr>
          <tr>
          	<td><label for="chklstName">Subject</label></td>
            <td> <s:property value="chklstSubject"></s:property></td>
                  
          </tr>

          <tr>
           <td><label for="chklstContents">Content</label></td> <td>
        			  <sjr:tinymce 
								id="chklstContents" 
								name="chklstContents" 
								rows="5" 
								cols="80" 
								width="790"
								readonly="true"
								toolbarButtonsRow1=" "
								toolbarButtonsRow4=" "
								toolbarButtonsRow3=" "
								toolbarButtonsRow2=" "
								toolbarAlign="left"	
								onblur="requireValue(this)"
								/>
           
           
            </td>
          </tr>
         
          
          
          
          
         
          	<tr><td colspan="2" ><table>
          	  
      <tr style="border-color: #FFFFFF"><s:iterator status="header" value="header"><td class="tdborder"><label><s:property/></label>&nbsp;</td> </s:iterator> <td class="tdborder"><label>Response AM</label></td> <td class="tdres"><label>Response Type</label></td><td class="tdborder"><label>Response SM</label></td> </tr>  
				
         <s:iterator status="rowstatus" value="chklstDetailList">
         <tr><s:iterator var="template" value="tempList"><td class="tdborder"><s:property></s:property></td></s:iterator>
         	
          	<td class="tdborder" >
          		<s:if test="%{chkChoice==\"Y\"}">
          			Yes
          		</s:if>
          		<s:elseif test="%{chkChoice==\"N\"}">
          		  No
          		</s:elseif>
          		<s:elseif test="%{chkChoice==\"A\"}">
          		  N/A
          		</s:elseif>
          			<s:hidden name="chklstDetailList[%{#rowstatus.index}].chklstDetailId"/>
          	</td>
          	
          	
          	
         		<td style="width: 200px;color:#000000;"><s:textarea name="chklstDetailList[%{#rowstatus.index}].chkResponseSm"  style="width: 200px;height:30px;border: thin solid;border-color:#D9DAEB;"></s:textarea></td>	
       			
       		</tr>
          	
          	
          	
          </s:iterator>
       	</table></td></tr>
   
            
              	 

          <tr>
            <td><label for="response">Remark</label></td> <td>
        			  <sjr:tinymce 
								id="chklstRemark" 
								name="chklstRemark" 
								rows="5" 
								cols="80" 
								width="790"
								readonly="true"
								toolbarButtonsRow1=" "
								toolbarButtonsRow4=" "
								toolbarButtonsRow3=" "
								toolbarButtonsRow2=" "
								toolbarAlign="left"	
								onblur="requireValue(this)"
								/>
           
           
            </td>
          </tr>
          <tr>
           <td >
               <sj:a href="#" indicator="indicator" button="true" onclick="updateresponsesm();" buttonIcon="ui-icon-circle-plus" >Response</sj:a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
               
              
            
                 </td>
                  <td >
                          
              <sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-arrowreturnthick-1-w" cssStyle="width:110px;" onclick="goBack()">back</sj:a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-document" cssStyle="width:160px;" onclick="goExport()">Export To Excel</sj:a>
            
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

