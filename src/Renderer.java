import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import javax.swing.SwingUtilities;

public class Renderer extends JFrame implements KeyListener {
    private int tileSize;
    private int width;
    private int height;

    private ImageLoader images;
    private GamePanel gamePanel;
    private GameLoop loop;
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

    public JPanel getPanel() {
        return gamePanel;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used for directional input
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // The Renderer has access to the Board, which has the Snake instance
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                gameBoard.snake.changeDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                gameBoard.snake.changeDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                gameBoard.snake.changeDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                gameBoard.snake.changeDirection(Direction.EAST);
                break;
            case KeyEvent.VK_R:
                if(!(loop.isRunning())) {
                    loop.restartGame();
                }
        }
    }

    public Renderer(int width, int height, GameLoop loop) {

        this.width = width;
        this.height = height;
        this.loop = loop;
        this.gameBoard = loop.getGameBoard();
        // start ImageLoader so Renderer has access to images via "this.images.XXXX"
        this.images = new ImageLoader();

        // Get the resolution of the main display
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        // adapt tileSize according to Y of User resolution
        tileSize = screenHeight / height;

        // rounds down to nearest number divisible by 16 for 16x16 texture
        int scale = Math.max((tileSize /16),1);
        tileSize = scale*16;

        this.setTitle("Worm Game");
        this.setPreferredSize(new Dimension((tileSize*width)+tileSize*2, (tileSize*height)+tileSize*2));

        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension((tileSize*width)+tileSize*2, (tileSize*height)+tileSize*2));
        this.add(gamePanel);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
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
                            this               // ImageObserver
                    );

                }
            }
        }
    }
}
