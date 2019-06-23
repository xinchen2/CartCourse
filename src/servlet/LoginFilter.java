package servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter {

    
    public LoginFilter() {
    
    }

	
	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		String[] arr = uri.split("\\.");
		if(arr.length>1 && ("css".equals(arr[1])||"js".equals(arr[1])||"png".equals(arr[1])||"jpg".equals(arr[1]) ||"properties".equals(arr[1])) )  {
			chain.doFilter(request, response);
			return;
		}
		String[] arr2 = uri.split("/");
		System.out.println("arr[-1]:"+arr2[arr2.length-1]);
		if("LoginServlet".equals(arr2[arr2.length-1]) || "RegisterServlet".equals(arr2[arr2.length-1]) || "register.html".equals(arr2[arr2.length-1]) || "CheckCodeServlet".equals(arr2[arr2.length-1]) ) {
			chain.doFilter(request, response);
			return; //±ÿ–Îº”…œreturn
		}
		
		HttpSession ses = req.getSession();
		System.out.println("LoginFilter:"+ses.getAttribute("id"));
		if(ses.getAttribute("id")!="" && ses.getAttribute("id")!=null) {
			chain.doFilter(request, response);
			return;
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
