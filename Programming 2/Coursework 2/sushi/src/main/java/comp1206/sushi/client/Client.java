package comp1206.sushi.client;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import comp1206.sushi.common.*;

public class Client implements ClientInterface {

    private static final Logger logger = LogManager.getLogger("Client");
	private Map<Dish, Number> basket = new HashMap<Dish, Number>();
	private User user;
	private static ClientComms comms;

	public Restaurant restaurant;
	public ArrayList<Postcode> postcodes;
	public ArrayList<Dish> dishes;
	public ArrayList<Order> orders;
	public ArrayList<User> users;

	public Client() {
		logger.info("Starting up client...");
		comms = new ClientComms(this);
		Thread clientCommsThread = new Thread(comms);
		clientCommsThread.start();
		notifyUpdate();
	}
	
	@Override
	public Restaurant getRestaurant() {
		this.restaurant = comms.getRestaurant();
		return restaurant;
	}
	
	@Override
	public String getRestaurantName() {
		return restaurant.getName();
	}

	@Override
	public Postcode getRestaurantPostcode() {
		return restaurant.getLocation();
	}
	
	@Override
	public User register(String username, String password, String address, Postcode postcode) {
		User user = new User(username, password, address, postcode);
		this.user = user;
		comms.addUser(user);
		notifyUpdate();
		return user;
	}

	@Override
	public User login(String username, String password) {
		this.users = comms.getUsers();
		for(User u : users) {
			if((u.getName().equals(username)) && (u.getPassword().equals(password))) {
				this.user = u;
				return u;
			}
		}
		return null;
	}

	@Override
	public List<Postcode> getPostcodes() {
		this.postcodes = comms.getPostcodes();
		return postcodes;
	}

	@Override
	public List<Dish> getDishes() {
		this.dishes = comms.getDishes();
		return dishes;
	}

	@Override
	public String getDishDescription(Dish dish) {
		List<Dish> dishes = comms.getDishes();
		for(Dish d : dishes) {
			if(dish.getName().equals(d.getName()))
				return d.getDescription();
		}
		return null;
	}

	@Override
	public Number getDishPrice(Dish dish) {
		List<Dish> dishes = comms.getDishes();
		for(Dish d : dishes) {
			if(dish.getName().equals(d.getName()))
				return d.getPrice();
		}
		return null;
	}

	@Override
	public Map<Dish, Number> getBasket(User user) {
		return basket;
	}

	@Override
	public Number getBasketCost(User user) {
		Double total = 0.0;
		for(Dish dish : basket.keySet()) {
			total += dish.getPrice().doubleValue() * basket.get(dish).intValue();
		}
		return total;
	}

	@Override
	public void addDishToBasket(User user, Dish dish, Number quantity) {
		basket.put(dish, quantity);
		notifyUpdate();
	}

	@Override
	public void updateDishInBasket(User user, Dish dish, Number quantity) {
		basket.replace(dish, quantity);
		notifyUpdate();
	}

	@Override
	public Order checkoutBasket(User user) {
		Order order = new Order();
		order.user = this.user;
		order.cost = getBasketCost(user);
		order.contents = basket;
		order.destination = this.user.getPostcode();
		comms.addOrder(order,order.getContents());
		clearBasket(user);
		return order;
	}

	@Override
	public void clearBasket(User user) {
		basket.clear();
		notifyUpdate();
	}

	@Override
	public List<Order> getOrders(User user) {
		this.orders = comms.getOrders();
		return orders;
	}

	@Override
	public boolean isOrderComplete(Order order) {
		for(Order o : comms.getOrders()) {
			if(o.getName().equals(order.getName())) {
				if(o.getStatus().equals("Complete"))
					return true;
			}
		}
		return false;
	}

	@Override
	public String getOrderStatus(Order order) {
		return order.getStatus();
	}

	@Override
	public Number getOrderCost(Order order) {
		return order.getCost();
	}

	@Override
	public void cancelOrder(Order order) {
		order.setStatus("Cancelled");
		comms.cancelOrder(order);
		clearBasket(user);
		notifyUpdate();
	}

	public User getUser() {
		return user;
	}

	@Override
	public void addUpdateListener(UpdateListener listener) {
		notifyUpdate();
	}

	@Override
	public void notifyUpdate() {}

}
