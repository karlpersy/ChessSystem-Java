package chess.piece;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;

	}

	private boolean validMovement(Position position) {
		ChessPiece pc = (ChessPiece) getBoard().piece(position);
		return pc == null || pc.getColor() != getColor();
	}

	private boolean testeRookCastling(Position position) {
		ChessPiece pc = (ChessPiece) getBoard().piece(position);
		return pc == null && pc instanceof Rook && pc.getColor() == pc.getColor() && pc.getMoveCount() == 0;

	}

	@Override
	public String toString() {
		return "K";
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
		// TESTANDO MOVIMENTOS POSSIVEIS ABAIXO
		posi.setValues(position.getRow() + 1, position.getColumn());
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
		// TESTANDO MOVIMENTO ESPECIAL (JOGADA ESPECIAL1, ROQUE MENOR)
		// TESTANDO SE O REI PODE MOVER 2 CAS PARA A DIREITA
		if (getMoveCount() == 0 && !chessMatch.getXeque()) {
			// KINGSIDE ROOK
			Position posi1 = new Position(position.getRow(), position.getColumn() + 3);
			// TESTANDO SE NA POSIÇÃO EXISTE UMA TORRE HAPITA A JOGADA
			if (testeRookCastling(posi1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				// TESTANDO SE AS POSIÇÕES DA DIREITA E DA ESQUERDA DO REI ESTÃO VAZIAS
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
		}
		// TESTANDO MOVIMENTO ESPECIAL (JOGADA ESPECIAL1, ROQUE MAIOR)
		// TESTANDO SE O REI PODE MOVER 2 CAS PARA PARA ESQUERDA
		if (getMoveCount() == 0 && !chessMatch.getXeque()) {
			// KINGSIDE ROKK
			Position posi2 = new Position(position.getRow(), position.getColumn() - 4);
			// TESTANDO SE NA POSIÇÃO EXISTE UMA TORRE HAPITA A JOGADA
			if (testeRookCastling(posi2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				// TESTANDO SE AS POSIÇÕES DA DIREITA E DA ESQUERDA DO REI ESTÃO VAZIAS
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}

		}

		return mat;
	}
}
