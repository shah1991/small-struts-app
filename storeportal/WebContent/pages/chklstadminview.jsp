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
<link type="text/css" rel="stylesheet" href="css/checktree.css" />
<script type="text/javascript" src="js/checktree.js"></script>

<script type="text/javascript">
	            var $checktree;
	            $(function(){
	                $checktree = $("ul.tree").checkTree();
	            });
</script>	

<script type="text/javascript">
function goHome() {
	document.forms[0].action = 'Login_home.do';
	document.forms[0].submit();
}

function goBack()
  {
	var topicId =document.getElementById("topicId").value;
	document.forms[0].action = 'ChklstPostMain_entry.do?topicId='+topicId;
	document.forms[0].submit();
  }
	
	function doUpdatePost() {
		if(autocheck(document.forms['TaskPostDetail'])){
			document.forms[0].action = 'TaskPostDetail_updateResponse.do';
			document.forms[0].submit();
		}
	}

	function ChangeFlag(){
	var val=document.getElementsByName("flag");
	
		if(val[0].checked){
		document.getElementById("areaDiv").style.display="block";
		document.getElementById("storeDiv").style.display="none";
			
		}else{
		document.getElementById("areaDiv").style.display="none";
		document.getElementById("storeDiv").style.display="block";
		}
		
	}	
	
</script>

</head>
<body onload="ChangeFlag()">
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="TaskPostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="id" id="id"/>
	<s:hidden name="topicId" id="topicId"/>
	<s:hidden name="chklstCode" id="chklstCode"/>
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
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">Recipients</td>
          </tr>
         <tr><td><label>Choose Area / Store</label></td><td>
         <s:if test="%{flag==\"A\"}">
         	<input type="radio" name="flag" id="flag" value="A" checked="checked" onchange="ChangeFlag(this)"/>Area 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="flag" id="flag" value="S" onchange="ChangeFlag(this)"/>Store
          </s:if>
         <s:elseif test="%{flag==\"S\"}">
         <input type="radio" name="flag" id="flag" value="A"  onchange="ChangeFlag(this)"/>Area 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="flag" id="flag" value="S" checked="checked" onchange="ChangeFlag(this)"/>Store
         </s:elseif>
         <s:else>
        
         	<input type="radio" name="flag" id="flag" value="A" checked="checked" onchange="ChangeFlag(this)"/>Area 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="flag" id="flag" value="S" onchange="ChangeFlag(this)"/>Store
         
         </s:else>
          </td>
         </tr>
         <tr>
         <td colspan="2" style="width:100%;border:0;">
         <table >
         <tr>
         <td style="width: 124px;border:0;">
	   	   
         </td>
         <td style="width: 250px;border:0;">
          <div id="areaDiv" >
	         <ul class="tree">
	            <li>
	                <input type="checkbox"/> <label> Areas </label>
	                <ul>
	                
							<s:iterator value="areaList">
	                    	<li>
	                    	<s:if test="%{areaId in alist}">
							<s:checkbox name="alist" id="alist" fieldValue="%{areaId}" value="true"/>
							</s:if>
							<s:else>
							<s:checkbox name="alist" id="alist" fieldValue="%{areaId}" value="false"/>
							</s:else>
	                        <label>${areaName}</label>
							</li>                			  
	            			  </s:iterator>
	                </ul>
	            </li>
	       	 </ul>
        	</div>
    		<div id="storeDiv" style="display:none">
    		 <ul class="tree">
            <li>
                <input type="checkbox"/> <label> Stores </label>
                <ul>
						<s:iterator value="areaList">
                    	<li>
                        <s:checkbox name="aslist" id="aslist  " fieldValue="%{areaId}" value="false"/>
                        <label>${areaName}</label>
                        <ul>
                        <s:iterator value="tblStoreList">
						<li>
						<s:if test="%{storeId in slist}">
						<s:checkbox name="slist" id="slist" fieldValue="%{storeId}" value="true"/>
						</s:if>
						<s:else>
						<s:checkbox name="slist" id="slist" fieldValue="%{storeId}" value="false"/>
						</s:else>
						<label>${storeCode} - ${storeName}</label>
						</li>
						</s:iterator>
                   </ul>
			</li>                			  
            			</s:iterator>
                </ul>
            </li>
        </ul>
        </div>
         </td>
         </tr>
         </table>
         </td>
         </tr>   
            
            
        
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
       	<tr><td colspan="2"><label for="chklstContens">Content</label></td></tr>
          <tr>
            <td></td><td><sjr:tinymce 
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
         
           <tr>
                  <td colspan="2"><table ><tr style="border-color: #FFFFFF"><s:iterator status="header" value="header"><td style="width: 100px;color:#000000;border-color: #FFFFFF"><s:property/>&nbsp;</td> </s:iterator>
                  <td>Action</td>  </tr></table>        
				 </td>
         	  </tr>
          <s:iterator status="Chklst" value="chklstDetailList">
          <tr><td colspan="2"><table><tr><s:iterator var="Chklst" value="tempList"><td style="width: 100px;color:#000000;border-color: #FFFFFF"><s:property></s:property></td></s:iterator></tr></table></td></tr>
          </s:iterator>
        
          <tr>
            
            <td>
              <sj:a href="#" indicator="indicator" button="true" buttonIcon="ui-icon-arrowreturnthick-1-w" cssStyle="width:110px;" onclick="goBack()">back</sj:a>
              
            
                 </td>
                  <td >
                          
               <sj:a href="#" indicator="indicator" button="true"  buttonIcon="ui-icon-home" cssStyle="width:110px;" onclick="goHome()">home</sj:a>
            
            
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

