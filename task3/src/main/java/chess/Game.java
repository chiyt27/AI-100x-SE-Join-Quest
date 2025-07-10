package chess;

public class Game {
    private Board board;
    private Color currentPlayer;
    private boolean gameOver;
    private Color winner;
    
    public Game() {
        this.board = new Board();
        this.currentPlayer = Color.RED;
        this.gameOver = false;
        this.winner = null;
    }
    
    public boolean makeMove(Position from, Position to) {
        if (!board.isValidPosition(from) || !board.isValidPosition(to)) {
            return false;
        }
        
        Piece piece = board.getPiece(from);
        if (piece == null) {
            return false;
        }
        
        if (!piece.isValidMove(from, to, board)) {
            return false;
        }
        
        // Check if capturing enemy general
        Piece capturedPiece = board.getPiece(to);
        if (capturedPiece instanceof General && capturedPiece.getColor() != piece.getColor()) {
            gameOver = true;
            winner = piece.getColor();
        }
        
        // Make the move
        board.setPiece(to, piece);
        board.setPiece(from, null);
        piece.setPosition(to);
        
        return true;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public Color getWinner() {
        return winner;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public Color getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void switchPlayer() {
        currentPlayer = (currentPlayer == Color.RED) ? Color.BLACK : Color.RED;
    }
}