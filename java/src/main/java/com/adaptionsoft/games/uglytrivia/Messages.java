package com.adaptionsoft.games.uglytrivia;

public class Messages {
    private static final String PlayerAdded = "%s was added%nThey are player number %s";
    private static final String PlayerRolled = "%s is the current player%nThey have rolled a %s";
    private static final String OutOfPenalty = "%s is getting out of the penalty box";
    private static final String NotOutOfPenalty = "%s is not getting out of the penalty box";
    private static final String PlayerMoved = "%s's new location is %s%nThe category is %s";
    private static final String CorrectAnswer = "Answer was correct!!!!%n%s now has %s Gold Coins.";
    private static final String IncorrectAnswer = "Question was incorrectly answered%n%s was sent to the penalty box";

    public static String GetPlayerAdded(String playerName, int playerNumber) {
        return String.format(PlayerAdded, playerName, playerNumber);
    }

    public static String GetPlayerRolled(String playerName, int roll) {
        return String.format(PlayerRolled, playerName, roll);
    }

    public static String GetOutOfPenalty(String playerName) {
        return String.format(OutOfPenalty, playerName);
    }

    public static String GetNotOutOfPenalty(String playerName) {
        return String.format(NotOutOfPenalty, playerName);
    }

    public static String GetPlayerMoved(String playerName, int place, String category) {
        return String.format(PlayerMoved, playerName, place, category);
    }

    public static String GetCorrectAnswer(String playerName, int coins) {
        return String.format(CorrectAnswer, playerName, coins);
    }

    public static String GetIncorrectAnswer(String playerName) {
        return String.format(IncorrectAnswer, playerName);
    }
}
