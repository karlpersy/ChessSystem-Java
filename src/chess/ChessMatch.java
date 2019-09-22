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
	public void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE),new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK),new Position(7, 4));
		board.placePiece(new King(board, Color.WHITE),new Position(7, 4));
	}
}