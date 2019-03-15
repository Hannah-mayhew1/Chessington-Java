package training.chessington.model;

import training.chessington.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class MoveUtilities {

    public static List<Move> getDiagonalMoves(Coordinates from, Board board, Piece piece) {

        List<Move> allowedMoves = new ArrayList<>();

        testingDestinationIsAllowed(from, allowedMoves, board, piece, +1, +1);

        testingDestinationIsAllowed(from, allowedMoves, board, piece, +1, -1);

        testingDestinationIsAllowed(from, allowedMoves, board, piece, -1, +1);

        testingDestinationIsAllowed(from, allowedMoves, board, piece, -1, -1);

        return allowedMoves;
    }

    public static List<Move> getVerticalAndHorizontalMoves(Coordinates from, Board board, Piece piece) {

        List<Move> allowedMoves = new ArrayList<>();

        testingDestinationIsAllowed(from, allowedMoves, board, piece, -1, 0);

        testingDestinationIsAllowed(from, allowedMoves, board, piece, +1, 0);

        testingDestinationIsAllowed(from, allowedMoves, board, piece, 0, +1);

        testingDestinationIsAllowed(from, allowedMoves, board, piece, 0, -1);

        return allowedMoves;
    }

    public static boolean moveIsAllowed(Move move, Board board, Piece piece) {
        boolean moveIsAllowed = false;
        if (move.getTo().getRow() >= 0 && move.getTo().getRow() <= 7 && move.getTo().getCol() >= 0 && move.getTo().getCol() <= 7) {
            if (board.get(move.getTo()) == null || board.get(move.getTo()).getColour() != piece.getColour()) {
                moveIsAllowed = true;
            }
        }
        return moveIsAllowed;
    }

    private static void testingDestinationIsAllowed(Coordinates from, List<Move> allowedMoves, Board board, Piece piece, int rowDir, int colDir) {
        for (int distance = 1; true; distance++) {
            Move move = new Move(from, from.plus(rowDir * distance, colDir * distance));
            if (moveIsAllowed(move, board, piece)) {
                Piece pieceOnDestination = board.get(move.getTo());
                if (pieceOnDestination == null) {
                    allowedMoves.add(move);
                } else if (pieceOnDestination.getColour() != piece.getColour()) {
                    allowedMoves.add(move);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }
}
