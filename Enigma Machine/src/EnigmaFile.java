import java.io.*;
import java.util.*;

public class EnigmaFile {
	
	EnigmaMachine fileEnigma;
	BasicRotor firstRotor, secondRotor, thirdRotor;
	Reflector fileReflector;
	int defaultPos1 = 5, defaultPos2 = 10, defaultPos3 = 15;
	
	
	//constructor which instantiates an EnigmaMachine with some default settings
	public EnigmaFile() {
		fileEnigma = new EnigmaMachine();
		firstRotor = new BasicRotor("typeI");
		firstRotor.setPosition(5);
		secondRotor = new BasicRotor("typeII");
		secondRotor.setPosition(10);
		thirdRotor = new BasicRotor("typeIII");
		thirdRotor.setPosition(15);
		fileReflector = new Reflector();
		fileReflector.initialise("ReflectorI");
		fileEnigma.addRotor(firstRotor, 0);
		fileEnigma.addRotor(secondRotor, 1);
		fileEnigma.addRotor(thirdRotor, 2);
		fileEnigma.addReflector(fileReflector);
	}
	
	//method for reading the file when given a file name
	public ArrayList<String> readFile(String fileName) {
		
		ArrayList<String> completeFile =  new ArrayList<String>();
		boolean shouldRead = true;
		
		//using java.io to read a file and using try-catch blocks to handle exceptions
		try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))){
			int i = 0;
			
			//checking our condition on whether to read the file or not
			while (shouldRead) {
				
				String currentLine = fileReader.readLine();
				
				//if currentLine is not null, we save the line to the ArrayList, else we stop
				if (currentLine != null) {
					completeFile.add(i, currentLine);
					i++;
				} else {
					break;
				}
			} 
		} catch(IOException e) {
				System.err.println(e);
		}
		return completeFile;
	}
	
	//method for taking an ArrrayList and combining it into a single String and return an ArrayList which is encoded by the default settings
	public ArrayList<String> encodeFile(ArrayList<String> inputFile) {
		
		ArrayList<String> resultList = new ArrayList<String>();
		String originalMsg = "";
		String encodedMsg = "";
		String[] resultArray;
		
		//loops through the ArrayList and creates a single String comprising of all the Strings in the ArrayList
		for (int i = 0; i < inputFile.size(); i++) {
			originalMsg = originalMsg + inputFile.get(i) + "a";
		}
		
		//encodes the single String and splits the String on the "space" ('a')
		encodedMsg = fileEnigma.encodeString(originalMsg);
		resultArray = encodedMsg.split("a");
		
		//reconstructs the ArrayList
		for (String x: resultArray) {
			resultList.add(x);
		}
		//rests the rotors in the EnigmaMachine;
		this.resetRotors();
		return resultList;
	}
	
	//method for creating a file and writing the objects of an ArrayList into the File in chronological order
	public void writeFile(String encodedFileName, ArrayList<String> encodedMsg) {
		
		//using PrintWriter to create a File while surrounding it with try-catch blocks 
		try {
			PrintWriter fileWriter = new PrintWriter(encodedFileName);
			
			//loops through all Strings in the ArrayList and creates a line and adds the String to it
			for (String x: encodedMsg) {
				fileWriter.println(x);
			}
			fileWriter.close();
		} catch(Exception e) {
			
		}
	}
	
	//resets the rotors to the default positions
	public void resetRotors() {
		firstRotor.setPosition(defaultPos1);
		secondRotor.setPosition(defaultPos2);
		thirdRotor.setPosition(defaultPos3);
	}
}
