package com.yferhaoui.connect4_minimax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public final class Connect4Test {

	private final static Boolean[][] convertGame(final char[][] game) {
		final Boolean[][] binaryGame = new Boolean[game.length][game[0].length];
		for (int i = 0; i < binaryGame.length; i++) {
			for (int j = 0; j < binaryGame[0].length; j++) {

				if (game[i][j] == '.') {
					binaryGame[i][j] = null;

				} else if (game[i][j] == 'X') {
					binaryGame[i][j] = true;
				} else {
					binaryGame[i][j] = false;
				}
			}
		}
		return binaryGame;
	}

	@Test
	@Order(1)
	public final void testIsWinnerYani() {

		final Player opponent = new OpponentPlayer(0); // O
		final Player playerAI = new AIPlayer((OpponentPlayer) opponent, 0, 0); // X

		// RANDOM TESTS
		final char[][] game1 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', 'O', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', 'O', '.', '.' } };// 5
		final Move move1 = new Move(9, 3);
//		Assertions.assertEquals(0, new Game(1, convertGame(game1)).evaluateMove(playerAI, move1, 0)); // X
		Assertions.assertEquals(-14, new Game(1, convertGame(game1)).evaluateMove(opponent, move1, 0)); // O

		// VERTICAL TESTS
		final char[][] game2 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.' } };
		final Move move2 = new Move(5, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game2)).evaluateMove(playerAI, move2, 0)); // X
//		Assertions.assertEquals(0, new Game(7, convertGame(game2)).evaluateMove(opponent, move2, 0)); // O

		final char[][] game3 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'X', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' },
				{ 'X', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' },
				{ 'X', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' } };
		final Move move3 = new Move(0, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game3)).evaluateMove(playerAI, move3, 0));
//		Assertions.assertEquals(0, new Game(7, convertGame(game3)).evaluateMove(opponent, move3, 0));

		final char[][] game4 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' },
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', 'X' },
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', 'X' },
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', 'X' } };
		final Move move4 = new Move(11, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game4)).evaluateMove(playerAI, move4, 0)); // X
//		Assertions.assertEquals(0, new Game(7, convertGame(game4)).evaluateMove(opponent, move4, 0)); // O

		// HORIZONTAL TESTS
		final char[][] game5 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move5 = new Move(3, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game5)).evaluateMove(playerAI, move5, 0)); // X
//		Assertions.assertEquals(-1, new Game(7, convertGame(game5)).evaluateMove(opponent, move5, 0)); // O

		final char[][] game6 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', 'O', '.', '.' },
				{ '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.', 'O', '.', '.' } };
		final Move move6 = new Move(5, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game6)).evaluateMove(playerAI, move6, 0)); // X
//		Assertions.assertEquals(-1, new Game(7, convertGame(game6)).evaluateMove(opponent, move6, 0)); // O

		final char[][] game7 = { // IMPOSSIBLE (Just for TEST)
				{ 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move7 = new Move(3, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game7)).evaluateMove(playerAI, move7, 0)); // X
//		Assertions.assertEquals(0, new Game(4, convertGame(game7)).evaluateMove(opponent, move7, 0)); // O

		final char[][] game8 = { // IMPOSSIBLE (Just for TEST)
				{ 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move8 = new Move(0, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game8)).evaluateMove(playerAI, move8, 0)); // X
//		Assertions.assertEquals(0, new Game(4, convertGame(game8)).evaluateMove(opponent, move8, 0)); // O

		final char[][] game9 = { // IMPOSSIBLE (Just for TEST)
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'X' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move9 = new Move(8, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game9)).evaluateMove(playerAI, move9, 0)); // X
//		Assertions.assertEquals(0, new Game(4, convertGame(game9)).evaluateMove(opponent, move9, 0)); // O

		final char[][] game10 = { // IMPOSSIBLE (Just for TEST)
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'X' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move10 = new Move(11, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game10)).evaluateMove(playerAI, move10, 0)); // X
//		Assertions.assertEquals(0, new Game(4, convertGame(game10)).evaluateMove(opponent, move10, 0)); // O

		final char[][] game11 = { // IMPOSSIBLE (Just for TEST)
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'X' } };
		final Move move11 = new Move(10, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game11)).evaluateMove(playerAI, move11, 0)); // X
//		Assertions.assertEquals(-1, new Game(4, convertGame(game11)).evaluateMove(opponent, move11, 0)); // O

		// DIAGONAL DOWN-RIGHT TESTS
		final char[][] game12 = { // IMPOSSIBLE (Just for TEST)
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move12 = new Move(3, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game12)).evaluateMove(playerAI, move12, 0)); // X
//		Assertions.assertEquals(-1, new Game(4, convertGame(game12)).evaluateMove(opponent, move12, 0)); // O

		final char[][] game13 = { // IMPOSSIBLE (Just for TEST)
				{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move13 = new Move(0, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game13)).evaluateMove(playerAI, move13, 0)); // X
//		Assertions.assertEquals(0, new Game(4, convertGame(game13)).evaluateMove(opponent, move13, 0)); // O

		final char[][] game14 = { // IMPOSSIBLE (Just for TEST)
				{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move14 = new Move(3, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game14)).evaluateMove(playerAI, move14, 0)); // X
//		Assertions.assertEquals(-1, new Game(4, convertGame(game14)).evaluateMove(opponent, move14, 0)); // O

		final char[][] game15 = { // IMPOSSIBLE (Just for TEST)
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };
		final Move move15 = new Move(11, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game15)).evaluateMove(playerAI, move15, 0)); // X
