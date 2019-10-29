package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
public abstract class Option extends JPanel {

	public ServerInterface server;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int w = (int)screenSize.getWidth(); int h = (int)screenSize.getHeight();
    public static final Color sushiRed = new Color(220,50,30);
	public static final Color sushiOrange = new Color(255,125,10);
	public static final Color sushiYellow = new Color(250,230,30);
	public static final Color sushiLime = new Color(60,220,10);
	public static final Color sushiGreen = new Color(0,180,40);
	public static final Color sushiCyan = new Color(30,210,230);
	public static final Color sushiBlue = new Color(5,70,210);
	public static final Color sushiMagenta = new Color(230,20,150);
	public static final Color sushiPurple = new Color(170, 50, 255);
	public static final Font sushiFont = new Font("Segoe UI", Font.PLAIN, 50);
	
	String[][] data;
    String[] columns;
    DefaultTableModel model;
	JTable table;
	JScrollPane scroll;
	JPanel tablePanel;
	DefaultComboBoxModel<String> comboxModel;
	JComboBox<String> combox;
	ArrayList<String> postcodeList;
	ArrayList<String> supplierList;

	public void refresh() {
		getData();
		tablePanel.remove(scroll);
		table = new JTable(model); 
		table.setRowHeight(h/20);
		scroll = new JScrollPane(table);
		tablePanel.add(scroll);
		changeFont(this, sushiFont);
	}

    public void create() {
		getData();

		table = new JTable(model); 
		table.setRowHeight(h/20);
		scroll = new JScrollPane(table);
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout());
		tablePanel.add(scroll);
		add(tablePanel);

		addOptions();
        changeFont(this, sushiFont);
	}

	public abstract void getData();
	
    public void addTitle(Color color, String title, String imagename) {
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(w,150));
		panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		panel.setBackground(color);
		JLabel label = new JLabel(title);
		try {
			Image image = ImageIO.read(this.getClass().getResource(imagename)).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			JLabel imageLabel = new JLabel(new ImageIcon(image));
			panel.add(imageLabel);
		} catch (Exception e) {}
		panel.add(label);
        add(panel);
	}

	public void addOptions() {}
	
	public JPanel createContainer(String string, Color color, ArrayList<JTextField> list) {
        JPanel container = new JPanel();  
        container.setBackground(color); 
        container.setLayout(new FlowLayout(FlowLayout.LEADING)); 
        JLabel label = new JLabel(string); 
		JTextField field = new JTextField("Enter text here"); 
		list.add(field);
        field.addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {field.setText("");}});
        container.add(label); label.setPreferredSize(new Dimension(400,70));
        container.add(field); field.setPreferredSize(new Dimension(400,70));
        return container; 
	}
	
	/**
		* changeFont method
		* Changes all the fonts in the server to sushiFont
		* Source https://stackoverflow.com/questions/12730230/set-the-same-font-for-all-component-java
		*/
		public static void changeFont (Component component, Font font) {
			component.setFont(sushiFont);
			if(component instanceof Container) {
				for (Component child : ((Container)component).getComponents()) {
					changeFont(child,sushiFont);
				}
			}
		}

}