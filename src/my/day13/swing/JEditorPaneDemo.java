package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.net.*;
import java.io.*;

public class JEditorPaneDemo extends JFrame {
	JTextField tfLocation;
	JEditorPane epBrowse;
	
	public JEditorPaneDemo()
	{
		super("JEditorPane 브라우저");
		
		Container cp=getContentPane();
		
		JPanel lp=new JPanel(new BorderLayout());
		tfLocation=new JTextField();
		lp.add(new Label("URL : "), BorderLayout.WEST);
		lp.add(tfLocation,BorderLayout.CENTER);
		
		cp.add(lp,BorderLayout.NORTH);
		
		epBrowse = new JEditorPane();
		
		epBrowse.setEditable(false);
		
		JScrollPane sp=new JScrollPane(epBrowse);
		
		cp.add(sp,BorderLayout.CENTER);
		
		tfLocation.addActionListener(new ActionListener(){
										public void actionPerformed(ActionEvent e) {
											try{
												URL url=new URL(e.getActionCommand());
												epBrowse.setPage(url);
											}catch(IOException ie) {
													System.err.println("IOException Error");
												}
										}
									});
																
	
		epBrowse.addHyperlinkListener(new URLListener());
	}
	
	public static void main(String[] args){
		JEditorPaneDemo f=new JEditorPaneDemo();
		f.setSize(800,600);
		f.setVisible(true);
	}


	class URLListener implements HyperlinkListener
	{
		public void hyperlinkUpdate(HyperlinkEvent e)
		{
			if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
			{
				JEditorPane pane=(JEditorPane)e.getSource();
			
				if(e instanceof HTMLFrameHyperlinkEvent)
				{
					HTMLFrameHyperlinkEvent evt=(HTMLFrameHyperlinkEvent)e;
					HTMLDocument doc=(HTMLDocument)pane.getDocument();
					doc.processHTMLFrameHyperlinkEvent(evt);
				}
				else
				{
					try
					{
						tfLocation.setText(e.getURL().toExternalForm());
						pane.setPage(e.getURL());
					}catch(IOException ex)
						{
							System.err.println("IOException Error");
						}
				}
			}
		}
	}
}
