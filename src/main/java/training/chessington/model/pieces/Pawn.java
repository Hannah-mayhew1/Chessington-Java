package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();
        int offset = colour == PlayerColour.WHITE ? -1 : 1;
        int startPosition = colour == PlayerColour.WHITE ? 6 : 1;

        Coordinates oneSquareAhead = from.plus(offset, 0);

        Move move = new Move(from, from.plus(offset, 0));
        if (MoveUtilities.moveIsAllowed(move, board, this) && board.get(oneSquareAhead) == null) {
            allowedMoves.add(move);
        }

        Coordinates twoSquaresAhead = from.plus(offset * 2, 0);

        move = new Move(from, from.plus(offset * 2, 0 ));
        if (MoveUtilities.moveIsAllowed(move, board, this) && board.get(oneSquareAhead) == null && board.get(twoSquaresAhead) == null && from.getRow() == startPosition) {
            allowedMoves.add(move);
        }

        Move captureRight = new Move(from, from.plus(offset, 1));
        if (MoveUtilities.moveIsAllowed(captureRight, board, this) && board.get(captureRight.getTo()) != null) {
            allowedMoves.add(captureRight);
        }

        Move captureLeft = new Move(from, from.plus(offset, -1));
        if (MoveUtilities.moveIsAllowed(captureLeft, board, this) && board.get(captureLeft.getTo()) != null) {
            allowedMoves.add(captureLeft);
        }
        return allowedMoves;
    }
}
