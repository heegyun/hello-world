package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

public class JTextFieldDemo extends JFrame implements ActionListener{

	JTextField tf1, tf2;
	JButton bt;
	JLabel res;
	JProgressBar pb;
	JPasswordField pwd;

	public JTextFieldDemo(){
		super(":::JTextFieldDemo:::");
		makeGui();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//생성자------
	public void makeGui(){
		Container cp=getContentPane();
		cp.setLayout(new GridLayout(0,1));
						//행은 가변적, 열은 1열로 고정
		tf1=new JTextField();
		tf2=new JTextField();
		cp.add(tf1);
		cp.add(tf2);
		//프로그레스바 생성및 부착
		pb=new JProgressBar();
		cp.add(pb);
		pb.setStringPainted(true);

		bt=new JButton("COPY", 
						new ImageIcon("./img/img06.gif"));
		cp.add(bt);
		ImageIcon ic=new ImageIcon("./img/img05.gif");
		res=new JLabel("결과",ic, JLabel.CENTER);
		JScrollPane sp=new JScrollPane(res);
		//라벨에 스크롤바를 붙인다.
		cp.add(sp);
		tf1.setBorder(new TitledBorder("원본파일(이미지파일)"));
		tf2.setBorder(new TitledBorder("목적파일"));
		bt.addActionListener(this);
	}//makeGui()--------------
	public void actionPerformed(ActionEvent e){
		Object o=e.getSource();
		if(o==bt){
			//fileCopy();
			Thread tr=new MyThread();
			tr.start();
		}
	}//-------------------------
	class MyThread extends Thread
	{
		public void run(){
			
			fileCopy();
			
		}
	}/////////////

	public void fileCopy(){
		FileInputStream fis=null;
		FileOutputStream fos=null;
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;	
		try
		{
			String source=tf1.getText().trim();
			String target=tf2.getText().trim();
			File sourceFile=new File(source);
			File targetFile=new File(target);
			//스트림 연결해서 읽고 쓰고하자.
			fis=new FileInputStream(sourceFile);
			bis=new BufferedInputStream(fis);
			fos=new FileOutputStream(targetFile);
			bos=new BufferedOutputStream(fos);
			int n=0;
			int count=0;
			long filesize=sourceFile.length();//파일크기
			byte ba[]=new byte[1024];
			while((n=bis.read(ba))!=-1){
				count+=n;//읽은 바이트수..
				//프로그레스바에 설정
				//int per=(읽은바이트수/전체파일크기)*100 
				pb.setValue(count);
				int per=(int)(count*100/filesize);
				pb.setString((int)(pb.getPercentComplete()*100)
									+" % 진행");
				bos.write(ba,0,n);
				bos.flush();
				try	{
					Thread.sleep(100);//0.1초
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}//while-----------
			bis.close(); bos.close();
			fis.close(); fos.close();
			//라벨의 아이콘 변경...
			ImageIcon i=new ImageIcon(target);
			res.setIcon(i);

			// ./img/img01.gif----> ./img/target.gif
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(this,
												e.getMessage());
		}
	}//fileCopy()--------------
	public static void main(String[] args) 
	{
		JTextFieldDemo jfc=new JTextFieldDemo();
		jfc.setSize(300,400);
		jfc.setVisible(true);
	}
}