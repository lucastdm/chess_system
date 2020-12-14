package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String WHITE = "\033[1;97m";   // WHITE BOLD BRIGHT
    public static final String YELLOW = "\033[1;93m";  // YELLOW BOLD BRIGHT
    public static final String BACKGROUND = "\u001b[47m"; // GREEN BRIGHT
	
    public static void clearScreen() {
    	System.out.print("\033[H\033[2J");
    	System.out.flush();
    }
    
    public static ChessPosition readChessPosition(Scanner sc) {
    	try {
	    	String s = sc.nextLine();
	    	char col = s.charAt(0);
	    	int row = Integer.parseInt(s.substring(1));
	    	return new ChessPosition(col, row);
    	}
    	catch (RuntimeException e) {
    		throw new InputMismatchException("Error reading ChessPosition. Valid values range from a1 to h8");
    	}
    }
    
    public static void printMatch(ChessMatch chessMatch) {
    	printBoard(chessMatch.getPieces());
    	System.out.println();
    	System.out.println("Turn: " + chessMatch.getTurn());
    	System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
    }
    
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8-i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i=0; i<pieces.length; i++) {
			System.out.print((8-i) + " ");
			for (int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(BACKGROUND);
		}
		if (piece == null) 
			System.out.print("·" + RESET);
		else {
			if (piece.getColor() == Color.WHITE)
				System.out.print(WHITE + piece + RESET);
			else 
				System.out.print(YELLOW + piece + RESET);
		}
		System.out.print(" ");
	}
}
