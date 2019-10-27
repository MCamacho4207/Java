import java.util.ArrayList;
import java.util.HashMap;

import client.*;
import common.Dish;
import common.Ingredient;
import common.Postcode;
import common.User;

public class ClientApplication {
	
	Client properties;
	
	public void initialise() {
		properties = new Client();
		
		properties.addUser(new User("Admin", "password"));
		properties.addPostcode(new Postcode("SO17 1AW"));
		properties.addPostcode(new Postcode("SO16 3QX"));
		
		HashMap<Ingredient, Number> salmonRecipe = new HashMap<Ingredient, Number>();
		Ingredient salmon = new Ingredient("Salmon", "grams");
		salmonRecipe.put(salmon, 1);
		
		properties.addDish(new Dish("Salmon and Rice", "its salmon and rice, wow", 5.00, salmonRecipe));
		
	}
	
	public void launchGUI(Client clientInterface) {
		ClientWindow customerWindow = new ClientWindow(clientInterface);
	}
	
	public void start() {
		initialise();
		launchGUI(properties);
	}
	
	public static void main(String args[]) {
		ClientApplication runningApplication = new ClientApplication();
		runningApplication.start();
	}

}
