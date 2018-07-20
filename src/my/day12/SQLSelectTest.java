package my.day12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLSelectTest {

	//  JDBC 1, 2 단계  메소드
	  public static Connection makeConnection()  {
			String url = "jdbc:mysql://localhost:3306/book_db?serverTimezone=UTC";	// book_db가 생성되어 있어야 한다!
			String id = "root";
			String password = "1234";
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("드라이버 적재 성공");
				con = DriverManager.getConnection(url, id, password);
				System.out.println("데이터베이스 연결 성공");
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버를 찾을 수 없습니다.");
			} catch (SQLException e) {
				System.out.println("연결에 실패하였습니다.");
			}
			return con;
	    }
	  
	  
	    public static void main(String arg[]) throws SQLException {
	        Connection con = makeConnection();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM books");
	        while (rs.next()) {
	               int id = rs.getInt("book_id");
	               String title = rs.getString("title");
	               System.out.println(id + " " + title);
	        }
	    }
	}