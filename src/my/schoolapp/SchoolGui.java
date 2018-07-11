package my.schoolapp;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SchoolGui extends Frame {

	SchoolApp sa = new SchoolApp();
	// 업무처리 로직을 가진 클래스-콘솔 애플리케이션
	Person p;
	TextArea ta;
	Button reg, del, find, print, save;
	// 등록, 삭제, 검색, 출력, 저장
	Button btStudent, btTeacher, btStaff, btFindOk, btFindClose;
	// 학생, 교사, 직원, 검색, 검색종료
	Button btOk, btCancel, btClose;

	Dialog dial1, dial2, dial3;
	// 대화상자 3개 만들기...
	Label lbName, lbAddr, lbId, lbPub;
	TextField tfName, tfAddr, tfId, tfPub, tfFindName;

	public SchoolGui() {
		super(":::학사관리 프로그램 v1.1:::");
		makeGui();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setSize(500, 500);
		setVisible(true);
	}// 생성자-----------

	/** GUI를 구성하는 메소드 */
	public void makeGui() {
		ta = new TextArea();
		add(ta, "Center");

		Panel pn = new Panel(new GridLayout(0, 1, 10, 10)) {
			// 패널 바깥 여백을 설정하는 메소드 오버라이딩
			public Insets getInsets() {
				return new Insets(10, 10, 10, 10);
			}
		};
		// 열은 1열로 고정시키고
		// 행은 컴포넌트 부착하는 것에 따라
		// 유동적으로..
		pn.setBackground(Color.orange);
		add(pn, "East");
		pn.add(reg = new Button("등  록"));
		pn.add(find = new Button("검  색"));
		pn.add(del = new Button("삭  제"));
		pn.add(print = new Button("출  력"));
		pn.add(save = new Button("저  장"));

		// Dialog 1만들기
		dial1 = new Dialog(this, true);
		dial2 = new Dialog(this, true);
		dial3 = new Dialog(this, "**검색**", true);

		// dial1에 컴포넌트 부착---
		btStudent = new Button("학생");
		btTeacher = new Button("교사");
		btStaff = new Button("직원");
		btClose = new Button("닫기");

		dial1.setTitle("**등록**");
		dial1.setLayout(new GridLayout(1, 0));
		dial1.add(btStudent);
		dial1.add(btTeacher);
		dial1.add(btStaff);
		dial1.add(btClose);

		// dial2에 컴포넌트 부착---
		Panel dp1 = new Panel();
		Panel dp2 = new Panel();
		Panel dp3 = new Panel();
		dp1.setLayout(new GridLayout(4, 1));
		dp2.setLayout(new GridLayout(4, 1));

		dial2.add(dp1, "West");// 라벨을 붙일 패널
		dial2.add(dp2, "Center");// 텍스트필드 붙일 패널
		dial2.add(dp3, "South");// 버튼 붙일 패널

		dp1.add(lbName = new Label("이름"));
		dp1.add(lbAddr = new Label("주소"));
		dp1.add(lbId = new Label("ID"));
		dp1.add(lbPub = new Label("기타"));

		dp2.add(tfName = new TextField());
		dp2.add(tfAddr = new TextField());
		dp2.add(tfId = new TextField());
		dp2.add(tfPub = new TextField());

		dp3.add(btOk = new Button("처리"));
		dp3.add(btCancel = new Button("취소"));

		// dial3에 검색 또는 삭제 관련 컴포넌트 부착
		Label lb = new Label("이름을 입력하세요");
		dial3.add(lb, "North");
		Panel dp4 = new Panel();
		dial3.add(dp4, "Center");
		tfFindName = new TextField(20);
		dp4.add(tfFindName);
		btFindOk = new Button("확인");
		btFindClose = new Button("닫기");
		dp4.add(btFindOk);
		dp4.add(btFindClose);

		// 리스너 부착------
		MyHandler my = new MyHandler();// 이벤트 처리 클래스
		reg.addActionListener(my);
		find.addActionListener(my);
		del.addActionListener(my);
		print.addActionListener(my);
		save.addActionListener(my);

		btStudent.addActionListener(my);
		btTeacher.addActionListener(my);
		btStaff.addActionListener(my);
		btOk.addActionListener(my);
		btCancel.addActionListener(my);
		btClose.addActionListener(my);

		btFindOk.addActionListener(my);
		btFindClose.addActionListener(my);

		// ta를 편집 불가능하게끔...
		ta.setEditable(false);

	}// makeGui()--------

	/** 이벤트를 처리하는 클래스-Inner Member Class */
	class MyHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o == reg) {// 등록
				dial1.pack();
				// 각 컴포넌트의 preferred size 만큼 압축하여
				// 크기를 정한다.
				dial1.setLocation(500, 0);// x,y좌표(500,0)
				dial1.setVisible(true);

			} else if (o == find) {// 검색
				dial3.setTitle("**검색**");
				dial3.pack();
				dial3.setVisible(true);

			} else if (o == del) {// 삭제
				dial3.setTitle("**삭제**");
				dial3.pack();
				dial3.setVisible(true);

			} else if (o == print) {// 출력
				String str = sa.printAll();
				ta.append(str + "\n");

			} else if (o == save) {// 저장
				// 1>, 2>
				FileDialog fd = new FileDialog(SchoolGui.this, "파일저장", FileDialog.SAVE);
				fd.setVisible(true);
				// 파일 경로 얻기
				String dir = fd.getDirectory();
				String file = fd.getFile();
				String filename = dir + file;
				setTitle(filename);

				try {
					FileWriter fw = new FileWriter(filename);
					String content = ta.getText();
					// ta로부터 가져온 내용을 fw를 통해 쓴다.
					fw.write(content);
					fw.flush();
					fw.close();
					setTitle("저장 완료");
					ta.append("저장 완료!!!->" + filename + "\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/*
				 * 텍스트에리어에 출력된 내용을 파일로 저장하자. 1> FileDialog를 저장 모드로 띄운다. 2> 파일명과 디렉토리명을 얻어와서 3>
				 * java.io.FileWriter클래스 생성자에 파일 절대경로를 넣어주고 4> ta의 내용을 FileWriter의 write()메소드 를
				 * 이용해서 쓰기 작업을 한다. ...이때 주의...FileWriter의 write() 메소드 호출 후 반드시 flush()메소드를
				 * 호출해주자.
				 */

			} else if (o == btStudent) {// 학생
				dial2.setTitle("**학생 등록**");
				lbId.setText("학번");
				lbPub.setText("학급");
				dial2.pack();
				dial2.setLocation(500, 30);
				dial2.setVisible(true);

			} else if (o == btTeacher) {// 교사
				dial2.setTitle("**교사 등록**");
				lbId.setText("교번");
				lbPub.setText("과목");
				dial2.pack();
				dial2.setLocation(500, 30);
				dial2.setVisible(true);

			} else if (o == btStaff) {// 직원
				dial2.setTitle("**직원 등록**");
				lbId.setText("사번");
				lbPub.setText("부서");
				dial2.pack();
				dial2.setLocation(500, 30);
				dial2.setVisible(true);

			} else if (o == btOk) {
				// 사용자 정보 등록
				String name = tfName.getText();
				String addr = tfAddr.getText();
				String id = tfId.getText();

				String pub = tfPub.getText();
				// 어떤 객체이냐에 따라 호출할 메소드가 달라진다.

				String title = dial2.getTitle();
				System.out.println(title);
				String word = title.substring(2, 4);
				// 문자열 인덱스 2부터 4의 직전까지 잘라내어
				// 돌려준다.
				System.out.println(word);
				if (word.equals("학생")) {
					p = new Student();
					((Student) p).setClassName(pub);
				} else if (word.equals("교사")) {
					p = new Teacher();
					((Teacher) p).setSubject(pub);
				} else if (word.equals("직원")) {
					p = new Staff();
					((Staff) p).setDept(pub);
				}
				p.setName(name);
				p.setAddr(addr);
				p.setId(id);
				/////// 비즈니스 메소드///////////////
				sa.addPerson(p);
				////////////////////////////////////
				tfName.setText("");
				tfId.setText("");
				tfAddr.setText("");
				tfPub.setText("");

				// 텍스트필드에 포커스가 가게끔..
				tfName.requestFocus();

			} else if (o == btCancel) {// 취소
				dial2.dispose();
				tfName.setText("");
				tfId.setText("");
				tfAddr.setText("");
				tfPub.setText("");

			} else if (o == btClose) {
				// dial1 닫기
				dial1.dispose();

			}
			if (o == btFindOk) {// 검색(삭제)확인
				String title = dial3.getTitle();
				String word = title.substring(2, 4);
				// 제목으로 검색 대화창인지 삭제 대화창인지
				// 구별하기 위해 ..

				if (word.equals("검색")) {
					String name = tfFindName.getText();
					////////////////////////////////
					String msg = sa.findPerson(name);
					////////////////////////////////
					ta.append(msg + "\n");
				} else {
					String name = tfFindName.getText();
					/////////////////////////////////
					String msg = sa.removePerson(name);
					////////////////////////////////
					ta.append(msg + "\n");
				}
				tfFindName.setText("");

			} else if (o == btFindClose) {// 검색(삭제)창 닫기
				dial3.dispose();
			}

		}

	}//////////////////

	public static void main(String[] args) {
		new SchoolGui();
	}// main()---------------

}//////////////////////////////////////////////