//		Assertions.assertEquals(-1, new Game(4, convertGame(game15)).evaluateMove(opponent, move15, 0)); // O

		final char[][] game16 = { // IMPOSSIBLE (Just for TEST)
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' } };
		final Move move16 = new Move(8, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(4, convertGame(game16)).evaluateMove(playerAI, move16, 0)); // X
//		Assertions.assertEquals(0, new Game(4, convertGame(game16)).evaluateMove(opponent, move16, 0)); // O

		// DIAGONAL UP-RIGHT TESTS
		final char[][] game17 = { // IMPOSSIBLE (Just for TEST)
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.' } };
		final Move move17 = new Move(9, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(6, convertGame(game17)).evaluateMove(playerAI, move17, 0)); // X
//		Assertions.assertEquals(0, new Game(6, convertGame(game17)).evaluateMove(opponent, move17, 0)); // O
	}

	@Test
	@Order(2)
	public final void testIsWinnerManon() {

		final Player opponent = new OpponentPlayer(0); // O
		final Player playerAI = new AIPlayer((OpponentPlayer) opponent, 0, 0); // X

		final char[][] game1 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'O', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move1 = new Move(3, 5);
//		Assertions.assertEquals(1, new Game(7, convertGame(game1)).evaluateMove(playerAI, move1, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(7, convertGame(game1)).evaluateMove(opponent, move1, 0));

		final char[][] game2 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 4
				{ '.', '.', '.', '.', '.', 'O', 'O', 'O', 'O', 'X', 'X', 'X' } };// 5
		final Move move2 = new Move(8, 5);
//		Assertions.assertEquals(1, new Game(9, convertGame(game2)).evaluateMove(playerAI, move2, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(9, convertGame(game2)).evaluateMove(opponent, move2, 0));

		final char[][] game3 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move3 = new Move(1, 5);
		Assertions.assertEquals(2, new Game(2, convertGame(game3)).evaluateMove(playerAI, move3, 0));
//		Assertions.assertEquals(-1, new Game(2, convertGame(game3)).evaluateMove(opponent, move3, 0));

		final char[][] game4 = { //
				{ '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'O', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move4 = new Move(5, 5);
//		Assertions.assertEquals(1, new Game(18, convertGame(game4)).evaluateMove(playerAI, move4, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(18, convertGame(game4)).evaluateMove(opponent, move4, 0));

		final char[][] game5 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', 'O', '.' }, // 3
				{ '.', '.', '.', 'X', '.', '.', '.', 'X', 'O', '.', 'X', '.' }, // 4
				{ '.', '.', '.', 'O', 'O', '.', 'X', 'X', 'X', '.', 'O', '.' } };// 5
		final Move move5 = new Move(8, 4);
//		Assertions.assertEquals(1, new Game(13, convertGame(game5)).evaluateMove(playerAI, move5, 0));
		Assertions.assertEquals(-7, new Game(13, convertGame(game5)).evaluateMove(opponent, move5, 0));

		final char[][] game6 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move6 = new Move(4, 5);
		Assertions.assertEquals(5, new Game(5, convertGame(game6)).evaluateMove(playerAI, move6, 0));
//		Assertions.assertEquals(1, new Game(5, convertGame(game6)).evaluateMove(opponent, move6, 0));

		final char[][] game7 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O' } };// 5
		final Move move7 = new Move(10, 1);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game7)).evaluateMove(playerAI, move7, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game7)).evaluateMove(opponent, move7, 0));

		final char[][] game8 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.' }, // 4
				{ 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', '.', '.', '.' } };// 5
		final Move move8 = new Move(7, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(16, convertGame(game8)).evaluateMove(playerAI, move8, 0));
//		Assertions.assertEquals(1, new Game(16, convertGame(game8)).evaluateMove(opponent, move8, 0));

		final char[][] game9 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move9 = new Move(4, 5);
		Assertions.assertEquals(5, new Game(3, convertGame(game9)).evaluateMove(playerAI, move9, 0));
//		Assertions.assertEquals(1, new Game(3, convertGame(game9)).evaluateMove(opponent, move9, 0));

		final char[][] game10 = { //
				{ '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'O', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', 'X', 'O', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'O', 'X', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', 'X', 'O', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', 'X', 'X', 'X' }, // 4
				{ 'X', '.', '.', '.', '.', '.', 'O', 'O', 'X', 'O', 'O', 'O' } };// 5
		final Move move10 = new Move(11, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(33, convertGame(game10)).evaluateMove(playerAI, move10, 0));
//		Assertions.assertEquals(1, new Game(33, convertGame(game10)).evaluateMove(opponent, move10, 0));

		final char[][] game11 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 4
				{ 'O', '.', '.', 'X', '.', '.', '.', 'X', '.', 'O', '.', 'O' } };// 5
		final Move move11 = new Move(0, 5);
//		Assertions.assertEquals(1, new Game(6, convertGame(game11)).evaluateMove(playerAI, move11, 0));
		Assertions.assertEquals(-2, new Game(6, convertGame(game11)).evaluateMove(opponent, move11, 0));

		final char[][] game12 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'O', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', '.', 'O', 'X', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 4
				{ 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', 'X', '.' } };// 5
		final Move move12 = new Move(2, 2);
//		Assertions.assertEquals(1, new Game(12, convertGame(game12)).evaluateMove(playerAI, move12, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game12)).evaluateMove(opponent, move12, 0));

		final char[][] game13 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'O', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move13 = new Move(6, 2);
//		Assertions.assertEquals(1, new Game(10, convertGame(game13)).evaluateMove(playerAI, move13, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(10, convertGame(game13)).evaluateMove(opponent, move13, 0));

		final char[][] game14 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', '.', '.', 'O', '.', '.', '.', '.', 'O', '.', 'X' } };// 5
		final Move move14 = new Move(4, 5);
//		Assertions.assertEquals(1, new Game(4, convertGame(game14)).evaluateMove(playerAI, move14, 0));
		Assertions.assertEquals(-6, new Game(4, convertGame(game14)).evaluateMove(opponent, move14, 0));

		final char[][] game15 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 3
				{ '.', '.', 'X', '.', '.', '.', '.', '.', 'O', 'X', 'O', '.' }, // 4
				{ '.', '.', 'O', '.', '.', '.', '.', 'O', 'X', 'X', 'O', 'X' } };// 5
		final Move move15 = new Move(10, 1);
//		Assertions.assertEquals(1, new Game(13, convertGame(game15)).evaluateMove(playerAI, move15, 0));
		Assertions.assertEquals(-4, new Game(13, convertGame(game15)).evaluateMove(opponent, move15, 0));

		final char[][] game16 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'O', 'O', 'O', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'X', 'X', 'X', 'O', '.', '.', '.', '.' } };// 5
		final Move move16 = new Move(7, 4);
//		Assertions.assertEquals(1, new Game(12, convertGame(game16)).evaluateMove(playerAI, move16, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game16)).evaluateMove(opponent, move16, 0));

		final char[][] game17 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'O', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move17 = new Move(3, 1);
//		Assertions.assertEquals(1, new Game(9, convertGame(game17)).evaluateMove(playerAI, move17, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(9, convertGame(game17)).evaluateMove(opponent, move17, 0));

		final char[][] game18 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', 'O', 'O', 'X', '.', 'O', '.', '.' }, // 3
				{ '.', '.', 'X', 'X', 'O', 'O', 'X', 'O', '.', 'X', 'O', '.' }, // 4
				{ '.', 'O', 'X', 'O', 'X', 'X', 'X', 'O', '.', 'O', 'X', '.' } };// 5
		final Move move18 = new Move(5, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(25, convertGame(game18)).evaluateMove(playerAI, move18, 0));
//		Assertions.assertEquals(1, new Game(25, convertGame(game18)).evaluateMove(opponent, move18, 0));

		final char[][] game19 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', 'O', 'X' }, // 4
				{ '.', '.', '.', '.', 'O', '.', '.', 'X', 'X', 'O', 'X', 'O' } };// 5
		final Move move19 = new Move(9, 4);
//		Assertions.assertEquals(1, new Game(13, convertGame(game19)).evaluateMove(playerAI, move19, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(13, convertGame(game19)).evaluateMove(opponent, move19, 0));

		final char[][] game20 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', 'O', 'O' }, // 4
				{ '.', '.', 'X', 'O', '.', 'X', 'O', 'X', '.', '.', 'X', 'X' } };// 5
		final Move move20 = new Move(11, 0);
//		Assertions.assertEquals(1, new Game(14, convertGame(game20)).evaluateMove(playerAI, move20, 0));
		Assertions.assertEquals(-2, new Game(14, convertGame(game20)).evaluateMove(opponent, move20, 0));

		final char[][] game21 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', '.', '.', '.', 'X', '.', '.', '.', '.', '.' }, // 3
				{ 'X', '.', 'O', '.', '.', '.', 'X', 'X', '.', '.', '.', '.' }, // 4
				{ 'O', '.', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', '.', '.' } };// 5
		final Move move21 = new Move(4, 5);
//		Assertions.assertEquals(1, new Game(15, convertGame(game21)).evaluateMove(playerAI, move21, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(15, convertGame(game21)).evaluateMove(opponent, move21, 0));

		final char[][] game22 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move22 = new Move(2, 2);
//		Assertions.assertEquals(1, new Game(8, convertGame(game22)).evaluateMove(playerAI, move22, 0));
		Assertions.assertEquals(-10, new Game(8, convertGame(game22)).evaluateMove(opponent, move22, 0));

		final char[][] game23 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.' }, // 4
				{ '.', 'X', 'O', 'X', 'X', '.', 'X', 'O', 'X', 'O', 'O', '.' } };// 5
		final Move move23 = new Move(9, 2);
//		Assertions.assertEquals(1, new Game(14, convertGame(game23)).evaluateMove(playerAI, move23, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game23)).evaluateMove(opponent, move23, 0));

		final char[][] game24 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', 'X' }, // 2
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', 'X', 'O' }, // 3
				{ 'X', '.', '.', '.', '.', 'X', '.', '.', '.', 'X', 'O', 'O' }, // 4
				{ 'O', '.', '.', '.', '.', 'O', '.', '.', 'X', 'X', 'O', 'X' } };// 5
		final Move move24 = new Move(11, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(16, convertGame(game24)).evaluateMove(playerAI, move24, 0));
