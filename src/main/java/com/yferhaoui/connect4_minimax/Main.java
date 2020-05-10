package com.yferhaoui.connect4_minimax;

import com.yferhaoui.basic.helper.StreamHelper;

public final class Main {

	private final static int MIN_DEPTH = 6;
	private final static int MAX_DEPTH = 12; // 12 is Dangerous with the Timeout

	// ------------------------------- //

	public final static void main(final String[] args) throws InterruptedException {

		System.out.println();

		// Players initialization
		Player player1 = null;
		Player player2 = null;

		final int indexS = StreamHelper.askNumberWithTime("Starting index", 0, 1, 0);
		final int minDepth = StreamHelper.askNumberWithTime("Choose min depth", MIN_DEPTH, MAX_DEPTH, 0);

		if (StreamHelper.askBooleanWithTime("Would you like AI begin ?", 0)) {

			player2 = new OpponentPlayer(indexS);
			player1 = new AIPlayer((OpponentPlayer) player2, indexS, minDepth);
		} else {

			player1 = new OpponentPlayer(indexS);
			player2 = new AIPlayer((OpponentPlayer) player1, indexS, minDepth);
		}

		StreamHelper.printlnWithTime("Start of the game !", 0);
		final Game game = new Game();
		player1.play(game, player2, game.getDefaultMoves().get(0));
		game.printGame(indexS);
		StreamHelper.printlnWithTime("End of the game !", 0);
	}
}
