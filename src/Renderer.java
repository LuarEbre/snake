import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Renderer extends JFrame {
    // 40px per tile as passed within width / height
    private int tileSize;

    public Renderer(int width, int height) {

        // Get the resolution of the main display
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int)(0.9*screenSize.height);

        // adapt tileSize according to Y of User resolution
        // e.g. for 25x25 at 2560x1440 -> (0.9*1440)/25 = 57
        this.tileSize = screenHeight / height;

        // rounds down to nearest number divisible by 16 for 16x16 texture
        int scale = Math.max((this.tileSize/16),1);
        this.tileSize = scale*16;


        this.setTitle("Worm Game");
        this.setPreferredSize(new Dimension(tileSize *width, tileSize *height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
