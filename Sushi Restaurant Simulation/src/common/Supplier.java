package common;

import java.util.*;

public class Supplier extends Model{
	
	protected Number distanceFrom;
	protected HashSet<Ingredient> ingredientList;
	
	public Supplier(String supplierName, Number inputDistance) {
		this.name = supplierName;
		this.distanceFrom = inputDistance;
	}

	
	public Supplier(String supplierName, Number inputDistance, HashSet<Ingredient> inputList) {
		this.name = supplierName;
		this.distanceFrom = inputDistance;
		this.ingredientList = inputList;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public Number getDistance() {
		return distanceFrom;
	}
	
	public void addToIngredients(Ingredient inputIngrdient) {
		ingredientList.add(inputIngrdient);
	}
}
