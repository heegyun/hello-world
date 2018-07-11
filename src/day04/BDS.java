package day04;

class House{
	//1.속성-멤버변수(has a관계)
	int room;
	String ownerName;
	String addr;
	//2. 기능, 행동양식-메소드
	public void existAt(){
		System.out.println(addr+"에 위치합니다.");
	}
	public void rent(int price){
		System.out.println(
			ownerName+"집: "+
			price+"원에 세를 놓고 있어요");
	}
	public void getInfo(){
		System.out.println("----House 정보----");
		System.out.println("소유주: "+ownerName);
		System.out.println("주   소: "+addr);
		System.out.println("방   수: "+room);
	}

}



public class  BDS{
	public static void main(String[] args){
		House h1=new House();
		//instance
		House h2=new House();
		h1.getInfo();
		h1.room=2;
		h1.ownerName="홍길동";
		h1.addr="서울 영등포구 당산동";
		h1.getInfo();
		h2.room=4;
		h2.ownerName="임꺽정";
                h2.addr="마포구 서교동";
		h2.getInfo();
              //얼마에 세를 주는가?
		h1.rent(1000);
		h2.rent(3000);
	}

	}