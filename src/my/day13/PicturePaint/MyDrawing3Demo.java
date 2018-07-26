package my.day13.PicturePaint;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyDrawing3Demo extends Frame {
	Panel p1, p2;
	Button red, green, blue, large, small, clear, allClear;
	MyCanvas can;
	public MyDrawing3Demo(){
		super("::    ◆그림판 v1.1◆    ::");
		p1=new Panel();
		p1.setBackground(Color.blue);
		add(p1,"North");

		//Anonymous 클래스 만들기---
		p2=new Panel(){
			public Insets getInsets(){
				return new Insets(40,20,20,20);		
			}//-------------
		};
		p2.setBackground(Color.DARK_GRAY);
		add(p2,"Center");

		//버튼 만들어  p1에 부착---
		p1.add(red=new Button("RED"));
		p1.add(green=new Button("GREEN"));
		p1.add(blue=new Button("BLUE"));
		p1.add(large=new Button("크게"));
		p1.add(small=new Button("작게"));
		p1.add(clear=new Button("지우기"));
		p1.add(allClear=new Button("모두 지우기"));

		//캔버스 부착----
		can=new MyCanvas();
		can.setSize(300,300);
		can.setBackground(Color.WHITE);
		p2.add(can);

		//버튼과 캔버스에 리스너 부착----
		MyHandler my=new MyHandler();
		red.addActionListener(my);
		green.addActionListener(my);
		blue.addActionListener(my);
		large.addActionListener(my);
		small.addActionListener(my);
		clear.addActionListener(my);
		allClear.addActionListener(my);
		can.addMouseMotionListener(my);

		addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}//생성자-------
	//이벤트 핸들러-이너클래스----
	class MyHandler extends MouseMotionAdapter implements ActionListener {
		public void mouseDragged(MouseEvent e){
			can.x=e.getX();
			can.y=e.getY();
			can.repaint();
		}//-----
		public void actionPerformed(ActionEvent e){
			Object o=e.getSource();
			can.flag=0;//flag를 0으로 주어 그리기가 기본작업이 되게 한다.
			if(o==red){
				can.cr=Color.red;
			}else if(o==green){
				can.cr=Color.green;
			}else if(o==blue){
				can.cr=Color.blue;
			}else if(o==large){
				can.w++;//can.w+=2;
				can.h++;
			}else if(o==small){
				if(can.w >3){//3이하로는 더 작아지지 않게끔...
					can.w--;
					can.h--;
				}
			}else if(o==clear){
				can.cr=can.getBackground();
			}else if(o==allClear){
				can.flag=1;
				can.repaint();
			}
			
		}//------

	}//////////////////////////////////
	
	class MyCanvas extends Canvas {
		int x=-13, y=-13, w=7, h=7;
		Color cr=Color.black;
		int flag=0;
		public void paint(Graphics g){
			//1. 그리기
			if(flag==0){
				g.setColor(cr);
				g.fillOval(x, y, w, h);
			}else if(flag==1){
				//2. 모두 지우기..
				g.clearRect(0,0,300,300);
			}
		}//paint()-------
		public void update(Graphics g){
			paint(g);
		}//update()-----

	}/////////////////////////////

	public static void main(String[] args) {
		MyDrawing3Demo d=new MyDrawing3Demo();
		d.setSize(500,500);
		d.setVisible(true);
	}
}//////////////////////////////

