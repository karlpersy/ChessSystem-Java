package chess.piece;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	
	public Pawn (Board board, Color color) {
		super(board, color);

	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		Position pc = new Position (0, 0);
		
		if (getColor () == Color.BLUE) {
			pc.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc)){
				mat[pc.getRow()][pc.getColumn()] = true; 	
				
			}
			pc.setValues(position.getRow() - 2, position.getColumn());
			Position pc1 = new Position (position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc) && getBoard().positionExists(pc1) && !getBoard().thereIsAPiece(pc1) && getMoveCount() == 0) {
				mat[pc.getRow()][pc.getColumn()] = true; 
			}
			pc.setValues(position.getRow() - 1, position.getColumn() -1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)){
				mat[pc.getRow()][pc.getColumn()] = true; 
			}
			pc.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)){
				mat[pc.getRow()][pc.getColumn()] = true; 
			}
			
		}
		else {
			pc.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc)){
				mat[pc.getRow()][pc.getColumn()] = true; 	
				
			}
			pc.setValues(position.getRow() + 2, position.getColumn());
			Position pc1 = new Position (position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc) && getBoard().positionExists(pc1) && !getBoard().thereIsAPiece(pc1) && getMoveCount() == 0) {
				mat[pc.getRow()][pc.getColumn()] = true; 
			}
			pc.setValues(position.getRow() + 1, position.getColumn() -1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)){
				mat[pc.getRow()][pc.getColumn()] = true; 
			}
			pc.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)){
				mat[pc.getRow()][pc.getColumn()] = true; 
			}
		}
		return mat;
	}
	@Override
	public String toString() {
		return "P";
	}

}
