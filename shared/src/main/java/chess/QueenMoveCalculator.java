package chess;

import java.util.Collection;

public class QueenMoveCalculator implements PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> bishopMoveList = new BishopMoveCalculator().pieceMoves(board, position);
        Collection<ChessMove> rookMoveList = new RookMoveCalculator().pieceMoves(board, position);
        bishopMoveList.addAll(rookMoveList);
        return bishopMoveList;
    }
}
