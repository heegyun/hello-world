package my.net;

import java.net.*;
import java.io.*;
import static java.lang.System.out;

class  EchoClient
{
	public static void main(String[] args) 
			throws Exception
		{
			String ip="172.16.10."+args[0];
			int port=7000;
			//소켓 생성
			Socket sock=new Socket(ip,port);
			
			InputStream is=sock.getInputStream();
			OutputStream os=sock.getOutputStream();
			BufferedReader in=new BufferedReader(
											new InputStreamReader(is));
			PrintWriter pout=new PrintWriter(os,true);

			BufferedReader key
				=new BufferedReader(new InputStreamReader(System.in));
			
			String sendMsg="", receiveMsg="";
			receiveMsg=in.readLine();
			//우선 서버가 보낸 메시지(안녕하세요 클님)을 들어 출력
			out.println("From Server>>>"+receiveMsg);

			while((sendMsg=key.readLine())!=null){
				pout.println(sendMsg);
				receiveMsg=in.readLine();
				out.println("From Server>>>"+receiveMsg);
			}//while-----------

			in.close(); pout.close(); key.close();
			sock.close();
		
			

		}//main()----------------
	}///////////////////////////////////
