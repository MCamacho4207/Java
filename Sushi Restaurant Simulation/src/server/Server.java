package server;

import java.io.FileNotFoundException;
import java.util.*;
import common.*;

public class Server implements ServerInterface{
	
	private ArrayList<Order> orderList;
	private ArrayList<Dish> dishList;
	private ArrayList<Ingredient> ingredientList;
	private ArrayList<Supplier> supplierList;
	private ArrayList<Staff> staffList;
	private ArrayList<Drone> droneList;
	private ArrayList<User> userList;
	private ArrayList<Postcode> postcodeList;
	Stock runningBusiness;
	
	public Server() {
		orderList = new ArrayList<Order>();
		dishList = new ArrayList<Dish>();
		ingredientList = new ArrayList<Ingredient>();
		supplierList = new ArrayList<Supplier>();
		staffList = new ArrayList<Staff>();
		droneList = new ArrayList<Drone>();
		userList = new ArrayList<User>();
		postcodeList = new ArrayList<Postcode>();
		runningBusiness = new Stock();
	}

	@Override
	public void loadConfiguration(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRestockingIngredientsEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRestockingDishesEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStock(Dish dish, Number stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStock(Ingredient ingredient, Number stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Dish> getDishes() {
		// TODO Auto-generated method stub
		return dishList;
	}

	@Override
	public Dish addDish(String name, String description, Number price, Number restockThreshold, Number restockAmount) {
		Dish newDish = new Dish(name, description, price, restockThreshold, restockAmount);
		dishList.add(newDish);
		return newDish;
	}

	@Override
	public void removeDish(Dish dish) throws UnableToDeleteException {
		if (dishList.contains(dish))  {
			dishList.remove(dish);
		} else if (!dishList.contains(dish)) {
			throw new UnableToDeleteException("Could not delete");
		}
		
	}

	@Override
	public void addIngredientToDish(Dish dish, Ingredient ingredient, Number quantity) {
		if (dishList.contains(dish)) {
			dishList.get(dishList.indexOf(dish)).addIngredient(ingredient, quantity);
		}
	}

	@Override
	public void removeIngredientFromDish(Dish dish, Ingredient ingredient) {
		if (dishList.contains(dish)) {
			dishList.get(dishList.indexOf(dish)).removeIngrdient(ingredient);
		}
	}

	@Override
	public void setRecipe(Dish dish, Map<Ingredient, Number> recipe) {
		if (dishList.contains(dish)) {
			dishList.get(dishList.indexOf(dish)).setRecipe(recipe);
		}
	}

	@Override
	public void setRestockLevels(Dish dish, Number restockThreshold, Number restockAmount) {
		if (dishList.contains(dish)) {
			dishList.get(dishList.indexOf(dish)).setRestockValues(restockThreshold, restockAmount);
		}
	}

	@Override
	public Number getRestockThreshold(Dish dish) {
		if (dishList.contains(dish)) {
			return dish.getRestockThreshold();
		}
		return 0;
	}

	@Override
	public Number getRestockAmount(Dish dish) {
		if (dishList.contains(dish)) {
			return dish.getRestockAmount();
		}
		return 0;
	}

	@Override
	public Map<Ingredient, Number> getRecipe(Dish dish) {
		if (dishList.contains(dish)) {
			return dish.getRecipe();
		}
		return null;
	}

	@Override
	public Map<Dish, Number> getDishStockLevels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> getIngredients() {
		// TODO Auto-generated method stub
		return ingredientList;
	}

	@Override
	public Ingredient addIngredient(String name, String unit, Supplier supplier, Number restockThreshold,
			Number restockAmount) {
		Ingredient newIngredient = new Ingredient(name, unit, supplier, restockThreshold, restockAmount);
		ingredientList.add(newIngredient);
		return newIngredient;
	}

	@Override
	public void removeIngredient(Ingredient ingredient) throws UnableToDeleteException {
		if (ingredientList.contains(ingredient)) {
			ingredientList.remove(ingredient);
		} else if (!ingredientList.contains(ingredient)) {
			throw new UnableToDeleteException("Could not delete");
		}
	}

	@Override
	public void setRestockLevels(Ingredient ingredient, Number restockThreshold, Number restockAmount) {
		if (ingredientList.contains(ingredient)) {
			ingredientList.get(ingredientList.indexOf(ingredient)).setRestockValues(restockThreshold, restockAmount);
		}	
	}

	@Override
	public Number getRestockThreshold(Ingredient ingredient) {
		if (ingredientList.contains(ingredient)) {
			return ingredient.getRestockThreshold();
		}
		return 0;
	}

	@Override
	public Number getRestockAmount(Ingredient ingredient) {
		if (ingredientList.contains(ingredient)) {
			return ingredient.getRestockAmount();
		}
		return 0;
	}

	@Override
	public Map<Ingredient, Number> getIngredientStockLevels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Supplier> getSuppliers() {
		// TODO Auto-generated method stub
		return supplierList;
	}

	@Override
	public Supplier addSupplier(String name, Number distance) {
		Supplier newSupplier = new Supplier(name, distance);
		supplierList.add(newSupplier);
		return newSupplier;
	}

	@Override
	public void removeSupplier(Supplier supplier) throws UnableToDeleteException {
		if (supplierList.contains(supplier)) {
			supplierList.remove(supplier);
		}
	}

	@Override
	public Number getSupplierDistance(Supplier supplier) {
		if (supplierList.contains(supplier)) {
			return supplier.getDistance();
		}
		return 0;
	}

	@Override
	public List<Drone> getDrones() {
		return droneList;
	}

	@Override
	public Drone addDrone(Number speed) {
		Drone newDrone = new Drone(speed);
		droneList.add(newDrone);
		return newDrone;
	}

	@Override
	public void removeDrone(Drone drone) throws UnableToDeleteException {
		if (droneList.contains(drone)) {
			droneList.remove(drone);
		}
	}

	@Override
	public Number getDroneSpeed(Drone drone) {
		if (droneList.contains(drone)) {
			return drone.getSpeed();
		}
		return 0;
	}

	@Override
	public String getDroneStatus(Drone drone) {
		if (droneList.contains(drone)) {
			return drone.getStatus();
		}
		return "N/A";
	}

	@Override
	public List<Staff> getStaff() {
		return staffList;
	}

	@Override
	public Staff addStaff(String name) {
		Staff newStaff = new Staff(runningBusiness, name);
		staffList.add(newStaff);
		return newStaff;
	}

	@Override
	public void removeStaff(Staff staff) throws UnableToDeleteException {
		if (staffList.contains(staff)) {
			staffList.remove(staff);
		} else if (!staffList.contains(staff)) {
			throw new UnableToDeleteException("Could not delete");
		}	
	}

	@Override
	public String getStaffStatus(Staff staff) {
		if (staffList.contains(staff)) {
			return staff.getStatus();
		}
		return "N/A";
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orderList;
	}

	@Override
	public void removeOrder(Order order) throws UnableToDeleteException {
		if (orderList.contains(order)) {
			orderList.remove(order);
		} else if (!orderList.contains(order)) {
			throw new UnableToDeleteException("Could not delete");
		}
	}

	@Override
	public Number getOrderDistance(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOrderComplete(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getOrderStatus(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getOrderCost(Order order) {
		if (orderList.contains(order)) {
			return order.getOrderPrice();
		}
		return 0;
	}

	@Override
	public List<Postcode> getPostcodes() {
		return postcodeList;
	}

	@Override
	public void addPostcode(String code, Number distance) {
		Postcode newPostcode = new Postcode(code, distance);
		postcodeList.add(newPostcode);
	}

	@Override
	public void removePostcode(Postcode postcode) throws UnableToDeleteException {
		if (postcodeList.contains(postcode)) {
			postcodeList.remove(postcode);
		} else if (!postcodeList.contains(postcode)) {
			throw new UnableToDeleteException("Could not delete");
		}
		
	}

	@Override
	public List<User> getUsers() {
		return userList;
	}

	@Override
	public void removeUser(User user) throws UnableToDeleteException {
		if (userList.contains(user)) {
			userList.remove(user);
		} else if (!userList.contains(user)) {
			throw new UnableToDeleteException("Could not delete");
		} 
	}

	@Override
	public void addUpdateListener(UpdateListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUpdate() {
		// TODO Auto-generated method stub
		
	} 
	
	
}
