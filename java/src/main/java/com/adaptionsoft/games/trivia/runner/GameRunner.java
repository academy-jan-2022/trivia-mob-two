
package com.adaptionsoft.games.trivia.runner;
import java.io.*;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) throws IOException {


		File outputFile =
				new File( "snapshots\\1\\output.txt");
		outputFile.createNewFile();

		PrintStream out = new PrintStream(outputFile);
		System.setOut(out);

		File csvFile =
				new File( "snapshots\\1\\csv.txt");
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
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			csvOut.println(roll + "," + notAWinner);
		} while (notAWinner);

		csvOut.close();


		
	}
}
