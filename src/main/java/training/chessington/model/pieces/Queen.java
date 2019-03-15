package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> queenMoves = new ArrayList<>();
        List<Move> diagonalMoves = MoveUtilities.getDiagonalMoves(from, board, this);
        List<Move> verticalAndHorizontalMoves = MoveUtilities.getVerticalAndHorizontalMoves(from, board, this);

        queenMoves.addAll(diagonalMoves);
        queenMoves.addAll(verticalAndHorizontalMoves);

        return queenMoves;

    }
}
