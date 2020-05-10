package com.yferhaoui.connect4_minimax;

public final class Move implements Comparable<Move> {

	private final int posX;
	private int posY;
	private int value;

	public Move(final int posX, final int posY) { // UNIT-TESTS Constructor
		this.posX = posX;
		this.posY = posY;
	}

	public Move(final int posX) {
		this.posX = posX;
		this.posY = Game.MAX_Y_INDEX;
	}

	// Setter
	public final void decrementPosY() {
		this.posY--;
	}

	public final void setValue(final int value) {
		this.value = value;
	}

	// Getter
	public final int getPosX() {
		return this.posX;
	}

	public final int getPosY() {
		return this.posY;
	}

	public final int getValue() {
		return this.value;
	}

	@Override
	public final int compareTo(final Move arg0) {

		// Try to sort by value (desc)
		final int res1 = Integer.valueOf(arg0.value).compareTo(this.value); // Order by desc
		if (res1 != 0) {
			return res1;
		}

		// Try to sort by distance from Bottom (desc)
		final int res2 = Integer.valueOf(arg0.posY).compareTo(this.posY);
		if (res2 != 0) {
			return res2;
		}

		// Finally sort by distance from center
		return Game.INDEX_VALUES[arg0.posX].compareTo(Game.INDEX_VALUES[this.posX]);
	}

	@Override
	public final String toString() {
		return "Column " + this.posX + " with value " + this.value;
	}

	public final String toString(final int indexS) {
		return "Column " + (this.posX + indexS) + " with value " + this.value;
	}
}
