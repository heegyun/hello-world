package my.schoolapp;

import static java.lang.System.out;

import java.util.InputMismatchException;
import java.util.Scanner;

/** Console Applicaton **/

public class SchoolApp {
	
	private Person[] p = new Person[30];// 30명 정원
	private int count; // 배열의 인덱스로 사용될 변수

	public Person[] getP() {
		return p;
	}

	public int getCount() {
		return count;
	}

	/** 메인 메뉴를 보여주는 메소드 * */
	public void mainMenu() {
		out.println("---------------Main Menu--------------");
		out.println("|1. 등록  2. 검색 3. 삭제 4.출력 5. 종료|");
		out.println("--------------------------------------");
		out.println("번호를 입력하세요=>");
		out.println("--------------------------------------");
	}// ----------

	/** 서브 메뉴를 보여주는 메소드 * */
	public void subMenu() {
		out.println("---------------Sub Menu--------------");
		out.println("| 1.학생  2. 강사 3. 직원 4. 상위메뉴  |");
		out.println("--------------------------------------");
		out.println("번호를 입력하세요=>");
		out.println("--------------------------------------");
	}

	/** 서브 메뉴 출력...케이스 별로 로직 수행 **/
	public void register() {
		Scanner sc = new Scanner(System.in);
		outer: // 레이블명
		while (true) {
			subMenu();
			int no = 0;
			try {
				no = sc.nextInt();
			} catch (InputMismatchException e) {
				out.println("숫자로 입력해야 해요!");
				return;
			}
			switch (no) {
			case 1:// 학생
				Student st = new Student();
				st.inputInfo();// 학생정보를 입력받음.
				addPerson(st);// 객체를 배열에 저장한다.
				break;
			case 2:// 교사
				Teacher tc = new Teacher();
				tc.inputInfo();
				addPerson(tc);
				break;
			case 3:// 직원
				Staff sf = new Staff();
				sf.inputInfo();
				addPerson(sf);
				break;
			case 4:// 상위 메뉴
				break outer;// 레이블 지정된 반복문을 이탈
			// return;
			// register()메소드를 호출한 쪽으로 제어권을 넘김
			default:
				out.println("지원되지 않는 메뉴번호입니다.");
			}
		} // while----
	}// register()------------------

	/** 등록한 사람들을 배열에 저장하는 메소드 */
	public void addPerson(Person person) {
		if (count < p.length) {
			p[count++] = person;
		} else {
			out.println("30명 정원 초과 되었어요!");
		}
	}// addPerson()------------

	/** 이름으로 등록된 사람 검색하는 메소드 */
	public String findPerson(String name) {
		for (int i = 0; i < count; i++) {
			String pname = p[i].getName(); // 배열에 저장된 사람의 이름을 꺼내온다.

			if (name.equals(pname)) { // 검색하는 사람의 이름과 배열에서 꺼낸이름이 동일하다면
				p[i].printAll(); // 그 사람 정보 출력
				return p[i].getInfo(); // awt와 연계하기 위해 문자열로 정보를 반환
			} // if-------------
		} // for----------
		out.println(name + "이란 사람 없어요!");
		return name + "이란 사람 없어요";

	}// findPerson()---------------

	/** 이름으로 삭제하는 메소드 */
	public String removePerson(String name) {
		for (int i = 0; i < count; i++) {
			String pname = p[i].getName();
			if (name.equals(pname)) {
				for (int j = i; j < count; j++) {
					p[j] = p[j + 1];
				} // for----
				count--; // 배열 인덱스 하나 감소.
				out.println(name + "님 정보가 삭제됐어요!");
				return name + "님 정보 삭제";
			} // if-------
		} // for---
		out.println(name + "님은 없어요!");
		return name + "님 없어요!";

	}// removePerson()-------------

	/** 배열에 저장된 사람의 모든 정보 출력 */
	public String printAll() {
		String str = "";
		for (int i = 0; i < count; i++) {
			if (p[i] instanceof Student) {
				str += "\n---학생정보---\n";
			} else if (p[i] instanceof Teacher) {
				str += "\n---교사정보---\n";
			} else {
				str += "\n---직원정보---\n";
			}
			str += p[i].getInfo();
		}
		out.println(str);
		return str;
	}// printAll()-------

	public static void main(String[] args) {
		SchoolApp sa = new SchoolApp();
		Scanner sc = new Scanner(System.in);
		while (true) {
			sa.mainMenu();
			// 메뉴 번호 입력
			int no = 0;
			try {
				no = sc.nextInt();
			} catch (InputMismatchException e) {
				out.println("숫자로 입력해야 해요");
				return;
			}
			switch (no) {
			case 1:// 등록
				sa.register();
				break;
			case 2:// 검색
				out.println("검색할 사람 이름=>");
				String name = sc.next();
				out.println("--" + name + "님을 검색합니다.--");
				sa.findPerson(name);
				break;
			case 3:// 삭제
				out.println("삭제할 사람 이름=>");
				name = sc.next();
				sa.removePerson(name);
				break;
			case 4:// 출력
				sa.printAll();
				break;
			case 5:// 종료
				out.println("Bye Bye~~");
				System.exit(0);
			}
		}
	}
}
		