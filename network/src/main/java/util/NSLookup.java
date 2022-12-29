package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print(">>");
				String str = sc.nextLine();
				
				if(str.equals("exit")) {
					System.out.println("종료");
					break;
				}
				
				InetAddress inet = InetAddress.getByName(str);
				System.out.println(inet.getHostName() + ":" + inet.getHostAddress());
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}
}
