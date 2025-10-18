import java.awt.*;

public class Util {
    public static Point generateRandomPoint(int width, int height) {
        int randomX = 1 + (int)(Math.random() * (width - 1));
        int randomY = 1 + (int)(Math.random() * (height - 1));
        return new Point(randomX, randomY);
    }
}
