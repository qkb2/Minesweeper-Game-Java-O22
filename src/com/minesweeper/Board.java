package com.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements Resettable {
    private boolean reset;
    private boolean giveUp;
    private GridButtonPanel gameGrid;
    private JPanel buttonPane;
    int gridSize;
    private JButton resetButton;
    private JButton giveUpButton;
    private Container contentPane;

    public Board(int gridSize) {
        super("Mines");

        // creates the center button grid by calling the GridButtonPanel constructor which creates it automatically
        this.gridSize = gridSize;
        gameGrid = new GridButtonPanel(gridSize);

        // creates the lower button pane for reset and give up buttons
        resetButton = new JButton("Reset");
        giveUpButton = new JButton("Give up?");
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(resetButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(giveUpButton);

        contentPane = getContentPane();
        contentPane.add(gameGrid, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        // settings etc.
        setSize(500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // listeners for button action
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        giveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAnswer();
            }
        });
    }

    @Override
    public void reset() {
        System.out.println("reset"); // for testing
        // maybe call reset on grid which will call reset on all buttons
    }

    public void showAnswer() {
        System.out.println("ans"); // for testing
        // probably sth similar to reset but w/out the starting new game and w/ showing the bombs? reveal all?
    }
}
