package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPEchoClient {  // UDP는 스트리밍 서비스등 데이터가 빠져도 상관없을 경우 사용
	private static final String SERVER_IP = "127.0.0.1";
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		Scanner scanner = null;
		
		try {
			// 1. scanner 생성
			scanner = new Scanner(System.in);
			
			// 2. socket 생성
			socket = new DatagramSocket();
			
			while(true) {
				System.out.print(">> ");
				String line = scanner.nextLine();
				
				if("exit".equals(line)) {
					break;
				}
	
				// 3. 데이터 보내기
				byte[] sndData = line.getBytes("utf-8");
				DatagramPacket sndPacket = new DatagramPacket(
						sndData,                  // 보낼 데이터
						sndData.length,           // 데이터의 길이
						new InetSocketAddress(SERVER_IP, UDPEchoServer.PORT));  

				socket.send(sndPacket);
				
				// 4. 데이터 받기
				DatagramPacket rcvPacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE], UDPEchoServer.BUFFER_SIZE); // 보내는 사이즈랑 받는 사이즈 일치
				socket.receive(rcvPacket); // Blocking
				
				// byte형으로 데이터 뽑기(rcvPacket에 데이터 들어있음)
				byte[] rcvData = rcvPacket.getData();
				
				int offset = rcvPacket.getLength(); // 길이 찾기
				// byte형을 String으로 변환
				String message = new String(rcvData, 0, offset, "utf-8");
				
				System.out.println("<< " + message);
			}
					
		} catch (SocketException e) {
			System.out.println("[UDP Echo Client] error : " + e);
		} catch (IOException e) {
			System.out.println("[UDP Echo Client] error : " + e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}	
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
}
