package chess;

public class Elephant extends Piece {
    
    public Elephant(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        
        // Elephant moves exactly 2 steps diagonally
        if (rowDiff != 2 || colDiff != 2) {
            return false;
        }
        
        // Check if crossing the river
        if (!canCrossRiver(to)) {
            return false;
        }
        
        // Check if target position is empty or has enemy piece
        Piece targetPiece = board.getPiece(to);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            return false; // Cannot capture own piece
        }
        
        // Check if midpoint is blocked (elephant eye blocking)
        Position midPoint = new Position((from.getRow() + to.getRow()) / 2, 
                                       (from.getCol() + to.getCol()) / 2);
        if (board.getPiece(midPoint) != null) {
            return false; // Midpoint blocked
        }
        
        return true;
    }
    
    private boolean canCrossRiver(Position to) {
        if (color == Color.RED) {
            // Red elephant cannot cross to black side (rows 6-10)
            return to.getRow() <= 5;
        } else {
            // Black elephant cannot cross to red side (rows 1-5)
            return to.getRow() >= 6;
        }
    }
}