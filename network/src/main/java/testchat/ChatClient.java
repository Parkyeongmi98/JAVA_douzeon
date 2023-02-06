package testchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP="0.0.0.0";
	
	public static void main(String[] args) {
		Socket socket=null;
		Scanner scanner=null;
		
		scanner=new Scanner(System.in);
		socket=new Socket();
		
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			ChatServer.log("접속");
			
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true);
			
			System.out.print("닉네");
			String nickname=scanner.nextLine();
			pw.println("join:"+nickname);
			String response=br.readLine();
			
			if(response.equals("join:ok")) {
				new ChatClientThread(socket).start();
			}else {
				ChatServer.log("닉네임 다시");
				return;
			}
			
			
			
			while(true) {
				System.out.println(">>");
				String input=scanner.nextLine();
				
				if("quit".equals(input)==true) {
					pw.println("quit");
					break;
				}else if(input==null) {
					ChatServer.log("내용");
					return;
				}else {
					pw.println("message:"+input);
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(socket!=null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
