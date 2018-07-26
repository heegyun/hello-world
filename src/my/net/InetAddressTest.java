package my.net;

import java.net.*;
//InetAddress클래스 사용:ip를 추상화한 클래스
class  InetAddressTest {
	public static void main(String[] args) 
	{
		InetAddress inet=null;
		try{
			inet=InetAddress.getByName(args[0]);
			System.out.println("연결 됐습니다.");
			System.out.println("getHostName: "+inet.getHostName());
			System.out.println("getHostAddress: "
								+inet.getHostAddress());
			System.out.println("getLocalHost: "+inet.getLocalHost());

			InetAddress[] inet2
				=InetAddress.getAllByName("www.hanmail.net");

			for (int i=0;i<inet2.length ;i++ )
			{
				System.out.println(inet2[i]);
			}

		}catch(UnknownHostException e){
			e.printStackTrace();
		}	
	}//main()-----------
}
