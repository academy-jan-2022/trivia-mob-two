package com.adaptionsoft.games.uglytrivia;

public class Player {

    public String name;
    public int purse;
    public int place;
    public int number;
    public boolean inPenaltyBox;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
