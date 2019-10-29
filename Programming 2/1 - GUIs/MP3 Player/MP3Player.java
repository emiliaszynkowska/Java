import javax.swing.*;
import java.awt.*;

public class MP3Player {

    public static void main(String[] args){

        //root frame
        JFrame root = new JFrame("MP3 Player");
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(800,500);
        root.setLocationRelativeTo(null);
        root.setUndecorated(true);
        root.setOpacity(0.7f);

        //main container
        Container container = root.getContentPane();

        //all content
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        container.add(content);

        //title
        JPanel titlePanel = new JPanel();
        content.add(titlePanel);
        JLabel title = new JLabel("Ed Sheeran - Bloodstream");
        title.setFont(new Font("Times", Font.ITALIC, 30)); 
        titlePanel.add(title);

        //album art
        JPanel artPanel = new JPanel();
        JLabel artLabel = new JLabel(new ImageIcon("ed.jpg"));
        artPanel.add(artLabel);
        content.add(artPanel);

        //bars
        JPanel bars = new JPanel();
        bars.setLayout(new FlowLayout());
        JLabel progressName = new JLabel("Progress");
        JLabel volumeName = new JLabel("Volume");
        JSlider progress = new JSlider();
        JSlider volume = new JSlider(0, 100);
        progressName.setFont(new Font("Times", Font.PLAIN, 15)); 
        volumeName.setFont(new Font("Times", Font.PLAIN, 15)); 
        bars.add(progressName);
        bars.add(progress);
        bars.add(volumeName);
        bars.add(volume);
        content.add(bars);

        //controls
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton back = new JButton(new ImageIcon("back.png"));
        JButton play = new JButton(new ImageIcon("play.png"));
        JButton next = new JButton(new ImageIcon("next.png"));
        back.setBorderPainted(false);
        play.setBorderPainted(false);
        next.setBorderPainted(false);
        back.setContentAreaFilled(false);
        play.setContentAreaFilled(false);
        next.setContentAreaFilled(false);
        controlPanel.add(back);
        controlPanel.add(play);
        controlPanel.add(next);
        content.add(controlPanel);

        root.setVisible(true);
    }

}