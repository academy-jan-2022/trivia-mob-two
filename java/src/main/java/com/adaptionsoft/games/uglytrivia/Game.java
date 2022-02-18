package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
    	}
    }

	public boolean add(String playerName) {

	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				movePlayer(roll);
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
			isGettingOutOfPenaltyBox = false;
				}
			
		} else {
			movePlayer(roll);
		}
		
	}

	private void movePlayer(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

		System.out.println(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
		askQuestion();
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
		return places[currentPlayer] % 4;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			boolean winner = true;
			if (isGettingOutOfPenaltyBox) {
				String correctAnswerText = "Answer was correct!!!!";
				System.out.println(correctAnswerText);
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				winner = didPlayerWin();
			}
				currentPlayer ++;
				resetCurrentPlayer();
				return winner;
		}
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			currentPlayer ++;
			resetCurrentPlayer();
			return didPlayerWin();
	}
	
	public boolean wrongAnswer(){
		String wrongAnswerText = "Question was incorrectly answered";
		System.out.println(wrongAnswerText);
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		currentPlayer ++;
		resetCurrentPlayer();
		return true;
	}

	private void resetCurrentPlayer() {
		if (currentPlayer == players.size()) currentPlayer = 0;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
