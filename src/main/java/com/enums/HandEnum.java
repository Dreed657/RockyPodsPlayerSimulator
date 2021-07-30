package com.enums;

public enum HandEnum {
    Rock(1),
    Paper(2),
    Scissors(3);

    private int _Hand;

    HandEnum(int hand) {
        this._Hand = hand;
    }

    public int getHand() {
        return this._Hand;
    }
}
