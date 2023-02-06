package testchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class ChatServerThread extends Thread{

	private String nickname;
	private Socket socket;
	private List<Writer> listWriters;
	PrintWriter pw=null;
	BufferedReader br=null;
	String data=null;
	
	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket=socket;
		this.listWriters=listWriters;
	}
	
	@Override
	public void run() {

		
		try {
			br=new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			
			while(true) {
				data=br.readLine();
				if(data==null) {
					ChatServer.log("종료");
					doQuit(pw);
					break;
				}
				
				String[] tokens=data.split(":");
				
				if("join".equals(tokens[0])) {
					doJoin(tokens[1],pw);
				}
				else if("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				}
				else if("quit".equals(tokens[0])) {
					doQuit(pw);
					break;
				}else {
					ChatServer.log("에러"+tokens[0]);
				}
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
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

	private void doMessage(String message) {
		String data=nickname+":"+message;
		System.out.println(data);
		broadcast(data);
		
	}

	private void doJoin(String nickName, Writer writer) {
		this.nickname=nickName;
		String data=nickName+"입장";
		System.out.println(data);
		broadcast(data);
		addWriter(writer);
		pw.println("join:ok");
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
		
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);
		String str=nickname+"퇴장";
		System.out.println(nickname+"퇴장");
		broadcast(str);
		
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter=(PrintWriter)writer;
				printWriter.println(data);
				
			}
		}
		
	}

	private void removeWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
		
	}
	

}
