package board;

public abstract class Piece {
	protected Position position;
	private Board board;
	
	
	public Piece( Board board) {
		this.board = board;
	}
	protected Board getBoard() {
		return board;
	}
	//METODO ABSTRACT
	public abstract boolean [][] possibleMoves();
	
	
	//TEMPLATE METHOD PADROES DE PROJETO
	//METODO QUE INDICA OS MOVIMENTOS POSSIVEIS PARA DETERMINADA PEÇA
	//METODO QUE CHAMA UMA POSSIVEL IMPLEMENTAÇÃO DE ALGUMA SUBCLASSE CONCRETA DA CLASSE Piece
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];	
	}
	
	//OPERAÇÃO PARA TESTAR SE AO MENOS 1 MOVIMENTO POSSIVEL
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat.length; j++) {
				if (mat[i][j]){
					return true;
				}
			}
		}
		return false;	
	}

}
