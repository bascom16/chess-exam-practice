package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMoveCalculator implements PieceMoveCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int[] direction = {1, -1};
        int myRow = position.getRow();
        int myCol = position.getColumn();
        for (int i : direction) {
            int steps = myCol;
            while (true) {
                ChessPosition endPosition = new ChessPosition(myRow, steps);
                ChessMove nextMove = new ChessMove(position, endPosition, null);
                if (checkMove(board, nextMove)) {
                    moveList.add(nextMove);
                } else {
                    break;
                }
                steps += i;
            }
        }
        for (int j : direction) {
            int steps = myRow;
            while (true) {
                ChessPosition endPosition = new ChessPosition(steps, myCol);
                ChessMove nextMove = new ChessMove(position, endPosition, null);
                if (checkMove(board, nextMove)) {
                    moveList.add(nextMove);
                } else {
                    break;
                }
                steps += j;
            }
        }
        return moveList;
    }
}
