package chess;

import board.Board;
import board.Piece;
import board.Position;

public abstract class ChessPiece extends Piece{
	private Color color;
	private int moveCount;
	
	
public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

public Color getColor() {
	return color;
}
public int getMoveCount() {
return moveCount;
}
public void increaseMoveCount() {
	moveCount ++;
}
public void decreaseMoveCount() {
	moveCount --;
}
//METODO QUE RETORNA POSIÇÃO (NO FORMATO DO XADREZ)
public ChessPosition getChessPosition() {
	return ChessPosition.fromPosition(position);
}
//OPERAÇÃO PARA TESTAR SE A POSIÇÃO DE MOVIMENTO ESTÁ VAZIA OU SE TEM UMA PEÇA DO OPONENTE 
protected boolean isThereOpponentPiece(Position position) {
	ChessPiece pc = (ChessPiece)getBoard().piece(position);
	return pc != null && getColor() != color;
	
}

}
