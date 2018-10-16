package day07;

public class Car {
	
	int speed;
	int distance;
	String color;
	
	public Car() {}
	
	public Car(String color, int speed) {
		this.color = color;
		this.speed = speed;
		
	}
	
	// speed 값을 5 증가 시키는 메소드
	public void speedUp() {
		speed = speed+5;
	}
	
	// speed 값을 10씩 감소시키는 메소드
	public void breakDown() {
		speed = speed-10;
	}
	
	//현재의 속도를 반환하는 메소드
	public int getCurrentSpeed() {
		return speed;
	}
	

}
