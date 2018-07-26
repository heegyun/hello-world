package my.day13.PicturePaint;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * 기 능: 마우스이벤트를 이용한 그림판 만들기1
 */
public class MyDrawingDemo extends Frame {
	int x = 50, y = 50;

	public MyDrawingDemo() {
		super(":: MyDrawingDemo ::");

		// 리스너 부착
		MyHandler my = new MyHandler();
		addMouseMotionListener(my);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}// 생성자-------

	class MyHandler extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			setTitle("x: " + x + ", y:" + y);
			// paint(new Graphics());
			// paint()메소드는 개발자가 직접 호출할 수 없다.
			// JVM 소관 사항...
			repaint();
			// 개발자가 repaint()를 호출하면-->|JVM --->update(g)--->paint(g)
			// 기존에 그린
			// 그림을 지우고
			// paint(g)를 호출
		}
	}///////////////////

	public void update(Graphics g) {
		// 1)배경색으로 지우고...
		// 2)paint(g);
		paint(g);
	}// update()----------

	// paint()메소드 오버라이딩-----
	public void paint(Graphics g) {
		// g.setColor(Color.blue);
		// g.fillOval(50,50, 100,100);
		g.setColor(Color.red);
		g.fillOval(x, y, 10, 10);

	}// paint()----------------

	public static void main(String[] args) {
		MyDrawingDemo d = new MyDrawingDemo();
		d.setSize(500, 500);
		d.setVisible(true);
	}
}
