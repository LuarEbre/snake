import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener {

    private Renderer renderer;
    private Board gameBoard;
    private Timer timer;

    private boolean running;

    public Board getGameBoard() {
        return gameBoard;
    }

    public GameLoop(int width, int height, int tickMS) {
        this.gameBoard = new Board(width, height);
        this.renderer = new Renderer(width, height, gameBoard);
        this.timer = new Timer(tickMS, this);
        // CRITICAL: Ensure the blocking while-loop is GONE here.
    }

    public void start() {
        this.running = true;
        // Trigger the first actionPerformed (move and repaint) immediately,
        // which draws the initial state before the timer delay.
        actionPerformed(null);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!running) {
            timer.stop();
            return;
        }
        boolean continueGame = gameBoard.snake.move();

        if(!continueGame) {
            running = false;
            // GAME OVER screen
        }
        renderer.repaintGame();
    }
}
