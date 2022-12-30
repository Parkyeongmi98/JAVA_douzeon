package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEchoServer {
	public static final int PORT = 5000;
	public static final int BUFFER_SIZE = 256;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			// 1. 소켓 생성
			socket = new DatagramSocket(PORT);
			
			while(true) {
				// 2. 데이터 수신
				DatagramPacket rcvPacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE); // 보내는 사이즈랑 받는 사이즈 일치
				socket.receive(rcvPacket); // Blocking
				
				// byte형으로 데이터 뽑기(rcvPacket에 데이터 들어있음)
				byte[] rcvData = rcvPacket.getData();
				
				int offset = rcvPacket.getLength(); // 길이 찾기
				// byte형을 String으로 변환
				String message = new String(rcvData, 0, offset, "utf-8");
				
				System.out.println("[UDP Echo server] received : " + message);
				
				// 3. 데이터 송신(보낼 메시지를 byte형으로)
				byte[] sndData = message.getBytes("utf-8");
				DatagramPacket sndPacket = new DatagramPacket(
						sndData,                 // 보낼 데이터
						sndData.length,          // 데이터의 길이
						rcvPacket.getAddress(),  // 보내는 사람 주소
						rcvPacket.getPort());    // 포트 번호
				
				socket.send(sndPacket);
			}			
		} catch (SocketException e) {
			System.out.println("[UDP Echo server] error : " + e);
		} catch (IOException e) {
			System.out.println("[UDP Echo server] error : " + e);
		} finally {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
}
