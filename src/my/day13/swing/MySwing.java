package my.day13.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class MySwing extends JFrame implements ActionListener
{
	JButton bt, bt2, bt3,bt4;
	public MySwing(){
		super(":::MySwing:::");
		bt=new JButton("나 스윙 버튼이야");
		
		//스윙에서는 컨테이너(JFrame, JDialog, JInternalFrame 등...)
		//에 직접 컴포넌트를 add()하지 못한다.
		//getContentPane()이란 메소드를 통해 컨테이너-컨텐트페인
		//을 얻어와 그 곳에 붙여여 함.
		Container con=getContentPane();
		//ContentPane
		con.setLayout(new GridLayout(1, 0));
		con.add(bt);
		
		ImageIcon icon1=new ImageIcon("./img/img01.gif");
		ImageIcon icon2=new ImageIcon("./img/img02.gif");
		ImageIcon icon3=new ImageIcon("./img/img03.gif");


		bt2=new JButton("OPEN", icon1);
		bt3=new JButton("INPUT", icon2);
		bt4=new JButton("CONFIRM", icon3);
		con.add(bt2);
		con.add(bt3);
		con.add(bt4);

		bt2.setVerticalTextPosition(JButton.BOTTOM);
		bt2.setHorizontalTextPosition(JButton.CENTER);

		bt3.setVerticalTextPosition(JButton.TOP);
		bt3.setHorizontalTextPosition(JButton.CENTER);
		bt3.setToolTipText("이건 풍선 도움말...");

		ImageIcon icon4=new ImageIcon("./img/img07.gif");
		bt4.setPressedIcon(icon4);
		bt4.setMnemonic('C');//단축키 alt+C
		
		//리스너 부착----
		bt.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);

		//각 버튼에 BORDER주기---
		bt.setBorder(new LineBorder(Color.yellow,3));
		bt2.setBorder(new TitledBorder("이건 제목이야"));
		bt3.setBorder(new BevelBorder(BevelBorder.LOWERED));
															//BevelBorder.RAISED
		bt4.setBorder(new EtchedBorder());


	
		//창닫기 처리---
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//생성자------

	public void actionPerformed(ActionEvent e){
		Object o=e.getSource();
		if(o==bt){
			JOptionPane.showMessageDialog(this, "환영합니다.");
		}else if(o==bt2){
			/*static void showMessageDialog(Component parentComponent,
					Object message, String title, int messageType)  */
			JOptionPane.showMessageDialog(bt2,"누르지마세요!",
			"경고", JOptionPane.WARNING_MESSAGE);

		}else if(o==bt3){
			//InputDialog를 띄워 "당신의 이름은?"이란 질문을 넣어주자.
			//사용자가 이름을 입력하면 그 이름이 프레임 제목에 올라가게...
			//static String showInputDialog(Object message) 
			String name=JOptionPane.showInputDialog("당신의 이름은?");
			setTitle(name);
		}else if(o==bt4){
			//ConfirmDialog를 띄워 "종료하시겠습니까?"를 넣어준뒤,
			//종료하겠다고 하면 종료시키자.
			//static int showConfirmDialog(Component parentComponent, 
			//				Object message, String title, int optionType)  
			int yn=JOptionPane.showConfirmDialog(bt4,
							"종료하시겠습니까?","", JOptionPane.YES_NO_OPTION);
			setTitle("yn="+yn);
			if(yn==JOptionPane.YES_OPTION)
			//if(yn==0)
				System.exit(0);
		}
	}//----------

	public static void main(String[] args) 
	{
		MySwing my=new MySwing();
		my.setSize(700,300);
		my.setVisible(true);
	}
}
