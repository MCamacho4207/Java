import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ParticipantInfo {
		private Socket socket;
		private BufferedReader in;
		private PrintStream out;
		private int portNum;
		
		ParticipantInfo(Socket givenSocket, int givenPort) {
			socket = givenSocket;
			portNum = givenPort;
			
			try {
				in = new BufferedReader(new InputStreamReader (givenSocket.getInputStream()));
				out = new PrintStream (givenSocket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR AT ParticipantInfo Constructor");
			}
			
		}
		
		public PrintStream getOut() {
			return out;
		}
		
		public BufferedReader getIn() {
			return in;
		}
		
		public Socket getSocket() {
			return socket;
		}
		
		public int getPort() {
			return portNum;
		}
		
		public void printStr() {
			try {
				System.out.println((Integer.toString(portNum)) + " says: " + in.readLine());
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR AT printStr()");
			}
		}
	}