package my.day12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyQuery4 {
	public static void main(String[] args) 
			throws Exception
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			Connection con=DriverManager.getConnection(url,"scott","tiger");
			System.out.println("DB연결~~");
			Statement st=con.createStatement();

			BufferedReader key
				=new BufferedReader(
					new InputStreamReader(System.in));

			String sql="";
			System.out.println("SQL문을 입력하셈[아무거나]=>");
			while((sql=key.readLine())!=null){
				//입력받은 sql문장을 일단 실행 
				boolean isRs=st.execute(sql);
				ResultSet rs=null;
				int updateCount=0;
				if(isRs){
				//isRs 가 true-->select문--->ResultSet얻어와서
				//그 결과를 출력
					rs=st.getResultSet();
					System.out.println("---------------------------");
					System.out.println("ID\tNAME\tTEL\tADDR");
					System.out.println("---------------------------");
					while(rs.next()){
						 int id=rs.getInt(1);
						 //String id=rs.getString(1);
						 String name=rs.getString(2);
						 String tel=rs.getString(3);
						 String addr=rs.getString(4);
						 System.out.println(id+"\t"+name+"\t"+tel
														+"\t"+addr);
					}//while--------
					if(rs!=null) rs.close();
				}else{
				//isRs가 false--->DML문---> 변경된 레코드수 출력
					updateCount=st.getUpdateCount();
					System.out.println(updateCount+"개의 레코드 변경");
				}
			}//while----------

			if(key!=null) key.close();	
			if(st!=null) st.close();
			if(con!=null) con.close();
		}
	}
