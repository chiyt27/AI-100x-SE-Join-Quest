package chess;

import java.util.Scanner;

public class ChineseChessGame {
    private Game game;
    private Scanner scanner;
    
    public ChineseChessGame() {
        this.game = new Game();
        this.scanner = new Scanner(System.in);
        initializeBoard();
    }
    
    private void initializeBoard() {
        Board board = game.getBoard();
        
        // 紅方棋子 (底部，行1-3)
        board.setPiece(new Position(1, 1), new Rook(Color.RED, new Position(1, 1)));
        board.setPiece(new Position(1, 2), new Horse(Color.RED, new Position(1, 2)));
        board.setPiece(new Position(1, 3), new Elephant(Color.RED, new Position(1, 3)));
        board.setPiece(new Position(1, 4), new Guard(Color.RED, new Position(1, 4)));
        board.setPiece(new Position(1, 5), new General(Color.RED, new Position(1, 5)));
        board.setPiece(new Position(1, 6), new Guard(Color.RED, new Position(1, 6)));
        board.setPiece(new Position(1, 7), new Elephant(Color.RED, new Position(1, 7)));
        board.setPiece(new Position(1, 8), new Horse(Color.RED, new Position(1, 8)));
        board.setPiece(new Position(1, 9), new Rook(Color.RED, new Position(1, 9)));
        
        board.setPiece(new Position(3, 2), new Cannon(Color.RED, new Position(3, 2)));
        board.setPiece(new Position(3, 8), new Cannon(Color.RED, new Position(3, 8)));
        
        board.setPiece(new Position(4, 1), new Soldier(Color.RED, new Position(4, 1)));
        board.setPiece(new Position(4, 3), new Soldier(Color.RED, new Position(4, 3)));
        board.setPiece(new Position(4, 5), new Soldier(Color.RED, new Position(4, 5)));
        board.setPiece(new Position(4, 7), new Soldier(Color.RED, new Position(4, 7)));
        board.setPiece(new Position(4, 9), new Soldier(Color.RED, new Position(4, 9)));
        
        // 黑方棋子 (頂部，行8-10)
        board.setPiece(new Position(10, 1), new Rook(Color.BLACK, new Position(10, 1)));
        board.setPiece(new Position(10, 2), new Horse(Color.BLACK, new Position(10, 2)));
        board.setPiece(new Position(10, 3), new Elephant(Color.BLACK, new Position(10, 3)));
        board.setPiece(new Position(10, 4), new Guard(Color.BLACK, new Position(10, 4)));
        board.setPiece(new Position(10, 5), new General(Color.BLACK, new Position(10, 5)));
        board.setPiece(new Position(10, 6), new Guard(Color.BLACK, new Position(10, 6)));
        board.setPiece(new Position(10, 7), new Elephant(Color.BLACK, new Position(10, 7)));
        board.setPiece(new Position(10, 8), new Horse(Color.BLACK, new Position(10, 8)));
        board.setPiece(new Position(10, 9), new Rook(Color.BLACK, new Position(10, 9)));
        
        board.setPiece(new Position(8, 2), new Cannon(Color.BLACK, new Position(8, 2)));
        board.setPiece(new Position(8, 8), new Cannon(Color.BLACK, new Position(8, 8)));
        
        board.setPiece(new Position(7, 1), new Soldier(Color.BLACK, new Position(7, 1)));
        board.setPiece(new Position(7, 3), new Soldier(Color.BLACK, new Position(7, 3)));
        board.setPiece(new Position(7, 5), new Soldier(Color.BLACK, new Position(7, 5)));
        board.setPiece(new Position(7, 7), new Soldier(Color.BLACK, new Position(7, 7)));
        board.setPiece(new Position(7, 9), new Soldier(Color.BLACK, new Position(7, 9)));
    }
    
