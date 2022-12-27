package com.minesweeper;

import javax.swing.*;

public class MainLoop {
    int gridSize;
    void initialize() {
        Object[] possibleValues = { 10, 20, 30 };
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose the game size", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        gridSize = (int) selectedValue;
//        System.out.println(gridSize);
//        GridButtonPanel cells = new GridButtonPanel(gridSize);
//        cells.display();
        Board board = new Board(gridSize);
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getGridSize() {
        return gridSize;
    }
}