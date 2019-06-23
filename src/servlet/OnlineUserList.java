package servlet;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserList implements HttpSessionAttributeListener,HttpSessionListener,ServletContextListener{
	private ServletContext app = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		this.app = sce.getServletContext();
		this.app.setAttribute("onlineCount", 0);
		//		this.app.setAttribute("online", new TreeSet());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		int count = (int)this.app.getAttribute("onlineCount");
		this.app.setAttribute("onlineCount", count+1);
		System.out.println(" Ù–‘‘ˆº”");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		int count = (int)this.app.getAttribute("onlineCount");
		this.app.setAttribute("onlineCount", count-1);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		int count = (int)this.app.getAttribute("onlineCount");
		this.app.setAttribute("onlineCount", count-1);
		System.out.println("session destroy");
	}

}
