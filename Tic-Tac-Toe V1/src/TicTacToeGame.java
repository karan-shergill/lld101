import Model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {
    public Deque<Player> players;
    public Board gameBoard;

    public TicTacToeGame() {
        players = new LinkedList<>();
        gameBoard = new Board(3);

        Player player1 = new Player("Player_1", new PlayingPieceX());
        Player player2 = new Player("Player_2", new PlayingPieceO());
        players.add(player1);
        players.add(player2);
    }

    public String runGame() {
        boolean noWinner = true;
        while (noWinner) {
            Player currPlayerTurn = players.removeFirst();

            gameBoard.printGameBoard();
            if (gameBoard.noFreeSpaceOnGameBoard()) {
                noWinner = false;
                continue;
            }

            System.out.println("Player: " + currPlayerTurn.getName() + " enter row & col to fill in (Format: row,col)");
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            String[] value = input.split(",");
            int row = Integer.parseInt(value[0]);
            int col = Integer.parseInt(value[1]);

            boolean pieceAddedSuccessfully = gameBoard.addPiece(row, col, currPlayerTurn.getPlayingPiece());
            if (!pieceAddedSuccessfully) {
                System.out.println("Selected row & col not free, try another row & col");
                players.addFirst(currPlayerTurn);
                continue;
            }
            players.addLast(currPlayerTurn);

            boolean winner = isThereWinner(row, col, currPlayerTurn.playingPiece);
            if (winner) {
                return currPlayerTurn.getName();
            }
        }

        return "Tie";
    }

    private boolean isThereWinner(int row, int col, PlayingPiece playingPiece) {
        boolean rowFilled = true;
        boolean colFilled = true;
        boolean diagonalFilled = true;
        boolean antiDiagonalFilled = true;


        for (int i=0; i<gameBoard.size; i++) {
            if (gameBoard.board[row][i] == null || gameBoard.board[row][i] != playingPiece) {
                rowFilled = false;
                break;
            }
        }

        for (int j=0; j<gameBoard.size; j++) {
            if (gameBoard.board[j][col] == null || gameBoard.board[j][col] != playingPiece) {
                colFilled = false;
                break;
            }
        }

        for (int i=0, j=0; i<gameBoard.size; i++, j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j] != playingPiece) {
                diagonalFilled = false;
                break;
            }
        }

        for (int i=0, j=gameBoard.size-1; i<gameBoard.size; i++, j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j] != playingPiece) {
                antiDiagonalFilled = false;
                break;
            }
        }

        return rowFilled || colFilled || diagonalFilled || antiDiagonalFilled;
    }
}
