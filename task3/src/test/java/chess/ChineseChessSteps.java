package chess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import static org.junit.jupiter.api.Assertions.*;

public class ChineseChessSteps {
    
    private Game game;
    private boolean moveResult;
    private String winner;
    
    @Given("the board is empty except for a Red General at \\({int}, {int}\\)")
    public void theboardIsEmptyExceptForARedGeneralAt(int row, int col) {
        game = new Game();
        Position position = new Position(row, col);
        General general = new General(Color.RED, position);
        game.getBoard().setPiece(position, general);
    }
    
    @Given("the board is empty except for a Red Guard at \\({int}, {int}\\)")
    public void theboardIsEmptyExceptForARedGuardAt(int row, int col) {
        game = new Game();
        Position position = new Position(row, col);
        Guard guard = new Guard(Color.RED, position);
        game.getBoard().setPiece(position, guard);
    }
    
    @Given("the board is empty except for a Red Rook at \\({int}, {int}\\)")
    public void theboardIsEmptyExceptForARedRookAt(int row, int col) {
        game = new Game();
        Position position = new Position(row, col);
        Rook rook = new Rook(Color.RED, position);
        game.getBoard().setPiece(position, rook);
    }
    
    @Given("the board is empty except for a Red Horse at \\({int}, {int}\\)")
    public void theboardIsEmptyExceptForARedHorseAt(int row, int col) {
        game = new Game();
        Position position = new Position(row, col);
        Horse horse = new Horse(Color.RED, position);
        game.getBoard().setPiece(position, horse);
    }
    
    @Given("the board is empty except for a Red Cannon at \\({int}, {int}\\)")
    public void theboardIsEmptyExceptForARedCannonAt(int row, int col) {
        game = new Game();
        Position position = new Position(row, col);
        Cannon cannon = new Cannon(Color.RED, position);
        game.getBoard().setPiece(position, cannon);
    }
    
    @Given("the board is empty except for a Red Elephant at \\({int}, {int}\\)")
    public void theboardIsEmptyExceptForARedElephantAt(int row, int col) {
        game = new Game();
        Position position = new Position(row, col);
        Elephant elephant = new Elephant(Color.RED, position);
        game.getBoard().setPiece(position, elephant);
    }
    
    @Given("the board is empty except for a Red Soldier at \\({int}, {int}\\)")
    public void theboardIsEmptyExceptForARedSoldierAt(int row, int col) {
        game = new Game();
        Position position = new Position(row, col);
        Soldier soldier = new Soldier(Color.RED, position);
        game.getBoard().setPiece(position, soldier);
    }
    
    @Given("the board has:")
    public void theboardHas(DataTable dataTable) {
        game = new Game();
        
        var rows = dataTable.asMaps(String.class, String.class);
        for (var row : rows) {
            String pieceName = row.get("Piece");
            String positionStr = row.get("Position");
            
            // Parse position like "(2, 4)"
            String[] coords = positionStr.replaceAll("[()]", "").split(", ");
            int rowNum = Integer.parseInt(coords[0]);
            int colNum = Integer.parseInt(coords[1]);
            Position position = new Position(rowNum, colNum);
            
            Piece piece = createPiece(pieceName, position);
            game.getBoard().setPiece(position, piece);
        }
    }
    
    private Piece createPiece(String pieceName, Position position) {
        if (pieceName.contains("Red General")) {
            return new General(Color.RED, position);
        } else if (pieceName.contains("Black General")) {
            return new General(Color.BLACK, position);
        } else if (pieceName.contains("Red Guard")) {
            return new Guard(Color.RED, position);
        } else if (pieceName.contains("Black Guard")) {
            return new Guard(Color.BLACK, position);
        } else if (pieceName.contains("Red Rook")) {
            return new Rook(Color.RED, position);
        } else if (pieceName.contains("Black Rook")) {
            return new Rook(Color.BLACK, position);
        } else if (pieceName.contains("Red Horse")) {
            return new Horse(Color.RED, position);
        } else if (pieceName.contains("Black Horse")) {
            return new Horse(Color.BLACK, position);
        } else if (pieceName.contains("Red Cannon")) {
            return new Cannon(Color.RED, position);
        } else if (pieceName.contains("Black Cannon")) {
            return new Cannon(Color.BLACK, position);
        } else if (pieceName.contains("Red Elephant")) {
            return new Elephant(Color.RED, position);
        } else if (pieceName.contains("Black Elephant")) {
            return new Elephant(Color.BLACK, position);
        } else if (pieceName.contains("Red Soldier")) {
            return new Soldier(Color.RED, position);
        } else if (pieceName.contains("Black Soldier")) {
            return new Soldier(Color.BLACK, position);
        }
        throw new IllegalArgumentException("Unknown piece: " + pieceName);
    }
    
    @When("Red moves the General from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void redMovesTheGeneralFromTo(int fromRow, int fromCol, int toRow, int toCol) {
        Position from = new Position(fromRow, fromCol);
        Position to = new Position(toRow, toCol);
        moveResult = game.makeMove(from, to);
    }
    
    @When("Red moves the Guard from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void redMovesTheGuardFromTo(int fromRow, int fromCol, int toRow, int toCol) {
        Position from = new Position(fromRow, fromCol);
        Position to = new Position(toRow, toCol);
        moveResult = game.makeMove(from, to);
    }
    
    @When("Red moves the Rook from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void redMovesTheRookFromTo(int fromRow, int fromCol, int toRow, int toCol) {
        Position from = new Position(fromRow, fromCol);
        Position to = new Position(toRow, toCol);
        moveResult = game.makeMove(from, to);
    }
    
    @When("Red moves the Horse from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void redMovesTheHorseFromTo(int fromRow, int fromCol, int toRow, int toCol) {
        Position from = new Position(fromRow, fromCol);
        Position to = new Position(toRow, toCol);
        moveResult = game.makeMove(from, to);
    }
    
    @When("Red moves the Cannon from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void redMovesTheCannonFromTo(int fromRow, int fromCol, int toRow, int toCol) {
        Position from = new Position(fromRow, fromCol);
        Position to = new Position(toRow, toCol);
        moveResult = game.makeMove(from, to);
    }
    
    @When("Red moves the Elephant from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void redMovesTheElephantFromTo(int fromRow, int fromCol, int toRow, int toCol) {
        Position from = new Position(fromRow, fromCol);
        Position to = new Position(toRow, toCol);
        moveResult = game.makeMove(from, to);
    }
    
    @When("Red moves the Soldier from \\({int}, {int}\\) to \\({int}, {int}\\)")
    public void redMovesTheSoldierFromTo(int fromRow, int fromCol, int toRow, int toCol) {
        Position from = new Position(fromRow, fromCol);
        Position to = new Position(toRow, toCol);
        moveResult = game.makeMove(from, to);
    }
    
    @Then("the move is legal")
    public void theMoveIsLegal() {
        assertTrue(moveResult, "Expected move to be legal but it was illegal");
    }
    
    @Then("the move is illegal")
    public void theMoveIsIllegal() {
        assertFalse(moveResult, "Expected move to be illegal but it was legal");
    }
    
    @Then("Red wins immediately")
    public void redWinsImmediately() {
        assertTrue(moveResult, "Move should be legal");
        assertTrue(game.isGameOver(), "Game should be over");
        assertEquals(Color.RED, game.getWinner(), "Red should win");
    }
    
    @Then("the game is not over just from that capture")
    public void theGameIsNotOverJustFromThatCapture() {
        assertTrue(moveResult, "Move should be legal");
        assertFalse(game.isGameOver(), "Game should not be over");
    }
}