import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListenerOne {

    int count = 0;
    JTextField text;

    public ListenerOne() {

        JFrame frame = new JFrame();
        frame.setSize(500,125);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        JPanel content = new JPanel();
        container.add(content);
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        content.add(buttons);
        JButton increment = new JButton("Increment");
        JButton reset = new JButton("Reset");
        buttons.add(increment);
        buttons.add(reset);
        increment.addActionListener(new IncrementListener());
        reset.addActionListener(new ResetListener());

        JPanel innerContent = new JPanel();
        innerContent.setLayout(new FlowLayout());
        content.add(innerContent);
        text = new JTextField("Button has been pressed 0 times");
        innerContent.add(text);

        frame.setVisible(true);

    }

    public static void main(String args[]) {
        new ListenerOne();
    }

    public class IncrementListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            count += 1;
            text.setText("Button has been pressed " + count + " times");
        }
    }
    public class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            count = 0;
            text.setText("Button has been pressed " + count + " times");
        }
    }

}

