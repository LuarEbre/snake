import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

class SnakeGame {
    public static void main(String args[]) {

        final int width = 16;
        final int height = 16;
        final int tickMS = 140;

        GameLoop loop = new GameLoop(width, height, tickMS);
        loop.runGame();
        }
    }