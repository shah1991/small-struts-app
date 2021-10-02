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
		document.forms[0].action = 'PostMain_hq.do';
		document.forms[0].submit();
	}
	
	
	function doCreatePost() {
            if (doCheck()){
		var start=document.getElementById("postSubject").value;
		if(start==""){
		   document.getElementById("subspan").innerHTML = "Subject is empty";
	    }else{
	    	 document.getElementById("subspan").innerHTML ="";
                     document.getElementById("checkspan").innerHTML ="";
			document.forms[0].action = 'PostDetail_detailadd.do';
			document.forms[0].submit();
		}
            }else{
                   document.getElementById("checkspan").innerHTML = "* Please select at least one Area or Store.";
                
            }
	}

	function doUpdatePost() {
            if (doCheck()){
		var start=document.getElementById("postSubject").value;
		if(start==""){
		   document.getElementById("subspan").innerHTML = "Subject is empty";
	    }else{
	    	 document.getElementById("subspan").innerHTML ="";
                     document.getElementById("checkspan").innerHTML ="";
			document.forms[0].action = 'PostDetail_detailupdate.do';
			document.forms[0].submit();
                }
            }else{
                   document.getElementById("checkspan").innerHTML = "* Please select at least one Area or Store.";
		}
	}

	function doDeletePost() {
		document.forms[0].action = 'PostDetail_delete.do';
		document.forms[0].submit();
	}
	
	
	function doEditUpload(){
            if (doCheck()){
		document.forms[0].action = 'PostDetail_uploadEditDoc.do';
		document.forms[0].submit();
            }else{
                document.getElementById("checkspan").innerHTML = "* Please select at least one Area or Store.";                
            }
		
	}
	
	function doUpload(){
            if (doCheck()){
		document.forms[0].action = 'PostDetail_uploadDoc.do';
		document.forms[0].submit();
            }else{
                   document.getElementById("checkspan").innerHTML = "* Please select at least one Area or Store.";
            }
		
		
	}
	
	
	function deleteUpload(id){
            if (doCheck()){
		document.forms[0].action = 'PostDetail_deleteDoc.do?delId='+id;
		document.forms[0].submit();
            }else{
                   document.getElementById("checkspan").innerHTML = "* Please select at least one Area or Store.";                
            }
		
	}
	
	function deleteEditUpload(id){
            if (doCheck()){
		document.forms[0].action = 'PostDetail_deleteEditDoc.do?delId='+id;
		document.forms[0].submit();
            }else{
                   document.getElementById("checkspan").innerHTML = "* Please select at least one Area or Store.";
            }
		
	}
	
        function doCheck()  {        
            var e= document.forms[0].elements.length;
		  var cnt=0;
                  var checked = false;
		  for(cnt=1;cnt<e;cnt++)
		  {
			  
		    if(document.forms[0].elements[cnt].checked)
		    {
		       checked = true;	 
		       break;
		    }
                  }
                  return checked;
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
   	<s:form action="PostDetail" name="PostDetail" id="PostDetail" method="post" enctype="multipart/form-data" theme="simple">
	<s:hidden name="id" id="id"/>
	<s:hidden name="topicId" id="topicId"/>
	<s:hidden name="recType" id="recType"/>
        <s:hidden name="breadCrumbt1" id="breadCrumbt1"/>             
   <div class="container_16">
	<div class="grid_12">
	    <div class="breadcrumb">
            <ul>
                <li>You are now at:</li>
                <li><a href="Login_home.do">home</a> &raquo;</li>
                <li><a href="${prevUrl0}">${breadCrumbt0}</a> &raquo;</li>      
                <s:if test="%{#session.prevUrlw !=null}">                
                    <li><a href=<s:property value="%{#session.prevUrl1}"/>>${breadCrumbt1}</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrly}"/>>Year</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrlm}"/>>Month</a> &raquo;</li>        
                    <li><a href=<s:property value="%{#session.prevUrlw}"/>>Week</a> &raquo;</li>        
                </s:if>         
                <s:else>
                    <li><a href="${prevUrl11}">${breadCrumbt1}</a> &raquo;</li>                            
                </s:else>
                
            </ul>
        </div>
        <div class="clearfix" /></div>
        
	

        <s:if test="%{#session.prevUrlw !=null}">                
            <h3>Add activity under "<s:property value="%{#session.breadCrumby}"/> <s:property value="%{#session.breadCrumbm}"/> <s:property value="%{#session.breadCrumbw}"/>"</h3>
        </s:if>
        <s:else>
            <h3>Add activity under "${breadCrumbt1}"</h3>
        </s:else>
        <s:if test="hasActionErrors() || hasActionMessages()">
        <p class="notification">
        		<s:actionerror />
				 <s:actionmessage />
        </p>
        </s:if>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reg_table">
         
         <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">Add recipients</td>
          </tr>
         
    <tr>
         <td colspan="2" width="100%">
         <table >
         <tr>
         <td style="width: 250px;">
     
        
       
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
         </td>
         <td style="width: 250px;">
    	 <ul class="tree">
            <li>
                <input type="checkbox"/> <label> Stores </label>
                <ul>
						<s:iterator value="areaList">
                    	<li>
                        <input type="checkbox" />
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
         </td>
         </tr>
          &nbsp;<span id="checkspan" class="errorspan"> </span>       
         </table>
         </td>
         </tr>   
         
        
          <tr>
            <td class="reg_head" width="15%"></td>
            <td class="reg_head">Add a new activity</td>
          </tr>
          <tr>
            <td><label for="postSubject">Subject</label></td>
            <td><s:textfield name="postSubject" id="postSubject" maxlength="100" style="width:500px" onblur="requireValue(this)"/>
            
            &nbsp;* &nbsp;	<span id="subspan" class="errorspan"> </span>
            </td>
          </tr>
          <tr>
            <td><label for="postContents">Content</label></td>
            <td></td>
          </tr>
          <tr>
            <td colspan="2"><sjr:tinymce 
								id="postContents" 
								name="postContents" 
								rows="10" 
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
             <s:if test="%{actUpdate}">
           <tr><td colspan="2">Attachment file</td></tr>
             <tr>
            
            <td colspan="2">
            <s:iterator value="uploadList">
                
                   <a href=".<s:property value="postAttachPath"/>" target="_blank" style="width:200px"> 
            			 <s:property  value="postAttachFilename" />
           			</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </s:iterator>
             
			</td>
          </tr>
          <tr>
            <td colspan="2">
               <s:iterator value="uploadList" >
                   
                   
                   <img src=".<s:property value="postAttachPath"/>" width="100px" height="100px;" title="${postAttachFilename }"></img>&nbsp;&nbsp;&nbsp;
               </s:iterator>
            </td>
          </tr>
              </s:if>
                    <tr>
            <td><label>Attachment</label></td>
            <td>
            <s:file name="postAttach" id="postAttach"  style="width: 300px" ></s:file>
             <s:if test="%{actUpdate}">
            	<sj:a href="#" indicator="indicator" button="true" onclick="doEditUpload();" buttonIcon="ui-icon-disk" cssStyle="height:27px;font-size:14px">Upload</sj:a>
          	</s:if>
            <s:else>
               <sj:a href="#" indicator="indicator" button="true" onclick="doUpload();" buttonIcon="ui-icon-disk" cssStyle="height:27px;font-size:14px">Upload</sj:a>
            </s:else>
			</td>
          </tr>
   			
          <tr><td colspan="2"><table align="center">
          		<s:if test="%{uploadRes.isEmpty()}">
    
				</s:if>
				<s:else>
					<tr><td class="tdborder"><label>Attachment Name</label></td> <td class="tdremove"><label>Remove Attachment</label></td></tr>
				</s:else>
          	<s:iterator  value="uploadRes" status="ures">
 				<tr>
    
          		<td class="tdborder">
	          		<a href=".<s:property value="attachPath"/>" target="_blank" style="width:200px">
		          		<img src=".<s:property value="attachPath"></s:property>" style="width:30px;height:30px;"></img>
		          		<s:property value="attachName"></s:property> 
	          		</a>
          		</td>
    	 	
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

