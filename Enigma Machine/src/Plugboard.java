import java.util.ArrayList;

public class Plugboard {
	
	ArrayList<Plug> plugsInPlugboard = new ArrayList<Plug>();
	
	private int numberOfPlugs = plugsInPlugboard.size();
	
	//method for checking and adding a plug you want to add
	public boolean addPlug(char plugInput1, char plugInput2) {
		Plug tempPlug = new Plug(plugInput1, plugInput2);
		
		//going through all plugs and seeing if the new plug fits with all current plugs
		for (Plug testPlug : plugsInPlugboard) {
			
			if (tempPlug.clashesWith(testPlug)) {
				return false;
			}
		}
		
		//since plug does not clash then we add the plug
		plugsInPlugboard.add(new Plug(plugInput1, plugInput2));
		return true;
	}
		
	//method for clearing all plugs
	public void clear() {
		plugsInPlugboard.clear();
	}
	
	//method for getting number of plugs
	public Integer getNumbPlugs() {
		return numberOfPlugs;
	}
	
	//method for the Plugboard to encode a character
	public char substitute(char inputSubChar) {
		
		//for loop to check all plugs in Plugboard
		for (Plug tempPlug : plugsInPlugboard) {
			
			//using an if statement to check if the given char is part of a plug in plugboard
			if (tempPlug.getEnd1() == inputSubChar || tempPlug.getEnd2() == inputSubChar) {
				return tempPlug.encode(inputSubChar);
			} 
		}
		return inputSubChar;
	}
}

  