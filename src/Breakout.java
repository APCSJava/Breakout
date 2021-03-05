import javax.swing.*;
import java.awt.*;

public class Breakout extends JFrame {

    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    public Breakout() {
        super();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Screen width is "+d.getWidth());
        System.out.println("Screen height is "+d.getHeight());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation((int) d.getWidth()/2-WIDTH/2, (int) d.getHeight()/2-HEIGHT/2);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        BreakoutPanel panel = new BreakoutPanel();
        add(panel);
    }


    public static void main(String[] args) {
        Breakout game = new Breakout();
        game.setVisible(true);
        System.out.println("Ready to go!");
    }

}
