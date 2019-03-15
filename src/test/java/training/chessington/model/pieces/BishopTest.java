package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {
    @Test
    public void bishopCanMoveAcrossAllSquaresDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates bishopCoords = new Coordinates(4, 4);
        board.placePiece(bishopCoords, bishop);

        //Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        //Asserts
        assertThat(moves).containsExactlyInAnyOrder(
                new Move(bishopCoords, bishopCoords.plus(-1, -1)),
                new Move(bishopCoords, bishopCoords.plus(-2, -2)),
                new Move(bishopCoords, bishopCoords.plus(-3, -3)),
                new Move(bishopCoords, bishopCoords.plus(-4, -4)),
                new Move(bishopCoords, bishopCoords.plus(1, 1)),
                new Move(bishopCoords, bishopCoords.plus(2, 2)),
                new Move(bishopCoords, bishopCoords.plus(3, 3)),
                new Move(bishopCoords, bishopCoords.plus(-1, 1)),
                new Move(bishopCoords, bishopCoords.plus(-2, 2)),
                new Move(bishopCoords, bishopCoords.plus(-3, 3)),
                new Move(bishopCoords, bishopCoords.plus(1, -1)),
                new Move(bishopCoords, bishopCoords.plus(2, -2)),
                new Move(bishopCoords, bishopCoords.plus(3, -3))
        );
    }
    @Test
    public void bishopCannotEnterSpaceOccupiedByOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates bishopCoords = new Coordinates(4, 4);
        board.placePiece(bishopCoords, bishop);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(6, 2);
        board.placePiece(pawnCoords, pawn);

        //Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        //Asserts
        assertThat(moves).doesNotContain(new Move(bishopCoords, bishopCoords.plus(2, -2)));

    }

    @Test
    public void bishopCanTakeOpponentPieceInSpace() {
        //Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates bishopCoords = new Coordinates(4, 4);
        board.placePiece(bishopCoords, bishop);

        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates pawnCoords = new Coordinates(2, 6);
        board.placePiece(pawnCoords, pawn);

        //Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        //Asserts
        assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(-2, 2)));

    }

    @Test
    public void bishopCannotMoveThroughOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates bishopCoords = new Coordinates(4, 4);
        board.placePiece(bishopCoords, bishop);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(2, 2);
        board.placePiece(pawnCoords, pawn);

        //Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        //Asserts
        assertThat(moves).doesNotContain(new Move(bishopCoords, bishopCoords.plus(-4, -4)));
        assertThat(moves).doesNotContain(new Move(bishopCoords, bishopCoords.plus(-3, -3)));
        assertThat(moves).doesNotContain(new Move(bishopCoords, bishopCoords.plus(-2, -2)));
        assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(-1, -1)));
        assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(1, 1)));
        assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(2, 2)));
        assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(3, 3)));
    }

}
