
public class Plug {
	
	private char end1;
	private char end2;
	
	public Plug (char input1, char input2) {
		//checking if trying to create Plug with the same ends
		if (input1 == input2) {
			
		} else {
			end1 = input1;
			end2 = input2;
		}
	}
	
	//method for getting first end of Plug
	public char getEnd1() {
		return end1;
	}
	
	//method for getting second end of Plug
	public char getEnd2() {
		return end2;
	}
	
	//method for setting first end of Plug
	public void setEnd1(char inputEnd1) {
		end1 = inputEnd1;
	}
	
	//method for setting the second end of Plug
	public void setEnd2(char inputEnd2) {
		end2 = inputEnd2;
	}
	
	//method to easily check the outcome of giving a 
	public char encode(char letterIn) {
		//checking whether letterIn is end1 or end2
		if (letterIn == end1) {
			return end2;
		} else if (letterIn == end2) {
			return end1;
		} else {
			return letterIn;
		}
	}
	
	//method for checking if a Plug can be connected to a Plugboard
	public boolean clashesWith(Plug plugin) {
		if (plugin.getEnd1() == end1 || plugin.getEnd1() == end2 || plugin.getEnd2() == end1 || plugin.getEnd2() == end2) {
			return true;
		} else {
			return false;
		}
	}
}
