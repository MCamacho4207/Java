
public class Reflector extends Rotor {
	
	private int[] reflector1 = {24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
	private int[] reflector2 = {5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11};
	
	//method for initialising the reflector
	public void initialise(String initialString) {
		if (initialString == "ReflectorI") {
			rotorMapping = reflector1;
		} else if (initialString == "ReflectorII") {
			rotorMapping = reflector2;
		} else {
			
		}
	}
	
	//method for returning the number in the array
	public int substitute(int i) {
		/*
		int n = 0;
		for (int x: rotorMapping) {
			if (i == x) {
				
				return n;
				
			}
			n++;
		}
		return n;
		*/
		return rotorMapping[i];
	}
	
}

