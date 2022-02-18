package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Game {

    public static final int MAX_PLACES = 12;
    List<Player> players = new ArrayList<>();

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayerIndex = 0;
    Player currentPlayer;

    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        currentPlayer = players.get(currentPlayerIndex);
        System.out.println(currentPlayer.name + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox) {
            attemptToGetOut(roll);
        } else {
            movePlayer(roll);
            askQuestion();
        }

    }

    private void attemptToGetOut(int roll) {
        if (isOdd(roll)) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(currentPlayer.name + " is getting out of the penalty box");
            movePlayer(roll);
            askQuestion();
        } else {
            System.out.println(currentPlayer.name + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
        }
    }

    private boolean isOdd(int roll) {
        return roll % 2 != 0;
    }

    private void movePlayer(int roll) {
        currentPlayer.place = findNextPlace(roll);

        System.out.println(currentPlayer.name + "'s new location is " + currentPlayer.place);
        System.out.println("The category is " + currentCategory());
    }

    private int findNextPlace(int roll) {
        return (currentPlayer.place + roll) % MAX_PLACES;
    }

    private void askQuestion() {
        if (currentCategory().equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (categoryTurn() == 0) return "Pop";
        if (categoryTurn() == 1) return "Science";
        if (categoryTurn() == 2) return "Sports";
        return "Rock";
    }

    private int categoryTurn() {
        return currentPlayer.place % 4;
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.inPenaltyBox) {
            boolean winner = true;
            if (isGettingOutOfPenaltyBox) {
                correctAnswer("Answer was correct!!!!");
                winner = didPlayerWin();
            }
            currentPlayerIndex = nextPlayer();
            return winner;
        }
        correctAnswer("Answer was corrent!!!!");


        currentPlayerIndex = nextPlayer();
        return didPlayerWin();
    }

    private void correctAnswer(String message) {
        System.out.println(message);
        currentPlayer.purse++;
        System.out.println(currentPlayer.name
                + " now has "
                + currentPlayer.purse
                + " Gold Coins.");
    }

    public boolean wrongAnswer() {
        String wrongAnswerText = "Question was incorrectly answered";
        System.out.println(wrongAnswerText);
        System.out.println(currentPlayer.name + " was sent to the penalty box");
        currentPlayer.inPenaltyBox = true;
        currentPlayerIndex = nextPlayer();
        return true;
    }

    private int nextPlayer() {
        return (currentPlayerIndex + 1) % players.size();
    }


    private boolean didPlayerWin() {
        return currentPlayer.purse != 6;
    }
}
