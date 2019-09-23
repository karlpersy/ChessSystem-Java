package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class MainProgram {

	public static void main(String[] args) {

		// INSTANCIAÇÃO PARA IMPRIMIR
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		while (true) {
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.println("Source: ");
			ChessPosition source = UI.readChessPosittion(sc);

			System.out.println();
			System.out.println("Target: ");
			ChessPosition target = UI.readChessPosittion(sc);

			ChessPiece capturePiece = chessMatch.perfomChessMove(source, target);

		}

	}
}
