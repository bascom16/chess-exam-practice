package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMoveCalculator implements PieceMoveCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        ArrayList<ChessMove> moveList = new ArrayList<>();
        int myRow = position.getRow();
        int myCol = position.getColumn();
        // Check ++, +-. -+, --;
        int[] direction = {1, -1};
        for (int horizontal : direction) {
            for (int vertical : direction) {
                int i = horizontal;
                int j = vertical;
                while (true) {
                    ChessPosition endPosition = new ChessPosition(myRow + i, myCol + j);
                    ChessMove newMove = new ChessMove(position, endPosition, null);
                    if (checkMove(board, newMove)) {
                        moveList.add(newMove);
                    } else {
                        break;
                    }
                    i += horizontal;
                    j += vertical;
                }

            }
        }
        return moveList;
    }
}
