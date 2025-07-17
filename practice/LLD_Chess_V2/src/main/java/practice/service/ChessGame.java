package practice.service;

import practice.constants.GameStatus;
import practice.constants.PieceType;
import practice.modal.Board;
import practice.modal.Move;
import practice.modal.Player;
import practice.utils.Utils;

import java.util.Deque;
import java.util.Scanner;

public class ChessGame implements Games {
    Deque<Player> players;
    Board gameBoard;
    GameStatus gameStatus;
    Scanner scanner;


    public ChessGame(Deque<Player> players) {
        this.players = players;
        this.gameBoard = Board.getBoardInstance();
        this.gameStatus = GameStatus.START;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void play() {
        gameStatus = GameStatus.IN_PROGRESS;
        while (gameStatus == GameStatus.IN_PROGRESS) {
            Player currPlayer = players.removeFirst();
            gameBoard.print();

            System.out.println(currPlayer.getName() + "Choose a move");
            Move move = Utils.getPlayersMove(scanner, gameBoard, currPlayer.isWhitePiece());

            if (move.getPiece().getPiecePlayStrategy().isValidMove(move.getFrom(), move.getTo())) {
                System.out.println("This is an invalid move for this type of piece");
                players.addFirst(currPlayer);
                continue;
            }

            if (move.getTo().getPiece().getChessPiece() == PieceType.KING) {
                currPlayer.playMove(move, gameBoard);
                System.out.println(currPlayer.getName() + "WON");
            } else {
                currPlayer.playMove(move, gameBoard);
            }

            players.addLast(currPlayer);
        }

    }
}
