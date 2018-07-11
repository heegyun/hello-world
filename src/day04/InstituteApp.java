package day04;

public class InstituteApp {

	public static void main(String[] args) {

		Student st = new Student();
		// st.name="홍길동";
		st.setName("홍길동");
		st.setNo(1);
		st.setCname("네트워크반");

		String nm = st.getName();
		System.out.println(nm);
		st.printInfo();

		Teacher tc = new Teacher();
		tc.setName("임꺽정");
		tc.setNo(201);
		tc.setSubject("자바");
		tc.printInfo();

		Staff sf = new Staff();
		sf.setName("이길동");
		sf.setNo(301);
		sf.setDept("교육실");
		sf.printInfo();
		// 문제1]학생 객체를 3개 더 생성하고...
		// 각각 이름,학번,학급 값을 넣어준뒤...
		// 배열에 저장하자.
		// for루프 돌리면서 저장된 학생 객체들의
		// 정보를 출력해보자.

		// 데이터형 변수명[]=new 데이터형[4];

		Student st2 = new Student();
		st2.setName("강길동");
		st2.setNo(2);
		st2.setCname("웹프로그래밍");

		Student st3 = new Student();
		st3.setName("송길동");
		st3.setNo(3);
		st3.setCname("웹디자인");

		Student st4 = new Student();
		st4.setName("윤길동");
		st4.setNo(4);
		st4.setCname("웹프로그래밍");

		// Student []arr={st,st2,st3,st4};
		Student[] arr = new Student[4];
		arr[0] = st;
		arr[1] = st2;
		arr[2] = st3;
		arr[3] = st4;
		for (int i = 0; i < arr.length; i++) {
			arr[i].printInfo();
			/*
			 * System.out.println("이름: " +arr[i].getName());
			 * System.out.println("학번: "+arr[i].getNo());
			 */
		} // for-----------------

		Teacher tc2 = new Teacher();
		tc2.setName("정길동");
		tc2.setNo(202);
		tc2.setSubject("닷넷");

		Teacher tc3 = new Teacher();
		tc3.setName("이길동");
		tc3.setNo(203);
		tc3.setSubject("네트워크");

		Teacher tarr[] = { tc, tc2, tc3 };
		for (int i = 0; i < tarr.length; i++) {
			tarr[i].printInfo();
		}

		
	}//main()----------
}//////////////////////////////
