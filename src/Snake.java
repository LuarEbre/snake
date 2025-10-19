import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    LinkedList<Point> body;
    Direction direction;
    Board board;

    public Direction getOppositeDirection(Direction direction) {
        if (direction == Direction.NORTH) return Direction.SOUTH;
        if (direction == Direction.SOUTH) return Direction.NORTH;
        if (direction == Direction.EAST) return Direction.WEST;
        if (direction == Direction.WEST) return Direction.EAST;
        return direction;
    }

    public void changeDirection(Direction direction) {
        Direction opposite = getOppositeDirection(this.direction);
        if(direction != opposite) {
            this.direction = direction;
        }
    }

    public boolean move() {

        Point newHead;

        if (direction == Direction.NORTH) {
            newHead = Util.shiftY(body.getFirst(),-1);
        }
        else if (direction == Direction.SOUTH) {
            newHead = Util.shiftY(body.getFirst(),1);
        }
        else if (direction == Direction.EAST) {
            newHead = Util.shiftX(body.getFirst(),1);
        }
        else {
            newHead = Util.shiftX(body.getFirst(),-1);
        }

        // singular lookup of next cell type
        CellType nextCell = board.getCellType(newHead);

        // if snake collides with food
        if(nextCell==CellType.FOOD) {
            // does not remove the tail, as snake grows
            // only moves food
            board.food.moveFood();
        }

        // if snake collides with itself or a wall (wall assigned to out-of-bounds coordinates)
        else if (nextCell==CellType.SNAKE||nextCell==CellType.WALL) {
            // see if collision is the tail of the snake
            if(board.snake.body.getLast().equals(newHead)) {
                body.removeLast();
                body.addFirst(newHead);
                return true;
            }
            // GAME OVER
            else return false;
        }

        // if snake does not collide with anything
        else {
            // remember last element, then delete it
            Point toDelete = body.getLast();
            body.removeLast();
            board.changeCell(toDelete.x,toDelete.y,CellType.EMPTY);
        }
        body.addFirst(newHead);
        board.changeCell(newHead.x,newHead.y,CellType.SNAKE);
        return true;
    }

    public Snake(Board board) {
        this.board = board;
        body = new LinkedList<>();
        Point startPos = Util.generateRandomPoint(board.getWidth(),board.getHeight());
        body.add(startPos);
        board.changeCell(startPos.x,startPos.y,CellType.SNAKE);
        direction = Direction.EAST;
    }
}