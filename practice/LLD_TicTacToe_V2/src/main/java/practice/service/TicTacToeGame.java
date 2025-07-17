package practice.service;

import practice.constants.GameStatus;
import practice.models.Board;
import practice.models.Cell;
import practice.models.Player;

import java.util.Deque;

public class TicTacToeGame implements Games {
    Deque<Player> players;
    Board board;
    GameStatus gameStatus;

    public TicTacToeGame(Deque<Player> players, Board board) {
        this.players = players;
        this.board = board;
        this.gameStatus = GameStatus.START;
    }

    @Override
    public void play() {
        gameStatus = GameStatus.IN_PROGRESS;

        while (gameStatus == GameStatus.IN_PROGRESS) {
            Player currPlayer = players.removeFirst();

            board.displayBoard();

            // Take input from player via scanner
            // As practice, defining row col to 0
            int row = 0;
            int col = 0;
            Cell cell = new Cell(row, col);

            boolean movePlayed = board.playMove(cell, currPlayer.getPlayingSymbol());

            if (!movePlayed) {
                System.out.println("You need to choose a row & col again!");
                players.addFirst(currPlayer);
                continue;
            }
            players.addLast(currPlayer);

            if (board.isWon(cell, currPlayer.getPlayingSymbol())) {
                System.out.println(currPlayer.getName() + " WON!");
                break;
            }

            if (board.isBoardFull()) {
                gameStatus = GameStatus.TIE;
                break;
            }
        }

        if (gameStatus == GameStatus.TIE) {
            System.out.println("GAME TIE!");
        }
    }
}
