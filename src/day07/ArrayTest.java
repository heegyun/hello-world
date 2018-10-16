package day07;

import java.util.Random;

public class ArrayTest {

	public static void main(String[] args) {

		Random rand = new Random();
		
		int[] arr = new int[100];
		for(int i=0;i<arr.length;i++) {
			
			arr[i] = rand.nextInt(100)+1;
		}
		
		int sum=0;
		
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
		}
		System.out.println("100개의 랜덤 정수의 평균 값은 " + (sum / 100) + "입니다.");
	}

}
