import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleSubmitCancelForm {

    public static void main(String[] args){

     	JFrame root = new JFrame("Simple Submit Cancel Form");
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(500,200);
        root.setLocationRelativeTo(null);

        Container container = root.getContentPane();
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        container.add(content);

        JPanel fieldPanel = new JPanel();
        content.add(fieldPanel);
        JTextField field = new JTextField(30);
        field.setEditable(true);
        fieldPanel.add(field);

        JPanel buttonPanel = new JPanel();
        content.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());
        JButton button1 = new JButton("Submit");
        JButton button2 = new JButton("Cancel");
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        root.setVisible(true);

        button1.addActionListener(new ActionListener() {
            String text;
            public void actionPerformed(ActionEvent e) {
                text = field.getText();
                field.setText("Submitted");
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                root.dispose();
            }
        });
          
    }

}