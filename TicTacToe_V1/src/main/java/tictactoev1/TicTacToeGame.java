package tictactoev1;

import tictactoev1.model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {
    private Deque<Player> players;
    private PlayingBoard playingBoard;

    public TicTacToeGame() {
        initializeGame();
    }

    private void initializeGame() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Enter size of game board");
        String input = inputScanner.nextLine();
        this.playingBoard = new PlayingBoard(Integer.parseInt(input));

        this.players = new LinkedList<>();

        System.out.println("Enter player 1 name and playing symbol(Input format: name,X/O)");
        input = inputScanner.nextLine();
        String[] player = input.split(",");
        String player1Piece = player[1];
        Player player1 = new Player(player[0],
                player1Piece.equals("X") ? new PlayingPiece(PlayingPieceType.X) :  new PlayingPiece(PlayingPieceType.O));

        System.out.println("Enter player 2 name");
        input = inputScanner.nextLine();
        Player player2 = new Player(input,
                player1Piece.equals("X") ? new PlayingPiece(PlayingPieceType.O) :  new PlayingPiece(PlayingPieceType.X));

        this.players.add(player1);
        this.players.add(player2);
    }

    public String playTicTacToeGame() {
        while (true) {
            Player currPlayerTurn = players.remove();

            playingBoard.printBoard();
            System.out.println(" It's player " + currPlayerTurn.getPlayerName() + " turn. Please choose row & col for your move(Input Format: row,col)");
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            String[] playerInput = input.split(",");
            int row = Integer.parseInt(playerInput[0]);
            int col = Integer.parseInt(playerInput[1]);

            boolean movePlayedSuccessfully = playingBoard.makeMove(currPlayerTurn.getPlayingPiece(), row, col);
            if (!movePlayedSuccessfully) {
                System.out.println(currPlayerTurn.getPlayerName() + ", your selected row, col for the move is incorrect, please select a valid row & col");
                players.addFirst(currPlayerTurn);
                continue;
            }
            players.addLast(currPlayerTurn);

            if (checkIfCurrPlayerIsWinner(currPlayerTurn.getPlayingPiece(), row, col)) {
                inputScanner.close();
                return currPlayerTurn.getPlayerName();
            }

            if (!checkIfPlayerCanPlayNextMove()) {
                break;
            }
        }

        return "TIE";
    }

    private boolean checkIfPlayerCanPlayNextMove() {
        for (int i=0; i<playingBoard.getBoardSize(); i++) {
            for (int j=0; j<playingBoard.getBoardSize(); j++) {
                if (playingBoard.getBoard()[i][j] == null) {
                    return  true;
                }
            }
        }
        return false;
    }

    private boolean checkIfCurrPlayerIsWinner(PlayingPiece playingPiece, int row, int col) {
        boolean rowCheck = true;
        boolean columnCheck = true;
        boolean diagonalCheck = true;
        boolean antiDiagonalCheck = true;

        for (int i=0; i<playingBoard.getBoardSize(); i++) {
            if (playingBoard.getBoard()[row][i] != playingPiece || playingBoard.getBoard()[row][i] == null) {
                rowCheck = false;
                break;
            }
        }

        for (int i=0; i<playingBoard.getBoardSize(); i++) {
            if (playingBoard.getBoard()[i][col] != playingPiece || playingBoard.getBoard()[i][col] == null) {
                columnCheck = false;
                break;
            }
        }

        for (int i=0, j=0; i<playingBoard.getBoardSize(); i++, j++) {
            if (playingBoard.getBoard()[i][j] != playingPiece || playingBoard.getBoard()[i][j] == null) {
                diagonalCheck = false;
                break;
            }
        }

        for (int i=0, j=playingBoard.getBoardSize()-1; i<playingBoard.getBoardSize(); i++, j--) {
            if (playingBoard.getBoard()[i][j] != playingPiece || playingBoard.getBoard()[i][j] == null) {
                antiDiagonalCheck = false;
                break;
            }
        }

        return rowCheck || columnCheck || diagonalCheck || antiDiagonalCheck;
    }
}
