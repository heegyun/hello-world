package my.day11;

import java.net.InetAddress;
import java.util.Scanner;

public class InetAdressEx {
	
	Scanner scanner;
	
	public InetAdressEx() {
		System.out.println("Host �̸��� �Է� �ϼ���.");
		
		scanner = new Scanner(System.in);
		try {
			InetAddress inetAddress = InetAddress.getByName(scanner.next());
			
			System.out.println("Computer NAME : " + inetAddress.getHostName());
			System.out.println("Computer IP : " + inetAddress.getHostAddress());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
