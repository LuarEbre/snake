import java.awt.*;

public class Util {
    public static Point generateRandomPoint(int width, int height) {
        int randomX = 1 + (int) (Math.random() * (width - 2));
        int randomY = 1 + (int) (Math.random() * (height - 2));
        return new Point(randomX, randomY);
    }

    public static Point shiftX(Point point, int n) {
        int newX = point.x + n;
        return new Point(newX, point.y);
    }

    public static Point shiftY(Point point, int n) {
        int newY = point.y + n;
        return new Point(point.x, newY);
    }
}