package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {
	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostName = inetAddress.getHostName(); // 내컴퓨터 이름 출력
			String hostIpAddress =inetAddress.getHostAddress(); // 내컴퓨터 IP주소 출력
			
			System.out.println(hostName);
			System.out.println(hostIpAddress);
			
			byte[] IpAddresses = inetAddress.getAddress();
			for (byte IpAddress : IpAddresses) {
				//System.out.print((int)IpAddress);
				System.out.print(IpAddress & 0x000000ff); // 2의 보수
				System.out.print(".");
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
