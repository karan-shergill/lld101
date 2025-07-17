package practice.strategy;

import practice.modal.Cell;

public interface PiecePlayStrategy {
    public boolean isValidMove(Cell from, Cell to);
}
