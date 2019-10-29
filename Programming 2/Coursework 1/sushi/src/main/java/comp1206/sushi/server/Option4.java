package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Option4 extends Option {

    JPanel container3;

    public Option4(ServerInterface server) {
        this.server = server;
        create();
    }

    @Override
    public void create() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addTitle(sushiLime, "Suppliers", "supplier.png");
        super.create();
    }

    @Override
    public void getData() {
        ArrayList<ArrayList<Object>> suppliersList = new ArrayList<ArrayList<Object>>();
		for(Supplier supplier : server.getSuppliers()) {
			ArrayList<Object> suppliersSubList = new ArrayList<Object>();
				suppliersSubList.add(supplier.getName());
				suppliersSubList.add(supplier.getPostcode().toString());
				suppliersList.add(suppliersSubList);
		}
		data = new String[suppliersList.size()][2];
		for(int i=0; i<suppliersList.size(); i++) {
			ArrayList<Object> currentList = suppliersList.get(i);
			for(int j=0; j<2; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
		}
		columns = new String[] {"Supplier", "Postcode"};
        model = new DefaultTableModel(data, columns);
        
        postcodeList = new ArrayList<String>();
        for(Postcode postcode : server.getPostcodes()) 
            postcodeList.add(postcode.getName());
            comboxModel = new DefaultComboBoxModel<String>(postcodeList.toArray(new String[postcodeList.size()]));
    }

    @Override

    public void addOptions() {
        super.addOptions();
		JPanel panel = new JPanel(); 
		panel.setBackground(sushiLime);
		panel.setMaximumSize(new Dimension(w,h/2));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        JPanel container = new JPanel();  
        container.setBackground(sushiLime); 
        container.setLayout(new FlowLayout(FlowLayout.LEADING)); 
        JLabel label = new JLabel("Supplier"); 
        JTextField field = new JTextField("Enter text here"); 
        field.addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {field.setText("");}});
        container.add(label); label.setPreferredSize(new Dimension(400,70));
        container.add(field); field.setPreferredSize(new Dimension(400,70));
        panel.add(container); 

        JPanel container2 = new JPanel();  
        container2.setBackground(sushiLime); 
        container2.setLayout(new FlowLayout(FlowLayout.LEADING)); 
        JLabel postcodeListLabel = new JLabel("Postcode"); postcodeListLabel.setPreferredSize(new Dimension(400,70));
        container2.add(postcodeListLabel);
        
        container3 = new JPanel();
        container2.add(container3);
        container3.setLayout(new FlowLayout(FlowLayout.LEADING));
        combox = new JComboBox<String>(comboxModel);
        container3.add(combox); combox.setPreferredSize(new Dimension(400,70));
        panel.add(container2);

        JButton addButton = new JButton(); addButton.setText("Add"); 
        JButton deleteButton = new JButton(); deleteButton.setText("Delete"); 
        container2.add(addButton); container2.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                    String result = field.getText();
                    field.setText("");
                    String result2 = combox.getSelectedItem().toString();
                    if((result instanceof String) && (!result.equals("Enter text here")) && (!result.equals(""))) {
                        try {
                            for(Postcode postcode : server.getPostcodes()) {
                                if(postcode.getName().equals(result2)) {
                                    server.addSupplier(result, postcode);
                                    supplierList.add(result);
                                }
                            }
                        } catch (Exception e) {}
                    }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selected = (String) table.getValueAt(table.getSelectedRow(),0).toString();
                try {
                    for(Supplier supplier : server.getSuppliers()) {
                        if(supplier.getName().equals(selected)) {
                            server.removeSupplier(supplier);
                            supplierList.remove(selected);
                        }
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