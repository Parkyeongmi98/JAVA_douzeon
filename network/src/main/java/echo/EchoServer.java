package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT = 8000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("starts... [port: " + PORT + "]");
			
			Socket socket = serverSocket.accept();
			
			InetSocketAddress inetRemoteSocketAddress = 
					(InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteport = inetRemoteSocketAddress.getPort();
			log("connected by client[" + remoteHostAddress + ":" + remoteport + "]");
			
			try {
				PrintWriter pw = new PrintWriter(
						new OutputStreamWriter(socket.getOutputStream()), true);
				
				BufferedReader br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				while (true) {
					String data = br.readLine(); // 갱을 붙여라
					if(data == null) {
						log("closed by client");
						break;
					}
					
					log("received: " + data);
					pw.println(data);
				}
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
			
		} catch (IOException e) {
			log("error: " + e); // 서버에서 나는 오류
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
	
	private static void log(String message) {
		System.out.println("[EchoServer]" + message);
	}
}