    public void displayBoard() {
        System.out.println("\n=== 中國象棋 ===");
        System.out.println("當前玩家: " + (game.getCurrentPlayer() == Color.RED ? "紅方" : "黑方"));
        System.out.println();
        
        // 顯示列號
        System.out.print("   ");
        for (int col = 1; col <= 9; col++) {
            System.out.printf("%2d ", col);
        }
        System.out.println();
        
        // 顯示棋盤
        for (int row = 10; row >= 1; row--) {
            System.out.printf("%2d ", row);
            for (int col = 1; col <= 9; col++) {
                Piece piece = game.getBoard().getPiece(new Position(row, col));
                if (piece == null) {
                    if (row == 5 || row == 6) {
                        System.out.print("-- "); // 河界
                    } else {
                        System.out.print("·  ");
                    }
                } else {
                    System.out.print(getPieceSymbol(piece) + " ");
                }
            }
            System.out.println();
            
            // 在第5行後顯示河界
            if (row == 6) {
                System.out.println("   ================= 楚河漢界 =================");
            }
        }
        System.out.println();
    }
    
    private String getPieceSymbol(Piece piece) {
        String symbol = "";
        if (piece instanceof General) symbol = "帥";
        else if (piece instanceof Guard) symbol = "仕";
        else if (piece instanceof Elephant) symbol = "相";
        else if (piece instanceof Horse) symbol = "馬";
        else if (piece instanceof Rook) symbol = "車";
        else if (piece instanceof Cannon) symbol = "炮";
        else if (piece instanceof Soldier) symbol = "兵";
        
        // 黑方棋子用不同符號
        if (piece.getColor() == Color.BLACK) {
            if (piece instanceof General) symbol = "將";
            else if (piece instanceof Guard) symbol = "士";
            else if (piece instanceof Elephant) symbol = "象";
            else if (piece instanceof Horse) symbol = "馬";
            else if (piece instanceof Rook) symbol = "車";
            else if (piece instanceof Cannon) symbol = "砲";
            else if (piece instanceof Soldier) symbol = "卒";
        }
        
        return symbol;
    }
    
    public void playGame() {
        System.out.println("歡迎來到中國象棋！");
        System.out.println("輸入格式: 起始位置 目標位置 (例如: 1 5 2 5)");
        System.out.println("輸入 'quit' 退出遊戲");
        
        while (!game.isGameOver()) {
            displayBoard();
            
            System.out.print("請輸入您的移動 (格式: 行 列 行 列): ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("感謝遊戲！");
                break;
            }
            
            try {
                String[] parts = input.split("\\s+");
                if (parts.length != 4) {
                    System.out.println("輸入格式錯誤！請輸入: 起始行 起始列 目標行 目標列");
                    continue;
                }
                
                int fromRow = Integer.parseInt(parts[0]);
                int fromCol = Integer.parseInt(parts[1]);
                int toRow = Integer.parseInt(parts[2]);
                int toCol = Integer.parseInt(parts[3]);
                
                Position from = new Position(fromRow, fromCol);
                Position to = new Position(toRow, toCol);
                
                // 檢查是否為當前玩家的棋子
                Piece piece = game.getBoard().getPiece(from);
                if (piece == null) {
                    System.out.println("該位置沒有棋子！");
                    continue;
                }
                
                if (piece.getColor() != game.getCurrentPlayer()) {
                    System.out.println("這不是您的棋子！");
                    continue;
                }
                
                boolean moveSuccessful = game.makeMove(from, to);
                if (moveSuccessful) {
                    System.out.println("移動成功！");
                    // 切換玩家
                    game.switchPlayer();
                } else {
                    System.out.println("無效的移動！請檢查象棋規則。");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("輸入格式錯誤！請輸入數字。");
            } catch (Exception e) {
                System.out.println("發生錯誤：" + e.getMessage());
            }
        }
        
        if (game.isGameOver()) {
            displayBoard();
            Color winner = game.getWinner();
            System.out.println("遊戲結束！");
            System.out.println((winner == Color.RED ? "紅方" : "黑方") + " 獲勝！");
        }
    }
    
    public static void main(String[] args) {
        ChineseChessGame chessGame = new ChineseChessGame();
        chessGame.playGame();
    }
}