package testchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientThread extends Thread{
	
	private BufferedReader br;
	private Socket socket;
	private PrintWriter pw=null;
	
	public ChatClientThread(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		
		try {
			br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			while(true) {
				String data=br.readLine();
				if(data==null) {
					ChatServer.log("다시");
					break;
					
				}
				System.out.println(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(pw!=null)pw.close();
		}
		
	}
	

}
