package my.day01;

import java.util.Random;
import java.util.Scanner;

public class NumberGame2 {

	public static void main(String args[]) {

		int num = 0;
		int count =0;
		/* 1단계 : Random 클래스 객체를 이용해 난수를 만든다. */
		Random rand = new Random();
		num = rand.nextInt(100);
		System.out.println("컴퓨터가 생성한 난수: " + num);

		/* 2단계: 숫자를 입력받아 올바른 입력인지 확인한다. */
		/* 숫자를 입력받기 위한 Scanner 클래스의 nextInt()메소드를 이용하여 입력받기 */
		Scanner scan = new Scanner(System.in);
		int inputNum = 0;
		/* 숫자를 맞출 때까지 반복하기위한 반복문 */
		while (true) {
			System.out.print("0과 100사이의 숫자를 입력하세요(예 : 45) : ");
			inputNum = scan.nextInt();

			/* 입력된 숫자가 범위에 맞는가? */
			if (inputNum >= 0 && inputNum <= 100) {
				  /* 몇 번째 시도인지 기록하기위해 count를 1만큼 증가시킨다. */
					count = count + 1;
				/* 3단계: 입력된 숫자가 난수와 일치하면 몇 번만에 맞췄는지 출력하고 */
				/* while문에서 빠져나간다. */

				if (inputNum == num) {
					// System.out.println("올바른 입력입니다.");
					//System.out.println(num);
					System.out.println(count + "번에 맞추셨습니다.");
					break;
				} else if (inputNum < num) {
					/* 난수보다 작다면 입력된 숫자가 난보다 작습니다. 를 출력한다. */
					System.out.println(inputNum + "은 난수보다 작습니다.");
				} else {
					/* 난수보다 크다면 입력된 숫자가 난보다 큽니다. 를 출력한다. */
					System.out.println(inputNum + "은 난수보다 큽니다.");
				}

			} else {
				/* 잘못된 입력이라면 다시 입력을 받는다. */
				System.out.println("잘못된 입력입니다.");
			}

		}
	}
}
