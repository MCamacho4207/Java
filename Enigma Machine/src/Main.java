import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	Scanner myScanner = new Scanner(System.in);
	
	public void main() {
		while (true) {
			System.out.println("===============================================================================");
			System.out.println("||MAIN MENU||");
			System.out.println("Choose an option:");
			System.out.println("1: Encode your own messages in an Enigma Machine with your own settings");
			System.out.println("2: Enigma Machine in action (Part 5 and Part 7)");
			System.out.println("3: Reading text from a File and writing the encoded text to an output File (Part 6)");
			System.out.println("4: Testing for the settings in an Enigma Machine using a Bombe (Part 8)");
			System.out.println("5: Terminate Program");
			System.out.println("Enter your option number here: ");
			
			String response = myScanner.nextLine();
			
			if (response.equals("1")) {
				option1();
			} else if (response.equals("2")) {
				option2();
			} else if (response.equals("3")) {
				option3();
			} else if (response.equals("4")) {
				option4();
			} else if (response.equals("5")) {
				System.exit(0);
			} else {
				System.err.println("That was not a valid option!");
				//break;
			}
		}
	}
	
	public void option1() {
		while (true) {
			System.out.println("===============================================================================");
			System.out.println("Creating an Enigma Machine...");
			System.out.println("");
			EnigmaMachine customEnigma = new EnigmaMachine();
			
			System.out.println("What type will your first BasicRotor be?");
			System.out.println("1: typeI");
			System.out.println("2: typeII");
			System.out.println("3: typeIII");
			System.out.println("4: typeIV");
			System.out.println("5: typeV");
			System.out.println("6: Cancel and return to Main Menu");
			String firstRotorType = myScanner.nextLine();
			System.out.println("");
			
			if (firstRotorType.equals("1")) {
				firstRotorType = "typeI";
			} else if (firstRotorType.equals("2")) {
				firstRotorType = "typeII";
			} else if (firstRotorType.equals("3")) {
				firstRotorType = "typeIII";
			}else if (firstRotorType.equals("4")) {
				firstRotorType = "typeIV";
			}else if (firstRotorType.equals("5")) {
				firstRotorType = "typeV";
			}else if (firstRotorType.equals("6")) {
				System.out.println("Custom Enigma Machine canceled.");
				System.out.println("You have been returned to the Main Menu.");
				break;
			} else {
				System.out.println("That was not a valid input!");
				System.out.println("Returning to Main Menu...");
				break;
			}
			
			System.out.println("What initial position will the first BasicRotor be in?");
			int firstRotorPos = myScanner.nextInt();
			
			customEnigma.addRotor(new BasicRotor(firstRotorType), 0);
			customEnigma.getRotor(0).setPosition(firstRotorPos);
			
			System.out.println("What type will your second BasicRotor be?");
			System.out.println("1: typeI");
			System.out.println("2: typeII");
			System.out.println("3: typeIII");
			System.out.println("4: typeIV");
			System.out.println("5: typeV");
			System.out.println("6: Cancel and return to Main Menu");
			String s = myScanner.nextLine();
			s = s + s;
			String secondRotorType = myScanner.nextLine();
			System.out.println("");
			
			if (secondRotorType.equals("1")) {
				secondRotorType = "typeI";
			} else if (secondRotorType.equals("2")) {
				secondRotorType = "typeII";
			} else if (secondRotorType.equals("3")) {
				secondRotorType = "typeIII";
			}else if (secondRotorType.equals("4")) {
				secondRotorType = "typeIV";
			}else if (secondRotorType.equals("5")) {
				secondRotorType = "typeV";
			}else if (secondRotorType.equals("6")) {
				System.out.println("Custom Enigma Machine canceled.");
				System.out.println("You have been returned to the Main Menu.");
				break;
			} else {
				System.out.println("hey That was not a valid input!");
				System.out.println("Returning to Main Menu...");
				break;
			}
			
			System.out.println("What initial position will the second BasicRotor be in?");
			int secondRotorPos = myScanner.nextInt();
			
			customEnigma.addRotor(new BasicRotor(secondRotorType), 1);
			customEnigma.getRotor(1).setPosition(secondRotorPos);
			
			System.out.println("What type will your third BasicRotor be?");
			System.out.println("1: typeI");
			System.out.println("2: typeII");
			System.out.println("3: typeIII");
			System.out.println("4: typeIV");
			System.out.println("5: typeV");
			System.out.println("6: Cancel and return to Main Menu");
			String x = myScanner.nextLine();
			x = x + x;
			String thirdRotorType = myScanner.nextLine();
			System.out.println("");
			
			if (thirdRotorType.equals("1")) {
				thirdRotorType = "typeI";
			} else if (thirdRotorType.equals("2")) {
				thirdRotorType = "typeII";
			} else if (thirdRotorType.equals("3")) {
				thirdRotorType = "typeIII";
			}else if (thirdRotorType.equals("4")) {
				thirdRotorType = "typeIV";
			}else if (thirdRotorType.equals("5")) {
				thirdRotorType = "typeV";
			}else if (thirdRotorType.equals("6")) {
				System.out.println("Custom Enigma Machine canceled.");
				System.out.println("You have been returned to the Main Menu.");
				break;
			} else {
				System.out.println("hey That was not a valid input!");
				System.out.println("Returning to Main Menu...");
				break;
			}
			
			System.out.println("What initial position will the third BasicRotor be in?");
			int thirdRotorPos = myScanner.nextInt();
			
			customEnigma.addRotor(new BasicRotor(thirdRotorType), 2);
			customEnigma.getRotor(2).setPosition(thirdRotorPos);
			
			System.out.println("What type of Reflector would you like to use?");
			System.out.println("1: ReflectorI");
			System.out.println("2: ReflectorII");
			System.out.println("3: Cancel and return to Main Menu");
			String reflectorType = myScanner.nextLine();
			reflectorType = myScanner.nextLine();
			
			if (reflectorType.equals("1")) {
				reflectorType = "ReflectorI";
			} else if (reflectorType.equals("2")) {
				reflectorType = "ReflectorII";
 			} else if (reflectorType.equals("3")) {
 				System.out.println("Custom Enigma Machine canceled.");
 				System.out.println("You have been returned to the Main Menu.");
 			} else {
 				System.out.println("That was not a valid input!");
 				System.out.println("Returning to Main Menu...");
 			}
			
			Reflector customReflector = new Reflector();
			customReflector.initialise(reflectorType);
			customEnigma.addReflector(customReflector);
			
			System.out.println("How many plugs would you like?");
			int customPlugs = myScanner.nextInt();
			String temp= myScanner.nextLine();
			temp = temp + temp;
			
			for (int i = 1; i <= customPlugs; i++) {
				System.out.println("Enter the first end of plug number " + i + ": "); 
				String firstEnd = myScanner.nextLine();
				System.out.println("Enter the other end of plug number " + i + ": ");
				String otherEnd = myScanner.nextLine();
				customEnigma.addPlug(firstEnd.charAt(0), otherEnd.charAt(0));
			}
			
			System.out.println("");
			System.out.println("Please Enter the message you would like to encode");
			String inputMsg = myScanner.nextLine();
			inputMsg = inputMsg.toUpperCase();
			
			System.out.println("");
			System.out.println("Encoding message...");
			String encodedMsg = customEnigma.encodeString(inputMsg);
			
			System.out.println("");
			System.out.println("Custom Enigma Settings:");
			System.out.println("First Basic Rotor: Type = " + firstRotorType + ", Initial Position = " + firstRotorPos);
			System.out.println("Second Basic Rotor: Type = " + secondRotorType + ", Initial Position = " + secondRotorPos);
			System.out.println("Third Basic Rotor: Type = " + thirdRotorType + ", Initial Position = " + thirdRotorPos);
			System.out.println("Reflector: Type = " + reflectorType);
			System.out.print("Plugs used: ");
			for (Plug a: customEnigma.enigmaBoard.plugsInPlugboard) {
				System.out.print(a.getEnd1() + " - " + a.getEnd2() + ", ");
			}
			System.out.println("");
			System.out.println("The original message was: " + inputMsg);
			System.out.println("The encoded message is: " + encodedMsg);
			break;
		}
	}
	
	public void option2() {
		while (true) {
			System.out.println("===============================================================================");
			System.out.println("Which test would you like to see?");
			System.out.println("Choose an option:");
			System.out.println("1: First Test");
			System.out.println("2: Second Test");
			System.out.println("3: Third Test");
			System.out.println("4: Return to the Main Menu");
			System.out.println("Enter your option here:");
			String chosenOption2 = myScanner.nextLine();
			
			if (chosenOption2.equals("1")) {
				System.out.println("===============================================================================");
				System.out.println("Creating a new Enigma Machine and adjusting settings for the Test 1...");
				EnigmaMachine test1 = new EnigmaMachine(); 
				
				BasicRotor test1Slot0 = new BasicRotor("typeI");
				test1Slot0.setPosition(6);
				BasicRotor test1Slot1 = new BasicRotor("typeII");
				test1Slot1.setPosition(12);
				BasicRotor test1Slot2 = new BasicRotor("typeIII");
				test1Slot2.setPosition(5);
				
				Reflector test1Reflect = new Reflector();
				test1Reflect.initialise("ReflectorI");
				
				
				test1.addPlug('A', 'M');
				test1.addPlug('G', 'L');
				test1.addPlug('E', 'T');
				test1.addRotor(test1Slot0, 0);
				test1.addRotor(test1Slot1, 1);
				test1.addRotor(test1Slot2, 2);
				test1.addReflector(test1Reflect);
		        
				System.out.println("Decoding the String " + "\"GFWIQH\"...");
				String test1Result = test1.encodeString("GFWIQH");
				
				System.out.println("Result is " + test1Result);
			} else if (chosenOption2.equals("2")) {
				System.out.println("===============================================================================");
				System.out.println("Creating a new Enigma Machine and adjusting settings for the Test 2...");
				EnigmaMachine test2 = new EnigmaMachine(); 
				
				BasicRotor test2Slot0 = new BasicRotor("typeIV");
				test2Slot0.setPosition(23);
				BasicRotor test2Slot1 = new BasicRotor("typeV");
				test2Slot1.setPosition(4);
				BasicRotor test2Slot2 = new BasicRotor("typeII");
				test2Slot2.setPosition(9);
				
				Reflector test2Reflect = new Reflector();
				test2Reflect.initialise("ReflectorII");
				
				
				test2.addPlug('B', 'C');
				test2.addPlug('R', 'I');
				test2.addPlug('S', 'M');
				test2.addPlug('A', 'F');
				test2.addRotor(test2Slot0, 0);
				test2.addRotor(test2Slot1, 1);
				test2.addRotor(test2Slot2, 2);
				test2.addReflector(test2Reflect);
		        
				System.out.println("Decoding the String " + "\"GACIG\"...");
				String test2Result = test2.encodeString("GACIG");
				
				System.out.println("Result is " + test2Result);
			} else if (chosenOption2.equals("3")) {
				System.out.println("===============================================================================");
				System.out.println("Creating a new Enigma Machine and adjusting settings for the Test 3...");
				EnigmaMachine test3 = new EnigmaMachine();
				
				TurnoverRotor test3Slot2 = new TurnoverRotor("typeIII", null, 2);
				test3Slot2.setPosition(7);
				TurnoverRotor test3Slot1 = new TurnoverRotor("typeII", test3Slot2, 1);
				test3Slot1.setPosition(11);
				TurnoverRotor test3Slot0 = new TurnoverRotor("typeI", test3Slot1, 0);
				test3Slot0.setPosition(23);
				Reflector test3Reflect = new Reflector();
				test3Reflect.initialise("ReflectorI");
				
				test3.addPlug('Q', 'F');
				
				test3.addRotor(test3Slot0, 0);
				test3.addRotor(test3Slot1, 1);
				test3.addRotor(test3Slot2, 2);
				test3.addReflector(test3Reflect);
				
				System.out.println("Decoding the String " + "\"OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT\"...");
				String test3Result = test3.encodeString("OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT");
				System.out.println("Result is: " + test3Result);
			} else if (chosenOption2.equals("4")) {
				break;
			} else {
				System.out.println("That was not a valid option!");
				System.out.println("You have been returned to the Main Menu.");
				break;
			}
		}
	}
	
	public void option3() {
		System.out.println("===============================================================================");
		String inputFileName = "TestFile.txt";
		
		System.out.println("Attempting to find and read File: " + inputFileName);
		EnigmaFile test = new EnigmaFile();
		ArrayList<String> input = test.readFile("TestFile.txt");
		
		System.out.println("");
		System.out.println("Printing the contents of the File...");
		for (String x: input) {
			System.out.println(x);
		}
		
		System.out.println("");
		System.out.println("Encoding the File...");
		ArrayList<String> encodedList = test.encodeFile(input);
		
		System.out.println("");
		System.out.println("Printing the encoded message...");
		for (String x: encodedList) {
			System.out.println(x);
		}
		
		System.out.println("");
		System.out.println("Creating a File called \"OutputTest.txt\" and wrting the encoded message to it");
		test.writeFile("OutputTest.txt", encodedList);
	}
	
	public void option4() {
		while (true ) {
			System.out.println("===============================================================================");
			System.out.println("Which challenge would you like to see?");
			System.out.println("1: First Challenge");
			System.out.println("2: Second Challenge");
			System.out.println("3: Third Challenge");
			System.out.println("4: Return to the Main Menu");
			System.out.println("Enter your option here:");
			String chosenOption4 = myScanner.nextLine();
			
			if (chosenOption4.equals("1")) {
				System.out.println("===============================================================================");
				System.out.println("Creating a Bombe for Challenge 1...");
				System.out.println("");
				Bombe myBombe1 = new Bombe();
				
				System.out.println("Simulating Enigma Machines...");
				System.out.println("Looking for a result with the word \"ANSWER\"...");
				System.out.println("");
				myBombe1.challenge1();
			} else if (chosenOption4.equals("2")) {
				System.out.println("===============================================================================");
				System.out.println("Creating a Bombe for Challenge 2...");
				System.out.println("");
				Bombe myBombe2 = new Bombe();
				
				System.out.println("Simulating Enigma Machines...");
				System.out.println("Looking for a result with the word \"ELECTRIC\"...");
				System.out.println("");
				myBombe2.challenge2();
			} else if (chosenOption4.equals("3")) {
				System.out.println("===============================================================================");
				System.out.println("Creating a Bombe for Challenge 3...");
				System.out.println("");
				Bombe myBombe3 = new Bombe();
				
				System.out.println("Simulating Enigma Machines...");
				System.out.println("Looking for a result with the word \"JAVA\"...");
				System.out.println("");
				myBombe3.challenge3();
			} else if (chosenOption4.equals("4")) {
				break;
			} else {
				System.out.println("That was not a valid option!");
				System.out.println("You have been returned to the Main Menu");
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Michael Camacho's COMP1202 Coursework 2017!");
		Main myMain = new Main();
		myMain.main();
	}
}
