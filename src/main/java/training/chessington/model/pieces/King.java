package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Game game) {
        List<Move> allowedKingMoves = new ArrayList<>();
        List<Move> potentialKingMoves = Arrays.asList(
                new Move(from, from.plus(-1, 1)),
                new Move(from, from.plus(0, 1)),
                new Move(from, from.plus(1, 1)),
                new Move(from, from.plus(1, -1)),
                new Move(from, from.plus(-1, -1)),
                new Move(from, from.plus(-1, 0)),
                new Move(from, from.plus(0, -1)),
                new Move(from, from.plus(1, 0))
        );

        for (Move move : potentialKingMoves) {
            if (MoveUtilities.moveIsAllowed(move, game.getBoard(), this)) {
                allowedKingMoves.add(move);
            }
        }
        return allowedKingMoves;
    }
}
