package common;

import java.util.*;

public class Stock {
	
	protected HashMap<Ingredient, Number> listOfIngredients = new HashMap<Ingredient, Number>();	
	protected HashMap<Dish, Number> listOfDishes = new HashMap<Dish, Number>();
	
	public void addIngrdient(Ingredient inputIngredient) {
		listOfIngredients.put(inputIngredient, inputIngredient.getRestockThreshold());
	}
	
	public void addDish(Dish givenDish) {
		listOfDishes.put(givenDish,  givenDish.getRestockThreshold());
	}
	
	public void addIngredients() {
		
	}
	
	public void addDishes() {
		
	}
	
	public synchronized HashMap<Ingredient, Number> getIngredients() {
		return listOfIngredients;
	}
	
	public synchronized HashMap<Dish, Number> getDishes() {
		return listOfDishes;
	}
	
	public static void main(String args[]) {
		Stock newBusiness = new Stock();
		
		Thread jeff = new Thread(new Staff(newBusiness, "jeff"));
		Thread adam = new Thread(new Staff(newBusiness, "adam"));
		
		Ingredient salmon = new Ingredient("Salmon", "grams");
		Ingredient rice = new Ingredient("Rice", "grames");
		
		HashMap<Ingredient, Number> salmonRecipe = new HashMap<Ingredient, Number>();
		
		salmonRecipe.put(salmon, 1);
		salmonRecipe.put(rice, 1);
		
		Dish salmonAndRice = new Dish("Salmon and Rice", "thats cool", 5.00, salmonRecipe);
		
		newBusiness.addIngrdient(salmon);
		newBusiness.addIngrdient(rice);
		newBusiness.addDish(salmonAndRice);
		
		jeff.start();
		adam.start();
		
		newBusiness.getDishes().replace(salmonAndRice, 5);
		//newBusiness.getIngredients().replace(rice, 0);
	}
}
