package Chess_V1.game.allGames;

import Chess_V1.enums.GameStatus;
import Chess_V1.enums.PieceColor;
import Chess_V1.game.BoardGames;
import Chess_V1.pieceFactory.Piece;
import Chess_V1.pieceFactory.concretePieces.King;
import Chess_V1.playerFactory.Player;
import Chess_V1.utils.Board;
import Chess_V1.utils.Cell;
import Chess_V1.utils.Constants;
import Chess_V1.utils.Move;

import java.util.ArrayList;
import java.util.Scanner;


public class ChessGame implements BoardGames {
    private Board board;
    private Player player1;
    private Player player2;
    boolean isWhiteTurn;
    private ArrayList<Move> gameLog;
    private GameStatus status;
    private Scanner inputScanner;

    public ChessGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = Board.getBoardInstance(Constants.CHESS_BOARD_DEFAULT_SIZE);
        this.isWhiteTurn = true;
        this.status = GameStatus.ACTIVE;
        this.gameLog = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
    }

    @Override
    public void startGame() {
        while (this.status == GameStatus.ACTIVE) {
            Player currPlayerTurn = this.isWhiteTurn ? player1 : player2;
            System.out.println(currPlayerTurn.getName() + ", Its your turn. Your game piece color is " +
                    (currPlayerTurn.isWhiteSide() ? PieceColor.WHITE : PieceColor.BLACK));

            // Ask for source coordinates
            System.out.println("Enter coordinates of piece you want to move: (e.g., 6 4)");
            int startRow = inputScanner.nextInt();
            int startCol = inputScanner.nextInt();

            Cell startCell = board.getCell(startRow, startCol);
            if (startCell == null || startCell.getPiece() == null) {
                System.out.println("Invalid move: No piece at source cell");
                continue;
            }

            // Ask for destination coordinates
            System.out.println("Enter coordinates of piece you want to move: (e.g., 7 6)");
            int endRow = inputScanner.nextInt();
            int endCol = inputScanner.nextInt();

            Cell endCell = board.getCell(endRow, endCol);

            makeMove(new Move(startCell, endCell), currPlayerTurn);
        }
        System.out.println("Game Over! Status: " + this.status);
        inputScanner.close();
    }

    private void makeMove(Move move, Player currPlayerTurn) {
        if (!move.isValidMove()) {
            System.out.println("Invalid move: Destination cell has same color piece");
            return;
        }

        Piece sourcePiece = move.getStartCell().getPiece();
        if (!sourcePiece.canMove(this.board, move.getStartCell(), move.getEndCell())) {
            System.out.println("Invalid move: this move is not valid for sourcePiece");
            return;
        }

        Piece destinationPiece = move.getEndCell().getPiece();
        if (destinationPiece != null) {
            //
            if (destinationPiece instanceof King && isWhiteTurn) {
                this.status = GameStatus.WHITE_WINS;
                return;
            }

            //
            if (destinationPiece instanceof King) {
                this.status = GameStatus.BLACK_WINS;
                return;
            }

            //
            destinationPiece.setPieceKilled(true);
        }

        move.getEndCell().setPiece(sourcePiece);
        move.getStartCell().setPiece(null);

        gameLog.add(move);

        this.isWhiteTurn = !this.isWhiteTurn;
    }
}
