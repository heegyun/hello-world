package my.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * 멀티 스레드 서버란: 스레드를 여러개 사용한다는 뜻. 
 * MultiServerMenssenger 클래스는 한번만 생성되지만 MultiServerRunnable 클래스는 접속되는 클라이언트 수 만큼 만든다.
 * 모든 채팅 클라이언트마다 대응하는 스레드 클래스의 객체가 있다는 것이다.
 *
 */
public class MultiServerMessenger {
	
	public ArrayList<MultiServerRunnable> arryList;
	public Socket socket;
	
	public MultiServerMessenger() throws IOException {
		arryList = new ArrayList<MultiServerRunnable>();
		ServerSocket ser = new ServerSocket(7777);
		MultiServerRunnable mst = null;
		while(true){
			socket = ser.accept(); 
			System.out.println("Client Access  is detected:" + socket);
			// 소켓을 Runnable 생성자에게 인수로 넘겨서 스레드런어블 객체를 만들게 한다.
			mst = new MultiServerRunnable(socket);
			arryList.add(mst);
			Thread thread = new Thread(mst);
			thread.start();
		}
		
	}//생성자..
	
	public static void main(String[] args) throws IOException {
		new MultiServerMessenger();
	}
	
	
	
	class MultiServerRunnable implements Runnable {

		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		public MultiServerRunnable(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public synchronized void run() {
			boolean isStop =false;
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				// 채팅 클라이언트가 데이터를 보내면 처리해준다. 대화 내용을 계속 들어라.
				while(!isStop) {
					String message = dis.readUTF();
					System.out.println("messageBefore: " + message);
					//대화내용을 듣고, 모든 클라이언트에게 메세지를 뿌려주는 메소드
					broadCastingWithoutMe(message);
				}
				arryList.remove(this);
				System.out.println("Connection is dead by user");
				System.out.println("ListSize:" + arryList.size());
			}catch(IOException  e) {
				arryList.remove(this);
				System.out.println("Connection is dead by system");
				System.out.println("error: " + e);
				System.out.println("ListSize:" + arryList.size());
				
			}
			
			
		}

		// arraylist에서 한꺼번에 꺼내서 뿌려주어야 한다.
		private void broadCastingWithoutMe(String message) throws IOException {
			for(MultiServerRunnable mst: arryList) {
				if(mst!=this) {
					System.out.println("mst:" + mst.toString());
					mst.send(message);
				}
			}
			
		}

		private void send(String message) throws IOException {
			System.out.println("send[message]:" + message);
			dos.writeUTF(message);
		}
		
	}

}
