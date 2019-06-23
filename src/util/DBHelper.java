package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
   
	private static final String driver = "com.mysql.cj.jdbc.Driver"; //���ݿ�����
	//�������ݿ��URL��ַ
//	private static final String url="jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8"; 
	private static final String url = "jdbc:mysql://localhost:3306/cart?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
	private static final String username="root";//���ݿ���û���
	private static final String password="123366";//���ݿ������
    
	private static Connection conn=null;
	
	// ����sql����ִ�ж���
	private static PreparedStatement pstmt;
	//�����ѯ���صĽ������
	private static ResultSet resultSet;
	
	//��̬����鸺���������
	static 
	{
		try
		{
			Class.forName(driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//����ģʽ�������ݿ����Ӷ���
	public static Connection getConnection() throws Exception
	{
		if(conn==null)
		{
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
	}
	
	
	/**
	 * ִ�в�ѯ����
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            ִ�в���
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> findResult(Connection conn,String sql, List<?> params)
			throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = conn.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		try
		{
		   Connection conn = DBHelper.getConnection();
		   if(conn!=null)
		   {
			   System.out.println("���ݿ�����������");
		   }
		   else
		   {
			   System.out.println("���ݿ������쳣��");
		   }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
