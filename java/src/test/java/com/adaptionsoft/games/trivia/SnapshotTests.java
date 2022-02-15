package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SnapshotTests {

    @Test
    void snapshot_test_one(){
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random();

        var notAWinner = true;
        do {

            aGame.roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }



        } while (notAWinner);
    }
}
