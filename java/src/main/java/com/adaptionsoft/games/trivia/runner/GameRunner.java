
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.util.Random;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args){

		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = new Random();

		do {
			var roll = rand.nextInt(5) + 1;
			aGame.roll(roll);

			boolean isCorrect = rand.nextInt(6) != 1;
			if (isCorrect) {
				notAWinner = aGame.wasCorrectlyAnswered();
			} else {
				notAWinner = aGame.wrongAnswer();
			}
		} while (notAWinner);
	}
}
