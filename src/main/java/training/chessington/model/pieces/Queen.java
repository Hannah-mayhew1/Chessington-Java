package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Game game) {
        List<Move> queenMoves = new ArrayList<>();
        List<Move> diagonalMoves = MoveUtilities.getDiagonalMoves(from,game.getBoard(), this);
        List<Move> verticalAndHorizontalMoves = MoveUtilities.getVerticalAndHorizontalMoves(from, game.getBoard(), this);

        queenMoves.addAll(diagonalMoves);
        queenMoves.addAll(verticalAndHorizontalMoves);

        return queenMoves;

    }
}
