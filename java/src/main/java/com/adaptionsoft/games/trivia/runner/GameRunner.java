
package com.adaptionsoft.games.trivia.runner;
import java.io.*;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) throws IOException {


		File outputFile =
				new File( "src/test/java/com/adaptionsoft/games/trivia/snapshots/2/output.txt");
		outputFile.createNewFile();

		PrintStream out = new PrintStream(outputFile);
		System.setOut(out);

		File csvFile =
				new File( "src/test/java/com/adaptionsoft/games/trivia/snapshots/2/csv.txt");
		outputFile.createNewFile();
		PrintWriter csvOut = new PrintWriter(csvFile);


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

			csvOut.println(roll + "," + isCorrect);
		} while (notAWinner);

		csvOut.close();


		
	}
}
