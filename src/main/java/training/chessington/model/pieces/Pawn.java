package training.chessington.model.pieces;

import training.chessington.model.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Game game) {
        List<Move> allowedMoves = new ArrayList<>();
        int offset = colour == PlayerColour.WHITE ? -1 : 1;
        int startPosition = colour == PlayerColour.WHITE ? 6 : 1;
        int fifthRank = colour == PlayerColour.WHITE ? 3 : 4;

        Coordinates oneSquareAhead = from.plus(offset, 0);

        Move move = new Move(from, from.plus(offset, 0));
        if (MoveUtilities.moveIsAllowed(move, game.getBoard(), this) && game.getBoard().get(oneSquareAhead) == null) {
            allowedMoves.add(move);
        }

        Coordinates twoSquaresAhead = from.plus(offset * 2, 0);

        move = new Move(from, from.plus(offset * 2, 0 ));
        if (MoveUtilities.moveIsAllowed(move, game.getBoard(), this) && game.getBoard().get(oneSquareAhead) == null && game.getBoard().get(twoSquaresAhead) == null && from.getRow() == startPosition) {
            allowedMoves.add(move);
        }

        Move captureRight = new Move(from, from.plus(offset, 1));
        //En Passant capture right
        if (!game.getPlayedMoves().isEmpty()) {
            Move lastMove = game.getPlayedMoves().get(game.getPlayedMoves().size() - 1);
            Piece lastPiece = game.getBoard().get(lastMove.getTo());

            if (lastPiece.getType() == PieceType.PAWN && Math.abs(lastMove.getTo().getRow() - lastMove.getFrom().getRow()) == 2
                    && from.getRow() == fifthRank && from.getCol() == lastMove.getTo().getCol() - 1) {
                allowedMoves.add(captureRight);
            }
        }

        if (MoveUtilities.moveIsAllowed(captureRight, game.getBoard(), this) && game.getBoard().get(captureRight.getTo()) != null) {
            allowedMoves.add(captureRight);
        }

        Move captureLeft = new Move(from, from.plus(offset, -1));
        // En Passant capture left
        if (!game.getPlayedMoves().isEmpty()) {
            Move lastMove = game.getPlayedMoves().get(game.getPlayedMoves().size() - 1);
            Piece lastPiece = game.getBoard().get(lastMove.getTo());

            if (lastPiece.getType() == PieceType.PAWN && Math.abs(lastMove.getTo().getRow() - lastMove.getFrom().getRow()) == 2
                    && from.getRow() == fifthRank && from.getCol() == lastMove.getTo().getCol() + 1) {
                allowedMoves.add(captureLeft);
            }
        }

        if (MoveUtilities.moveIsAllowed(captureLeft, game.getBoard(), this) && game.getBoard().get(captureLeft.getTo()) != null) {
            allowedMoves.add(captureLeft);
        }


        return allowedMoves;
    }
}
