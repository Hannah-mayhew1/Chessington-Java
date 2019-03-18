package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.*;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {
    @Test
    public void rookCanMoveAcrossAllSquaresHorizontallyAndVertically() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(2, 3);
        board.placePiece(rookCoords, rook);
        Game game = new Game(board);

        //Act
        List<Move> moves = rook.getAllowedMoves(rookCoords, game);

        //Asserts
        assertThat(moves).containsExactlyInAnyOrder(
                new Move(rookCoords, rookCoords.plus(-1, 0)),
                new Move(rookCoords, rookCoords.plus(-2, 0)),
                new Move(rookCoords, rookCoords.plus(1, 0)),
                new Move(rookCoords, rookCoords.plus(2, 0)),
                new Move(rookCoords, rookCoords.plus(3, 0)),
                new Move(rookCoords, rookCoords.plus(4, 0)),
                new Move(rookCoords, rookCoords.plus(5, 0)),
                new Move(rookCoords, rookCoords.plus(0, -3)),
                new Move(rookCoords, rookCoords.plus(0, -2)),
                new Move(rookCoords, rookCoords.plus(0, -1)),
                new Move(rookCoords, rookCoords.plus(0, 1)),
                new Move(rookCoords, rookCoords.plus(0, 2)),
                new Move(rookCoords, rookCoords.plus(0, 3)),
                new Move(rookCoords, rookCoords.plus(0, 4))
        );
    }

    @Test
    public void rookCannotEnterSpaceOccupiedByOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(7, 0);
        board.placePiece(rookCoords, rook);

        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(7, 1);
        board.placePiece(knightCoords, knight);
        Game game = new Game(board);

        //Act
        List<Move> moves = knight.getAllowedMoves(rookCoords, game);

        //Asserts
        assertThat(moves).doesNotContain(new Move(rookCoords, rookCoords.plus(0, 1)));
    }

    @Test
    public void rookCanTakeOpponentPieceInSpace() {
        //Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(7, 0);
        board.placePiece(rookCoords, rook);

        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates knightCoords = new Coordinates(7, 1);
        board.placePiece(knightCoords, knight);
        Game game = new Game(board);

        //Act
        List<Move> moves = knight.getAllowedMoves(rookCoords, game);

        //Asserts
        assertThat(moves).doesNotContain(new Move(rookCoords, knightCoords));
    }

    @Test
    public void rookCannotMoveThroughOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(7, 0);
        board.placePiece(rookCoords, rook);

        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(4, 0);
        board.placePiece(knightCoords, knight);
        Game game = new Game(board);

        //Act
        List<Move> moves = rook.getAllowedMoves(rookCoords, game);

        //Asserts
        assertThat(moves).doesNotContain(new Move(rookCoords, rookCoords.plus(-3, 0)));
        assertThat(moves).doesNotContain(new Move(rookCoords, rookCoords.plus(-4, 0)));
        assertThat(moves).doesNotContain(new Move(rookCoords, rookCoords.plus(-5, 0)));
        assertThat(moves).doesNotContain(new Move(rookCoords, rookCoords.plus(-6, 0)));
        assertThat(moves).doesNotContain(new Move(rookCoords, rookCoords.plus(-7, 0)));
        assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(-1, 0)));
        assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(-2, 0)));
    }
}
