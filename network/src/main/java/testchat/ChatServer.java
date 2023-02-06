package testchat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT=9999;
	
	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		List<Writer> listWriters =new ArrayList<>();
		
		try {
			serverSocket =new ServerSocket();
			
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT),10);
			log("연결 기다림");
			
			while(true) {
				Socket socket=serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			log("서버오");
		}finally {
			if(serverSocket!=null)
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		

	}
	public static void log(String message) {
		System.out.println("[ChatServer]"+message);
	}

}
