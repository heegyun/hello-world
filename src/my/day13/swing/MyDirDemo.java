package my.day13.swing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
/**
기   능: File클래스 활용, 스트림 활용
*/
public class  MyDirDemo extends JFrame
implements ActionListener, ListSelectionListener{
	JTextField tfDir;
	JList lst;
	File fileList[];//디렉토리의 파일 목록을 가질 변수
	JTextArea ta;
	JButton btSave, btClear, btColor;
	JComboBox combo;
	String [] fonts;

	BufferedReader br;
	BufferedWriter bw;

	public MyDirDemo(){
		super(":: MyDirDemo ::");
		Container cp=getContentPane();
		tfDir=new JTextField();
		cp.add(tfDir,"North");
		tfDir.setBorder(new TitledBorder("디렉토리명을 입력하세요"));

		lst=new JList();//디렉토리 파일 목록을 여기에 보여주자.
		cp.add(new JScrollPane(lst),"West");
		lst.setBorder(new TitledBorder("파일 목록"));

		//ta부착
		ta=new JTextArea();
		cp.add(new JScrollPane(ta),"Center");

		//아래(South)에 패널 부착
		JPanel p1=new JPanel();
		cp.add(p1,"South");
		p1.add(btSave=new JButton("SAVE"));
		p1.add(btClear=new JButton("CLEAR"));
		p1.add(btColor=new JButton("COLOR"));
		//시스템 서체를 가져오는 사용자 정의 메소드 호출
		fonts=getSystemFonts();
		combo=new JComboBox(fonts);
		p1.add(new JScrollPane(combo));

		//리스너 부착----
		tfDir.addActionListener(this);
		btSave.addActionListener(this);
		btClear.addActionListener(this);
		btColor.addActionListener(this);
		//JList에는 어떤 리스너 부착??
		lst.addListSelectionListener(this);


		addWindowListener(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}//생성자-------

	public void valueChanged(ListSelectionEvent e){
		Object o=e.getSource();
		if(o==lst){
			//1. 리스트에서 선택한 파일명을 얻어오자.
			//Object getSelectedValue()
			//boolean getValueIsAdjusting() 
			boolean res=e.getValueIsAdjusting();
			System.out.println("res: "+res);
			//마우스 클릭할 때true, 마우스 뗄때 false를 리턴.
			if(!res){		
				try{
					readFile();//사용자 정의 메소드 호출..
				}catch(IOException ex){
					JOptionPane.showMessageDialog(this,
						"Error: "+ex.getMessage());
				}
			}//if----------
		}
	}//--------------
	public String[] getSystemFonts(){
			GraphicsEnvironment ge=
				  GraphicsEnvironment.getLocalGraphicsEnvironment();
					String[] f=ge.getAvailableFontFamilyNames();
					//for(int i=0;i<f.length;i++)
					//System.out.println(f[i]);
		return f;
	}//-----------------
	public void readFile() throws IOException{
		ta.setText("");//읽기전에 ta를 빈문자열로 초기화...
		Object obj=lst.getSelectedValue();
		
			//setTitle(obj.toString());
			File selFile=(File)obj;
	if(selFile !=null){
			System.out.println(selFile);
			//2. 그 파일이 java 또는  txt파일이라면
			String fname=selFile.getName();
			if(fname.endsWith(".java")||fname.endsWith(".txt")){
			//3. 1번 파일의 절대경로를 구한뒤
				String path=obj.toString();
			//4. 파일을 읽는 노드스트림과 연결한다.
				FileReader fr=new FileReader(selFile);
			//5. BufferedReader로 필터링...
			br=new BufferedReader(fr);
			//6. 반복문 돌면서 줄단위로 읽어온 내용을
			//7. ta에 append()하자.	
			String line="";
			while((line=br.readLine())!=null){
				ta.append(line+"\n");
			}//while----
			//8. 자원 반납
			if(br!=null) br.close();
			if(fr!=null) fr.close();

			}else{
				JOptionPane.showMessageDialog(this,
							"선택한 파일이 디렉토리이거나 읽기 힘든 파일입니다");
			}
	  }//if---널체크 if문---
	}//readFile()--------------

	public void actionPerformed(ActionEvent e){
		Object o=e.getSource();
		if(o==tfDir){
			//1. 사용자가 입력한 디렉토리명을 얻어온다.
			String dirName=tfDir.getText().trim();
			//2. 1에서 얻어온 문자열을 넣은 File객체 생성
			File dir=new File(dirName);
			//3. 디렉토리인지 아닌지 판별...
			if(dir.isDirectory()){
			//4. 디렉토리라면...해당 디렉토리의 파일 목록을 얻어오자.
				fileList=dir.listFiles();
				//String[] flist=dir.list();
				//File[] flist=dir.listFiles();
			}//if------
			//5. 얻어온 파일 목록을 JList에 붙이자...
			lst.setListData(fileList);
		}else if(o==btSave){
			saveFile();///사용자 정의 메소드 호출
		}else if(o==btClear){
			ta.setText("");
		}else if(o==btColor){
			changeTxtColor();//사용자 정의 메소드 호출
		}
	}//---------------
	public void changeTxtColor(){
		//1. JColorChooser객체의 메소드(showXXX())를 호출하여
		//   색상 다이얼로그를 띄운다.
		//static Color showDialog(Component component, 
		//									String title, Color initialColor) 
		Color cr=JColorChooser.showDialog(this, "", Color.blue);
	
		//2. 사용자가 선택한 색상을 얻어온다.
		//3. ta에 그 색상을 글자색으로 적용한다.
		ta.setForeground(cr);

	}//changeTxtColor()--------
	public void saveFile(){
		//1. JFileChooser 객체를 생성하여 저장다이얼로그를 보여준다.
			JFileChooser jc=new JFileChooser();
			jc.showSaveDialog(this);
			//2. 1에서 사용자가 입력한 파일명을 얻어와
			// File getSelectedFile()  
			File saveFile=jc.getSelectedFile();
			//취소를 누르면 null 을 리턴함...따라서 널체크 필수..
			if(saveFile!=null)
				setTitle(saveFile.getName());
		try{
			String contents=ta.getText();
			if(contents==null|| contents.trim().equals("")){
				JOptionPane.showMessageDialog(this,"저장할 내용을"
													+"입력해야 합니다.");
				ta.requestFocus();
				//ta에 포커스 주기..
				return;
			}//if--
			//3. 파일에 쓰는 스트림과 노드 연결을 하고...
			FileWriter fw=new FileWriter(saveFile);
			//4. BufferedWriter로 필터링을 한 뒤		
			bw=new BufferedWriter(fw);
			//5. ta(텍스트에리어)의 내용을 4번 스트림을 통해
			//   파일에 쓰기 작업을 한다. 
			
			bw.write(contents);
			bw.flush();
			//6. 연결된 스트림 닫기.
			if(bw!=null) bw.close();
			if(fw!=null) fw.close();
		}catch(IOException ex){
			System.out.println("IO Error: "+ex.getMessage());
		}

	}//saveFile()---------

	public static void main(String[] args) 
	{
		MyDirDemo d=new MyDirDemo();
		d.setSize(800,500);
		d.setVisible(true);
	}
}
