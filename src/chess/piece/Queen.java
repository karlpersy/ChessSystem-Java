package chess.piece;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
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
			mat[posi.getRow()][posi.getColumn()] = false;

		}
		// TESTANDO MOVIMENTOS POSSIVEIS À ESQUERDA/*
		posi.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
			posi.setColumn(posi.getColumn() - 1);
		}
		// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
		if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = false;

		}
		// TESTANDO MOVIMENTOS POSSIVEIS À DIREITA
		posi.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
			posi.setColumn(posi.getColumn() + 1);
		}
		// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
		if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = false;

		}
		// TESTANDO MOVIMENTOS POSSIVEIS ABAIXO/*/
		posi.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
			posi.setRow(posi.getRow() + 1);
		}
		// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
		if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = false;

		}
		// TESTANDO MOVIMENTOS POSSIVEIS NOROESTE (ACIMA, ESQUERDA)
		posi.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
			posi.setValues(posi.getRow() - 1, posi.getColumn() - 1);
		}
		// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
		if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = false;
		}
		// TESTANDO MOVIMENTOS POSSIVEIS NORDESTE (ACIMA, DIREITA)
		posi.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
			posi.setValues(posi.getRow() - 1, posi.getColumn() + 1);
		}
		// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
		if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = false;

		}
		// TESTANDO MOVIMENTOS POSSIVEIS SUDESTE (ABAIXO, DIREITA) |_
		posi.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
			posi.setValues(posi.getRow() + 1, posi.getColumn() + 1);
		}
		// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
		if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = false;

		}
		// TESTANDO MOVIMENTOS POSSIVEIS SUDOESTE (ABAIXO, ESQUERDA) _|
		posi.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(posi) && !getBoard().thereIsAPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
			posi.setValues(posi.getRow() + 1, posi.getColumn() - 1);
		}
		// TESTANDO SE HÁ PEÇAS DO OPONENTE NA POSIÇÃO
		if (getBoard().positionExists(posi) && isThereOpponentPiece(posi)) {
			mat[posi.getRow()][posi.getColumn()] = false;

		}
		return mat;
	}

	@Override
	public String toString() {
		return "Q";
	}

}
