package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
�ۼ���: 2007.11.
�ۼ���: �鼺��
��   ��: 
*/
public class  MyMDIFrameDemo extends JFrame
{
	JButton open1, open2;
	JLabel lb;
	ImageIcon img, img2, img3;
	JInternalFrame child1, child2;
	//내부 프레임...프레임 안에 존재할 수 있는 프레임...Window 계열...

	public MyMDIFrameDemo(){
		super(":: MyMDIFrameDemo ::");
		Container cp=getContentPane();
		//컨텐트 페인에 내부 프레임을 붙일 예정...
		//그럴려면...좌표 잡아서 붙여야 함.
		//레이아웃 해제--null
		cp.setLayout(null);
		
		JPanel p1=new JPanel();
		
		p1.setBackground(Color.yellow);
		p1.setBounds(0,0, 700, 50);
		p1.add(open1=new JButton("Open Child1"));
		cp.add(p1);

		img=new ImageIcon("./img/img05.gif");
		img2=new ImageIcon("./img/img06.gif");
		img3=new ImageIcon("./img/img07.gif");

		child1=new JInternalFrame("child1",true, true, true, true);
											//resizable, closable,최대화, 최소화
		//내부 프레임--컨테이너..
		cp.add(child1);
		child1.setBounds(0,80,250,250);
		
		//내부프레임에 라벨 붙이기
		Container child1Con=child1.getContentPane();
		child1Con.add(lb=new JLabel(img));
		child1.setVisible(true);//내부 프레임도 프레임이기 때문에
									//반드시 setVisible(true)줘야 한다.

		//MyJInternalFrame객체 생성 및 부착
		child2=new MyJInternalFrame();
		cp.add(child2);
		child2.setBounds(300,300,250,250);
		child2.setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//생성자-------
	public static void main(String[] args) 
	{
		MyMDIFrameDemo d=new MyMDIFrameDemo();
		d.setSize(700,700);
		d.setVisible(true);
	}
}/////////////////////////////////
class MyJInternalFrame extends JInternalFrame
implements ActionListener, Runnable
{
	JButton bt;
	JProgressBar pb;
	JLabel res;
	int sum=0;

	public MyJInternalFrame(){
		super("child2",true,true,true);
		Container child2Con=getContentPane();
		child2Con.setLayout(new GridLayout(3,1, 10,10));
		
		pb=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		child2Con.add(pb);
		pb.setValue(100);
		pb.setStringPainted(true);

		bt=new JButton("진행");
		child2Con.add(bt);
		res=new JLabel("결과",JLabel.CENTER);
		child2Con.add(res);

		//리스너 부착
		bt.addActionListener(this);

	}//생성자----
	public void actionPerformed(ActionEvent e){
		Thread tr=new Thread(this);
		tr.start();
	}//---------------
	public void run(){
		//1~10까지 합산하면서...진행바 10%씩 감소...
		for(int i=0;i<=10;i++){
			sum+=i;
			//pb.setValue(100-(i*10));
			pb.setValue(pb.getValue()-10);
			try
			{
				Thread.sleep(100);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			res.setText("1부터 10의 합: "+sum);
			
		}//for------
		sum=0;
	}//---------------
}//////////////////////////////////
/*실습 문제
1] 진행버튼 누르면 1부터 10까지 합산된 결과가
    JLabel 문자열로 나오게 하자.
2] 아울러 프로그레스바가 처음에 100에 있다가
    합산이 진행될 수록 10%씩 줄어들도록 하자.
3] child2프레임이 사이즈 조정, 닫기, 최대화, 최소화가 되게끔

*/

