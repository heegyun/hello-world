package my.day11;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConsolChatClient implements Runnable {

	Socket sock;

	public ConsolChatClient() {
		try {
			sock = new Socket("127.0.0.1", 5555);
			System.out.println("서버와 연결 성공...");
			// 서버와 통신할 스트림 생성..
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);
			String msg = "";
			////////////////////////////////////////
			Thread tr = new Thread(this);
			tr.start();
			///////////////////////////////////////

			// 서버에게 메세지 전달
			while ((msg = br.readLine()) != null) {
				pw.println(msg);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new ConsolChatClient();

	}

	@Override
	public void run() {
		// 서버가 보내는 메세지를 계속 받아서 자기 콘솔에 출력
		try {
			InputStream is = sock.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String serverMsg;

			while (true) {
				serverMsg = br.readLine();
				System.out.println("From Server =>> " + serverMsg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}