import java.awt.*;

public class Food {
    Point position;
    Board board;

    public void moveFood() {
        // no longer sets prior FOOD to EMPTY, since the snake's logic will set the cell to SNAKE
        board.changeCell(this.position.x,this.position.y,CellType.EMPTY);
        do {
            this.position = Util.generateRandomPoint(board.getWidth(), board.getHeight());
        } while (board.getCellType(this.position) != CellType.EMPTY);
        board.changeCell(this.position.x,this.position.y,CellType.FOOD);
    }

    public Food(Board board) {
        this.board = board;
        this.position = Util.generateRandomPoint(board.getWidth(),board.getHeight());
        board.changeCell(this.position.x,this.position.y,CellType.FOOD);
    }
}
