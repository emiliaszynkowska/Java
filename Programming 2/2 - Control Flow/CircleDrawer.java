import javax.swing.*;
import java.awt.*;
import java.lang.Math;
@SuppressWarnings("serial")
 
public class CircleDrawer extends JFrame {

    public static void main(String[] args) {
        new CircleDrawer();
    }

    public CircleDrawer() {
        setTitle("Recursive Circle Drawer");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		setSize((int)screenWidth*2/3,(int)screenHeight*2/3);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void recurse(Graphics g, int widthHeight, int color, int counter, double x){
        g.setColor(new Color(10,color,10));
        if (counter != 7){
            int xValue = (int) Math.round(1250+x-widthHeight/2);
            g.fillOval(xValue,700-widthHeight/2,widthHeight,widthHeight);

            recurse(g,widthHeight/2,color-30,counter+1,x+1250/Math.pow(2, counter));
            recurse(g,widthHeight/2,color-30,counter+1,x-1250/Math.pow(2, counter));
            recurse(g,widthHeight/2,color-30,counter+1,x);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        recurse(g,700,240,0,0d);
    }

}