package my.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFrameMessenger  extends JFrame implements  ActionListener {

	public Socket socket;
	public DataInputStream dis;
	public DataOutputStream dos;
	
	private JTextArea jta;
	private JPanel jp;
	private JTextField jtx;
	private JButton jbtn;
	
	public JFrameMessenger() {
		super();
		initGUI();
		startThread();
	}
	

	private void initGUI() {
		setTitle("멀티 채팅 클라이언트");
		jta = new JTextArea();
		jp = new JPanel();	
		jtx = new JTextField(20);
		jbtn = new JButton("전송");
		jbtn.addActionListener(this);
		jp.add(jtx);
		jp.add(jbtn);
		add(jta,"Center");
		add(jp,"South");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		this.setSize(400,300);
		
	}

	private void startThread() {
		new Thread() {
			public void run() {
				try {
					socket = new Socket("203.237.143.127",7777);
					System.out.println("Connected...");
					dos = new DataOutputStream(socket.getOutputStream());
					dis = new DataInputStream(socket.getInputStream());
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				
				// 채팅 서버에서 보내오는 데이터를 받아 데이터를 textarea에 출력한다.
				while(true) {
					try {
						String message = (String)dis.readUTF();
						jta.setText("상대방:" + message+"\n" + jta.getText());
						System.out.println("message:" + message);
					}catch(IOException e) {
						
					}
				}
				
			}
		}.start();
		
	}


	public static void main(String[] args) {
		JFrameMessenger jfm = new JFrameMessenger();
		jfm.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object obj = arg0.getSource();
		
		if(obj==jbtn) {
			try {
				dos.writeUTF(jtx.getText().toString());
				jta.setText("나: " + jtx.getText()+"\n" + jta.getText());
				jtx.setText("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}



}
