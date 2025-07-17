package practice.utils;

import practice.modal.Board;
import practice.modal.Move;

import java.util.Scanner;

public class Utils {
    public static Move getPlayersMove(Scanner scanner, Board board, boolean isWhite) {
        while (true) {
            int row1=0, col1=0;
            // todo: get row1, col1 from user that is position of piece player wants to play: FROM
            if (!board.isValidPiecePositionFrom(row1, col1, isWhite)) {
                continue;
            }

            int row2=0, col2=0;
            // todo: get row2, col2 from user that is position of piece player wants to play: TO
            if (!board.isValidPiecePositionTo(row2, col2, isWhite)) {
                continue;
            }

            return new Move(board.getBoard()[row1][col1], board.getBoard()[row2][col2], board.getBoard()[row1][col1].getPiece());
        }

    }
}
