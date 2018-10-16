package day07;

class Circle {
	int radius; // 반지름
	String color; //

	void setRadius(int r) {
		radius = r;
	}

	double calcArea() {
		return 3.14 * radius * radius;
	}

	void print() {
		System.out.println("원의 반지름: " + radius);
		System.out.println("원의 면적: " + calcArea());
	}
}

public class CircleTest {
	public static void main(String[] args) {

		Circle obj;

		obj = new Circle();
		obj.radius = 100;

		obj.color = "blue";

		double area = obj.calcArea();
		System.out.println("원의 면적=" + area);
	}
}
