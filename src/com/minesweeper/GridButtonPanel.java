package com.minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GridButtonPanel implements Resettable {

    private int gridSize;
    private List<Cell> list = new ArrayList<Cell>();
    private JPanel panel;

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

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
                        + " " + (b.equals(gb)));
                gb.setEnabled(false);
            }
        });
        return b;
    }

    public JPanel getPanel() {
        return panel;
    }

    private void createGridPanel() {
        JPanel p = new JPanel(new GridLayout(gridSize, gridSize));
        for (int i = 0; i < gridSize * gridSize; i++) {
            int row = i / gridSize;
            int col = i % gridSize;
            Cell gb = createGridButton(row, col);
            list.add(gb);
            p.add(gb);
        }
        this.panel = p;
    }

    public GridButtonPanel(int gridSize) {
        this.gridSize = gridSize;
        this.createGridPanel();
    }

    @Override
    public void reset() {
        for (Cell cell:
             list) {
            cell.reset();
        }
    }
}
