package common;

import java.util.*;
import java.util.Map.Entry;

public class Staff extends Model implements Runnable{
	
	protected Stock business;
	protected String status;
	protected HashMap<Ingredient, Number> ingredientStock;
	protected HashMap<Dish, Number> dishStock;
	protected Random random;
	
	public Staff (Stock givenBusiness, String inputName) {
		this.name = inputName;
		this.status = "Idle";
		this.business = givenBusiness;
		random = new Random();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void run() {
		while (true) {
			ingredientStock = business.getIngredients();
			dishStock = business.getDishes();
			if (ingredientStock == null || dishStock == null) {
				break;
			}
			
			for (Entry<Dish, Number> dish : dishStock.entrySet()) {
				if (dish.getValue().intValue() < dish.getKey().getRestockThreshold().intValue()) {
					makeDish(dish.getKey());
				}
			}
		}
		
	}
	
	public void makeDish(Dish givenDish) {
		boolean shouldCook = true;
		HashMap<Ingredient, Number> requiredRecipe = (HashMap<Ingredient, Number>) givenDish.getRecipe();
		
		HashMap<Ingredient, Number> currentIngredients = business.getIngredients();
		System.out.println("checking ingredients");
		
		for (Entry<Ingredient, Number> recipeIngredient : requiredRecipe.entrySet()) {
			if (requiredRecipe.containsKey(recipeIngredient.getKey())) {
				if (requiredRecipe.get(recipeIngredient.getKey()).intValue() <= currentIngredients.get(recipeIngredient.getKey()).intValue()) {
					
					
					
				} else if (!(requiredRecipe.get(recipeIngredient.getKey()).intValue() <= currentIngredients.get(recipeIngredient.getKey()).intValue())) {
					//go get more ingredient
					shouldCook = false;
					break;
				}
			}
		}
		
		if (shouldCook) {
			this.status = "Cooking";
			try { 
				System.out.println("cooking");
				Thread.sleep(1000*(random.nextInt(40) + 20));
			} catch (Exception e) {
				
			}
			for (Entry<Ingredient, Number> ingredient : requiredRecipe.entrySet()) {
				currentIngredients.replace(ingredient.getKey(),  currentIngredients.get(ingredient.getKey()).intValue() - ingredient.getValue().intValue());
			}
			business.getDishes().replace(givenDish, business.getDishes().get(givenDish).intValue() + 1);
			this.status = "Idle";
			
			
			for (Entry<Ingredient, Number> ingredient : business.getIngredients().entrySet()) {
				System.out.println(ingredient.getValue());
			}
		}
	}
	
	public String getStatus() {
		return status;
	}
	
	

}
