package day04;

public class  Teacher {
	private String name;//멤버변수-인스턴스변수
	private int no;
	private String subject;

	public void setName(String name){
		//매개변수name: 지역변수
		this.name=name;
		//멤버변수=지역변수

		//멤버변수와 지역변수가 이름이 동일할 경우
		//지역변수에게 우선권이 있다.
		//이럴 경우 멤버변수와 지역변수를 구분할
		//필요가 있는데...그 방법으로
		//멤버변수(인스턴스변수) 앞에 this. 을 붙여주자.
	}
	public void setNo(int no){
		this.no=no;
	}
	public void setSubject(String subject){
		this.subject=subject;
	}
	public String getName(){
		return name;//this.name;
	}
	public int getNo(){
		return no;
	}
	public String getSubject(){
		return subject;
	}
	public void printInfo(){
		System.out.println("---강사 정보---");
		System.out.println("이름: "+name);
		System.out.println("교번: "+no);
		System.out.println("과목: "+subject);
	}//--------------------
}///////////////////////////////////
