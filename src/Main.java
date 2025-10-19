import java.util.concurrent.TimeUnit;

class SnakeGame {
    public static void main(String args[]) {

        final int width = 20;
        final int height = 20;

        Board gameBoard = new Board(4,4);
        gameBoard.printCells();

        while(gameBoard.snake.move()) {
            System.out.println("Snake has moved!");
            gameBoard.printCells();
        }
        Renderer gameWindow = new Renderer(width, height);
        }
    }