//		Assertions.assertEquals(1, new Game(16, convertGame(game24)).evaluateMove(opponent, move24, 0));

		final char[][] game25 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.' } };// 5

		final Move move25 = new Move(0, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game25)).evaluateMove(playerAI, move25, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game25)).evaluateMove(opponent, move25, 0));

		final char[][] game26 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move26 = new Move(2, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game26)).evaluateMove(playerAI, move26, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game26)).evaluateMove(opponent, move26, 0));

		final char[][] game27 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'O', 'X', 'O', 'X', 'X', 'X', 'O', '.' } };// 5
		final Move move27 = new Move(9, 2);
//		Assertions.assertEquals(1, new Game(13, convertGame(game27)).evaluateMove(playerAI, move27, 0));
		Assertions.assertEquals(-7, new Game(13, convertGame(game27)).evaluateMove(opponent, move27, 0));

		final char[][] game28 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', 'O', 'X', 'O', 'X', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O' }, // 4
				{ '.', '.', '.', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X' } };// 5
		final Move move28 = new Move(7, 2);
//		Assertions.assertEquals(1, new Game(24, convertGame(game28)).evaluateMove(playerAI, move28, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(24, convertGame(game28)).evaluateMove(opponent, move28, 0));

		final char[][] game29 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', '.', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'O', 'O', 'X', 'O', '.', '.', '.' } };// 5
		final Move move29 = new Move(4, 1);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game29)).evaluateMove(playerAI, move29, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game29)).evaluateMove(opponent, move29, 0));

		final char[][] game30 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O' }, // 3
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', 'O', 'X', 'X' }, // 4
				{ '.', '.', '.', 'X', '.', '.', '.', '.', 'X', 'O', 'O', 'O' } };// 5
		final Move move30 = new Move(10, 0);
//		Assertions.assertEquals(1, new Game(18, convertGame(game30)).evaluateMove(playerAI, move30, 0));
		Assertions.assertEquals(-3, new Game(18, convertGame(game30)).evaluateMove(opponent, move30, 0));

		final char[][] game31 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move31 = new Move(5, 5);
//		Assertions.assertEquals(1, new Game(8, convertGame(game31)).evaluateMove(playerAI, move31, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(8, convertGame(game31)).evaluateMove(opponent, move31, 0));

		final char[][] game32 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', '.', '.', '.', '.', '.', 'O', 'X', 'X', '.' } };// 5
		final Move move32 = new Move(10, 5);
		Assertions.assertEquals(2, new Game(5, convertGame(game32)).evaluateMove(playerAI, move32, 0));
//		Assertions.assertEquals(1, new Game(5, convertGame(game32)).evaluateMove(opponent, move32, 0));

		final char[][] game33 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'O', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move33 = new Move(3, 2);
//		Assertions.assertEquals(1, new Game(20, convertGame(game33)).evaluateMove(playerAI, move33, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(20, convertGame(game33)).evaluateMove(opponent, move33, 0));

		final char[][] game34 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O', 'O', 'X' } };// 5
		final Move move34 = new Move(8, 4);
//		Assertions.assertEquals(1, new Game(14, convertGame(game34)).evaluateMove(playerAI, move34, 0));
		Assertions.assertEquals(-6, new Game(14, convertGame(game34)).evaluateMove(opponent, move34, 0));

		final char[][] game35 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', 'X', 'O', '.' } };// 5
		final Move move35 = new Move(9, 5);
		Assertions.assertEquals(3, new Game(3, convertGame(game35)).evaluateMove(playerAI, move35, 0));
//		Assertions.assertEquals(1, new Game(3, convertGame(game35)).evaluateMove(opponent, move35, 0));

		final char[][] game36 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'X', '.', '.', '.', 'X', 'X', '.', '.', '.' } };// 5
		final Move move36 = new Move(2, 2);
//		Assertions.assertEquals(1, new Game(8, convertGame(game36)).evaluateMove(playerAI, move36, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(8, convertGame(game36)).evaluateMove(opponent, move36, 0));

		final char[][] game37 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', 'O', 'O' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', 'X', 'X' }, // 4
				{ '.', 'X', '.', 'O', '.', 'O', 'X', '.', '.', 'X', 'O', 'O' } };// 5
		final Move move37 = new Move(11, 3);
//		Assertions.assertEquals(1, new Game(14, convertGame(game37)).evaluateMove(playerAI, move37, 0));
		Assertions.assertEquals(-3, new Game(14, convertGame(game37)).evaluateMove(opponent, move37, 0));

		final char[][] game38 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 4
				{ '.', '.', 'O', 'O', 'X', 'X', 'X', 'X', '.', '.', 'O', 'O' } };// 5
		final Move move38 = new Move(7, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(10, convertGame(game38)).evaluateMove(playerAI, move38, 0));
//		Assertions.assertEquals(1, new Game(10, convertGame(game38)).evaluateMove(opponent, move38, 0));

		final char[][] game39 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', 'X' } };// 5
		final Move move39 = new Move(4, 2);
//		Assertions.assertEquals(1, new Game(7, convertGame(game39)).evaluateMove(playerAI, move39, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(7, convertGame(game39)).evaluateMove(opponent, move39, 0));

		final char[][] game40 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'O', '.', '.', '.', 'O', 'X', '.', '.' } };// 5
		final Move move40 = new Move(9, 3);
		Assertions.assertEquals(11, new Game(7, convertGame(game40)).evaluateMove(playerAI, move40, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game40)).evaluateMove(opponent, move40, 0));

	}

	@Test
	@Order(2)
	public final void testWinnerRaphael() {

		final Player opponent = new OpponentPlayer(0); // O
		final Player playerAI = new AIPlayer((OpponentPlayer) opponent, 0, 0); // X

		// RANDOM TESTS
		final char[][] game1 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move1 = new Move(5, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(12, convertGame(game1)).evaluateMove(playerAI, move1, 0));
//		Assertions.assertEquals(1, new Game(12, convertGame(game1)).evaluateMove(opponent, move1, 0));

		final char[][] game2 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', 'X', '.', '.', '.', '.' } };// 5
		final Move move2 = new Move(7, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game2)).evaluateMove(playerAI, move2, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game22)).evaluateMove(opponent, move22, 0));

		final char[][] game3 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'X', 'X', 'X', '.', 'O', '.', '.', '.' } };// 5
		final Move move3 = new Move(3, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game3)).evaluateMove(playerAI, move3, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game33)).evaluateMove(opponent, move33, 0));

		final char[][] game4 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move4 = new Move(3, 2);
//		Assertions.assertEquals(1, new Game(7, convertGame(game44)).evaluateMove(playerAI, move44, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(7, convertGame(game4)).evaluateMove(opponent, move4, 0));

		final char[][] game5 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move5 = new Move(5, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(8, convertGame(game5)).evaluateMove(playerAI, move5, 0));
//		Assertions.assertEquals(1, new Game(8, convertGame(game55)).evaluateMove(opponent, move55, 0));

		final char[][] game6 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'X', '0', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move6 = new Move(6, 2);
//		Assertions.assertEquals(1, new Game(11, convertGame(game66)).evaluateMove(playerAI, move66, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game6)).evaluateMove(opponent, move6, 0));

		final char[][] game7 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'O', 'O', 'O', 'O', 'X', 'X', '.', 'X', '.', '.', '.', '.' } };// 5
		final Move move7 = new Move(3, 5);
//		Assertions.assertEquals(1, new Game(7, convertGame(game77)).evaluateMove(playerAI, move77, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(7, convertGame(game7)).evaluateMove(opponent, move7, 0));

		final char[][] game8 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move8 = new Move(0, 2);
