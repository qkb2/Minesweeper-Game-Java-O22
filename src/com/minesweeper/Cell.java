package com.minesweeper;

import javax.swing.*;

public class Cell extends JButton implements Resettable {
    private boolean isBomb;
    private boolean isRevealed;
    private int value;

    public void setValue(int my_value) {
        value = my_value;
    }

    public void increseValue(int my_value) {
        if (value != -1) {
            value += my_value;
        }
    }
    public int getValue() {
        return value;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void setRevealed(boolean revealed) {
        System.out.println(revealed);
        isRevealed = revealed;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    Cell(String str) {
        super(str);
        this.isBomb = false;
        this.isRevealed = false;
        this.value = 0;
    }

    @Override
    public void reset() {
        this.isBomb = false;
        this.isRevealed = false;
        this.value = 0;
        this.setText("");
        this.setEnabled(true);
    }
}
