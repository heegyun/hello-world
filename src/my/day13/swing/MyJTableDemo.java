package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class  MyJTableDemo extends JFrame{
	MyTableModel model;//자료구조(data)를 갖는 부분..
	JTable table;//View
	public MyJTableDemo(){
		super(":: MyJTableDemo ::");

		//1. 고유의 모델 객체 생성
		model=new MyTableModel();
		//2. View 컴포넌트 생성해서 1번 모델과 연결...
		table=new JTable(model);
		Container cp=getContentPane();
		cp.add(new JScrollPane(table),"Center");
		//테이블의 행의 높이를 지정
		table.setRowHeight(30);
		table.setSelectionBackground(Color.yellow);
		table.setSelectionForeground(Color.blue);

		javax.swing.table.JTableHeader header=table.getTableHeader();
		header.setReorderingAllowed(false);
		//컬럼 헤더들이 재정렬이 안되게끔 false를 줘 막는다.

		addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}//생성자-------
	public static void main(String[] args) 
	{
		MyJTableDemo d=new MyJTableDemo();
		d.setSize(500,500);
		d.setVisible(true);
	}
}
