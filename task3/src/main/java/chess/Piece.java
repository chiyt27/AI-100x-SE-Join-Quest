package chess;

public abstract class Piece {
    protected Color color;
    protected Position position;
    
    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public abstract boolean isValidMove(Position from, Position to, Board board);
}