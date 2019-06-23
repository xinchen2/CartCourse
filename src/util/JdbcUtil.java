package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.JdbcPool;

public class JdbcUtil {
    
    /**
    * @Field: pool
    *          ���ݿ����ӳ�
    */ 
    private static JdbcPool pool = new JdbcPool();
    
    
    public static Connection getConnection() throws SQLException{
        return pool.getConnection();
    }
    
    public static void release(Connection conn,Statement st,ResultSet rs){
        if(rs!=null){
            try{
                //�رմ洢��ѯ�����ResultSet����
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                //�رո���ִ��SQL�����Statement����
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if(conn!=null){
            try{
                //�ر�Connection���ݿ����Ӷ���
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
    	Connection	conn = null;
    	try {
			conn = pool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			release(conn, null, null);
		}
	}
}
