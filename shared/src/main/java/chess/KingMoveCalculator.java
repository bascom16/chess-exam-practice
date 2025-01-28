package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMoveCalculator implements PieceMoveCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int myRow = position.getRow();
        int myCol = position.getColumn();
        int[] direction = {1, 0, -1};
        for (int i : direction) {
            for (int j : direction) {
                if (!(i == 0 && j == 0)) {
                    ChessPosition endPosition = new ChessPosition(myRow + i, myCol + j);
                    ChessMove nextMove = new ChessMove(position, endPosition, null);
                    if (checkMove(board, nextMove)) {
                        moveList.add(nextMove);
                    }
                }
            }
        }
        return moveList;
    }
}
