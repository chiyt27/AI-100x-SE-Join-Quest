package chess;

public class Guard extends Piece {
    
    public Guard(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        // Guard can only move one step diagonally
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        
        if (rowDiff != 1 || colDiff != 1) {
            return false; // Must move exactly one step diagonally
        }
        
        // Check if the move is within the palace
        if (!isInPalace(to)) {
            return false;
        }
        
        // Check if target position is empty or has enemy piece
        Piece targetPiece = board.getPiece(to);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            return false; // Cannot capture own piece
        }
        
        return true;
    }
    
    private boolean isInPalace(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        
        if (color == Color.RED) {
            // Red palace: rows 1-3, cols 4-6
            return row >= 1 && row <= 3 && col >= 4 && col <= 6;
        } else {
            // Black palace: rows 8-10, cols 4-6
            return row >= 8 && row <= 10 && col >= 4 && col <= 6;
        }
    }
}