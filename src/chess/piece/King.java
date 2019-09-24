package chess.piece;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean validMovement(Position position) {
		ChessPiece pc = (ChessPiece) getBoard().piece(position);
		return pc == null || pc.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position posi = new Position(0, 0);

		// TESTANDO MOVIMENTOS POSSIVEIS ACIMA
		posi.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS NOROESTE (ACIMA, ESQUERDA)
		posi.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS NORDESTE (ACIMA, DIREITA)
		posi.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS ABAIXO
		posi.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS SUDOESTE (ABAIXO, ESQUERDA)
		posi.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS SUDESTE (ABAIXO, DIREITA)
		posi.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS À ESQUERDA
		posi.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS À DIREITA
		posi.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(posi) && validMovement(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		return mat;
	}
}
