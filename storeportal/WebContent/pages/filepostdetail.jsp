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
	
	function doSearch() {
		document.forms[0].action = 'FilePostMain_entry.do';
		document.forms[0].submit();
	}
	
	
	function doCreatePost() {
		var sm=document.getElementById("store").checked;
		//var fm=document.getElementById("franchise").checked;
		//if((sm==false) && (fm==false)){
		if(sm==false){
		document.getElementById("managerspan").innerHTML = "Choose Manager";
		}else{
		
			document.getElementById("managerspan").innerHTML = "";
			document.forms[0].action = 'FilePostDetail_detailadd.do';
	    	document.forms[0].submit();
		}
	}

	
	function doUpload(){
		var doc=document.getElementById("docAttach").value;
		if(doc==""){
		document.getElementById("docspan").innerHTML = "Select a attachment";
		}else{
		document.getElementById("docspan").innerHTML = "";
		document.forms[0].action = 'FilePostDetail_uploadZip.do';
		document.forms[0].submit();
		}
		
	}
	

	function deleteUpload(id){
		document.forms[0].action = 'FilePostDetail_deleteDoc.do?delId='+id;
		document.forms[0].submit();
		
	}
	
	
</script>
<style type="text/css">
label{
font-weight: bold;
font-size: 16x;

}

.tdborder{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 300px;height: 5px;
}
.tdremove{
border: thin solid;background-color:#F9FEFF;border-color:#D9DAEB;
width: 150px;height: 5px;
}
</style>
</head>
<body>
  <br style="clear:both"/>
<div class="container_16">
   <jsp:include page="/include/userheader.jsp"/>  
   	<s:form action="FilePostDetail" name="FilePostDetail" id="FilePostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="id" id="id"/>
	<s:hidden name="topicId" id="topicId"/>

	<s:hidden name="recType" id="recType"/>

	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <s:if test="%{#session.prevUrlfsw !=null}">                
                    <li><a href="${prevUrlfs0}">${breadCrumbfs0}</a> &raquo;</li>    
                    <li><a href=<s:property value="%{#session.prevUrlfsy}"/>>Year</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrlfsm}"/>>Month</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrlfsw}"/>>Date</a> &raquo;</li>        
                </s:if>         
                <s:else>
                    <li><a href="${prevUrlfs11}">${breadCrumbfs0}</a> &raquo;</li>                                                
                </s:else>
            </ul>
        </div>
        <div class="clearfix" /></div>
        <s:if test="%{#session.prevUrlfsw !=null}">                
            <h3>Add activity under "<s:property value="%{#session.breadCrumbfsy}"/> <s:property value="%{#session.breadCrumbfsm}"/> <s:property value="%{#session.breadCrumbfsw}"/>"</h3>
        </s:if>
        <s:else>
        <h3>Add activity under "${breadCrumbfs0}"</h3>
        </s:else>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
         
         <tr>
            <td class="reg_head" width="130"></td>
            <td class="reg_head">Add recipients</td>
          </tr>
          <tr>
            <td><br/><label>Choose Manager</label> </td>
            <td><br/>
              <s:checkbox name="store" id="store" value="true"></s:checkbox>Store   
               <!-- &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   
               <s:checkbox name="franchise" id="franchise" ></s:checkbox> Franchise-->
            &nbsp; &nbsp; &nbsp; <span id="managerspan" class="errorspan"> </span>
             <br/><br/>
			</td>
        </tr>
	      <tr>
	            <td><label>Attachment Zip File</label></td>
	            <td>
	             <s:file name="docAttach" id="docAttach"  style="width: 300px" ></s:file> <sj:a href="#" indicator="indicator" button="true" onclick="doUpload();" buttonIcon="ui-icon-disk" cssStyle="height:27px;font-size:14px">Upload</sj:a>&nbsp;
	             <span id="docspan" class="errorspan"> </span>
				</td>
	        </tr>
       
       
       <tr><td colspan="2"><table>
          		<s:if test="%{uploadRes.isEmpty()}">
    
				</s:if>
				<s:else>
					<tr><td class="tdborder"><label>Attachment Name</label></td> <td class="tdremove"><label>Remove Attachment</label></td></tr>
				</s:else>
          	<s:iterator  value="uploadRes" status="ures">
 				<tr>
    
          		<td class="tdborder"><s:property value="attachName"></s:property> </td>
    	 	
 		 	    <td class="tdremove">
 		 	    <s:if test="%{actUpdate}">
 		 				<a href="#" onclick="deleteEditUpload('<s:property value="#ures.index"/>')">Remove</a>
 		 		</s:if>
 		 		<s:else>
 		 				<a href="#" onclick="deleteUpload('<s:property value="#ures.index"/>')">Remove</a>
 		 		</s:else>
 		 		</td>
 		 	
 		 	</tr>
 		 	
          </s:iterator>
      		</table></td>
 		 </tr>      	
            
          <tr>
            <td></td>
            <td>
            <s:if test="%{actUpdate}">
									
									<sj:a href="#" indicator="indicator" button="true" onclick="doUpdatePost();" buttonIcon="ui-icon-disk" >Update</sj:a>&nbsp;&nbsp;
									<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
							    </s:if>
								<s:else>
									<sj:a href="#" indicator="indicator" button="true" onclick="doCreatePost();" buttonIcon="ui-icon-circle-plus" >Submit</sj:a>&nbsp;&nbsp;
									<sj:a href="#" indicator="indicator" button="true" onclick="doSearch();" buttonIcon="ui-icon-circle-close" >Cancel</sj:a>
								</s:else>
            </td>
          </tr>
        </table>
        
        
        <p>&nbsp;</p>
        <!-- content end -->

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

