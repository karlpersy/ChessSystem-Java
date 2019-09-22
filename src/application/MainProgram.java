package application;

import chess.ChessMatch;

public class MainProgram {

	public static void main(String[] args) {
		System.out.println("Hello seus CAVALOSS!!!");
		

       
       //FUNÇÃO PARA IMPRIMIR AS PEÇAS
       ChessMatch chessMatch = new ChessMatch ();
       UI.printBoard(chessMatch.getPieces());
	}

}
