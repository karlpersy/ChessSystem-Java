package board;

public class Board {
	private Integer rows;
	private Integer columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1){
			throw new BoardException("ERROR CREATING BOARD! There must be at least 1 row and 1 column.");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	// METODO (piece) QUE RETORNA A MATRIZ(piece) NA LINHA(row) E COLUNA(column)
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the Board!");
		}
		return pieces[row][column];
	}

	// METODO (piece)  COM SOBRECARGA
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the Board!");
		}
		return pieces[position.getRow()][position.getColumn()];

	}

	// METODO RESPONSAVEL POR COLOCAR A PE�A (Piece piece) NA POSI��O (Position position) do TABULEIRO (Board)
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position! " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;

	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;

	}
     //METODO QUE PEGA A POSI��O E RETORNA UM OBEJETO "boolean" FALANDO SE A POSI��O EXISTE OU N�O 
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	 //METODO "boolean" RECEBE UMA POSI��O E RETORNA UM OBEJETO "boolean" FALANDO SE NELA EXISTE UMA PE�A!
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the Board!");
		}
		return piece(position) != null;

	}

}
