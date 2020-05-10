package com.yferhaoui.connect4_minimax;

import java.util.ArrayList;
import java.util.List;

import com.yferhaoui.basic.helper.StreamHelper;

public final class Game {

	private final static int[] SORTED_X_INDEXES = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	private final static int[] SORTED_Y_INDEXES = { 0, 1, 2, 3, 4, 5 };

	// To choose the best move is two moves has the same value
	public final static Integer[] INDEX_VALUES = { 0, 1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 0 };
	private final static int[][] STRATEGIC_MOVES = new int[][] { //
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, // If last Move is 0
			{ 1, 2, 0, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, // If last Move is 1
			{ 2, 3, 1, 4, 0, 5, 6, 7, 8, 9, 10, 11 }, // If last Move is 2
			{ 3, 4, 2, 5, 1, 6, 0, 7, 8, 9, 10, 11 }, // If last Move is 3
			{ 4, 5, 3, 6, 2, 7, 1, 8, 0, 9, 10, 11 }, // If last Move is 4
			{ 5, 6, 4, 7, 3, 8, 2, 9, 1, 10, 0, 11 }, // If last Move is 5
			{ 6, 5, 7, 4, 8, 3, 9, 2, 10, 1, 11, 0 }, // If last Move is 6
			{ 7, 6, 8, 5, 9, 4, 10, 3, 11, 2, 1, 0 }, // If last Move is 7
			{ 8, 7, 9, 6, 10, 5, 11, 4, 3, 2, 1, 0 }, // If last Move is 8
			{ 9, 8, 10, 7, 11, 6, 5, 4, 3, 2, 1, 0 }, // If last Move is 9
			{ 10, 9, 11, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, // If last Move is 10
			{ 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, // If last Move is 11
	};

	private final static int NUMBER_ROWS = 6;
	private final static int NUMBER_COLUMNS = 12;

	private final static int MAX_MOVES = 42;

	public final static int MAX_X_INDEX = NUMBER_COLUMNS - 1;
	public final static int MAX_Y_INDEX = NUMBER_ROWS - 1;

	public final static int MAX_LOSE_SCORE = Integer.MIN_VALUE + MAX_MOVES + 1;
	public final static int MIN_WIN_SCORE = Integer.MAX_VALUE - MAX_MOVES - 1;

	private final static int PADDING_RIGHT = MAX_X_INDEX - 3;
	private final static int PADDING_BOTTOM = MAX_Y_INDEX - 3;

	// -----------------------

	private final Boolean[][] game;
	private int numberMoves;

	// Constructors
	public Game() { // Initial constructor

		this.numberMoves = 0;
		this.game = new Boolean[Game.NUMBER_ROWS][Game.NUMBER_COLUMNS];

		for (int i : SORTED_Y_INDEXES) {
			for (int j : SORTED_X_INDEXES) {
				this.game[i][j] = null;
			}
		}
	}

	public Game(final int numberMoves, final Boolean[][] game) { // To clone the current Game for each Workers

		this.numberMoves = numberMoves;
		this.game = new Boolean[Game.NUMBER_ROWS][Game.NUMBER_COLUMNS];

		for (int i : SORTED_Y_INDEXES) {
			for (int j : SORTED_X_INDEXES) {
				this.game[i][j] = game[i][j];
			}
		}
	}

	// Setter
	public final void makeMove(final Move move, final Player player) { // Need to be fast

		this.numberMoves++;
		while (this.game[move.getPosY()][move.getPosX()] != null) { // Gravity effect
			move.decrementPosY();
		}
		this.game[move.getPosY()][move.getPosX()] = player.getBinary(); // Make the move
	}

	public final void undoMove(final Move move) { // Need to be fast

		this.numberMoves--;
		this.game[move.getPosY()][move.getPosX()] = null; // Undo the move
	}

	public final void printGame(final int indexStart) { // Show the game

		StreamHelper.printlnWithTime("Number of moves : " + this.numberMoves, 0);

		// Print the game
		System.out.println();
		for (final Boolean[] line : this.game) {
			System.out.print("   ");
			for (final Boolean binary : line) {

				if (binary == null) {
					System.out.print(" . ");
				} else if (binary) {
					System.out.print(" X ");
				} else {
					System.out.print(" O ");
				}

			}
			System.out.println();
		}

		// Print the column numbers
		System.out.print("   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n   ");
		for (int i = 0; i < this.game[0].length; i++) {
			System.out.print(StreamHelper.fillLeftSpace((i + indexStart) + " ", 3));
		}
		System.out.println("\n");
	}

	// Getter
	public final int evaluateMove(final Player player, final Move move, final int depth) { // Need to be fast

		final Boolean binary = player.getBinary();
		final Boolean opponentBinary = player.getOpponentBinary();
		final int posX = move.getPosX();
		final int posY = move.getPosY();

		int res = 0; // IF NO WIN => RETURN THE EVALUATED GAME STATE

		// HORIZONTAL CHECK
		final int startX = Math.max(posX - 3, 0);
		final int endX = Math.min(PADDING_RIGHT, move.getPosX());

		for (int i = startX; i <= endX; i++) {

			final Boolean pos1 = this.game[posY][i];
			final Boolean pos2 = this.game[posY][i + 1];
			final Boolean pos3 = this.game[posY][i + 2];
			final Boolean pos4 = this.game[posY][i + 3];

			if (pos1 == binary && pos2 == binary && pos3 == binary && pos4 == binary) { // WIN CHECK
				return player.getWinScore(depth);

			} else if (pos1 != opponentBinary // CHECK IF IT'S A FREE ROW OF 4
					&& pos2 != opponentBinary //
					&& pos3 != opponentBinary && pos4 != opponentBinary) {

				res += (pos1 == binary ? 1 : 0) // EVALUATE HORIZONTAL
						+ (pos2 == binary ? 1 : 0) //
						+ (pos3 == binary ? 1 : 0) //
						+ (pos4 == binary ? 1 : 0);
			}
		}

		// VERTICAL CHECK (NO NEED LOOP BECAUSE OF GRAVITY)
		final int posY1 = posY + 1;
		final int posY2 = posY + 2;

		if (posY <= PADDING_BOTTOM //
				&& this.game[posY][posX] == binary //
				&& this.game[posY1][posX] == binary //
				&& this.game[posY2][posX] == binary && this.game[posY + 3][posX] == binary) { // WIN CHECK
			return player.getWinScore(depth);

		} else if (posY == 5) { // X = 5 | COUNT FREE CONNECTED
			res++;

		} else if (posY == 4) { // X = 4
			res += 1 + (this.game[posY1][posX] == binary ? 1 : 0);

		} else if (posY == 3) { // X = 3
			res += 1 + (this.game[posY1][posX] == binary ? 1 + (this.game[posY2][posX] == binary ? 1 : 0) : 0);

		} else if (posY == 2 && this.game[posY1][posX] == binary) { // X = 2
			res += 2 + (this.game[posY2][posX] == binary ? 1 : 0);

		} else if (posY == 1 && this.game[posY1][posX] == binary && this.game[posY2][posX] == binary) { // X = 1
			res += 3;
		}

		// COMMON VARIABLES OF THE DIAGONAL CHECKS
		final int startXMinusPosX = startX - posX;
		final int paddingRightMinusPosX = PADDING_RIGHT - posX;

		// DIAGONAL DOWN-RIGHT CHECK
		final int startY = Math.max(posY - 3, 0);
		final int startIndexDR = Math.max(startXMinusPosX, startY - posY);
		final int endIndexDR = Math.min(0, Math.min(paddingRightMinusPosX, PADDING_BOTTOM - posY));

		for (int i = startIndexDR; i <= endIndexDR; i++) {

			final int newPosX = posX + i;
			final int newPosY = posY + i;

			final Boolean pos1 = this.game[newPosY][newPosX];
			final Boolean pos2 = this.game[newPosY + 1][newPosX + 1];
			final Boolean pos3 = this.game[newPosY + 2][newPosX + 2];
			final Boolean pos4 = this.game[newPosY + 3][newPosX + 3];

			if (pos1 == binary && pos2 == binary && pos3 == binary && pos4 == binary) { // WIN CHECK
				return player.getWinScore(depth);

			} else if (pos1 != opponentBinary // CHECK IF IT'S A FREE DIAGONAL OF 4
					&& pos2 != opponentBinary //
					&& pos3 != opponentBinary && pos4 != opponentBinary) {

				res += (pos1 == binary ? 1 : 0) // EVALUATE DIAGONAL
						+ (pos2 == binary ? 1 : 0) //
						+ (pos3 == binary ? 1 : 0) //
						+ (pos4 == binary ? 1 : 0);
			}
		}

		// DIAGONAL UP-RIGHT CHECK
		final int startIndexUR = Math.max(startXMinusPosX, Math.max(posY - MAX_Y_INDEX, -3));
		final int endIndexUR = Math.min(0, Math.min(paddingRightMinusPosX, -(3 - posY)));

		for (int i = startIndexUR; i <= endIndexUR; i++) {

			final int newPosX = posX + i;
			final int newPosY = posY - i;

			final Boolean pos1 = this.game[newPosY][newPosX];
			final Boolean pos2 = this.game[newPosY - 1][newPosX + 1];
			final Boolean pos3 = this.game[newPosY - 2][newPosX + 2];
			final Boolean pos4 = this.game[newPosY - 3][newPosX + 3];

			if (pos1 == binary && pos2 == binary && pos3 == binary && pos4 == binary) { // WIN CHECK
				return player.getWinScore(depth);

			} else if (pos1 != opponentBinary // CHECK IF IT'S A FREE DIAGONAL OF 4
					&& pos2 != opponentBinary //
					&& pos3 != opponentBinary && pos4 != opponentBinary) {

				res += (pos1 == binary ? 1 : 0) // EVALUATE DIAGONAL
						+ (pos2 == binary ? 1 : 0) //
						+ (pos3 == binary ? 1 : 0) //
						+ (pos4 == binary ? 1 : 0);
			}
		}
		return player.getScore(res, depth);
	}

	public final List<Move> getDefaultMoves() {

		final List<Move> moves = new ArrayList<Move>(NUMBER_COLUMNS);
		for (final int col : STRATEGIC_MOVES[5]) {
			if (this.isValidMove(col)) {
				moves.add(new Move(col));
			}
		}
		return moves;
	}

	public final List<Move> getStrategicMoves(final Move targetMove) { // Need to be fast

		final List<Move> moves = new ArrayList<Move>(NUMBER_COLUMNS);
		for (final int col : STRATEGIC_MOVES[targetMove.getPosX()]) {

			if (this.isValidMove(col)) {
				moves.add(new Move(col));
			}
		}
		return moves;
	}

	public final boolean isFull() { // Need to be fast
		return this.numberMoves == Game.MAX_MOVES;
	}

	public final boolean isValidMove(final int posX) { // Need to be fast
		return this.game[0][posX] == null;
	}

	public final Game clone() { // Need to be fast
		return new Game(this.numberMoves, this.game);
	}
}
