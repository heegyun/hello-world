package day03;

public class PrimitiveType {
	
	public PrimitiveType() {
		System.out.println("1.정수형------------");
		byte bt = 10;// -128 ~ 127
		byte bt2 = 127;
		System.out.println("bt: " + bt);
		System.out.println("bt2: " + bt2);
		short st = 1000;
		System.out.println("st: " + st);
		int it = 30;// 정수형의 디폴트
		long ln = 40;
		long ln2 = 40L;// l, L이라는 접미사를 붙여준다.
		System.out.println("it: " + it);
		System.out.println("ln: " + ln);
		System.out.println("ln2: " + ln2);
		int i = 1000000000;// 0이 9개
		// int형의 최대 범위: 2147483627
		long j = 10000000000L;// 0이 10개
		System.out.println("i: " + i);
		System.out.println("j: " + j);
		int a = 010;// 8진수
		// 8진수: 0 ~ 7
		// 앞에 0을 붙인다.
		// 011 : 1*8^1 +1*8^0=8+1=9
		System.out.println("a: " + a);
		int b = 0341;
		System.out.println("b: " + b);
		long c = 0x11;
		// 16진수: 0 ~ 9, A, B, C,D,E,F
		// 앞에 0x를 붙인다.
		// 0x11: 1*16^1+1*16^0=17
		System.out.println(c);
		int d = 0x2Ac;
		// 2*16^2+10*16+12==684
		System.out.println(d);
	}// 생성자----------

	public static void main(String[] args) {
		PrimitiveType p = new PrimitiveType();
	}// main()-----------
}////////////////////////////////////