//		Assertions.assertEquals(1, new Game(11, convertGame(game88)).evaluateMove(playerAI, move88, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game8)).evaluateMove(opponent, move8, 0));

		final char[][] game9 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', '.', '.', '.', '.', 'X', 'X', 'X', 'X' } };// 5
		final Move move9 = new Move(11, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game9)).evaluateMove(playerAI, move9, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game99)).evaluateMove(opponent, move99, 0));

		final char[][] game10 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', 'X' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X', 'O' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', 'O' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', 'O' } };// 5
		final Move move10 = new Move(11, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game10)).evaluateMove(playerAI, move10, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game10)).evaluateMove(opponent, move10, 0));

		final char[][] game11 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', 'X', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', 'X', '.', '.' } };// 5
		final Move move11 = new Move(6, 1);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(14, convertGame(game11)).evaluateMove(playerAI, move11, 0));
//		Assertions.assertEquals(1, new Game(14, convertGame(game11)).evaluateMove(opponent, move11, 0));

		final char[][] game12 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move12 = new Move(5, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(8, convertGame(game12)).evaluateMove(playerAI, move12, 0));
//		Assertions.assertEquals(1, new Game(8, convertGame(game12)).evaluateMove(opponent, move12, 0));

		final char[][] game13 = { //
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move13 = new Move(3, 0);
//		Assertions.assertEquals(1, new Game(9, convertGame(game13)).evaluateMove(playerAI, move13, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(9, convertGame(game13)).evaluateMove(opponent, move13, 0));

		final char[][] game14 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move14 = new Move(5, 4);
		Assertions.assertEquals(9, new Game(3, convertGame(game14)).evaluateMove(playerAI, move14, 0));
//		Assertions.assertEquals(1, new Game(3, convertGame(game14)).evaluateMove(opponent, move14, 0));

		final char[][] game15 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'O', 'X', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'X', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move15 = new Move(1, 1);
//		Assertions.assertEquals(1, new Game(17, convertGame(game1515)).evaluateMove(playerAI, move1515, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(17, convertGame(game15)).evaluateMove(opponent, move15, 0));

		final char[][] game16 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move16 = new Move(4, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game16)).evaluateMove(playerAI, move16, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game16)).evaluateMove(opponent, move16, 0));

		final char[][] game17 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move17 = new Move(3, 5);
//		Assertions.assertEquals(1, new Game(1, convertGame(game17)).evaluateMove(playerAI, move17, 0));
		Assertions.assertEquals(-7, new Game(1, convertGame(game17)).evaluateMove(opponent, move17, 0));

		final char[][] game18 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'O', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'O', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'O', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move18 = new Move(0, 2);
//		Assertions.assertEquals(1, new Game(7, convertGame(game18)).evaluateMove(playerAI, move18, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(7, convertGame(game18)).evaluateMove(opponent, move18, 0));

		final char[][] game19 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', 'X', 'X' } };// 5
		final Move move19 = new Move(10, 2);
//		Assertions.assertEquals(1, new Game(11, convertGame(game19)).evaluateMove(playerAI, move19, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game19)).evaluateMove(opponent, move19, 0));

		final char[][] game20 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', 'X', '.', '.', '.', '.' } };// 5
		final Move move20 = new Move(7, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game20)).evaluateMove(playerAI, move20, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game20)).evaluateMove(opponent, move20, 0));

		final char[][] game21 = { //
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move21 = new Move(6, 0);
//		Assertions.assertEquals(1, new Game(11, convertGame(game21)).evaluateMove(playerAI, move21, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game21)).evaluateMove(opponent, move21, 0));

		final char[][] game22 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'O', 'X', 'X', 'X', 'O', 'O' } };// 5
		final Move move22 = new Move(11, 4);
//		Assertions.assertEquals(1, new Game(20, convertGame(game22)).evaluateMove(playerAI, move22, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(20, convertGame(game22)).evaluateMove(opponent, move22, 0));

		final char[][] game23 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '0', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', '.', 'O', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move23 = new Move(4, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(9, convertGame(game23)).evaluateMove(playerAI, move23, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game23)).evaluateMove(opponent, move23, 0));

		final char[][] game24 = { //
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move24 = new Move(3, 1);
//		Assertions.assertEquals(1, new Game(13, convertGame(game24)).evaluateMove(playerAI, move24, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(13, convertGame(game24)).evaluateMove(opponent, move24, 0));

		final char[][] game25 = { //
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move25 = new Move(3, 0);
//		Assertions.assertEquals(1, new Game(11, convertGame(game25)).evaluateMove(playerAI, move25, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game25)).evaluateMove(opponent, move25, 0));

		final char[][] game26 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 2
				{ 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 3
				{ 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 4
				{ 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' } };// 5
		final Move move26 = new Move(11, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game26)).evaluateMove(playerAI, move26, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game26)).evaluateMove(opponent, move26, 0));

		final char[][] game27 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', '.', '.', '.' } };// 5
		final Move move27 = new Move(8, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game27)).evaluateMove(playerAI, move27, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game27)).evaluateMove(opponent, move27, 0));

		final char[][] game28 = { //
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'O', 'X', '.', '.' } };// 5
		final Move move28 = new Move(4, 0);
//		Assertions.assertEquals(1, new Game(11, convertGame(game28)).evaluateMove(playerAI, move28, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game28)).evaluateMove(opponent, move28, 0));

		final char[][] game29 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'X', 'O', 'O', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'X', 'O', 'X', '.', '.', '.', '.' } };// 5
		final Move move29 = new Move(7, 1);
//		Assertions.assertEquals(1, new Game(11, convertGame(game29)).evaluateMove(playerAI, move29, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game29)).evaluateMove(opponent, move29, 0));

		final char[][] game30 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', '.', '.', '.' } };// 5
		final Move move30 = new Move(8, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game30)).evaluateMove(playerAI, move30, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game30)).evaluateMove(opponent, move30, 0));

		final char[][] game31 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O', 'O', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'O', 'X', 'O' } };// 5
		final Move move31 = new Move(11, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(19, convertGame(game31)).evaluateMove(playerAI, move31, 0));
//		Assertions.assertEquals(1, new Game(19, convertGame(game31)).evaluateMove(opponent, move31, 0));

		final char[][] game32 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', '.', '.', '.' } };// 5
		final Move move32 = new Move(7, 1);
//		Assertions.assertEquals(1, new Game(12, convertGame(game32)).evaluateMove(playerAI, move32, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game32)).evaluateMove(opponent, move32, 0));

		final char[][] game33 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'O', '.', 'X', 'X', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', '.', 'X', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'O', '.', 'O', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', 'O', 'O', 'X', 'X', '.', '.', '.' } };// 5
		final Move move33 = new Move(5, 2);
//		Assertions.assertEquals(1, new Game(15, convertGame(game33)).evaluateMove(playerAI, move33, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(15, convertGame(game33)).evaluateMove(opponent, move33, 0));

		final char[][] game34 = { //
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', '.', '.', '.' } };// 5
		final Move move34 = new Move(7, 1);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game34)).evaluateMove(playerAI, move34, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game34)).evaluateMove(opponent, move34, 0));

		final char[][] game35 = { //
				{ '.', '.', '.', 'O', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 0
				{ '.', '.', '.', 'O', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', 'O', 'O', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', 'X', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', '.', '.', '.', 'O', 'X', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', '.', '.', 'O', 'O', 'X', '.', '.', '.' } };// 5
		final Move move35 = new Move(3, 0);
//		Assertions.assertEquals(1, new Game(17, convertGame(game35)).evaluateMove(playerAI, move35, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(17, convertGame(game35)).evaluateMove(opponent, move35, 0));
	}

	@Test
	@Order(3)
	public final void testWinnerAnthony() {

		final Player opponent = new OpponentPlayer(0); // O
		final Player playerAI = new AIPlayer((OpponentPlayer) opponent, 0, 0); // X

		final char[][] game0 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'O', 'X', 'X', 'X', 'O', 'O' } };// 5
		final Move move0 = new Move(11, 4);
