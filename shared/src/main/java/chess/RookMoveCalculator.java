package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMoveCalculator implements PieceMoveCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int[] direction = {1, -1};
        int myRow = position.getRow();
        int myCol = position.getColumn();
        for (int i : direction) {
            int shift = i;
            while (true) {
                ChessPosition endPosition = new ChessPosition(myRow, myCol + shift);
                ChessMove nextMove = new ChessMove(position, endPosition, null);
                if (checkMove(board, nextMove)) {
                    moveList.add(nextMove);
                    if (isOccupied(board, endPosition)) {
                        break;
                    }
                } else {
                    break;
                }
                shift += i;
            }
        }
        for (int j : direction) {
            int shift = j;
            while (true) {
                ChessPosition endPosition = new ChessPosition(myRow + shift, myCol);
                ChessMove nextMove = new ChessMove(position, endPosition, null);
                if (checkMove(board, nextMove)) {
                    moveList.add(nextMove);
                    if (isOccupied(board, endPosition)) {
                        break;
                    }
                } else {
                    break;
                }
                shift += j;
            }
        }
        return moveList;
    }
}
