package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBHelper;
import util.JdbcUtil;



@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegisterServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String path = "index.jsp";
		String id = request.getParameter("exampleInputEmail1");
		String password = request.getParameter("exampleInputPassword1");
		String password2 = request.getParameter("exampleInputPassword2");
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PreparedStatement ps = null;
		int result = 0;
		if(id==null || "".equals(id)) {
			request.setAttribute("code", 1);  //邮箱为空
			return;
		}
		if(password == null || "".equals(password)) {
			request.setAttribute("code", 2);  //密码为空
			return;
		}if(!password.equals(password2)) {
			request.setAttribute("code", 3);  //密码不一致
			return;
		}	
		
		try {
			String sql = "insert into cart.USER(id,password) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			result = ps.executeUpdate();
			if(result!=0) {
				response.setContentType("text/html;charset=utf-8");    
				response.getWriter().write("<script>alert('注册成功！点击确定后跳转到登陆界面');</script>");    
				response.getWriter().write("<script>window.location='index.jsp';window.close();</script>");    
				response.getWriter().flush();

			//	request.getRequestDispatcher(path).forward(request, response);
			}else {
				System.out.println("没有查询到用户");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(conn, ps, null);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
