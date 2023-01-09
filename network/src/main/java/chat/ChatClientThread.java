package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatClientThread extends Thread {
	private BufferedReader br;
	// Socket Read
	private Socket socket;
	private PrintWriter pw = null;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			//reader를 통해 읽은 데이터 콘솔에 출력하기(message 처리)
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"),true);
			while(true) {
				String data = br.readLine();
				if(data == null) {
					ChatClient.log("다시 입력해주세요.");
					break;
				}
				System.out.println(data);
			}
		} catch (UnsupportedEncodingException e) {
			ChatClient.log("#채팅을 종료하였습니다.");
		} catch (IOException e) {
			ChatClient.log("##채팅을 종료하였습니다.");
		}
	}

}