//		Assertions.assertEquals(1, new Game(20, convertGame(game100)).evaluateMove(playerAI, move100, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(20, convertGame(game0)).evaluateMove(opponent, move0, 0));

		final char[][] game1 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'O', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move1 = new Move(3, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(9, convertGame(game1)).evaluateMove(playerAI, move1, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game1)).evaluateMove(opponent, move1, 0));

		final char[][] game2 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'O', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'X', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move2 = new Move(1, 2);
//		Assertions.assertEquals(1, new Game(14, convertGame(game2)).evaluateMove(playerAI, move2, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game2)).evaluateMove(opponent, move2, 0));

		final char[][] game3 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'O', 'X', 'X', 'X', 'X', '.', '.' } };// 5
		final Move move3 = new Move(9, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(9, convertGame(game3)).evaluateMove(playerAI, move3, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game3)).evaluateMove(opponent, move3, 0));

		final char[][] game4 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.' } };// 5
		final Move move4 = new Move(5, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game4)).evaluateMove(playerAI, move4, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game4)).evaluateMove(opponent, move4, 0));

		final char[][] game5 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'O', 'X', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'X', 'X', 'O', 'O', 'O', 'O', '.', '.', '.' } };// 5
		final Move move5 = new Move(8, 5);
//		Assertions.assertEquals(1, new Game(12, convertGame(game5)).evaluateMove(playerAI, move5, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game5)).evaluateMove(opponent, move5, 0));

		final char[][] game6 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', '.', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', '.', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'O', 'X', 'O', '.', '.', '.', '.' } };// 5
		final Move move6 = new Move(6, 3);
		Assertions.assertEquals(16, new Game(9, convertGame(game6)).evaluateMove(playerAI, move6, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game6)).evaluateMove(opponent, move6, 0));

		final char[][] game7 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move7 = new Move(1, 4);
		Assertions.assertEquals(6, new Game(5, convertGame(game7)).evaluateMove(playerAI, move7, 0));
//		Assertions.assertEquals(1, new Game(5, convertGame(game7)).evaluateMove(opponent, move7, 0));

		final char[][] game8 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', 'O' } };// 5
		final Move move8 = new Move(11, 5);
//		Assertions.assertEquals(1, new Game(2, convertGame(game8)).evaluateMove(playerAI, move8, 0));
		Assertions.assertEquals(-3, new Game(2, convertGame(game8)).evaluateMove(opponent, move8, 0));

		final char[][] game9 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'X', '.', 'O', 'X', 'X', 'X' }, // 4
				{ '.', '.', '.', 'X', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'O' } };// 5
		final Move move9 = new Move(11, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(17, convertGame(game9)).evaluateMove(playerAI, move9, 0));
//		Assertions.assertEquals(1, new Game(17, convertGame(game9)).evaluateMove(opponent, move9, 0));

		final char[][] game10 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move10 = new Move(2, 5);
		Assertions.assertEquals(8, new Game(5, convertGame(game10)).evaluateMove(playerAI, move10, 0));
//		Assertions.assertEquals(1, new Game(5, convertGame(game10)).evaluateMove(opponent, move10, 0));

		final char[][] game11 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', 'O', '.', '.', '.', '.' } };// 5
		final Move move11 = new Move(5, 1);
//		Assertions.assertEquals(1, new Game(13, convertGame(game11)).evaluateMove(playerAI, move11, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(13, convertGame(game11)).evaluateMove(opponent, move11, 0));

		final char[][] game12 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'X', 'O', 'X', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', 'O', 'X', 'O', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'O', 'X', 'O', 'X', 'O', '.', '.', '.' } };// 5
		final Move move12 = new Move(7, 2);
//		Assertions.assertEquals(1, new Game(15, convertGame(game12)).evaluateMove(playerAI, move12, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(15, convertGame(game12)).evaluateMove(opponent, move12, 0));

		final char[][] game13 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 4
				{ 'O', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', 'X', '.' } };// 5
		final Move move13 = new Move(0, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game13)).evaluateMove(playerAI, move13, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game13)).evaluateMove(opponent, move13, 0));

		final char[][] game14 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'X', '.' } };// 5
		final Move move14 = new Move(10, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game14)).evaluateMove(playerAI, move14, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game14)).evaluateMove(opponent, move14, 0));

		final char[][] game15 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'X' } };// 5
		final Move move15 = new Move(11, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game15)).evaluateMove(playerAI, move15, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game15)).evaluateMove(opponent, move15, 0));

		final char[][] game16 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'X', 'X', 'X', 'O', 'O', 'O', '.', '.', '.' } };// 5
		final Move move16 = new Move(2, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game16)).evaluateMove(playerAI, move16, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game16)).evaluateMove(opponent, move16, 0));

		final char[][] game17 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'O', 'O', 'X', 'X', 'X', '.', '.', '.', '.' } };// 5
		final Move move17 = new Move(4, 5);
//		Assertions.assertEquals(1, new Game(14, convertGame(game17)).evaluateMove(playerAI, move17, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game17)).evaluateMove(opponent, move17, 0));

		final char[][] game18 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'O', 'O', 'O', '.', '.', '.', '.' } };// 5
		final Move move18 = new Move(7, 5);
//		Assertions.assertEquals(1, new Game(6, convertGame(game18)).evaluateMove(playerAI, move18, 0));
		Assertions.assertEquals(-8, new Game(6, convertGame(game18)).evaluateMove(opponent, move18, 0));

		final char[][] game19 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move19 = new Move(4, 4);
//		Assertions.assertEquals(1, new Game(2, convertGame(game19)).evaluateMove(playerAI, move19, 0));
		Assertions.assertEquals(-9, new Game(2, convertGame(game19)).evaluateMove(opponent, move19, 0));

		final char[][] game20 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'X', 'X', 'X', 'O', 'O', 'X', '.', '.', '.', '.' } };// 5
		final Move move20 = new Move(4, 2);
//		Assertions.assertEquals(1, new Game(14, convertGame(game20)).evaluateMove(playerAI, move20, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game20)).evaluateMove(opponent, move20, 0));

		final char[][] game21 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'O', 'X', 'X', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', 'O', '.', '.', '.', '.' } };// 5
		final Move move21 = new Move(6, 2);
//		Assertions.assertEquals(1, new Game(12, convertGame(game21)).evaluateMove(playerAI, move21, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game21)).evaluateMove(opponent, move21, 0));

		final char[][] game22 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', 'O', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', 'O', '.', '.', '.', '.' } };// 5
		final Move move22 = new Move(5, 1);
//		Assertions.assertEquals(1, new Game(11, convertGame(game22)).evaluateMove(playerAI, move22, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game22)).evaluateMove(opponent, move22, 0));

		final char[][] game23 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move23 = new Move(2, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(10, convertGame(game23)).evaluateMove(playerAI, move23, 0));
//		Assertions.assertEquals(1, new Game(10, convertGame(game23)).evaluateMove(opponent, move23, 0));

		final char[][] game24 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move24 = new Move(5, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(10, convertGame(game24)).evaluateMove(playerAI, move24, 0));
//		Assertions.assertEquals(1, new Game(10, convertGame(game24)).evaluateMove(opponent, move24, 0));

		final char[][] game25 = { // Y
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', 'X', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', 'O', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'O', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move25 = new Move(5, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game25)).evaluateMove(playerAI, move25, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game25)).evaluateMove(opponent, move25, 0));

		final char[][] game26 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.' } };// 5
		final Move move26 = new Move(1, 4);
		Assertions.assertEquals(6, new Game(3, convertGame(game26)).evaluateMove(playerAI, move26, 0));
//		Assertions.assertEquals(1, new Game(3, convertGame(game26)).evaluateMove(opponent, move26, 0));

		final char[][] game27 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move27 = new Move(5, 5);
