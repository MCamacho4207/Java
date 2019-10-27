import server.*;

public class ServerApplication {
	
	public void initialise() {
		
	}
	
	public void launchGUI(Server serverInterface) {
		ServerWindow myServerWindow = new ServerWindow(serverInterface);
	}
	
	public void start() {
		initialise();
		launchGUI(new Server());
	}
	
	public static void  main(String args[]) {
		ServerApplication myServerApplication = new ServerApplication();
		myServerApplication.start();
	}

}
