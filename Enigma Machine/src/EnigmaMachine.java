import java.util.*;

public class EnigmaMachine {
	
	Plugboard enigmaBoard;
	ArrayList<BasicRotor> enigmaRotors;
	Reflector reflectRotor;
	
	//contructor which instantiates the Plugboard and ArraList<BasicRotor>
	public EnigmaMachine() {
		enigmaBoard = new Plugboard();
		enigmaRotors = new ArrayList<BasicRotor>();
	}
	
	//method for adding Plugs to the Plugboard
	public boolean addPlug(char a, char b) {
		return enigmaBoard.addPlug(a, b);
	}
	
	//removes all Plugs in Plugboard
	public void clearPlugboard() {
		enigmaBoard.clear();
	}
	
	//method for encoding a letter based on state of EnigmaMachine
	public char encodeLetter(char inputOriginalChar) {
		
		//checking for "space" character
		if (inputOriginalChar == 'a') {
			return 'a';
		}
		
		//takes input from keyboard and changes it depending on the state of Plugs in Plugboard
		char subChar = enigmaBoard.substitute(inputOriginalChar);
		
		//uses Integer class to change from char to int
		int charInt = new Integer(subChar).intValue() - 65;
		
		//sends the int to the rotors in consecutive order while the rotors map the input to their appropriate mapping
		int charInt0 = enigmaRotors.get(0).substitute(charInt);
		int charInt1 = enigmaRotors.get(1).substitute(charInt0);
		int charInt2 = enigmaRotors.get(2).substitute(charInt1);
		
		//sends output from the rotors to the reflector which is mapped to its mapping
		int charIntReflect = reflectRotor.substitute(charInt2);
		
		//sends output from the reflector to the rotors in reverse consecutive order while mapping its value to their mapping
		int charIntInverse2 = enigmaRotors.get(2).substituteBack(charIntReflect);
		int charIntInverse1 = enigmaRotors.get(1).substituteBack(charIntInverse2);
		int charIntInverse0 = enigmaRotors.get(0).substituteBack(charIntInverse1);
		
		//uses casting to convert from int to char
		char boardChar = (char) (charIntInverse0 + 65);
		
		//changes character depending on state of Plugboard
		char encodedChar = enigmaBoard.substitute(boardChar);
		
		//rotates the first rotor
		enigmaRotors.get(0).rotate();
		
		return encodedChar;
	} 
	
	public String encodeString(String inputMsg) {
		
		String encodedString = "";
		ArrayList<String> encodedMsg = new ArrayList<String>();
		
		//loops through the String and adds the encoded the Character to an ArrayList<String>
		for (int i = 0; i <= inputMsg.length() - 1; i++) {
			Character a = this.encodeLetter(inputMsg.charAt(i));
			//converts the Character to a String and adds it to the List
			encodedMsg.add(a.toString());
		}
		
		Iterator<String> charIterator = encodedMsg.iterator();
	
		//creates a single String from the ArrayList
		for (int i = 0; i <= encodedMsg.size() - 1; i++) {
			encodedString = encodedString + encodedMsg.get(i);
		}
		return encodedString;
	}
	
	//getter and setter methods for EnigmaMachine
	public ArrayList<BasicRotor> getRotorList() {
		return enigmaRotors;
	}
	
	public void addRotor(BasicRotor inputBasicRotor, int slot) {
		enigmaRotors.add(slot, inputBasicRotor);
	}
	
	public BasicRotor getRotor(int slot) {
		return enigmaRotors.get(slot);
	}
	
	public void addReflector(Reflector inputReflector) {
		reflectRotor = inputReflector;
	}
	
	public Reflector getReflector() {
		return reflectRotor;
	}
	
	public void setPosition(int slot, int inputRotorPosition) {
		enigmaRotors.get(slot).setPosition(inputRotorPosition);
	}
}
