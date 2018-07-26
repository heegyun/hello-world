package my.net;

import java.net.*;
import java.io.*;
import java.util.*;

class  ChatServer
{
	Vector<ChatHandler> handlers;
	public ChatServer(int port)
		throws Exception{
		ServerSocket ser=new ServerSocket(port);
		handlers=new Vector<ChatHandler>(5,2);
		while(true){
			System.out.println("클라이언트 연결 기다림...");
			Socket s=ser.accept();
			System.out.println(s.getInetAddress()+"가 접속함..");
			//////////////////
			 ChatHandler chat=new ChatHandler(this, s);
			 //클과의 통신을 담당할 스레드
			 handlers.add(chat);
			chat.start();
			/////////////////

		}//while-----
	}//생성자-------------
	public static void main(String[] args) 
	{
		try{
			new ChatServer(7788);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}////////////////////////////
class ChatHandler extends Thread
{
	ChatServer server;
	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;
	boolean stop=false;

	public ChatHandler(ChatServer server, Socket s){
			this.server=server;
			sock=s;
			try{
				dis=new DataInputStream(sock.getInputStream());
				dos=new DataOutputStream(sock.getOutputStream());				
			}catch (Exception e)	{
				System.out.println(e.getMessage());
			}
	}//생성자------------
	public void run(){
		String logId=null;
		try{
		logId=dis.readUTF();//로그인한 클의 아이디를 얻어온다.
		broadcast("##["+logId+"]님이 입장하셨습니다.##");
		while(!stop){
			String msg=dis.readUTF();//대화내용 듣기
			broadcast(logId+">>"+msg);
		}//-------

		}catch(Exception e){
			broadcast(logId+"님과 접속이 차단됐습니다.");
			System.out.println(e.getMessage());
		}
	}//run()--------------
	/**모든 클라이언트에게 메시지를 뿌려주는 기능*/
	public synchronized void broadcast(String msg){
		Enumeration<ChatHandler> en	=server.handlers.elements();
		//벡터에 저장된 ChatThread를 한꺼번에 꺼내오자.
		while(en.hasMoreElements()){
			ChatHandler c=en.nextElement();
			try{
				c.dos.writeUTF(msg);
				c.dos.flush();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}//while----------

	}//broadcast()----------------
}///////////////////////////



