package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator implements PieceMoveCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int myRow = position.getRow();
        int myCol = position.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int direction = (myColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int[] shift = {-1, 0, 1};
        for (int i : shift) {
            ChessPosition endPosition = new ChessPosition(myRow + direction, myCol + i);
            ChessMove nextMove = new ChessMove(position, endPosition, null);
            if (checkPromotion(board, nextMove)) {
                if (checkMove(board, nextMove)) {
                    for (ChessPiece.PieceType promotionPiece : ChessPiece.PieceType.values()) {
                        if (promotionPiece != ChessPiece.PieceType.KING && promotionPiece != ChessPiece.PieceType.PAWN) {
                            ChessMove promotionMove = new ChessMove(position, endPosition, promotionPiece);
                            moveList.add(promotionMove);
                        }
                    }
                }
            } else {
                if (checkMove(board, nextMove)) {
                    moveList.add(nextMove);
                }
            }
        }
        // starting move
        ChessPosition startingMovePosition = new ChessPosition(myRow + direction + direction, myCol);
        ChessMove startingMove = new ChessMove(position, startingMovePosition, null);
        if (checkMove(board, startingMove)) {
            moveList.add(startingMove);
        }
        return moveList;
    }

    @Override
    public boolean checkMove(ChessBoard board, ChessMove move) {
        ChessPosition endPosition = move.getEndPosition();
        int endRow = endPosition.getRow();
        if (endRow > 8 || endRow < 1) {
            return false;
        }
        int endCol = endPosition.getColumn();
        if (endCol > 8 || endCol < 1) {
            return false;
        }
        // if straight on
        ChessPosition startPosition = move.getStartPosition();
        int myRow = startPosition.getRow();
        int myCol = startPosition.getColumn();
        ChessGame.TeamColor myColor = board.getPiece(startPosition).getTeamColor();
        int direction = (myColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
        if (myCol == endCol) {
            if (endRow == myRow + direction) {
                return !isOccupied(board, endPosition); // if move is 1 tile straight ahead good if empty
            } if (endRow == myRow + direction + direction) {
                if (myRow == 2 || myRow == 7) {
                    ChessPosition inBetweenPosition = new ChessPosition(endRow - direction, endCol);
                    if (!isOccupied(board, inBetweenPosition) && !isOccupied(board, endPosition)) {
                        return true;
                    }
                }
            }
        }
        // if diagonal
        if (endCol == myCol + 1 || endCol == myCol - 1) {
            return isOccupied(board, endPosition) && board.getPiece(endPosition).getTeamColor() != myColor;
        }
    return false;
    }

    private boolean checkPromotion(ChessBoard board, ChessMove move) {
        ChessPosition endPosition = move.getEndPosition();
        int endRow = endPosition.getRow();
        ChessPosition startPosition = move.getStartPosition();
        ChessGame.TeamColor myColor = board.getPiece(startPosition).getTeamColor();
        if (myColor == ChessGame.TeamColor.WHITE && endRow == 8 || myColor == ChessGame.TeamColor.BLACK && endRow == 1) {
            if (!isOccupied(board, endPosition)) {
                return true;
            } else {
                return board.getPiece(endPosition).getTeamColor() != myColor;
            }
        }
        return false;
    }
}
