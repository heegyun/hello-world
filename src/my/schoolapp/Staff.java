package day04;

public class  Staff
{
	private String name;
	private int no;
	private String dept;

	public void setName(String name){
		this.name=name;
	}
	public void setNo(int no){
		this.no=no;
	}
	public void setDept(String dept){
		this.dept=dept;
	}
	public String getName(){
		return name;//this.name;
	}
	public int getNo(){
		return no;
	}
	public String getDept(){
		return dept;
	}
	public void printInfo(){
		System.out.println("--직원 정보--");
		System.out.println("이름: "+name);
		System.out.println("사번: "+no);
		System.out.println("부서: "+dept);
	}//------------
	
}////////////////////////
