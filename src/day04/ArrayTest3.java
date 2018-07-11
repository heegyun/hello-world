package day04;

public class ArrayTest3 {
	public static void main(String[] args) {
		System.out.println("1.----------------");
		int a[][]; // int[]a[], int [][]a;
		a = new int[2][3]; // 2행 3열
		a[0][0] = 10;
		a[0][1] = 20;
		a[0][2] = 30;
		a[1][0] = 40;
		a[1][1] = 50;
		a[1][2] = 60;
		
		System.out.println("a[0][0]=" + a[0][0]);
		for (int i = 0; i < a.length; i++) {        // a.length: 행의 크기
			for (int k = 0; k < a[i].length; k++) { // a[i].length: 열의 크기
				System.out.print(a[i][k] + " ");
			} 
			System.out.println();
		} 
		
		System.out.println("2.------------------");
		float[] b[] = { { 0.1F, 10 }, { 011 }, { 10, 'A', 20 } };
		for (int i = 0; i < b.length; i++) {
			for (int k = 0; k < b[i].length; k++) {
				System.out.print(b[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println("3.-----------------");
		// 행의 크기만 먼저 잡아놓고 열의 크기를 가변적으로
		// 할당하는 방법
		char[][] ch = new char[3][];// 3행
		System.out.println("ch.length: " + ch.length);// 행의 크기 출력
		ch[0] = new char[2]; // 2열
		System.out.println("ch[0].length: " + ch[0].length); //열의 크기 출력
		ch[1] = new char[4];// 4열
		ch[2] = new char[3];// 3열
		ch[0][0] = 'H';
		ch[0][1] = 'i';
		ch[1][0] = 'J';
		ch[1][1] = 'a';
		ch[1][2] = 'v';
		ch[1][3] = 'a'; 
		for (int i = 0; i < ch.length; i++) {
			for (int k = 0; k < ch[i].length; k++) {
				System.out.print(ch[i][k]);
			}
			System.out.print("  ");
		}
		System.out.println("4.---------------------");
		
		/* 문제1] String 데이터를 저장하는 이차원 배열을  선언하세요. 행과 열의 크기는 맘대로..
		 "Hello", "Network", "Programming","Good!"
		 위의 문자열들을 저장한 뒤
		 for루프를 이용해 출력해봅시다. */
		
		String str[][] = { { "Hello" }, { "Network", new String("Programming") }, { "Good!" } };
		for (int i = 0; i < str.length; i++) {
			for (int k = 0; k < str[i].length; k++) {
				System.out.print(str[i][k]);
			}
			System.out.print("  ");
		}
		
		/*
		 * 문제 2] 2차원 배열에 다음의 값을 저장하되, for루프를 이용해 저장하자. 그런 뒤, 저장된 값들을 출력해봅시다. 
		 * 1 0 0 0 0 
		 * 0 1 0 0 0  
		 * 0 0 1 0 0 
		 * 0 0 0 1 0
		 * 0 0 0 0 1
		 */
		System.out.println("5.-------------------");
		int arr[][] = new int[5][5];

		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr[i].length; k++) {  
				if (i == k)                         
					arr[i][k] = 1;  // 첨자가 같은 경우 1이다.
				System.out.print(arr[i][k] + " ");
			}
			System.out.println();
		} 

	}
}//////////////////////////////////////////////
