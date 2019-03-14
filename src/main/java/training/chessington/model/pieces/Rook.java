package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();

//        List<Move> potentialMoves = Arrays.asList(
//            new Move(from, from.plus(1, 0)),
//            new Move(from, from.plus(2, 0)),
//            new Move(from, from.plus(3, 0)),
//            new Move(from, from.plus(4, 0)),
//            new Move(from, from.plus(5, 0)),
//            new Move(from, from.plus(6, 0)),
//            new Move(from, from.plus(7, 0)),
//            new Move(from, from.plus(0, 1)),
//            new Move(from, from.plus(0, 2)),
//            new Move(from, from.plus(0, 3)),
//            new Move(from, from.plus(0, 4)),
//            new Move(from, from.plus(0, 5)),
//            new Move(from, from.plus(0, 6)),
//            new Move(from, from.plus(0, 7)),
//            new Move(from, from.plus(-1, 0)),
//            new Move(from, from.plus(-2, 0)),
//            new Move(from, from.plus(-3, 0)),
//            new Move(from, from.plus(-4, 0)),
//            new Move(from, from.plus(-5, 0)),
//            new Move(from, from.plus(-6, 0)),
//            new Move(from, from.plus(-7, 0)),
//            new Move(from, from.plus(0, -1)),
//            new Move(from, from.plus(0, -2)),
//            new Move(from, from.plus(0, -3)),
//            new Move(from, from.plus(0, -4)),
//            new Move(from, from.plus(0, -5)),
//            new Move(from, from.plus(0, -6)),
//            new Move(from, from.plus(0, -7))
//        );
//
//        for (Move move : potentialMoves) {
//            if (moveIsInBounds(move, board)) {
//                allowedMoves.add(move);
//            }
//        }

        for (int upDistance = 1; upDistance < 8; upDistance++) {
            Move move = new Move(from, from.plus(-upDistance, 0));
            if (moveIsInBounds(move, board)) {
                Piece pieceOnDestination = board.get(move.getTo());
                if (pieceOnDestination == null) {
                    allowedMoves.add(move);
                } else if (pieceOnDestination.getColour() != colour) {
                    allowedMoves.add(move);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int downDistance = 1; downDistance < 8; downDistance++) {
            Move move = new Move(from, from.plus(+downDistance, 0));
            if (moveIsInBounds(move, board)) {
                Piece pieceOnDestination = board.get(move.getTo());
                if (pieceOnDestination == null) {
                    allowedMoves.add(move);
                } else if (pieceOnDestination.getColour() != colour) {
                    allowedMoves.add(move);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int rightDistance = 1; rightDistance < 8; rightDistance++) {
            Move move = new Move(from, from.plus(0, +rightDistance));
            if (moveIsInBounds(move, board)) {
                Piece pieceOnDestination = board.get(move.getTo());
                if (pieceOnDestination == null) {
                    allowedMoves.add(move);
                } else if (pieceOnDestination.getColour() != colour) {
                    allowedMoves.add(move);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        for (int leftDistance = 1; leftDistance < 8; leftDistance++) {
            Move move = new Move(from, from.plus(0, -leftDistance));
            if (moveIsInBounds(move, board)) {
                Piece pieceOnDestination = board.get(move.getTo());
                if (pieceOnDestination == null) {
                    allowedMoves.add(move);
                } else if (pieceOnDestination.getColour() != colour) {
                    allowedMoves.add(move);
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return allowedMoves;
    }

    private boolean moveIsInBounds(Move move, Board board) {
        boolean moveIsAllowed = false;

        if (move.getTo().getRow() >= 0 && move.getTo().getRow() <= 7 && move.getTo().getCol() >= 0 && move.getTo().getCol() <= 7) {
            moveIsAllowed = true;
        }

        return moveIsAllowed;
    }
}
