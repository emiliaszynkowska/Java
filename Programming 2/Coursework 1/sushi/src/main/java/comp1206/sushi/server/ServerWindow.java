package comp1206.sushi.server;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import comp1206.sushi.common.*;
import comp1206.sushi.server.ServerInterface.UnableToDeleteException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.lang.model.util.ElementScanner6;
import java.io.IOException;
import java.io.File;

/**
 * Create the ServerWindow class
 * This provides the Sushi Server interface
 */
public class ServerWindow extends JFrame implements UpdateListener {

	/**
	 * Connect the ServerWindow to the server so it can retrieve data
	 * Create a JTabbedPane, this is the main menu and contains options 1-9
	 * Get the current screen dimensions
	 * Set the main font sushiFont - this is used across the GUI
	 */
	private ServerInterface server;
	private JTabbedPane menu;
	ArrayList<Option> optionsList;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int w = (int)screenSize.getWidth(); int h = (int)screenSize.getHeight();
	public static final Font sushiFont = new Font("Segoe UI", Font.PLAIN, 50);
	
	/**
	 * Create a new server window
	 * @param server instance of the server to interact with
	 */
	 
	public ServerWindow(ServerInterface server) {

		super("SPreDS");
		this.server = server;
		this.setTitle(server.getRestaurantName() + " Server");
		server.addUpdateListener(this);

		/**
		 * Set a default size for the window
		 * Setting the location to null will put the window in the centre of the screen
		 * The window is resizeable and its components will adapt to a change in size
		 */
		setSize(w*2/3,h*7/8);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		changeFont(this, sushiFont);
		setVisible(true);

		/**
		 * Get the content pane and create the main JPanel
		 */
		Container container = this.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		JPanel header = new JPanel(); header.setBackground(Color.white);
		JPanel subheader = new JPanel(); subheader.setBackground(Color.white);
		header.setMaximumSize(new Dimension(w,200));
		header.setLayout(new FlowLayout(FlowLayout.LEADING));
		subheader.setLayout(new BoxLayout(subheader, BoxLayout.PAGE_AXIS));

		/**
		 * Create the header - this contains the SPreDS logo, title, and menu addButton
		 * The Image/BufferedImage class is used to get images from files
		 * These can be inserted into ImageIcons and JLabels
		 * This is efficient as JLabels can be positioned using Layout Manager commands
		 */
		try {
			BufferedImage logoImage = ImageIO.read(this.getClass().getResource("logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
			BufferedImage titleImage = ImageIO.read(this.getClass().getResource("spreds.png"));
			JLabel titleLabel = new JLabel(new ImageIcon(titleImage));
			JLabel descriptionLabel = new JLabel("Sushi Preparation and Delivery System");
			header.add(logoLabel); 
			subheader.add(titleLabel); 
			subheader.add(descriptionLabel); 
			header.add(subheader);
			container.add(header);
		} catch (IOException e) {}

		/**
		 * Create the main Menu
		 */
		optionsList = new ArrayList<Option>();
		menu = new JTabbedPane();
		Option option1 = new Option1(server); menu.addTab("Postcodes", option1); optionsList.add(option1);
		Option option2 = new Option2(server); menu.addTab("Drones", option2); optionsList.add(option2);
		Option option3 = new Option3(server); menu.addTab("Staff", option3); optionsList.add(option3);
		Option option4 = new Option4(server); menu.addTab("Suppliers", option4); optionsList.add(option4);
		Option option5 = new Option5(server); menu.addTab("Ingredients", option5); optionsList.add(option5);
		Option option6 = new Option6(server); menu.addTab("Dishes", option6); optionsList.add(option6);
		Option option7 = new Option7(server); menu.addTab("Orders", option7); optionsList.add(option7);
		Option option8 = new Option8(server); menu.addTab("Users", option8); optionsList.add(option8);
		Option option9 = new Option9(server); menu.addTab("Map", option9); optionsList.add(option9);
		container.add(menu);

		startTimer();
	}

	/**
	 * Start the timer which updates the user interface based on the given interval to update all panels
	 */
	public void startTimer() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); 
		scheduler.scheduleAtFixedRate(() -> refreshAll(), 0, 5, TimeUnit.SECONDS);
	}
	
	/**
	 * Refresh all parts of the server application based on receiving new data, calling the server afresh
	 */
	public void refreshAll() {
		((Option) menu.getSelectedComponent()).refresh();
	}
	
	@Override
	/**
	 * Respond to the model being updated by refreshing all data displays
	 */
	public void updated(UpdateEvent updateEvent) {
		refreshAll();
	}

	public static void changeFont (Component component, Font font) {
		component.setFont(sushiFont);
		if(component instanceof Container) {
			for (Component child : ((Container)component).getComponents()) {
				changeFont(child,sushiFont);
			}
		}
	}

}
