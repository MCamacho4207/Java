
public class TurnoverRotor extends BasicRotor{
	
	int turnoverPosition, currentSlot;
	BasicRotor adjacentRotor;
	
	//constructor which defines the rotorType, currentSlot and adjacentRotor of this rotor (note the adjacent rotor can be null)
	public TurnoverRotor(String rotorType, BasicRotor inputRotor, int inputSlot) {
		super (rotorType);
		if (rotorType == "typeI") {
			turnoverPosition = 24;
		}else if (rotorType == "typeII") {
			turnoverPosition = 12;
		}else if (rotorType == "typeIII") {
			turnoverPosition = 3;
		}else if (rotorType == "typeIV") {
			turnoverPosition = 17;
		}else if (rotorType == "typeV") {
			turnoverPosition = 7;
		} else {
			
		}
		adjacentRotor = inputRotor;
		currentSlot = inputSlot;
	}
	
	//simple method which rotates the adjacentRotor depending on the currentSlot
	public void rotateNextRotor() {
		if (currentSlot == 0 || currentSlot == 1) {
			adjacentRotor.rotate();
		} 
	}
	
	//rotates this rotor and and subsequent rotors depending on turnoverPosition 
	public void rotate() {
		if (rotorPosition + 1 == ROTORSIZE && rotorPosition + 1 == turnoverPosition) {
			rotorPosition = 0;
			rotateNextRotor();
		} else if (rotorPosition + 1 == turnoverPosition){
			rotorPosition = rotorPosition + 1;
			rotateNextRotor();
		} else if (rotorPosition + 1 == ROTORSIZE) {
			rotorPosition = 0;
		} else {
			rotorPosition = rotorPosition + 1;
		}
	}
	
	//getter and setter methods for member variables
	public int getCurrentSlot() {
		return currentSlot;
	}
	
	public void setCurrentSlot(int inputSlot) {
		currentSlot = inputSlot;
	}
	
	public int getTurnoverPosition() {
		return turnoverPosition;
	}
	
	public void setTurnoverPosition(int inputPosition) {
		turnoverPosition = inputPosition;
	}
	
	public BasicRotor getAdjacentRotor() {
		return adjacentRotor;
	}
}
