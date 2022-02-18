package com.adaptionsoft.games.uglytrivia;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class Questions {
    private final Map<String, LinkedList<String>> questionMap;

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
        questionMap.put("Pop", popQuestions);
        questionMap.put("Science", scienceQuestions);
        questionMap.put("Sports", sportsQuestions);
        questionMap.put("Rock", rockQuestions);
    }

    public String ask(String category) {
		return questionMap.get(category).removeFirst();
    }
}
