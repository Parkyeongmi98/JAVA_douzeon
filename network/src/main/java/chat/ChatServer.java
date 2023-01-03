// 1. 서버는 여러 클라이언트가 접속할 수 있어야 한다. (다중 처리 가능, 멀티스레드 프로그래밍)                 
// 2. 서버는 여러 클라이언트에게 동시에 메시지를 보낼 수 있는 브로드캐스팅(broadcasting) 기능이 있어야 한다.
// 3. EchoServer의 각 스레드는 자신의 IO Stream 객체만 사용하면 되었지만, Chat Server에서는 다른 스레드의 IO Stream을 사용해야 한다. (printWriter 객체)
// 4. 닉네임을 등록하기 위한 요청, 메시지를 전달하기 위한 요청, 방을 나가기 위한 요청 등 클라이언트의 요청을 구별하기 위해 프로토콜(채팅 프로토콜)을 설계해야 한다.

package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 9999;  // 포트번호 지정

	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		// PrintWriter를 담을 수 있는 List 생성
		List<Writer> listWriters = new ArrayList<>();
		
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			// 2. 바인딩
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			log("연결 기다림 " + hostAddress + ":" + PORT);
			
			while(true) {
				Socket socket = serverSocket.accept();
				// 클라이언트와 연결된 후 채팅 데이터 통신은 CharServerThread가 한다
				// 요청이 수락하고 스레드를 생성할 때 list객체를 스레드 생성자를 통해 전달
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			log("서버 오류"); // 서버에서 나는 오류
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String message) {
		System.out.println("[ChatServer]" + message);
	}
}
