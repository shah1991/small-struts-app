package net.slide.action;

import org.apache.struts2.ServletActionContext;

import com.dfsg.sso.filter.SSOFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MenuAction extends ActionSupport {

	private String method;

	@Override
	public String execute() throws Exception {
		if(getMethod().endsWith("logout")){
			ActionContext.getContext().getSession().clear();
			
			// SSO Changes :: START
			if ( SSOFilter.IS_ENABLED && !SSOFilter.getLogoutURL().isEmpty() ) {
				ServletActionContext.getRequest().getSession().invalidate();
				ServletActionContext.getResponse().sendRedirect(SSOFilter.getLogoutURL());
				return null;
			}
			// SSO Changes :: END
		}
		
		
		return getMethod();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
