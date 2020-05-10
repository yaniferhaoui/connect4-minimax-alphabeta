package com.yferhaoui.connect4_minimax;

import com.yferhaoui.basic.helper.StreamHelper;

public abstract class Player {

	private final boolean binary;
	private final boolean opponentBinary;

	protected Player(final boolean binary) {
		this.binary = binary;
		this.opponentBinary = !binary;
	}

	// Setter
	protected void play(final Game game, final Player player, final Move move) throws InterruptedException {

		move.setValue(game.evaluateMove(this, move, 0)); // Evaluate current score
		
		if (move.getValue() > Game.MIN_WIN_SCORE || move.getValue() < Game.MAX_LOSE_SCORE) {
			StreamHelper.printlnWithTime(this.getClass().getSimpleName() + " Won !", 0);

		} else if (game.isFull()) {
			StreamHelper.printlnWithTime("Draw !", 0);

		} else {
			player.play(game, this, move);
		}
	}

	// Getter
	public abstract int getWinScore(final int depth);

	public abstract int getScore(final int score, final int depth);

	public final boolean getOpponentBinary() {
		return this.opponentBinary;
	}

	public final boolean getBinary() {
		return this.binary;
	}
}
