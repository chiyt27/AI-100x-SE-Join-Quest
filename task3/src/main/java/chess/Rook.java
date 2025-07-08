package chess;

public class Rook extends Piece {
    
    public Rook(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        // Rook moves horizontally or vertically
        if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
            return false; // Must move in straight line
        }
        
        // Check if target position is empty or has enemy piece
        Piece targetPiece = board.getPiece(to);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            return false; // Cannot capture own piece
        }
        
        // Check if path is clear (no jumping over pieces)
        return isPathClear(from, to, board);
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
}