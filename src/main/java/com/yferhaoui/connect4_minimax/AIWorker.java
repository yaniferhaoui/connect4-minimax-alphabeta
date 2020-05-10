package com.yferhaoui.connect4_minimax;

import com.yferhaoui.basic.helper.StreamHelper;
import com.yferhaoui.basic.helper.TimeHelper;

public final class AIWorker extends Thread {

	private final OpponentPlayer opponentPlayer;
	private final AIPlayer aiPlayer;
	private final AIWorker[] workers;
	private final Move firstMove;
	private final Game game;
	private final int indexS;
	private int minDepth;

	public AIWorker(final OpponentPlayer opponentPlayer, //
			final AIPlayer aiPlayer, //
			final AIWorker[] workers, //
			final Move firstMove, //
			final Game game, //
			final int indexS, //
			final int minDepth) {

		this.opponentPlayer = opponentPlayer;
		this.aiPlayer = aiPlayer;
		this.workers = workers;
		this.firstMove = firstMove;
		this.game = game;
		this.indexS = indexS;
		this.minDepth = minDepth;
	}

	@Override
	public final void run() {

		this.game.makeMove(this.firstMove, this.aiPlayer); // Make move => depth += 1
		this.firstMove.setValue(this.minValue(this.firstMove, 1, Integer.MIN_VALUE, Integer.MAX_VALUE));

		if (this.firstMove.getValue() > Game.MIN_WIN_SCORE) { // If the worker found a win move

			for (int i = 0; i < this.workers.length; i++) { // Ask the end of every Workers

				while (this.workers[i] == null) { // Wait worker is created
					TimeHelper.sleepUninterruptibly(100);
				}
				workers[i].askEnd();
			}
		}

		StreamHelper.printlnWithTime(this.firstMove.toString(this.indexS), 1);
	}

	// Setter
	public final void askEnd() {
		this.minDepth = 0;
	}

	public final void decrementDepth() {
		this.minDepth--;
	}

	// Getter
	private final int maxValue(final Move move, final int depth, int alpha, final int beta) {

		final int res = this.game.evaluateMove(this.opponentPlayer, move, depth);
		if (depth >= this.minDepth || res < Game.MAX_LOSE_SCORE) { // IF IT'S A LOSE SCORE
			return res;

		} else if (this.game.isFull()) {
			return 0;
		}

		final int newDepth = depth + 1;
		int value = Integer.MIN_VALUE;

		for (final Move localMove : this.game.getStrategicMoves(move)) {

			this.game.makeMove(localMove, this.aiPlayer);
			value = Math.max(value, this.minValue(localMove, newDepth, alpha, beta));
			this.game.undoMove(localMove);

			if (value >= beta) {
				return value;

			} else if (value > alpha) {
				alpha = value;
			}
		}
		return value;
	}

	private final int minValue(final Move move, final int depth, final int alpha, int beta) {

		final int res = this.game.evaluateMove(this.aiPlayer, move, depth);
		if (depth >= this.minDepth || res > Game.MIN_WIN_SCORE) { // IF IT'S A WIN SCORE
			return res;

		} else if (this.game.isFull()) {
			return 0;
		}

		final int newDepth = depth + 1;
		int value = Integer.MAX_VALUE;

		for (final Move localMove : this.game.getStrategicMoves(move)) {

			this.game.makeMove(localMove, this.opponentPlayer);
			value = Math.min(value, this.maxValue(localMove, newDepth, alpha, beta));
			this.game.undoMove(localMove);

			if (value <= alpha) {
				return value;

			} else if (value < beta) {
				beta = value;
			}
		}
		return value;
	}
}
