package my.day12;

import java.sql.*;

public class ConnectDatabase {
	
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/book_db";	// book_db가 생성되어 있어야 한다!
		String id = "root";
		String password = "1234";
		Connection con = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
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
	}
}