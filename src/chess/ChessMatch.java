package chess;

import board.Board;
import board.Piece;
import board.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {
	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();

	}

	// METODO PARA RETORNAR A MATRIZ DE PEÇAS DO JOGO DE XADREZ
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// DONWCASTING
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
      //VALIDANDO POSIÇÕES E VALIDANDO MOVIMENTOS DE ORIGEM
	public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturePiece = makeMove(source, target);
		return (ChessPiece) capturePiece;
	}

	// MOVIMENTO DAS PEÇAS (POSIÇÃO DE ORIGEM PARA POSIÇÃO DE DESTINO)
	private Piece makeMove(Position source, Position target) {
		Piece pc = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);
		board.placePiece(pc, target);
		return capturePiece;

	}

	// VALIDANDO AS POSIÇÕES DE ORIGEM
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position!");
		}
		// TESTANDO SE HÁ MOVIMENTOS POSSIVEIS
		if (!board.piece(position).isThereAnyPossibleMove()) {
		throw new ChessException("There is no possible moves  for chosen piece!");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	public void initialSetup() {
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('c', 1, new King(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
		;
	}
}
