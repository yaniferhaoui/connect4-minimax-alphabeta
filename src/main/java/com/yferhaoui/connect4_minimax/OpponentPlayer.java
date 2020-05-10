package com.yferhaoui.connect4_minimax;

import com.yferhaoui.basic.helper.StreamHelper;

public final class OpponentPlayer extends Player {

	private final int indexS;
	private final int indexE;

	public OpponentPlayer(final int indexS) {
		super(false); // false => O
		this.indexS = indexS;
		this.indexE = Game.MAX_X_INDEX + this.indexS;
	}

	@Override
	public final void play(final Game game, final Player player, Move move) throws InterruptedException {

		game.printGame(this.indexS); // To show the game

		move = null;
		do {
			final int index = StreamHelper.askNumberWithTime("Your turn !", this.indexS, this.indexE, 0) - this.indexS;
			if (game.isValidMove(index)) {
				move = new Move(index);
			}

		} while (move == null);

		game.makeMove(move, this); // Make the move

		super.play(game, player, move); // Continue until the game is over
	}

	@Override
	public final int getWinScore(final int depth) { // Negative score
		return Integer.MIN_VALUE + depth;
	}

	@Override
	public final int getScore(final int score, final int depth) { // Negative score
		return -score + depth;
	}
}
