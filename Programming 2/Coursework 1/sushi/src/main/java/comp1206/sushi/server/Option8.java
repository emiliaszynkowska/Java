package comp1206.sushi.server;
import comp1206.sushi.common.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Option8 extends Option {

	public Option8(ServerInterface server) {
        this.server = server;
        create();
    }

    @Override
    public void create() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addTitle(sushiMagenta, "Users", "person.png");
        super.create();
    }

    @Override
    public void getData() {
        ArrayList<ArrayList<Object>> usersList = new ArrayList<ArrayList<Object>>();
		for(User user : server.getUsers()) {
			ArrayList<Object> usersSubList = new ArrayList<Object>();
				usersSubList.add(user.getName());
				usersSubList.add(user.getPostcode().toString());
		}
		data = new String[usersList.size()][2];
		for(int i=0; i<usersList.size(); i++) {
			ArrayList<Object> currentList = usersList.get(i);
			for(int j=0; j<2; j++) {
				String currentString = currentList.get(j).toString();
				data[i][j] = currentString;
			}
		}
		columns = new String[] {"User", "Postcode"};
        model = new DefaultTableModel(data, columns);
    }
    
}