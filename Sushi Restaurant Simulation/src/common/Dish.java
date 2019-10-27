package common;

import java.util.*;

public class Dish extends Model{
	
	protected String description;
	protected Number price;
	protected Map<Ingredient, Number> recipe;
	protected Number restockThreshold = 10;
	protected Number restockAmount = 10;
	
	public Dish(String dishName, String dishDescription, Number dishPrice, HashMap<Ingredient, Number> dishRecipe) {
		this.name = dishName;
		this.description = dishDescription;
		this.price = dishPrice;
		this.recipe = dishRecipe;
	}
	
	public Dish(String dishName, String dishDescription, Number dishPrice, Number restockThreshold, Number restockAmount) {
		this.name = dishName;
		this.description = dishDescription;
		this.price = dishPrice;
		this.restockThreshold = restockThreshold;
		this.restockAmount = restockAmount;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void addIngredient(Ingredient newIngredient, Number amount) {
		recipe.put(newIngredient, amount);
	}
	
	public void removeIngrdient(Ingredient givenIngredient) {
		recipe.remove(givenIngredient);
	}
	
	public void setRecipe(Map<Ingredient, Number> newRecipe) {
		this.recipe = newRecipe;
	}
	
	public void setRestockValues(Number restockThreshold, Number restockAmount) {
		this.restockThreshold = restockThreshold;
		this.restockAmount = restockAmount;
	}
	
	public Number getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Map<Ingredient, Number> getRecipe() {
		return recipe;
	}
	
	public Number getRestockThreshold() {
		return restockThreshold;
	}
	
	public Number getRestockAmount() {
		return restockAmount;
	}

}
