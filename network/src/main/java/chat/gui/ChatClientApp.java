package chat.gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import chat.ChatServer;

public class ChatClientApp {
	private static final String SERVER_IP = "0.0.0.0";

	public static void main(String[] args) {
		String nickname = null;
		Scanner scanner = new Scanner(System.in);
		Socket socket = null;
		
		try {
			while( true ) {
				
				System.out.println("닉네임을 입력하세요.");
				System.out.print(">>> ");
				nickname = scanner.nextLine();
				
				if (!nickname.isEmpty()) {
					break;
				}
				
				System.out.println("닉네임은 한글자 이상 입력해야 합니다.\n");
			}
			
			scanner.close();
			//1. create socket
			socket = new Socket();
			
			//2. connect to server
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			ChatServer.log("접속되었습니다.");
			
			//3. get iostream
			BufferedReader br = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), "utf-8"));

			PrintWriter pw = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			//4. join protocol 진행
			pw.println("join:" + nickname);
			String line = br.readLine();
			if ("join:ok".equals(line)) {
				new ChatWindow(nickname, socket).show();
				return;
			} else {
				System.out.println("닉네임을 다시 설정해주세요.");
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
			try {
				if(!socket.isClosed() && socket != null) {
					socket.close();					
				}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	

}
