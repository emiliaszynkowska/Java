package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Option6 extends Option {

    public Option6(ServerInterface server) {
        this.server = server;
        create();
    }

    @Override
    public void create() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addTitle(sushiCyan, "Dishes", "dishes.png");
        super.create();
    }
    @Override
    public void getData() {
        ArrayList<ArrayList<Object>> dishesList = new ArrayList<ArrayList<Object>>();
		for(Dish dish : server.getDishes()) {
			ArrayList<Object> dishesSubList = new ArrayList<Object>();
			dishesSubList.add(dish.getName());
			dishesSubList.add(dish.getDescription());
			dishesSubList.add(dish.getPrice().toString());
			dishesSubList.add(dish.getRestockAmount().toString());
			dishesSubList.add(dish.getRestockThreshold().toString());
			dishesList.add(dishesSubList);
		}
		data = new String[dishesList.size()][5];
		for(int i=0; i<dishesList.size(); i++) {
			ArrayList<Object> currentList = dishesList.get(i);
			for(int j=0; j<5; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
		columns = new String[] {"Dish", "Description", "Price", "Amount", "Threshold"};
        model = new DefaultTableModel(data, columns);
        }
    }

    @Override
    public void addOptions() {
        super.addOptions();
		JPanel panel = new JPanel(); 
        panel.setBackground(sushiCyan);
        panel.setMaximumSize(new Dimension(w,h/2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        ArrayList<JTextField> fieldList = new ArrayList<JTextField>();
        panel.add(createContainer("Dish", sushiCyan, fieldList));
        panel.add(createContainer("Description", sushiCyan, fieldList));
        panel.add(createContainer("Price", sushiCyan, fieldList));
        panel.add(createContainer("Restock Threshold", sushiCyan, fieldList));

        JPanel container = new JPanel();  
        container.setBackground(sushiCyan); 
        container.setLayout(new FlowLayout(FlowLayout.LEADING)); 
        
        JLabel label = new JLabel("Restock Amount"); 
		JTextField field = new JTextField("Enter text here"); 
		fieldList.add(field);
        field.addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {field.setText("");}});
        container.add(label); label.setPreferredSize(new Dimension(400,70));
        container.add(field); field.setPreferredSize(new Dimension(400,70));
        
        JButton addButton = new JButton(); addButton.setText("Add"); 
		JButton deleteButton = new JButton(); deleteButton.setText("Delete"); 
        container.add(addButton); container.add(deleteButton);
        panel.add(container);

        ArrayList<String> results = new ArrayList<String>();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for(JTextField field : fieldList) {
                    results.add(field.getText());
                    field.setText("");
                }
                if(!((results.contains("")) || (results.contains("Enter text here")))) {
                    try {
                        server.addDish(results.get(0), results.get(1), Double.parseDouble(results.get(2)), Integer.parseInt(results.get(3)), Integer.parseInt(results.get(4)));
                    } catch (Exception e) {}
                }  
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selected = (String) table.getValueAt(table.getSelectedRow(),0);
                try {
                    for(Dish dish : server.getDishes()) {
                        if(dish.getName().equals(selected)) 
                            server.removeDish(dish);
                    }
                } catch (Exception e) {}
            }
        });
		add(panel);
	}
    
}