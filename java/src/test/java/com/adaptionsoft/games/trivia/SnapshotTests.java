package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;

public class SnapshotTests {



    @ParameterizedTest
    @ValueSource(ints = {
            1,2,3,4,5
    })
    void should_be_same(int fileFolder) throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArray);
        System.setOut(out);

        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        File rollResult = new File("src/test/java/com/adaptionsoft/games/trivia/snapshots/" + fileFolder + "/csv.txt");

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

        File output = new File("src/test/java/com/adaptionsoft/games/trivia/snapshots/" + fileFolder + "/output.txt");

        Assertions.assertEquals(Files.readString(output.toPath()),byteArray.toString());
    }
}
