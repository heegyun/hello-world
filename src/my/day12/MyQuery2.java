package my.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyQuery2 {
	Connection con;
	Statement st;
	BufferedReader key;
	String url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
	String user = "root", pwd = "1234";

	public MyQuery2() {
		try {
			dbConnect();
			inputSql();
			closeAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// 생성자----------

	public void dbConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, user, pwd);
		System.out.println("DB연결 성공~~");
	}// ------------------

	public void inputSql() throws IOException, SQLException {
		key = new BufferedReader(new InputStreamReader(System.in));
		String sql = "";
		System.out.println("SQL문을 입력하세요[insert/delete/update문]=>");
		st = con.createStatement();

		while ((sql = key.readLine()) != null) {
			int n = st.executeUpdate(sql.trim());
			System.out.println(n + "개의 레코드 수정 완료");
			System.out.println("SQL문을 입력하세요[insert/delete/update문]=>");
		} // while--------

	}// ------------------

	public void closeAll() throws Exception {
		if (st != null)
			st.close();
		if (con != null)
			con.close();
		if (key != null)
			key.close();
	}// -------------------

	public static void main(String[] args) {
		new MyQuery2();
	}
}
