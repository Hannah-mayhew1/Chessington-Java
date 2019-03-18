package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.*;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class QueenTest {
    @Test
    public void queenCanMoveAcrossAllSquaresHorizontallyAndVerticallyAndDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(4, 3);
        board.placePiece(queenCoords, queen);
        Game game = new Game(board);

        //Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        //Asserts
        assertThat(moves).containsExactlyInAnyOrder(
                new Move(queenCoords, queenCoords.plus(-1, 0)),
                new Move(queenCoords, queenCoords.plus(-2, 0)),
                new Move(queenCoords, queenCoords.plus(-3, 0)),
                new Move(queenCoords, queenCoords.plus(-4, 0)),
                new Move(queenCoords, queenCoords.plus(1, 0)),
                new Move(queenCoords, queenCoords.plus(2, 0)),
                new Move(queenCoords, queenCoords.plus(3, 0)),
                new Move(queenCoords, queenCoords.plus(0, -3)),
                new Move(queenCoords, queenCoords.plus(0, -2)),
                new Move(queenCoords, queenCoords.plus(0, -1)),
                new Move(queenCoords, queenCoords.plus(0, 1)),
                new Move(queenCoords, queenCoords.plus(0, 2)),
                new Move(queenCoords, queenCoords.plus(0, 3)),
                new Move(queenCoords, queenCoords.plus(0, 4)),
                new Move(queenCoords, queenCoords.plus(-1, -1)),
                new Move(queenCoords, queenCoords.plus(-2, -2)),
                new Move(queenCoords, queenCoords.plus(-3, -3)),
                new Move(queenCoords, queenCoords.plus(1, 1)),
                new Move(queenCoords, queenCoords.plus(2, 2)),
                new Move(queenCoords, queenCoords.plus(3, 3)),
                new Move(queenCoords, queenCoords.plus(-1, 1)),
                new Move(queenCoords, queenCoords.plus(-2, 2)),
                new Move(queenCoords, queenCoords.plus(-3, 3)),
                new Move(queenCoords, queenCoords.plus(-4, 4)),
                new Move(queenCoords, queenCoords.plus(1, -1)),
                new Move(queenCoords, queenCoords.plus(2, -2)),
                new Move(queenCoords, queenCoords.plus(3, -3))
        );
    }

    @Test
    public void queenCannotEnterSpaceOccupiedByOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(4, 3);
        board.placePiece(queenCoords, queen);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(2, 5);
        board.placePiece(pawnCoords, pawn);
        Game game = new Game(board);

        //Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        //Asserts
        assertThat(moves).doesNotContain(new Move(queenCoords, queenCoords.plus(-2, 2)));

    }

    @Test
    public void queenCanTakeOpponentPieceInSpace() {
        //Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(4, 3);
        board.placePiece(queenCoords, queen);

        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates pawnCoords = new Coordinates(2, 1);
        board.placePiece(pawnCoords, pawn);
        Game game = new Game(board);

        //Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        //Asserts
        assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-2, -2)));

    }

    @Test
    public void queenCannotMoveThroughOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(4, 3);
        board.placePiece(queenCoords, queen);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(3, 4);
        board.placePiece(pawnCoords, pawn);
        Game game = new Game(board);

        //Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, game);

        //Asserts
        assertThat(moves).doesNotContain(new Move(queenCoords, queenCoords.plus(-1, 1)));
        assertThat(moves).doesNotContain(new Move(queenCoords, queenCoords.plus(-2, 2)));
        assertThat(moves).doesNotContain(new Move(queenCoords, queenCoords.plus(-3, 3)));
        assertThat(moves).doesNotContain(new Move(queenCoords, queenCoords.plus(-4, 4)));
        assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-1, -1)));
        assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-2, -2)));
        assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-3, -3)));
    }


}
