package comp1206.sushi.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import comp1206.sushi.common.Order;
import comp1206.sushi.common.User;

public class Order extends Model implements Serializable {

	public String name;
	public String status;
	public Number cost;
	public Map<Dish,Number> contents;
	public Boolean delivering;
	public Postcode destination;
	public User user;

	public Order() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		this.name = dtf.format(now);
		this.cost = getCost();
		this.delivering = false;
		this.setStatus("Recieved");
	}

	public void setDelivering() {
		this.setStatus("Delivering");
		this.delivering = true;
	}
	public void setDelivered() {
		this.setStatus("Delivered");
		this.delivering = false;
	}
	public Boolean isDelivering() {
		return this.delivering;
	}

	public void setDestination(Postcode postcode) {
		this.destination = postcode;
	}
	public Postcode getDestination() {
		return this.destination;
	}
	public Number getDistance() { return this.getDestination().getDistance(); }

	@Override
	public String getName() {
		return this.name;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public String getStatus() {
		return status;
	}

	public Number getCost() {
		Double total = 0.0;
		if(contents == null) { return total; }
		for(Map.Entry e : contents.entrySet()) {
			String cost = e.getValue().toString();
			total += Double.parseDouble(cost);
		}
		return total;
	}

	public Map<Dish,Number> getContents() { return contents; }

	public void setStatus(String status) {
		notifyUpdate("status", this.status, status);
		this.status = status;
	}

	public void setCost(Number cost) {
		notifyUpdate("cost", this.cost, cost);
		this.cost = cost;

}
	public void setContents(Map<Dish,Number> contents) {
		notifyUpdate("contents", this.contents, contents);
		this.contents = contents;
	}

}
