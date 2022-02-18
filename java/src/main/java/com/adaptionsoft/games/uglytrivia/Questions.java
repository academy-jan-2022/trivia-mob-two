package com.adaptionsoft.games.uglytrivia;

import java.util.Hashtable;
import java.util.LinkedList;

public class Questions {
    Hashtable<String, LinkedList<String>> questions;

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
        questions = new Hashtable<>();
        questions.put("Pop", popQuestions);
        questions.put("Science", scienceQuestions);
        questions.put("Sports", sportsQuestions);
        questions.put("Rock", rockQuestions);
    }

    public void ask(String category) {
		System.out.println(questions.get(category).removeFirst());
    }
}
