/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.slide.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author private
 */
@SuppressWarnings("serial")
public class UrlBreadCrumb {
    
        public static final String getUrl(){
                HttpServletRequest req = ServletActionContext.getRequest();
                String url = req.getHeader("referer");           
          //      System.out.println(url);
                return url;
        }	    
}
