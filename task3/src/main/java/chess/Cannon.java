package chess;

public class Cannon extends Piece {
    
    public Cannon(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        // Cannon moves horizontally or vertically like Rook
        if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
            return false; // Must move in straight line
        }
        
        Piece targetPiece = board.getPiece(to);
        
        if (targetPiece == null) {
            // Moving to empty square - path must be clear (like Rook)
            return isPathClear(from, to, board);
        } else {
            // Capturing - must jump exactly one piece (screen)
            if (targetPiece.getColor() == this.color) {
                return false; // Cannot capture own piece
            }
            return hasExactlyOneScreen(from, to, board);
        }
    }
    
    private boolean isPathClear(Position from, Position to, Board board) {
        int rowDir = Integer.compare(to.getRow(), from.getRow());
        int colDir = Integer.compare(to.getCol(), from.getCol());
        
        int currentRow = from.getRow() + rowDir;
        int currentCol = from.getCol() + colDir;
        
        while (currentRow != to.getRow() || currentCol != to.getCol()) {
            Position checkPos = new Position(currentRow, currentCol);
            if (board.getPiece(checkPos) != null) {
                return false; // Path blocked
            }
            currentRow += rowDir;
            currentCol += colDir;
        }
        
        return true;
    }
    
    private boolean hasExactlyOneScreen(Position from, Position to, Board board) {
        int rowDir = Integer.compare(to.getRow(), from.getRow());
        int colDir = Integer.compare(to.getCol(), from.getCol());
        
        int currentRow = from.getRow() + rowDir;
        int currentCol = from.getCol() + colDir;
        int screenCount = 0;
        
        while (currentRow != to.getRow() || currentCol != to.getCol()) {
            Position checkPos = new Position(currentRow, currentCol);
            if (board.getPiece(checkPos) != null) {
                screenCount++;
            }
            currentRow += rowDir;
            currentCol += colDir;
        }
        
        return screenCount == 1;
    }
}