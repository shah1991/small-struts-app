package net.slide.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.slide.dao.UserDao;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



@SuppressWarnings("serial")
public class AppInit extends HttpServlet {
	
	public void init() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		
		UserDao userObj = new UserDao();
		userObj.getUserById(new Long(1));
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
	}

}
