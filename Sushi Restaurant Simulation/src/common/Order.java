package common;

import java.util.*;
import java.util.Map.Entry;

public class Order extends Model{
	
	protected HashMap<Dish, Number> orderItems;
	protected Number price;
	protected String status;
	
	public Order(HashMap<Dish, Number> givenBasket) {
		this.name = "New Order";
		this.orderItems = givenBasket;
		this.status = "Preparing";
		
		for (Entry<Dish, Number> basketDish : givenBasket.entrySet()) {
			price =+ basketDish.getKey().getPrice().doubleValue()*basketDish.getValue().doubleValue();
		}
	}
	
	public Order(HashMap<Dish, Number> givenBasket, String orderName) {
		this.name = orderName;
		this.orderItems = givenBasket;
		this.status = "Preparing";
		
		for (Entry<Dish, Number> basketDish : givenBasket.entrySet()) {
			price =+ basketDish.getKey().getPrice().doubleValue()*basketDish.getValue().doubleValue();
		}
	}
	
	public Order() {
		this.price = 0;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Number getOrderPrice() {
		return price;
	}
}
