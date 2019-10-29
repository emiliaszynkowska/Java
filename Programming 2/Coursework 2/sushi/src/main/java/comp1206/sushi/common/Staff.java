package comp1206.sushi.common;

import comp1206.sushi.server.Server;

import java.io.Serializable;
import java.util.ConcurrentModificationException;

public class Staff extends Model implements Runnable, Serializable {

	private String name;
	private String status;
	private Number fatigue;
	public Thread staffThread;
	private Stock stock;

	public Staff(String name, Stock stock) {
		this.setName(name);
		this.setFatigue(0);
		this.setStatus("Idle");
		this.setStock(stock);
		staffThread = new Thread(this);
		staffThread.start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Number getFatigue() {
		return fatigue;
	}

	public void setFatigue(Number fatigue) {
		this.fatigue = fatigue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		notifyUpdate("status",this.status,status);
		this.status = status;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
    public void run() {
		while(true) {
			try {
				stock.checkDishes(this);
			} catch (ConcurrentModificationException c) {
				continue;
			}
		}
    }

    public void checkBattery() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException i) {}
		setFatigue((Integer)getFatigue() + 1);
		if((Integer)getFatigue() == 10) {
			setStatus("Resting");
			try {
				while ((Integer)getFatigue() != 0) {
					Thread.sleep(1000);
					setFatigue((Integer)getFatigue() - 1);
				}
				setStatus("Idle");
			} catch (InterruptedException i) {}
		}
	}

}
