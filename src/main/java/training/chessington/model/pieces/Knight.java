package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();
        List<Move> potentialMoves = Arrays.asList(
            new Move(from, from.plus(-2, 1)),
            new Move(from, from.plus(-1, 2)),
            new Move(from, from.plus(1, 2)),
            new Move(from, from.plus(2, 1)),
            new Move(from, from.plus(2, -1)),
            new Move(from, from.plus(1, -2)),
            new Move(from, from.plus(-1, -2)),
            new Move(from, from.plus(-2, -1))
        );

        for (Move move : potentialMoves) {
            if (moveIsAllowed(move, board)) {
                allowedMoves.add(move);
            }
        }
        return allowedMoves;
    }

    private boolean moveIsAllowed(Move move, Board board) {
        boolean moveIsAllowed = false;
        if (move.getTo().getRow() >= 0 && move.getTo().getRow() <= 7 && move.getTo().getCol() >= 0 && move.getTo().getCol() <= 7) {
            if (board.get(move.getTo()) == null || board.get(move.getTo()).getColour() != colour) {
                moveIsAllowed = true;
            }
        }
        return moveIsAllowed;
    }
}
