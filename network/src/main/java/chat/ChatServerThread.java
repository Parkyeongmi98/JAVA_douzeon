package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread{
	private String nickname;
	private Socket socket;
	private List<Writer> listWriters;
	
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		String data = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		
		// 1. Remote Host Information
		InetSocketAddress inetRemoteSocketAddress = 
				(InetSocketAddress)socket.getRemoteSocketAddress();
		String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
		int remoteport = inetRemoteSocketAddress.getPort();
		log("connected by client[" + remoteHostAddress + ":" + remoteport + "]");
		
		try {
			// 2. 스트림 얻기
			pw = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 3. 요청 처리
			while (true) {
				data = br.readLine(); // 갱을 붙여라
				if(data == null) {
					log("closed by client");
					break;
				}
				
				log("received: " + data);
				pw.println(data);
			}
			
			// 4. 프로토콜 분석
			String[] tokens = data.split(":");
			
			if("join".equals(tokens[0])) {
			   doJoin(tokens[1], pw);
			} else if("message".equals(tokens[0])) {
			   doMessage(tokens[1]);
			} else if("quit".equals(tokens[0])) {	   
			   doQuit(tokens[1]);
			} else {
			   log("에러:알수 없는 요청(" + tokens[0] + ")");
			}

		} catch(SocketException ex) {
			System.out.println("[server] suddenly closed by client");
		} catch(IOException ex) {
			log("error: " + ex);
		} finally {
			try {
				if(socket != null && !socket.isClosed() ) {
					socket.close();
				}
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void doQuit(String tokens) {
		removeWriter(tokens);
		
		String data = nickname + "님이 퇴장하였습니다.";
		broadcast(data);
	}

	private void removeWriter(String tokens) {
		
		
	}

	private void doMessage(String message) {
		
		
	}

	private void doJoin(String nickName, PrintWriter pw) {
		this.nickname = nickName;
		
		String data = nickName + "님이 참여하였습니다."; 
		broadcast(data);

		addWriter(pw);
		
		pw.println("join:ok");
		pw.flush();


	}
	
	private void broadcast(String data) {
		synchronized(listWriters) {
		for(Writer writer : listWriters) {
			PrintWriter printWriter = (PrintWriter)writer;
			printWriter.println(data);
			printWriter.flush();
		    }
		}
	}

	private void addWriter(PrintWriter pw) {
		synchronized(listWriters) {
		      listWriters.add(pw);
		}	
	}

	private void log(String message) {
 		System.out.println("[ChatServer#" + getId() + "]" + message);
 	}
}
