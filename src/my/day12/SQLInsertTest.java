package my.day12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInsertTest {
	
	//  JDBC 1, 2 단계  메소드
	 public static Connection makeConnection() {
			String url = "jdbc:mysql://localhost:3306/book_db?serverTimezone=UTC";	// book_db가 생성되어 있어야 한다!
			String id = "root";
			String password = "1234";
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
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

	
	 // 3. 레코드 추가하기 메소드 
		private static void addBook(String title, String publisher, String year,int price) {
			Connection con = makeConnection();
			try {
				Statement stmt = con.createStatement();
				String s = "INSERT INTO books (title, publisher, year, price) VALUES ";
				s += "('" + title + "','" + publisher + "','" + year + "','"
						+ price + "')";
				System.out.println(s);
				int i = stmt.executeUpdate(s);
				if (i == 1)
					System.out.println("레코드 추가 성공");
				else
					System.out.println("레코드 추가 실패");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}//
		
		public static void main(String arg[]) {
			addBook("Artificial Intellegence", "Addison Wesley", "2002", 35000);
		}
	}