package day03;

public class DataType {
	public DataType() {
		System.out.println("2.실수형--------------");
		// float:단정밀도. 소수점 이하 7자리까지...
		// double: 배정밀도. 소수점 이하 15~16자리...
		// 디폴트

		float ft = 1.234F;
		float ft2 = 0.56f;
		// float형은 부동소수점일 경우 반드시 접미사 F를
		// 붙여주어야 한다. 그렇지 않으면 double 로 인식한다.
		System.out.println("ft: " + ft);
		System.out.println("ft2: " + ft2);

		float ft3 = 321;
		System.out.println("ft3: " + ft3);

		float ft4 = 3.456F;
		int e = (int) ft4;
		System.out.println("e: " + e);

		float ft5 = 0x12;// 1*16+2=18
		System.out.println("ft5: " + ft5);// 18.0

		// byte->short->int->long | ->float ->double
		// char-----+
		// ---------------------> 묵시적 형변환
		// <---------------------강제적 형변환(Casting)
		// 캐스팅연산자()를 이용하여 형변환을 해줘야 한다.
		// 이때 주의. 데이터 손실이 있을 수 있다.
		byte b = 10;
		long c = b;
		System.out.println("c: " + c);
		int d = (int) c;
		// 강제적 형변환
		System.out.println("d: " + d);

		// double형
		double dt1 = .889;
		System.out.println("dt1: " + dt1);
		double dt2 = .889E5;
		// 0.889* 10^5
		double dt3 = .889E-5;
		// 0.889* 10^-5;
		System.out.println("dt2: " + dt2);
		System.out.println("dt3: " + dt3);

		float ft6 = .123E4F;
		System.out.println("ft6: " + ft6);

		System.out.println("3. 문자형--------------");
		// char : 2byte 0 ~65535. 모든 국가의 언어 표현가.
		// 자바 유니코드 체계 사용.
		char ch = 'A';// 65
		System.out.println("ch: " + ch);
		char ch2 = '가';
		System.out.println("ch2: " + ch2);
		char ch3 = '\uff57';
		System.out.println("ch3: " + ch3);

		System.out.println(ch + 1);// 66
		// 문자형 +(산술연산) 문자형 ===> 정수
		// 묵시적 형변환
		/*
		 * byte----+ short---+---int형보자 작은 데이터들이 char----+ 연산에서 사용될 경우 그 결과는 int 형으로 자동
		 * 형변환(promotion)된다.
		 */
		byte ba1 = 10;
		byte ba2 = 20;
		int result = ba1 + ba2;
		System.out.println("result: " + result);

		char ch4 = 'A';
		char ch5 = 'B';// 66
		int result2 = ch4 + ch5;
		System.out.println("result2: " + result2);
		int result3 = ch5 + 1;// 67==>C
		char ch6 = (char) result3;
		System.out.println("ch6: " + ch6);
		System.out.println("4. 논리형--------------");
		boolean bool = true;
		System.out.println("bool: " + bool);
		boolean bool2 = 5 > 10;
		System.out.println("bool2: " + bool2);
		// 자바의 논리형은 0이나 1로 호환되지 않는다.
		// 형변환도 할 수 없다.
		// int bool3=(int)true; [x]

	}// 생성자---------

	public static void main(String[] args) {
		DataType dt = new DataType();
	}// main()---------

}///////////////////////////
