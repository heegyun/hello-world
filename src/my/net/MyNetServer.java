package my.net;

import java.net.*;
import java.io.*;
import static java.lang.System.out;
/*TCP통신 방식에서 서버단에서는
ServerSocket과 Socket이 필요*/
class  MyNetServer
{
	public static void main(String[] args) 
		throws IOException
	{
		//서버소켓: 포트 번호 필요...
		int port=5000;
		int myNum=Integer.parseInt(args[0]);
		//1. 서버 소켓 생성-----
		ServerSocket ser=new ServerSocket(port);

		out.println("클라이언트 연결을 기다림...");
		while(true){
			Socket sock=ser.accept();
			//클이 접속해서 들어오면 서버 소켓은 클과
			//연결된 소켓객체를 반환해준다.
			out.println("클이 접속해옴...");
			out.println("클의 IP: "
				+sock.getInetAddress().getHostAddress());

			//클과 통신하기 위해 스트림 연결
			OutputStream os=sock.getOutputStream();
			DataOutputStream dos
				=new DataOutputStream(os);
			dos.writeUTF(args[1]);
			dos.writeInt(myNum);
			dos.flush();
			dos.close();
			sock.close();
		}//while--------

		//ser.close();

	}
}
