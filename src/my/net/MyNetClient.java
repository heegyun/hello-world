package my.net;

import java.net.*;
import java.io.*;
class  MyNetClient
{
	public static void main(String[] args)
		throws Exception{
		//클라이언트에서는 Socket만 필요...
				//서버의 IP주소와 port번호가 필요
				Socket sock=new Socket("172.16.10.207",5000);		
				//연결되면 Socket객체가 생성되고...연결 안되면 예외 발생
				System.out.println("서버와 연결됨...");	
				InputStream is=sock.getInputStream();
				DataInputStream dis=new DataInputStream(is);
				String data=dis.readUTF();
				System.out.println("서버로부터>>"+data);
				int num=dis.readInt();
				System.out.println("서버로부터>>"+num);
				dis.close();
				is.close();
				sock.close();
			}//main()-----------------
		}////////////////////////////////////////////////////