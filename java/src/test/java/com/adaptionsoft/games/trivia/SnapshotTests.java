package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

public class SnapshotTests {

    @Test
    void should_be_same() throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArray);
        System.setOut(out);

        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        File rollResult = new File("src/test/java/com/adaptionsoft/games/trivia/snapshots/1/csv.txt");

        Scanner rollInput = new Scanner(rollResult);
        while (rollInput.hasNext()){
            String[] currentRoll = rollInput.next().split(",");
            aGame.roll(Integer.parseInt(currentRoll[0]));
            if(currentRoll[1].equals("true")){
                aGame.wasCorrectlyAnswered();
            }
            else{
                aGame.wrongAnswer();
            }
        }

        out.close();

        File output = new File("src/test/java/com/adaptionsoft/games/trivia/snapshots/1/output.txt");

        Assertions.assertEquals(Files.readString(output.toPath()),byteArray.toString());


    }
}
