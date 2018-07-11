package day04;

public class  Student{
	private String name;
	private int no;
	private String cname;

	public Student() {}
	// 생성자1.
	public Student(String name) {
		this.name = name;
	}
	
	public Student(String n, int no, String cn) {
		name =n;
		this.no= no;
		cname = cn;
	}
	
	public void setName(String n){
		name=n;
	}
	public void setNo(int n){
		no=n;
	}
	public void setCname(String c){
		cname=c;
	}

	public String getName(){
		return name;
	}
	public int getNo(){
		return no;
	}
	public String getCname(){
		return cname;
	}
	//학생정보를 출력하는 메소드
		public void printInfo(){
			System.out.println("---학생 정보---");
			System.out.println("이름: "+name);
			System.out.println("학번: "+no);
			System.out.println("학급: "+cname);
		}
}////////////////////////////////
