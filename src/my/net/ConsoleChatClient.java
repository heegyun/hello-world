package my.net;

import java.net.*;
import java.io.*;
import static java.lang.System.out;

class  ConsoleChatClient implements Runnable{
String ip;
Socket sock;
int port=5555;
public ConsoleChatClient(String n) throws Exception{
	ip="172.16.10."+n;
	sock=new Socket(ip,port);
	out.println("##서버와 연결됨####");
	BufferedReader key
		=new BufferedReader(new InputStreamReader(System.in));
	PrintWriter pout=new PrintWriter(sock.getOutputStream(),true);
	Thread tr=new Thread(this);
	tr.start();//서버가 보내주는 메시지를 계속 듣는 스레드 동작
	String msg="";
	while((msg=key.readLine())!=null){
		pout.println(msg);
	}//while------
}//생성자---------
public void run(){
	try
	{
		InputStream is=sock.getInputStream();
		BufferedReader br=new BufferedReader(
						new InputStreamReader(is));
		String serverMsg="";
		while(true){
			serverMsg=br.readLine();
			out.println("From Server: "+serverMsg);
		}//while-----
	}
	catch (Exception e)
	{
		out.println("예외: "+e.getMessage());
	}
}//run()----------

public static void main(String[] args)  throws Exception
{
	new ConsoleChatClient(args[0]);
	
}
}
