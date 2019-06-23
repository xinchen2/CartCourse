package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;
import util.JdbcUtil;
import entity.Items;

//商品的业务逻辑类
public class ItemsDAO {

	// 获得所有的商品信息
	public ArrayList<Items> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>(); // 商品集合
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from cart.ITEMS;"; // SQL语句
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);// 把一个商品加入集合
			}
			return list; // 返回集合。
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			JdbcUtil.release(conn, stmt, rs);
		}

	}
	
	
	// 获得所有的商品信息
		public int getItemsCount() {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = JdbcUtil.getConnection();
				String sql = "select count(*) from cart.ITEMS;"; // SQL语句
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1); 
				}else {
					return 0;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return 0;
			} finally {
				JdbcUtil.release(conn, stmt, rs);
			}

		}
	
	
	// 分页获取商品信息
		public ArrayList<Items> getSplitItems(int fromIndex,int size) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<Items> list = new ArrayList<Items>(); // 商品集合
			try {
				conn = JdbcUtil.getConnection();
				String sql = "select * from cart.ITEMS limit ?,?;"; // SQL语句
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, fromIndex);
				stmt.setInt(2, size);
				rs = stmt.executeQuery();
				while (rs.next()) {
					Items item = new Items();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setCity(rs.getString("city"));
					item.setNumber(rs.getInt("number"));
					item.setPrice(rs.getInt("price"));
					item.setPicture(rs.getString("picture"));
					list.add(item);// 把一个商品加入集合
				}
				return list; // 返回集合。
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally {
				JdbcUtil.release(conn, stmt, rs);
			}

		}

	// 根据商品编号获得商品资料
	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from cart.ITEMS where id=?;"; // SQL语句
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			JdbcUtil.release(conn, stmt, rs);
		}
	}
	//获取最近浏览的前3条商品信息
	public ArrayList<Items> getViewList(String list)
	{
		System.out.println("list:"+list);
		ArrayList<Items> itemlist = new ArrayList<Items>();
		int iCount=3; //每次返回前3条记录
		if(list!=null&&list.length()>0)
		{
		    String[] arr = list.split("#");
		    System.out.println("arr.length="+arr.length);
		    for(int i=0;i<arr.length && i<iCount;i++) {
		    	itemlist.add(getItemsById(Integer.parseInt(arr[i])));
		    }
		    return itemlist;
		}
		else
		{
			return null;
		}
		
	}

	public static void main(String[] args) {
		ItemsDAO dao = new ItemsDAO();
		System.out.println(dao.getItemsCount());
		System.out.println(dao.getSplitItems(2, 10));
	}
	
}
