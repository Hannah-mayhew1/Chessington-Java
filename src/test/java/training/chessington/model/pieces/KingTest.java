package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.*;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {
    @Test
    public void kingCanMoveOneSquaresHorizontallyAndVerticallyAndDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates kingCoords = new Coordinates(4, 4);
        board.placePiece(kingCoords, king);
        Game game = new Game(board);

        //Act
        List<Move> moves = king.getAllowedMoves(kingCoords, game);

        //Asserts
        assertThat(moves).containsExactlyInAnyOrder(
                new Move(kingCoords, kingCoords.plus(-1, 0)),
                new Move(kingCoords, kingCoords.plus(1, 0)),
                new Move(kingCoords, kingCoords.plus(0, -1)),
                new Move(kingCoords, kingCoords.plus(0, 1)),
                new Move(kingCoords, kingCoords.plus(-1, -1)),
                new Move(kingCoords, kingCoords.plus(1, 1)),
                new Move(kingCoords, kingCoords.plus(-1, 1)),
                new Move(kingCoords, kingCoords.plus(1, -1))
        );
    }
    @Test
    public void kingCannotEnterSpaceOccupiedByOwnPiece() {
        //Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates kingCoords = new Coordinates(4, 4);
        board.placePiece(kingCoords, king);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(5, 4);
        board.placePiece(pawnCoords, pawn);
        Game game = new Game(board);

        //Act
        List<Move> moves = king.getAllowedMoves(kingCoords, game);

        //Asserts
        assertThat(moves).doesNotContain(new Move(kingCoords, kingCoords.plus(1, 0)));
    }
    @Test
    public void kingCanTakeOpponentPieceInSpace() {
        //Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates kingCoords = new Coordinates(4, 3);
        board.placePiece(kingCoords, king);

        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates pawnCoords = new Coordinates(4, 2);
        board.placePiece(pawnCoords, pawn);
        Game game = new Game(board);

        //Act
        List<Move> moves = king.getAllowedMoves(kingCoords, game);

        //Asserts
        assertThat(moves).contains(new Move(kingCoords, kingCoords.plus(0, -1)));

    }
}
