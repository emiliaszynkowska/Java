package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Option1 extends Option {

    public Option1(ServerInterface server) {
        this.server = server;
        create();
    }

    @Override
    public void create() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addTitle(sushiRed, "Postcodes", "postcode.png");
        super.create();
    }

    @Override
    public void getData() {
        ArrayList<ArrayList<Object>> postcodesList = new ArrayList<ArrayList<Object>>();
		for(Postcode postcode : server.getPostcodes()) {
            Postcode destination = server.getRestaurantPostcode();
            postcode.calculateDistance(destination);
            ArrayList<Object> postcodesSubList = new ArrayList<Object>();
                postcodesSubList.add(postcode.getName());
                postcodesSubList.add(postcode.getLatLong().get("lat"));
                postcodesSubList.add(postcode.getLatLong().get("lon"));
                postcodesSubList.add(postcode.getDistance());
				postcodesList.add(postcodesSubList);
		}
		data = new String[postcodesList.size()][4];
		for(int i=0; i<postcodesList.size(); i++) {
			ArrayList<Object> currentList = postcodesList.get(i);
			for(int j=0; j<4; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
        }
        columns = new String[] {"Postcode", "Latitude", "Longitude", "Distance"};
        model = new DefaultTableModel(data, columns);
    }

    @Override
    public void addOptions() {
        super.addOptions();
        JPanel panel = new JPanel(); 
		panel.setBackground(sushiRed);
		panel.setMaximumSize(new Dimension(w,h/2));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel container = new JPanel();  
		container.setBackground(sushiRed); 
        container.setLayout(new FlowLayout(FlowLayout.LEADING)); 
        
		JLabel label = new JLabel("Postcode"); 
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
                if((result instanceof String) && (!result.equals("Enter text here")) && (!result.equals(""))) {
                    try {
                        server.addPostcode(result);
                        postcodeList.add(result);
                    } catch (Exception e) {}
                }
            };
        });
		deleteButton.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent event) {
                String selected = (String) table.getValueAt(table.getSelectedRow(),0);
                try {
                    for(Postcode postcode : server.getPostcodes()) {
                        if (postcode.getName().equals(selected))
                            server.removePostcode(postcode);
                    }
                    for (String listPostcode : postcodeList) {
                        if (listPostcode.equals(selected)) {
                            postcodeList.remove(listPostcode);
                        }
                    }
                } catch (Exception e) {}
			}
        });
        add(panel);
	}

}