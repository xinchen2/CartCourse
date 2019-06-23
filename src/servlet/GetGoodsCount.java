package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import entity.Items;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/GetGoodsCount")
public class GetGoodsCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetGoodsCount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemsDAO itemsDao = new ItemsDAO(); 
		int goodsCount = itemsDao.getItemsCount();
		
		JSONObject object = new JSONObject();
			
		object.put("goodsCount", goodsCount);
		response.setContentType("json/html");
		response.getWriter().print(object);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
