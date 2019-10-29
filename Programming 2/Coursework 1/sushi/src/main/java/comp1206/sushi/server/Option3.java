package comp1206.sushi.server;
import comp1206.sushi.common.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Option3 extends Option {

	public Option3(ServerInterface server) {
		this.server = server;
		create();
    }

    @Override
    public void create() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addTitle(sushiYellow, "Staff", "staff.png");
        super.create();
    }

    @Override
    public void getData() {
		ArrayList<ArrayList<Object>> staffList = new ArrayList<ArrayList<Object>>();
		for(Staff staff : server.getStaff()) {
			ArrayList<Object> staffSubList = new ArrayList<Object>();
				staffSubList.add(staff.getName());
				if(staff.getStatus() != null)
					staffSubList.add(staff.getStatus());
				else 
					staffSubList.add("Idle");
				staffList.add(staffSubList);
		}
		data = new String[staffList.size()][2];
		for(int i=0; i<staffList.size(); i++) {
			ArrayList<Object> currentList = staffList.get(i);
			for(int j=0; j<2; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
		}
		columns = new String[] {"Staff", "Status"};
        model = new DefaultTableModel(data, columns);
	}

	@Override
	public void addOptions() {
		super.addOptions();
		JPanel panel = new JPanel();
		panel.setBackground(sushiYellow);
		panel.setMaximumSize(new Dimension(w,h/2));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel container1 = new JPanel();
		container1.setBackground(sushiYellow);
		container1.setLayout(new FlowLayout(FlowLayout.LEADING));
		panel.add(container1);

		JLabel label1 = new JLabel("Staff");
		JTextField field1 = new JTextField("Enter text here");
		field1.addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {field1.setText("");}});
		container1.add(label1); label1.setPreferredSize(new Dimension(400,70));
		container1.add(field1); field1.setPreferredSize(new Dimension(400,70));

		JButton addButton = new JButton(); addButton.setText("Add");
		JButton deleteButton = new JButton(); deleteButton.setText("Delete");
		container1.add(addButton); container1.add(deleteButton);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String result1 = field1.getText();
				field1.setText("");
				if((result1 instanceof String) && (!result1.equals("")) && (!result1.equals("Enter text here"))) {
					try {
						server.addStaff(result1);
					} catch (Exception e) {}
				}
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String selected = (String) table.getValueAt(table.getSelectedRow(),0);
				try {
					for(Staff staff : server.getStaff()) {
						if(staff.getName().equals(selected))
							server.removeStaff(staff);
					}
				} catch (Exception e) {}
			}
		});
		add(panel);
	}

}