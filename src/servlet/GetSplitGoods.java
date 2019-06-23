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


@WebServlet("/GetSplitGoods")
public class GetSplitGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSplitGoods() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int size = 10;
		ItemsDAO itemsDao = new ItemsDAO(); 

	//	int currentPage = Integer.valueOf(request.getParameter("currentPage"));
	//	request.setAttribute("currentPage", currentPage);
		
		int currentPage =  Integer.valueOf(request.getParameter("currentPage"));
		request.setAttribute("currentPage", currentPage);
		System.out.println("currentPage"+currentPage);
        ArrayList<Items> list = itemsDao.getSplitItems((currentPage-1)*size,size);
		
			JSONArray array = new JSONArray();
			JSONObject object = new JSONObject();
			for(int i=0;i<list.size();i++) {
				Items item = list.get(i);
				object.put("id", item.getId());
				object.put("picture", item.getPicture());
				object.put("name", item.getName());
				object.put("city", item.getCity());
				object.put("price", item.getPrice());
				array.add(object);
			}	
			
			System.out.println(array);
			//json传输的数据，需要设置编码类型，否则乱码；编码过滤器这是不起作用
			response.setContentType("json/html; charset=utf-8"); 
		//	response.setContentType("/html");
			response.getWriter().print(array);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