//		Assertions.assertEquals(1, new Game(6, convertGame(game27)).evaluateMove(playerAI, move27, 0));
		Assertions.assertEquals(-3, new Game(6, convertGame(game27)).evaluateMove(opponent, move27, 0));

		final char[][] game28 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'X', 'O', 'X', 'O', '.', '.', '.', '.' } };// 5
		final Move move28 = new Move(7, 5);
//		Assertions.assertEquals(1, new Game(12, convertGame(game28)).evaluateMove(playerAI, move28, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game28)).evaluateMove(opponent, move28, 0));

		final char[][] game29 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', '.', '.', 'X' } };// 5
		final Move move29 = new Move(11, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game29)).evaluateMove(playerAI, move29, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game29)).evaluateMove(opponent, move29, 0));

		final char[][] game30 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'O', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'X', 'X', 'X', 'O', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'X', 'O', 'O', 'O', 'X', 'O', '.', '.' } };// 5
		final Move move30 = new Move(3, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(21, convertGame(game30)).evaluateMove(playerAI, move30, 0));
//		Assertions.assertEquals(1, new Game(21, convertGame(game30)).evaluateMove(opponent, move30, 0));

		final char[][] game31 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', 'O', '.', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'O', 'X', 'X', 'X', 'X', '.', '.', '.' } };// 5
		final Move move31 = new Move(8, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(14, convertGame(game31)).evaluateMove(playerAI, move31, 0));
//		Assertions.assertEquals(1, new Game(14, convertGame(game31)).evaluateMove(opponent, move31, 0));

		final char[][] game32 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move32 = new Move(5, 4);
//		Assertions.assertEquals(1, new Game(6, convertGame(game32)).evaluateMove(playerAI, move32, 0));
		Assertions.assertEquals(-10, new Game(6, convertGame(game32)).evaluateMove(opponent, move32, 0));

		final char[][] game33 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'X', 'X', 'X', 'X', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'O', 'O', 'X', '.', '.', '.', '.' } };// 5
		final Move move33 = new Move(7, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game33)).evaluateMove(playerAI, move33, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game33)).evaluateMove(opponent, move33, 0));

		final char[][] game34 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move34 = new Move(2, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game34)).evaluateMove(playerAI, move34, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game34)).evaluateMove(opponent, move34, 0));

		final char[][] game35 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'X', 'O' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'O' } };// 5
		final Move move35 = new Move(8, 0);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(20, convertGame(game35)).evaluateMove(playerAI, move35, 0));
//		Assertions.assertEquals(1, new Game(20, convertGame(game35)).evaluateMove(opponent, move35, 0));

		final char[][] game36 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'O', 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move36 = new Move(2, 3);
//		Assertions.assertEquals(1, new Game(12, convertGame(game36)).evaluateMove(playerAI, move36, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game36)).evaluateMove(opponent, move36, 0));

		final char[][] game37 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', 'X', 'O', 'O', 'O', '.', '.', '.' } };// 5
		final Move move37 = new Move(8, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(9, convertGame(game37)).evaluateMove(playerAI, move37, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game37)).evaluateMove(opponent, move37, 0));

		final char[][] game38 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move38 = new Move(4, 5);
		Assertions.assertEquals(11, new Game(5, convertGame(game38)).evaluateMove(playerAI, move38, 0));
//		Assertions.assertEquals(1, new Game(5, convertGame(game38)).evaluateMove(opponent, move38, 0));

		final char[][] game39 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.' }, // 4
				{ '.', '.', 'X', '.', '.', '.', '.', 'O', 'X', 'X', 'O', '.' } };// 5
		final Move move39 = new Move(10, 5);
//		Assertions.assertEquals(1, new Game(6, convertGame(game39)).evaluateMove(playerAI, move39, 0));
		Assertions.assertEquals(-2, new Game(6, convertGame(game39)).evaluateMove(opponent, move39, 0));

		final char[][] game40 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', 'X', 'O', 'X', '.', '.', '.', '.' } };// 5
		final Move move40 = new Move(7, 5);
		Assertions.assertEquals(6, new Game(7, convertGame(game40)).evaluateMove(playerAI, move40, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game40)).evaluateMove(opponent, move40, 0));

		final char[][] game41 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'X', 'X', 'X', 'O', '.', '.', 'O', '.', '.', '.' } };// 5
		final Move move41 = new Move(2, 4);
		Assertions.assertEquals(8, new Game(7, convertGame(game41)).evaluateMove(playerAI, move41, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game41)).evaluateMove(opponent, move41, 0));

		final char[][] game42 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'O', 'O', 'O', '.', '.', '.', '.' } };// 5
		final Move move42 = new Move(7, 5);
//		Assertions.assertEquals(1, new Game(7, convertGame(game42)).evaluateMove(playerAI, move42, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(7, convertGame(game42)).evaluateMove(opponent, move42, 0));

		final char[][] game43 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'O', 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move43 = new Move(6, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(8, convertGame(game43)).evaluateMove(playerAI, move43, 0));
//		Assertions.assertEquals(1, new Game(8, convertGame(game43)).evaluateMove(opponent, move43, 0));

		final char[][] game44 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move44 = new Move(3, 3);
//		Assertions.assertEquals(1, new Game(6, convertGame(game44)).evaluateMove(playerAI, move44, 0));
		Assertions.assertEquals(-12, new Game(6, convertGame(game44)).evaluateMove(opponent, move44, 0));

		final char[][] game45 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'O', 'O', 'O', '.', '.', '.', '.' } };// 5
		final Move move45 = new Move(7, 5);
//		Assertions.assertEquals(1, new Game(9, convertGame(game45)).evaluateMove(playerAI, move45, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(9, convertGame(game45)).evaluateMove(opponent, move45, 0));

		final char[][] game46 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', 'X', 'X', 'X', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'O', 'O', 'X', 'O', '.', '.', '.' } };// 5
		final Move move46 = new Move(4, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game46)).evaluateMove(playerAI, move46, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game46)).evaluateMove(opponent, move46, 0));

		final char[][] game47 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move47 = new Move(3, 3);
		Assertions.assertEquals(14, new Game(7, convertGame(game47)).evaluateMove(playerAI, move47, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game47)).evaluateMove(opponent, move47, 0));

		final char[][] game48 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', '.', '.', 'O', 'O', 'X', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'X', 'O', 'X', 'X', 'X', 'O', '.', '.', '.' } };// 5
		final Move move48 = new Move(7, 2);
//		Assertions.assertEquals(1, new Game(14, convertGame(game48)).evaluateMove(playerAI, move48, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game48)).evaluateMove(opponent, move48, 0));

		final char[][] game49 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'X', 'O', 'X', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'O', 'X', 'O', 'O', '.', '.', '.' } };// 5
		final Move move49 = new Move(5, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(15, convertGame(game49)).evaluateMove(playerAI, move49, 0));
//		Assertions.assertEquals(1, new Game(15, convertGame(game49)).evaluateMove(opponent, move49, 0));

		final char[][] game50 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move50 = new Move(3, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game50)).evaluateMove(playerAI, move50, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game50)).evaluateMove(opponent, move50, 0));

		final char[][] game51 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'O', 'O', 'X', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'X', 'X', 'X', 'O', '.', '.', '.', '.' } };// 5
		final Move move51 = new Move(3, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(9, convertGame(game51)).evaluateMove(playerAI, move51, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game51)).evaluateMove(opponent, move51, 0));

		final char[][] game52 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'O', 'O', 'X', 'X', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'O', '.', '.', '.' } };// 5
		final Move move52 = new Move(6, 1);
//		Assertions.assertEquals(1, new Game(13, convertGame(game52)).evaluateMove(playerAI, move52, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(13, convertGame(game52)).evaluateMove(opponent, move52, 0));

		final char[][] game53 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move53 = new Move(3, 4);
		Assertions.assertEquals(9, new Game(3, convertGame(game53)).evaluateMove(playerAI, move53, 0));
