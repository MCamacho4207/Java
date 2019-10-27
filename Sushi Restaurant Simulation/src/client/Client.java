package client;

import java.util.*;
import java.util.Map.Entry;
import common.*;

public class Client implements ClientInterface{
	
	protected ArrayList<User> userList;
	protected ArrayList<Postcode> postcodeList;
	protected ArrayList<Dish> dishList;
	protected Number orderNumber;
	
	public Client() {
		userList = new ArrayList<User>();
		postcodeList = new ArrayList<Postcode>();
		dishList = new ArrayList<Dish>();
		orderNumber = 1;
	}

	@Override
	public User register(String username, String password, String address, Postcode postcode) {
		User newUser = new User(username, password, address, postcode);
		userList.add(newUser);
		return newUser;
	}

	@Override
	public User login(String username, String password) {
		for (User u : userList) {
			if (u.getName().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public List<Postcode> getPostcodes() {
		return postcodeList;
	}

	@Override
	public List<Dish> getDishes() {
		return dishList;
	}

	@Override
	public String getDishDescription(Dish dish) {
		return dish.getDescription();
	}

	@Override
	public Number getDishPrice(Dish dish) {
		return dish.getPrice();
	}

	@Override
	public Map<Dish, Number> getBasket(User user) {
		if (userList.contains(user)) {
			return user.getUserBasket();
		}
		return new HashMap<Dish, Number>();
	}

	@Override
	public Number getBasketCost(User user) {
		double cost = 0;
		
		if (userList.contains(user)) {
			for (Entry<Dish, Number> basketDish : user.getUserBasket().entrySet()) {
				cost =+ basketDish.getKey().getPrice().doubleValue()*basketDish.getValue().doubleValue();
			}
		}
		return cost;
	}

	@Override
	public void addDishToBasket(User user, Dish dish, Number quantity) {
		if (userList.contains(user)) {
			user.getUserBasket().put(dish, quantity);
		}
	}

	@Override
	public void updateDishInBasket(User user, Dish dish, Number quantity) {
		if (userList.contains(user)) {
			user.getUserBasket().replace(dish, quantity);
		}
	}

	@Override
	public Order checkoutBasket(User user) {
		if (userList.contains(user)) {
			String orderName = "Order " + orderNumber;
			Order newOrder = new Order(user.getUserBasket(), orderName);
			user.getUserOrderList().add(newOrder);
			orderNumber = orderNumber.intValue() + 1;
			return newOrder;
		}
		return new Order();
	}

	@Override
	public void clearBasket(User user) {
		if (userList.contains(user)) {
			user.getUserBasket().clear();
		}
	}

	@Override
	public List<Order> getOrders(User user) {
		if (userList.contains(user)) {
			return user.getUserOrderList();
		}
		return new ArrayList<Order>();
	}

	@Override
	public boolean isOrderComplete(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getOrderStatus(Order order) {
		return order.getStatus();
	}

	@Override
	public Number getOrderCost(Order order) {
		return order.getOrderPrice();
	}

	@Override
	public void cancelOrder(Order order) {
		for (User u : userList) {
			if (u.getUserOrderList().contains(order)) {
				u.getUserOrderList().remove(order);
			}
		}
	}
	
	public void addUser(User user) {
		userList.add(user);
	}
	
	public void addPostcode(Postcode postcode) {
		postcodeList.add(postcode);
	}
	
	public void addDish(Dish dish) {
		dishList.add(dish);
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
