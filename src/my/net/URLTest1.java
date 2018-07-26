package my.net;

import java.net.*;
import java.io.*;
import static java.lang.System.out;

class  URLTest1
{
	public static void main(String[] args) 
	{
		try{
			URL url=new URL(args[0]);
			//명령줄인수로 완전한 형태-http://www.naver.com
			//ftp://ftp.kaist.ac.kr/pub/
			//http://www.kbs.co.kr
			//file:///c:\myjava\test.html
			//http://www.hyundai.or.kr/intro/president.asp?sgubun=1
			out.println("Protocol: "+url.getProtocol());
			out.println("Host:  "+url.getHost());
			out.println("Port:  "+url.getPort());
			out.println("File: "+url.getFile());
			//포트가 -1을 반환하는 경우가 있는데, 이것은 -1 번으로
			//url이 접속을 시도하는 것이 아니라 프로토콜에
			//해당하는 디폴트 포트로 접속이 일어난다는 점을 의미
			
			InputStream is=url.openStream();
			//openStream()메소드를 이용하면 URL이 위치한 곳과
			//자동으로 접속이 일어나고, 결과로 InputStream이 반환
			//된다.
			BufferedReader br
				=new BufferedReader(new InputStreamReader(is));
			String contents="";
			while((contents=br.readLine())!=null){
				out.println(contents);
			}
			br.close();
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}//main()-----------------
}