class SnakeGame {
    public static void main(String args[]) {
        Board gameBoard = new Board(30,20);
        gameBoard.printCells();
        gameBoard.food.moveFood();
        System.out.println("Food has been moved!");
        gameBoard.printCells();
    }
}