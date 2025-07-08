package chess;

public class General extends Piece {
    
    public General(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        // General can only move one step horizontally or vertically
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        
        if (rowDiff + colDiff != 1) {
            return false; // Must move exactly one step
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
        
        // Check for flying generals rule - generals cannot face each other
        if (wouldCreateFlyingGenerals(to, board)) {
            return false;
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
    
    private boolean wouldCreateFlyingGenerals(Position to, Board board) {
        // Find the enemy general
        General enemyGeneral = findEnemyGeneral(board);
        if (enemyGeneral == null) {
            return false;
        }
        
        // Check if they would be on the same column
        if (to.getCol() != enemyGeneral.getPosition().getCol()) {
            return false;
        }
        
        // Check if there are any pieces between them on the same column
        int minRow = Math.min(to.getRow(), enemyGeneral.getPosition().getRow());
        int maxRow = Math.max(to.getRow(), enemyGeneral.getPosition().getRow());
        
        for (int row = minRow + 1; row < maxRow; row++) {
            Position checkPos = new Position(row, to.getCol());
            if (board.getPiece(checkPos) != null) {
                return false; // There's a piece between them, so it's safe
            }
        }
        
        return true; // Generals would face each other with no pieces between
    }
    
    private General findEnemyGeneral(Board board) {
        Color enemyColor = (this.color == Color.RED) ? Color.BLACK : Color.RED;
        
        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 9; col++) {
                Position pos = new Position(row, col);
                Piece piece = board.getPiece(pos);
                if (piece instanceof General && piece.getColor() == enemyColor) {
                    return (General) piece;
                }
            }
        }
        return null;
    }
}