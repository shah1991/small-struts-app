<%@taglib uri="/struts-tags" prefix="s"%>

<div class="navBg">
      <div class="navOuter">
        <div class="menu">
         <ul>
            <li><a href="Menu.do?method=home" >Home</a></li>
            <li class="sepra">|</li>
            <li><a  class="hasSubMenu"> Site </a>
            	<div class="submenus">
				<ul>
			  		<li><a href="StoreMain_search.do">Store</a></li>
			  		<li><a href="AreaMain_search.do">Area</a></li>
			  		
			  	</ul>
		  	</div>
		</li>
		<li class="sepra">|</li>
	    <li><a  href="ForumMain_search.do"> Forum </a>
	    </li>
	    
		<li class="sepra">|</li>
	    <li><a  href="TaskMain_search.do"> Task </a>
	    </li>

		<li class="sepra">|</li>
	    <li><a  href="ChklstGroup_search.do"> CheckList</a>
	    </li>
	   
	   <li class="sepra">|</li>
	    <li><a  href="FileGroup_search.do"> File share</a>
	    </li>

	    <li class="sepra">|</li>	    
	    <s:if test="%{'['+#session.loginUser.userAdmin+']' == '[Y]'}">
		    <li><a  class="hasSubMenu">Administration</a>
		    	<div class="submenus">
			    <ul>
			    	<%-- SSO Changes :: START --%>
			    	<%-- 
			        <li><a href="UserMain_search.do">Users</a></li>
			        <li><a href="RoleMain_search.do">Roles</a></li>
			         --%>
			         <%-- SSO Changes :: End --%>
			        <li><a href="HomeLinks_search.do">Home Link</a></li>
			       
			    </ul>
			    </div>
		    </li>
		    <li class="sepra">|</li>
	    </s:if>
	    	<%-- SSO Changes :: START --%>
            <%--<li class="last"><a href="Menu.do?method=chgpwd">Settings</a></li>--%>
          	<%-- SSO Changes :: END --%>
         </ul>
       </div>
     </div>
   </div>
