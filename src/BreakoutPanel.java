import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BreakoutPanel extends JPanel {

    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 16;

    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private Paddle paddle = new Paddle();
    private Ball ball = new Ball();
    private Background background = new Background("cat.jpg");

    public BreakoutPanel() {
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                Brick b = new Brick(r, c, randomColor());
                bricks.add(b);
            }
        }
        TitledBorder border = BorderFactory.createTitledBorder("This goes here.");
        setBorder(border);
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });
        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) paddle.move(-20);
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) paddle.move(20);
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                paddle.x = e.getX() - paddle.width / 2;
            }
        });
        setFocusable(true);
    }

    private void updateGame() {
        final Line2D leftBorder, rightBorder, topBorder, bottomBorder;
        leftBorder = new Line2D.Double(0, 0, 0, Breakout.HEIGHT);
        rightBorder = new Line2D.Double(Breakout.WIDTH, 0, Breakout.WIDTH, Breakout.HEIGHT);
        topBorder = new Line2D.Double(0, 0, Breakout.WIDTH, 0);
        bottomBorder = new Line2D.Double(0, Breakout.HEIGHT, Breakout.WIDTH, Breakout.HEIGHT);

        // check collisions
        Rectangle2D proj = ball.project();
        boolean flipped = false;
        if (proj.intersects(paddle)) {
            ball.flipVerticalSpeed();
            flipped = true;
        }
        if (proj.intersectsLine(leftBorder) || proj.intersectsLine(rightBorder)) {
            ball.flipHorizontalSpeed();
        }
        if (proj.intersectsLine(topBorder)) {
            ball.flipVerticalSpeed();
            flipped = true;
        }
        if (proj.intersectsLine(bottomBorder)) ball = new Ball();
        for (int i = 0; i < bricks.size(); i++) {
            Brick b = bricks.get(i);
            if (b.intersects(proj)) {
                bricks.remove(i);
                if (!flipped) {
                    flipped = true;
                    ball.flipVerticalSpeed();
                }
            }
        }
        ball.update();
    }

    /**
     * JPanel will receive a call to paintComponent when Java is good and ready.
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        background.draw(g2);
        if (bricks.size() == 0) {
            // win
        } else {
            paddle.draw(g2);
            ball.draw(g2);
            for (Brick b : bricks) {
                b.draw(g2);
            }
        }
        showMessage("Hello, Breakout", g2);
    }

    private Color randomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        Color c = new Color(r, g, b, 128);
        return c;
    }

    public void showMessage(String s, Graphics2D g2) {
        Font font = new Font("SansSerif", Font.BOLD + Font.ITALIC, 40);
        Rectangle2D textBox = font.getStringBounds(s, g2.getFontRenderContext());
        g2.setFont(font);
        g2.setColor(new Color(0, 0, 0, 64));
        g2.drawString(s, 50, 50);
    }
}
