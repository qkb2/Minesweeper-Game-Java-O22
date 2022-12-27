package com.minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GridButtonPanel extends JPanel implements Resettable {

    private int gridSize;
    private List<Cell> list = new ArrayList<>();

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    } // I'll leave it just in case

    private Cell getGridButton(int r, int c) {
        int index = r * gridSize + c;
        return list.get(index);
    }

    private Cell createGridButton(final int row, final int col) {
        Cell b = new Cell("");
        b.reset();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cell gb = GridButtonPanel.this.getGridButton(row, col);
                System.out.println("r" + row + ",c" + col
                        + " " + (b == gb)
                        + " " + (b.equals(gb))); // for testing
                // here you can add the button functionalities on-click
                gb.setEnabled(false);
            }
        });
        return b;
    }

    public GridButtonPanel(int gridSize) {
        super(new GridLayout(gridSize, gridSize));
        this.gridSize = gridSize;
        // sets all the buttons in a grid
        for (int i = 0; i < gridSize * gridSize; i++) {
            int row = i / gridSize;
            int col = i % gridSize;
            Cell gb = createGridButton(row, col);
            list.add(gb);
            add(gb);
        }
    }

    @Override
    public void reset() {
        for (Cell cell:
             list) {
            cell.reset();
        }
    }
}
