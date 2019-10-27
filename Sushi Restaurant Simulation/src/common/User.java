package common;

import java.util.ArrayList;
import java.util.HashMap;

public class User extends Model{
	
	protected String password, address;
	protected Postcode postcode;
	protected HashMap<Dish, Number> userBasket;
	protected ArrayList<Order> userOrders;
	
	public User(String registerUsername, String registerPassword, String registerAddress, Postcode registerPostcode) {
		this.name = registerUsername;
		this.password = registerPassword;
		this.address = registerAddress;
		this.postcode = registerPostcode;
		
		userBasket = new HashMap<Dish, Number>();
		userOrders = new ArrayList<Order>();
	}
	
	public User(String loginUsername, String loginPassword) {
		this.name = loginUsername;
		this.password = loginPassword;
		
		userBasket = new HashMap<Dish, Number>();
		userOrders = new ArrayList<Order>();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	} 
	
	public String getAddress() {
		return address;
	}
	
	public Postcode getPostcode() {
		return postcode;
	}
	
	public HashMap<Dish, Number> getUserBasket() {
		return userBasket;
	}
	
	public ArrayList<Order> getUserOrderList() {
		return userOrders;
	}
	

}
