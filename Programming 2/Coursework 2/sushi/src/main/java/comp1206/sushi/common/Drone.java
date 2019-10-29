package comp1206.sushi.common;
import java.io.Serializable;
import java.util.ConcurrentModificationException;

public class Drone extends Model implements Runnable, Serializable {

	private Number speed;
	private Number progress;
	private Number capacity;
	private Number battery;
	private String status;
	private Postcode source;
	private Postcode destination;
	public Thread droneThread;
	private Stock stock;

	public Drone(Number speed, Stock stock) {
		this.setSpeed(speed);
		this.setCapacity(1);
		this.setBattery(100);
		this.setStatus("Idle");
		this.setProgress(null);
		this.setStock(stock);
		droneThread = new Thread(this);
		droneThread.start();
	}

	public Number getSpeed() {
		return speed;
	}
	
	public Number getProgress() {
		return progress;
	}
	
	public void setProgress(Number progress) {
		this.progress = progress;
	}
	
	public void setSpeed(Number speed) {
		this.speed = speed;
	}

	public void setStock(Stock stock) { this.stock = stock; }
	
	@Override
	public String getName() {
		return "Drone (" + getSpeed() + " speed)";
	}

	public Postcode getSource() {
		return source;
	}

	public void setSource(Postcode source) {
		this.source = source;
	}

	public Postcode getDestination() {
		return destination;
	}

	public void setDestination(Postcode destination) {
		this.destination = destination;
	}

	public Number getCapacity() {
		return capacity;
	}

	public void setCapacity(Number capacity) {
		this.capacity = capacity;
	}

	public Number getBattery() {
		return battery;
	}

	public void setBattery(Number battery) {
		this.battery = battery;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		notifyUpdate("status",this.status,status);
		this.status = status;
	}

	@Override
	public void run() {
		while(true) {
			try {
				stock.checkIngredients(this);
				stock.checkOrders(this);
			} catch (ConcurrentModificationException c) {
				continue;
			}
		}
	}

	public void checkBattery() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException i) {}
		setBattery((Integer)getBattery() - 10);
		if((Integer)getBattery() == 0) {
			setStatus("Charging");
			setDestination(null);
			try {
				while ((Integer)getBattery() != 100) {
					Thread.sleep(1000);
					setBattery((Integer)getBattery() + 10);
				}
			} catch (InterruptedException i) {}
		}
	}
	
}
