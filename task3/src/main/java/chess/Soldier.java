package chess;

public class Soldier extends Piece {
    
    public Soldier(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();
        
        // Must move exactly one step
        if (Math.abs(rowDiff) + Math.abs(colDiff) != 1) {
            return false;
        }
        
        // Check if target position is empty or has enemy piece
        Piece targetPiece = board.getPiece(to);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            return false; // Cannot capture own piece
        }
        
        boolean hasCrossedRiver = hasCrossedRiver(from);
        
        if (color == Color.RED) {
            // Red soldier moves forward (increasing row numbers)
            if (rowDiff < 0) {
                return false; // Cannot move backward
            }
            
            if (!hasCrossedRiver && colDiff != 0) {
                return false; // Cannot move sideways before crossing river
            }
        } else {
            // Black soldier moves forward (decreasing row numbers)
            if (rowDiff > 0) {
                return false; // Cannot move backward
            }
            
            if (!hasCrossedRiver && colDiff != 0) {
                return false; // Cannot move sideways before crossing river
            }
        }
        
        return true;
    }
    
    private boolean hasCrossedRiver(Position position) {
        if (color == Color.RED) {
            // Red soldier crosses river at row 6 and beyond
            return position.getRow() >= 6;
        } else {
            // Black soldier crosses river at row 5 and below
            return position.getRow() <= 5;
        }
    }
}