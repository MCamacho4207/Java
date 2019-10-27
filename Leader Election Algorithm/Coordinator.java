import java.net.*;
import java.util.*;
import java.io.*;


public class Coordinator implements Runnable{

	private InetAddress localAddr; 
	private ServerSocket coordSocket;
	private int myPort;
	private int max_participants = 4;
	private ArrayList<ParticipantInfo> participantList;
	private int counter = 0;
	private boolean end = false;
	private String voteCandidates;
	
	
	
	public Coordinator() {
		
	}
	
	public Coordinator(int port, int participants, String options) {
		myPort = port;
		max_participants = participants;
		voteCandidates = options;
		participantList = new ArrayList<ParticipantInfo>();
	}
			

	@Override
	public void run() {
		
		try {
			localAddr = InetAddress.getLocalHost(); 
			
			
			coordSocket = new ServerSocket(myPort, 100);
			coordSocket.setSoTimeout(5000);
			System.out.println("Coordinator connected.");
			System.out.println("Coordinator waiting...");
			
			
			while (true) {
				try {
					while(true) {
						Socket s = coordSocket.accept(); 
						
						
						BufferedReader tempin = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
						
						String readStr = tempin.readLine();
						while (readStr !=  null) {
							System.out.println("msg read as: " + readStr);
							StringTokenizer tok = new StringTokenizer(readStr);
							String first = tok.nextToken();
							String second = tok.nextToken();
							
							if (first.equals("JOIN") && (second != null)) {
								participantList.add(new ParticipantInfo(s, Integer.parseInt(second)));
								System.out.println("Socket added!");
							}
							break;
						}
						
						if (participantList.size() >= max_participants) {
							break;
						}
					}
					
					
				    //String str = getDetails(participantList.get(0));
					sendDetailsAll();
					System.out.println("Details succesfully sent!");
					
					sendVoteOptions();
				    System.out.println("Vote options succesfully sent!");
					
					break;	
					
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("ERROR AT initialisation loop");
					break;
				}
			}
		    
		    coordSocket.close();
		    System.out.println("Coordinator closed");		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void printAll() {
		Iterator<ParticipantInfo> itr = participantList.iterator();
		
		while (itr.hasNext()) {
			try {
				itr.next().printStr();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR AT printAll()");
			}
		}	
	}
	
	public void sendDetailsAll() {
		Iterator<ParticipantInfo> itr = participantList.iterator();
		
		while (itr.hasNext()) {
			try {
				ParticipantInfo p = itr.next();
				PrintStream s = p.getOut();
				
				s.println("DETAILS " + getDetails(p));
				s.flush();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR AT sendAll()");
			}
		}
	}
	
	public void sendVoteOptions() {
		Iterator<ParticipantInfo> itr = participantList.iterator();
		
		while (itr.hasNext()) {
			ParticipantInfo p = itr.next();
			
			p.getOut().println("VOTE_OPTIONS " + voteCandidates);
			p.getOut().flush();
		}
		
	}
	
	public String getDetails(ParticipantInfo p) {
		String s = "";
		Iterator<ParticipantInfo> itr = participantList.iterator();
		
		while (itr.hasNext()) {
			String end = Integer.toString(itr.next().getPort());
			if (!end.equals(Integer.toString(p.getPort()))) {
				s = end + " " + s;
			}
			
		}
		
		return s;
	}
	
	
	
}


