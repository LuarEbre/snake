import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;


public class GameLoop implements ActionListener {

    private Renderer renderer;
    private Board gameBoard;
    private Timer timer;
    private Graphics2D graphics;
    private int score;
    private int highScore = 0;
    private boolean running = false;

    private File io;
    private Scanner scanner;
    private PrintWriter writer;

    public GameLoop(int width, int height, int tickMS) {
        try {
            this.io = new File("highscore.txt");
            if(!(io.exists())) {
                io.createNewFile();
                PrintWriter writer = new PrintWriter(io);
                writer.print(0);
                writer.close();
            }
            // for reading
            this.scanner = new Scanner(this.io);
            this.highScore = scanner.nextInt();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        this.timer = new Timer(tickMS, this);
        this.gameBoard = new Board(width, height);
        this.renderer = new Renderer(width, height, this);
        renderer.repaintGame();
    }

    public boolean isRunning() {
        return running;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void restartGame() {
        if (this.running) return;
        this.gameBoard = new Board(this.gameBoard.getWidth(), this.gameBoard.getHeight());
        this.renderer.setGameBoard(this.gameBoard);
        this.running = true;
        timer.start();
    }

    public void runGame() {
        if(this.running) return;
        this.running = true;
        actionPerformed(null);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.paintComponents(renderer.getGraphics());
        if(!running) {
            timer.stop();
            return;
        }
        gameBoard.printCells();

        boolean continueGame = gameBoard.snake.move();

        this.score = this.gameBoard.snake.body.size()-1;

        if(this.score > this.highScore) {
            this.highScore = this.score;
            try {
                this.writer = new PrintWriter(this.io);
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
                System.exit(1);
            }
            this.writer.print(this.highScore);
            this.writer.close();
        }

        System.out.println("Current score: " + this.score);
        System.out.println("Highscore: " + this.highScore);

        if(!continueGame) {
            running = false;
            // GAME OVER screen
        }

    }
}