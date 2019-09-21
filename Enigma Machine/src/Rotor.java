
public abstract class  Rotor {
	
	String rotorName;
	int rotorPosition, ROTORSIZE = 26;
	int[] rotorMapping;
	
	
	//defining some abstract methods
	public abstract void initialise(String initialString);
	
	public abstract int substitute(int i);
	
	//setter and getter methods for member variables
	public int getPosition() {
		return rotorPosition;
	}
	
	public void setPosition(int i) {
		rotorPosition = i;
	}
	
	public String getRotorType() {
		return rotorName;
	}

}
