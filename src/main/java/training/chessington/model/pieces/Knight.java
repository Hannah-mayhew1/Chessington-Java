package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Game game) {
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
            if (MoveUtilities.moveIsAllowed(move, game.getBoard(), this)) {
                allowedMoves.add(move);
            }
        }
        return allowedMoves;
    }
}
