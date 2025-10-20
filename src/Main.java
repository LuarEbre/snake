import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities; // <<< ADD THIS IMPORT

class SnakeGame {
    public static void main(String args[]) {

        final int width = 16;
        final int height = 16;

        // CRITICAL FIX: Initialize all GUI elements on the EDT.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameLoop loop = new GameLoop(width, height, 100);
                loop.start();
            }
        });
    }
}