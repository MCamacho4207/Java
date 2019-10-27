import java.net.*;
import java.util.*;
import java.io.*;


public class Participant implements Runnable{
	
	private String name;
	private String currentVote = "VOTE";
	private InetAddress localAddr; 
	private ServerSocket partSocket;
	private Socket coordSocket;
	private int partPort, coordPort;
	private BufferedReader in;
	private PrintStream out;
	private boolean end = false;
	private ArrayList<String> voteOptions;
	private ArrayList<String> participantIdentifiers;
	private ArrayList<ParticipantInfo> participantsInvolved;
	
	public Participant() {
		
	}
	
	public Participant(int port1, int port2, String Id) {
		partPort = port1;
		coordPort = port2;
		name = Id;
		voteOptions = new ArrayList<String>();
		participantIdentifiers = new ArrayList<String>();
		participantsInvolved = new ArrayList<ParticipantInfo>();
	}
			

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println("Connecting " + name + " ...");
			localAddr = InetAddress.getLocalHost(); 
			
			partSocket = new ServerSocket(partPort, 100);
			partSocket.setSoTimeout(500);
			coordSocket = new Socket(localAddr, coordPort);
			coordSocket.setSoTimeout(10000);
			out = new PrintStream(coordSocket.getOutputStream());
			out.flush();
			in = new BufferedReader( new InputStreamReader( coordSocket.getInputStream() ) );
			
			System.out.println(name + " Connected.");
			
			while(true) {
				try {
					
					out.println("JOIN " + Integer.toString(partPort));
					out.flush();
					System.out.println(name + " sent msg");
					end = true;
					break;
				} catch(Exception e) {
					e.printStackTrace();
					break;
				}
			}
			
			try {
				Thread.sleep(50);
				String firstMsg = in.readLine();
				StringTokenizer firstTok = new StringTokenizer(firstMsg);
				String head1 = firstTok.nextToken();
				
				if (head1.equals("DETAILS")) {
					while (firstTok.hasMoreTokens()) {
						participantIdentifiers.add(firstTok.nextToken());
					}
				}
				
				
				
				Thread.sleep(50);
				String secondMsg = in.readLine();
				StringTokenizer secondTok = new StringTokenizer(secondMsg);
				String head2 = secondTok.nextToken();
						
				if (head2.equals("VOTE_OPTIONS")) {
					while (secondTok.hasMoreTokens()) {
						voteOptions.add(secondTok.nextToken());
					}
				}
				
				
				System.out.println("Establishing connections...");
				establishAllConnections();
				
				
				startVote(1);
				
				
				deliverOutcome();
				
				
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR AT RECIEVING DETAILS AND VOTE OPTIONS");
			}
			
			
			Thread.sleep(6000);
			out.close();
			in.close();
			partSocket.close();
			coordSocket.close();
			System.out.println(name + " closed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("code reached here 77");
		}
	}
	
	public void deliverOutcome() {
		out.println(currentVote);
		out.flush();
	}
	
	public void startVote(int rounds) {
		currentVote = currentVote + " " + Integer.toString(partPort) + " " + chooseVote();
		
		System.out.println("Sending votes...");
		sendVote(rounds);
		
		System.out.println("Recieving votes...");
		recieveVote();
	}
	
	public void sendVote(int rounds) {
		for (int i = 0; i < rounds; i++) {
			Iterator<ParticipantInfo> itr = participantsInvolved.iterator();
			
			while (itr.hasNext()) {
				ParticipantInfo p = itr.next();
				
				p.getOut().println(" " + Integer.toString(partPort) + " " + chooseVote());
				p.getOut().flush();
				
			}
		}
	}
	
	public void recieveVote() {
		
		Iterator<ParticipantInfo> itr = participantsInvolved.iterator();
		try {
			
			while(itr.hasNext()) {
				String s = itr.next().getIn().readLine();
				System.out.println("IMPORTANT - " + s);
				currentVote = currentVote += s;
						
			}
			
		} catch (Exception e) {
			System.err.println("ERROR AT READING VOTE");
		}
	}
	
	public void buildParticipantList() {
		Iterator<String> itr = participantIdentifiers.iterator();
		int tempPort = Integer.parseInt(itr.next());
		
		while (itr.hasNext()) {
			try {
				participantsInvolved.add(new ParticipantInfo(new Socket(localAddr, tempPort),tempPort));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void debug() {
		Iterator<ParticipantInfo> itr = participantsInvolved.iterator();
		
		while (itr.hasNext()) {
			System.out.println(itr.next().getPort());
		}
		
	}
	
	public void establishAllConnections() {
		
		while (participantsInvolved.size() < participantIdentifiers.size()) {
			Iterator<String> itr = participantIdentifiers.iterator();
			
			while (itr.hasNext()) {
				int portTemp = Integer.parseInt(itr.next());
				try {
					
					Socket s1 = new Socket(localAddr, portTemp);
					
					participantsInvolved.add(new ParticipantInfo(s1, portTemp));
					
				} catch (Exception e1) {
					try {
						Socket s2 = partSocket.accept();
						
						participantsInvolved.add(new ParticipantInfo(s2, portTemp));
						
						
						
						
					} catch (Exception e2) {
						System.err.println("failed");
					}
					
				}
			}
		}
		
		//System.out.println(participantsInvolved.get(0).getPort());
		
	}
	
	public String chooseVote() {
		
		return voteOptions.get(0);
	}
	
}
