package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Option5 extends Option {

    JPanel container3;

    public Option5(ServerInterface server) {
        this.server = server;
        create();
    }

    @Override
    public void create() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addTitle(sushiGreen, "Ingredients", "ingredients.png");
        super.create();
    }

    @Override
    public void getData() {
        ArrayList<ArrayList<Object>> ingredientsList = new ArrayList<ArrayList<Object>>();
		for(Ingredient ingredient : server.getIngredients()) {
			ArrayList<Object> ingredientsSubList = new ArrayList<Object>();
				ingredientsSubList.add(ingredient.getName());
				ingredientsSubList.add(ingredient.getUnit());
				ingredientsSubList.add(ingredient.getSupplier().toString());
				ingredientsSubList.add(ingredient.getRestockAmount().toString());
				ingredientsSubList.add(ingredient.getRestockThreshold().toString());
				ingredientsList.add(ingredientsSubList);
		}
		data = new String[ingredientsList.size()][5];
		for(int i=0; i<ingredientsList.size(); i++) {
			ArrayList<Object> currentList = ingredientsList.get(i);
			for(int j=0; j<5; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
		}
		columns = new String[] {"Ingredient", "Unit", "Supplier", "Amount", "Threshold"};
        model = new DefaultTableModel(data, columns);
        
        supplierList = new ArrayList<String>();
        for(Supplier supplier : server.getSuppliers()) 
            supplierList.add(supplier.getName());
            comboxModel = new DefaultComboBoxModel<String>(supplierList.toArray(new String[supplierList.size()]));
    }

    @Override
    public void addOptions() {
        super.addOptions();
		JPanel panel = new JPanel(); 
		panel.setBackground(sushiGreen);
		panel.setMaximumSize(new Dimension(w,h/2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        ArrayList<JTextField> fieldList = new ArrayList<JTextField>();
        panel.add(createContainer("Ingredient", sushiGreen, fieldList));
        panel.add(createContainer("Unit", sushiGreen, fieldList));
        panel.add(createContainer("Restock Threshold", sushiGreen, fieldList));
        panel.add(createContainer("Restock Amount", sushiGreen, fieldList));
            
        JPanel container2 = new JPanel();  
        container2.setBackground(sushiGreen); 
        container2.setLayout(new FlowLayout(FlowLayout.LEADING)); 
        JLabel supplierListLabel = new JLabel("Supplier"); supplierListLabel.setPreferredSize(new Dimension(400,70));
        container2.add(supplierListLabel);

        container3 = new JPanel();
        container2.add(container3);
        container3.setLayout(new FlowLayout(FlowLayout.LEADING));
        combox = new JComboBox<String>(comboxModel);
        container3.add(combox); combox.setPreferredSize(new Dimension(400,70));
        panel.add(container2);

        ArrayList<String> results = new ArrayList<String>();
		JButton addButton = new JButton(); addButton.setText("Add"); 
		JButton deleteButton = new JButton(); deleteButton.setText("Delete"); 
        container2.add(addButton); container2.add(deleteButton);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for(JTextField field : fieldList) {
                    results.add(field.getText());
                    field.setText("");
                }
                if(!((results.contains("")) || (results.contains("Enter text here")))) {
                    try {
                        for(Supplier supplier : server.getSuppliers()) {
                            if(supplier.getName().equals(combox.getSelectedItem().toString())) {
                                server.addIngredient(results.get(0), results.get(1), supplier, Integer.parseInt(results.get(2)), Integer.parseInt(results.get(3)));
                            }
                        }
                    } catch (Exception e) {}
                }  
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selected = (String) table.getValueAt(table.getSelectedRow(),0);
                try {
                    for(Ingredient ingredient : server.getIngredients()) {
                        if(ingredient.getName().equals(selected)) 
                            server.removeIngredient(ingredient);
                    }
                } catch (Exception e) {}
            }
        });
		add(panel);
    }

    @Override
    public void refresh() {
        super.refresh();
        container3.remove(combox);
        combox = new JComboBox<String>(comboxModel);
        combox.setPreferredSize(new Dimension(400,70));
        combox.setFont(sushiFont);
        container3.add(combox);
    }

}
    
