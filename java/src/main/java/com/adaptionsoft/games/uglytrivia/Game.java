package com.adaptionsoft.games.uglytrivia;

import java.util.*;

import static com.adaptionsoft.games.uglytrivia.Game.Category.*;


public class Game {

    public static final int MAX_PLACES = 12;
    Questions questions;
    List<Player> players;
    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        this.questions = new Questions();
        this.players = new ArrayList<>();
    }

    public void add(String playerName) {
        Player newPlayer = new Player(playerName, players.size() + 1);
        players.add(newPlayer);

        System.out.println(
                Messages.GetPlayerAdded(playerName, newPlayer.number)
        );
    }

    public void roll(int roll) {
        if (currentPlayer == null)
            currentPlayer = players.stream().filter(player -> player.number == 1).findFirst().orElseThrow();

        System.out.println(
                Messages.GetPlayerRolled(currentPlayer.name, roll)
        );

        if (currentPlayer.inPenaltyBox) {
            attemptToGetOut(roll);
        } else {
            movePlayer(roll);
            System.out.println(
                    questions.ask(currentCategory())
            );
        }
    }

    private void attemptToGetOut(int roll) {
        if (isOdd(roll)) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(
                    Messages.GetOutOfPenalty(currentPlayer.name)
            );
            movePlayer(roll);
            System.out.println(
                    questions.ask(currentCategory())
            );
        } else {
            System.out.println(
                    Messages.GetNotOutOfPenalty(currentPlayer.name)
            );
            isGettingOutOfPenaltyBox = false;
        }
    }

    private boolean isOdd(int roll) {
        return roll % 2 != 0;
    }

    private void movePlayer(int roll) {
        currentPlayer.place = findNextPlace(roll);

        System.out.println(
                Messages.GetPlayerMoved(currentPlayer.name, currentPlayer.place, currentCategory().value)
        );
    }

    private int findNextPlace(int roll) {
        return (currentPlayer.place + roll) % MAX_PLACES;
    }


    private Category currentCategory() {
        if (categoryTurn() == 0) return POP;
        if (categoryTurn() == 1) return SCIENCE;
        if (categoryTurn() == 2) return SPORTS;
        return ROCK;
    }

    private int categoryTurn() {
        return currentPlayer.place % 4;
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.inPenaltyBox && isGettingOutOfPenaltyBox) {
            currentPlayer.purse++;

            System.out.println(
                    Messages.GetCorrectAnswer(currentPlayer.name, currentPlayer.purse)
            );
        }
        if (currentPlayer.inPenaltyBox) {
            nextPlayer();
            return true;
        }
        correctAnswer("Answer was corrent!!!!");
        nextPlayer();
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
        System.out.println(
                Messages.GetIncorrectAnswer(currentPlayer.name)
        );
        currentPlayer.inPenaltyBox = true;
        nextPlayer();
        return true;
    }

    private void nextPlayer() {
        int nextPlayerNumber = (currentPlayer.number) % players.size() + 1;
        currentPlayer = players
                .stream()
                .filter(player -> player.number == nextPlayerNumber)
                .findFirst()
                .orElseThrow();
    }

    private boolean didPlayerWin() {
        return currentPlayer.purse != 6;
    }

    public enum Category {
        POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

        private final String value;

        Category(String value) {
            this.value = value;
        }

    }
}
