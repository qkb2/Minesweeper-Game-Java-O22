package com.minesweeper;

import javax.swing.*;

public class Cell extends JButton implements Resettable {
    private boolean isBomb;
    private boolean isRevealed;

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void setRevealed(boolean revealed) {
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
    }

    @Override
    public void reset() {
        this.isBomb = false;
        this.isRevealed = false;
        this.setEnabled(true);
    }
}
