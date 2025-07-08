package chess;

public class Board {
    private Piece[][] grid;
    private static final int ROWS = 10;
    private static final int COLS = 9;
    
    public Board() {
        this.grid = new Piece[ROWS][COLS];
    }
    
    public Piece getPiece(Position position) {
        return grid[position.getRow() - 1][position.getCol() - 1];
    }
    
    public void setPiece(Position position, Piece piece) {
        grid[position.getRow() - 1][position.getCol() - 1] = piece;
    }
    
    public boolean isEmpty(Position position) {
        return getPiece(position) == null;
    }
    
    public boolean isValidPosition(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return row >= 1 && row <= ROWS && col >= 1 && col <= COLS;
    }
    
    public void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = null;
            }
        }
    }
}