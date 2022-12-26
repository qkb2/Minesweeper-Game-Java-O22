package com.minesweeper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements Resettable {
    private JButton newGameButton;
    private JButton giveUpButton;
    private JButton resumeButton;
    private boolean reset;
    private boolean giveUp;
    private GridButtonPanel grid;
    private JFrame mainFrame;

    public void initializeFrame(MainLoop root) {
        mainFrame = new JFrame("Mines");
        int gridSize = root.getGridSize();
        grid = new GridButtonPanel(gridSize);
        JPanel p = grid.getPanel();
        mainFrame.add(p);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset = true;
            }
        });
        giveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { giveUp = true; }
        });

        mainFrame.setSize(400,400);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void reset() {

    }
}
