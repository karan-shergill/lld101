package Chess_V1.movementStrategy.concreteMovementStrategies;

import Chess_V1.utils.Board;
import Chess_V1.utils.Cell;
import Chess_V1.movementStrategy.MovementStrategy;

public class PawnMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int rowDiff = endCell.getRow() - startCell.getRow();
        int colDiff = Math.abs(endCell.getCol() - startCell.getCol());
        
        boolean isWhite = startCell.getPiece().isPieceWhite();
        boolean isDestinationEmpty = endCell.getPiece() == null;
        
        // White pawns move up (decrease row), black pawns move down (increase row)
        int direction = isWhite ? -1 : 1;
        
        // Forward movement (1 square)
        if (rowDiff == direction && colDiff == 0 && isDestinationEmpty) {
            return true;
        }
        
        // Initial 2-square move
        if (isInitialPosition(startCell, isWhite) && 
            rowDiff == 2 * direction && colDiff == 0 && isDestinationEmpty) {
            return true;
        }
        
        // Diagonal capture
        if (rowDiff == direction && colDiff == 1 && !isDestinationEmpty) {
            return true;
        }
        
        return false;
    }
    
    private boolean isInitialPosition(Cell startCell, boolean isWhite) {
        return (isWhite && startCell.getRow() == 6) || (!isWhite && startCell.getRow() == 1);
    }
}
