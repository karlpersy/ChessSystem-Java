package chess.piece;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Horse extends ChessPiece {

	public Horse(Board board, Color color) {
		super(board, color);

	}

	private boolean validMovement(Position position) {
		ChessPiece pc = (ChessPiece) getBoard().piece(position);
		return pc == null || pc.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position posi = new Position(0, 0);

		// TESTANDO MOVIMENTOS POSSIVEIS DO CAVALO
		posi.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		return mat;
	}

	@Override
	public String toString() {
		return "H";
	}
}
