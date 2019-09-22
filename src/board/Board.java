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
	//METODO PIECE QUE RETORNA  MATRIZ DA Piece NA LINHA E COLUNA
	public Piece piece (int row, int column){
		return pieces [row][column];
	}
	//METODO PPiece COM SOBRECARGA 
	public Piece piece (Position position) {
		return pieces[position.getRow()][position.getColumn()];
		
	}
     


}
