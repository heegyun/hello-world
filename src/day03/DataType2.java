package day03;

public class DataType2 {
	public void go() {
		System.out.println("----gogo-------------");
		System.out.println("5. 참조형-------------");
		// 참조형(Reference Type)은
		// 반드시 new연산자로 객체를 생성해 사용해야 한다.
		// 그러나 예외는 있다. String의 경우, 너무나도 자주
		// 사용하기 때문에 마치 기본자료형 처럼
		// 문자열 값을 직접 할당해줄 수 있게 하고 있다.

		String str1 = "Hello";
		String str2 = new String("Hello");
		System.out.println("str1: " + str1);
		System.out.println("str2: " + str2);
		String str3 = " Java ~!";
		System.out.println(str1 + str3);
		// String의 값을 더하기 연산자로 결합하면
		// 결과는 문자열 결합이 일어난다.
		int m = 10;
		int n = 30;
		System.out.println(m + n + "<=합");
		System.out.println("합=>" + m + n);
		System.out.println("합=>" + (m + n));
	}// go()----------

	public static void main(String[] args) {
		DataType2 dt = new DataType2();
		dt.go();

	}// main()----------

}////////////////////////////