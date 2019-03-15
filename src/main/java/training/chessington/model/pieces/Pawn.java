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




        /*if (colour.equals(PlayerColour.WHITE) && from.getRow() != 0) {
            if (from.getCol() != 7) {
                Piece diagRight = board.get(from.plus(-1, 1));
                if (diagRight != null && diagRight.getColour() == PlayerColour.BLACK) {
                    allowedMoves.add(new Move(from, from.plus(-1, 1)));
                }
            }
            if (from.getCol() != 0) {
                Piece diagLeft = board.get(from.plus(-1, -1));
                if (diagLeft != null && diagLeft.getColour() == PlayerColour.BLACK) {
                    allowedMoves.add(new Move(from, from.plus(-1, -1)));
                }
            }
            if (board.get(from.plus(-1, 0)) == null) { // if space in front is empty
                if (from.getRow() == 6) {
                    if (board.get(from.plus(-2,0)) == null) {
                        allowedMoves.add(new Move(from, from.plus(-2, 0)));
                    }
                }
                allowedMoves.add(new Move(from, from.plus(-1, 0)));
            }
        }

        if (colour.equals(PlayerColour.BLACK) && from.getRow() != 7) {
            if (from.getCol() != 7) {
                Piece diagRight = board.get(from.plus(1, 1));
                if (diagRight != null && diagRight.getColour() == PlayerColour.WHITE) {
                    allowedMoves.add(new Move(from, from.plus(1, 1)));
                }
            }
            if (from.getCol() != 0) {
                Piece diagLeft = board.get(from.plus(1, -1));
                if (diagLeft != null && diagLeft.getColour() == PlayerColour.WHITE) {
                    allowedMoves.add(new Move(from, from.plus(1, -1)));
                }
            }
            if (board.get(from.plus(1, 0)) == null) {
                if (board.get(from.plus(2,0)) == null) {
                    if (from.getRow() == 1) {
                        allowedMoves.add(new Move(from, from.plus(2, 0)));
                    }
                }
                allowedMoves.add(new Move(from, from.plus(1, 0)));
            }
        }*/
        return allowedMoves;
    }
}
