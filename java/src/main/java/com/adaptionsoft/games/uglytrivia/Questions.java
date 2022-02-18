package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.Game.Category;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import static com.adaptionsoft.games.uglytrivia.Game.Category.*;

public class Questions {
    private final Map<Category, LinkedList<String>> questionMap;

    public Questions() {
        LinkedList<String> popQuestions = new LinkedList<>();
        LinkedList<String> scienceQuestions = new LinkedList<>();
        LinkedList<String> sportsQuestions = new LinkedList<>();
        LinkedList<String> rockQuestions = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
        questionMap = new Hashtable<>();
        questionMap.put(POP, popQuestions);
        questionMap.put(SCIENCE, scienceQuestions);
        questionMap.put(SPORTS, sportsQuestions);
        questionMap.put(ROCK, rockQuestions);
    }

    public String ask(Category category) {
        return questionMap.get(category).removeFirst();
    }
}
