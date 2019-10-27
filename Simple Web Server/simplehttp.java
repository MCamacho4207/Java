import java.util.*;
import java.net.*;
import java.io.*;

public class simplehttp implements Runnable {
	
	static final File WEB_ROOT = new File(".");
	static final String DEFAULT_FILE = "index.html";
	static final String FILE_NOT_FOUND = "404.html";
	static final String METHOD_NOT_SUPPORTED = "not_supported.html";
	
	private Socket httpSocket;
	private static int portNum = 8080;
    private BufferedReader read;
    private PrintWriter output;
    private BufferedOutputStream dataOutput;
	
	
    public static void main(String[] args) {
		try {
			ServerSocket httpServer = new ServerSocket(portNum);
			System.out.println("HTTP Server running. Awaiting messages...");
			
			
			while (true) {
				JavaHTTPServer myServer = new JavaHTTPServer(httpServer.accept());
				
				
				Thread thread = new Thread(myServer);
				thread.start();
			}
			
		} catch (IOException e) {
			System.err.println("Connection error : " + e.getMessage());
		}
	}
	
	public simplehttp (Socket s) {
		httpSocket = s;
	}

	@Override
	public void run() {
		
		try {
			read = new BufferedReader(new InputStreamReader(httpSocket.getInputStream()));
			
			output = new PrintWriter(httpSocket.getOutputStream());
			
			dataOutput = new BufferedOutputStream(httpSocket.getOutputStream());
			
			
			String inputLine = read.readLine();
			StringTokenizer parse = new StringTokenizer(inputLine);
			
			String headerCommand = parse.nextToken().toUpperCase();
			String fileRequested = parse.nextToken().toLowerCase();
			
			if (headerCommand.equals("GET")) {
				
				if (fileRequested.endsWith("/")) {
					fileRequested += DEFAULT_FILE;
				}
				
				File file = new File(WEB_ROOT, fileRequested);
				int fileLength = (int) file.length();
				String content = getContentType(fileRequested);
				
				if (headerCommand.equals("GET")) { 
					byte[] fileData = readFileData(file, fileLength);
					
					
					//output.println("HTTP/1.1 404 Not Found");
					output.println("HTTP/1.1 200 OK");
					
					output.println("Date: " + new Date());
					output.println("Server: Java HTTP mac3g17");
					output.println("Content-type: " + content);
					output.println("Content-length: " + fileLength);
					output.println(""); 
					output.flush();
					
					dataOutput.write(fileData, 0, fileLength);
					dataOutput.flush();
				}
				
			} else {
				File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
				int fileLength = (int) file.length();
				String contentMimeType = "text/html";
				
				byte[] fileData = readFileData(file, fileLength);
				
				output.println("HTTP/1.1 501 Not Implemented");
				output.println("Server: Java HTTP mac3g17");
				output.println("Date: " + new Date());
				output.println("Content-type: " + contentMimeType);
				output.println("Content-length: " + fileLength);
				output.println("");
				output.flush(); 
				
				dataOutput.write(fileData, 0, fileLength);
				dataOutput.flush();
			}
			
			
			System.out.println(inputLine);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	
	private String getContentType(String fileRequested) {
		if (fileRequested.endsWith(".htm")  ||  fileRequested.endsWith(".html"))
			return "text/html";
		else
			return "text/plain";
	}
	
	private byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];
		
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) 
				fileIn.close();
		}
		
		return fileData;
	}

}