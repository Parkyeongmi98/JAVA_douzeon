package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private static final String DOCUMENT_ROOT = "./webapp";
	private Socket socket;
	
	public RequestHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), "utf-8"));

			
			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort());
				
			String request = null;
			while (true) {
				String line = br.readLine();
				
				// 브라우저 연결을 끊으면
				if (line == null) {
					break;
				}
				
				// SimpleHttpServer는 요청의 헤더만 처리
				if("".equals(line)) {
					break;
				}
				
				// 요청 헤더의 첫 번째 라인만 읽음
				if(request == null) {
					request = line;
					break;
				}
			}
			
			// 요청 처리
//			consoleLog(request);
			String[] tokens = request.split(" ");
			// tokens[0] : 메소드   tokens[1] : url   tokens[2] : protocol
			if("GET".equals(tokens[0])) {
				consoleLog(request);
				reponseStaticResource(outputStream, tokens[1], tokens[2]);
			} else {
				// methods: POST, PUT, DELETE, HEAD, CONNECT
				// SimpleHttpServer 에서는 무시(400 Bad Resquest)
				// reponse400Error(outputStream, tokens[2]);
				
			}
		} catch(Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try{
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
				
			} catch(IOException ex) {
				consoleLog("error:" + ex);
			}
		}			
	}
	
	private void reponseStaticResource (
			OutputStream outputStream, 
			String url, 
			String protocol) throws IOException {
		// default(welcome) file set
		if("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + url);
		
		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		// 응답
		outputStream.write("HTTP/1.1 200 OK\r\n".getBytes("UTF-8"));
		outputStream.write(("Content-Type:" + contentType + "; charset=utf-8\r\n").getBytes("UTF-8"));
		outputStream.write("\r\n".getBytes());
		outputStream.write(body);
	}

	public void consoleLog(String message) {
		System.out.println("[httpd#" + getId() + "] " + message);
	}
}
