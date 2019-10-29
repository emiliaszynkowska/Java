package comp1206.sushi.server;

import java.util.*;
import comp1206.sushi.common.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server implements ServerInterface {

    private static final Logger logger = LogManager.getLogger("Server");
    private ServerComms serverComms;
    private DataPersistence dataPersistence;

	public Restaurant restaurant;
	public ArrayList<Dish> dishes = new ArrayList<Dish>();
	public ArrayList<Drone> drones = new ArrayList<Drone>();
	public ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	public ArrayList<Order> orders = new ArrayList<Order>();
	public ArrayList<Staff> staff = new ArrayList<Staff>();
	public ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	public ArrayList<User> users = new ArrayList<User>();
	public ArrayList<Postcode> postcodes = new ArrayList<Postcode>();
	private ArrayList<UpdateListener> listeners = new ArrayList<UpdateListener>();
	private Stock stock;

	public Server() {
		stock = new Stock();
		stock.setServer(this);
		logger.info("Starting up server...");
		dataPersistence = new DataPersistence(this);
		serverComms = new ServerComms(this);
		Thread commsThread = new Thread(serverComms);
		commsThread.start();
	}

	@Override
	public List<Dish> getDishes() {
		return this.dishes;
	}

	@Override
	public Dish addDish(String name, String description, Number price, Number restockThreshold, Number restockAmount) {
		Dish newDish = new Dish(name,description,price,restockThreshold,restockAmount);
		this.dishes.add(newDish);
		this.notifyUpdate();
		return newDish;
	}

	@Override
	public void removeDish(Dish dish) {
		this.dishes.remove(dish);
		this.notifyUpdate();
	}

	@Override
	public Map<Dish, Number> getDishStockLevels() {
		List<Dish> dishes = getDishes();
		HashMap<Dish, Number> levels = new HashMap<Dish, Number>();
		for(Dish dish : dishes) {
			levels.put(dish,dish.getStock());
		}
		return levels;
	}

	@Override
	public void setRestockingIngredientsEnabled(boolean enabled) {}

	@Override
	public void setRestockingDishesEnabled(boolean enabled) {}

	@Override
	public void setStock(Dish dish, Number stock) {
		dish.setStock(dish, stock);
	}

	@Override
	public void setStock(Ingredient ingredient, Number stock) {
		ingredient.setStock(ingredient, stock);
	}

	@Override
	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	@Override
	public Ingredient addIngredient(String name, String unit, Supplier supplier, Number restockThreshold, Number restockAmount, Number weight) {
		Ingredient mockIngredient = new Ingredient(name,unit,supplier,restockThreshold,restockAmount,weight);
		this.ingredients.add(mockIngredient);
		this.notifyUpdate();
		return mockIngredient;
	}

	@Override
	public void removeIngredient(Ingredient ingredient) throws UnableToDeleteException {
		int index = this.ingredients.indexOf(ingredient);
		for(Dish d : getDishes()) {
			for(Ingredient i : d.getRecipe().keySet()) {
				if(i.getName().equals(ingredient.getName())) {
					throw new UnableToDeleteException("Ingredient part of dish");
				}
			}
		}
		this.ingredients.remove(index);
		this.notifyUpdate();
	}

	@Override
	public List<Supplier> getSuppliers() {
		return this.suppliers;
	}

	@Override
	public Supplier addSupplier(String name, Postcode postcode) {
		Supplier mock = new Supplier(name,postcode);
		mock.getPostcode().getLatLong();
		mock.getPostcode().calculateDistance(getRestaurantPostcode());
		this.suppliers.add(mock);
		notifyUpdate();
		return mock;
	}


	@Override
	public void removeSupplier(Supplier supplier) {
		int index = this.suppliers.indexOf(supplier);
		this.suppliers.remove(index);
		this.notifyUpdate();
	}

	@Override
	public List<Drone> getDrones() {
		return this.drones;
	}

	@Override
	public Drone addDrone(Number speed) {
		Drone mock = new Drone(speed, stock);
		mock.setSource(getRestaurantPostcode());
		mock.setDestination(null);
		this.drones.add(mock);
		this.notifyUpdate();
		return mock;
	}

	@Override
	public void removeDrone(Drone drone) {
		drone.droneThread.stop();
		int index = this.drones.indexOf(drone);
		this.drones.remove(index);
		this.notifyUpdate();
	}

	@Override
	public List<Staff> getStaff() {
		return this.staff;
	}

	@Override
	public Staff addStaff(String name) {
		Staff mock = new Staff(name, stock);
		staff.add(mock);
		notifyUpdate();
		return mock;
	}

	@Override
	public void removeStaff(Staff staff) {
		staff.staffThread.stop();
		this.staff.remove(staff);
		this.notifyUpdate();
	}

	@Override
	public List<Order> getOrders() {
		return this.orders;
	}

	public Order addOrder(Map<Dish, Number> contents) {
		Order mock = new Order();
		mock.user = new User("Admin","password","Restaurant",getRestaurantPostcode());
		mock.setContents(contents);
		mock.getDestination().getLatLong();
		mock.getDestination().calculateDistance(getRestaurantPostcode());
		orders.add(mock);
		notifyUpdate();
		return mock;
	}
	public Order addOrder(Order order) {
		orders.add(order);
		notifyUpdate();
		return order;
	}

	@Override
	public void removeOrder(Order order) {
		int index = this.orders.indexOf(order);
		this.orders.remove(index);
		this.notifyUpdate();
	}

	@Override
	public Number getOrderCost(Order order) {
		return order.getCost();
	}

	@Override
	public Map<Ingredient, Number> getIngredientStockLevels() {
		List<Ingredient> ingredients = getIngredients();
		HashMap<Ingredient, Number> levels = new HashMap<Ingredient, Number>();
		for(Ingredient ingredient : ingredients) {
			levels.put(ingredient, ingredient.getStock());
		}
		return levels;
	}

	@Override
	public Number getSupplierDistance(Supplier supplier) {
		return supplier.getDistance();
	}

	@Override
	public Number getDroneSpeed(Drone drone) {
		return drone.getSpeed();
	}

	@Override
	public Number getOrderDistance(Order order) {
		order.getDestination().calculateDistance(getRestaurantPostcode());
		return order.getDistance();
	}

	@Override
	public void addIngredientToDish(Dish dish, Ingredient ingredient, Number quantity) {
		if(quantity == Integer.valueOf(0)) {
			removeIngredientFromDish(dish,ingredient);
		} else {
			dish.getRecipe().put(ingredient,quantity);
		}
		this.notifyUpdate();
	}

	@Override
	public void removeIngredientFromDish(Dish dish, Ingredient ingredient) {
		dish.getRecipe().remove(ingredient);
		this.notifyUpdate();
	}

	@Override
	public Map<Ingredient, Number> getRecipe(Dish dish) {
		return dish.getRecipe();
	}

	@Override
	public List<Postcode> getPostcodes() {
		return this.postcodes;
	}

	@Override
	public Postcode addPostcode(String code) {
		Postcode mock = new Postcode(code);
		mock.getLatLong();
		mock.calculateDistance(getRestaurantPostcode());
		this.postcodes.add(mock);
		this.notifyUpdate();
		return mock;
	}

	@Override
	public void removePostcode(Postcode postcode) throws UnableToDeleteException {
		for(Supplier s : getSuppliers()) {
			if(s.getPostcode().getName().equals(postcode.getName())) {
				throw new UnableToDeleteException("Postcode used in Supplier");
			}
		}
		this.postcodes.remove(postcode);
		this.notifyUpdate();
	}

	@Override
	public List<User> getUsers() {
		return this.users;
	}

	@Override
	public User addUser(String username, String password, String location, Postcode postcode) {
		User mock = new User(username, password, location, postcode);
		mock.getPostcode().getLatLong();
		mock.getPostcode().calculateDistance(getRestaurantPostcode());
		users.add(mock);
		notifyUpdate();
		return mock;
	}
	public User addUser(User user) {
		users.add(user);
		notifyUpdate();
		return user;
	}

	@Override
	public void removeUser(User user) {
		this.users.remove(user);
		this.notifyUpdate();
	}

	@Override
	public void loadConfiguration(String filename) {
		Configuration configuration = new Configuration(this, filename);
		configuration.getRestaurant();
		restaurant = new Restaurant(configuration.restaurantName, configuration.restaurantPostcode);
		configuration.read();
		System.out.println("Loaded configuration: " + filename);
	}

	@Override
	public void setRecipe(Dish dish, Map<Ingredient, Number> recipe) {
		dish.setRecipe(recipe);
		notifyUpdate();
	}

	@Override
	public boolean isOrderComplete(Order order) {
		if(order.getStatus().equals("Delivered") || order.getStatus().equals("Cancelled"))
			return true;
		else
			return false;
	}

	@Override
	public String getOrderStatus(Order order) {
		return getOrderStatus(order);
	}

	@Override
	public String getDroneStatus(Drone drone) {
		Random rand = new Random();
		if(rand.nextBoolean()) {
			return "Idle";
		} else {
			return "Flying";
		}
	}

	@Override
	public String getStaffStatus(Staff staff) {
		Random rand = new Random();
		if(rand.nextBoolean()) {
			return "Idle";
		} else {
			return "Working";
		}
	}

	@Override
	public void setRestockLevels(Dish dish, Number restockThreshold, Number restockAmount) {
		dish.setRestockThreshold(restockThreshold);
		dish.setRestockAmount(restockAmount);
		this.notifyUpdate();
	}

	@Override
	public void setRestockLevels(Ingredient ingredient, Number restockThreshold, Number restockAmount) {
		ingredient.setRestockThreshold(restockThreshold);
		ingredient.setRestockAmount(restockAmount);
		this.notifyUpdate();
	}

	@Override
	public Number getRestockThreshold(Dish dish) {
		return dish.getRestockThreshold();
	}

	@Override
	public Number getRestockAmount(Dish dish) {
		return dish.getRestockAmount();
	}

	@Override
	public Number getRestockThreshold(Ingredient ingredient) {
		return ingredient.getRestockThreshold();
	}

	@Override
	public Number getRestockAmount(Ingredient ingredient) {
		return ingredient.getRestockAmount();
	}

	@Override
	public void addUpdateListener(UpdateListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void notifyUpdate() {
		this.listeners.forEach(listener -> listener.updated(new UpdateEvent()));
	}

	@Override
	public Postcode getDroneSource(Drone drone) {
		return drone.getSource();
	}

	@Override
	public Postcode getDroneDestination(Drone drone) {
		return drone.getDestination();
	}

	@Override
	public Number getDroneProgress(Drone drone) {
		return drone.getProgress();
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
	public Restaurant getRestaurant() {
		return restaurant;
	}

}
