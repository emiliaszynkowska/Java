import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DieFrame extends JFrame {

    public void init() {
        this.setSize(320,380);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        DieFrame dieFrame = new DieFrame();
        DiePanel diePanel = new DiePanel();
        dieFrame.init();
        dieFrame.add(diePanel);
        
    }
}

class DiePanel extends JPanel {

    int number;

    public DiePanel() {
        this.setLayout(null);
        JButton button = new JButton("Roll");
        button.addActionListener(e -> {this.roll();this.repaint();});
        button.setSize(300,30);
        this.add(button);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,30,300,300);
        g.setColor(Color.white);
        int x = (getWidth() - 50) / 2;
        int y = (getHeight() - 50) / 2;
        
        if(number == 1) {
            g.fillOval(x,y,50,50);
        }
        else if(number == 2) {
            g.fillOval(50,50,50,50);
            g.fillOval(200,230,50,50);
        }
        else if(number == 3) {
            g.fillOval(x,y,50,50);
            g.fillOval(50,50,50,50);
            g.fillOval(200,230,50,50);
        }
        else if(number == 4) {
            g.fillOval(50,50,50,50);
            g.fillOval(200,230,50,50);
            g.fillOval(50,230,50,50);
            g.fillOval(200,50,50,50);
        }
        else if(number == 5) {
            g.fillOval(x,y,50,50);
            g.fillOval(50,50,50,50);
            g.fillOval(200,230,50,50);
            g.fillOval(50,230,50,50);
            g.fillOval(200,50,50,50);
        }
        else if(number == 6) {
            g.fillOval(50,50,50,50);
            g.fillOval(200,230,50,50);
            g.fillOval(50,230,50,50);
            g.fillOval(200,50,50,50);
            g.fillOval(50,140,50,50);
            g.fillOval(200,140,50,50);
    }
}
    public void roll() {
        this.number = new Random().nextInt(6) + 1;
    }

}   



