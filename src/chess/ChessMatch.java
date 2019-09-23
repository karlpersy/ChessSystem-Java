package chess;

import board.Board;
import board.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {
	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();

	}

	// METODO PARA RETORNAR A MATRIZ DE PE�AS DO JOGO DE XADREZ
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat  = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				//DONWCASTING
				mat[i][j] = (ChessPiece) board.piece(i, j);
			} 
		}
		return mat;
	}
	private void placeNewPiece (char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toposition());
	}
	public void initialSetup() {
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
	}
}
