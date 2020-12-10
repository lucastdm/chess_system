package boardgame;

public class Board {
	private int rows, columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column!");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int col) {
		if (!positionExists(row, col)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][col];
	}
	
	public Piece piece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[pos.getRow()][pos.getColumn()];
	}
	
	public void placePiece(Piece piece, Position pos) {
		if (thereIsAPiece(pos)) {
			throw new BoardException("There is a piece at position " + pos + " already!");
		}
		pieces[pos.getRow()][pos.getColumn()] = piece;
		piece.position = pos;
	}
	
	private boolean positionExists(int row, int col) {
		return row >= 0 && row < rows && col >=0 && col < columns;
	}
	
	public boolean positionExists(Position pos) {
		return positionExists(pos.getRow(), pos.getColumn());
	}
	
	public boolean thereIsAPiece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardException("Position not on the board!");
		}
		return piece(pos) != null;
	}
}
