package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        return MoveUtilities.getVerticalAndHorizontalMoves(from, board, this);
    }
}
