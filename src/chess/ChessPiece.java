package chess;

import board.Board;
import board.Piece;
import board.Position;

public abstract class ChessPiece extends Piece{
	private Color color;
	
	
public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

public Color getColor() {
	return color;
}
//OPERAÇÃO PARA TESTAR SE A POSIÇÃO DE MOVIMENTO ESTÁ VAZIA OU SE TEM UMA PEÇA DO OPONENTE 
protected boolean isThereOpponentPiece(Position position) {
	ChessPiece pc = (ChessPiece)getBoard().piece(position);
	return pc != null && getColor() != color;
	
}

}
