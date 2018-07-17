package my.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConsolChatServer implements Runnable {

	ServerSocket ser;
	Socket socket;

	public ConsolChatServer() {
		try {
			ser = new ServerSocket(5555);
			System.out.println("서버대기중");
			socket = ser.accept();
			System.out.println("클라이언트와 연결 성공..");
			// 클라이언트와 통신할 스트림 생성..
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			String msg = "";
			////////////////////////////////////////
			Thread tr = new Thread(this);
			tr.start();
			///////////////////////////////////////

			// 클라이언트에게 메세지 전달
			while ((msg = br.readLine()) != null) {
				pw.println(msg);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ConsolChatServer();

	}

	@Override
	public void run() {
		// 클라이언트가 보내는 메세지를 계속 받아서 자기 콘솔에 출력
		try {
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String clientMsg;

			while (true) {
				clientMsg = br.readLine();
				System.out.println("From Client =>> " + clientMsg);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}