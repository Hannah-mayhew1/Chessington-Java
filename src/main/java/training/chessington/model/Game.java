package training.chessington.model;

import training.chessington.model.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int SIZE = 8;
    private final Board board;

    private PlayerColour nextPlayer = PlayerColour.WHITE;

    public List<Move> playedMoves = new ArrayList<>();

    public List<Move> getPlayedMoves() {
        return playedMoves;
    }

    private boolean isEnded = false;

    public Game(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Piece pieceAt(int row, int col) {
        return board.get(new Coordinates(row, col));
    }

    public List<Move> getAllowedMoves(Coordinates from) {
        if (isEnded) {
            return new ArrayList<>();
        }

        Piece piece = board.get(from);
        if (piece == null || piece.getColour() != nextPlayer) {
            return new ArrayList<>();
        }

        return piece.getAllowedMoves(from, this);
    }

    public void makeMove(Move move) throws InvalidMoveException {
        if (isEnded) {
            throw new InvalidMoveException("Game has ended!");
        }

        Coordinates from = move.getFrom();
        Coordinates to = move.getTo();

        Piece piece = board.get(from);
        if (piece == null) {
            throw new InvalidMoveException(String.format("No piece at %s", from));
        }

        if (piece.getColour() != nextPlayer) {
            throw new InvalidMoveException(String.format("Wrong colour piece - it is %s's turn", nextPlayer));
        }

        if (!piece.getAllowedMoves(move.getFrom(), this).contains(move)) {
            throw new InvalidMoveException(String.format("Cannot move piece %s from %s to %s", piece, from, to));
        }

        checkForEnPassantMove(move);
        board.move(from, to);
        playedMoves.add(move);
        nextPlayer = nextPlayer == PlayerColour.WHITE ? PlayerColour.BLACK : PlayerColour.WHITE;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public String getResult() {
        return null;
    }

    public void checkForEnPassantMove(Move move) {
        if (!this.getPlayedMoves().isEmpty()) {
            Move lastMove = this.getPlayedMoves().get(this.getPlayedMoves().size() - 1);
            Piece lastPiece = this.getBoard().get(lastMove.getTo());
            if (lastPiece.getType() == Piece.PieceType.PAWN) {
                if (Math.abs(lastMove.getTo().getRow() - lastMove.getFrom().getRow()) == 2) {
                    int offset = lastPiece.getColour() == PlayerColour.BLACK ? 1 : -1;
                    Coordinates middleSquare = lastMove.getFrom().plus(offset, 0);
                    if (move.getTo().equals(middleSquare)) {
                        board.removePiece(lastMove.getTo());
                    }
                }
            }
        }

    }
}
