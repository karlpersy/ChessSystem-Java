package chess;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import board.Board;
import board.Piece;
import board.Position;
import chess.piece.Bishop;
import chess.piece.Horse;
import chess.piece.King;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean xeque;
	private boolean xequeMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;

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

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequeMate() {
		return xequeMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;

	}

	public ChessPiece getPromoted() {
		return promoted;
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

	// CHAMANDO PROXIMO JOGADOR
	private void nextTurnPlayer() {
		turn++;
		currentPlayer = (currentPlayer == Color.BLUE) ? Color.RED : Color.BLUE;
	}

// VALIDANDO POSIÇÕES E VALIDANDO MOVIMENTOS DE ORIGEM
	public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		// TESTE PARA SABER SE O JOGADOR COLOCOU SUA RAINHA(Queen)EM XEQUE
		if (testXeque(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		// REFERÊNCIA DA PEÇA FOI MOVIDA
		ChessPiece movedPiece = (ChessPiece) board.piece(target);
		// JOGADA ESPECIAL (PROMOTED)
		promoted = null;
		if (movedPiece instanceof Pawn) {
			if ((movedPiece.getColor() == Color.BLUE && target.getRow() == 0)
					|| (movedPiece.getColor() == Color.RED && target.getRow() == 7)) {
				promoted = (ChessPiece) board.piece(target);
				promoted = replacePromotedPiece("Q");
			}
		}
		// FUNÇÃO PARA SABER SE O JOGADOR FICOU EM XEQUE COM O MOVIMENTO FEITO
		xeque = (testXeque(opponent(currentPlayer))) ? true : false;
		if (testXequeMate(opponent(currentPlayer))) {
			xequeMate = true;
		} else {
			// CHAMANDO PROXIMO JOGADOR DEPOIS DE EXECUTAR A JOGADA
			nextTurnPlayer();
		}
		// TESTANDO SE A PEÇA MOVIDA FOI UM PEÃO E SE ELE MOVEU DUAS CASAS (JOGADA
		// ESPECIAL ENPASSANT)
		if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2
				|| (movedPiece instanceof Pawn && (target.getRow() == source.getRow() + 2)))) {
			enPassantVulnerable = movedPiece;
		} else
			enPassantVulnerable = null;
		return (ChessPiece) capturedPiece;

	}

	public ChessPiece replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("There is not piece to be promoted");

		}
		if (!type.equals("B") && !type.equals("H") && !type.equals("R") && !type.equals("Q")) {
			throw new InvalidParameterException("Invalid type for promoted");
		}
		Position posi = promoted.getChessPosition().toPosition();
		Piece pc = board.removePiece(posi);
		piecesOnTheBoard.remove(pc);
		ChessPiece newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, posi);
		piecesOnTheBoard.add(newPiece);
		return newPiece;
	}
//METODO AUXILIAR PARA INSTANCIAR A PEÇA ESPECIFICA DA JOGADA

	private ChessPiece newPiece(String type, Color color) {
		if (type.equals("B"))
			return new Bishop(board, color);
		if (type.equals("H"))
			return new Horse(board, color);
		if (type.equals("R"))
			return new Rook(board, color);
		return new Queen(board, color);
	}

// MOVIMENTO DAS PEÇAS (POSIÇÃO DE ORIGEM PARA POSIÇÃO DE DESTINO)
	private Piece makeMove(Position source, Position target) {
		// CASTING DOWNCASTING
		ChessPiece pc = (ChessPiece) board.removePiece(source);
		pc.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(pc, target);
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		// TESTANDO MOVIMENTO ESPECIAL1 (JOGADA ESPECIAL1, ROQUE GRANDE) #KINGSIDE ROOK
		if (pc instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceP = new Position(source.getRow(), source.getColumn() + 3);
			Position targetP = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceP);
			board.placePiece(rook, targetP);
			rook.increaseMoveCount();
		}
		// TESTANDO MOVIMENTO ESPECIAL2 (JOGADA ESPECIAL, ROQUE GRANDE) #QUEENSIDE ROOK
		if (pc instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceP = new Position(source.getRow(), source.getColumn() - 4);
			Position targetP = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceP);
			board.placePiece(rook, targetP);
			rook.increaseMoveCount();
		}
		// MOVIMENTO JOGADA ESPECIAL ENPASSANT DO PEÃO (Pawn)
		if (pc instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {
				Position pawnPosition;
				if (pc.getColor() == Color.BLUE) {
					pawnPosition = new Position(target.getRow() + 1, target.getColumn());

				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}
		return capturedPiece;
	}

// METODO PARA DESFAZER O MOVIMENTO CASO O JOGADOR COLOQUE SUA RAINHA(Queen) EM XEQUE
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece pc = (ChessPiece) board.removePiece(target);
		pc.decreaseMoveCount();
		board.placePiece(pc, source);
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		// DESFAZENDO MOVIMENTO ESPECIAL1 (JOGADA ESPECIAL1, ROQUE GRANDE) #KINGSIDE
		// ROOK
		if (pc instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceP = new Position(source.getRow(), source.getColumn() + 3);
			Position targetP = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetP);
			board.placePiece(rook, sourceP);
			rook.decreaseMoveCount();
		}
		// DESFAZENDO MOVIMENTO ESPECIAL2 (JOGADA ESPECIAL, ROQUE GRANDE) #QUEENSIDE
		// ROOK
		if (pc instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceP = new Position(source.getRow(), source.getColumn() - 4);
			Position targetP = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetP);
			board.placePiece(rook, sourceP);
			rook.decreaseMoveCount();
		}
		// DESFAZENDO MOVIMENTO PARA JOGADA ESPECIAL ENPASSANT DO PEÃO (Pawn)
		if (pc instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece pawn = (ChessPiece) board.removePiece(target);
				Position pawnPosition;
				if (pc.getColor() == Color.BLUE) {
					pawnPosition = new Position(3, target.getColumn());

				} else {
					pawnPosition = new Position(4, target.getColumn());
				}

				board.placePiece(pawn, pawnPosition);

			}
		}
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

	// METODO PARA CAPTURAR PEÇA DO ADVERSARIO(ATENÇÃO!REVISAR NO METODO!)
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("You can't move the piece to the chosen position.");
		}
	}

