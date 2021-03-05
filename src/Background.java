import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background {

    Image image = null;
    boolean loaded = false;

    public Background(String imageFile) {
        try {
            image = ImageIO.read(new File(imageFile));
            loaded = true;
        } catch (IOException e) {
            // leave unloaded
        }
    }

    public void draw(Graphics2D g2) {
        if (loaded) {
            g2.drawImage(image, 0, 0, Breakout.WIDTH, Breakout.HEIGHT, null);
        }
    }

}
