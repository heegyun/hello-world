package my.day11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


/*
 * 구현 내용.
 * 1. 클라이언트와 서버가 연결이 되면...
 *  2. 서버가 먼저 클라이언트에게 "안녕하세요 클라이언트님" 이라는 메세지를 먼저 보낸다.
 *  3. 클라이언트는 콘솔에서 키보드 입력을 통해 메세지를 보낸다.
 *  4. 서버는 클라이언트로부터 받은 메세지를 보고 
 *     ㄱ) "안녕하세요? 나 또는 "하이" -> "클라이언트님 반가워요." 란 메세지를 전달
 *     ㄴ) "오늘 날짜는" -> 오늘 날짜를 얻어서 전달..
 *     ㄷ) 기타 다른 메세지가 오면 -> "클라이언트 님 어서 가~~~" 란 메세지를 보낸다.
 */

public class EchoServer {
	
	ServerSocket ser;
	Socket sock;
	int port=5000;
	
	public EchoServer() throws Exception{
		ser = new ServerSocket(port);// 대기 시작
		System.out.println("서버 대기중...");
		sock=ser.accept();
		System.out.println(sock.getInetAddress()+" 가 연결됨");
		// 스트림 얻어오기..
		// 클라이언트와 통신할 스트림 생성..
		BufferedReader  br = new BufferedReader
				(new InputStreamReader(sock.getInputStream()));
		PrintWriter pw = new PrintWriter(sock.getOutputStream(),true);
		
		pw.println("안녕하세요 클라이언트 님");
		String msg="";
		
		while((msg=br.readLine())!=null){
			if(msg.startsWith("안녕")||msg.startsWith("하이")){
				pw.println(sock.getInetAddress()+"님 반가워요");
			}else if(msg.startsWith("오늘 날짜")){
				Date date = new Date();
				pw.println(date.toString());
			}else{
				pw.println(sock.getInetAddress()+"님 어여가~~~");
			}
			
		}
		pw.close();
		br.close();
		sock.close();
		ser.close();
	
	
	}
	

	public static void main(String[] args) throws Exception {
		new EchoServer();

	}

}
