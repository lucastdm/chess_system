package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position pos = new Position(0, 0);
		
		// White pawns
		if (getColor() == Color.WHITE) {
			pos.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() - 2, position.getColumn());
			Position pos2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos) && getBoard().positionExists(pos2) && !getBoard().thereIsAPiece(pos2) && getMoveCount() == 0) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			// en passant (special move)
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		}
		// Black pawns
		else {
			pos.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() + 2, position.getColumn());
			Position pos2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(pos) && !getBoard().thereIsAPiece(pos) && getBoard().positionExists(pos2) && !getBoard().thereIsAPiece(pos2) && getMoveCount() == 0) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				mat[pos.getRow()][pos.getColumn()] = true;
			}
			// en passant (special move)
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
