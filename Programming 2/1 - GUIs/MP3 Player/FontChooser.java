import javax.swing.*;
import java.awt.*;

public class FontChooser {

    public static void main(String[] args){

        //root frame
     	JFrame root = new JFrame("Font Chooser");
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(800,200);
        root.setLocationRelativeTo(null);

        //main container
        Container container = root.getContentPane();

        //all content
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        container.add(content);

        //checkboxes
        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));
        JCheckBox bold = new JCheckBox("Bold");
        JCheckBox italic = new JCheckBox("Italic");
        checkBoxes.add(bold);
        checkBoxes.add(italic);
        content.add(checkBoxes);

        //combo box
        JPanel comboxPanel = new JPanel();
        content.add(comboxPanel);
        comboxPanel.setLayout(new BoxLayout(comboxPanel, BoxLayout.PAGE_AXIS));
        String[] options = new String[] {"Times", "Helvetica", "Courier"};
        JComboBox<String> combox = new JComboBox<String>(options);
        comboxPanel.add(combox);

        //text field
        JPanel textField = new JPanel();
        content.add(textField);
        JTextField field = new JTextField(30);
        field.setEditable(true);
        textField.add(field);

        //ok button
        JPanel okPanel = new JPanel();
        content.add(okPanel);
        JButton ok = new JButton("Ok");
        okPanel.add(ok);

        root.setVisible(true);
    }

}