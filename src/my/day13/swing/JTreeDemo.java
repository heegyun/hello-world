package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;

public class JTreeDemo extends JFrame {
	
	Object[] root = { "프로그램", "Database", "System" };
	JTree tree;// View

	public JTreeDemo() {
		super("::  JTreeDemo ::");
		Container cp = getContentPane();

		Vector<Vector> node1 = new Vector<Vector>() {
			public String toString() {
				return "JavaPro84";
			}
		};

		Vector<String> node2 = new Vector<String>() {
			public String toString() {
				return "Language";
			}
		};
		Vector<String> node3 = new Vector<String>() {
			public String toString() {
				return "DB";
			}
		};

		node1.add(node2);
		node1.add(node3);

		node2.add("JAVA");
		node2.add("JSP");
		node2.add("EJB");

		node3.add("Oracle");
		node3.add("MS-SQL");
		node3.add("mySQL");

		root[0] = node1;
		cp.add(new JScrollPane(tree = new JTree(root)));
		tree.setRootVisible(true);// 루트 설정

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}// 생성자-------

	public static void main(String[] args) {
		JTreeDemo d = new JTreeDemo();
		d.setSize(500, 500);
		d.setVisible(true);
	}
}
