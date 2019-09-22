package board;

public class Board {
	private Integer rows;
	private Integer columns;
	private Piece[][] pieces;
	
	public Board (int rows, int columns) {
		this.rows = getRows();
		this.columns = getColumns();
		pieces = new Piece[rows][columns];
	}
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getColumns() {
		return columns;
	}
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	//METODO (Piece) QUE RETORNA A MATRIZ(piece) NA LINHA(row) E COLUNA(column)
	public Piece piece (int row, int column){
		return pieces [row][column];
	}
	//METODO (Piece) COM SOBRECARGA 
	public Piece piece (Position position) {
		return pieces[position.getRow()][position.getColumn()];
		
	}
	//METODO RESPONSAVEL POR COLOCAR A PE�A (Piece piece) NA POSI��O (Position position) do TABULEIRO (Board)
    public void placePiece (Piece piece,Position position) {
    	pieces [position.getRow()][position.getColumn()] = piece;
    	 piece.position = position;
    	
    }


}
