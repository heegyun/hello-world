package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**

기   능: GlassPane 을 사용해보자.
*/

public class  GlassPaneDemo extends JFrame {
	public GlassPaneDemo(){
		super(":: GlassPaneDemo ::");
		//ContentPane에 JTextArea부착
		Container cp=getContentPane();
		cp.add(new JScrollPane(new JTextArea()));

		//GlassPane을 얻어오자.
		JComponent gp=(JComponent)getGlassPane();
		gp.setLayout(null);
		JPanel p1=new JPanel(new GridLayout(0,1,5,5));
		gp.add(p1);
		p1.setBounds(200,10,80, 100);

		JButton btNew=new JButton("NEW");
		btNew.setBackground(Color.blue);
		btNew.setForeground(Color.white);
		p1.add(btNew);

		JButton btCopy=new JButton("COPY");
		btCopy.setBackground(Color.red);
		btCopy.setForeground(Color.white);
		p1.add(btCopy);

		JButton btPaste=new JButton("PASTE");
		btPaste.setBackground(Color.yellow);
		btPaste.setForeground(Color.gray);
		p1.add(btPaste);

		gp.setVisible(true);//GlassPane도 setVisible(true)줘야 나온다.
		p1.setOpaque(false);//투명하게...
		btPaste.setOpaque(false);

		addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}//생성자-------
	public static void main(String[] args) 
	{
		GlassPaneDemo d=new GlassPaneDemo();
		d.setSize(500,500);
		d.setVisible(true);
	}
}
