package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Option7 extends Option {

	public Option7(ServerInterface server) {
		this.server = server;
        create();
    }
	
    @Override
    public void create() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addTitle(sushiBlue, "Orders", "orders.png");
        super.create();
    }

    @Override
    public void getData() {
        ArrayList<ArrayList<Object>> ordersList = new ArrayList<ArrayList<Object>>();
		for(Order order : server.getOrders()) {
			ArrayList<Object> ordersSubList = new ArrayList<Object>();
			ordersSubList.add(order.getName());
			if(order.getStatus() != null)
				ordersSubList.add(order.getStatus());
			else
				ordersSubList.add("None");
			ordersList.add(ordersSubList);
		}
		data = new String[ordersList.size()][2];
		for(int i=0; i<ordersList.size(); i++) {
			ArrayList<Object> currentList = ordersList.get(i);
			for(int j=0; j<2; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
		}
		columns = new String[] {"Date", "Status"};
		model = new DefaultTableModel(data, columns);
    }
    
}