//METODO QUE DEVOLVE O OPONENTE (BLUE OR RED)
	private Color opponent(Color color) {
		return (color == Color.BLUE) ? Color.RED : Color.BLUE;
	}

//METODO PARA FAZER VARREDURA E LOCALIZA O REI DO OPONENTE
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece pc : list) {
			if (pc instanceof King) {
				return (ChessPiece) pc;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board!");
	}

// METODO PARA TESTAR SE O JOGADOR ESTÁ EM XEQUE
	private boolean testXeque(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece pc : opponentPieces) {
			boolean[][] mat = pc.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}

		}
		return false;
	}

//METODO PARA TESTAR SE A PEÇA ESTÁ EM XEQUE ANTES DO XEQUE MATE 
	private boolean testXequeMate(Color color) {
		if (!testXeque(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		// FAZ A VARREDURA PARA IDENTIFICAR SE HÁ ALGUM MOVIMENTO E SE NÃO É UM
		// MOVIMENTO QUE PODE DAR XEQUE MATE
		for (Piece pc : list) {
			boolean[][] mat = pc.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					if (mat[i][j]) {
						// (SE XEQUEMATE) A PEÇA VOLTA PARA A POSIÇÃO
						Position source = ((ChessPiece) pc).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testXeque = testXeque(color);
						undoMove(source, target, capturedPiece);
						if (!testXeque) {
							return false;

						}
					}
				}

			}
		}
		return true;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

//POSICIONANDO TODAS AS PEÇAS
	public void initialSetup() {
//POSICIONANDO PEÇAS AZUIS 
		placeNewPiece('a', 1, new Rook(board, Color.BLUE));
		placeNewPiece('b', 1, new Horse(board, Color.BLUE));
		placeNewPiece('c', 1, new Bishop(board, Color.BLUE));
		placeNewPiece('d', 1, new Queen(board, Color.BLUE));
		placeNewPiece('e', 1, new King(board, Color.BLUE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.BLUE));
		placeNewPiece('g', 1, new Horse(board, Color.BLUE));
		placeNewPiece('h', 1, new Rook(board, Color.BLUE));

		placeNewPiece('a', 2, new Pawn(board, Color.BLUE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.BLUE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.BLUE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.BLUE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.BLUE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.BLUE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.BLUE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.BLUE, this));
//POSICIONANDO PEÇAS VERMELHAS 
		placeNewPiece('a', 8, new Rook(board, Color.RED));
		placeNewPiece('b', 8, new Horse(board, Color.RED));
		placeNewPiece('c', 8, new Bishop(board, Color.RED));
		placeNewPiece('d', 8, new Queen(board, Color.RED));
		placeNewPiece('e', 8, new King(board, Color.RED, this));
		placeNewPiece('f', 8, new Bishop(board, Color.RED));
		placeNewPiece('g', 8, new Horse(board, Color.RED));
		placeNewPiece('h', 8, new Rook(board, Color.RED));

		placeNewPiece('a', 7, new Pawn(board, Color.RED, this));
		placeNewPiece('b', 7, new Pawn(board, Color.RED, this));
		placeNewPiece('c', 7, new Pawn(board, Color.RED, this));
		placeNewPiece('d', 7, new Pawn(board, Color.RED, this));
		placeNewPiece('e', 7, new Pawn(board, Color.RED, this));
		placeNewPiece('f', 7, new Pawn(board, Color.RED, this));
		placeNewPiece('g', 7, new Pawn(board, Color.RED, this));
		placeNewPiece('h', 7, new Pawn(board, Color.RED, this));

	}
}
