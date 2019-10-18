package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class MainProgram {

	public static void main(String[] args) {

//INSTANCIA��O PARA IMPRIMIR
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getXequeMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosittion(sc);

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosittion(sc);

				ChessPiece capturedPiece = chessMatch.perfomChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				if (chessMatch.getPromoted() != null) {
					System.out.println("Enter piece for promotion (B/H/R/Q)");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}

			} catch (ChessException CE) {
				System.out.println(CE.getMessage());
				sc.nextLine();
			} catch (InputMismatchException IE) {

				System.out.println(IE.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
}
