package my.day12;


import java.sql.*;
import java.util.*;

public class MemberDAO {
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	
	public void dbConnect() throws Exception{
		Class.forName(
			"oracle.jdbc.driver.OracleDriver");
		String url
		="jdbc:oracle:thin:@localhost:1521:xe";
		con=DriverManager.getConnection(url,
				"scott","tiger");
		System.out.println("Db Connected...");
		
	}
	public int insertMember(String id, String name,
			String tel, String addr)
	{
		try{
			String sql="insert into member values(" +
					"member_no.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.setString(3, tel);
			ps.setString(4,addr);
			
			int n=ps.executeUpdate();
			return n;
			
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			try{
				if(ps!=null)ps.close();
			}catch(SQLException e){}			
		}
		
	}//insertMember()-----------
	public int deleteMember(String id){
		try{
			String sql="delete from member where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			
			return ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			try{
				if(ps!=null)ps.close();
			}catch(SQLException e){}			
		}
	}
	public int updateMember(String colName,
			String value, int no){
		try{
			String sql="update member set "+colName
			+"=? WHERE no=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,value);
			ps.setInt(2, no);
						
			return ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			try{
				if(ps!=null)ps.close();
			}catch(SQLException e){}			
		}
	}
	public MemberDTO[] selectAll(){
		try{
			String sql="select * from member";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			MemberDTO[] arr=makeArray(rs);			
		
			return arr;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
			}catch(SQLException e){}			
		}
	}//selectAll()--------------
	public MemberDTO[] selectByName(String name){
		try{
			String sql
			="select * from member where name=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			MemberDTO[] arr
				=makeArray(rs);
			return arr;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
			}catch(SQLException e){}			
		}
		
	}//selectByName()--------------
	public MemberDTO selectById(String id){
		try{
			String sql
			="select * from member where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			MemberDTO arr[]=makeArray(rs);
			return arr[0];
			//return makeArray(rs)[0];			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
			}catch(SQLException e){}			
		}
	}//selectById()--------------
	//ResultSet���� �����͸� ������
	//MemberDTO[]�迭�� ��� �����ִ� �޼ҵ�
	public MemberDTO[] makeArray(ResultSet rs)
	throws SQLException{
		Vector<MemberDTO> v
			=new Vector<MemberDTO>();
		while(rs.next()){
			int no=rs.getInt("no");
			String id=rs.getString("id");
			String name=rs.getString("name");
			String tel=rs.getString("tel");
			String addr=rs.getString("addr");
			//�Ѱ��� ���ڵ�....DTO��ü�� �����..
			MemberDTO dto
			=new MemberDTO(no,id,name,tel,addr);
			v.add(dto);
		}//while-----
		MemberDTO memArr[]
		            =new MemberDTO[v.size()];
		v.copyInto(memArr);
		//���Ϳ� ����� ��ü�� memArr�迭�� ī��		
		
		return memArr;
	}//selectById()----------
	public void close(){
		try{
			if(con!=null)
				con.close();
		}catch(SQLException e){}
		
	}//close()---------

}//////////////////////////////////////




