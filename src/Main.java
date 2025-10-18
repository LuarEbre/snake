import java.util.concurrent.TimeUnit;

class SnakeGame {
    public static void main(String args[]) {

        Board gameBoard = new Board(10,5);
        gameBoard.printCells();

        while(gameBoard.snake.move()) {
            System.out.println("Snake has moved!");
            gameBoard.printCells();
        }
        }
    }