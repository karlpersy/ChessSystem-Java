package application;

import chess.ChessMatch;

public class MainProgram {

	public static void main(String[] args) {
 
       //FUNÇÃO PARA IMPRIMIR AS PEÇAS
       ChessMatch chessMatch = new ChessMatch();
       UI.printBoard(chessMatch.getPieces());
	}

}
