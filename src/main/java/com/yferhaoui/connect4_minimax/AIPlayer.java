package com.yferhaoui.connect4_minimax;

import java.util.Collections;
import java.util.List;

import com.yferhaoui.basic.helper.StreamHelper;

public final class AIPlayer extends Player {

	private final OpponentPlayer opponentPlayer;
	private final int indexS;
	private final int minDepth;

	public AIPlayer(final OpponentPlayer opponentPlayer, final int indexS, final int minDepth) {
		super(true); // true => X
		this.opponentPlayer = opponentPlayer;
		this.indexS = indexS;
		this.minDepth = minDepth;
	}

	@Override
	public final void play(final Game game, final Player player, final Move targetMove) throws InterruptedException {

		game.printGame(this.indexS); // To show the game

		final List<Move> moves = game.getStrategicMoves(targetMove);
		final AIWorker[] workers = new AIWorker[moves.size()];
		final Timeout timeout = new Timeout(workers, this.minDepth);

		StreamHelper.printlnWithTime("AIPlayer is playing...\n", 0);
		final long time = System.currentTimeMillis();

		for (int i = 0; i < workers.length; i++) { // Launch every workers

			workers[i] = new AIWorker(this.opponentPlayer, //
					this, //
					workers, //
					moves.get(i), //
					game.clone(), //
					this.indexS, //
					this.minDepth);
			workers[i].start();
		}

		timeout.start(); // Start the timeout

		for (final AIWorker worker : workers) { // Wait until all the workers have finished
			worker.join();
		}

		Collections.sort(moves);
		game.makeMove(moves.get(0), this);
		System.out.println();

		final float timeCalculation = (System.currentTimeMillis() - time) / 1000f;
		StreamHelper.printlnWithTime("AIPlayer choose : " + moves.get(0).toString(this.indexS), 0);
		final int value = moves.get(0).getValue();
		if (value > 1000) {
			StreamHelper.printlnWithTime("AIPlayer win in : " + (Integer.MAX_VALUE - value) + " moves", 0);
		}
		StreamHelper.printlnWithTime("Calculation time: " + String.format("%.2f", timeCalculation) + " seconds", 0);
		timeout.stopTimeout();

		super.play(game, player, moves.get(0)); // Continue until the game is over
	}

	@Override
	public final int getWinScore(final int depth) {
		return Integer.MAX_VALUE - depth;
	}

	@Override
	public final int getScore(final int score, final int depth) {
		return score - depth;
	}
}
