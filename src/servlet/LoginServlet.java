package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemsDAO;
import util.DBHelper;
import util.JdbcUtil;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "goods2.jsp";
		String id = request.getParameter("exampleInputEmail1");
		String password = request.getParameter("exampleInputPassword1");
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("LoginServert:"+id+password);
		if(id==null || "".equals(id)) {
			request.setAttribute("code", 1);  //邮箱为空
			path = "index.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		if(password == null || "".equals(password)) {
			request.setAttribute("code", 2);  //密码为空
			path = "index.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}	
		
		String checkCode=request.getParameter("checkCode");
		HttpSession session = request.getSession();
		if(!session.getAttribute("checkCode").toString().equalsIgnoreCase(checkCode)){
			request.setAttribute("code", 4);  //验证码错误
			System.out.println("验证码错误，实际："+checkCode+",shengcheng："+session.getAttribute("checkCode").toString());
			path = "index.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		
		try {
			String sql = "select * from cart.USER where id=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				session.setAttribute("id", rs.getString("id"));
				ItemsDAO itemsDao = new ItemsDAO(); 
				int size = 10;
				int goodsCount = itemsDao.getItemsCount();
				request.setAttribute("pageCount", (goodsCount+size-1)/size);
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}else {
				request.setAttribute("code", 3);  //用户名或密码错误
				System.out.println("没有查询到用户");
				path = "index.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.release(null, ps, rs);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
