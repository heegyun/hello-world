package my.day12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyQuery1 {
	public static void main(String[] args)
			throws ClassNotFoundException, SQLException
		{
			String driver="org.gjt.mm.mysql.Driver";
			Class.forName(driver);
			/*1. OracleDriver를 메모리에 로딩한다.
			   2. OracleDriver객체를 만들어준다.(static initializer를 이용)
			   3. 드라이버 객체를 DriverManager에 등록해준다.
			*/
			System.out.println("드라이버 로딩 성공!");
			String url="jdbc:mysql://localhost:3306/member?useSSL=false";
			String user="root", pwd="1234";
			Connection con
				=DriverManager.getConnection(url, user, pwd);
			System.out.println("DB연결 성공!");

			Statement stmt=con.createStatement();
			//sql 문을 실행할 메소드를 가짐
			String sql="INSERT INTO member VALUES(1,'임꺽정','222-222','서울 강남구')";
			
			int updateCount=stmt.executeUpdate(sql);	
			System.out.println(updateCount+"개의 레코드가 삽입됨");
			
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();

		}//main()---------
	}
	/* JDBC 프로그래밍 절차**************************************
	1. 드라이버 로딩...Class.forName()
	2. 커넥션 할당받기... DriverManager.getConnectiont() 을 통해..
	3. 쿼리문 전송을 위한 Statement  또는 PreparedStatement
	    할당받기.... con.createStatement()/ con.prepareStatement()
						를 통해
	4. Statement/PreparedStatement를 통해 쿼리문 전송
	    <1> DML(insert, delete, update) 문인 경우...
				-> int updateCount =st.executeUpdate(sql)
		<2> select 문인 경우...
				-->ResultSet rs=st.executeQuery(sql)
	5. select 문일 경우
			ResultSet의 논리적 커서를 이동시키면서
			각 컬럼의 데이터를 꺼내온다.
			boolean b=rs.next() : 커서 이동, 커서가 위치한 지점에 
									레코드가 있으면 true를 리턴
									없으면 false를 리턴한다.
									커서는 맨처음에 첫번째 행의 직전에
									위치하고 있다가, next()가 호출되면
									후진한다.
			rs.getXXX(컬럼인덱스)
			rs.getXXX(컬럼명) 등의 메소드를 통해 데이터를 꺼내온다.
			...이때 get뒤에는 컬럼의 데이터 유형과 맞춰준 자료형을
			   기재한다.
			   number 인경우 rs.getInt(1)
			   varchar2인경우 rs.getString(2);
			   sysdate인 경우 rs.getDate(3) 등....
	6.  다 사용한 자원 반납
			rs.close();
			st.close();
			con.close();
			...이 때 null체크해서 close()해주는 것이 좋다.
				finally 블럭에서 구현하는 것이 좋음


	*/