import java.awt.Point;
import java.util.LinkedList;

enum Direction {
    NORTH, SOUTH, EAST, WEST;
}

public class Snake {
    LinkedList<Point> body;
    Direction direction;

    public void move() {

    }
    public Snake(Board board) {
        body = new LinkedList<>();
        Point startPos = Util.generateRandomPoint(board.getWidth(),board.getHeight());
        body.add(startPos);
        board.changeCell(startPos.x,startPos.y,CellType.SNAKE);
        direction = Direction.EAST;
    }
}
