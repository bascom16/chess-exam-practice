package chess;

import java.util.Collection;

public interface PieceMoveCalculator {

    Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition  position);

    default boolean checkMove(ChessBoard board, ChessMove move) {
        // Check for out of bounds
        ChessPosition endPosition = move.getEndPosition();
        int row = endPosition.getRow();
        if (row > 8 || row < 1) {
            return false;
        }
        int col = endPosition.getColumn();
        if (col > 8 || col < 1) {
            return false;
        }
        // Check if space is occupied
        if (!isOccupied(board, endPosition)) {
            return true;
        } else {
            return board.getPiece(endPosition).getTeamColor() != board.getPiece(move.getStartPosition()).getTeamColor();
        }
    }

    default boolean isOccupied(ChessBoard board, ChessPosition position) {
        return board.getPiece(position) != null;
    }
}