//		Assertions.assertEquals(1, new Game(3, convertGame(game53)).evaluateMove(opponent, move53, 0));

		final char[][] game54 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', '.', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move54 = new Move(4, 3);
//		Assertions.assertEquals(1, new Game(6, convertGame(game54)).evaluateMove(playerAI, move54, 0));
		Assertions.assertEquals(-10, new Game(6, convertGame(game54)).evaluateMove(opponent, move54, 0));

		final char[][] game55 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move55 = new Move(2, 5);
		Assertions.assertEquals(2, new Game(4, convertGame(game55)).evaluateMove(playerAI, move55, 0));
//		Assertions.assertEquals(1, new Game(4, convertGame(game55)).evaluateMove(opponent, move55, 0));

		final char[][] game56 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', '.', '.', 'X', 'X', 'X', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'X', 'O', 'X', 'O', 'O', 'O', 'O' } };// 5
		final Move move56 = new Move(11, 5);
//		Assertions.assertEquals(1, new Game(14, convertGame(game56)).evaluateMove(playerAI, move56, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game56)).evaluateMove(opponent, move56, 0));

		final char[][] game57 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'X', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'O', 'X', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'O', 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move57 = new Move(1, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(17, convertGame(game57)).evaluateMove(playerAI, move57, 0));
//		Assertions.assertEquals(1, new Game(17, convertGame(game57)).evaluateMove(opponent, move57, 0));

		final char[][] game58 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'O', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move58 = new Move(3, 1);
//		Assertions.assertEquals(1, new Game(14, convertGame(game58)).evaluateMove(playerAI, move58, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game58)).evaluateMove(opponent, move58, 0));

		final char[][] game59 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'X', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', 'X', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', 'O', '.' } };// 5
		final Move move59 = new Move(7, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(15, convertGame(game59)).evaluateMove(playerAI, move59, 0));
//		Assertions.assertEquals(1, new Game(15, convertGame(game59)).evaluateMove(opponent, move59, 0));

		final char[][] game60 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', 'X', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move60 = new Move(6, 2);
//		Assertions.assertEquals(1, new Game(12, convertGame(game60)).evaluateMove(playerAI, move60, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(12, convertGame(game60)).evaluateMove(opponent, move60, 0));

		final char[][] game61 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', 'O', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'O', 'X' } };// 5
		final Move move61 = new Move(7, 4);
//		Assertions.assertEquals(1, new Game(18, convertGame(game61)).evaluateMove(playerAI, move61, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(18, convertGame(game61)).evaluateMove(opponent, move61, 0));

		final char[][] game62 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O', 'O', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'X', 'O', 'X' } };// 5
		final Move move62 = new Move(6, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(20, convertGame(game62)).evaluateMove(playerAI, move62, 0));
//		Assertions.assertEquals(1, new Game(20, convertGame(game62)).evaluateMove(opponent, move62, 0));

		final char[][] game63 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', 'X' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O', 'O', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', 'O', 'X' } };// 5
		final Move move63 = new Move(9, 2);
//		Assertions.assertEquals(1, new Game(20, convertGame(game63)).evaluateMove(playerAI, move63, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(20, convertGame(game63)).evaluateMove(opponent, move63, 0));

		final char[][] game64 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move64 = new Move(1, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game64)).evaluateMove(playerAI, move64, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game64)).evaluateMove(opponent, move64, 0));

		final char[][] game65 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', 'X', 'X' }, // 4
				{ '.', '.', '.', '.', 'X', '.', '.', 'X', 'X', 'O', 'O', 'O' } };// 5
		final Move move65 = new Move(10, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(15, convertGame(game65)).evaluateMove(playerAI, move65, 0));
//		Assertions.assertEquals(1, new Game(15, convertGame(game65)).evaluateMove(opponent, move65, 0));

		final char[][] game66 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'O', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move66 = new Move(1, 3);
//		Assertions.assertEquals(1, new Game(14, convertGame(game66)).evaluateMove(playerAI, move66, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game66)).evaluateMove(opponent, move66, 0));

		final char[][] game67 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ 'O', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'X', 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'O', 'O', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move67 = new Move(1, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(19, convertGame(game67)).evaluateMove(playerAI, move67, 0));
//		Assertions.assertEquals(1, new Game(19, convertGame(game67)).evaluateMove(opponent, move67, 0));

		final char[][] game68 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move68 = new Move(1, 1);
//		Assertions.assertEquals(1, new Game(14, convertGame(game68)).evaluateMove(playerAI, move68, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(14, convertGame(game68)).evaluateMove(opponent, move68, 0));

		final char[][] game69 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move69 = new Move(0, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game69)).evaluateMove(playerAI, move69, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game69)).evaluateMove(opponent, move69, 0));

		final char[][] game70 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'X', 'O', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', 'X', 'X' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', 'X', 'X', 'O' } };// 5
		final Move move70 = new Move(9, 2);
//		Assertions.assertEquals(1, new Game(16, convertGame(game70)).evaluateMove(playerAI, move70, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(16, convertGame(game70)).evaluateMove(opponent, move70, 0));

		final char[][] game71 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'X', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move71 = new Move(1, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(12, convertGame(game71)).evaluateMove(playerAI, move71, 0));
//		Assertions.assertEquals(1, new Game(12, convertGame(game71)).evaluateMove(opponent, move71, 0));

		final char[][] game72 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move72 = new Move(3, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game72)).evaluateMove(playerAI, move72, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game72)).evaluateMove(opponent, move72, 0));

		final char[][] game73 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'O', 'O', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O', 'X', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'x', 'X', 'O', 'X', 'X' } };// 5
		final Move move73 = new Move(7, 3);
//		Assertions.assertEquals(1, new Game(15, convertGame(game73)).evaluateMove(playerAI, move73, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(15, convertGame(game73)).evaluateMove(opponent, move73, 0));

		final char[][] game74 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move74 = new Move(0, 2);
//		Assertions.assertEquals(1, new Game(10, convertGame(game74)).evaluateMove(playerAI, move74, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(10, convertGame(game74)).evaluateMove(opponent, move74, 0));

		final char[][] game75 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'O', 'X', 'O', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'O', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move75 = new Move(3, 2);
//		Assertions.assertEquals(1, new Game(15, convertGame(game75)).evaluateMove(playerAI, move75, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(15, convertGame(game75)).evaluateMove(opponent, move75, 0));

		final char[][] game76 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X', '.', '.' }, // 4
				{ '.', '.', '.', 'X', '.', 'O', 'X', 'O', 'O', 'O', 'O', '.' } };// 5
		final Move move76 = new Move(8, 5);
//		Assertions.assertEquals(1, new Game(9, convertGame(game76)).evaluateMove(playerAI, move76, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(9, convertGame(game76)).evaluateMove(opponent, move76, 0));

		final char[][] game77 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'x', 'X', 'O', 'X', 'O', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'X', 'X', 'X', 'O', 'O', 'X', '.', '.', '.', '.' } };// 5
		final Move move77 = new Move(1, 3);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(21, convertGame(game77)).evaluateMove(playerAI, move77, 0));
//		Assertions.assertEquals(1, new Game(21, convertGame(game77)).evaluateMove(opponent, move77, 0));

		final char[][] game78 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move78 = new Move(2, 2);
//		Assertions.assertEquals(1, new Game(12, convertGame(game78)).evaluateMove(playerAI, move78, 0));
		Assertions.assertEquals(-5, new Game(12, convertGame(game78)).evaluateMove(opponent, move78, 0));

		final char[][] game79 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'X', 'X', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'X', 'O', 'X', 'O', 'O', 'O', 'X', '.', '.', '.', '.' } };// 5
		final Move move79 = new Move(6, 3);
		Assertions.assertEquals(11, new Game(11, convertGame(game79)).evaluateMove(playerAI, move79, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game79)).evaluateMove(opponent, move79, 0));

		final char[][] game80 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', 'O', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', 'X', '.' } };// 5
		final Move move80 = new Move(10, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(11, convertGame(game80)).evaluateMove(playerAI, move80, 0));
