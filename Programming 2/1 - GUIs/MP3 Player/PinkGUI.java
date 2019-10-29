import javax.swing.*;
import java.awt.*;

public class PinkGUI {

    public static void main(String[] args){

     	JFrame root = new JFrame("Simple GUI");
        root.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        root.setSize(900,900);
        root.setLocationRelativeTo(null);

        Container content = root.getContentPane();

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.pink);
        content.add(buttons);
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");

        b1.setPreferredSize(new Dimension(300, 300));
        b2.setPreferredSize(new Dimension(300, 300));
        b3.setPreferredSize(new Dimension(300, 300));
        b4.setPreferredSize(new Dimension(300, 300));

        b1.setFont(new Font("Arial", Font.PLAIN, 25));
        b2.setFont(new Font("Arial", Font.PLAIN, 25));
        b3.setFont(new Font("Arial", Font.PLAIN, 25));
        b4.setFont(new Font("Arial", Font.PLAIN, 25));

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 0;
        buttons.add(b1,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 2;
        cst.gridy = 0;
        buttons.add(b2,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 2;
        buttons.add(b3,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 2;
        cst.gridy = 2;
        buttons.add(b4,cst);

        root.setVisible(true);
          
    }

}