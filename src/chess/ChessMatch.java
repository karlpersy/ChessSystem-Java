package chess;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Piece;
import board.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;

	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.BLUE;
		initialSetup();

	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentplayer() {
		return currentPlayer;
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

	// OPERAÇÃO QUE RETORNA OS MOVIMENTOS POSSIVEIS PARA A PEÇA
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		// VALIDANDO POSIÇÕES
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	// VALIDANDO POSIÇÕES E VALIDANDO MOVIMENTOS DE ORIGEM
	public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturePiece = makeMove(source, target);
		// CHAMANDO PROXIMO JOGADOR DEPOIS DE EXECUTAR A JOGADA
		nextTurnPlayer();
		return (ChessPiece) capturePiece;
	}

	// MOVIMENTO DAS PEÇAS (POSIÇÃO DE ORIGEM PARA POSIÇÃO DE DESTINO)
	private Piece makeMove(Position source, Position target) {
		Piece pc = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(pc, target);
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	// VALIDANDO AS POSIÇÕES DE ORIGEM
	private void validateSourcePosition(Position position) {
			if (!board.thereIsAPiece(position)) {
				throw new ChessException("There is no piece on source position!");
			}
			// THROW PARA EVITAR QUE O JOGADOR USE AS PEÇAS DO ADVERSARIO (COM DONWCASTING)
			if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
				throw new ChessException("This is not your piece!");
			}
			// TESTANDO SE HÁ MOVIMENTOS POSSIVEIS
			if (!board.piece(position).isThereAnyPossibleMove()) {
				throw new ChessException("There is no possible moves  for chosen piece!");
			}
		}

	//ATENÇÃO ESSE METODO PRECISARÁ SER REVISTO NO ENCERRAMENTO, PODE OCORRER ERRO DE LOGICA!
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("You can't move the piece to the chosen position.");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	// CHAMANDO PROXIMO JOGADOR
	private void nextTurnPlayer() {
		turn++;
		currentPlayer = (currentPlayer == Color.BLUE) ? Color.RED : Color.BLUE;
	}

	public void initialSetup() {
		placeNewPiece('c', 2, new Rook(board, Color.BLUE));
		placeNewPiece('c', 1, new King(board, Color.BLUE));
		placeNewPiece('d', 2, new Rook(board, Color.BLUE));
		placeNewPiece('e', 2, new Rook(board, Color.BLUE));
		placeNewPiece('e', 1, new Rook(board, Color.BLUE));
		placeNewPiece('d', 1, new King(board, Color.BLUE));

		placeNewPiece('c', 7, new Rook(board, Color.RED));
		placeNewPiece('c', 8, new Rook(board, Color.RED));
		placeNewPiece('d', 7, new Rook(board, Color.RED));
		placeNewPiece('e', 7, new Rook(board, Color.RED));
		placeNewPiece('e', 8, new Rook(board, Color.RED));
		placeNewPiece('d', 8, new King(board, Color.RED));
		;
	}
}
