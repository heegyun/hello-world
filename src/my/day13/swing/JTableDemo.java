package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;


public class  JTableDemo extends JFrame {
	JTable table;//View -(Visual Component)
	Object data[][]={  //Model파트-data를 가지는 부분
{"홍길동", new Integer(1), new ImageIcon("./img/img01.gif"), new Boolean(true) },
{"김길동", new Integer(2), new ImageIcon("./img/img02.gif"), new Boolean(false) },
{"정길동", new Integer(3), new ImageIcon("./img/img03.gif"), new Boolean(true) },
{"강길동", new Integer(4), new ImageIcon("./img/img04.gif"), new Boolean(false) }
};
	String[] colNames={"이름","번호","캐릭터","사람인가?"};
	//Controller : 모델과 view부분을 연결하여 동작방식이나 이벤트 등등의
	//				   제어를 하는 부분.
	//					XXXRenderer...XXXEditor...

	public JTableDemo(){
		super(":: JTableDemo ::");
		Container cp=getContentPane();
		//table=new JTable(3,2);//3행2열 형태의 테이블
		//JTable(Object[][] rowData, Object[] columnNames) 
		table=new JTable(data, colNames);
		cp.add(new JScrollPane(table));

		addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}//생성자-------
	public static void main(String[] args) 
	{
		JTableDemo d=new JTableDemo();
		d.setSize(500,500);
		d.setVisible(true);
	}
}
