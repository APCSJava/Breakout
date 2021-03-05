import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Paddle extends Rectangle2D.Double {

    public static final int WIDTH = 2 * Breakout.WIDTH / BreakoutPanel.NUM_COLS;
    public static final int HEIGHT = Breakout.HEIGHT / 4 / BreakoutPanel.NUM_ROWS;

    private Color color;

    public Paddle() {
        super(Breakout.WIDTH / 2, Breakout.HEIGHT * 0.9, WIDTH, HEIGHT);
        this.color = Color.BLUE;
    }

    public void draw(Graphics2D g2) {
        g2.setPaint(color);
        g2.fill(this);
    }

    public void move(int i) {
        x += i;
        if (i > 0 && x + WIDTH > Breakout.WIDTH) {
            x = Breakout.WIDTH - WIDTH;
        } else if (i < 0 && x < 0) x = 0;
    }
}
