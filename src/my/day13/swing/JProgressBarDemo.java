package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class  JProgressBarDemo extends JFrame {
	JProgressBar pb;
	JSlider sl, sl2, sl3;
	JButton bt;
	JPanel p1,p2, centerP;
	Canvas can;
	int blue;

	public JProgressBarDemo(){
		super(":: JProgressBarDemo ::");
		Container cp=getContentPane();
		p1=new JPanel(new BorderLayout());
		cp.add(p1,"North");
		pb=new JProgressBar(0,200);
		p1.add(pb,"Center");
		bt=new JButton("진행");
		p1.add(bt,"East");

		pb.setStringPainted(true);
		//상태바에 퍼센티지가 표시됨.

		sl=new JSlider();
		cp.add(sl,"South");

		sl2=new JSlider(JSlider.VERTICAL,0,100, 20);
												//최소값, 최대값, 막대바 위치
		cp.add(sl2, "West");
		sl2.setMajorTickSpacing(10);//큰눈금을 10단위로..
		sl2.setMinorTickSpacing(5);//작은 눈금 5단위로...
		sl2.setPaintTicks(true);//눈금이 나타나도록 true값을 주자.
		sl2.setPaintLabels(true);//눈금 수치 나타나도록...

		//sl3를 동쪽에 붙이자. 최소값:0 최대값:255
		sl3=new JSlider(JSlider.VERTICAL,0,255, 50);
		cp.add(sl3,"East");
		sl3.setMajorTickSpacing(50);
		sl3.setMinorTickSpacing(25);
		sl3.setPaintTicks(true);
		sl3.setPaintLabels(true);
		
		//centerP패널을 중앙에 붙인다.
		centerP=new JPanel();
		cp.add(centerP,"Center");

		//Canvas 생성해서 centerP에 붙이자.
		can=new Canvas();
		centerP.add(can);
		//캔버스 생상을 new Color( 200, 80, 슬라이더값으로)
		can.setSize(200,200);
		can.setBackground(new Color(200,80,blue));
		//JSlider를 조정할 때 마다 값을 얻어야 함-->blue값
		//JSlider의 이벤트 처리

		MyHandler my=new MyHandler();
		bt.addActionListener(my);
		sl3.addChangeListener(my);

		addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}//생성자-------


	class MyThread extends Thread
	{
		public void run(){
			for (int i=pb.getMinimum();i<=pb.getMaximum() ;i++ )
			{
				pb.setValue(pb.getMaximum()-i);
				try
				{
					Thread.sleep(100);//0.1초간
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}//for-----
		}//run()-----------
	}///////////////
	class MyHandler implements ActionListener, ChangeListener
	{
		public void stateChanged(ChangeEvent e){
			Object o=e.getSource();
			if(o==sl3){
				blue=sl3.getValue();
				setTitle("blue: "+blue);
				can.setBackground(new Color(200,80,blue));
			}
		}//----------------------
		public void actionPerformed(ActionEvent e){
			Object o=e.getSource();
			if(o==bt){
				///스레드 동작...
				MyThread tr=new MyThread();
				tr.start();
			}
		}
	}///////////////////

	public static void main(String[] args) 
	{
		JProgressBarDemo d=new JProgressBarDemo();
		d.setSize(500,500);
		d.setVisible(true);
	}
}
