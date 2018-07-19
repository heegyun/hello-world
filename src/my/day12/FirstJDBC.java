package my.day12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstJDBC {
	
	public static void main(String[] args) {

		try { // 1. 드라이버 로딩 Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			System.out.println("Driver Loading Success!");
			// 2. DB와 커넥션(연결)
			String url="";
			Connection con = DriverManager.getConnection(url);
			System.out.println("DB Connected~~!");

		
			// 3. DB에 SQL문(질의문)을 전송하기 위한 Statement객체 할당받기...Connection의 createStatement()메소드를 통해
			Statement stmt = con.createStatement();
			
			// 4. DB에 질의문 전송
			// Statement의 executeXXX()메소드를 통해서...
			String sql = "";
			ResultSet rs = stmt.executeQuery(sql);
			// select문일 경우는 executeQuery(sql)을 이용
			// 이때 반환타입은 ResultSet

			// 5. ResultSet의 커서를 이동하면서 데이터를 꺼내와 출력
			while (rs.next()) {
				String col1 = rs.getString(1);
				String col2 = rs.getString(2);
				System.out.println(col1 + "\t" + col2);
			} // while----------
				// 6. DB와 연결된 자원 반납***매우 중요***

			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
