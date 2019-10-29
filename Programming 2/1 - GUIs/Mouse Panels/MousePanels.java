import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.util.*;

public class MousePanels {

    ArrayList<JPanel> panels = new ArrayList<JPanel>();

    public void handleDrag() {
        for(int i=0;i<panels.size();i++) {
            final int j = i;
            panels.get(i).addMouseMotionListener(new MouseMotionAdapter() {

                public void mouseDragged(MouseEvent e) {
                    e.translatePoint(e.getComponent().getLocation().x, e.getComponent().getLocation().y);
                    panels.get(j).setLocation(e.getX(), e.getY());
                }

            });
        }
    }

    public MousePanels() {

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1000,650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        JPanel content = new JPanel();
        container.add(content);
        content.setLayout(null);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panels.add(panel1);
        panels.add(panel2);
        panels.add(panel3);
        panels.add(panel4);
        panels.add(panel5);

        panel1.setBounds(300,000,200,200);
        panel2.setBounds(500,200,200,200);
        panel3.setBounds(0,0,200,300);
        panel4.setBounds(100,300,300,200);
        panel5.setBounds(650,0,300,200);

        panel1.setBackground(Color.GREEN);
        panel2.setBackground(Color.RED);
        panel3.setBackground(Color.cyan);
        panel4.setBackground(Color.yellow);
        panel5.setBackground(Color.BLUE);

        content.add(panel1);
        content.add(panel2);
        content.add(panel3);
        content.add(panel4);
        content.add(panel5);

        JTextField field = new JTextField();
        content.add(field);
        field.setBounds(0,500,1000,100);

        frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                field.setText("Mouse Location: " + e.getX() + "," + e.getY());
            }
        });

    }

    public static void main(String args[]) {
        MousePanels mousePanels = new MousePanels();
        mousePanels.handleDrag();

    }

}



