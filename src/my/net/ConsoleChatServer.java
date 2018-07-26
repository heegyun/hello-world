package my.net;

import java.net.*;
import java.io.*;
import static java.lang.System.out;
/**구현 내용
...채팅 서버와 채팅 클라이언트가 일대일로 채팅하는 프로그램
1) 도스 콘솔에서 키보드로 메시지를 입력해 보냄
2) Swing으로 GUI환경을 만들어서 채팅 프로그램 구현
*/

class ConsoleChatServer implements Runnable 
{
	Socket s;
	public ConsoleChatServer(){
		try{
		out.println("ConsoleChatServer Started....");
		ServerSocket ser=new ServerSocket(5555);
		s=ser.accept();
		out.println("##클과 연결 됐음###");
		//키보드 입력 스트림 생성
		BufferedReader key
			=new BufferedReader(new InputStreamReader(System.in));
		//클에게 메시지 보낼 스트림--
		PrintWriter pout=new PrintWriter(s.getOutputStream(),true);
		//클에게 키보드로 메시지 전송---
		Thread tr=new Thread(this);
		tr.start();
		String msg="";
		while((msg=key.readLine())!=null){
			pout.println(msg);//클에게 메시지 전송
		}//while------------
		}catch(Exception e){
			out.println("예외: "+e.getMessage());
		}
	}//생성자-----------------
	public void run(){
		//클이 보내오는 메시지를 계속 받아서 자기 콘솔에 출력
		try
		{
			InputStream is=s.getInputStream();
			BufferedReader br=new BufferedReader(
										new InputStreamReader(is));
			String clientMsg="";
			while(true){
				clientMsg=br.readLine();
				out.println("From Client>>"+clientMsg);
			}//while-----			
		}
		catch (Exception e)
		{
			System.out.println("예외: "+e.getMessage());
		}
	}//run()----------------
	public static void main(String[] args) 
	{
		new ConsoleChatServer();//서버 가동..
	}
}
