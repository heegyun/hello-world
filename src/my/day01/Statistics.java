package my.day01;


import java.util.Scanner;

public class Statistics
{
      public static void main(String[] args)
      {
    	  // 사용자로부터의 입력 값들을 저장하는 변수들을 선언한다
          int number1, number2, number3;

          // 입력된 수들의 최대값, 최소값, 합계와 평균을 위한 변수들을 선언한다
          int maximum, minimum, sum;
          double average;

          // 키보드로부터 입력하는 데이터를 읽기 위한 Scanner 객체를 만든다.
           Scanner scan = new Scanner (System.in);

          // 변수 sum의 초기값을 0으로 한다
          sum = 0;

          // 사용자로부터 첫번째 수를 입력하라고 요청한다. 그리고 입력된 값을 읽는다
          System.out.print("첫번째 수를 입력하세요:\t");
          number1 = scan.nextInt();

          // 사용자로부터 두번째 수를 입력하라고 요청한다. 그리고 입력된 값을 읽는다
          System.out.print("두번째 수를 입력하세요:\t");
          number2 = scan.nextInt();

          // 사용자로부터 세번째 수를 입력하라고 요청한다. 그리고 입력된 값을 읽는다
          System.out.print("세번째 수를 입력하세요:\t");
          number3 = scan.nextInt();

          // 최대값을 계산한다
          maximum = number1;
          if (maximum < number2) maximum = number2;
          if (maximum < number3) maximum = number3;

          // 최소값을 계산한다
          minimum = number1;
          if (minimum > number2) minimum = number2;
          if (minimum > number3) minimum = number3;

          // 합계와 평균을 계산한다
          sum = number1 + number2 + number3;
          average = sum / 3.0;

          // 결과들을 출력한다
          System.out.println("최대값: " + maximum);
          System.out.println("최소값: " + minimum);
          System.out.println("합계: " + sum);
          System.out.println("평균: " + average);
      }
   }
