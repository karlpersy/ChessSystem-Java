package chess.piece;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position posi = new Position(0, 0);

			// TESTANDO MOVIMENTOS POSSIVEIS ACIMA
			posi.setValues(position.getRow() - 1, position.getColumn());
			while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
				posi.setRow(posi.getRow() - 1);
			}
			// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
			if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
	
			}
			// TESTANDO MOVIMENTOS POSSIVEIS À ESQUERDA
			posi.setValues(position.getRow(), position.getColumn() - 1);
			while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
				posi.setColumn(posi.getColumn() - 1);
			}
			// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
			if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
	
			}
			// TESTANDO MOVIMENTOS POSSIVEIS À DIREITA
			posi.setValues(position.getRow(), position.getColumn() + 1);
			while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
				posi.setColumn(posi.getColumn() + 1);
			}
			// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
			if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
	
			}
			// TESTANDO MOVIMENTOS POSSIVEIS ABAIXO
			posi.setValues(position.getRow() + 1, position.getColumn());
			while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
				posi.setRow(posi.getRow() + 1);
			}
			// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
			if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
				mat[posi.getRow()][posi.getColumn()] = true;
	
			}
		return mat;
	}
}
