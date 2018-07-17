package my.day11;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class EchoClient {

Socket sock;
	
	public EchoClient() throws Exception{
		sock = new Socket("127.0.0.1",5000);
		System.out.println("서버와 연결 됨");
		InputStream is = sock.getInputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		BufferedReader key = new BufferedReader(
				new InputStreamReader(System.in));
		
		PrintWriter pw = new PrintWriter(sock.getOutputStream(),true);
		
		String receiveMsg, sendMsg;
		//서버가 보낸 메세지 읽어오기
		receiveMsg = br.readLine();
		System.out.println("From Server => " + receiveMsg);
		
		while((sendMsg=key.readLine())!=null){
			pw.println(sendMsg);
			receiveMsg = br.readLine();
			System.out.println("From Server => " + receiveMsg);
			
		}
		br.close();
		pw.close();
		key.close();
		sock.close();
				
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		new EchoClient();
		 
	}

}
