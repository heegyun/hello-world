package day04;
import static java.lang.System.out;
import java.util.Scanner;
class Person{//멤버변수
	String name;
	int age;
	char sex;//'M', 'F'
	String tel;

	public String showProfile(){
		String info="---"+name+" 프로필-----";
			info+="\n나이 : "+age;
			info+="\n성별 : "+sex;
			info+="\n전화 : "+tel;
		return info; //문자열 반환
	}//showProfile()-------
	
	public void wantJob(String job, int pay)	{
		System.out.println("---"+name+"님 ----");
		System.out.println(" 희망직종: "+job);
		System.out.println(" 희망 연봉: "+pay);
	}//wantJob()------------
}//Person Class End///////////////

public class  JobApp {  //구직 등록 프로그램..
	public void showMenu(){
		out.println("*******JOB v1.1*************");
		out.println("** 1. 구직 등록				  **");
		out.println("** 2. 구인 등록				  **");
		out.println("** 3. 구직자 정보 출력      **");
		out.println("** 4. 구인회사 정보 출력   **");
		out.println("** 5. 종료				  **");
		out.println("*****************************");
		out.println(" 메뉴 번호를 입력하세요=>");
		out.println("*****************************");
	}//showMenu()------------

	public static void inputPerson(){
		//이름,나이,성별,전화번호를 입력받아
		//Person객체에 저장
		Scanner sc=new Scanner(System.in);
		Person p1=new Person();
		out.println("--구직 등록 start-------");
		out.println("이름 입력=>");
		String nm=sc.next();
		p1.name=nm;
		out.println("나이 입력=>");
		int a=sc.nextInt();
		p1.age=a;
		//성별, 전화번호 입력.....
		out.println("--성별 메뉴---");
		out.println(" 1.남자  2.여자");
		out.println("----------------");
		out.println("성별 메뉴번호 입력=>");
		int s=sc.nextInt();
		if(s==1)
			p1.sex='M';
		else if(s==2)
			p1.sex='F';
		else
			out.println("지원되지 않는 메뉴 번호입니다.");
		out.println("전화번호 입력=>");
		String t=sc.next();
		p1.tel=t;
		String info=p1.showProfile();
		System.out.println(info);
	}//inputPerson()------------
	
	public static void main(String[] args) {	
		Scanner sc=new Scanner(System.in);
		JobApp ja=new JobApp();
	while(true){
		ja.showMenu();
		int no=sc.nextInt();
		if(no==5){//종료
			System.exit(0);//프로그램 종료-0: 정상종료
		}else if(no==1){//1. 구직등록...
		//직장을 구하는 사람의 인적 정보를 입력받아야 한다.
		JobApp.inputPerson();
		//인적정보를 입력받는 모듈
		}
	}//while--------------
		
	}//main()----------
}///////////////////////////


/* 여기를 먼저 
Person p1=new Person();
		p1.name="윤희노";
		p1.age=20;
		p1.sex='M';
		p1.tel="111-1111";

		Person p2=new Person();
		p2.name="용한나";
		p2.age=22;
		p2.sex='F';
		p2.tel="222-2222";

		String str=p1.showProfile();
                System.out.println(str);
		
		String str2=p2.showProfile();
                System.out.println(str2);
		
		//wantJob()메소드 호출하기
		p1.wantJob("프로그래머",3000);
		p2.wantJob("웹프로그래머",4000);
*/