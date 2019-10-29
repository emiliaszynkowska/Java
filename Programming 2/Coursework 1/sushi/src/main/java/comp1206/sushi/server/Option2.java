package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Option2 extends Option {

    public Option2(ServerInterface server) {
        this.server = server;
        create();
    }

    @Override
    public void create() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addTitle(sushiOrange, "Drones", "drones.png");
        super.create();
    }

    @Override
    public void getData() {
        ArrayList<ArrayList<Object>> dronesList = new ArrayList<ArrayList<Object>>();
		for(Drone drone : server.getDrones()) {
			ArrayList<Object> dronesSubList = new ArrayList<Object>();
                dronesSubList.add(drone.getSpeed().toString());
				if(drone.getSource() != null)
					dronesSubList.add(drone.getSource().toString());
				else		
					dronesSubList.add("");
				if(drone.getDestination() != null)
					dronesSubList.add(drone.getDestination().toString());
				else		
					dronesSubList.add("");
				if(drone.getStatus() != null)
					dronesSubList.add(drone.getStatus().toString());
				else		
					dronesSubList.add("Idle");
				if(drone.getProgress() != null)
					dronesSubList.add(drone.getProgress().toString());
				else		
					dronesSubList.add("0%");
				dronesList.add(dronesSubList);
		}
		data = new String[dronesList.size()][5];
		for(int i=0; i<dronesList.size(); i++) {
			ArrayList<Object> currentList = dronesList.get(i);
			for(int j=0; j<5; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
        }
        columns = new String[] {"Speed", "Source", "Destination", "Status", "Progress"};
		model = new DefaultTableModel(data, columns);
    }

    @Override
    public void addOptions() {
        super.addOptions();
		JPanel panel = new JPanel(); 
		panel.setBackground(sushiOrange);
		panel.setMaximumSize(new Dimension(w,h/2));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        JPanel container = new JPanel();  
        container.setBackground(sushiOrange); 
        container.setLayout(new FlowLayout(FlowLayout.LEADING)); 
        JLabel label = new JLabel("Speed"); 
        JTextField field = new JTextField("Enter text here"); 
        field.addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {field.setText("");}});
        container.add(label); label.setPreferredSize(new Dimension(400,70));
        container.add(field); field.setPreferredSize(new Dimension(400,70));
        panel.add(container); 

		JButton addButton = new JButton(); addButton.setText("Add"); 
		JButton deleteButton = new JButton(); deleteButton.setText("Delete"); 
		container.add(addButton); container.add(deleteButton);
                
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String result = field.getText();
                field.setText("");
                while(true) {
                    if(Double.parseDouble(result) >= 0) {
                        try {
                            server.addDrone(Integer.valueOf(result));
                            break;
                        } catch (Exception e) {}
                        try {
                            server.addDrone(Double.parseDouble(result));
                        } catch (Exception e) {}
                        break;
                    } else {
                        break;
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer selectedInteger = Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString());
                Double selectedDouble = Double.parseDouble(table.getValueAt(table.getSelectedRow(),0).toString());
                try {
                    for(Drone drone : server.getDrones()) {
                        if(drone.getSpeed() == selectedInteger) 
                            server.removeDrone(drone);
                        else if(drone.getSpeed() == selectedDouble) 
                            server.removeDrone(drone);
                    }
                } catch (Exception e) {}
            }
        });
		add(panel);
	}
    
}