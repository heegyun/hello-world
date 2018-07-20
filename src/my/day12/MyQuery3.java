package my.day12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyQuery3 {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
		Connection con = DriverManager.getConnection(url, "root", "1234");
		System.out.println("DB Con: " + con);
		Statement st = con.createStatement();
		String sql = "SELECT * FROM member ORDER BY id DESC";
		ResultSet rs = st.executeQuery(sql);
		// select문일 경우 executeQuery()메소드를 이용
		System.out.println("----------------------------");
		System.out.println("아이디\t이름\t전화\t주소");
		System.out.println("----------------------------");

		while (rs.next()) {
			int id = rs.getInt("id");// rs.getInt(1);//컬럼 인덱스 1부터
			String name = rs.getString("name");// rs.getString(2);
			String tel = rs.getString("tel");
			String addr = rs.getString("addr");
			System.out.println(id + "\t" + name + "\t" + tel + "\t" + addr);
		} // while------
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (con != null)
			con.close();

	}
}
