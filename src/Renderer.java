import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer extends JFrame {
    private int tileSize;
    private int width;
    private int height;

    private ImageLoader images;
    private GamePanel gamePanel;
    private Board gameBoard;

    public void repaintGame() {
        gamePanel.repaint();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Renderer(int width, int height, Board gameBoard) {

        this.width = width;
        this.height = height;
        this.gameBoard = gameBoard;

        // start ImageLoader so Renderer has access to images via Renderer.images.XXXX
        this.images = new ImageLoader();

        // Get the resolution of the main display
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int)(0.9*screenSize.height);

        // adapt tileSize according to Y of User resolution
        // e.g. for 25x25 at 2560x1440 -> (0.9*1440)/25 = 57
        tileSize = screenHeight / height;

        // rounds down to nearest number divisible by 16 for 16x16 texture
        int scale = Math.max((tileSize /16),1);
        tileSize = scale*16;

        this.setTitle("Worm Game");
        this.setPreferredSize(new Dimension((tileSize*width)+tileSize*2, (tileSize*height)+tileSize*2));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension((tileSize*width)+tileSize*2, (tileSize*height)+tileSize*2));
        this.add(gamePanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.paintComponent(getGraphics());
    }

    private class GamePanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            BufferedImage tileImage;

            final int RIGHT_EDGE = width-1;
            final int BOTTOM_EDGE = height-1;

            for (int y = -1; y <= height; y++) {
                for(int x = -1; x <= width; x++) {

                    int pixelX = x*tileSize+tileSize;
                    int pixelY = y*tileSize+tileSize;

                    CellType cellType = gameBoard.getCellType(new Point(x,y));

                    // check for food
                    if (cellType == CellType.FOOD) {
                        // If it's food, use the food image
                        tileImage = Renderer.this.images.food;
                    }
                    // check for snake
                    else if (cellType == CellType.SNAKE) {
                        // If it's snake, use the snake body image
                        tileImage = Renderer.this.images.worm;
                    }

                    // check for padding tiles
                    else if (y==-1) tileImage = Renderer.this.images.sky;
                    else if (x==-1) tileImage = Renderer.this.images.gravel;
                    else if (y==BOTTOM_EDGE+1) tileImage = Renderer.this.images.gravel;
                    else if (x==RIGHT_EDGE+1) tileImage = Renderer.this.images.gravel;

                    // check for special dirt cases

                    // check for corners / edges
                    else if (x == 0 && y == 0) {
                        tileImage = images.grassLeft;      // Top-Left
                    }
                    else if (x == RIGHT_EDGE && y == 0) {
                        tileImage = images.grassRight;     // Top-Right
                    }
                    else if (x == 0 && y == BOTTOM_EDGE) {
                        tileImage = images.dirtBottomLeft; // Bottom-Left
                    }
                    else if (x == RIGHT_EDGE && y == BOTTOM_EDGE) {
                        tileImage = images.dirtBottomRight; // Bottom-Right
                    }
                    else if (y == 0) {
                        tileImage = images.grass;          // Top Edge
                    }
                    else if (y == BOTTOM_EDGE) {
                        tileImage = images.dirtBottom;     // Bottom Edge
                    }
                    else if (x == 0) {
                        tileImage = images.dirtLeft;       // Left Edge
                    }
                    else if (x == RIGHT_EDGE) {
                        tileImage = images.dirtRight;      // Right Edge
                    }
                    // default case
                    else {
                        tileImage = images.dirt;           // Inner Tiles
                    }

                    g2d.drawImage(
                            tileImage,         // The determined image for this tile
                            pixelX,            // X position
                            pixelY,            // Y position
                            tileSize,          // Width (scaled)
                            tileSize,          // Height (scaled)
                            null               // ImageObserver
                    );

                }
            }
        }
    }
}
