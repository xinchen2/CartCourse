<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="entity.Items"%>
<%@ page import="dao.ItemsDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./css/home.css">
	<style type="text/css">
		h1{text-align: center}
	</style>
  </head>
  
  <body>
    <h1>商品展示</h1>
    	<div class="container">
          <div class="row">   
          <!-- 商品循环开始 -->
           <% 
               ItemsDAO itemsDao = new ItemsDAO(); 
               ArrayList<Items> list = itemsDao.getAllItems();
               if(list!=null&&list.size()>0)
               {
	               for(int i=0;i<list.size();i++)
	               {
	                  Items item = list.get(i);
           %> 
 		<div class="col-sm-4 col-md-3 col-xs-12">
    	<div class="thumbnail">
    		<a href="details.jsp?id=<%=item.getId()%>"><img src="images/<%=item.getPicture()%>" alt="..."></a>
    	  	<div class="caption">
    	    	<h3><%=item.getName() %></h3>
    	    	<p>产地:<%=item.getCity() %>&nbsp;&nbsp;价格:￥ <%=item.getPrice() %></p>
    	  	</div>
    	</div>
  		</div> 
          <%
                   }
              } 
          %>
    </div></div>
    <script src="./js/jquery-3.3.1.js"></script> 
	<script type="text/javascript" src="./js/popper.js"></script> 
	<script type="text/javascript" src="./js/bootstrap.js"></script>
	<script type="text/javascript" src="js/masonry.pkgd.js"></script> 
</body>
</html>
