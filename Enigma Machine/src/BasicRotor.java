
public class BasicRotor extends Rotor{
	
					  
	int[] basicRotor1 = {4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9};
	int[] basicRotor2 = {0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4};
	int[] basicRotor3 = {1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14};
	int[] basicRotor4 = {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};
	int[] basicRotor5 = {21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10};
	
	//constructor which checks which rotor type to use
	public BasicRotor(String rotorType) {
		this.initialise(rotorType);
	}
	
	//method for substituting an integer depending on rotor position
	public int substitute(int i) {
		
		//removes the rotorPosition before mapping
		i = i - rotorPosition;
		
		//checking if the result is negative
		if (i < 0) {
			i = 26 + i;
		} 
		
		//returns the result of the mapping and checks of the result is bigger than 25
		int output = rotorMapping[i] + rotorPosition;
		if (output > 25) {
			//result is bigger than 25 so we loop around the alphabet by substracting 26
			output = output - 26;
		}
		return output;
	}
	
	//method to return original integer given from substitute (inverse method) 
	public int substituteBack(int i) {
		
		//removes rotorPosition before mapping and checks if result is negative
		i = i - rotorPosition;
		if (i < 0) {
			i = i + 26;
		}
		
		//this for-loop will look for the position in the array which will return the mapping given
		int n = 0;
		for (int x: rotorMapping) {
			if (i == x) {
				
				break;
				
			}
			n++;
		}
		
		//n will be the position in the mapping when a match is found
		int result = n + rotorPosition;
		if (result > 25) {
			result = result - 26;
		}
		return result;	
	} 
	
	//method which checks which rotor type to use
	public void initialise(String rotorType) {
		if (rotorType == "typeI") {
			rotorMapping = basicRotor1;
		} else if(rotorType == "typeII") {
			rotorMapping = basicRotor2;
		} else if(rotorType == "typeIII") {
			rotorMapping = basicRotor3;
		} else if(rotorType == "typeIV") {
			rotorMapping = basicRotor4;
		} else if(rotorType == "typeV") {
			rotorMapping = basicRotor5;
		} else {
			
		}
		rotorName = rotorType;
	}
	
	//rotates the rotorPosition by one unless its reached its ROTORSIZE where it resets it to 0
	public void rotate() {
		if (rotorPosition + 1 == ROTORSIZE) {
			rotorPosition = 0;
		} else {
			rotorPosition = rotorPosition + 1;
		}
	}
	
}
