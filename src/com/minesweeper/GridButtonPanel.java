package com.minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.*;
public class GridButtonPanel extends JPanel implements Resettable {

    private int gridSize;
    private int bombsNum;
    private int cellsRevealed = 0;
    private int cellsToReveal;
    private final double bombsDensity = 0.12;
    private List<Cell> list = new ArrayList<>();
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    } // I'll leave it just in case

    private Cell getGridButton(int r, int c) {
        int index = r * gridSize + c;
        return list.get(index);
    }

    private void revealSurrounding(Cell b, MouseEvent e, int row, int col) {
        // left top
        if (row -  1 >= 0 && col - 1 >= 0) {
            if (getGridButton(row -  1, col - 1).isEnabled()) {
                mouseClickAction(getGridButton(row -  1, col - 1), e, row - 1, col - 1);
            }
        }
        // left
        if (col - 1 >= 0) {
            if (getGridButton(row, col - 1).isEnabled()) {
                mouseClickAction(getGridButton(row, col - 1), e, row, col - 1);
            }
        }
        // left bottom
        if (row +  1 < gridSize && col - 1 >= 0) {
            if (getGridButton(row +  1, col - 1).isEnabled()) {
                mouseClickAction(getGridButton(row +  1, col - 1), e, row + 1, col - 1);
            }
        }
        // bottom
        if (row +  1 < gridSize) {
            if (getGridButton(row +  1, col).isEnabled()) {
                mouseClickAction(getGridButton(row +  1, col), e, row + 1, col);
            }
        }
        // right bottom
        if (row +  1 < gridSize && col + 1 < gridSize) {
            if (getGridButton(row +  1, col + 1).isEnabled()) {
                mouseClickAction(getGridButton(row +  1, col + 1), e, row + 1, col + 1);
            }
        }
        // right
        if (col + 1 < gridSize) {
            if (getGridButton(row, col + 1).isEnabled()) {
                mouseClickAction(getGridButton(row, col + 1), e, row, col + 1);
            }
        }
        // right top
        if (row -  1 >= 0 && col + 1 < gridSize) {
            if (getGridButton(row -  1, col + 1).isEnabled()) {
                mouseClickAction(getGridButton(row -  1, col + 1), e, row - 1, col + 1);
            }
        }
        // top
        if (row -  1 >= 0) {
            if (getGridButton(row -  1, col).isEnabled()) {
                mouseClickAction(getGridButton(row -  1, col), e, row - 1, col);
            }
        }
    }

    private void mouseClickAction(Cell b, MouseEvent e, int row, int col) {
        if (SwingUtilities.isRightMouseButton(e)) {
            if (b.isEnabled()) {
                if (b.getText() == "!") b.setText("");
                else b.setText("!");
            }
        }
        else if (SwingUtilities.isLeftMouseButton(e) ) {
            if (!b.isEnabled()) {return;}

            b.setEnabled(false);
            if (b.getValue() == -1) {
                b.setText("B");
                gameOver();
            }
            // Cascade reveal
            else {
                cellsRevealed += 1;
                if (b.getValue() == 0) {
                    revealSurrounding(b, e, row, col);
                    b.setText("");
                } else b.setText(Integer.toString(b.getValue()));

                if (cellsRevealed == cellsToReveal) gameWon();
                else {int tmp = cellsToReveal - cellsRevealed;}
            }
        }
    }



    private Cell createGridButton(final int row, final int col) {
        Cell b = new Cell("");
        b.reset();
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseClickAction(b, e, row, col);
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
            gb.setMargin(new Insets(0, 0, 0, 0));
            list.add(gb);
            add(gb);
        }
    }
    private void increaseSurrounding(int row, int col) {
        // left top
        if (row -  1 >= 0 && col - 1 >= 0) {
            getGridButton(row -  1, col - 1).increseValue(1);
        }
        // left
        if (col - 1 >= 0) {
            getGridButton(row, col - 1).increseValue(1);
        }
        // left bottom
        if (row +  1 < gridSize && col - 1 >= 0) {
            getGridButton(row +  1, col - 1).increseValue(1);
        }
        // bottom
        if (row +  1 < gridSize) {
            getGridButton(row +  1, col).increseValue(1);
        }
        // right bottom
        if (row +  1 < gridSize && col + 1 < gridSize) {
            getGridButton(row +  1, col + 1).increseValue(1);
        }
        // right
        if (col + 1 < gridSize) {
            getGridButton(row, col + 1).increseValue(1);
        }
        // right top
        if (row -  1 >= 0 && col + 1 < gridSize) {
            getGridButton(row -  1, col + 1).increseValue(1);
        }
        // top
        if (row -  1 >= 0) {
            getGridButton(row -  1, col).increseValue(1);
        }
    }

    public void placeBombsOnGrid() {
        bombsNum = (int)(this.gridSize * this.gridSize * this.bombsDensity);
        cellsToReveal = gridSize * gridSize - bombsNum;
        int tmp_random_num;
        Random r = new Random();
        for (int i = 0; i < bombsNum; i++) {
            while (true) {
                tmp_random_num = r.nextInt((this.gridSize * this.gridSize));
                if (!list.get(tmp_random_num).isBomb()) {
                    UIManager.put("Button.disabledText", Color.black);
                    repaint();
                    list.get(tmp_random_num).setBomb(true);
                    list.get(tmp_random_num).setValue(-1);
                    int row = tmp_random_num / gridSize;
                    int col = tmp_random_num % gridSize;
                    increaseSurrounding(row, col);
                    break;
                }
            }
        }
    }

    @Override
    public void reset() {
        for (Cell cell:
                list) {
            cell.reset();
        }
        cellsRevealed = 0;
        placeBombsOnGrid();
    }
    public void showAnswer() {
        for (Cell cell:
                list) {
            if (cell.isEnabled()) {
                cell.setEnabled(false);
                if (cell.getValue() == -1) {
                    cell.setText("B");
                }
                else {
                    if (cell.getValue() != 0) {
                        cell.setText(Integer.toString(cell.getValue()));
                    }
                }
            }
        }
    }

    private void gameOver() {
        JPanel frame = new JPanel();
        JOptionPane.showMessageDialog(frame,
                "You revealed the bomb.",
                "Game Over !!!",
                JOptionPane.PLAIN_MESSAGE);
        showAnswer();
    }

    private void gameWon() {
        JPanel frame = new JPanel();
        JOptionPane.showMessageDialog(frame,
                "All bombs are found.",
                "You won !!!",
                JOptionPane.PLAIN_MESSAGE);
        showAnswer();
    }
}
