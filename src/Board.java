import java.awt.*;

public class Board {

    Cell[][] cells;
    int width;
    int height;
    Snake snake;
    Food food;

    // toString-like method for debugging
    public void printCells() {

        String reset = "\u001b[0m";
        String green = "\u001b[38;2;57;204;64m";
        String red = "\u001b[38;2;232;16;38m";

        for(int i=0;i<this.height;i++) {
            for(int j=0;j<this.width;j++) {
                if(cells[j][i].type==CellType.SNAKE)
                    System.out.print(green + "SNAKE " + reset);
                else if(cells[j][i].type==CellType.FOOD)
                    System.out.print(red + "FOOD  " + reset);
                else
                    System.out.print("EMPTY ");
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void changeCell(int x, int y, CellType type) {
        this.cells[x][y].type = type;
    }

    public CellType getCellType(Point p) {
        if (p.x >= 0 && p.x < width && p.y >= 0 && p.y < height) {
            return this.cells[p.x][p.y].type;
        }
        else return CellType.WALL;
    }

    public Board(int w, int h) {
        this.width = w;
        this.height = h;
        cells = new Cell[w][h];

        for(int i=0;i<w;i++) {
            for(int j=0;j<h;j++) {
                cells[i][j] = new Cell();
            }
        }

        snake = new Snake(this);
        food = new Food(this);

    }
}
