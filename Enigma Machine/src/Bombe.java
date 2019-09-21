
public class Bombe {
	
	EnigmaMachine bombeMachine;
	
	//
	public String challenge1() {
		
		int n = 0;
		for (int i = 0; i < 26; i++) {
			for (int x = 0; x < 26; x++) {
			
			char a = (char) (i + 65);
			char b = (char) (x + 65);
			
			bombeMachine = new EnigmaMachine();
			
			BasicRotor slot0 = new BasicRotor("typeIV");
			BasicRotor slot1 = new BasicRotor("typeIII");
			BasicRotor slot2 = new BasicRotor("typeII");
			Reflector reflector1 = new Reflector();
			reflector1.initialise("ReflectorI");
			
			bombeMachine.addRotor(slot0, 0);
			bombeMachine.addRotor(slot1, 1);
			bombeMachine.addRotor(slot2, 2);
			bombeMachine.addReflector(reflector1);
			
			bombeMachine.enigmaRotors.get(0).setPosition(8);
			bombeMachine.enigmaRotors.get(1).setPosition(4);
			bombeMachine.enigmaRotors.get(2).setPosition(21);
			
			bombeMachine.addPlug('D', a);
			bombeMachine.addPlug('S', b);
			String encodedString = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI"; 
			String decodedString = bombeMachine.encodeString(encodedString);
			
				if (decodedString.contains("ANSWER")) {
					n = n + 1;
					System.out.println("Decoded Message: " + decodedString);
					System.out.println("Plugs used: " + a + " - " + b);
				}
			}	
		}
		System.out.println(n + " setting(s) found");
		return "";
	}
	
	public String challenge2() {
		int n = 0;
		for (int x = 0; x <= 25; x++) {
			for (int y = 0; y <= 25; y++) {
				for (int z = 0; z <= 25; z++) {
					bombeMachine = new EnigmaMachine();
					
					BasicRotor slot0 = new BasicRotor("typeV");
					BasicRotor slot1 = new BasicRotor("typeIII");
					BasicRotor slot2 = new BasicRotor("typeII");
					Reflector reflector1 = new Reflector();
					reflector1.initialise("ReflectorI");
					
					bombeMachine.addRotor(slot0, 0);
					bombeMachine.addRotor(slot1, 1);
					bombeMachine.addRotor(slot2, 2);
					bombeMachine.addReflector(reflector1);
					
					bombeMachine.enigmaRotors.get(2).setPosition(z);
					bombeMachine.enigmaRotors.get(1).setPosition(y);
					bombeMachine.enigmaRotors.get(0).setPosition(x);
					
					bombeMachine.addPlug('H', 'L');
					bombeMachine.addPlug('G', 'P');
					
					String encodedString = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
					String decodedString =  bombeMachine.encodeString(encodedString);
					if (decodedString.contains("ELECTRIC")) {
						n = n + 1;
						System.out.println("Decoded message: " + decodedString);
						System.out.println("First BasicRotor position: " + x);
						System.out.println("Second BasicRotor position: " + y);
						System.out.println("Third BasicRotor position: " + z);
					}
				}
			}
		}
		System.out.println(n + " setting(s) found");
		return "";
	}
	
	public String challenge3() {
		int n = 0;
		String slotx = "typeI", sloty = "typeI", slotz = "typeI";
		for (int x = 1; x <= 5; x++) {
			for (int y = 1; y <= 5; y++) {
				for (int z = 1; z <= 5; z++) {
					if (x == 1) {
						slotx = "typeI";
					} else if (x == 2) {
						slotx = "typeII";
					} else if (x == 3) {
						slotx = "typeIII";
					} else if (x == 4) {
						slotx = "typeIV";
					} else if (x == 5) {
						slotx = "typeV";
					}
					if (y == 1) {
						sloty = "typeI";
					} else if (y == 2) {
						sloty = "typeII";
					} else if (y == 3) {
						sloty = "typeIII";
					} else if (y == 4) {
						sloty = "typeIV";
					} else if (y == 5) {
						sloty = "typeV";
					}
					if (z == 1) {
						slotz = "typeI";
					} else if (z == 2) {
						slotz = "typeII";
					} else if (z == 3) {
						slotz = "typeIII";
					} else if (z == 4) {
						slotz = "typeIV";
					} else if (z == 5) {
						slotz = "typeV";
					} 
					
					bombeMachine = new EnigmaMachine();
					
					BasicRotor slot0 = new BasicRotor(slotx);
					BasicRotor slot1 = new BasicRotor(sloty);
					BasicRotor slot2 = new BasicRotor(slotz);
					Reflector reflector1 = new Reflector();
					reflector1.initialise("ReflectorI");
					
					bombeMachine.addRotor(slot0, 0);
					bombeMachine.addRotor(slot1, 1);
					bombeMachine.addRotor(slot2, 2);
					bombeMachine.addReflector(reflector1);
					
					
					bombeMachine.enigmaRotors.get(0).setPosition(22);
					bombeMachine.enigmaRotors.get(1).setPosition(24);
					bombeMachine.enigmaRotors.get(2).setPosition(23);
					
					bombeMachine.addPlug('M', 'F');
					bombeMachine.addPlug('O', 'I');
					
					String encodedString = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
					String decodedString = bombeMachine.encodeString(encodedString);
					
					if (decodedString.contains("JAVA")) {
						n = n + 1;
						System.out.println("Decoded message: " + decodedString);
						System.out.println("First BasicRotor type: " + slotx);
						System.out.println("Second BasicRotor type: " + sloty);
						System.out.println("Third BasicRotor type: " + slotz);
					}
				}
			}
		}
		System.out.println(n + " setting(s) found");
		return "";
	}
}
