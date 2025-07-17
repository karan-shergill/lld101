package practice.strategy;

import practice.modal.Cell;

public class KnightStrategyPiece implements PiecePlayStrategy {
    @Override
    public boolean isValidMove(Cell from, Cell to) {
        return false;
    }
}
