package application;

import chess.ChessMatch;

public class MainProgram {

	public static void main(String[] args) {
		System.out.println("Hello seus CAVALOSS!!!");
		

       
       //FUN��O PARA IMPRIMIR AS PE�AS
       ChessMatch chessMatch = new ChessMatch ();
       UI.printBoard(chessMatch.getPieces());
	}

}
