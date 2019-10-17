package chess.piece;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;

	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position pc = new Position(0, 0);
		// MOVIMENTOS JOGADOR BLUE
		if (getColor() == Color.BLUE) {
			pc.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc)) {
				mat[pc.getRow()][pc.getColumn()] = true;

			}
			pc.setValues(position.getRow() - 2, position.getColumn());
			Position pc1 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc) && getBoard().positionExists(pc1)
					&& !getBoard().thereIsAPiece(pc1) && getMoveCount() == 0) {
				mat[pc.getRow()][pc.getColumn()] = true;
			}
			pc.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)) {
				mat[pc.getRow()][pc.getColumn()] = true;
			}
			pc.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)) {
				mat[pc.getRow()][pc.getColumn()] = true;
			}
			// TESTANDO A POSSIBILIDADE DE OCORRER A JOGADA ESPECIAL ENPASSANT
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if ((getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable())) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if ((getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable())) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}

		}
		// MOVIMENTOS JOGADOR RED
		else {
			pc.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc)) {
				mat[pc.getRow()][pc.getColumn()] = true;

			}
			pc.setValues(position.getRow() + 2, position.getColumn());
			Position pc1 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(pc) && !getBoard().thereIsAPiece(pc) && getBoard().positionExists(pc1)
					&& !getBoard().thereIsAPiece(pc1) && getMoveCount() == 0) {
				mat[pc.getRow()][pc.getColumn()] = true;
			}
			pc.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)) {
				mat[pc.getRow()][pc.getColumn()] = true;
			}
			pc.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(pc) && isThereOpponentPiece(pc)) {
				mat[pc.getRow()][pc.getColumn()] = true;
			}
			// TESTANDO A POSSIBILIDADE DE OCORRER A JOGADA ESPECIAL ENPASSANT
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if ((getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable())) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() - 1);
				if ((getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable())) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
