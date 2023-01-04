package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;


import chat.gui.ChatWindow;

public class ChatServerThread extends Thread{
	// 연결된 클라이언트 닉네임 저장
	private String nickname;
	// 통신을 위한 스트림 얻어 오기 위해 Socket 객체 저장
	private Socket socket;
	private List<Writer> listWriters;
	PrintWriter pw = null;
	BufferedReader br = null;
	String data = null;
	
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		
		try {
			// 1. 문자 단위 처리와 라인 단위 읽기를 위해 보조 스트림객체 생성
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), "utf-8"));
			pw = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			// 2. 요청 처리
			while (true) {			
				data = br.readLine(); 
				if(data == null) {
					ChatServer.log("종료되었습니다."); // ctrl + c 종료
					// quit보내지 않고 소켓을 닫은 경우
					doQuit(pw);
					break;
				}
				
				//System.out.println(data);
			
				// 3. 프로토콜 분석
				// 요청명령 : 파라미터1:파라미터2:..\r\n -> 요청구분
				String[] tokens = data.split(":");
				
				if("join".equals(tokens[0])) {
				   doJoin(tokens[1], pw);
				} else if("message".equals(tokens[0])) {
				   doMessage(tokens[1]);
				} else if("quit".equals(tokens[0])) {	   
				   doQuit(pw);
				   break;
				} else {
				   ChatServer.log("에러:알수 없는 요청(" + tokens[0] + ")");
				}
			}
			
		} catch (SocketException e) {
			ChatServer.log("closed by client"); // xshell 껐을 경우
		} catch (IOException e) {
		} finally {
			if(socket != null && !socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
	}
	
	private void doQuit(Writer writer) {
		removeWriter(writer);
		String str = nickname + "님이 퇴장하였습니다.";
		System.out.println(nickname + ": 퇴장");
		broadcast(str);
	}

	private void removeWriter(Writer writer) {
		
		
	}

	private void doMessage(String message) {
		String data = message;
		System.out.println(nickname + ":" + message);
		broadcast(data);
		
	}

	private void doJoin(String nickName, Writer writer) {
		// 프로토콜
		// join:nickname\r\n
		this.nickname = nickName;
		
		String data = nickName + "님이 참여하였습니다.";
		System.out.println(nickname + ": 참여");
		
		broadcast(data);
		// writer pool에 저장
		addWriter(writer);
		// ack 방 참여 성공을 클라이언트에게 알리기
		// 중복 체크
		
		pw.println("join:ok");
	}
	
	// 서버에 연결된 모든 클라이언트에게 메시지 보내는 메소드
	private void broadcast(String data) {
		//동기화 처리
		synchronized(listWriters) {
		for(Writer writer : listWriters) {
			PrintWriter printWriter = (PrintWriter)writer; //다운캐스팅
			printWriter.println(nickname + ": " + data);
			// printWriter.println(data);
			
		    }
		}
	}

	private void addWriter(Writer writer) {
		// 여러 스레드가 하나의 공유객체에 접근할때 동기화 보장 synchronized
		synchronized(listWriters) {
			// Writer pool(list)에 Writer 추가
		    listWriters.add(writer);
		}	
	}
}
