// 1. 키보드로 입력이 가능                
// 2. 입력 중에 메시지를 수신 할 수 있다. 
// 3. 즉, 키보드 입력을 받는 작업은 main thread에서
// 4. 데이터 수신과 프로토콜 처리 작업은 데이터 수신 Thread에서 처리하도록 작성한다.

package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "0.0.0.0";

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		
		try {
			//1. 키보드 연결
			scanner = new Scanner(System.in);
			
			//2. socket 생성
			socket = new Socket();
			
			//3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			log("접속되었습니다.");
			
			//4. reader/writer 생성
			BufferedReader br = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), "utf-8"));

			PrintWriter pw = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			//5. join 프로토콜
			System.out.print("닉네임 >> ");
			String nickname = scanner.nextLine();
			pw.println("join:" + nickname);
			String response = br.readLine();
			
			if(response.equals("join:ok")) {
				//6. ChatClientReceiveThread 시작
				new ChatClientThread(socket).start();
			} else {
				log("닉네임을 다시 설정하세요.");
				return;
			}
			
			//7. 키보드 입력 처리
			while(true) {
				System.out.print("대화를 입력하세요 >>");
			    String input = scanner.nextLine();
			    System.out.println(nickname + ":" + input);
			    System.out.println(input);
			    if("quit".equals(input) == true) {	 
			        // 8. quit 프로토콜 처리
			    	pw.println("quit");
			        break;
			        
			    	} else if (input == null) {
			    		log("내용을 입력해주세요.");
			    		return;
			    	
			    	} else {
						
			    	// 9. 메시지 처리 
			    	pw.println("message:" + input);			
			    }
			 }

		} catch (NoSuchElementException e) {
			log("채팅방 강제 종료되었습니다.");
		} catch (SocketException e) {
			log("채팅방을 나갔습니다.");
		} catch (IOException e) {
			log("채팅방을 나갔습니다.");
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
				//10. 자원정리
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			
			}
		}  

	}
	
	public static void log(String message) {
		System.out.println("[ChatClient]" + message);
	}
}
