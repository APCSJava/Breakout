import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Brick extends Rectangle2D.Double {

    public static final int WIDTH = Breakout.WIDTH / BreakoutPanel.NUM_COLS;
    public static final int HEIGHT = Breakout.HEIGHT / 2 / BreakoutPanel.NUM_ROWS;

    private Color color;

    public Brick(int row, int column, Color color) {
        super(column * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setPaint(color);
        g2.fill(this);
    }
}
