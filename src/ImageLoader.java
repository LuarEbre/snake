import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageLoader {
    public BufferedImage gravel;
    public BufferedImage sky;
    public BufferedImage dirt;
    public BufferedImage dirtLeft;
    public BufferedImage dirtRight;
    public BufferedImage dirtBottom;
    public BufferedImage dirtBottomLeft;
    public BufferedImage dirtBottomRight;
    public BufferedImage grass;
    public BufferedImage grassLeft;
    public BufferedImage grassRight;
    public BufferedImage food;
    public BufferedImage worm;

    public ImageLoader() {
        // read images into BufferedImage instances
        try {
            gravel = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/gravel.jpeg")));
            sky = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/sky2.jpeg")));
            dirt = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/dirt.jpeg")));
            dirtLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/left-dirt.jpeg")));
            dirtRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/right-dirt.jpeg")));
            dirtBottom = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/bottom-dirt.jpeg")));
            dirtBottomLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/bl-dirt.jpeg")));
            dirtBottomRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/br-dirt.jpeg")));
            grass = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/grass.jpeg")));
            grassLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/left-grass.jpeg")));
            grassRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/right-grass.jpeg")));
            food = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/mushroom.jpeg")));
            worm = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Resources/worm.jpeg")));

        } catch (Exception e) {
            // Exit if essential images fail to load
            System.err.println("Error loading image file: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}