//		Assertions.assertEquals(1, new Game(11, convertGame(game80)).evaluateMove(opponent, move80, 0));

		final char[][] game81 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', 'O', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'O', 'X', 'X', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'O', 'O', 'X', 'X', '.', '.', '.' } };// 5
		final Move move81 = new Move(8, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game81)).evaluateMove(playerAI, move81, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game81)).evaluateMove(opponent, move81, 0));

		final char[][] game82 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'O', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move82 = new Move(2, 2);
//		Assertions.assertEquals(1, new Game(10, convertGame(game82)).evaluateMove(playerAI, move82, 0));
		Assertions.assertEquals(-9, new Game(10, convertGame(game82)).evaluateMove(opponent, move82, 0));

		final char[][] game83 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'X', '.' } };// 5
		final Move move83 = new Move(8, 5);
		Assertions.assertEquals(11, new Game(5, convertGame(game83)).evaluateMove(playerAI, move83, 0));
//		Assertions.assertEquals(1, new Game(5, convertGame(game83)).evaluateMove(opponent, move83, 0));

		final char[][] game84 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'O', 'X', '.', '.', 'O', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', 'X', 'X', 'O', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'O', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move84 = new Move(2, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(14, convertGame(game84)).evaluateMove(playerAI, move84, 0));
//		Assertions.assertEquals(1, new Game(14, convertGame(game84)).evaluateMove(opponent, move84, 0));

		final char[][] game85 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', 'O', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'O', 'X', 'X', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move85 = new Move(4, 3);
//		Assertions.assertEquals(1, new Game(13, convertGame(game85)).evaluateMove(playerAI, move85, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(13, convertGame(game85)).evaluateMove(opponent, move85, 0));

		final char[][] game86 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', 'X', 'O', 'O', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', 'X', 'O', 'X', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', 'O', '.', '.' } };// 5
		final Move move86 = new Move(6, 2);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game86)).evaluateMove(playerAI, move86, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game86)).evaluateMove(opponent, move86, 0));

		final char[][] game87 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move87 = new Move(4, 1);
//		Assertions.assertEquals(1, new Game(9, convertGame(game87)).evaluateMove(playerAI, move87, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(9, convertGame(game87)).evaluateMove(opponent, move87, 0));

		final char[][] game88 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', 'O', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'O' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', 'O', 'O', 'X', 'X' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'O', 'O' }, // 3
				{ '.', '.', '.', '.', '.', '.', '.', 'X', 'X', 'O', 'X', 'O' }, // 4
				{ '.', '.', '.', '.', '.', '.', '.', 'O', 'X', 'X', 'X', 'O' } };// 5
		final Move move88 = new Move(7, 3);
//		Assertions.assertEquals(1, new Game(24, convertGame(game88)).evaluateMove(playerAI, move88, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(24, convertGame(game88)).evaluateMove(opponent, move88, 0));

		final char[][] game89 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', 'X', 'X', 'X', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'X', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move89 = new Move(5, 4);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(13, convertGame(game89)).evaluateMove(playerAI, move89, 0));
//		Assertions.assertEquals(1, new Game(13, convertGame(game89)).evaluateMove(opponent, move89, 0));

		final char[][] game90 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', 'X', 'O', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', 'X', 'O', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move90 = new Move(6, 4);
		Assertions.assertEquals(5, new Game(9, convertGame(game90)).evaluateMove(playerAI, move90, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game90)).evaluateMove(opponent, move90, 0));

		final char[][] game91 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'O', 'X', 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move91 = new Move(3, 5);
//		Assertions.assertEquals(1, new Game(9, convertGame(game91)).evaluateMove(playerAI, move91, 0));
		Assertions.assertEquals(-3, new Game(9, convertGame(game91)).evaluateMove(opponent, move91, 0));

		final char[][] game92 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'O', 'O', 'X', 'X', 'X', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'X', 'X', 'O', 'O', 'O', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'X', 'O', 'X', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move92 = new Move(3, 3);
//		Assertions.assertEquals(1, new Game(16, convertGame(game92)).evaluateMove(playerAI, move92, 0));
		Assertions.assertEquals(-7, new Game(16, convertGame(game92)).evaluateMove(opponent, move92, 0));

		final char[][] game93 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', '.', 'O', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'O', 'X', '.', '.', '.', '.', '.' } };// 5
		final Move move93 = new Move(5, 4);
//		Assertions.assertEquals(1, new Game(6, convertGame(game93)).evaluateMove(playerAI, move93, 0));
		Assertions.assertEquals(-6, new Game(6, convertGame(game93)).evaluateMove(opponent, move93, 0));

		final char[][] game94 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', '.', '.', 'O', 'X', 'X', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', '.', '.', 'X', 'O', 'X', 'O', '.', '.', '.' } };// 5
		final Move move94 = new Move(6, 4);
		Assertions.assertEquals(11, new Game(9, convertGame(game94)).evaluateMove(playerAI, move94, 0));
//		Assertions.assertEquals(1, new Game(9, convertGame(game94)).evaluateMove(opponent, move94, 0));

		final char[][] game95 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', 'O', '.', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'X', 'O', 'O', '.', '.', '.', '.', '.' } };// 5
		final Move move95 = new Move(6, 5);
//		Assertions.assertEquals(1, new Game(8, convertGame(game95)).evaluateMove(playerAI, move95, 0));
		Assertions.assertEquals(-5, new Game(8, convertGame(game95)).evaluateMove(opponent, move95, 0));

		final char[][] game96 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'X', 'X', '.', '.', 'O', '.', '.', '.', '.' }, // 4
				{ '.', '.', 'X', 'O', 'O', 'O', 'X', 'O', 'X', '.', '.', '.' } };// 5
		final Move move96 = new Move(7, 4);
//		Assertions.assertEquals(1, new Game(10, convertGame(game96)).evaluateMove(playerAI, move96, 0));
		Assertions.assertEquals(-7, new Game(10, convertGame(game96)).evaluateMove(opponent, move96, 0));

		final char[][] game97 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', '.', '.', 'O', 'X', 'X', 'X', 'X', '.', '.', '.', '.' } };// 5
		final Move move97 = new Move(7, 5);
		Assertions.assertEquals(Integer.MAX_VALUE, new Game(7, convertGame(game97)).evaluateMove(playerAI, move97, 0));
//		Assertions.assertEquals(1, new Game(7, convertGame(game97)).evaluateMove(opponent, move97, 0));

		final char[][] game98 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ 'X', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 3
				{ 'X', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 4
				{ 'X', 'X', 'O', 'O', 'O', '.', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move98 = new Move(0, 2);
//		Assertions.assertEquals(1, new Game(11, convertGame(game98)).evaluateMove(playerAI, move98, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(11, convertGame(game98)).evaluateMove(opponent, move98, 0));

		final char[][] game99 = { // Y
				{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 0
				{ '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 1
				{ '.', 'O', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // 2
				{ '.', 'O', 'X', 'O', '.', 'X', '.', '.', '.', '.', '.', '.' }, // 3
				{ '.', 'X', 'X', 'O', 'O', 'X', '.', '.', '.', '.', '.', '.' }, // 4
				{ '.', 'O', 'X', 'O', 'X', 'X', '.', '.', '.', '.', '.', '.' } };// 5
		final Move move99 = new Move(1, 1);
//		Assertions.assertEquals(1, new Game(17, convertGame(game99)).evaluateMove(playerAI, move99, 0));
		Assertions.assertEquals(Integer.MIN_VALUE, new Game(17, convertGame(game99)).evaluateMove(opponent, move99, 0));

}
}
