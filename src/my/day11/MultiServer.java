package my.day11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
	
	/**
	 * 
	 *  클라이언트의 요청을 처리하고, 메세지 처리는
	 *   MultiServerRunnable 객체가 처리하도록 분리한다.
	 */

	ServerSocket ser;
	public ArrayList<MultiServerRunnable> arrlist  ;	
	MultiServerRunnable mr;
	Socket socket;
	
	public MultiServer(){
		
		try {
			arrlist = new ArrayList<MultiServerRunnable>();
			ser = new ServerSocket(5000);
			System.out.println("서버 대기중.");
			
			
			while(true){
				socket= ser.accept();
				
				System.out.println("연결 성공");
				mr = new MultiServerRunnable(socket);
				arrlist.add(mr);
				Thread thread = new Thread(mr);
				thread.start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
			
	
	public static void main(String[] args) {
		new MultiServer();

	}

	/*
	 * 클라이언트의 메세지 처리를 담당하는 스레드 구현 객체
	 */
	class MultiServerRunnable implements Runnable {
		Socket sock;
		DataInputStream dis;
		DataOutputStream dos;

		public MultiServerRunnable(Socket sock) {
			this.sock = sock;

		}

		// arraylist에 있는 객체들에게 동시에 메세지 출력하는 메소드
		public void broadCasting(String msg) throws IOException {
			for (MultiServerRunnable mr : arrlist) {
				if(mr!=this){
					System.out.println("Message: " + msg.toString());
					mr.send(msg);
				}
				
			}
		}

		public void send(String msg) throws IOException {
			System.out.println("send[Message]:" + msg);
			dos.writeUTF(msg);
			dos.flush();
		}

		@Override
		public synchronized void run() {
			boolean isStop=false;
			try {
				dis = new DataInputStream(sock.getInputStream());
				dos = new DataOutputStream(sock.getOutputStream());

				while (!isStop) {
					String msg = dis.readUTF().toString();
					System.out.println("Message: " + msg);
					broadCasting(msg);

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}//MultiServerRunnable---

}//MultiServer-----
