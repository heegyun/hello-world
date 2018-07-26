package my.net;

import java.net.*;
import java.io.*;
import static java.lang.System.out;
/**
구현할 내용
-Client가 Server와 접속하면...
  
  1) 서버로부터 먼저 "안녕하세요 클라이언트님?" 이란
      메시지를 받는다.
  
  2) 그러면 클라이언트는 도스 콘솔에서 키보드 입력을 통해
      메시지를 보낸다[키보드 입력스트림과 서버쪽 소켓과
	  연결하여 메시지를 주고받는 인풋/아웃풋 스트림이 필요]
 
  3) 그러면 서버는 클라이언트로부터 받은 메시지를 보고
       ㄱ) "안녕하세요?" 나 "하이"란 메시지가 오면
	        --->"클님 반가워요" 라고 메시지를 보내고
		ㄴ) "오늘 날짜는" 이란 메시지가 오면
		    ----> 오늘 날짜를 보내고
        ㄷ) 기타 다른 메시지가 오면
		   "~~~님 어여 가~~"란 메시지를 보내자.


*/

class  EchoServer
{
	public static void main(String[] args) 
	{
		int port=7000;
		ServerSocket ser=null;
		Socket sock=null;
		out.println("EchoServer Started...");
		try
		{
			ser=new ServerSocket(port);
			//대기 시작...
			sock=ser.accept();
			out.println(sock.getInetAddress()+"가 연결해 왔습니다.");
			out.println(sock.getPort()+"로 연결이 되었네요");

			//클과 통신할 스트림 생성
			BufferedReader in=new BufferedReader(
										new InputStreamReader(
												sock.getInputStream()));
			//클의 메시지를 듣는 스트림
			
			PrintWriter pout
				=new PrintWriter(sock.getOutputStream(),true);
			//클에게 메시지를 보내는 스트림
			
			pout.println("안녕하세요 클라이언트님!");
			String msg="";
			while((msg=in.readLine())!=null){
				if(msg.startsWith("안녕")||msg.startsWith("하이")){
					pout.println("["+sock.getInetAddress()+"] 님 방가!");
				}else if(msg.startsWith("오늘 날짜")){
					java.util.Date today=new java.util.Date();
					pout.println(today.toString());
				}else{
					pout.println("["+sock.getInetAddress()+"] 님 어여 가~~");
				}			
			}//while----
			in.close();
			pout.close();
			sock.close();
			ser.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}//main()-------------------
}
