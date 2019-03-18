package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.*;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KnightTest {

    @Test
    public void whiteKnightCanMoveInLShape() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(5, 2);
        board.placePiece(knightCoords, knight);
        Game game = new Game(board);

        //Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, game);

        //Asserts
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(-2, 1)));
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(-1, 2)));
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(1, 2)));
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(2, 1)));
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(2, -1)));
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(1, -2)));
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(-1, -2)));
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(-2, -1)));
    }
    @Test
    public void whiteKnightCanNotBeMovedOffLeftOrBottomOfBoard() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(7, 1);
        board.placePiece(knightCoords, knight);
        Game game = new Game(board);

        //Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, game);

        //Asserts
        assertThat(moves).containsExactlyInAnyOrder(
            new Move(knightCoords, knightCoords.plus(-2, -1)),
            new Move(knightCoords, knightCoords.plus(-2, 1)),
            new Move(knightCoords, knightCoords.plus(-1, 2))
        );
    }

    @Test
    public void blackKnightCanNotBeMovedOffTopOrRightOfBoard() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(0, 6);
        board.placePiece(knightCoords, knight);
        Game game = new Game(board);

        //Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, game);

        //Asserts
        assertThat(moves).containsExactlyInAnyOrder(
                new Move(knightCoords, knightCoords.plus(2, 1)),
                new Move(knightCoords, knightCoords.plus(2, -1)),
                new Move(knightCoords, knightCoords.plus(1, -2))
        );
    }

    @Test
    public void knightCannotEnterSpaceOccupiedByOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(7, 1);
        board.placePiece(knightCoords, knight);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(6, 3);
        board.placePiece(pawnCoords, pawn);
        Game game = new Game(board);

        //Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, game);

        //Asserts
        assertThat(moves).doesNotContain(new Move(knightCoords, knightCoords.plus(-1, 2)));

    }

    @Test
    public void knightCanTakeOpponentPieceInSpace() {
        //Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(5, 2);
        board.placePiece(knightCoords, knight);

        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates pawnCoords = new Coordinates(3, 3);
        board.placePiece(pawnCoords, pawn);
        Game game = new Game(board);

        //Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, game);

        //Asserts
        assertThat(moves).contains(new Move(knightCoords, knightCoords.plus(-2, 1)));

    }
}
