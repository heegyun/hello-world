package my.schoolapp;

import java.util.Scanner;

public abstract class Person {
	
	
	private String name;
	private String addr;
	
	public Person() {
	    super();
	
	}// 기본생성자----
	
	public Person(String name, String addr) {
		super();
		this.name = name;
		this.addr = addr;
	}// 인자생성자--------

	/**setter,getter-----*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**추상메소드(=메소드의 BODY가 없는 메소드)  */
	public abstract void setId(String id);
	public abstract String getId();
	
	/** 사람 정보를 입력받는 모듈 */
	public void inputInfo() {
		System.out.println("이름을 입력하세요=>");
		Scanner sc = new Scanner(System.in);
		String nm = sc.nextLine();
		setName(nm);// 멤버 변수에 입력값을 저장
		System.out.println("주소를 입력하세요=>");
		String ad = sc.nextLine();
		setAddr(ad);// 멤버 변수에 입력값을 저장
	}// -------------
	
	/**사람의 정보를 문자열로 돌려주는 메소드 */
	public String getInfo(){
	String info="이름: "+name+"\n주소: "+addr;
	return info;
	}


	/**사람 정보를 콘솔에 출력해주는 메소드*/
	public void printAll(){
	System.out.println(this.getInfo()); 
	}

	/**사람의 이름을 반환해주는 메소드*/
	public String toString(){
	return name;
	}

}///////////////////////////////////////////////////////














