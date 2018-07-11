package my.pcmange;

import java.util.Date;
import java.util.Scanner;

public class Manager {

	public static void main(String[] args) {
		/* 프로그램 수행에 필요한 변수 선언 */
		boolean bFalg = true;
		int iMenu = 0;
		int iTotal = 0;
		Date d;

		Scanner scan = new Scanner(System.in);

		/* PC의 수량을 입력받는다. */
		System.out.print("등록할 컴퓨터의 수량을 입력하세요 : \t");
		iMenu = scan.nextInt();

		/* 입력된 수량의 크기로 PCInfo 클래스 객체 배열을 만든다. */
		PCInfo pc[] = new PCInfo[iMenu];

		/* 객체 배열에 CPU, 메모리 정보가 설정된 PCInfo객체의 레퍼런스를 저장한다. */
		for (int id = 0; id < pc.length; id++) {
			String strCpu = "";
			int iMem = 0;
			System.out.println("PC " + id + "번");

			System.out.print("컴퓨터의 기종을 입력하세요 : \t");
			strCpu = scan.next();
			System.out.print("메모리의 양을 입력하세요(MB) : \t");
			iMem = scan.nextInt();

			pc[id] = new PCInfo();
			pc[id].setPCInof(id, strCpu, iMem);
		}

		do {
			System.out.println("\n=====================================");
			System.out.println("1. 컴퓨터 사용 시작 [" + 0 + " - " + (pc.length - 1) + "]");
			System.out.println("2. 컴퓨터 사용 종료 [" + 0 + " - " + (pc.length - 1) + "]");
			System.out.println("3. 매출정보 출력");
			System.out.println("4. 프로그램 종료");
			System.out.println("=====================================");
			System.out.print("메뉴의 번호를 선택하세요(1-4) : \t");

			try {

				iMenu = scan.nextInt();

			} catch (Exception e) {
				System.out.println("1-5까지의 숫자로 입력해주세요.");
				continue;
			}

			switch (iMenu) {
			case 1:
				/*
				 * 관리자로부터 PC번호를 입력받아 해당 PCInfo객체의 start 메서드를 호출한다.
				 */
				try {
					System.out.print("컴퓨터의 번호를 입력하세요 : \t");
					iMenu = scan.nextInt();
				} catch (Exception e) {
					continue;
				}
				d = new Date();
				pc[iMenu].start(d.getTime());
				break;

			case 2:
				/*
				 * 관리자로부터 PC번호를 입력받아 해당 PCInfo객체의 stop 메서드를 호출한다.
				 */
				try {
					System.out.print("컴퓨터의 번호를 입력하세요 : \t");
					iMenu = scan.nextInt();
				} catch (Exception e) {
					continue;
				}
				d = new Date();
				System.out.println("사용금액은 " + pc[iMenu].stop(d.getTime()) + "원 입니다.");
				break;
			case 3:
				/* PCInfo 객체 getTotal 메서드를 호출하여 매출액과 그 합을 출력한다. */
				iTotal = 0;
				for (int id = 0; id < pc.length; id++) {
					System.out.println("PC " + id + "번 : " + pc[id].getTotal() + "원");
					iTotal = iTotal + pc[id].getTotal();
				}
				System.out.println("전체 매출 : " + iTotal + "원");
				break;
			case 4:
				bFalg = false;
				break;
			default:
				break;
			}
		} while (bFalg);
	}

	}
