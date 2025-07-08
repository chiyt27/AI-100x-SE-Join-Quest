package chess;

public class Horse extends Piece {
    
    public Horse(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        
        // Horse moves in L-shape: 2 squares in one direction, 1 in perpendicular
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }
        
        // Check if target position is empty or has enemy piece
        Piece targetPiece = board.getPiece(to);
        if (targetPiece != null && targetPiece.getColor() == this.color) {
            return false; // Cannot capture own piece
        }
        
        // Check for leg blocking
        Position legPosition = getLegPosition(from, to);
        if (board.getPiece(legPosition) != null) {
            return false; // Leg is blocked
        }
        
        return true;
    }
    
    private Position getLegPosition(Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();
        
        if (Math.abs(rowDiff) == 2) {
            // Moving 2 rows, leg is 1 row in that direction
            return new Position(from.getRow() + rowDiff/2, from.getCol());
        } else {
            // Moving 2 cols, leg is 1 col in that direction
            return new Position(from.getRow(), from.getCol() + colDiff/2);
        }
    }
}